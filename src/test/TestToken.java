package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import token.Token;
import token.TokenType;

class TestToken {

	@Test
	void test() {
		Token tok0  = new Token(TokenType.ASSIGN,1);
		Token tok1  = new Token(TokenType.DIV,1);
		Token tok2  = new Token(TokenType.EOF,5);
		Token tok3  = new Token(TokenType.FLOAT,1,"1.0");
		Token tok4  = new Token(TokenType.ID,1,"pippo");
		Token tok5  = new Token(TokenType.INT,1,"1");
		Token tok6  = new Token(TokenType.MINUS,1);
		Token tok7  = new Token(TokenType.PLUS,1);
		Token tok8  = new Token(TokenType.PRINT,11);
		Token tok9  = new Token(TokenType.SEMI,1);
		Token tok10 = new Token(TokenType.TIMES,1);
		Token tok11 = new Token(TokenType.TYFLOAT,1);
		Token tok12 = new Token(TokenType.TYINT,1);
		assertEquals("<ASSIGN,r:1>",tok0.toString());
		assertEquals("<DIV,r:1>",tok1.toString());
		assertEquals("<EOF,r:5>",tok2.toString());
		assertEquals("<FLOAT,r:1,1.0>",tok3.toString());
		assertEquals("<ID,r:1,pippo>",tok4.toString());
		assertEquals("<INT,r:1,1>",tok5.toString());
		assertEquals("<MINUS,r:1>",tok6.toString());
		assertEquals("<PLUS,r:1>",tok7.toString());
		assertEquals("<PRINT,r:11>",tok8.toString());
		assertEquals("<SEMI,r:1>",tok9.toString());
		assertEquals("<TIMES,r:1>",tok10.toString());
		assertEquals("<TYFLOAT,r:1>",tok11.toString());
		assertEquals("<TYINT,r:1>",tok12.toString());
		
	}

}
