/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.utp.isc.pro4.ajedrez.modelo;

import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import javax.swing.JOptionPane;
import excepciones.MovimientoNoValidoException;
import java.awt.geom.GeneralPath;
/**
 *
 * @author utp
 */
public class Peon extends Ficha {

    public Peon(Color color) {
        super(color);
    }

    @Override
    public boolean mover(Tablero tablero,Casilla casillaI, Casilla casillaF){
        boolean ocupada = false, efectivo = false;
            
            int cI,cF,fI,fF, restaA,fIAUX, restaB;
            cI = casillaI.getColumna() - 'A';//x Inicial
            fI = casillaI.getFila() - 1;//y Inicial
            cF = casillaF.getColumna() - 'A';//x Final 
            fF = casillaF.getFila() - 1 ;//y Final
             fIAUX = fI;//Para validar la posicion inicial del peon sin que se pierda
            restaA = fI - fF;
            restaB = cF-cI;
            Casilla casillaC;   

            if((Math.abs(restaA) == 2 || Math.abs(restaA) == 1)){// Condicion general de movimiento del peon
                 if(restaA == 2 && casillaI.getFicha().getColor() == Color.NEGRO){//Caso para mover 2 casillas BLANCO
                    fI = fI + 1;
                    casillaC = tablero.getCasilla(fI,cI);
                    if(fI != fF || cI != cF){
                        ocupada = casillaC.isOcupada();
                    }
                }

                  else if(restaA == 2 && casillaI.getFicha().getColor() == Color.BLANCO){//Caso para mover 2 casillas NEGRO
                    fI = fI - 1;

                     casillaC = tablero.getCasilla(fI,cI);
                    if(fI != fF || cI != cF){
                        ocupada = casillaC.isOcupada();
                    }
                }
               
                
                System.out.println(ocupada);
                System.out.println("restaA: "+ restaA);
                if(!ocupada){
                    if(!casillaF.isOcupada()){//Movimiento normal
                   if(this.getColor() == Color.BLANCO){
                              if(restaA == 1 || (restaA == 2 && fIAUX == 6)){
                                casillaI.setFichaNull();
                                super.asociarFichaTablero(this, casillaF);
                                efectivo = true;
                            }
                              else if(fIAUX != 6){
                               JOptionPane.showMessageDialog(null, "Este movimiento no es valido en esta fila");
                            }
                              else{
                                JOptionPane.showMessageDialog(null,"asi no se mueve el peon");
                                
                              }
                        }
                       else if(this.getColor() == Color.NEGRO){
                             if((restaA == -1 && cF == cI) || (restaA == -2 && fIAUX == 1)){
                                casillaI.setFichaNull();
                                super.asociarFichaTablero(this, casillaF);
                                efectivo = true;
                            }
                             else if (fIAUX != 1){
                                JOptionPane.showMessageDialog(null,"Este movimiento no es valido en esta fila");
                            }  
                                     else{
                                      JOptionPane.showMessageDialog(null,"asi no se mueve el peon");
                                     }
                        }
                    }
                     else if(casillaF.isOcupada()){
                        if(casillaI.getFicha().getColor() != casillaF.getFicha().getColor()){
                            if(Math.abs(restaB) == 1){
                                if(casillaI.getFicha().getColor() == Color.NEGRO && restaA == -1){
                                
                                    this.comer(casillaI, casillaF);
                                    efectivo=true;
                                }    
                            
                           else if(casillaI.getFicha().getColor() == Color.BLANCO && restaA == 1){                                     
                                      this.comer(casillaI, casillaF);
                                      efectivo = true;
                        }
                    }               
            }
                 else{
                    JOptionPane.showMessageDialog(null,"De esa forma no se mueve el peon");
        }
    }
                }
            }
            else if(ocupada){//Movimiento no valido por elemento en la trayectoria
                   //  throw new MovimientoNoValidoException("Movimiento no valido por ficha en trayectoria");
                      JOptionPane.showMessageDialog(null,"Movimiento no valido por ficha en trayectoria");
            }
    
            else{
             //  throw new MovimientoNoValidoException("De esa forma no se mueve el peon");
                JOptionPane.showMessageDialog(null,"De esa forma no se mueve el peon");

            }
    return efectivo;

    }
    
    
      //TODO: Mover como peon
         
  

    @Override
    public void draw(Graphics2D g, float x, float y) {
        // 50x50 dibujar la ficha
        
        GeneralPath polyline = new GeneralPath(GeneralPath.WIND_EVEN_ODD, 17);
        polyline.moveTo(x + 15, y + 45);
        polyline.lineTo(x + 35, y + 45);
        polyline.lineTo(x + 30, y + 40);
        polyline.lineTo(x + 30, y + 31);      
        polyline.lineTo(x + 20, y + 31);
        polyline.lineTo(x + 20, y + 40);
        polyline.lineTo(x + 15, y + 45);
        
        
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
        
        g.fill(new Ellipse2D.Float(x + 17, y + 10, 16, 16));
        g.fill(new Ellipse2D.Float(x + 17, y + 26, 16, 5));
        g.setPaint(java.awt.Color.BLACK);
        g.draw(new Ellipse2D.Float(x + 17, y + 10, 16, 16));
        g.draw(new Ellipse2D.Float(x + 17, y + 26, 16, 5));
        
    }
     
    
    
    @Override
    public void haceJaque(Tablero tablero){
        int cI, fI, cF, fF, restaA, restaB;
        cI = this.getCasilla().getColumna() - 'A';
        fI = this.getCasilla().getFila() - 1;
        Casilla casillaC;
        Ficha rey;
        rey = this;
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                casillaC = tablero.getCasilla(i,j);
                if(casillaC.getFicha() instanceof Rey && casillaC.getFicha().getColor() != this.getColor()){
                    rey = casillaC.getFicha();
                }
            }
        }
        cF = rey.getCasilla().getColumna() - 'A';
        fF = rey.getCasilla().getFila() - 1;
        restaA = fI - fF;
        restaB = cF - cI;
        if(Math.abs(restaB) == 1){
            if(this.getColor() == Color.NEGRO && restaA == -1){
                setJaque(true);
            } 
            else if(this.getColor() == Color.BLANCO && restaA == 1){
                setJaque(true);
            }    
        }
     }
   }
