package es.bancodehierro.banco.prestamo;

import es.bancodehierro.banco.cc.CuentaCorriente;
import es.bancodehierro.banco.cc.Movimiento;
import static es.bancodehierro.banco.enumeraciones.EnumMovimiento.PRESTAMO;
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

    //Els he afegit, cal discutir-ho. Veure mètodes cuotaMensual y
    //Cálculo total a Pagar (Jaume)
    private double tasaInteresAnual;
    private int nombreAños;

    public Prestamo(int codigoPrestamo, String tipoPrestamo, Date fechaInicio, Date fechaFinal, Double importePrestado, Double tasaInteresAnual, Empleado empleadoAutorizacion, CuentaCorriente cuentaCorriente) {
        this.codigoPrestamo = codigoPrestamo;
        this.tipoPrestamo = tipoPrestamo;
        this.fechaInicio = fechaInicio;
        this.fechaFinal = fechaFinal;
        this.importePrestado = importePrestado;
        this.tasaInteresAnual = tasaInteresAnual;
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
            //Get para conseguir el tipo y lo igualamos a PRESTAMO
            //PRESTAMO, tipo de dato enumerario importado de la clase Enumeración.
            if (aux.getTipo() == PRESTAMO) {
                prestamos.add(aux);
            }
        }
        return prestamos;
    }

    /**
     * <b>Cálculo cuota mensual</b> Si pedimos un préstamo de 1.000 € a 18 meses
     * con un Interés del 3,5, entonces para obtener la cuota mensual es 1.000 x
     * 3,5/1,200 (12 mesos) x 18 (durada préstec) I = 1000 x 3.5/1,200 x 18 =
     * 52.50€.
     *
     * @author Jaume Mayol
     * @return
     */
    public double cuotaMensual() {
        double taxaInteresMensual = tasaInteresAnual / 1200;
        double importeMensual = importePrestado * taxaInteresMensual / (1
                - (Math.pow(1 / (1 + taxaInteresMensual), nombreAños * 12)));
        return importeMensual;
    }

    /**
     * Cálculo total a pagar (capital prestado + intereses) Se utiliza el método
     * cuotaMensual y se hace la sencilla de operación de multiplicar su
     * resultado por el nombre de Años del préstamo por los 12 meses del año.
     *
     * @author Jaume Mayol
     * @see cuotaMensual
     * @return el pago total que deberà el prestatario
     */
    public double calculoTotalAPagar() {
        double pagoTotal = cuotaMensual() * nombreAños * 12;
        return pagoTotal;
    }

    /**
     * Cancelar Préstamo Versió molt "experimental" També es pot calcular com:
     * [deuda pendint x interes Esperado x meses restantes] / 12
     *
     * @author Jaume Mayol
     * @param importePagado
     * @param tasaInteresMensual
     * @param tasaInteresEsperado
     * @return
     */
    public double cancelarPrestamo(double importePagado, double tasaInteresMensual, double tasaInteresEsperado) {
        double interesesCancelacion = importePrestado * tasaInteresMensual / (1
                - (Math.pow(1 / (1 + tasaInteresEsperado), nombreAños * 12)));
        double pagoFinal = calculoTotalAPagar() - importePagado + interesesCancelacion;
        //eliminarPrestamo();
        return pagoFinal;
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

    /**
     * Mètodo de inserción de prestamo.
     *
     * @author Rafel Sastre.
     * @return
     */
//    public String insertarPrestamo() {
//        return "INSERT INTO PRESTAMO (CODIGO_PRESTAMO, IMPORTE_PRESTAMO, DURACION_MES_PRESTAMO, DNI_TRABAJADOR, FECHA_FIRMA_PRESTAMO, CODIGO_SUC_TARJETA, NUMERO_CC_PRESTAMO) VALUES (" + getCodigo_Pretamo() + ", " + getImporte_Prestamo() + ", "getDuracion_Mes_Prestamo() + ", " + getDni_Trabajador() + ", " + getFecha_Firma_Prestamo() + ", " + getCodigo_Suc_Tarjeta() + ", " + getNumero_Cc_Prestamo() + ")";
//    }

    /**
     * Método de modificación de préstamo (por código)
     *
     * @author Pau Riera.
     * @return
     */
    public String updatePrestamo() {
        return "UPDATE Prestamo SET (" + getCodigoPrestamo() + ", " + getTipoPrestamo() + ", " + getFechaInicio() + ", " + getFechaFinal() + ", " + getImportePrestado() + ", " + getImporteRestante() + ", " + getCuotaMensual() + ", " + getEmpleadoAutorizacion() + ", " + getCuentaCorriente() + ") WHERE codiprestamo = " + getCodigoPrestamo();
    }

    /**
     * Método de eliminación de préstamo (por código)
     *
     * @author Jaume Mayol
     * @return
     */
    public String eliminarPrestamo() {
        return "DELETE FROM Prestamo WHERE CodigoPrestamo=" + getCodigoPrestamo();
    }

}
