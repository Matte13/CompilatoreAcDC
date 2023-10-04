package ast;

import visitor.IVisitor;

/**
 * @author Matteo Schirinzi 20035542
 */
public class NodeCost extends NodeExpr{

	private String value;
	private LangType type;
	
	/**
	 * @author Matteo Schirinzi 20035542
	 * @param value - valore della costante
	 * @param type - tipo della costante
	 */
	public NodeCost(String value, LangType type) {
		super();
		this.value = value;
		this.type = type;
	}
	
	/**
	 * @author Matteo Schirinzi 20035542
	 * @return value - restituisce il valore del nodo
	 */
	public String getValue() {
		return this.value;
	}
	
	/**
	 * @author Matteo Schirinzi 20035542
	 * @return type - restituisce il LangType del nodo
	 */
	public LangType getType() {
		return this.type;
	}

	/**
	 * @author Matteo Schirinzi 20035542
	 * @return string - restituisce una rapprresentazione della classe in formato di stringa
	 */
	@Override
	public String toString() {
		return "NodeCost [value=" + value + ", type=" + type + "]";
	}
	
	/**
	 * @author Matteo Schirinzi 20035542
	 * @param visitor - richiama il metodo visit della classe
	 */
	public void accept (IVisitor visitor) {
		visitor.visit(this);
	}
	
}
