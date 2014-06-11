package es.bancodehierro.banco.excepciones;

public class ImporteNegativoException extends Exception {

    public ImporteNegativoException() {
    }

    public ImporteNegativoException(String msg) {
        super(msg);
    }
}
