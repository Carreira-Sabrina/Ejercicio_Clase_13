package ar.com.carreira.sabrina.persistencia_relacional;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class EjercicioJDBC 
{
    public static void main( String[] args ){
    	
    	String urlBD = "jdbc:mysql://localhost:3306/";
    	String nombreBD = "qatar2022";
    	String usuario = "root";
    	String password = "LtCdrData_4249";
    	
    	Connection conexion = null;
    	
    	
    	try {
    		Class.forName("com.mysql.cj.jdbc.Driver");
			conexion = DriverManager.getConnection(urlBD + nombreBD, usuario, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
    	
    	//Crear consulta para insertar empleado en la nomina de trabajo
    	String sqlAgregarEmpleado ="INSERT INTO empleados (`dni`,`nombre`,`apellido`,`nacionalidad`,"
    			+ "`departamento`) VALUES(\"18501999\",\"Ricky\",\"Maravilla\",\"argentino\",\"4\")";
    	//Consulta para modificar la nacionalidad de un empleado
    	String sqlModifNacEmpleado = "UPDATE `empleados` SET `nacionalidad` = \"colombiano\" WHERE"
    			+ " `empleados`.`dni` = \"93561487\"";
    	//Consulta eliminar departamento
    	String sqlEliminarDepto = "DELETE FROM departamentos WHERE departamentos.id = 6";
    	//Consulta para conocer a los empleados del Depto de Logística
    	String sqlConocerEmpleadosDepto = "SELECT * FROM empleados WHERE `departamento` = 1";
    	//Consulta para obtener la lista de departamentos ordenada alfabeticamente
    	String DeptosAlfabeticamente = "SELECT * FROM `departamentos` ORDER BY `nombre` ASC";
    	
    	Statement stAgregarEmpleado;
    	Statement stModifNacEmpleado;
    	Statement stEliminarDepto;
    	Statement stEmpleadosDepto;
    	Statement stDeptosAlfabeticamente;
    	
    	
    	try {
    		
			
			stAgregarEmpleado = conexion.createStatement(); 
			stModifNacEmpleado =conexion.createStatement(); 
			stEliminarDepto = conexion.createStatement();
			stEmpleadosDepto = conexion.createStatement(); 
			stDeptosAlfabeticamente =conexion.createStatement();
			 
			 //Se ejecuta consulta para agregar empleado
			 stAgregarEmpleado.executeUpdate(sqlAgregarEmpleado); 
			 //Se ejecuta consulta para modificar nacionalidad de empleado
			 stModifNacEmpleado.executeUpdate(sqlModifNacEmpleado); 
			 //Se ejecuta consulta para eliminar Depto 
			 stEliminarDepto.executeUpdate(sqlEliminarDepto);
			 
	    	
	    	//Consulta para conocer todos los empleados de un departamento
	    	ResultSet rsEmpleadosDpto = stEmpleadosDepto.executeQuery(sqlConocerEmpleadosDepto);
	    	System.out.println("EMPLEADOS DEL DEPARTAMENTO DE LOGISTICA");
	    	while(rsEmpleadosDpto.next()) {
	    		String dni = rsEmpleadosDpto.getString("dni");
	    		String nombre = rsEmpleadosDpto.getString("nombre");
	    		String apellido = rsEmpleadosDpto.getString("apellido");
	    		String nacionalidad = rsEmpleadosDpto.getString("nacionalidad");
	    		int dpto = rsEmpleadosDpto.getInt("departamento");
	    		
	    		System.out.print(dni + "  " + nombre + "  " + apellido + "  " + nacionalidad + "  " + dpto +"\n");	
	    	}
	    	
	    	System.out.println("\n");
	    	System.out.println("DEPARTAMENTOS");
	    	
	    	//Se ejecuta consulta para para obtener la lista de departamentos ordenada alfabeticamente
	    	ResultSet rsDptosAlfabeticamente = stDeptosAlfabeticamente.executeQuery(DeptosAlfabeticamente);
	    	while(rsDptosAlfabeticamente.next()) {
	    		int id = rsDptosAlfabeticamente.getInt("id");
	    		String nombre = rsDptosAlfabeticamente.getString("nombre");
	    		
	    		System.out.println(id + "  " + nombre );
	    	}
	    	
	    	
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
       
    }
}
