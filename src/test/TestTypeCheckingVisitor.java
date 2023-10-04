package test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import ast.NodeProgram;
import ast.TypeDescriptor;
import parser.Parser;
import scanner.Scanner;
import visitor.TypeCheckingVisitor;

class TestTypeCheckingVisitor {

	@Test
	void testDichiarazioniRipetute() {
		assertDoesNotThrow(() -> {
			NodeProgram np = new Parser(new Scanner("./src/test/data/testTypeChecking/1_dicRipetute.txt")).parse();
			TypeCheckingVisitor t = new TypeCheckingVisitor();
			np.accept(t);
			Assertions.assertEquals(TypeDescriptor.ERROR, np.getResType());
			}
		);
	}
	
	@Test
	void testFileCorrect() {
		assertDoesNotThrow(() -> {
			NodeProgram np = new Parser(new Scanner("./src/test/data/testTypeChecking/2_fileCorrect.txt")).parse();
			TypeCheckingVisitor t = new TypeCheckingVisitor();
			np.accept(t);
			Assertions.assertEquals(TypeDescriptor.VOID, np.getResType());
			}
		);
	}
	
	@Test
	void testIdNotDeclare() {
		assertDoesNotThrow(() -> {
			NodeProgram np = new Parser(new Scanner("./src/test/data/testTypeChecking/3_IdNotDeclare.txt")).parse();
			TypeCheckingVisitor t = new TypeCheckingVisitor();
			np.accept(t);
			Assertions.assertEquals(TypeDescriptor.ERROR, np.getResType());
			}
		);
	}
	
	@Test
	void testErrorAssignConvert() {
		assertDoesNotThrow(() -> {
			NodeProgram np = new Parser(new Scanner("./src/test/data/testTypeChecking/errorAssignConvert.txt")).parse();
			TypeCheckingVisitor t = new TypeCheckingVisitor();
			np.accept(t);
			Assertions.assertEquals(TypeDescriptor.ERROR, np.getResType());
			}
		);
	}
	
	@Test
	void testErrorOp() {
		assertDoesNotThrow(() -> {
			NodeProgram np = new Parser(new Scanner("./src/test/data/testTypeChecking/errorOp.txt")).parse();
			TypeCheckingVisitor t = new TypeCheckingVisitor();
			np.accept(t);
			Assertions.assertEquals(TypeDescriptor.ERROR, np.getResType());
			}
		);
		
	}

	@Test
	void testFileCorrect2() {
		assertDoesNotThrow(() -> {
			NodeProgram np = new Parser(new Scanner("./src/test/data/testTypeChecking/fileCorrect2.txt")).parse();
			TypeCheckingVisitor t = new TypeCheckingVisitor();
			np.accept(t);
			Assertions.assertEquals(TypeDescriptor.VOID, np.getResType());
			}
		);
	}

	@Test
	void testGenerale() {
		assertDoesNotThrow(() -> {
			NodeProgram np = new Parser(new Scanner("./src/test/data/testTypeChecking/testGenerale.txt")).parse();
			TypeCheckingVisitor t = new TypeCheckingVisitor();
			np.accept(t);
			Assertions.assertEquals(TypeDescriptor.VOID, np.getResType());
 			}
		);
	}
	
	@Test
	void testGenerale2() {
		assertDoesNotThrow(() -> {
			NodeProgram np = new Parser(new Scanner("./src/test/data/testTypeChecking/testGenerale2.txt")).parse();
			TypeCheckingVisitor t = new TypeCheckingVisitor();
			np.accept(t);
			Assertions.assertEquals(TypeDescriptor.VOID, np.getResType());
			}
		);
	}

}
