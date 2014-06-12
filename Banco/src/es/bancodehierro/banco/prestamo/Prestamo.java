package es.bancodehierro.banco.prestamo;

import es.bancodehierro.banco.cc.CuentaCorriente;
import es.bancodehierro.banco.cc.Movimiento;
import static es.bancodehierro.banco.enumeraciones.EnumMovimiento.PRESTAMO;
import es.bancodehierro.banco.excepciones.PrestamoException;
import es.bancodehierro.banco.persona.Empleado;
import es.bancodehierro.banco.sucursal.Sucursal;
import java.util.ArrayList;
import java.util.Date;

/**
 * Classe de Prestamo.
 *
 * @author Miquel Vallespir, Rafel Sastre, Pau Riera, Jaume Mayol i Tomeu
 * Moranta.
 */
public class Prestamo {

    private int codigo_Prestamo;
    private Double importe_Prestamo;
    private Date duracion_Mes_Prestamo;
    private Empleado dni_Trabajador;
    private Date fecha_Firma_Prestamo;
    private Sucursal codigo_Suc_Tarjeta;
    private CuentaCorriente numero_Cc_Prestamo;
    private ArrayList<Movimiento> listaMovimientos;

    //Els he afegit, cal discutir-ho. Veure mètodes cuotaMensual y
    //Cálculo total a Pagar (Jaume)
    private double tasaInteresAnual;
    private int nombreAños;

    public Prestamo(int codigo_Prestamo, Double importe_Prestamo, Date duracion_Mes_Prestamo, Empleado dni_Trabajador, Date fecha_Firma_Prestamo, Sucursal codigo_Suc_Tarjeta, CuentaCorriente numero_Cc_Prestamo) {
        this.codigo_Prestamo = codigo_Prestamo;
        this.importe_Prestamo = importe_Prestamo;
        this.duracion_Mes_Prestamo = duracion_Mes_Prestamo;
        this.dni_Trabajador = dni_Trabajador;
        this.fecha_Firma_Prestamo = fecha_Firma_Prestamo;
        this.codigo_Suc_Tarjeta = codigo_Suc_Tarjeta;
        this.numero_Cc_Prestamo = numero_Cc_Prestamo;
        this.listaMovimientos = new ArrayList<>();
    }

    public int getCodigo_Prestamo() {
        return codigo_Prestamo;
    }

    public Double getImporte_Prestamo() {
        return importe_Prestamo;
    }

    public Date getDuracion_Mes_Prestamo() {
        return duracion_Mes_Prestamo;
    }

    public Empleado getDni_Trabajador() {
        return dni_Trabajador;
    }

    public Date getFecha_Firma_Prestamo() {
        return fecha_Firma_Prestamo;
    }

    public Sucursal getCodigo_Suc_Tarjeta() {
        return codigo_Suc_Tarjeta;
    }

    public CuentaCorriente getNumero_Cc_Prestamo() {
        return numero_Cc_Prestamo;
    }

    public ArrayList<Movimiento> getListaMovimientos() {
        return listaMovimientos;
    }

    public double getTasaInteresAnual() {
        return tasaInteresAnual;
    }

    public int getNombreAños() {
        return nombreAños;
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
//    public double cuotaMensual() {
//        double taxaInteresMensual = tasaInteresAnual / 1200;
//        double importeMensual = importePrestado * taxaInteresMensual / (1
//                - (Math.pow(1 / (1 + taxaInteresMensual), nombreAños * 12)));
//        return importeMensual;
//    }
    /**
     * Cálculo total a pagar (capital prestado + intereses) Se utiliza el método
     * cuotaMensual y se hace la sencilla de operación de multiplicar su
     * resultado por el nombre de Años del préstamo por los 12 meses del año.
     *
     * @author Jaume Mayol
     * @see cuotaMensual
     * @return el pago total que deberà el prestatario
     */
//    public double calculoTotalAPagar() {
//        double pagoTotal = cuotaMensual() * nombreAños * 12;
//        return pagoTotal;
//    }
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
//    public double cancelarPrestamo(double importePagado, double tasaInteresMensual, double tasaInteresEsperado) {
//        double interesesCancelacion = importePrestado * tasaInteresMensual / (1
//                - (Math.pow(1 / (1 + tasaInteresEsperado), nombreAños * 12)));
//        double pagoFinal = calculoTotalAPagar() - importePagado + interesesCancelacion;
//        //eliminarPrestamo();
//        return pagoFinal;
//    }
    public void setImporte_Prestamo(Double importe_Prestamo) {
        this.importe_Prestamo = importe_Prestamo;
    }

    public void setFecha_Firma_Prestamo(Date fecha_Firma_Prestamo) {
        this.fecha_Firma_Prestamo = fecha_Firma_Prestamo;
    }

    public void setTasaInteresAnual(double tasaInteresAnual) {
        this.tasaInteresAnual = tasaInteresAnual;
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
    public String insertarPrestamo() {
        return "INSERT INTO PRESTAMO (CODIGO_PRESTAMO, IMPORTE_PRESTAMO, DURACION_MES_PRESTAMO, DNI_TRABAJADOR, FECHA_FIRMA_PRESTAMO, CODIGO_SUC_TARJETA, NUMERO_CC_PRESTAMO) VALUES (" + getCodigo_Prestamo() + ", " + getImporte_Prestamo() + ", " + getDuracion_Mes_Prestamo() + ", '" + getDni_Trabajador() + "', " + getFecha_Firma_Prestamo() + ", " + getCodigo_Suc_Tarjeta() + ", '" + getNumero_Cc_Prestamo() + "')";
    }

    /**
     * Método de modificación de préstamo (por código)
     *
     * @author Pau Riera.
     * @return
     */
//    public String updatePrestamo() {
//        return "UPDATE Prestamo SET (" + getCodigoPrestamo() + ", " + getTipoPrestamo() + ", " + getFechaInicio() + ", " + getFechaFinal() + ", " + getImportePrestado() + ", " + getImporteRestante() + ", " + getCuotaMensual() + ", " + getEmpleadoAutorizacion() + ", " + getCuentaCorriente() + ") WHERE codiprestamo = " + getCodigoPrestamo();
//    }
    /**
     * Método de eliminación de préstamo (por código)
     *
     * @author Jaume Mayol
     * @return
     */
    public String eliminarPrestamo() {
        return "DELETE FROM Prestamo WHERE Codigo_Prestamo=" + getCodigo_Prestamo();
    }

}
