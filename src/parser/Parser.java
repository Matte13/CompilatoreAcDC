package parser;

import java.util.ArrayList;

import ast.LangOper;
import ast.LangType;
import ast.NodeAssign;
import ast.NodeBinOp;
import ast.NodeCost;
import ast.NodeDecSt;
import ast.NodeDecl;
import ast.NodeDeref;
import ast.NodeExpr;
import ast.NodeId;
import ast.NodePrint;
import ast.NodeProgram;
import ast.NodeStm;
import customException.LexicalException;
import customException.SyntaxException;
import scanner.Scanner;
import token.Token;
import token.TokenType;

/**
 * Il parser sintetizza l’Abstract Syntax Tree (AST), che è un albero di parsing in forma astratta nella fase di analisi sintattica. 
 * Si utilizza l’AST per l’analisi semantica e poi per la sintesi dell’output.
 * In questo compilatore l’AST viene utilizzato per fare analisi semantica ed elaborazione dell’output.
 * @author Matteo Schirinzi 20035542
 */
public class Parser {

	private final Scanner scanner;

	/**
	 * @author Matteo Schirinzi 20035542
	 * @param scanner - Scanner da memorizzare nel campo privato scanner
	 */
	public Parser(Scanner scanner) {
		this.scanner = scanner;
	}
	
	/**
	 * @author Matteo Schirinzi 20035542
	 * @title Parser - match
	 * @param tipo - tipo di token con cui fare la match
	 * @return tk - restituisce il Token dopo il match
	 * @throws SyntaxException - errore di tipo sintattico nel caso si aspettava un tokentype differente
	 * Controlla che il prossimo token dello stream abbia uno specifico tipo lo consuma e lo ritorna, altrimenti produce un ERRORE.
	 */
	private Token match(TokenType tipo) throws SyntaxException{
		Token tk;
		try {
			tk = this.scanner.peekToken();
			if(tipo.equals(tk.getTipo())) return this.scanner.nextToken();
		}
		catch(LexicalException e) {
			throw new SyntaxException("Lexical Exception", e);
		}
		
		throw new SyntaxException("ErroreSintattico: Aspettato " + tipo + " token invece di " + tk.getTipo() + " alla riga " + tk.getRiga());
	}
	
	/**
	 * @author Matteo Schirinzi 20035542
	 * @return nodeProgram - restituisce il nodo principale NodeProgram
	 * @throws SyntaxException - gestisce eventuali errori sintattici 
	 * Richiama la parsePrg che ritorna senza dare errori se il programma è sintatticamente corretto
	 */
	public NodeProgram parse() throws SyntaxException {
		return this.parsePrg();
	}
	
	/**
	 * @author Matteo Schirinzi 20035542
	 * @return nodeProgram - restituisce il nodo principale NodeProgram
	 * @throws SyntaxException - gestisce eventuali errori sintattici (includendo riga e token in errore)
	 * Ritorna senza dare errori se il programma è sintatticamente corretto altrimenti lancia un eccezione sintattica
	 */
	private NodeProgram parsePrg() throws SyntaxException {
		Token tk;
		try {
			tk = this.scanner.peekToken();
		}
		catch(LexicalException e) {
			throw new SyntaxException("Lexical Exception", e);
		}
		switch (tk.getTipo()) {
			case TYFLOAT, TYINT, ID, PRINT, EOF:
				ArrayList<NodeDecSt> dec = parseDSs();
				match(TokenType.EOF);
				return new NodeProgram(dec);
		default:
			// ErroreSintattico token tk alla riga tk.getRiga()
			throw new SyntaxException("ErroreSintattico: Il token " + tk.getTipo() + " alla riga " + tk.getRiga());
		}
	}
	
	/**
	 * @author Matteo Schirinzi 20035542
	 * @return ArrayList[NodeDecSt] - restituisce l'array list creato
	 * @throws SyntaxException - gestisce eventuali errori sintattici (includendo riga e token in errore)
	 */
	private ArrayList<NodeDecSt> parseDSs() throws SyntaxException {
		Token tk;
		try {
			tk = this.scanner.peekToken();
		}
		catch(LexicalException e) {
			throw new SyntaxException("Lexical Exception", e);
		}
		switch (tk.getTipo()) {
			case TYFLOAT, TYINT:
				NodeDecl dec = parseDcl();
				ArrayList<NodeDecSt> decs = parseDSs();
				decs.add(0, dec);
				return decs;
			case ID, PRINT:
				NodeStm stm = parseStm();
				ArrayList<NodeDecSt> decs2 = parseDSs();
				decs2.add(0, stm);
				return decs2;
			case EOF:
				return new ArrayList<NodeDecSt>();
		default:
			// ErroreSintattico token tk alla riga tk.getRiga()
			throw new SyntaxException("ErroreSintattico: Il token " + tk.getTipo() + " alla riga " + tk.getRiga());
		}
	}
	
