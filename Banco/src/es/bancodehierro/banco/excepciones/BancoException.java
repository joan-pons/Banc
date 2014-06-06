package es.bancodehierro.banco.excepciones;

/**
 *
 * @author Banco
 */
public class BancoException extends Exception {

    /**
     * Creates a new instance of <code>BancoException</code> without detail
     * message.
     */
    public BancoException() {
    }

    /**
     * Constructs an instance of <code>BancoException</code> with the specified
     * detail message.
     *
     * @param msg the detail message.
     */
    public BancoException(String msg) {
        super(msg);
    }
}
