package ast;

import symbolTable.Attributes;
import visitor.IVisitor;

/**
 * @author Matteo Schirinzi 20035542
 */
public class NodeId extends NodeAST{
	
	private final String name;
	private Attributes definition;

	/**
	 * @author Matteo Schirinzi 20035542
	 * @param name - nome dell'identificatore
	 */
	public NodeId(String name) {
		this.name = name;
	}
	
	/**
	 * @author Matteo Schirinzi 20035542
	 * @param name - nome dell'identificatore
	 * @param definition - attributi dell'identificatore
	 * Costruttore alternativo a due parametri
	 */
	public NodeId(String name, Attributes definition) {
		super();
		this.name = name;
		this.definition = definition;
	}
	
	/**
	 * @author Matteo Schirinzi 20035542 
	 * @return name - restituisce il nome dell'id
	 */
	public String getName() {
		return name;
	}

	/**
	 * @author Matteo Schirinzi 20035542
	 * @return definition - restituisce la definition dell'id
	 */
	public Attributes getDefinition() {
		return definition;
	}

	/**
	 * @author Matteo Schirinzi 20035542
	 * @param definition - aggiunge la definition dell'id
	 * Lo aggiungo all'id quando lo accedo così poi non devo più cercarlo nella SymbolTable -> ho già l'entry nella SymbolTable
	 */
	public void setDefinition(Attributes definition) {
		this.definition = definition;
	}

	/**
	 * @author Matteo Schirinzi 20035542
	 * @return string - restituisce una rapprresentazione della classe in formato di stringa
	 */
	@Override
	public String toString() {
		return "NodeId [name=" + name + "]";
	}
	
	/**
	 * @author Matteo Schirinzi 20035542
	 * @param visitor - richiama il metodo visit della classe
	 */
	public void accept (IVisitor visitor) {
		visitor.visit(this);
	}
	
}
