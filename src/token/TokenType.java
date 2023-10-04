package token;

/**
 * Classe enumerativa che rappresenta tutte le tipologie (tipi) di Token
 * @author Matteo Schirinzi 20035542
 */
public enum TokenType {
	/**
     * Tipo floating point
     */
	TYFLOAT,
	/**
     * Tipo intero
     */
	TYINT,
	/**
     * Stampa
     */
	PRINT,
	/**
     * Identificatore
     */
	ID,
	/**
     * Valore intero
     */
	INT,
	/**
     * Valore floating point
     */
	FLOAT,
	/**
     * Assegnamento
     */
	ASSIGN,
	/**
     * Addizione
     */
	PLUS,
	/**
     * Sottrazione
     */
	MINUS,
	/**
     * Moltiplicazione
     */
	TIMES,
	/**
     * Divisione
     */
	DIV,
	/**
     * Semicolon
     */
	SEMI,
	/**
     * Fine del file
     */
	EOF;
}
