package es.bancodehierro.banco.prestamo;

import java.util.ArrayList;
import java.util.Date;

/**
 * Classe de Prestamo.
 *
 * @author Miquel Vallespir, Rafel Sastre, Pau Riera, Jaume Mayol, Tomeu Moranta
 */
public class Prestamo {

    private int codigoPrestamo;
    private String tipoPrestamo;
    private Date fechaInicio;
    private Date fechaFinal;
    private Double importePrestado;
    private Double importeRestante;
    private Double cuotaMensual;
    private Empleado empleadoAutorizacion;
    private CuentaCorriente cuentaCorriente;
    private ArrayList<Movimiento> listaMovimientos;

    public Prestamo(int codigoPrestamo, String tipoPrestamo, Date fechaInicio, Date fechaFinal, Double importePrestado, Double importeRestante, Double cuotaMensual, Empleado empleadoAutorizacion, CuentaCorriente cuentaCorriente) {
        this.codigoPrestamo = codigoPrestamo;
        this.tipoPrestamo = tipoPrestamo;
        this.fechaInicio = fechaInicio;
        this.fechaFinal = fechaFinal;
        this.importePrestado = importePrestado;
        this.importeRestante = importeRestante;
        this.cuotaMensual = cuotaMensual;
        this.empleadoAutorizacion = empleadoAutorizacion;
        this.cuentaCorriente = cuentaCorriente;
        this.listaMovimientos = new ArrayList<>();
    }

    public int getCodigoPrestamo() {
        return codigoPrestamo;
    }

    public String getTipoPrestamo() {
        return tipoPrestamo;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public Date getFechaFinal() {
        return fechaFinal;
    }

    public Double getImportePrestado() {
        return importePrestado;
    }

    public Double getImporteRestante() {
        return importeRestante;
    }

    public Double getCuotaMensual() {
        return cuotaMensual;
    }

    public Empleado getEmpleadoAutorizacion() {
        return empleadoAutorizacion;
    }

    public CuentaCorriente getCuentaCorriente() {
        return cuentaCorriente;
    }

    public ArrayList<Movimiento> getListaMovimientos() {
        return listaMovimientos;
    }

    public void setFechaFinal(Date fechaFinal) {
        this.fechaFinal = fechaFinal;
    }

    public void setImporteRestante(Double importeRestante) {
        this.importeRestante = importeRestante;
    }

    public void setCuotaMensual(Double cuotaMensual) {
        this.cuotaMensual = cuotaMensual;
    }

}
