package ventana;

import javax.swing.*;

public class Ventana extends JFrame {

    private static final long serialVersionUID = 1L;

    private int sizeX = 800;
    private int sizeY=700;

    private Panel_1 panel1 = new Panel_1();
    private Panel_2 panel2 = new Panel_2();
    private Panel_3 panel3 = new Panel_3();
    private Panel_4 panel4 = new Panel_4();
    
    public Ventana(){

        configurarVentana();
        agregarComponentes();
    }

    public void configurarVentana(){

        this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
        this.setTitle("Tablero de Control de Películas");
        this.setSize(sizeX, sizeY);
        
        this.setVisible(true);
        this.setResizable(false);   //Para que no se pueda modificar el tamaño de la ventana
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  //Para que cuando se presione la X se cierre la ventana
    }

    public void agregarComponentes(){

       this.add(panel1);
       this.add(panel2);
       this.add(panel3);
       this.add(panel4);
    }
}