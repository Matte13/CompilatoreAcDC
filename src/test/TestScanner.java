package test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import customException.LexicalException;
import scanner.Scanner;
import token.Token;
import token.TokenType;

class TestScanner {
	
	private String path;
	private Scanner scanner;
	
	void init(String path) {
		try {
			this.scanner = new Scanner(path);
		} catch (LexicalException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	void testSkipChars() {
		this.path = "./src/test/data/testScanner/testEOF.txt";
		try {
			this.init(path);
			assertEquals("<"+TokenType.EOF+",r:3>", this.scanner.nextToken().toString());
		} catch (LexicalException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	
	@Test
	void testInt() {
		this.path = "./src/test/data/testScanner/testINT.txt";
		try {
			this.init(path);
			assertEquals("<"+TokenType.INT+",r:2,698>", this.scanner.nextToken().toString());
		} catch (LexicalException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	void testFloat() {
		this.path = "./src/test/data/testScanner/testFLOAT.txt";
		Throwable exception;
		
		try {
			this.init(path);
			
			exception = assertThrows(LexicalException.class, () -> this.scanner.nextToken());
		    assertEquals("Errore: il carattere che segue zero alla riga: 1 non è valido", exception.getMessage());
		    
			assertEquals("<"+TokenType.FLOAT+",r:1,8.895>", this.scanner.nextToken().toString());
			
			exception = assertThrows(LexicalException.class, () -> this.scanner.nextToken());
		    assertEquals("Errore: numero non valido (nessun numero dopo la virgola) alla riga: 3", exception.getMessage()); 
		    
		    exception = assertThrows(LexicalException.class, () -> this.scanner.nextToken());
		    assertEquals("Errore: numero non valido (troppi numeri dopo la virgola) alla riga: 5", exception.getMessage());	
		    
		    assertEquals("<"+TokenType.FLOAT+",r:7,5.0>", this.scanner.nextToken().toString());
		    
		    assertEquals("<"+TokenType.FLOAT+",r:8,99.89>", this.scanner.nextToken().toString());
		} catch (LexicalException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	}

	@Test
	void testID() {
		this.path = "./src/test/data/testScanner/testID.txt";
		try {
			this.init(path);
			assertEquals("<"+TokenType.ID+",r:1,jskjdsfhkjdshkf>", this.scanner.nextToken().toString());
			assertEquals("<"+TokenType.ID+",r:2,printl>",  this.scanner.nextToken().toString());
			assertEquals("<"+TokenType.ID+",r:4,hhhjj>",  this.scanner.nextToken().toString());
		} catch (LexicalException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Test 
	void testKeywoards() {
		this.path = "./src/test/data/testScanner/testKeywords.txt";
		try {
			this.init(path);
			assertEquals("<"+TokenType.PRINT+",r:2>", this.scanner.nextToken().toString());
			assertEquals("<"+TokenType.TYFLOAT+",r:2>", this.scanner.nextToken().toString());
			assertEquals("<"+TokenType.TYINT+",r:5>", this.scanner.nextToken().toString());
		} catch (LexicalException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Test
	void testOperators() {
		this.path = "./src/test/data/testScanner/testOperators.txt";
		try {
			this.init(path);
			
			assertEquals("<"+TokenType.PLUS+",r:1>", this.scanner.nextToken().toString());
			
			assertEquals("<"+TokenType.MINUS+",r:2>", this.scanner.nextToken().toString());
			assertEquals("<"+TokenType.TIMES+",r:2>", this.scanner.nextToken().toString());
			
			assertEquals("<"+TokenType.DIV+",r:3>", this.scanner.nextToken().toString());
			
			assertEquals("<"+TokenType.ASSIGN+",r:8>", this.scanner.nextToken().toString());
			
			assertEquals("<"+TokenType.SEMI+",r:10>", this.scanner.nextToken().toString());
		} catch (LexicalException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Test
	void testScanID() {
		this.path = "./src/test/data/testScanner/testScanID.txt";
		try {
			this.init(path);
			
			assertEquals("<"+TokenType.TYINT+",r:1>", this.scanner.nextToken().toString());
			
			assertEquals("<"+TokenType.TYFLOAT+",r:2>", this.scanner.nextToken().toString());
			
			assertEquals("<"+TokenType.PRINT+",r:3>", this.scanner.nextToken().toString());
			
			assertEquals("<"+TokenType.ID+",r:4,nome>", this.scanner.nextToken().toString());
			
			assertEquals("<"+TokenType.ID+",r:5,intnome>", this.scanner.nextToken().toString());
			
			assertEquals("<"+TokenType.TYINT+",r:6>", this.scanner.nextToken().toString());
			assertEquals("<"+TokenType.ID+",r:6,nome>", this.scanner.nextToken().toString());
			
		} catch (LexicalException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	void testGenerale() {
		this.path = "./src/test/data/testScanner/testGenerale.txt";
		try {
			this.init(path);
			
			assertEquals("<"+TokenType.TYINT+",r:1>", this.scanner.nextToken().toString());
			assertEquals("<"+TokenType.ID+",r:1,temp>", this.scanner.nextToken().toString());
			assertEquals("<"+TokenType.SEMI+",r:1>", this.scanner.nextToken().toString());
			
			assertEquals("<"+TokenType.ID+",r:2,temp>", this.scanner.nextToken().toString());
			assertEquals("<"+TokenType.ASSIGN+",r:2>", this.scanner.nextToken().toString());
			
			Throwable exception = assertThrows(LexicalException.class, () -> this.scanner.nextToken());
		    assertEquals("Errore: numero non valido (nessun numero dopo la virgola) alla riga: 2", exception.getMessage());
		    
		    assertEquals("<"+TokenType.SEMI+",r:2>", this.scanner.nextToken().toString());
		    
		    assertEquals("<"+TokenType.TYFLOAT+",r:4>", this.scanner.nextToken().toString());
		    assertEquals("<"+TokenType.ID+",r:4,b>", this.scanner.nextToken().toString());
		    assertEquals("<"+TokenType.SEMI+",r:4>", this.scanner.nextToken().toString());
		    
		    assertEquals("<"+TokenType.ID+",r:5,b>", this.scanner.nextToken().toString());
		    assertEquals("<"+TokenType.ASSIGN+",r:5>", this.scanner.nextToken().toString());
		    assertEquals("<"+TokenType.ID+",r:5,temp>", this.scanner.nextToken().toString());
		    assertEquals("<"+TokenType.PLUS+",r:5>", this.scanner.nextToken().toString());
		    assertEquals("<"+TokenType.FLOAT+",r:5,3.2>", this.scanner.nextToken().toString());
		    assertEquals("<"+TokenType.SEMI+",r:5>", this.scanner.nextToken().toString());
		    
		    assertEquals("<"+TokenType.PRINT+",r:6>", this.scanner.nextToken().toString());
		    assertEquals("<"+TokenType.ID+",r:6,b>", this.scanner.nextToken().toString());
		    
		} catch (LexicalException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	void testPeekToken() {
		this.path = "./src/test/data/testScanner/testGenerale.txt";
		try {
			this.init(path);
			
			assertEquals(this.scanner.peekToken().getTipo(), TokenType.TYINT );
			assertEquals(this.scanner.nextToken().getTipo(), TokenType.TYINT );
			assertEquals(this.scanner.peekToken().getTipo(), TokenType.ID );
			assertEquals(this.scanner.peekToken().getTipo(), TokenType.ID );
			
			Token t = this.scanner.nextToken();
			
			assertEquals(t.getTipo(), TokenType.ID);
			assertEquals(t.getRiga(), 1);
			assertEquals(t.getVal(), "temp");
		} catch (LexicalException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Test
	void testAggiuntivoIdCorretto() {
		this.path = "./src/test/data/testScanner/testAggiuntivoIDCorretto.txt";
		try {
			this.init(path);
			
			assertEquals("<"+TokenType.TYINT+",r:1>", this.scanner.nextToken().toString());
			assertEquals("<"+TokenType.ID+",r:1,var>", this.scanner.nextToken().toString());
			assertEquals("<"+TokenType.ID+",r:1,intvar>", this.scanner.nextToken().toString());
			
			assertEquals("<"+TokenType.TYFLOAT+",r:2>", this.scanner.nextToken().toString());
			assertEquals("<"+TokenType.ID+",r:2,var>", this.scanner.nextToken().toString());
			assertEquals("<"+TokenType.ID+",r:2,floatvar>", this.scanner.nextToken().toString());
			
			assertEquals("<"+TokenType.PRINT+",r:3>", this.scanner.nextToken().toString());
			assertEquals("<"+TokenType.ID+",r:3,ciao>", this.scanner.nextToken().toString());
			assertEquals("<"+TokenType.ID+",r:3,printciao>", this.scanner.nextToken().toString());
		} catch (LexicalException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	void testAggiuntivoIDErrato() {
		this.path = "./src/test/data/testScanner/testAggiuntivoIDErrato.txt";
		Throwable exception;
		
		try {
			this.init(path);
			
			exception = assertThrows(LexicalException.class, () -> this.scanner.nextToken());
		    assertEquals("Numeri rilevati in: 'var1' alla riga: 2", exception.getMessage());
		    
		    exception = assertThrows(LexicalException.class, () -> this.scanner.nextToken());
		    assertEquals("Errore: numero non valido (lettera rilevata) alla riga: 2", exception.getMessage());
			
			assertEquals("<"+TokenType.ID+",r:2,ar>", this.scanner.nextToken().toString());
			
			exception = assertThrows(LexicalException.class, () -> this.scanner.nextToken());
		    assertEquals("Numeri rilevati in: 'var1' alla riga: 3", exception.getMessage());
			
			exception = assertThrows(LexicalException.class, () -> this.scanner.nextToken());
		    assertEquals("Il carattere '.' alla riga 4 non è legale", exception.getMessage());
		    
		    assertEquals("<"+TokenType.ID+",r:4,var>", this.scanner.nextToken().toString());
		    
		    assertEquals("<"+TokenType.ID+",r:6,var>", this.scanner.nextToken().toString());
		    
		    exception = assertThrows(LexicalException.class, () -> this.scanner.nextToken());
		    assertEquals("Il carattere '.' alla riga 6 non è legale", exception.getMessage());
		    
		    assertEquals("<"+TokenType.ID+",r:6,vars>", this.scanner.nextToken().toString());
		    
		} catch (LexicalException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	void testAggiuntivoNumeriCorretti() {
		this.path = "./src/test/data/testScanner/testAggiuntivoNumeriCorretti.txt";
		try {
			this.init(path);
			
			assertEquals("<"+TokenType.INT+",r:2,110>", this.scanner.nextToken().toString());
			
			assertEquals("<"+TokenType.FLOAT+",r:3,110.1>", this.scanner.nextToken().toString());
			assertEquals("<"+TokenType.FLOAT+",r:3,110.12>", this.scanner.nextToken().toString());
			assertEquals("<"+TokenType.FLOAT+",r:3,112.123>", this.scanner.nextToken().toString());
			assertEquals("<"+TokenType.FLOAT+",r:3,112.1242>", this.scanner.nextToken().toString());
			assertEquals("<"+TokenType.FLOAT+",r:3,121.12345>", this.scanner.nextToken().toString());
			
			assertEquals("<"+TokenType.INT+",r:5,0>", this.scanner.nextToken().toString());
			assertEquals("<"+TokenType.FLOAT+",r:5,0.1>", this.scanner.nextToken().toString());
			
			assertEquals("<"+TokenType.FLOAT+",r:6,0.12>", this.scanner.nextToken().toString());
			assertEquals("<"+TokenType.FLOAT+",r:6,0.123>", this.scanner.nextToken().toString());
			
			assertEquals("<"+TokenType.FLOAT+",r:7,0.1242>", this.scanner.nextToken().toString());
			assertEquals("<"+TokenType.FLOAT+",r:7,0.12345>", this.scanner.nextToken().toString());
			
			assertEquals("<"+TokenType.FLOAT+",r:8,1.0>", this.scanner.nextToken().toString());
		} catch (LexicalException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	void testAggiuntivoNumeriErrati() {
		this.path = "./src/test/data/testScanner/testAggiuntivoNumeriErrati.txt";
		Throwable exception;
		try {
			this.init(path);
			
			exception = assertThrows(LexicalException.class, () -> this.scanner.nextToken());
		    assertEquals("Errore: il carattere che segue zero alla riga: 1 non è valido", exception.getMessage());
		    
		    exception = assertThrows(LexicalException.class, () -> this.scanner.nextToken());
		    assertEquals("Errore: il carattere che segue zero alla riga: 1 non è valido", exception.getMessage());
		   
			assertEquals("<"+TokenType.ID+",r:1,bc>", this.scanner.nextToken().toString());
			
			exception = assertThrows(LexicalException.class, () -> this.scanner.nextToken());
		    assertEquals("Errore: il carattere che segue zero alla riga: 1 non è valido", exception.getMessage());
			
		    exception = assertThrows(LexicalException.class, () -> this.scanner.nextToken());
		    assertEquals("Errore: il carattere che segue zero alla riga: 1 non è valido", exception.getMessage());
		   
			assertEquals("<"+TokenType.INT+",r:1,99>", this.scanner.nextToken().toString());
			
			exception = assertThrows(LexicalException.class, () -> this.scanner.nextToken());
		    assertEquals("Errore: numero non valido (nessun numero dopo la virgola) alla riga: 2", exception.getMessage());
			
		    exception = assertThrows(LexicalException.class, () -> this.scanner.nextToken());
		    assertEquals("Errore: numero non valido (troppi numeri dopo la virgola) alla riga: 2", exception.getMessage());
		    
		    exception = assertThrows(LexicalException.class, () -> this.scanner.nextToken());
		    assertEquals("Errore: numero non valido (doppio punto rilevato) alla riga: 2", exception.getMessage());
		    
		    exception = assertThrows(LexicalException.class, () -> this.scanner.nextToken());
		    assertEquals("Errore: numero non valido (lettera rilevata) alla riga: 3", exception.getMessage());
			
		    exception = assertThrows(LexicalException.class, () -> this.scanner.nextToken());
		    assertEquals("Errore: numero non valido (lettera rilevata) alla riga: 3", exception.getMessage());
			
		    assertEquals("<"+TokenType.ID+",r:3,cd>", this.scanner.nextToken().toString());
		    
		    exception = assertThrows(LexicalException.class, () -> this.scanner.nextToken());
		    assertEquals("Errore: numero non valido (lettera rilevata) alla riga: 3", exception.getMessage());
			
		    assertEquals("<"+TokenType.INT+",r:3,123>", this.scanner.nextToken().toString());
		    
		    exception = assertThrows(LexicalException.class, () -> this.scanner.nextToken());
		    assertEquals("Errore: numero non valido (lettera rilevata) alla riga: 5", exception.getMessage());
			
		    exception = assertThrows(LexicalException.class, () -> this.scanner.nextToken());
		    assertEquals("Errore: numero non valido (lettera rilevata) alla riga: 5", exception.getMessage());
			
		    assertEquals("<"+TokenType.ID+",r:5,bc>", this.scanner.nextToken().toString());
		    
		    exception = assertThrows(LexicalException.class, () -> this.scanner.nextToken());
		    assertEquals("Errore: numero non valido (nessun numero dopo la virgola) alla riga: 6", exception.getMessage());
			
		    exception = assertThrows(LexicalException.class, () -> this.scanner.nextToken());
		    assertEquals("Errore: numero non valido (troppi numeri dopo la virgola) alla riga: 6", exception.getMessage());
			
		    exception = assertThrows(LexicalException.class, () -> this.scanner.nextToken());
		    assertEquals("Errore: numero non valido (lettera rilevata) alla riga: 7", exception.getMessage());
			
		    exception = assertThrows(LexicalException.class, () -> this.scanner.nextToken());
		    assertEquals("Errore: numero non valido (lettera rilevata) alla riga: 7", exception.getMessage());
		    
		    assertEquals("<"+TokenType.ID+",r:7,cd>", this.scanner.nextToken().toString());
		    
		    exception = assertThrows(LexicalException.class, () -> this.scanner.nextToken());
		    assertEquals("Errore: numero non valido (lettera rilevata) alla riga: 7", exception.getMessage());
		    
		    assertEquals("<"+TokenType.INT+",r:7,123>", this.scanner.nextToken().toString());
		    
		    exception = assertThrows(LexicalException.class, () -> this.scanner.nextToken());
		    assertEquals("Il carattere '.' alla riga 9 non è legale", exception.getMessage());
		    
		    assertEquals("<"+TokenType.INT+",r:9,123>", this.scanner.nextToken().toString());
		
		} catch (LexicalException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
