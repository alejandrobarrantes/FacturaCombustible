package controller;
/**
 *
 * @author 
 */

import model.Compra;
import model.Utilities;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Objects;
import view.Vista;
import view.VistaFactura;

public class Controlador {
    Vista vistaPrincipal;
    VistaFactura vistaFactura;
    Compra compra=new Compra("", "", "", 0);
    Utilities utilities=new Utilities();
    boolean calculado=false;

    public Controlador(Vista v) {
        vistaPrincipal = v;
        vistaPrincipal.setVisible(true);
        vistaPrincipal.addButtonListener(new ControladorListener());
        vistaPrincipal.addItems();
    }

    class ControladorListener implements ActionListener{
        String preferenciaCompra="";
        String tipoVehiculo="";
        String tipoCombustible="";
        float precioLitro=0;
        float monto=0;
        float cantLitros=0;
        int id;
        @Override
        public void actionPerformed(ActionEvent e) {
            String opcion =e.getActionCommand();
            
            if(opcion.equals("1") || opcion.equals("2")){//COMPROBACIONES
                float verificacion=0;
                try {
                    verificacion=Float.parseFloat(vistaPrincipal.getjTxtCantidad().getText());
                } catch (NumberFormatException numberFormatException) {
                    vistaPrincipal.showErrorMessage("Sólo puede digitar números");
                    return;
                }
                if (vistaPrincipal.getjCBTipoVehiculo().getSelectedItem().toString().equals("") ||
                        vistaPrincipal.getjCBTipoComb().getSelectedItem().toString().equals("") ||
                        vistaPrincipal.getjCBPreferencia().getSelectedItem().toString().equals("")) {
                    vistaPrincipal.showErrorMessage("Debe completar todas las opciones");
                    return;
                }
                if (vistaPrincipal.getjTxtCantidad().getText().isBlank()) {
                    vistaPrincipal.showErrorMessage("Debe completar todas las opciones");
                    return;
                }
                
                if (verificacion%1!=0) {
                    vistaPrincipal.showErrorMessage("Digite una cantidad entera");
                    return;
                }
                if (opcion.equals("2") && !calculado) {
                    vistaPrincipal.showErrorMessage("Presione Calcular Monto antes de Pagar");
                    return;
                }
            }
            
            if(opcion.equals("1")){//botón CALCULAR MONTO
                tipoVehiculo=vistaPrincipal.getjCBTipoVehiculo().getSelectedItem().toString();
                tipoCombustible=vistaPrincipal.getjCBTipoComb().getSelectedItem().toString();
                precioLitro=utilities.retornaPrecioLitro(tipoCombustible);
                
                if (preferenciaCompra.equals("Tanque Lleno")){
                    cantLitros=Float.parseFloat(vistaPrincipal.getjTxtCantidad().getText());
                    monto=utilities.calculaMonto(precioLitro, cantLitros);
                }
                
                if (preferenciaCompra.equals("Por Cantidad de Litros")){
                    cantLitros=Float.parseFloat(vistaPrincipal.getjTxtCantidad().getText());
                    float cantidad=Float.parseFloat(vistaPrincipal.getjTxtCantidad().getText());
                    monto=utilities.calculaMonto(cantidad, precioLitro);
                }
                if (preferenciaCompra.equals("Cantidad de Dinero")){
                    monto=Float.parseFloat(vistaPrincipal.getjTxtCantidad().getText());
                    cantLitros= (monto/precioLitro);
                }
                vistaPrincipal.getjTxtMonto().setText(String.valueOf(Math.floor(monto)));
                
                compra=new Compra(tipoVehiculo, tipoCombustible, preferenciaCompra, cantLitros);
                compra.setMontoTotal(monto);
                calculado=true;
            }
            
            if(opcion.equals("13")){//combobox de preferencia de compra
                tipoVehiculo=vistaPrincipal.getjCBTipoVehiculo().getSelectedItem().toString();
                tipoCombustible=vistaPrincipal.getjCBTipoComb().getSelectedItem().toString();
                precioLitro=utilities.retornaPrecioLitro(tipoCombustible);
                preferenciaCompra=vistaPrincipal.getjCBPreferencia().getSelectedItem().toString();
                compra.setTipoCompra(preferenciaCompra);
                vistaPrincipal.setTipo(preferenciaCompra);
                
                if(preferenciaCompra.equals("Tanque Lleno")){
                    if (tipoVehiculo.equals("Automovil")) {
                        cantLitros=utilities.numeroAleatorio(30, 60);
                    }
                    if (tipoVehiculo.equals("Camion")) {
                        cantLitros=utilities.numeroAleatorio(60, 110);
                    }
                    if (tipoVehiculo.equals("Motocicleta")) {
                        cantLitros=utilities.numeroAleatorio(5, 9);
                    }
                    
                    vistaPrincipal.getjTxtCantidad().setText(String.valueOf(cantLitros));
                    
                    vistaPrincipal.setTipo("Cantidad de Litros");
                    vistaPrincipal.getjTxtCantidad().setEnabled(false);
                }
                
                if(preferenciaCompra.equals("Por Cantidad de Litros")){
                    vistaPrincipal.getjTxtCantidad().setEnabled(true);
                    vistaPrincipal.setTipo("Digite la cantidad de Litros");
                }
                if(preferenciaCompra.equals("Cantidad de Dinero")){
                    vistaPrincipal.getjTxtCantidad().setEnabled(true);
                    vistaPrincipal.setTipo("Digite el monto de Dinero");
                }
            }
            
            if(opcion.equals("2")){ // boton PAGAR
                vistaPrincipal.setVisible(false);
                vistaFactura=new VistaFactura(compra);
                vistaFactura.setVisible(true);
                vistaFactura.addButtonListener(new ControladorListener());
            }
            
            if(opcion.equals("3")){ // boton PAGAR
                vistaPrincipal = new Vista();
                vistaPrincipal.setVisible(true);
                vistaPrincipal.addButtonListener(new ControladorListener());
                vistaPrincipal.addItems();
                vistaFactura.setVisible(false);
                vistaFactura=null;
            }
            
           
        }
    }


}