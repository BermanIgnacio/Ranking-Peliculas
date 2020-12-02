package ventana;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;

import rankigpeliculas.Mensajero;

public class Panel_4 extends JPanel {

    private static final long serialVersionUID = 1L;

    private String ejeY[]= {"3000","6000","9000","12000","15000","18000","21000","24000","27000","30000","33000"};
    private String ejeX[] = {"0","1","2","3","4","5"};
    private int alto0 = 0;
    private int alto1 = 0;
    private int alto2 = 0;
    private int alto3 = 0;
    private int alto4 = 0;
    private int alto5 = 0;

    private int nums=11;
    private int esp=20;

    public Panel_4() {

        String title = "HISTOGRAMA";
        TitledBorder border = BorderFactory.createTitledBorder(title);
        border.setTitleJustification(TitledBorder.CENTER);
        border.setTitleFont(new Font("SansSerif", Font.BOLD, 20));
        this.setBorder(border);
        this.setLayout(new BorderLayout());
        this.add(new PanelDibujo());
        Mensajero.getInstance().setPanel_4(this);
    }

    public class PanelDibujo extends JPanel {

        private static final long serialVersionUID = 1L;

        public PanelDibujo() {

            setLayout(new BorderLayout());
            this.setPreferredSize(new Dimension(400,300));
            this.setBackground(Color.WHITE);
            repaint();
            this.setVisible(true);
        }

        @Override
        protected void paintComponent(Graphics g){

            super.paintComponent(g);
            Graphics2D graf = (Graphics2D) g;
            Font fontT = new Font("Serif", Font.BOLD,12);
            Font fontY = new Font("SansSerif", Font.BOLD,16);
            Font fontX = new Font("SansSerif", Font.BOLD,20);

            graf.setColor(Color.BLACK); //Color de linea y numeros
            graf.setStroke(new BasicStroke(3)); //Cambia el grosor de la linea
            graf.drawLine(180, 280, 625, 280); //Linea Eje X

            graf.drawLine(270, 320, 270, 30); //Linea Eje Y
            graf.fillPolygon(new int[] {265, 270, 276}, new int[] {40, 24, 40}, 3); //Flecha del final eje Y
            
            graf.setFont(fontT);
            graf.drawString("Viewers", 220, 40);
            graf.drawString("Scrore", 600, 300);
            
            //Numeros eje X
            graf.setFont(fontX);
            for (int i=0, j=320; i <6; i++,j+=50) 
                graf.drawString(ejeX[i], j, 310);

            //Numeros eje Y
            graf.setFont(fontY);
            int x=215;
            for (int i=0, j=287-esp; i <nums; i++, j-=esp) 
                graf.drawString(ejeY[i], x, j);

            //Lineas de escala Y
            graf.setStroke(new BasicStroke(2));
            for (int i=0, j=280-esp; i <nums; i++, j-=esp)
                graf.drawLine(265, j, 275, j);
            
            //Lineas de escala X
            for (int i=0, j=326; i < 6; i++, j+=50)
                graf.drawLine(j, 280, j, 290);

            graf.setColor(Color.CYAN);//Color del rectangulo
            graf.fillRect(310, 278-alto0, 30, alto0); 
            graf.fillRect(360, 278-alto1, 30, alto1);
            graf.fillRect(410, 278-alto2, 30, alto2);
            graf.fillRect(460, 278-alto3, 30, alto3);
            graf.fillRect(510, 278-alto4, 30, alto4);
            graf.fillRect(560, 278-alto5, 30, alto5);
            //Se le resta la altura porque se grafica de arriba para abajo.
            //De modo que si aumenta la altura la coordenada Y sube y no traspace el eje X.
        }
    }

    /**
     * Convierte los valores numericos a strings
     * Si los viewres pasan el plano los sobrescribo a su limite
     * Pongo los viewers en escala
     * Reemplazo las alturas con los valores ya escalados
     * Y llamo para que se re dibuje
     * @param viewerxRank Arreglo con los viewers que votaron ranking de 0..5.
     * @param ejeY Arreglo con los valores de las escalas
     * @param esp Distancia entre divisiones de la escala.
     * @param esc Valor de escala.
     * @param nums cantidad de divisiones de la escala.
     **/
    public void setAlturas(int[] viewerxRank, String[] ejeY, int esp, int esc, int nums){

        for (int i = 0; i < viewerxRank.length; i++) {
            if(viewerxRank[i]>(nums+1)*esc)
                viewerxRank[i]=(nums+1)*esc;
            viewerxRank[i]= viewerxRank[i]*esp/esc;
        }
        this.esp=esp; 
        this.nums=nums;
        this.ejeY=ejeY;
        this.alto0 = viewerxRank[0];
        this.alto1 = viewerxRank[1];
        this.alto2 = viewerxRank[2];
        this.alto3 = viewerxRank[3];
        this.alto4 = viewerxRank[4];
        this.alto5 = viewerxRank[5];
        repaint();
    }
}