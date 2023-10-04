package ast;

import visitor.IVisitor;

/**
 * @author Matteo Schirinzi 20035542
 */
public class NodeDecl extends NodeDecSt{

	private final NodeId id;
	private final LangType type;
	private final NodeExpr init;
	
	/**
	 * @author Matteo Schirinzi 20035542
	 * @param id - identificatore della dichiarazione
	 * @param type - tipo della dichiarazione
	 * @param init - eventuale inizializzazione
	 */
	public NodeDecl(NodeId id, LangType type, NodeExpr init) {
		this.id = id;
		this.type = type;
		this.init = init;
	}

	/**
	 * @author Matteo Schirinzi 20035542
	 * @return id - restituisce l'id della dichiarazione
	 */
	public NodeId getId() {
		return id;
	}

	/**
	 * @author Matteo Schirinzi 20035542
	 * @return type - restituisce il tipo della dichiarazione
	 */
	public LangType getType() {
		return type;
	}

	/**
	 * @author Matteo Schirinzi 20035542
	 * @return init - restituisce l'eventuale inizializzazione
	 */
	public NodeExpr getInit() {
		return init;
	}

	/**
	 * @author Matteo Schirinzi 20035542
	 * @return string - restituisce una rapprresentazione della classe in formato di stringa
	 */
	@Override
	public String toString() {
		return "NodeDecl [id=" + id + ", type=" + type + ", init=" + init + "]";
	}
	
	/**
	 * @author Matteo Schirinzi 20035542
	 * @param visitor - richiama il metodo visit della classe
	 */
	public void accept (IVisitor visitor) {
		visitor.visit(this);
	}

	
	
	
}
