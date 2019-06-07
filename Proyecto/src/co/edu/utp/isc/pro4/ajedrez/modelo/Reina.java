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
import java.awt.geom.Path2D;
import java.awt.geom.Rectangle2D;
import javax.swing.JOptionPane;

/**
 * 
 * @author utp
 */
public class Reina extends Ficha {

    public Reina(Color color) {
        super(color);
    }
    
    private boolean trayectoria(Tablero tablero, Casilla casillaI, Casilla casillaF){
        boolean ocupada = false;
        int cI,cF,fI,fF;
        cI = casillaI.getColumna() - 'A';//x Inicial
        fI = casillaI.getFila() - 1;//y Inicial
        cF = casillaF.getColumna() - 'A';//x Final 
        fF = casillaF.getFila() - 1 ;//y Final
        Casilla casillaC;
        casillaC = casillaI;
        //Condiciones diagonales
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
        //Condiciones rectas
        else if(casillaF.getColumna() > casillaI.getColumna() && casillaF.getFila() == casillaI.getFila()){//DERECHA
            cI = cI + 1;
        }
        else if(casillaF.getColumna() < casillaI.getColumna() && casillaF.getFila() == casillaI.getFila()){//IZQUIERDA
            cI = cI - 1;
        }
        else if(casillaF.getFila() < casillaI.getFila() && casillaF.getColumna() == casillaI.getColumna()){//ABAJO 
            fI = fI - 1;
        }
        else if(casillaF.getFila() > casillaI.getFila() && casillaF.getColumna() == casillaI.getColumna()){//ARRIBA
            fI = fI + 1;
        }
        casillaC = tablero.getCasilla(fI,cI);
        if(cI != cF || fI != fF){
            ocupada = casillaC.isOcupada();
        }
        while((cI != cF || fI != fF) && ocupada==false){
            casillaC = tablero.getCasilla(fI,cI);
            ocupada = casillaC.isOcupada();
            if (casillaF.getColumna() > casillaI.getColumna() && casillaF.getFila() > casillaI.getFila()){
                cI = cI + 1;
                fI = fI + 1;
            }
            //condiciones diagonales
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
            //Condiciones rectas
            else if(casillaF.getColumna() > casillaI.getColumna() && casillaF.getFila() == casillaI.getFila()){//DERECHA
            cI = cI + 1;
            }
            else if(casillaF.getColumna() < casillaI.getColumna() && casillaF.getFila() == casillaI.getFila()){//IZQUIERDA
                cI = cI - 1;
            }
            else if(casillaF.getFila() < casillaI.getFila() && casillaF.getColumna() == casillaI.getColumna()){//ABAJO 
                fI = fI - 1;
            }
            else if(casillaF.getFila() > casillaI.getFila() && casillaF.getColumna() == casillaI.getColumna()){//ARRIBA
                fI = fI + 1;
            }
        }
            return ocupada;
    }
    

     public boolean mover(Tablero tablero,Casilla casillaI, Casilla casillaF) {
            boolean ocupada = false, efectivo = false;
        int cI,cF,fI,fF, restaA, restaB;
        cI = casillaI.getColumna() - 'A';//x Inicial
        fI = casillaI.getFila() - 1;//y Inicial
        cF = casillaF.getColumna() - 'A';//x Final 
        fF = casillaF.getFila() - 1 ;//y Final
        restaA = fI - fF;
        restaB = cI - cF;
        if(Math.abs(restaA) == Math.abs(restaB) || (fI==fF || cI==cF)){
            ocupada = trayectoria(tablero, casillaI, casillaF);
            if(!ocupada){
                if(!casillaF.isOcupada()){
                        casillaI.setFichaNull();
                        super.asociarFichaTablero(this, casillaF);
                        efectivo = true;
                    }
                    else{//Que en la casilla final haya una ficha                       TIPO 2 (COMER)
                    if(this.getColor() != casillaF.getFicha().getColor()){//Si la fichaI y la fichaF son de diferente color
                        this.comer(casillaI,casillaF);
                        efectivo = true;   
                    }
                    else{
                        
                        // System.out.println("Ambas fichas son del mismo color");
                    }
                }
            
            }


            else{
               
               // Hay una ficha en la trayectoria
            }
        }
  
             else{
           // System.out.println("De esa forma no se mueve la reina");
           JOptionPane.showMessageDialog(null,"De esa forma no se mueve la reina");
        }
        return efectivo;
    }
    public void haceJaque(Tablero tablero){
        int cI, fI, cF, fF, restaA, restaB;
        cI = this.getCasilla().getColumna() - 'A';
        fI = this.getCasilla().getFila() - 1;
        Casilla casillaC;
        Ficha rey;
        rey = this;
        boolean ocupada;
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                casillaC = tablero.getCasilla(i,j);
                if(casillaC.getFicha() instanceof Rey && casillaC.getFicha().getColor() != this.getColor()){
                    rey = casillaC.getFicha();
                    System.out.println(" REY:" + rey.getCasilla());
                }
            }
        }
        cF = rey.getCasilla().getColumna() - 'A';
        fF = rey.getCasilla().getFila() - 1;
        restaA = fI - fF;
        restaB = cI - cF;

        if(Math.abs(restaA) == Math.abs(restaB) || (fI==fF || cI==cF)){
            ocupada = trayectoria(tablero, this.getCasilla(), rey.getCasilla());
            if(!ocupada){
                this.setJaque(true);
            }
        }
}
  

    @Override
    public void draw(Graphics2D g, float x, float y) {
        GeneralPath polyline = new GeneralPath(GeneralPath.WIND_EVEN_ODD, 17);
        polyline.moveTo(x + 10, y + 45);
        polyline.lineTo(x + 40, y + 45);
        polyline.lineTo(x + 30, y + 40);
        polyline.lineTo(x + 30, y + 18);
        polyline.lineTo(x + 35, y + 9);
        polyline.lineTo(x + 15, y + 9);
        polyline.lineTo(x + 20, y + 18);
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
        
        g.fill(new Ellipse2D.Float(x + 21, y + 4, 8, 5));
        g.fill(new Ellipse2D.Float(x + 15, y + 20, 20, 5));
        g.fill(new Ellipse2D.Float(x + 20, y + 18, 10, 2));
        g.setPaint(java.awt.Color.BLACK);
        g.draw(new Ellipse2D.Float(x + 21, y + 4, 8, 5));
        g.draw(new Ellipse2D.Float(x + 15, y + 20, 20, 5));
        g.draw(new Ellipse2D.Float(x + 20, y + 18, 10, 2));
    }

}
