package customException;

/**
 * Estende la classe di java Exception e viene utilizzata per gestire le eccezioni di tipo Lessicale
 * @author Matteo Schirinzi 20035542
 */
public class LexicalException extends Exception{

	private static final long serialVersionUID = 4009653621635393755L;

	/**
	 * @author Matteo Schirinzi 20035542
	 * Costruttore vuoto
	 */
	public LexicalException() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @author Matteo Schirinzi 20035542
	 * @param message - messaggio dell'eccezione
	 * @param cause - Throwable che viene utilizzato per concatenare un'altra eccezione
	 * Costruttore Alternativo
	 */
	public LexicalException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @author Matteo Schirinzi 20035542
	 * @param message - messaggio dell'eccezione
	 * Costruttore Alternativo
	 */
	public LexicalException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

}