	/**
	 * @author Matteo Schirinzi 20035542
	 * @return nodeDecl - restituisce un nuovo nodo dichiarazione
	 * @throws SyntaxException - gestisce eventuali errori sintattici (includendo riga e token in errore)
	 */
	private NodeDecl parseDcl() throws SyntaxException {
		Token tk;
		try {
			tk = this.scanner.peekToken();
		}
		catch(LexicalException e) {
			throw new SyntaxException("Lexical Exception", e);
		}
		switch (tk.getTipo()) {
			case TYFLOAT, TYINT:
				LangType type = parseTy();
				Token tkId = match(TokenType.ID);
				NodeExpr init = parseDclP();
				return new NodeDecl(new NodeId(tkId.getVal()), type, init);
		default:
			// ErroreSintattico token tk alla riga tk.getRiga()
			throw new SyntaxException("ErroreSintattico: Il token " + tk.getTipo() + " alla riga " + tk.getRiga());
		}
	}
	
	/**
	 * @author Matteo Schirinzi 20035542
	 * @return nodeExpr - restituisce un nodo espressione
	 * @throws SyntaxException - gestisce eventuali errori sintattici (includendo riga e token in errore)
	 */
	private NodeExpr parseDclP() throws SyntaxException {
		Token tk;
		try {
			tk = this.scanner.peekToken();
		}
		catch(LexicalException e) {
			throw new SyntaxException("Lexical Exception", e);
		}
		switch (tk.getTipo()) {
			case SEMI:
				match(TokenType.SEMI);
				return null;	// non ho inizializzazione
			case ASSIGN:
				match(TokenType.ASSIGN);
				NodeExpr expr = parseExp();
				match(TokenType.SEMI);
				return expr;
		default:
			// ErroreSintattico token tk alla riga tk.getRiga()
			throw new SyntaxException("ErroreSintattico: Il token " + tk.getTipo() + " alla riga " + tk.getRiga());
		}
		
	}
	
	/**
	 * @author Matteo Schirinzi 20035542
	 * @return nodeStm - restituisce un nodo di tipo statement (print o id)
	 * @throws SyntaxException - gestisce eventuali errori sintattici (includendo riga e token in errore)
	 */
	private NodeStm parseStm() throws SyntaxException {
		Token tk;
		try {
			tk = this.scanner.peekToken();
		}
		catch(LexicalException e) {
			throw new SyntaxException("Lexical Exception", e);
		}
		switch (tk.getTipo()) {
			case ID:
				Token tkId = match(TokenType.ID);
				match(TokenType.ASSIGN);
				NodeExpr expr = parseExp();
				match(TokenType.SEMI);
				return new NodeAssign(new NodeId(tkId.getVal()), expr);
			case PRINT:
				match(TokenType.PRINT);
				Token tkId2 = match(TokenType.ID);
				match(TokenType.SEMI);
				return new NodePrint(new NodeId(tkId2.getVal()));
		default:
			// ErroreSintattico token tk alla riga tk.getRiga()
			throw new SyntaxException("ErroreSintattico: Il token " + tk.getTipo() + " alla riga " + tk.getRiga());
		}
	}
	
	/**
	 * @author Matteo Schirinzi 20035542
	 * @return nodeExpr - resituisce un nodo espressione
	 * @throws SyntaxException - gestisce eventuali errori sintattici (includendo riga e token in errore)
	 */
	private NodeExpr parseExp() throws SyntaxException {
		Token tk;
		try {
			tk = this.scanner.peekToken();
		}
		catch(LexicalException e) {
			throw new SyntaxException("Lexical Exception", e);
		}
		switch (tk.getTipo()) {
			case INT, FLOAT, ID:
				NodeExpr left = parseTr();
				return parseExpP(left);
		default:
			// Errore Sintattico token tk alla riga tk.getRiga() 
			throw new SyntaxException("ErroreSintattico: Il token " + tk.getTipo() + " alla riga: " + tk.getRiga());
		}
	}
	
