package visitor;

import ast.*;

/**
 * E' un'interfaccia che permette di implementare il pattern comportamentale visitor nella classi TypeChecking e CodeGenerator
 * @author Matteo Schirinzi 20035542
*/
public interface IVisitor {
	
	public abstract void visit(NodeProgram node);
	
	public abstract void visit(NodeId node);
	
	public abstract void visit(NodeDecl node);
	
	public abstract void visit(NodeBinOp node);
	
	public abstract void visit(NodePrint node);
	
	public abstract void visit(NodeAssign node);
	
	public abstract void visit(NodeDeref node);

	public abstract void visit(NodeConvert node);

	public abstract void visit(NodeCost node);
	
}
