/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.utp.isc.pro4.ajedrez.modelo;

import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.geom.GeneralPath;
import excepciones.MovimientoNoValidoException;
import java.awt.geom.Ellipse2D;
import javax.swing.JOptionPane;
/**
 *
 * @author utp
 */ 
public class Torre extends Ficha { 

    public Torre(Color color) {
        super(color);
    }
    
    private boolean trayectoria(Tablero tablero,Casilla casillaI, Casilla casillaF)  {
            boolean ocupada = false;
            int cI,cF,fI,fF;
            cI = casillaI.getColumna() - 'A';//x Inicial
            fI = casillaI.getFila() - 1;//y Inicial
            cF = casillaF.getColumna() - 'A';//x Final 
            fF = casillaF.getFila() - 1 ;//y Final
            Casilla casillaC;
            casillaC = casillaI;
        
                if (casillaF.getColumna() > casillaI.getColumna()){
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
                     System.out.println("OCUPADA:" +  ocupada);
                   
                    
                    
                    if (casillaF.getColumna() > casillaI.getColumna()){
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
            
            
            
            return ocupada;
        }
    @Override
    public boolean mover(Tablero tablero,Casilla casillaI, Casilla casillaF) {
        boolean ocupada = false, efectivo = false;
        int cI,cF,fI,fF;
        cI = casillaI.getColumna() - 'A';//x Inicial
        fI = casillaI.getFila() - 1;//y Inicial
        cF = casillaF.getColumna() - 'A';//x Final 
        fF = casillaF.getFila() - 1 ;//y Final
        Casilla casillaC;
        casillaC = casillaI;
        if(fI==fF || cI==cF){
            ocupada = trayectoria(tablero, casillaI, casillaF);
            if(!ocupada){
                if(!casillaF.isOcupada()){//Movimiento normal
                    casillaI.setFichaNull();
                    super.asociarFichaTablero(this, casillaF);
                    efectivo = true;
                }
                else if(casillaI.getFicha().getColor() != casillaF.getFicha().getColor()){
                    this.comer(casillaI, casillaF);
                    efectivo = true;
                }
                else if(casillaI.getFicha().getColor() == casillaF.getFicha().getColor()){//Si la ficha inicial es del mismo color que la final no es valido
                    //System.out.println("Movimiento no valido porque ambas fichas son del mismo color.");
                }
            }
            else{//Movimiento no valido por elemento en la trayectoria
                //System.out.println("Movimiento no valido por ficha en trayectoria");
            }
        }
        else{
        //    System.out.println("Asi no se mueve la torre");
          JOptionPane.showMessageDialog(null,"si no se mueve la torre");
        }
        return efectivo;
    }
    @Override
    public void haceJaque(Tablero tablero){
        int cI, fI, cF, fF;
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
                }
            }
        }
        cF = rey.getCasilla().getColumna() - 'A';
        fF = rey.getCasilla().getFila() - 1;
        if(fI==fF || cI==cF){
            ocupada = trayectoria(tablero, this.getCasilla(), rey.getCasilla());
            if(!ocupada){
                this.setJaque(true);
            }
        }
    }    
              

    

   public void draw(Graphics2D g, float x, float y) {
        GeneralPath polyline = new GeneralPath(GeneralPath.WIND_EVEN_ODD, 17);
        polyline.moveTo(x + 15, y + 5);
        polyline.lineTo(x + 15, y + 15);
        polyline.lineTo(x + 20, y + 20);
        
        
        //Base
        polyline.lineTo(x + 18, y + 42);
        
        polyline.lineTo(x + 15, y + 42);
        polyline.lineTo(x + 15, y + 45);
        polyline.lineTo(x + 35, y + 45);
        polyline.lineTo(x + 35, y + 42);
        
        polyline.lineTo(x + 32, y + 42);
        
        
        //Superior
        polyline.lineTo(x + 30, y + 20);
        polyline.lineTo(x + 35, y + 15);
        polyline.lineTo(x + 35, y + 5);
        polyline.lineTo(x + 31, y + 5);
        polyline.lineTo(x + 31, y + 9);
        polyline.lineTo(x + 27, y + 9);
        polyline.lineTo(x + 27, y + 5);
        polyline.lineTo(x + 23, y + 5);
        polyline.lineTo(x + 23, y + 9);
        polyline.lineTo(x + 19, y + 9);
        polyline.lineTo(x + 19, y + 5);
        polyline.lineTo(x + 15, y + 5);
        
        

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
        
        g.fill(new Ellipse2D.Float(x + 18, y + 20, 14, 3));
        g.fill(new Ellipse2D.Float(x + 15, y + 40, 20, 3));
        g.setPaint(java.awt.Color.BLACK);
        g.draw(new Ellipse2D.Float(x + 18, y + 20, 14, 3));
        g.draw(new Ellipse2D.Float(x + 15, y + 40, 20, 3));
    }
}
