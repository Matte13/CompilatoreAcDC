package ast;

/**
 * Classe enumerativa che rappresenta per ogni nodo visitato nel TypeChecking il suo risultato
 * @author Matteo Schirinzi 20035542
*/
public enum TypeDescriptor {
	/**
     * Int per i nodi espressione corretti (e che quindi hanno uno dei due tipi)
     */
	INT,
	/**
     * Float per i nodi espressione corretti (e che quindi hanno uno dei due tipi)
     */
	FLOAT,
	/**
     * Void per i nodi dichiarazione e istruzione corretti
     */
	VOID,
	/**
     * Error per un nodo scorretto (o con sottoparti scorrette)
     */
	ERROR
}
