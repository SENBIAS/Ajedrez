/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.utp.isc.pro4.ajedrez.modelo;

import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.GeneralPath;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import javax.swing.JOptionPane;

/**
 *
 * @author utp
 */
public class Rey extends Ficha {

    public Rey(Color color) {
        super(color);
    }

    @Override
    public boolean mover(Tablero tablero, Casilla casillaI, Casilla casillaF) {
        boolean ocupada = false, efectivo = false;
    int cI,cF,fI,fF, restaF, restaC;
    cI = casillaI.getColumna() - 'A';//x Inicial
    fI = casillaI.getFila() - 1;//y Inicial
    cF = casillaF.getColumna() - 'A';//x Final 
    fF = casillaF.getFila() - 1 ;//y Final
    Casilla casillaC;
    casillaC = casillaI;
    restaF = fI - fF;
    restaC = cI - cF;
    if(Math.abs(restaF) <= 1 & Math.abs(restaC) <= 1){
       if (casillaF.getColumna() > casillaI.getColumna() && casillaF.getFila() > casillaI.getFila()){
                    cI = cI + 1;
                    fI = fI + 1;
                }
                else if(casillaF.getColumna() < casillaI.getColumna() && casillaF.getFila() > casillaI.getFila()){
                    cI = cI - 1;
                    fI = fI + 1;
                }
                else if(casillaF.getColumna() < casillaI.getColumna() && casillaF.getFila() < casillaI.getFila()){
                    cI = cI - 1;
                    fI = fI - 1;
                }
                else if(casillaF.getColumna() > casillaI.getColumna() && casillaF.getFila() < casillaI.getFila()){
                    cI = cI + 1;
                    fI = fI - 1;
                }
               
                
                
                // condicion del movimiento de la torre
                
                
                else if (casillaF.getColumna() > casillaI.getColumna()){
                    cI = cI + 1;
                }
                else if(casillaF.getColumna() < casillaI.getColumna()){
                    cI = cI - 1;
                }
                else if(casillaF.getFila() < casillaI.getFila()){
                    fI = fI - 1;
                }
                else if(casillaF.getFila() > casillaI.getFila()){
                    fI = fI + 1;
                }
                
                   casillaC = tablero.getCasilla(fI,cI);
                if(cI != cF || fI != fF){
                    ocupada = casillaC.isOcupada();
                }
                while((cI != cF || fI != fF) && ocupada==false){
                    casillaC = tablero.getCasilla(fI,cI);
                    ocupada=casillaC.isOcupada();
                   
                    
                   if (casillaF.getColumna() > casillaI.getColumna() && casillaF.getFila() > casillaI.getFila()){
                    cI = cI + 1;
                    fI = fI + 1;
                }
                else if(casillaF.getColumna() < casillaI.getColumna() && casillaF.getFila() > casillaI.getFila()){
                    cI = cI - 1;
                    fI = fI + 1;
                }
                else if(casillaF.getColumna() < casillaI.getColumna() && casillaF.getFila() < casillaI.getFila()){
                    cI = cI - 1;
                    fI = fI - 1;
                }
                else if(casillaF.getColumna() > casillaI.getColumna() && casillaF.getFila() < casillaI.getFila()){
                    cI = cI + 1;
                    fI = fI - 1;
                }
               
                
                
                // condicion del movimiento de la torre
                
                
                else if (casillaF.getColumna() > casillaI.getColumna()){
                    cI = cI + 1;
                }
                else if(casillaF.getColumna() < casillaI.getColumna()){
                    cI = cI - 1;
                }
                else if(casillaF.getFila() < casillaI.getFila()){
                    fI = fI - 1;
                }
                else if(casillaF.getFila() > casillaI.getFila()){
                    fI = fI + 1;
                }   
                    
                 
                }
               
                  if(!casillaF.isOcupada()){//Que en la casilla final no haya nada    TIPO 1 (MOVIMIENTO NORMAL)
                    if(!ocupada){//Si no hay nada en la trayectoria
                        casillaI.setFichaNull();
                        super.asociarFichaTablero(this, casillaF);
                        efectivo=true;
                    }
                    else{
                      //  System.out.println("Hay una ficha en la trayectoria");
                        JOptionPane.showMessageDialog(null,"Hay una ficha en la trayectoria");
                    }
                }
                else{//Que en la casilla final haya una ficha                       TIPO 2 (COMER)
                   if(this.getColor() != casillaF.getFicha().getColor()){//Si la fichaI y la fichaF son de diferente color
                        if(!ocupada){
                              if(casillaF.getFicha() instanceof Rey){
                                JOptionPane.showMessageDialog(null, "Fin Del Juego");
                            }
                            this.comer(casillaI,casillaF);
                             efectivo=true;
                        }
                        else{
                           // System.out.println("Hay una ficha en trayectoria");
                            JOptionPane.showMessageDialog(null,"Hay una ficha en la trayectoria");
                        }
                   }
                   else{
                      // System.out.println("Ambas fichas son del mismo color");
                        JOptionPane.showMessageDialog(null,"Ambas fichas son del mismo color");
                   }
                }
                
                
                
                
                
                
                
            }
            
            else{
                System.out.println("De esa forma no se mueve el Rey");
            }


        return efectivo;
        
        }


    @Override
     public void draw(Graphics2D g, float x, float y) {
        GeneralPath polyline = new GeneralPath(GeneralPath.WIND_EVEN_ODD, 17);
        polyline.moveTo(x + 10, y + 45);
        polyline.lineTo(x + 40, y + 45);
        polyline.lineTo(x + 30, y + 40);
        polyline.lineTo(x + 30, y + 24);
        polyline.lineTo(x + 35, y + 15);
        
        polyline.lineTo(x + 27, y + 15);
        polyline.lineTo(x + 27, y + 11);
        polyline.lineTo(x + 31, y + 11);
        polyline.lineTo(x + 31, y + 8);
        polyline.lineTo(x + 27, y + 8);
        polyline.lineTo(x + 27, y + 5);
        polyline.lineTo(x + 23, y + 5);
        polyline.lineTo(x + 23, y + 8);
        polyline.lineTo(x + 19, y + 8);
        polyline.lineTo(x + 19, y + 11);
        polyline.lineTo(x + 23, y + 11);
        polyline.lineTo(x + 23, y + 15);
        polyline.lineTo(x + 15, y + 15);
        polyline.lineTo(x + 20, y + 24);
        polyline.lineTo(x + 20, y + 40);
        polyline.lineTo(x + 10, y + 45);
        
        g.setPaint(new GradientPaint(x, y,
                getColor() == Color.BLANCO ? java.awt.Color.CYAN : java.awt.Color.BLACK,
                x + 100, y + 50,
                java.awt.Color.WHITE));
        g.fill(polyline);

        g.setColor(java.awt.Color.BLACK);
        g.draw(polyline);
        
        g.setPaint(new GradientPaint(x, y,
                getColor() == Color.NEGRO ? java.awt.Color.BLACK : java.awt.Color.CYAN,
                x + 50, y + 50,
                java.awt.Color.WHITE));
        
        g.fill(new Ellipse2D.Float(x + 15, y + 26, 20, 5));
        g.fill(new Ellipse2D.Float(x + 20, y + 24, 10, 2));
        g.setPaint(java.awt.Color.BLACK);
        g.draw(new Ellipse2D.Float(x + 15, y + 26, 20, 5));
        g.draw(new Ellipse2D.Float(x + 20, y + 24, 10, 2));
    }


     @Override
    public void haceJaque(Tablero tablero) {
    }

      
    }
    


