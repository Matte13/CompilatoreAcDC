package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import ast.NodeProgram;
import customException.LexicalException;
import customException.SyntaxException;
import parser.Parser;
import scanner.Scanner;

class TestParser {

	private String path;
	private Scanner scanner;
	private Parser parser;
	
	void init(String path) {
		try {
			this.scanner = new Scanner(path);
		} catch (LexicalException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.parser = new Parser(this.scanner);
	}
	
	@Test
	void testParserCorretto1() {
		NodeProgram nodoP;
		this.path = "./src/test/data/testParser/testParserCorretto1.txt";
		
		try {
			this.init(path);
			nodoP = this.parser.parse();
			assertEquals(nodoP.toString(),"NodeProgram [decSts=[NodePrint [id=NodeId [name=stampa]], NodeDecl [id=NodeId [name=numberfloat], type=FLOATy, init=null], NodeDecl [id=NodeId [name=floati], type=INTy, init=null], NodeAssign [id=NodeId [name=a], expr=NodeBinOp [left=NodeCost [value=5, type=INTy], right=NodeCost [value=3, type=INTy], op=PLUS]], NodeAssign [id=NodeId [name=b], expr=NodeBinOp [left=NodeDeref [id=NodeId [name=a]], right=NodeCost [value=5, type=INTy], op=PLUS]]]]");
			//Stampa formattata per controllo
			System.out.println("testParserCorretto1: ");
			System.out.println(nodoP.toStringFormatted());
		} catch (SyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	void testParserCorretto2(){
		NodeProgram nodoP;
		this.path = "./src/test/data/testParser/testParserCorretto2.txt";
		
		try {
			this.init(path);
			nodoP = this.parser.parse();
			assertEquals(nodoP.toString(),"NodeProgram [decSts=[NodeDecl [id=NodeId [name=num], type=INTy, init=null], NodeAssign [id=NodeId [name=num], expr=NodeCost [value=5, type=INTy]], NodeAssign [id=NodeId [name=num], expr=NodeDeref [id=NodeId [name=id]]], NodeAssign [id=NodeId [name=num], expr=NodeBinOp [left=NodeDeref [id=NodeId [name=id]], right=NodeCost [value=5.0, type=FLOATy], op=PLUS]], NodeAssign [id=NodeId [name=num], expr=NodeBinOp [left=NodeDeref [id=NodeId [name=id]], right=NodeCost [value=5, type=INTy], op=TIMES]], NodeAssign [id=NodeId [name=num], expr=NodeBinOp [left=NodeDeref [id=NodeId [name=id]], right=NodeDeref [id=NodeId [name=id]], op=TIMES]], NodeAssign [id=NodeId [name=num], expr=NodeBinOp [left=NodeBinOp [left=NodeDeref [id=NodeId [name=id]], right=NodeCost [value=5, type=INTy], op=PLUS], right=NodeBinOp [left=NodeBinOp [left=NodeCost [value=8, type=INTy], right=NodeCost [value=6.0, type=FLOATy], op=TIMES], right=NodeCost [value=2, type=INTy], op=DIV], op=MINUS]], NodeAssign [id=NodeId [name=num], expr=NodeBinOp [left=NodeBinOp [left=NodeBinOp [left=NodeDeref [id=NodeId [name=id]], right=NodeCost [value=5, type=INTy], op=TIMES], right=NodeBinOp [left=NodeCost [value=8.0, type=FLOATy], right=NodeCost [value=6, type=INTy], op=TIMES], op=MINUS], right=NodeCost [value=2, type=INTy], op=PLUS]], NodePrint [id=NodeId [name=ok]]]]");
			//Stampa formattata per controllo
			System.out.println("\ntestParserCorretto2: ");
			System.out.println(nodoP.toStringFormatted());
		} catch (SyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	void testParserCorretto3() {
		NodeProgram nodoP;
		this.path = "./src/test/data/testParser/testParserCorretto3.txt";
		
		try {
			this.init(path);
			nodoP = this.parser.parse();
			assertEquals(nodoP.toString(), "NodeProgram [decSts=[NodeDecl [id=NodeId [name=temp], type=INTy, init=NodeBinOp [left=NodeBinOp [left=NodeCost [value=5, type=INTy], right=NodeBinOp [left=NodeCost [value=7, type=INTy], right=NodeCost [value=6.0, type=FLOATy], op=TIMES], op=MINUS], right=NodeCost [value=2, type=INTy], op=PLUS]], NodeDecl [id=NodeId [name=y], type=FLOATy, init=NodeBinOp [left=NodeDeref [id=NodeId [name=temp]], right=NodeCost [value=1, type=INTy], op=PLUS]], NodeAssign [id=NodeId [name=temp], expr=NodeBinOp [left=NodeDeref [id=NodeId [name=y]], right=NodeBinOp [left=NodeBinOp [left=NodeCost [value=7, type=INTy], right=NodeDeref [id=NodeId [name=temp]], op=DIV], right=NodeCost [value=4, type=INTy], op=DIV], op=PLUS]], NodePrint [id=NodeId [name=temp]]]]");
			//Stampa formattata per controllo
			System.out.println("\ntestParserCorretto3: ");
			System.out.println(nodoP.toStringFormatted());
		} catch (SyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	void testParserEcc_0() {
		this.path = "./src/test/data/testParser/testParserEcc_0.txt";
		this.init(path);
		Throwable exception = assertThrows(SyntaxException.class, () -> this.parser.parse());
		assertEquals("ErroreSintattico: Il token SEMI alla riga 1", exception.getMessage());
	}
	
	@Test
	void testParserEcc_1() {
		this.path = "./src/test/data/testParser/testParserEcc_1.txt";
		this.init(path);
		Throwable exception = assertThrows(SyntaxException.class, () -> this.parser.parse());
		assertEquals("ErroreSintattico: Il token TIMES alla riga 2", exception.getMessage());
	}

	@Test
	void testParserEcc_2() {
		this.path = "./src/test/data/testParser/testParserEcc_2.txt";
		this.init(path);
		Throwable exception = assertThrows(SyntaxException.class, () -> this.parser.parse());
		assertEquals("ErroreSintattico: Il token INT alla riga 1", exception.getMessage());
	}
	
	@Test
	void testParserEcc_3() {
		this.path = "./src/test/data/testParser/testParserEcc_3.txt";
		this.init(path);
		Throwable exception = assertThrows(SyntaxException.class, () -> this.parser.parse());
		assertEquals("ErroreSintattico: Aspettato ASSIGN token invece di PLUS alla riga 2", exception.getMessage());
	}

	@Test
	void testParserEcc_4() {
		this.path = "./src/test/data/testParser/testParserEcc_4.txt";
		this.init(path);
		Throwable exception = assertThrows(SyntaxException.class, () -> this.parser.parse());
		assertEquals("ErroreSintattico: Aspettato ID token invece di INT alla riga 2", exception.getMessage());
	}
	
	@Test
	void testParserEcc_5() {
		this.path = "./src/test/data/testParser/testParserEcc_5.txt";
		this.init(path);
		Throwable exception = assertThrows(SyntaxException.class, () -> this.parser.parse());
		assertEquals("ErroreSintattico: Aspettato ID token invece di INT alla riga 3", exception.getMessage());
	}
	
}
