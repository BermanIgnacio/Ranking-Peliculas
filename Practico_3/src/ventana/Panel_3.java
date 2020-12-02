package ventana;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;

import rankigpeliculas.Mensajero;
import registros.*;

public class Panel_3 extends JPanel implements ActionListener {

    private static final long serialVersionUID = 1L;

    private JPanel panelSelector = new JPanel();    
    private JPanel panelTabla = new JPanel();  
    private CardLayout cardLayout;

    private JLabel seleTi= new JLabel("  Cantidad de resultados a mostrar:  ");
    private String[] listSelec = {"5", "10", "20", "100", "1000", "TODOS"};
    private JComboBox<String> selector = new JComboBox<String>(listSelec);

    private JPanel panel5;
    private JPanel panel10;
    private JPanel panel20;
    private JPanel panel100;
    private JPanel panel1000;
    private JPanel panelTodos;

    private JTable tabla5;
    private JTable tabla10;
    private JTable tabla20;
    private JTable tabla100;
    private JTable tabla1000;
    private JTable tablaTodos;
    
    public Panel_3() {

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setSize(new Dimension(800,107));
        configurarPanelSelector();
        setTablas();
        setPaneles();
        configurarPanelTablas();
        this.add(panelSelector);
        this.add(panelTabla);
        selector.addActionListener(this);
        Mensajero.getInstance().setPanel_3(this);
    }

    private void configurarPanelSelector() {

        panelSelector.setLayout(new FlowLayout());
        panelSelector.add(seleTi);
        panelSelector.add(selector);
    }

    private void setTablas() {

        this.tabla5 = configurarTabla(5);
        this.tabla10 = configurarTabla(10);
        this.tabla20 = configurarTabla(20);
        this.tabla100 = configurarTabla(100);
        this.tabla1000 = configurarTabla(1000);
        this.tablaTodos = configurarTabla(1000);
    }

    private JTable configurarTabla(int filas) {

        JTable tabla = new JTable(setModelo(filas));
        Font f = new Font("SansSerif", Font.PLAIN, 15);
        tabla.setFont(f);
        tabla.getTableHeader().setFont(f);
        tabla.setAutoCreateRowSorter(true);
        tabla.getTableHeader().setReorderingAllowed(false);
        return tabla;
    }

    private DefaultTableModel setModelo(int filas) {
        
        DefaultTableModel modelo = new DefaultTableModel() {

            private static final long serialVersionUID = 1L;
            //Para que no se pueda editar el contenido de la tabla
            @Override
            public boolean isCellEditable(int row, int column) {
               return false;
            }

            //Es para cuando se quiere ordenar la tabal por viewrers o ranking. 
            //Porque si no estubiera las columnas se ordenarian como strings en vez de numeros
            @Override
            public Class<?> getColumnClass(int column) {
                
                switch (column) {
                    case 0: return String.class;
                    case 1: return Integer.class;
                    case 2: return Double.class;
                    default: return String.class;
        }}}; 
        modelo.addColumn("Nombre de Pelicula");
        modelo.addColumn("# Viewers");
        modelo.addColumn("# Ranking");
        modelo.setNumRows(filas);
        return modelo;
    }

    private void setPaneles() {

        this.panel5 = configurarPanel(tabla5);
        this.panel10 = configurarPanel(tabla10);
        this.panel20 = configurarPanel(tabla20);
        this.panel100 = configurarPanel(tabla100);
        this.panel1000 = configurarPanel(tabla1000);
        this.panelTodos = configurarPanel(tablaTodos);
    }

    private JPanel configurarPanel(JTable tablaX) {
        
        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(800,75));
        JScrollPane scrollPane = new JScrollPane(tablaX);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(scrollPane);
        return panel;
    }

    private void configurarPanelTablas() {

        cardLayout = new CardLayout();
        panelTabla.setLayout(cardLayout);

        panelTabla.add(panel5, "tabla5");
        panelTabla.add(panel10, "tabla10");
        panelTabla.add(panel20, "tabla20");
        panelTabla.add(panel100, "tabla100");
        panelTabla.add(panel1000, "tabla1000");
        panelTabla.add(panelTodos, "tablaTodos");
    }
    
    public void actionPerformed (ActionEvent evento) {
        
        if (evento.getSource()==this.selector) {
            //Si los archivos ya fueron procesados, entonces si se cambia de panel vuelva a mostrar el grafico del resumen. Si no se perderia
            if(Mensajero.getInstance().getProcesado())
                Mensajero.getInstance().mensajeResumen(Mensajero.getInstance().getTotal());
            switch (this.selector.getSelectedIndex()) {
                case 0: this.cardLayout.show(this.panelTabla, "tabla5"); break;
                case 1: this.cardLayout.show(this.panelTabla, "tabla10"); break;
                case 2: this.cardLayout.show(this.panelTabla, "tabla20"); break;
                case 3: this.cardLayout.show(this.panelTabla, "tabla100"); break;
                case 4: this.cardLayout.show(this.panelTabla, "tabla1000"); break;
                case 5: this.cardLayout.show(this.panelTabla, "tablaTodos"); break;
                default: break;
        }}      
    }

    public void procesarLista(ArrayList<Pelicula> filas) {

        double[] por=Mensajero.getInstance().getPorcentaje();
        cargarTablas(tabla5,5,filas,por);
        cargarTablas(tabla10,10,filas,por);
        cargarTablas(tabla20,20,filas,por);
        cargarTablas(tabla100,100,filas,por);
        cargarTablas(tabla1000,1000,filas,por);
        if(filas.size() > 1000) {
            tablaTodos.setModel(setModelo(filas.size()));
        }
        cargarTablas(tablaTodos,filas.size(),filas,por);
        Mensajero.getInstance().mensajeBarra(100);
    }

    private void cargarTablas(JTable tablaI, int filaI, ArrayList<Pelicula> filas, double[] por) {
         
        tablaI.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent event) {
                //cuando se selecciona una celda se generan 2 eventos, esta condición previene que se redibuje 2 veces
                if(event.getValueIsAdjusting())
                    return;
                //Reordena el modelo de la tabla si se llegó a cambiar el orden
                //y le envia al Panel_4 los datos de la celada seleccionada
                int selection = tablaI.getSelectedRow();
                selection = tablaI.convertRowIndexToModel(selection);
                Mensajero.getInstance().mensajeRangos(filas.get(selection));
            }
        });
        //Ampliar primera columna
        tablaI.getColumnModel().getColumn(0).setPreferredWidth(300);
        int i,j;
        DefaultTableModel modelo;
        Pelicula t;   
        int porRedondeado=-1;
        modelo= (DefaultTableModel) tablaI.getModel();
        for (i = 0; i<filaI; i++) {
            por[1]+=por[0];
            if(((int) por[1])%1==0 && (int) por[1]!=porRedondeado) {    
                porRedondeado=(int) por[1];
                Mensajero.getInstance().mensajeBarra(porRedondeado);
            }
            t=filas.get(i);
            j=0;
            modelo.setValueAt(t.getTitulo(), i, j);
            j++;
            modelo.setValueAt(t.getViewers(), i, j);
            j++;
            modelo.setValueAt(t.getRanking(), i, j);
        }
    }
}