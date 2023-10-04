package scanner;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PushbackReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import customException.LexicalException;
import token.*;

/**
 * Lo scanner legge l’istruzione e genera un token e lo passa al parser, scompone il programma in token. 
 * Lo scanner e quindi l’analizzatore lessicale vengono rappresentati da automi a stati finiti deterministici.
 * Il lessico del linguaggio è specificato da una grammatica regolare tramite espressioni regolari
 * @author Matteo Schirinzi 20035542
 */
public class Scanner {
	final char EOF = (char) -1; // int 65535
	private int riga;
	private PushbackReader buffer;

	// skpChars: insieme caratteri di skip (include EOF) e inizializzazione
	private ArrayList<Character> skpChars;
	
	// letters: insieme lettere e inizializzazione
	private ArrayList<Character> letters;
			
	// digits: cifre e inizializzazione
	private ArrayList<Character> digits;

	// operatorsMap: mapping fra caratteri '+', '-', '*', '/', '=', ';' e il TokenType corrispondente
	private HashMap<Character, TokenType> operatorsMap;
	
	// keyWordsMap: mapping fra le stringhe "print", "float", "int" e il TokenType corrispondente
	private HashMap<String, TokenType> keyWordsMap;
	
	//	currentToken
	private Token currentToken;

	/**
	 * @author Matteo Schirinzi 20035542
	 * @param fileName - file di input da scanerizzare 
	 * @throws LexicalException - incapsula una FileNotFoundException nel caso il file non venga trovato
	 * Inizializza la riga a 1, chiama la funzione initField() per l'inizializzazione dei campi della classe
	 * e prende come input il nome di un file e costruisce un PushbackReader
	 */
	public Scanner(String fileName) throws LexicalException {

		try {
			this.buffer = new PushbackReader(new FileReader(fileName));
		}
		catch(FileNotFoundException e) {
			
			throw new LexicalException("File non trovato", e);
		}
		
		this.riga = 1;
		
		//inizializzare campi che non hanno inizializzazione
		this.initField();
			
	}
	
	/**
	 * @author Matteo Schirinzi 20035542
	 * @return currentToken - restituisce il Token letto
	 * @throws LexicalException
	 * Restituisce il prossimo token dell’input senza consumarlo
	 */
	public Token peekToken() throws LexicalException {
		if(this.currentToken == null) return this.currentToken = this.nextToken();
		return this.currentToken;
	}

	/**
	 * @author Matteo Schirinzi 20035542
	 * @return token - restituisce il Token letto
	 * @throws LexicalException - emette un'eccezione di tipo lessicale in caso di errore nella lettura di un token dal file
	 * Restituisce il prossimo token dell’input
	 */
	public Token nextToken() throws LexicalException {
		
		if(this.currentToken != null)
		{
			Token t = this.currentToken;
			this.currentToken = null;
			return t;
		}
		
		// nextChar contiene il prossimo carattere dell'input.
		char nextChar = peekChar();

		// Avanza nel buffer leggendo i carattere in skipChars
		while(this.skpChars.contains(nextChar)) {
			
			//incrementando riga se leggi '\n'
			if(nextChar == '\n')
				this.riga++;
			
			// Se raggiungi la fine del file ritorna il Token EOF
			if(nextChar == EOF) 
				return new Token(TokenType.EOF, this.riga);
			
			this.readChar();
			nextChar = this.peekChar();
		}
		
		// Se nextChar e' in numbers return scanNumber()
		if(this.digits.contains(nextChar)) return this.scanNumber();

		// Se nextChar e' in letters return scanId()
		if(this.letters.contains(nextChar))	return this.scanId();

		// Se nextChar e' in operators ritorna il Token associato con l'operatore o il delimitatore
		if(this.operatorsMap.containsKey(nextChar))	return new Token((TokenType)this.operatorsMap.get(this.readChar()), this.riga);

		// Altrimenti il carattere NON E' UN CARATTERE LEGALE 
		this.readChar();	//consumo il carattere di errore
		throw new LexicalException("Il carattere '" + nextChar + "' alla riga " + this.riga + " non è legale");
		
	}

	
	/**
	 * @author Matteo Schirinzi 20035542
	 * @return token - restituisce il Token letto
	 * @throws LexicalException - emette un'eccezione di tipo lessicale in caso di errore nella lettura di un token dal file
	 * Legge sia un intero che un float e ritorna il Token INUM o FNUM, i caratteri letti sono accumulati 
	 * in una stringa che verra' assegnata al campo valore del Token
	 */
	private Token scanNumber() throws LexicalException {
		
		char nextChar;
		String number = "";
		int cont_float = 0;
		
		nextChar = peekChar();
		if(nextChar == '0')
		{
			number += this.readChar();
			nextChar = peekChar();
			if(this.digits.contains(nextChar) || this.letters.contains(nextChar))
			{
				this.readChar(); //consumo il carattere che ha causato l'errore
				throw new LexicalException("Errore: il carattere che segue zero alla riga: " + this.riga + " non è valido");
			}
		}
		
		
		while(this.digits.contains(nextChar)){
			number += nextChar;
			this.readChar(); //consumo il carattere
			nextChar = this.peekChar();
		}
		
		if(this.operatorsMap.containsKey(nextChar) || this.skpChars.contains(nextChar)) 
			return new Token(TokenType.INT, this.riga, number);
		
		if(this.letters.contains(nextChar)) 
		{
			this.readChar(); //consumo il carattere che ha generato errore
			throw new LexicalException("Errore: numero non valido (lettera rilevata) alla riga: " + this.riga);
		}
		
		if(nextChar == '.') {
			number += nextChar;
			this.readChar(); //consumo il carattere
			nextChar = this.peekChar();
			while(this.digits.contains(nextChar)) {
				number += nextChar;
				this.readChar(); //consumo il carattere
				nextChar = this.peekChar();
				cont_float ++;
				if((this.skpChars.contains(nextChar) || this.operatorsMap.containsKey(nextChar)) && cont_float < 6 && cont_float > 0) 
					return new Token(TokenType.FLOAT, this.riga, number);
			}
			if(nextChar == '.') 
			{
				this.readChar(); //consumo il carattere che ha generato errore
				throw new LexicalException("Errore: numero non valido (doppio punto rilevato) alla riga: " + this.riga);
			}
			if(this.letters.contains(nextChar)) 
			{
				this.readChar(); //consumo il carattere che ha generato errore
				throw new LexicalException("Errore: numero non valido (lettera rilevata) alla riga: " + this.riga);
			}
			if(cont_float >= 6) throw new LexicalException("Errore: numero non valido (troppi numeri dopo la virgola) alla riga: " + this.riga);
			if(cont_float < 1) throw new LexicalException("Errore: numero non valido (nessun numero dopo la virgola) alla riga: " + this.riga);
		}
		
		//Altrimenti errore sul numero
		throw new LexicalException("Errore: numero '" + number + "' non valido alla riga: " + this.riga);
		
		
	}
	
