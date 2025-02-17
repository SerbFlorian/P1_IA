package utils;

import models.Mapa;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class LectorMapa {

    public static Mapa leerMapaDesdeArchivo(String rutaArchivo) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(rutaArchivo));

        // Leer dimensiones
        String[] dimensiones = br.readLine().split(" ");
        int filas = Integer.parseInt(dimensiones[0]);
        int columnas = Integer.parseInt(dimensiones[1]);

        // Leer coordenadas de inicio
        String[] inicio = br.readLine().split(" ");
        int inicioX = Integer.parseInt(inicio[0]);
        int inicioY = Integer.parseInt(inicio[1]);

        // Leer coordenadas de destino
        String[] destino = br.readLine().split(" ");
        int destinoX = Integer.parseInt(destino[0]);
        int destinoY = Integer.parseInt(destino[1]);

        // Leer la matriz del mapa
        int[][] mapa = new int[filas][columnas];
        for (int i = 0; i < filas; i++) {
            String[] valores = br.readLine().split(" ");
            for (int j = 0; j < columnas; j++) {
                if (valores[j].equals("X")) {
                    mapa[i][j] = -1; // -1 representa un precipicio
                } else {
                    mapa[i][j] = Integer.parseInt(valores[j]);
                }
            }
        }

        br.close();
        return new Mapa(filas, columnas, inicioX, inicioY, destinoX, destinoY, mapa);
    }
}
