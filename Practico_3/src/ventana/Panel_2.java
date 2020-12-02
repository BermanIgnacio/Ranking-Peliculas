package ventana;

import java.awt.*;
import javax.swing.*;
import rankigpeliculas.Mensajero;


public class Panel_2 extends JPanel {

    private static final long serialVersionUID = 1L;

    private JPanel panel1;
    private JPanel panel2;
    private JPanel panel3;

    private JTextField text1;
    private JTextField text2;
    private JTextField text3;

    private JLabel label1= new JLabel("           Usuarios    ");
    private JLabel label2= new JLabel("           Peliculas   ");
    private JLabel label3= new JLabel("         Cant. de Votos");

    private GridBagConstraints c = new GridBagConstraints();

    public Panel_2() {

        this.setLayout(new GridBagLayout());
        this.setSize(new Dimension(800,100));
        configurarTextosyLabels();
        posicionarSubPaneles();
        Mensajero.getInstance().setPanel_2(this);
    }

    private void configurarTextosyLabels() {

        Font font = new Font("SansSerif", Font.BOLD,20);

        text1=cearJText(font);
        text2=cearJText(font);
        text3=cearJText(font);

        label1.setFont(font);
        label2.setFont(font);
        label3.setFont(font);
    
    }

    private JTextField cearJText(Font font) {

        JTextField text= new JTextField("????",6);
        text.setHorizontalAlignment(JTextField.CENTER);
        text.setEditable(false);
        text.setFont(font);
        return text;
    }

    private void posicionarSubPaneles() {

        panel1=subPanel(text1,label1);
        panel2=subPanel(text2,label2);
        panel3=subPanel(text3,label3);

        c.gridx = 0; // El área de texto empieza en la columna 0.
        c.gridy = 0; // El área de texto empieza en la fila 0
        c.gridwidth = 1; // El área de texto ocupa 1 columna.
        c.gridheight = 1; // El área de texto ocupa 1 fila.
        this.add(panel1, c);

        c.gridx = 1;
        c.gridy = 0;
        c.gridwidth = 1;
        c.gridheight = 1;
        this.add(panel2, c);

        c.gridx = 2;
        c.gridy = 0;
        c.gridwidth = 1;
        c.gridheight = 1;
        this.add(panel3, c);
    }

    private JPanel subPanel(JTextField text, JLabel label) {

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEtchedBorder());

        panel.add(text);
        panel.add(label);
        return panel;
    }

    public void setTexto1(String txt) {
        
        this.text1.setText(txt);
    }

    public void setTexto2(String txt) {
        
        this.text2.setText(txt);
    }

    public void setTexto3(String txt) {
        
        this.text3.setText(txt);
    }
}