package es.bancodehierro.banco.excepciones;

/**
 *
 * @author Miquel Vallespir, Rafel Sastre, Pau Riera, Jaume Mayol, Tomeu Moranta
 */
public class PrestamoException extends BancoException {

    /**
     * Creates a new instance of <code>PrestamoException</code> without detail
     * message.
     */
    public PrestamoException() {
    }

    /**
     * Constructs an instance of <code>PrestamoException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public PrestamoException(String msg) {
        super(msg);
    }
}
