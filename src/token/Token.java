package token;

/**
 * Un Token descrive un insieme di caratteri che hanno lo stesso significato come ad esempio, identificatori, operatori, keywords, numeri, delimitatori. 
 * Le espressioni regolari sono usate per descrivere i token che sono unità lessicale restituita dall’analizzatore lessicale e fornita come ingresso al parser 
 * @author Matteo Schirinzi 20035542
*/
public class Token {

	private int riga;
	private TokenType tipo;
	private String val;
	
	/**
	 * @author Matteo Schirinzi 20035542
	 * @param tipo - TokenType del token
	 * @param riga - riga corrispondente al token
	 * @param val - valore del token
	 * Viene utilizzato per i Token che hanno un valore
	 */
	public Token(TokenType tipo, int riga, String val) {
		this(tipo, riga);
		this.val = val;
	}
	
	/**
	 * @author Matteo Schirinzi 20035542
	 * @param tipo - TokenType del token
	 * @param riga - riga corrispondente al token
	 * Costruttore Alternativo, viene utilizzato per i Token che non hanno un valore
	 */
	public Token(TokenType tipo, int riga) {
		this.riga = riga;
		this.tipo = tipo;
	}

	// Getters per i campi (i setter non servono perchè una volta istanziato il token non serve modificarlo)
	
	/**
	 * @author Matteo Schirinzi 20035542
	 * @return riga - restituisce la riga alla quale è presente il Token
	 */
	public int getRiga() {
		return this.riga;
	}

	/**
	 * @author Matteo Schirinzi 20035542
	 * @return tipo - restituisce il tipo del Token (TokenType)
	 */
	public TokenType getTipo() {
		return this.tipo;
	}

	/**
	 * @author Matteo Schirinzi 20035542
	 * @return val - restituisce il valore del Token 
	 */
	public String getVal() {
		return this.val;
	}

	/**
	 * @author Matteo Schirinzi 20035542
	 * @return string - restituisce una rapprresentazione della classe in formato di stringa
	 */
	@Override
	public String toString() {
		if(this.tipo == TokenType.INT || this.tipo == TokenType.FLOAT || this.tipo == TokenType.ID)
			return "<" + this.tipo + ",r:" + this.riga + "," + this.val + ">";
		else
			return "<" + this.tipo + ",r:" + this.riga + ">";
	}
	
	

    
    
	

     

}
