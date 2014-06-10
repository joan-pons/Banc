package es.bancodehierro.banco.prestamo;

import es.bancodehierro.banco.cc.CuentaCorriente;
import es.bancodehierro.banco.cc.Movimiento;
import es.bancodehierro.banco.excepciones.PrestamoException;
import es.bancodehierro.banco.persona.Empleado;
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

    /**
     * Buscador de préstamos El método parte del listado Movimiento y selecciona
     * sólo los de tipo Préstamo. Se puede reaprovechar para otros grupos!
     *
     * @author Jaume Mayol
     * @param listaMovimientos
     * @return el objeto ArrayList de Movimiento con el tipo sólo de préstamo.
     * @see es.bancodehierro.banco.cc.Movimiento
     */
    public ArrayList<Movimiento> buscadorPrestamos(ArrayList<Movimiento> listaMovimientos) {
        ArrayList<Movimiento> prestamos = new ArrayList<>();
        for (int i = 0; i < listaMovimientos.size(); ++i) {
            Movimiento aux = listaMovimientos.get(i);
            //S'ha de fer públic l'atribut tipus!!! (grup compte corrent!)
            if (aux.tipo == PRESTAMO) {
                prestamos.add(aux);
            }
        }
        return prestamos;
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

    public boolean añadirMovimiento(Movimiento mov) throws PrestamoException {

        if (mov != null) {
            this.listaMovimientos.add(mov);
            return true;
        }

        return false;

    }

}
