package rankigpeliculas;

import java.io.*;
import java.util.*;
import java.util.concurrent.TimeUnit;

import registros.*;

public class ProcesoArchivos {

    private Total total = new Total();
    private ArrayList<Pelicula> pelis = new ArrayList<Pelicula>();
    private String[] campos;
    private double por = 0;
    private int porRedondeado = -1;
    private double f1, f2;

    public ProcesoArchivos() {

        calcularPorcentaje();
        procesarMovies();
        procesarRatings();
        promediarRanking();
        Collections.sort(pelis, Collections.reverseOrder());
        Mensajero.getInstance().setAcumulado(por);
        Mensajero.getInstance().mensajeTotal(total);
        Mensajero.getInstance().mensajeLista(pelis);
        Mensajero.getInstance().mensajeResumen(total);
    }

    private void calcularPorcentaje() {

        long progres = 0;
        BufferedReader bufferLectura = null;
        try {
            InputStream mov = getClass().getResourceAsStream("/movies.csv");
            bufferLectura = new BufferedReader(new InputStreamReader(mov));
            f1 = bufferLectura.lines().count();
            progres += (f1 * 2); // Lo multiplico porque utilizo f1 2 veces (en la lectura y promediar)
            bufferLectura.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        bufferLectura = null;
        try {
            InputStream rat = getClass().getResourceAsStream("/ratings.csv");
            bufferLectura = new BufferedReader(new InputStreamReader(rat));
            f2 = bufferLectura.lines().count();
            progres += f2;
            bufferLectura.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        double f3 = 1135 + f1;// 1135 (son la sumatoria de todas las filas de cada tabla del Panel_3) + f1
                              // (son la cantidad de filas que tendrá tablaTodos)
        progres += f3;
        f1 = ((f1 * 100) / progres) / f1;
        f2 = ((f2 * 100) / progres) / f2;
        f3 = ((f3 * 100) / progres) / f3;
        Mensajero.getInstance().setPorcentaje(f3);
    }

    private void procesarMovies() {

        BufferedReader bufferLectura = null;
        String linea;
        Pelicula fila;
        String str[];
        try {
            // Abrir el .csv en buffer de lectura
            InputStream mov = getClass().getResourceAsStream("/movies.csv");
            bufferLectura = new BufferedReader(new InputStreamReader(mov));
            // Leer una linea del archivo
            linea = bufferLectura.readLine();
            avancePorcentaje(f1);
            // Leo otra linea para saltar el encavezado
            linea = bufferLectura.readLine();
            while (linea != null) {
                avancePorcentaje(f1);
                total.aumentarPeliculas();
                // Separacion especial a las lineas que comienzan con "
                if (linea.contains("\"")) {
                    str = linea.split("\"", -2);
                    campos[0] = str[1].substring(0, str[1].length() - 1);
                    if(campos[0].equals("7789")) //Tratamiento especial para linea 5021 del archivo
                        campos[1]=str[3]+"\""+str[7];
                    else   
                        campos[1] = str[3];
                }
                // Sepapar la linea leída con el separador ","
                else
                    campos = linea.split(",");
                // Guarda el id y titulo de las Peliculas
                fila = crearPelicula(campos);
                // Agrega "fila" a un ArrayList
                pelis.add(fila);
                // Volver a leer otra línea del fichero
                linea = bufferLectura.readLine();
            }
            try {
                bufferLectura.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void procesarRatings() {

        String linea;
        BufferedReader bufferLectura = null;
        int[] uIdAnt = { 0 };

        try {
            InputStream rat = getClass().getResourceAsStream("/ratings.csv");
            bufferLectura = new BufferedReader(new InputStreamReader(rat));
            linea = bufferLectura.readLine();
            avancePorcentaje(f2);
            linea = bufferLectura.readLine();
            while (linea != null) {
                avancePorcentaje(f2);
                campos = linea.split(",");
                procesarLinea(campos, uIdAnt);
                linea = bufferLectura.readLine();
            }
            try {
                bufferLectura.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void procesarLinea(String[] campos, int[] uIdAnt) {

        double rank = Double.parseDouble(campos[2]);
        // Aumento la cantidad de peliculas del repectivo ranking
        switch ((int) rank) {
            case 0: { total.aumentarRank0(); break; }
            case 1: { total.aumentarRank1(); break; }
            case 2: { total.aumentarRank2(); break; }
            case 3: { total.aumentarRank3(); break; }
            case 4: { total.aumentarRank4(); break; }
            case 5: { total.aumentarRank5(); break; }
        }
        // Condicion para no contar ususarios repetidos del archivo (ya que es en orden)
        if (Integer.parseInt(campos[0]) != uIdAnt[0]) {
            uIdAnt[0] = Integer.parseInt(campos[0]);
            total.aumentarUsuarios();
        }
        // Busco en el arreglo hasta encontrar que el id leido sea igual al id de la
        // pelicula del arreglo.
        int i = busqueda(0, pelis.size(), Integer.parseInt(campos[1]));
        switch ((int) rank) {
            case 0: { pelis.get(i).aumentarRank0(); break; }
            case 1: { pelis.get(i).aumentarRank1(); break; }
            case 2: { pelis.get(i).aumentarRank2(); break; }
            case 3: { pelis.get(i).aumentarRank3(); break; }
            case 4: { pelis.get(i).aumentarRank4(); break; }
            case 5: { pelis.get(i).aumentarRank5(); break; }
        }
        pelis.get(i).aumentarViewers(); // Aumento los viewers para esa película
        pelis.get(i).aumentarRanking(rank); // Aumento el ranking para esa película
        total.aumentarVotos(); // Aumento la cantidad total de votos generales
    }

    private int busqueda(int ini, int fin, int id) {
        if (fin >= ini) {
            int med = ini + (fin - ini) / 2;

            if (pelis.get(med).getMovieId() == id)
                return med;

            if (pelis.get(med).getMovieId() > id)
                return busqueda(ini, med - 1, id);

            return busqueda(med + 1, fin, id);
        }
        return -1;
    }

    private void promediarRanking() {

        for (Pelicula p : pelis) {
            if (p.getViewers() != 0)
                p.setRanking(p.promedioRanking());
            avancePorcentaje(f1);
        }
    }

    private Pelicula crearPelicula(String[] metadata) {

        int id = Integer.parseInt(metadata[0]);
        String nombre = metadata[1];
        return new Pelicula(id, nombre);
    }

    private void avancePorcentaje(double f) {

        this.por += f;
        // Condición para que no envíe porcentajes con decimal y no reenvíe el porcentaje
        if (((int) por) % 1 == 0 && (int) por != porRedondeado) {
            try {
                TimeUnit.MILLISECONDS.sleep(50);
            } catch (InterruptedException e){}
            porRedondeado = (int) por;
            Mensajero.getInstance().mensajeBarra(porRedondeado);
        }
    }
}