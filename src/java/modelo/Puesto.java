/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;

public class Puesto {
    private int idpuesto;
    private String puestos;
    private Conexion cn;

    public Puesto() {
    }

    public Puesto(int idpuesto, String puestos) {
        this.idpuesto = idpuesto;
        this.puestos = puestos;
    }

    public int getIdpuesto() {
        return idpuesto;
    }

    public void setIdpuesto(int idpuesto) {
        this.idpuesto = idpuesto;
    }

    public String getPuestos() {
        return puestos;
    }

    public void setPuestos(String puestos) {
        this.puestos = puestos;
    }

    public DefaultTableModel leer() {
        DefaultTableModel tabla = new DefaultTableModel();
        try {
            cn = new Conexion();
            cn.abrir_cn();
            String query = "SELECT * FROM puestos";
            ResultSet consulta = cn.conexionBD.createStatement().executeQuery(query);
            String encabezado[] = {"idpuesto", "puestos"};
            tabla.setColumnIdentifiers(encabezado);
            String datos[] = new String[2];
            while (consulta.next()) {
                datos[0] = consulta.getString("idpuesto");
                datos[1] = consulta.getString("puestos");
                tabla.addRow(datos);
            }
            cn.cerrar_cn();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return tabla;
    }

    public int agregar() {
        int retorno = 0;
        try {
            PreparedStatement parametro;
            cn = new Conexion();
            String query = "INSERT INTO puestos (idpuesto, puestos) VALUES (?, ?)";
            cn.abrir_cn();
            parametro = cn.conexionBD.prepareStatement(query);
            parametro.setInt(1, getIdpuesto());
            parametro.setString(2, getPuestos());
            retorno = parametro.executeUpdate();
            cn.cerrar_cn();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            retorno = 0;
        }
        return retorno;
    }

    public int modificar() {
        int retorno = 0;
        try {
            PreparedStatement parametro;
            cn = new Conexion();
            String query = "UPDATE marcas SET puestos = ? WHERE idpuestos = ?";
            cn.abrir_cn();
            parametro = cn.conexionBD.prepareStatement(query);
            parametro.setString(1, getPuestos());
            parametro.setInt(2, getIdpuesto());
            retorno = parametro.executeUpdate();
            cn.cerrar_cn();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            retorno = 0;
        }
        return retorno;
    }

    public int eliminar() {
        int retorno = 0;
        try {
            PreparedStatement parametro;
            cn = new Conexion();
            String query = "DELETE FROM marcas WHERE idpuestos = ?";
            cn.abrir_cn();
            parametro = cn.conexionBD.prepareStatement(query);
            parametro.setInt(1, getIdpuesto());
            retorno = parametro.executeUpdate();
            cn.cerrar_cn();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            retorno = 0;
        }
        return retorno;
    }
    public static void main(String [] args){
        Puesto m= new Puesto();
        m.setIdpuesto(3);
        m.setPuestos("hunter");
        int resultado= m.agregar();
         DefaultTableModel tabla = new DefaultTableModel();
        tabla = m.leer();
        System.out.println("el resultado es"+ tabla);
    }
}

