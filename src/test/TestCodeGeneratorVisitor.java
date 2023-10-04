package test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import ast.NodeProgram;
import parser.Parser;
import scanner.Scanner;
import visitor.CodeGeneratorVisitor;
import visitor.TypeCheckingVisitor;


class TestCodeGeneratorVisitor {

	@Test
	void testCorrect() {
		assertDoesNotThrow(() -> {
			NodeProgram np = new Parser(new Scanner("./src/test/data/testCodeGenerator/testCorrect.txt")).parse(); 
			np.accept(new TypeCheckingVisitor());
			np.accept(new CodeGeneratorVisitor());
			
			String code = np.getCodice();
			System.out.println("testCorrect: " + code.toString() + "\n");
			
			Assertions.assertEquals("5 sa 0 k 4 sb 0 k la 5 k 3.2 lb 5 k / 0 k 5 k - 0 k sb 0 k lb p P", np.getCodice());
			}
		);
	}
	
	@Test
	void testCorrect2() {
		assertDoesNotThrow(() -> {
			NodeProgram np = new Parser(new Scanner("./src/test/data/testCodeGenerator/testCorrect2.txt")).parse(); 
			np.accept(new TypeCheckingVisitor());
			np.accept(new CodeGeneratorVisitor());
			
			String code = np.getCodice();
			System.out.println("testCorrect2: " + code.toString() + "\n");
			
			Assertions.assertEquals("1.0 6 5 k 5 k / 0 k sb 0 k lb p P 1 6 / 0 k sa 0 k la p P la 5 k sb 0 k", np.getCodice());
			}
		);
	}

	@Test
	void testCorrect3() {
		assertDoesNotThrow(() -> {
			NodeProgram np = new Parser(new Scanner("./src/test/data/testCodeGenerator/testCorrect3.txt")).parse(); 
			np.accept(new TypeCheckingVisitor());
			np.accept(new CodeGeneratorVisitor());
			
			String code = np.getCodice();
			System.out.println("testCorrect3: " + code.toString() + "\n");
			
			Assertions.assertEquals("5 sa 0 k 3.2 la 5 k / 0 k sa 0 k la p P", np.getCodice());
			}
		);
	}

	@Test
	void testIdNotDeclare() {
		assertDoesNotThrow(() -> {
			NodeProgram np = new Parser(new Scanner("./src/test/data/testCodeGenerator/testIdNotDeclare.txt")).parse(); 
			np.accept(new TypeCheckingVisitor());
			np.accept(new CodeGeneratorVisitor());
			
			String code = np.getCodice();
			System.out.println("testIdNotDeclare: " + code.toString() + "\n");
			
			Assertions.assertEquals("", np.getCodice());
			}
		);
	}
	

}
