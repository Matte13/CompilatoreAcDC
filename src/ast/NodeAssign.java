package ast;

import visitor.IVisitor;

/**
 * @author Matteo Schirinzi 20035542
 */
public class NodeAssign extends NodeStm{

	private NodeId id = null;
	private NodeExpr expr = null;
	
	/**
	 * @author Matteo Schirinzi 20035542
	 * @param id - identificatore per l'assegnamento
	 * @param expr - espressione da assegnare all'id
	 */
	public NodeAssign (NodeId id, NodeExpr expr) {
		this.id = id;
		this.expr = expr;
	}
	
	/**
	 * @author Matteo Schirinzi 20035542
	 * @return id - identificatore
	 */
	public NodeId getId() {
		return this.id;
	}
	
	/**
	 * @author Matteo Schirinzi 20035542
	 * @return expr - espressione
	 */
	public NodeExpr getExpr() {
		return this.expr;
	}

	/**
	 * @author Matteo Schirinzi 20035542
	 * @param expr - espressione da modificare
	 */
	public void setExpr(NodeExpr expr) {
		this.expr = expr;
	}

	/**
	 * @author Matteo Schirinzi 20035542
	 * @return string - restituisce una rapprresentazione della classe in formato di stringa
	 */
	@Override
	public String toString() {
		return "NodeAssign [id=" + id + ", expr=" + expr + "]";
	}

	/**
	 * @author Matteo Schirinzi 20035542
	 * @param visitor - richiama il metodo visit della classe
	 */
	public void accept(IVisitor visitor) {
		visitor.visit(this);
	}
	
	
}
