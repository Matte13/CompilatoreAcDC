package visitor;

import java.util.HashMap;

import ast.NodeAssign;
import ast.NodeBinOp;
import ast.NodeConvert;
import ast.NodeCost;
import ast.NodeDecSt;
import ast.NodeDecl;
import ast.NodeDeref;
import ast.NodeId;
import ast.NodePrint;
import ast.NodeProgram;
import ast.TypeDescriptor;
import symbolTable.SymbolTable;

/**
 * E' la classe che implementa l'ultima fase del comoilatore ovvero la generazione del codice 
 * @author Matteo Schirinzi 20035542
 */
public class CodeGeneratorVisitor implements IVisitor{
	
	private HashMap<ast.LangOper,Character> operators;

	/**
	 * @author Matteo Schirinzi 20035542
	 * Inizializza i registri e richiama la funzione init() 
	 */
	public CodeGeneratorVisitor() {
		super();
		Registri.init();
		this.init();
	}
	
	/**
	 * @author Matteo Schirinzi 20035542
	 * @param node - nodeProgram 
	 * Se il TypeChecking non genera errori visita tutti i nodi generando il codice di ognuno e concatenandolo
	 */
	@Override
	public void visit(NodeProgram node) {
		String codice = "";
		
		//controllo che ci siano abbastanza registri per effetturare la generazione del codice
		if(Registri.size() < SymbolTable.size())
			node.setCodice(codice);
		
		//controllo che il TypeChecking non restituisca un ERROR altrimenti il codeGenerator genera stringa vuota
		if(node.getResType() != TypeDescriptor.ERROR) {
			for(NodeDecSt nodeSt: node.getDecSts()) {
				nodeSt.accept(this);
				if(nodeSt.getCodice() != null)
					codice += nodeSt.getCodice() + " ";
			}
			
			node.setCodice(codice.substring(0, codice.length()-1));
		}
		else
			node.setCodice(codice);
	}

	/**
	 * @author Matteo Schirinzi 20035542
	 * @param node - nodo identificatore
	 * Setto il codice del nodo perchè non contine altri nodi al suo interno visitando la symboltable
	 */
	@Override
	public void visit(NodeId node) {
		node.setCodice("" + SymbolTable.lookup(node.getName()).getRegistro());
	}

	/**
	 * @author Matteo Schirinzi 20035542
	 * @param node - nodo dichiarazione
	 * Genera un nuovo registro da associare all’attributo dell’identificatore dichiarato e lo assegna all’identificatore nella symbol table. 
	 * Se c’è una inizializzazione generare codice come per l’assegnamento (alla variabile dichiarata).
	 */
	@Override
	public void visit(NodeDecl node) {
		node.getId().getDefinition().setRegistro(Registri.newRegister());
		
		//c'è un inizializzazione
		if(node.getInit() != null) {
			node.getId().accept(this);
			node.getInit().accept(this);
			node.setCodice(node.getInit().getCodice() + " s" + node.getId().getCodice() + " 0 k");	//riporto la precisione a 0 
		}
	}

	/**
	 * @author Matteo Schirinzi 20035542
	 * @param node - nodo operazione binaria
	 * Genera codice per l’espressione a sinistra, poi per quella a destra e poi quello dell’operazione.
	 */
	@Override
	public void visit(NodeBinOp node) {
		String codice = "";
		Character operatore = this.operators.get(node.getOp());
		
        node.getLeft().accept(this);	//genero il codice per l'espressione a sinistra
        node.getRight().accept(this);	//genero il codice per l'espressione a destra
        codice += node.getLeft().getCodice() + " " + node.getRight().getCodice();
        
        //Se i due nodi non sono entrambi int devo settare la precisione a 5 k
        if (node.getLeft().getResType() == TypeDescriptor.FLOAT && node.getRight().getResType() == TypeDescriptor.FLOAT)
        	codice += " 5 k";
        
        node.setCodice(codice + " " + operatore + " 0 k");	//resetto la precisone a 0 k 
	}

	/**
	 * @author Matteo Schirinzi 20035542
	 * @param node - nodo di stampa
	 * Genera il codice per fare il push sullo stack del registro associato al identificatore. 
	 * Genera il codice per stamparlo e poi rimuoverlo dallo stack.
	 */
	@Override
	public void visit(NodePrint node) {
		node.getId().accept(this);
		node.setCodice("l" + node.getId().getCodice() + " p P");
	}

	/**
	 * @author Matteo Schirinzi 20035542
	 * @param node - nodo assegnamento
	 * Genera codice per l’espressione a destra dell’assegnamento. 
	 * Memorizza il top dello stack nel registro associato all’identificatore a sinistra. 
	 * Riporta la precisione a 0.
	 */
	@Override
	public void visit(NodeAssign node) {
		node.getId().accept(this);
		node.getExpr().accept(this);
		node.setCodice(node.getExpr().getCodice() + " s" + node.getId().getCodice() + " 0 k"); //Riporto la precisone a 0
	}

	/**
	 * @author Matteo Schirinzi 20035542
	 * @param node - nodoDeref
	 * Genera il codice per fare il push sullo stack del registro associato all’identificatore.
	 */
	@Override
	public void visit(NodeDeref node) {
		node.getId().accept(this);
		node.setCodice("l" + node.getId().getCodice());
	}

	/**
	 * @author Matteo Schirinzi 20035542
	 * @param node - nodo conversione di tipo (casting)
	 * Genera il codice per dal codice dell'espressione di NodeConvert
	 */
	@Override
	public void visit(NodeConvert node) {
		node.getExpr().accept(this);
		node.setCodice(node.getExpr().getCodice() + " 5 k");
	}

	/**
	 * @author Matteo Schirinzi 20035542
	 * @param node - nodo costante
	 * Genera il codice per fare il push sullo stack della costante, prende il valore del nodo costante e lo inserisce come codice
	 */
	@Override
	public void visit(NodeCost node) {
		node.setCodice(node.getValue());
	}
	
	/**
	 * @author Matteo Schirinzi 20035542
	 * Inizializza l'hashMap operators
	 */
	private void init() {
		this.operators = new HashMap<>();
		this.operators.put(ast.LangOper.PLUS, '+');
		this.operators.put(ast.LangOper.MINUS, '-');
		this.operators.put(ast.LangOper.DIV, '/');
		this.operators.put(ast.LangOper.TIMES, '*');
	}

}
