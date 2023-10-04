package ast;

import visitor.IVisitor;

/**
 * @author Matteo Schirinzi 20035542
 */
public class NodePrint extends NodeStm{

	private final NodeId id;
	
	/**
	 * @author Matteo Schirinzi 20035542
	 * @param id - identificatore 
	 */
	public NodePrint(NodeId id) {
		this.id = id;
	}
	
	/**
	 * @author Matteo Schirinzi 20035542 
	 * @return id - restituisce l'identificatore
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
		return "NodePrint [id=" + id + "]";
	}
	
	/**
	 * @author Matteo Schirinzi 20035542
	 * @param visitor - richiama il metodo visit della classe
	 */
	public void accept(IVisitor visitor) {
		visitor.visit(this);
	}
	
	
}
