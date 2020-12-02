package ventana;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import rankigpeliculas.*;

public class Panel_1 extends JPanel implements ActionListener {

    private static final long serialVersionUID = 1L;

    private JButton boton= new JButton(" Procesar Datos ");
    private JProgressBar barra= new JProgressBar(0,100);
    private GridBagConstraints c = new GridBagConstraints();

    public Panel_1() {
    
        this.setLayout(new GridBagLayout());
        this.setSize(new Dimension(800,100));
        barra.setValue(0);
        barra.setStringPainted(true);
        boton.addActionListener(this);
        ajustarAlineacion();
        Mensajero.getInstance().setPanel_1(this);
    }

    private void ajustarAlineacion(){
        
        agregarVacio(0);

        agregarVacio(1);
        
        c.gridx = 2; // El área del elemento empieza en la columna 2.
        c.gridy = 0; // El área del elemento empieza en la fila 0
        c.gridwidth = 1; // El área del elemento ocupa 1 columna.
        c.gridheight = 1; // El área del elemento ocupa 1 fila.
        this.add(boton, c);

        agregarVacio(3);

        c.gridx = 4;
        c.gridy = 0;
        c.gridwidth = 1;
        c.gridheight = 1;
        this.add(barra, c);
    }

    private void agregarVacio(int col) {

        JLabel vacio = new JLabel("                                       ");
        c.gridx = col;
        c.gridy = 0;
        c.gridwidth = 1;
        c.gridheight = 1;
        this.add(vacio, c);
    }

    public void actionPerformed (ActionEvent evento) {

        if (evento.getSource()==this.boton) {
            //Crea un "Hilo" para procesar el archivo en paralelo
            new Thread(new SimpleTask()).start();
            this.boton.setEnabled(false);;
        }
    }

    public void actualizarBarra(int por) {
        this.barra.setValue(por);
    }

    public class SimpleTask implements Runnable {

        @Override
        public void run() {
            new ProcesoArchivos();
        }
    }
}