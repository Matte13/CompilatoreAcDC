package visitor;

import ast.*;
import symbolTable.Attributes;
import symbolTable.SymbolTable;

/**
 * Implementa l'analisi semantica visitando l’ast e controllando:
 * 1)Che le variabili siano state dichiarate prima di essere usate e siano dichiarate una volta sola
 * 2)Che siano stati usati i giusti tipi. All’occorrenza effettua una conversione,
 * 3)La raggiungibilità del codice,
 * 4)Corretto ritorno di valori dai metodi.
 * In questa fase, nel caso di approccio alla traduzione in due fasi il type checker aggiunge nell’ast delle informazioni di tipo.
 * @author Matteo Schirinzi 20035542
*/
public class TypeCheckingVisitor implements IVisitor{
	
	/**
	 * @author Matteo Schirinzi 20035542
	 * Richiama il metodo per inizializzare la symbol table 
	 */
	public TypeCheckingVisitor() {
		SymbolTable.init();
	}

	/**
	 * @author Matteo Schirinzi 20035542
	 * @param node - nodo programma
	 */
	@Override
	public void visit(NodeProgram node) {
		TypeDescriptor res = TypeDescriptor.VOID;
		
		for(NodeDecSt n : node.getDecSts()) {
			n.accept(this);
			if(n.getResType() == TypeDescriptor.ERROR)
				res = TypeDescriptor.ERROR;
		}
		node.setResType(res);
	}

	/**
	 * @author Matteo Schirinzi 20035542
	 * @param node - nodo identificatore
	 */
	@Override
	public void visit(NodeId node) {
		String name = node.getName();
		Attributes attribute = SymbolTable.lookup(name);
		
		if(attribute == null) 
			node.setResType(TypeDescriptor.ERROR);
		else 
		{
			if(attribute.getTipo().equals(LangType.INTy))
				node.setResType(TypeDescriptor.INT);
			else if(attribute.getTipo().equals(LangType.FLOATy)) 
				node.setResType(TypeDescriptor.FLOAT);
			node.setDefinition(attribute);
		}
	}

	/**
	 * @author Matteo Schirinzi 20035542
	 * @param node - nodo dichiarazione
	 */
	@Override
	public void visit(NodeDecl node) {
		NodeId id = node.getId();
		NodeExpr expr = node.getInit();
		
		if(SymbolTable.lookup(id.getName()) != null)
			node.setResType(TypeDescriptor.ERROR);	//variabile già dichiarata
		else
		{
			//se l'espressione non è nulla la controllo
			if(expr != null)
				expr.accept(this);
			
			//identificatore ancora non dichiarato, viene aggiunta alla SymbolTable
			Attributes a = new Attributes(node.getType());
			id.setDefinition(a);
			SymbolTable.enter(id.getName(), a);
			node.setResType(TypeDescriptor.VOID);
		}
		
	}

	/**
	 * @author Matteo Schirinzi 20035542
	 * @param node - nodo operazione binaria
	 */
	@Override
	public void visit(NodeBinOp node) {
		NodeExpr exprLeft = node.getLeft();
		NodeExpr exprRight = node.getRight();
		
		exprLeft.accept(this);
		exprRight.accept(this);
		
		if(exprLeft.getResType().equals(TypeDescriptor.ERROR) || exprRight.getResType().equals(TypeDescriptor.ERROR)) { 
			node.setResType(TypeDescriptor.ERROR);
		}
		else if(exprLeft.getResType().equals(exprRight.getResType())) 
				node.setResType(exprLeft.getResType());
		else if(exprLeft.getResType().equals(TypeDescriptor.INT) && exprRight.getResType().equals(TypeDescriptor.FLOAT)) {
					NodeExpr nodeExpr = this.convert(exprLeft);
					node.setLeft(nodeExpr);
					node.setResType(TypeDescriptor.FLOAT);
		}
		else if(exprLeft.getResType().equals(TypeDescriptor.FLOAT) && exprRight.getResType().equals(TypeDescriptor.INT)){
					NodeExpr nodeExpr = this.convert(exprRight);
					node.setRight(nodeExpr);
					node.setResType(TypeDescriptor.FLOAT);
		}
		
	}

	/**
	 * @author Matteo Schirinzi 20035542
	 * @param node - nodo di stampa
	 */
	@Override
	public void visit(NodePrint node) {
		NodeId id = node.getId();
		
		id.accept(this);
		
		if(id.getResType() == TypeDescriptor.ERROR) 
			node.setResType(TypeDescriptor.ERROR);
		else 
			node.setResType(TypeDescriptor.VOID);
		
	}

	/**
	 * @author Matteo Schirinzi 20035542
	 * @param node - nodo assegnamento
	 */
	@Override
	public void visit(NodeAssign node) {
		NodeId id = node.getId();
		NodeExpr expr = node.getExpr();
		
		id.accept(this);
		expr.accept(this);
		
		if(id.getResType() == TypeDescriptor.ERROR || expr.getResType() == TypeDescriptor.ERROR)
			node.setResType(TypeDescriptor.ERROR);
		else if(id.getResType() == TypeDescriptor.INT && expr.getResType() == TypeDescriptor.INT)
			node.setResType(TypeDescriptor.VOID);
		else if(id.getResType() == TypeDescriptor.INT && expr.getResType() == TypeDescriptor.FLOAT)
			node.setResType(TypeDescriptor.ERROR);
		else if(id.getResType() == TypeDescriptor.FLOAT && expr.getResType() == TypeDescriptor.INT) {
			node.setExpr(this.convert(expr));
			node.setResType(TypeDescriptor.VOID);
		}
		else if(id.getResType() == TypeDescriptor.FLOAT && expr.getResType() == TypeDescriptor.FLOAT)
			node.setResType(TypeDescriptor.VOID);

	}

	/**
	 * @author Matteo Schirinzi 20035542
	 * @param node - nodeDeref
	 */
	@Override
	public void visit(NodeDeref node) {
		node.getId().accept(this);
		node.setResType(node.getId().getResType());
	}

	/**
	 * @author Matteo Schirinzi 20035542
	 * @param node - nodo conversione 
	 * Genero NodeConvert quando devo effettuare una conversione di tipo da int a float o viceversa
	 */
	@Override
	public void visit(NodeConvert node) {
		
		node.getExpr().accept(this);
		
		if(node.getExpr().getResType().equals(TypeDescriptor.ERROR))
			node.setResType(TypeDescriptor.ERROR);
		else
			node.setResType(TypeDescriptor.FLOAT);
	}

	/**
	 * @author Matteo Schirinzi 20035542
	 * @param node - nodo costante
	 */
	@Override
	public void visit(NodeCost node) {
		if(node.getType().equals(LangType.INTy))
			node.setResType(TypeDescriptor.INT);
		else if(node.getType().equals(LangType.FLOATy))
			node.setResType(TypeDescriptor.FLOAT);
		else
			node.setResType(TypeDescriptor.ERROR);
		
	}
	
	/**
	 * @author Matteo Schirinzi 20035542
	 * @param node - nodo espressione
	 * @return NodeExpr - restutiuisce un nodeconvert dopo la conversione
	 * Metodo di appoggio per effetturare la conversione di tipo
	 */
	private NodeExpr convert (NodeExpr node) {
		NodeConvert nodeConvert = new NodeConvert(node);
		nodeConvert.setResType(TypeDescriptor.FLOAT);
		return nodeConvert;
	}
	
	

}
