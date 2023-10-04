package ast;

import visitor.IVisitor;

/**
 * @author Matteo Schirinzi 20035542
 */
public abstract class NodeAST {

	private TypeDescriptor resType;
	private String codice;
	
	/**
	 * @author Matteo Schirinzi 20035542
	 * @param visitor
	 */
	public abstract void accept(IVisitor visitor);

	/**
	 * @author Matteo Schirinzi 20035542
	 * @return resType - restituisce il TypeDescriptor del nodo
	 */
	public TypeDescriptor getResType() {
		return resType;
	}

	/**
	 * @author Matteo Schirinzi 20035542
	 * @param resType - modifica il TypeDescriptor del nodo
	 */
	public void setResType(TypeDescriptor resType) {
		this.resType = resType;
	}

	/**
	 * @author Matteo Schirinzi 20035542
	 * @return codice - restituisce il codice corrispondete al nodo
	 */
	public String getCodice() {
		return codice;
	}

	/**
	 * @author Matteo Schirinzi 20035542
	 * @param codice - modifica il codice corrispondete al nodo
	 */
	public void setCodice(String codice) {
		this.codice = codice;
	}
	
	
	
	
}
