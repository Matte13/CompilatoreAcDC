package ast;

import java.util.ArrayList;
import visitor.IVisitor;

/**
 * @author Matteo Schirinzi 20035542
 */
public class NodeProgram extends NodeAST {
	
	private final ArrayList<NodeDecSt> decSts;

	/**
	 * @author Matteo Schirinzi 20035542
	 * @param decSts - array list di nodi NodeDecSt
	 */
	public NodeProgram(ArrayList<NodeDecSt> decSts) {
		this.decSts = decSts;
	}
	
	/**
	 * @author Matteo Schirinzi 20035542
	 * Costruttore Alternativo - inzializza l'arraylist
	 */
	public NodeProgram () {
		this.decSts = new ArrayList<NodeDecSt> ();
	}
	
	/**
	 * @author Matteo Schirinzi 20035542
	 * @return decSts - restituisce l'array list di nodi NodeDecSt
	 */
	public ArrayList<NodeDecSt> getDecSts(){
		return this.decSts;
	}

	/**
	 * @author Matteo Schirinzi 20035542
	 * @return string estituisce una rapprresentazione della classe in formato di stringa
	 */
	@Override
	public String toString() {
		return "NodeProgram [decSts=" + decSts + "]";
	}
	
	/**
	 * @author Matteo Schirinzi 20035542
	 * @return print - restituisce una rapprresentazione della classe in formato di stringa formattato per facilitare la lettura nei test
	 */
	public String toStringFormatted() {
		String print = "NodeProgram [\n\tdecSts=[\n";
			for(NodeDecSt node : this.decSts) {
				print += "\t\t"+ node.toString() + ",\n";
			}
			print +="\t]\n"+"]";
		return print;
	}
	
	/**
	 * @author Matteo Schirinzi 20035542
	 * @param visitor - richiama il metodo visit della classe
	 */
	public void accept(IVisitor visitor) {
		visitor.visit(this);
	}


}
