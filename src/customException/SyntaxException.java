package customException;

/**
 * Estende la classe di java Exception e viene utilizzata per gestire le eccezioni di tipo Sintattico
 * @author Matteo Schirinzi 20035542
 */
public class SyntaxException extends Exception{

	private static final long serialVersionUID = -1376889437215558971L;

	/**
	 * @author Matteo Schirinzi 20035542
	 * Costruttore vuoto
	 */
	public SyntaxException() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @author Matteo Schirinzi 20035542
	 * @param message - messaggio dell'eccezione
	 * @param cause - Throwable che viene utilizzato per concatenare un'altra eccezione
	 * Costruttore Alternativo
	 */
	public SyntaxException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @author Matteo Schirinzi 20035542
	 * @param message - messaggio dell'eccezione
	 * Costruttore Alternativo
	 */
	public SyntaxException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

}
