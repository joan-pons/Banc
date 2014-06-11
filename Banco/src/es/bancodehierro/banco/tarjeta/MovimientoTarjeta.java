package es.bancodehierro.banco.tarjeta;


public class MovimientoTarjeta {
    int codigo;
    char tipo;
    String fecha;
    String concepto;
    double importe;
    int codigoTarjeta;

    public MovimientoTarjeta(char tipo, String fecha, String concepto, double importe, int codigoTarjeta) {
        this.tipo = tipo;
        this.fecha = fecha;
        this.concepto = concepto;
        this.importe = importe;
        this.codigoTarjeta = codigoTarjeta;
    }
    
}
