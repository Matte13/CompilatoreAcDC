package ast;

import visitor.IVisitor;

/**
 * @author Matteo Schirinzi 20035542
 */
public class NodeDeref extends NodeExpr{

	private NodeId id;
	
	/**
	 * @author Matteo Schirinzi 20035542
	 * @param id - identificatore
	 */
	public NodeDeref (NodeId id) {
		this.id = id;
	}
	
	/**
	 * @author Matteo Schirinzi 20035542
	 * Costruttore alternativo con id null
	 */
	public NodeDeref() {
		this.id = null;
	}
	
	/**
	 * @author Matteo Schirinzi 20035542
	 * @return id - restituisce il nodo id
	 */
	public NodeId getId() {
		return this.id;
	}

	/**
	 * @author Matteo Schirinzi 20035542
	 * @return string - restituisce una rapprresentazione della classe in formato di stringa
	 */
	@Override
	public String toString() {
		return "NodeDeref [id=" + id + "]";
	}
	
	/**
	 * @author Matteo Schirinzi 20035542
	 * @param visitor - richiama il metodo visit della classe
	 */
	public void accept (IVisitor visitor) {
		visitor.visit(this);
	}
	
	
}
