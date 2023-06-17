/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Dell-PC
 */
public class Utilities {

    public Utilities() {
    }
    
    
    public float retornaPrecioLitro(String tipoCombustible){
        switch (tipoCombustible) {
            case "Diesel":
                return (float) 834.57;
            case "Super":
                return (float) 945.27;
            case "Regular":
                return (float) 992.05;
            default:
                return  0;
        }
    }
    
    public float calculaMonto(float cantLitros, float precioLitro){
        return cantLitros*precioLitro;
    }
    
    public int numeroAleatorio(int min, int max){
        return (int) Math.floor((Math.random()*(max-min + 1)) + min);
    }
}
