/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Dell-PC
 */
public class Compra {
    private String tipoVehiculo;
    private String tipoCombustible;
    private String tipoCompra;
    private float cantLitros;
    private float montoTotal;

    public Compra(String tipoVehiculo, String tipoCombustible, String tipoCompra, float cantLitros) {
        this.tipoVehiculo = tipoVehiculo;
        this.tipoCombustible = tipoCombustible;
        this.tipoCompra = tipoCompra;
        this.cantLitros = cantLitros;
    }

    public String getTipoVehiculo() {
        return tipoVehiculo;
    }

    public void setTipoVehiculo(String tipoVehiculo) {
        this.tipoVehiculo = tipoVehiculo;
    }

    public String getTipoCombustible() {
        return tipoCombustible;
    }

    public void setTipoCombustible(String tipoCombustible) {
        this.tipoCombustible = tipoCombustible;
    }

    public String getTipoCompra() {
        return tipoCompra;
    }

    public void setTipoCompra(String tipoCompra) {
        this.tipoCompra = tipoCompra;
    }

    public float getCantLitros() {
        return cantLitros;
    }

   

    public void setCantLitros(int cantLitros) {
        this.cantLitros = cantLitros;
    }

    public float getMontoTotal() {
        return montoTotal;
    }

    public void setMontoTotal(float montoTotal) {
        this.montoTotal = montoTotal;
    }
    
    
    
    
    
}