	/**
	 * @author Matteo Schirinzi 20035542
	 * @param left - espressione sinistra
	 * @return nodeExpr - restituisce un nodo espressione
	 * @throws SyntaxException - gestisce eventuali errori sintattici (includendo riga e token in errore)
	 */
	private NodeExpr parseExpP(NodeExpr left) throws SyntaxException {
		Token tk;
		try {
			tk = this.scanner.peekToken();
		}
		catch(LexicalException e) {
			throw new SyntaxException("Lexical Exception", e);
		}
		switch (tk.getTipo()) {
			case PLUS:
				match(TokenType.PLUS);
				NodeExpr terP = parseTr();
				NodeBinOp expP = new NodeBinOp (left, terP, LangOper.PLUS);
				return parseExpP(expP);
			case MINUS:
				match(TokenType.MINUS);
				NodeExpr terM = parseTr();
				NodeBinOp expM = new NodeBinOp(left, terM, LangOper.MINUS);
				return parseExpP(expM);
			case SEMI:
				return left;
		default:
			// ErroreSintattico token tk alla riga tk.getRiga()
			throw new SyntaxException("ErroreSintattico: Il token " + tk.getTipo() + " alla riga " + tk.getRiga());
		}
	}
	
	/**
	 * @author Matteo Schirinzi 20035542
	 * @return nodeExpr - restituisce un nodo espressione
	 * @throws SyntaxException - gestisce eventuali errori sintattici (includendo riga e token in errore)
	 */
	private NodeExpr parseTr() throws SyntaxException {
		Token tk;
		try {
			tk = this.scanner.peekToken();
		}
		catch(LexicalException e) {
			throw new SyntaxException("Lexical Exception", e);
		}
		switch (tk.getTipo()) {
			case INT, FLOAT, ID:
				NodeExpr expr = parseVal();
				return parseTrP(expr);
		default:
			// ErroreSintattico token tk alla riga tk.getRiga()
			throw new SyntaxException("ErroreSintattico: Il token " + tk.getTipo() + " alla riga " + tk.getRiga());
		}
	}
	
	/**
	 * @author Matteo Schirinzi 20035542
	 * @param left - espressione sinistra
	 * @return nodeExpr - resituisce un nodo espressione
	 * @throws SyntaxException - gestisce eventuali errori sintattici (includendo riga e token in errore)
	 */
	private NodeExpr parseTrP(NodeExpr left) throws SyntaxException {
		Token tk;
		try {
			tk = this.scanner.peekToken();
		}
		catch(LexicalException e) {
			throw new SyntaxException("Lexical Exception", e);
		}
		switch (tk.getTipo()) {
			case TIMES:
				match(TokenType.TIMES);
				NodeExpr expr = parseVal();
				return parseTrP(new NodeBinOp(left, expr, LangOper.TIMES));
			case DIV:
				match(TokenType.DIV);
				NodeExpr expr2 = parseVal();
				return parseTrP(new NodeBinOp(left, expr2, LangOper.DIV));
			case PLUS, MINUS, SEMI:
				return left;
		default:
			// ErroreSintattico token tk alla riga tk.getRiga()
			throw new SyntaxException("ErroreSintattico: Il token " + tk.getTipo() + " alla riga " + tk.getRiga());
		}
	}
	
	/**
	 * @author Matteo Schirinzi 20035542
	 * @return langType - resituisce il tipo del token dopo la match
	 * @throws SyntaxException - gestisce eventuali errori sintattici (includendo riga e token in errore)
	 */
	private LangType parseTy() throws SyntaxException {
		Token tk;
		try {
			tk = this.scanner.peekToken();
		}
		catch(LexicalException e) {
			throw new SyntaxException("Lexical Exception", e);
		}
		switch (tk.getTipo()) {
			case TYFLOAT:
				match(TokenType.TYFLOAT);
				return LangType.FLOATy;
			case TYINT:
				match(TokenType.TYINT);
				return LangType.INTy;
		default:
			// ErroreSintattico token tk alla riga tk.getRiga()
			throw new SyntaxException("ErroreSintattico: Il token " + tk.getTipo() + " alla riga " + tk.getRiga());
		}
	}
	
	/**
	 * @author Matteo Schirinzi 20035542
	 * @return nodeExpr - viene restituito o un NodeCost o un NodeDeref
	 * @throws SyntaxException - gestisce eventuali errori sintattici (includendo riga e token in errore)
	 */
	private NodeExpr parseVal() throws SyntaxException {
		Token tk;
		try {
			tk = this.scanner.peekToken();
		}
		catch(LexicalException e) {
			throw new SyntaxException("Lexical Exception", e);
		}
		switch (tk.getTipo()) {
			case INT:
				Token tInt = match(TokenType.INT);
				return new NodeCost(tInt.getVal(), LangType.INTy);
			case FLOAT:
				Token tFloat = match(TokenType.FLOAT);
				return new NodeCost(tFloat.getVal(), LangType.FLOATy);
			case ID:
				Token tId = match(TokenType.ID);
				return new NodeDeref(new NodeId(tId.getVal()));
		default:
			// ErroreSintattico token tk alla riga tk.getRiga()
			throw new SyntaxException("ErroreSintattico: Il token " + tk.getTipo() + " alla riga " + tk.getRiga());
		}
	}
		
	
}
