package visitor;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * E'una classe utilizzata per la definizione dei registri che saranno associati agli identificatori 
 * @author Matteo Schirinzi 20035542
 */
public class Registri {
	
	private static ArrayList<Character> registri;
	
	/**
	 * @author Matteo Schirinzi 20035542
	 * Inizializza l'array list contente l'elenco dei registri
	 */
	public static void init() {
		registri = new ArrayList<>(Arrays.asList(new Character[] {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'}));
	}
	
	/**
	 * @author Matteo Schirinzi 20035542
	 * @return char - restituisce il registro
	 * Restituisce il registro all'inizio dell'array (prima di restituirlo lo rimuove dall'array)
	 */
	public static char newRegister() {
		char c = registri.get(0);
		registri.remove(0);
		return c;
	}
	
	/**
	 * @author Matteo Schirinzi 20035542
	 * @return size - restituisce la dimensione dell'array dei registri
	 */
	public static int size() {
		return registri.size();
	}

}