	/**
	 * @author Matteo Schirinzi 20035542
	 * @return token - restituisce il Token letto
	 * @throws LexicalException - emette un'eccezione di tipo lessicale in caso di errore nella lettura di un token dal file
	 * Legge tutte le lettere minuscole e ritorna un Token ID o il Token associato Parola Chiave 
	 * (per generare i Token per le parole viene usato l'HashMap corrispondente)
	 */
	private Token scanId() throws LexicalException {
	
		String caratteriLetti = "";
		char nextChar;
		
		nextChar = this.peekChar();
		
		while(this.letters.contains(nextChar)){
			caratteriLetti += nextChar;
			this.readChar(); //consumo il carattere
			nextChar = this.peekChar();
		}
		
		if(this.digits.contains(nextChar))
		{
			caratteriLetti += this.readChar();
			throw new LexicalException("Numeri rilevati in: '" + caratteriLetti + "' alla riga: " + this.riga);
		}
		
		if(nextChar == ';' || nextChar == '.' || this.skpChars.contains(nextChar) || nextChar == '=' || this.operatorsMap.containsKey(nextChar)) {
			if(this.keyWordsMap.containsKey(caratteriLetti))
				return new Token (this.keyWordsMap.get(caratteriLetti), this.riga);
			else
				return new Token (TokenType.ID, this.riga, caratteriLetti);
		}
		
		//Altrimenti c'è un errore nella lettura dell'id
		throw new LexicalException("Errore: parola '" + caratteriLetti + "' non valida alla riga: " + this.riga);
		
		
	}
	
	/**
	 * @author Matteo Schirinzi 20035542
	 * @return char - carattere prelevato dal file
	 * @throws LexicalException - incapsula l'errore di IO nel caso di lettura errata dal file
	 * Legge il carattere dal file e lo restituisce, consumando il carattere
	 */
	private char readChar() throws LexicalException {
		try {
			return ((char) this.buffer.read());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			throw new LexicalException("Errore di IO nella readChar", e);
		}
	}
	
	/**
	 * @author Matteo Schirinzi 20035542
	 * @return char - carattere prelevato dal file
	 * @throws LexicalException - incapsula l'errore di IO nel caso di lettura errata dal file
	 * Legge il carattere e lo restituisce, poi torna indietro di un carattere (quindi legge senza consumare il carattere)
	 */
	private char peekChar() throws LexicalException {
		char c;
		try {
			c = (char) buffer.read();
			buffer.unread(c);	
			return c;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			throw new LexicalException("Errore di IO nella peekChar", e);
		}
		
	}
	
	/**
	 * @author Matteo Schirinzi 20035542
	 * Inizializzazione i campi della classe
	 */
	private void initField() {
		this.skpChars = new ArrayList<>(Arrays.asList(new Character[] {' ', '\n', '\t', '\r', EOF}));
		
		this.letters = new ArrayList<>(Arrays.asList(new Character[] {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'}));
		
		this.digits = new ArrayList<>(Arrays.asList(new Character[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' })); 
		
		this.operatorsMap = new HashMap<>();
		this.operatorsMap.put('+', TokenType.PLUS);
		this.operatorsMap.put('-', TokenType.MINUS);
		this.operatorsMap.put('*', TokenType.TIMES);
		this.operatorsMap.put('/', TokenType.DIV);
		this.operatorsMap.put('=', TokenType.ASSIGN);
		this.operatorsMap.put(';', TokenType.SEMI);
		
		this.keyWordsMap = new HashMap<>();
		this.keyWordsMap.put("print", TokenType.PRINT);
		this.keyWordsMap.put("float", TokenType.TYFLOAT);
		this.keyWordsMap.put("int", TokenType.TYINT);
		
		this.currentToken = null;
	}
}
