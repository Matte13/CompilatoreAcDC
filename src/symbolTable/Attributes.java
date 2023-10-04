package symbolTable;

import ast.LangType;

/**
 * Classe che associa il LangType e il registro all'identificatore 
 * @author Matteo Schirinzi 20035542
 */
public class Attributes {

	private LangType tipo;
	private char registro;
	
	/**
	 * @author Matteo Schirinzi 20035542
	 * @param tipo - tipo dell'identificatore
	 */
	public Attributes(LangType tipo) {
		super();
		this.tipo = tipo;
	}

	/**
	 * @author Matteo Schirinzi 20035542
	 * @return tipo - restituisce il tipo dell'identificatore
	 */
	public LangType getTipo() {
		return tipo;
	}

	/**
	 * @author Matteo Schirinzi 20035542
	 * @param tipo - tipo dell'identificatore
	 */
	public void setTipo(LangType tipo) {
		this.tipo = tipo;
	}

	/**
	 * @author Matteo Schirinzi 20035542
	 * @return registro - restituisce il registro associato all'identificatore
	 */
	public char getRegistro() {
		return registro;
	}

	/**
	 * @author Matteo Schirinzi 20035542
	 * @param registro - associa il registro all'identificatore
	 */
	public void setRegistro(char registro) {
		this.registro = registro;
	}
	
	
	
}
