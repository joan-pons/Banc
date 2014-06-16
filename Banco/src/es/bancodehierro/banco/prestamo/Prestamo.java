package es.bancodehierro.banco.prestamo;

import es.bancodehierro.banco.cc.CuentaCorriente;
import es.bancodehierro.banco.cc.Movimiento;
import es.bancodehierro.banco.conexion.Conexion;
import static es.bancodehierro.banco.enumeraciones.EnumMovimiento.PRESTAMO;
import es.bancodehierro.banco.excepciones.PrestamoException;
import es.bancodehierro.banco.persona.Empleado;
import es.bancodehierro.banco.sucursal.Sucursal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

/**
 * Clase de Prestamo.
 * Toma el control sobre los préstamos y las operaciones sobre ellos.
 * @author Miquel Vallespir, Rafel Sastre, Pau Riera, Jaume Mayol i Tomeu
 * Moranta.
 */
public class Prestamo {
    /**
     * Atributos de la clase préstamo, almacenan información sobre el préstamo en cuestión.
     */
    private int codigoPrestamo;
    private Double importePrestamo;
    private int duracionMesPrestamo;
    private Empleado dniTrabajador;
    private Date fechaFirmaPrestamo;
    private Sucursal codigoSucTarjeta;
    private CuentaCorriente numeroCcPrestamo;
    private ArrayList<Movimiento> listaMovimientos;

    /**
     * Constructor de la clase Préstamo
     * @param codigoPrestamo Código único que identifica el préstamo.
     * @param importePrestamo Cantidad mensual a pagar.
     * @param duracionMesPrestamo Cuántos meses durará el préstamo.
     * @param dniTrabajador DNI del trabajador que dará de alta el préstamo
     * @param numeroCcPrestamo Cuenta Corriente en la cual se asociará el préstamo.
     */
    public Prestamo(int codigoPrestamo, Double importePrestamo, int duracionMesPrestamo, Empleado dniTrabajador, CuentaCorriente numeroCcPrestamo) {
        this.codigoPrestamo = codigoPrestamo;
        this.importePrestamo = importePrestamo;
        this.duracionMesPrestamo = duracionMesPrestamo;
        this.dniTrabajador = dniTrabajador;
        this.numeroCcPrestamo = numeroCcPrestamo;
        this.listaMovimientos = new ArrayList<>();
    }

    public int getCodigoPrestamo() {
        return codigoPrestamo;
    }

    public Double getImportePrestamo() {
        return importePrestamo;
    }

    public int getDuracionMesPrestamo() {
        return duracionMesPrestamo;
    }

    public Empleado getDniTrabajador() {
        return dniTrabajador;
    }

    public Date getFechaFirmaPrestamo() {
        return fechaFirmaPrestamo;
    }

    public Sucursal getCodigoSucTarjeta() {
        return codigoSucTarjeta;
    }

    public CuentaCorriente getNumeroCcPrestamo() {
        return numeroCcPrestamo;
    }

    public ArrayList<Movimiento> getListaMovimientos() {
        return listaMovimientos;
    }

    /**
     * Ver Movimientos Préstamo
     * Busca todos los movimientos de un préstamo.
     * @Jaume Mayol
     */
    public void verMovimientosPrestamo(){
       try {
            ResultSet rs = Conexion.conectar().createStatement().executeQuery("SELECT CODIGO_MP,"
                    + "CODIGO_PRESTAMO_MP,"
                    + "OPERACION_MP"
                    + "to_char(FECHA_MP),"
                    + "IMPORTE_MP,"
                    + "CONCEPTO_MP "
                    + "FROM MOVIMIENTO_PRESTAMO "
                    + "WHERE CODIGO_PRESTAMO_MP='"+codigoPrestamo+"'");
            while(rs.next()){
                System.out.println(
                        "Codigo Movimiento: "+rs.getString(1)+
                        ", Codigo Préstamo: "+rs.getInt(2)+
                        ", Operacion: "+rs.getString(3)+
                        ", Fecha: "+rs.getString(4)+
                        ", Importe: "+rs.getDouble(5)+
                        ", Concepto: "+rs.getString(6));

            }
        } catch (SQLException ex) {
                System.out.println("Error: " + ex.getMessage() + ". \n ErrorCode:" + ex.getErrorCode() + ", SQLState:" + ex.getSQLState());
        }
        
    }
    
    public void setImportePrestamo(Double importePrestamo) {
        this.importePrestamo = importePrestamo;
    }

    public void setFechaFirmaPrestamo(Date fechaFirmaPrestamo) {
        this.fechaFirmaPrestamo = fechaFirmaPrestamo;
    }

    /**
     * Añade un movimiento a la lista de movimientos propia del préstamo.
     * @param mov
     * @return boolean
     * @throws PrestamoException 
     */
    public boolean añadirMovimiento(Movimiento mov) throws PrestamoException {
        if (mov != null) {
            this.listaMovimientos.add(mov);
            return true;
        }
        return false;
    }
    
    /**
 * Buscador de préstamos El método parte del listado Movimiento y selecciona
 * sólo los de tipo Préstamo.
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
     * Mètodo de inserción de prestamo.
     *
     * @author Rafel Sastre.
     * @return String
     */
    public String insertarPrestamo() {
        return "INSERT INTO PRESTAMO (CODIGO_PRESTAMO, IMPORTE_PRESTAMO, DURACION_MES_PRESTAMO, DNI_TRABAJADOR, FECHA_FIRMA_PRESTAMO, CODIGO_SUC_TARJETA, NUMERO_CC_PRESTAMO) VALUES (" + getCodigoPrestamo() + ", " + getImportePrestamo() + ", " + getDuracionMesPrestamo() + ", '" + getDniTrabajador() + "', " + getFechaFirmaPrestamo() + ", " + getCodigoSucTarjeta() + ", '" + getNumeroCcPrestamo() + "')";
    }

    /**
     * Método de modificación de préstamo (por código)
     *
     * @author Pau Riera.
     * @return String
     */
    public String updatePrestamo() {
        return "UPDATE Prestamo SET (" + getCodigoPrestamo() + ", " + getImportePrestamo() + ", " + getDuracionMesPrestamo() + ", " + getDniTrabajador() + ", " + getFechaFirmaPrestamo() + ", " + getCodigoSucTarjeta() + ", " + getNumeroCcPrestamo() + ") WHERE codiprestamo = " + getCodigoPrestamo();
    }

    /**
     * Eliminar Préstamo Elimina el préstamo por código, si lo encuentra. Si no, lanza
     * una excepción de préstamo o de SQL.
     *
     * @author Jaume Mayol Hervás
     * @return String
     */
    public String eliminarPrestamo() {
        return "DELETE FROM Prestamo WHERE Codigo_Prestamo=" + getCodigoPrestamo();

    }
}
