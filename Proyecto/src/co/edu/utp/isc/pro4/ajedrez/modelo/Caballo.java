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
import java.awt.geom.Rectangle2D;
import javax.swing.JOptionPane;

/**
 *
 * @author utp
 */
public class Caballo extends Ficha {

    public Caballo(Color color) {
        super(color);
    }

    @Override
    public boolean mover(Tablero tablero,Casilla casillaI, Casilla casillaF) {
        boolean ocupada = false;
            int cI,cF,fI,fF;
            boolean efectivo = false;
            cI = casillaI.getColumna() - 'A';//x Inicial
            fI = casillaI.getFila() - 1;//y Inicial
            cF = casillaF.getColumna() - 'A';//x Final 
            fF = casillaF.getFila() - 1 ;//y Final
    
     if((fI-fF)*(fI-fF) +(cI-cF)*(cI-cF) == 5){

                if(!casillaF.isOcupada()){

                casillaI.setFichaNull();
                super.asociarFichaTablero(this, casillaF);
                efectivo = true;
                }
                 else {

                if((this.getColor() != casillaF.getFicha().getColor())){
                   if(casillaF.getFicha() instanceof Rey){
                                JOptionPane.showMessageDialog(null, "Fin Del Juego");
                }
                   this.comer(casillaI,casillaF);
                    efectivo = true;
                }
                else {
                    JOptionPane.showMessageDialog(null, "Fichas del mismo color ");
                }
                }


            }
     
     else{
         JOptionPane.showMessageDialog(null, "Asi no se mueve el caballo");
         
     }
     return efectivo;
            }

    public void haceJaque(Tablero tablero){
            int cI, fI, cF, fF;
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
            if((fI-fF)*(fI-fF) + (cI-cF)*(cI-cF) == 5){
                this.setJaque(true);
            }
    }

    

    @Override
   public void draw(Graphics2D g, float x, float y) {
        
        GeneralPath polyline = new GeneralPath(GeneralPath.WIND_EVEN_ODD, 17);
        polyline.moveTo(x + 10, y + 45);
        polyline.lineTo(x + 40, y + 45);
        polyline.lineTo(x + 40, y + 38);
        polyline.lineTo(x + 30, y + 30);
        polyline.lineTo(x + 37, y + 30);
        //Cabeza
        polyline.lineTo(x + 24, y + 20);
        polyline.lineTo(x + 37, y + 20);
        polyline.lineTo(x + 37, y + 15);
        polyline.lineTo(x + 27, y + 5);
        //orejas
        polyline.lineTo(x + 22, y + 5);
        polyline.lineTo(x + 22, y);
        
        //crin
        polyline.lineTo(x + 12, y + 15);
        polyline.lineTo(x + 12, y + 30);
        
        polyline.lineTo(x + 20, y + 30);
        polyline.lineTo(x + 10, y + 38);
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
        
        g.fill(new Ellipse2D.Float(x + 10, y + 33, 30, 5));
        g.setPaint(java.awt.Color.BLACK);
        g.draw(new Ellipse2D.Float(x + 10, y + 33, 30, 5));
    }
    public void comer() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
