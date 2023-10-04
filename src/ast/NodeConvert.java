package ast;

import visitor.IVisitor;

/**
 * @author Matteo Schirinzi 20035542
 */
public class NodeConvert extends NodeExpr{
	
	private NodeExpr expr;
	
	/**
	 * @author Matteo Schirinzi 20035542
	 * @param expr - espressione
	 */
	public NodeConvert(NodeExpr expr) {
		super();
		this.expr = expr;
	}

	/**
	 * @author Matteo Schirinzi 20035542 
	 * @return expr - espressione
	 * Restituisce l'espressione
	 */
	public NodeExpr getExpr() {
		return expr;
	}

	/**
	 * @author Matteo Schirinzi 20035542
	 * @param visitor - richiama il metodo visit della classe
	 */
	public void accept(IVisitor visitor) {
		visitor.visit(this);
	}

	/**
	 * @author Matteo Schirinzi 20035542
	 * @return string - restituisce una rapprresentazione della classe in formato di stringa
	 */
	@Override
	public String toString() {
		return "NodeConvert [expr=" + expr + "]";
	}
	
	


}
