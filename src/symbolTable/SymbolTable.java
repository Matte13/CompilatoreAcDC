package symbolTable;

import java.util.HashMap;

/** 
 * Rappresenta la tabella dei simboli nella quale vengono raccolte informazioni sui nomi delle variabili usate nel codice
 * @author Matteo Schirinzi 20035542
*/
public class SymbolTable {
	
	private static HashMap<String, Attributes> table;
	
	/**
	 * @author Matteo Schirinzi 20035542
	 * Metodo utilizzato per l'inizializzazione della symbol table (dell'hashMap)
	 */
	public static void init() {
		table = new HashMap <String, Attributes>();
	}
	
	/**
	 * @author Matteo Schirinzi 20035542
	 * @param id - identificatore
	 * @param entry - attributi dell'identificatore (riga della symboltable)
	 * @return boolean - restituisce true se inserito correttamente altrimenti false
	 * Metodo utilizzato per inserire una entry nella symbol table
	 */
	public static boolean enter(String id, Attributes entry) {
		Attributes value = table.get(id);
		
		if(value != null)
			return false;
		else {
			table.put(id, entry);
			return true;
		}		
	}
	
	/**
	 * @author Matteo Schirinzi 20035542
	 * @param id - identificatore
	 * @return attributes - restituisce la entry di un certo id all'interno della symbol table
	 */
	public static Attributes lookup(String id) {
		return table.get(id);
	}

	/**
	 * @author Matteo Schirinzi 20035542
	 * @return string - restituisce una rappresentazione della classe in formato di stringa
	 */
	public static String toStr() {
		StringBuilder res = new StringBuilder("Symbol Table\n===============\n");
		
		for (HashMap.Entry<String, Attributes> entry : table.entrySet()) {
			res.append(entry.getKey()).append("    \t").append(entry.getValue()).append("\n");
		}
		
		return res.toString();
	}

	/**
	 * @author Matteo Schirinzi 20035542
	 * @return size - dimensione della symbol table
	 */
	public static int size() {
		return (table.size());
	}

}
