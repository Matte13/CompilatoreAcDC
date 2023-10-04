package ast;

import visitor.IVisitor;

/**
 * @author Matteo Schirinzi 20035542
 */
public class NodeBinOp extends NodeExpr{

	private NodeExpr left;
	private NodeExpr right;
	private LangOper op = null;
	
	/**
	 * @author Matteo Schirinzi 20035542
	 * @param left - parte sinistra dell'espressione
	 * @param right - parte destra dell'espressione
	 * @param op - operatore
	 */
	public NodeBinOp (NodeExpr left, NodeExpr right, LangOper op) {
		super();
		this.left = left;
		this.right = right;
		this.op = op;
	}
	
	/**
	 * @author Matteo Schirinzi 20035542
	 * @return op - restituisce l'operatore dell'espressione
	 */
	public LangOper getOp() {
		return this.op;
	}
	
	/**
	 * @author Matteo Schirinzi 20035542
	 * @return left - restituisce la parte sinistra dell'espressione
	 */
	public NodeExpr getLeft() {
		return this.left;
	}
	
	/**
	 * @author Matteo Schirinzi 20035542
	 * @return right - restituisce la parte destra dell'espressione
	 */
	public NodeExpr getRight() {
		return this.right;
	}

	/**
	 * @author Matteo Schirinzi 20035542 
	 * @param left - nodo sinistro dell'espressione da modificare 
	 * Modifica l'espressione sinistra
	 */
	public void setLeft(NodeExpr left) {
		this.left = left;
	}

	/**
	 * @author Matteo Schirinzi 20035542
	 * @param right - nodo destro dell'espressione da modificare  
	 * Modifica l'espressione destra
	 */
	public void setRight(NodeExpr right) {
		this.right = right;
	}

	/**
	 * @author Matteo Schirinzi 20035542
	 * @return string - restituisce una rappresentazione della classe in formato di stringa
	 */
	@Override
	public String toString() {
		return "NodeBinOp [left=" + left + ", right=" + right + ", op=" + op + "]";
	}

	/**
	 * @author Matteo Schirinzi 20035542
	 * @param visitor - richiama il metodo visit della classe
	 */
	public void accept (IVisitor visitor) {
		visitor.visit(this);
	}
	
	
}
