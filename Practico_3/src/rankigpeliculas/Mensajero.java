package rankigpeliculas;

import java.util.ArrayList;

import registros.*;
import ventana.*;

public class Mensajero {

    private Panel_1 panel1;
    private Panel_2 panel2;
    private Panel_3 panel3;
    private Panel_4 panel4;
    private boolean procesado=false;
    private Total total;
    private Double porcentaje;
    private Double acumulado;

    private static Mensajero instancia= new  Mensajero();

    public Mensajero() {}

    public static Mensajero getInstance() {
		return instancia;
	}

    public void setPanel_1(Panel_1 panel) {
        this.panel1=panel;
    }

    public void setPanel_2(Panel_2 panel) {
        this.panel2=panel;
    }

    public void setPanel_3(Panel_3 panel) {
        this.panel3=panel;
    }
    public void setPanel_4(Panel_4 panel) {
        this.panel4=panel;
    }

    public void mensajeBarra(int porcentaje) {
        this.panel1.actualizarBarra(porcentaje);
    }
    
    public void mensajeTotal(Total total) {

        this.panel2.setTexto1(total.getUsuarios().toString());
        this.panel2.setTexto2(total.getMovies().toString());
        this.panel2.setTexto3(total.getVotos().toString());
    }

    public void mensajeLista(ArrayList<Pelicula> tabla) {
        this.panel3.procesarLista(tabla);
    }

    public void mensajeResumen(Total total) {

        this.total=total;
        this.procesado=true;
        int[] ranks={total.getRank0(),total.getRank1(),total.getRank2(),total.getRank3(),total.getRank4(),total.getRank5()};
        String ejeY[] = {"3000","6000","9000","12000","15000","18000","21000","24000","27000","30000","33000"};
        this.panel4.setAlturas(ranks,ejeY,20,3000,11);
    }

    public void mensajeRangos(Pelicula fila) {
        
        int[] ranks={fila.getRank0(),fila.getRank1(),fila.getRank2(),fila.getRank3(),fila.getRank4(),fila.getRank5()};
        String ejeY[] = {"25","50","75","100","125"};
        this.panel4.setAlturas(ranks,ejeY,40,25,5);
        
        
    }

    //Sirve para saber si los archivos ya fueron procesados
    public boolean getProcesado() {
        return this.procesado;
    }

    public Total getTotal() {
        return this.total;
    }

    
    public void setPorcentaje(double por) {
        this.porcentaje=por;
    }

    public void setAcumulado(double acu) {
        this.acumulado=acu;
    }

    /**
     * Devuelve un arreglo con el porcentaje que representará cada iteracion del llenado de las tablas, 
     * y el porcentaje acumulado de ProcesarArchivo
    */
    public double[] getPorcentaje() {
        double[] x={this.porcentaje, this.acumulado};
        return x;
    }
}