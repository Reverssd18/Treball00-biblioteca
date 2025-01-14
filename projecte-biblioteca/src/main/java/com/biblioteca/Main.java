package com.biblioteca;

import java.io.File;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

import org.json.JSONArray;
import org.json.JSONObject;


public class Main {
    static Scanner scanner = new Scanner(System.in);

    public static void menuPrincipal() {
        while (true) {
            System.out.println("\n---Gestió de biblioteca---");
            System.out.print("1. Llibres\n2. Usuaris\n3. Préstecs\n0. Sortir\nEscull una opció:  ");
            String opc = scanner.nextLine().toLowerCase();
            switch (opc) {
                case "0":
                case "sortir": {return; }
                case "1":
                case "llibres": menuLlibres();
                case "2":
                case "usuaris": menuUsuaris();
                case "3":
                case "prestecs": 
                case "préstecs": menuPrestecs();
                default: error();
            }
        }
    }

    public static void menuLlibres() {
        while (true) {
            System.out.println("\n---Gestió de llibres---");
            System.out.print("1. Afegir llibre\n2. Modificar llibre\n3. Eliminar llibre\n4. Llistar llibre\n0. Tornar al menú principal\nEscull una opció:  ");
            String opc = scanner.nextLine().toLowerCase();
            switch (opc) {
                case "0":
                case "tornar": menuPrincipal();
                case "1":
                case "afegir": afegirLlibres();
                case "2":
                case "modificar": modificarLlibres();
                case "3":
                case "eliminar": eliminarLlibres(); 
                case "4":
                case "llistar": llistarLlibres();
                default: error();
            }
        }
    }

    public static void menuPrestecs() {
        while (true) {
            System.out.println("\n---Gestió de Préstecs---");
            System.out.print("1. Afegir préstec\n2. Modificar préstec\n3. Eliminar préstec\n4. Llistar préstec\n0. Tornar al menú principal\nEscull una opció:  ");
            String opc = scanner.nextLine().toLowerCase();
            switch (opc) {
                case "0":
                case "tornar": menuPrincipal();
                case "1":
                case "afegir": afegirPrestecs();
                case "2":
                case "modificar": modificarPrestecs();
                case "3":
                case "eliminar": eliminarPrestecs(); 
                case "4":
                case "llistar": llistarPrestecs();
                default: error();
            }
        }
    }

    public static void menuUsuaris() {
        while (true) {
            System.out.println("\n---Gestió de Usuaris---");
            System.out.print("1. Afegir usuaris\n2. Modificar usuaris\n3. Eliminar usuaris\n4. Llistar usuaris\n0. Tornar al menú principal\nEscull una opció:  ");
            String opc = scanner.nextLine().toLowerCase();
            switch (opc) {
                case "0":
                case "tornar": menuPrincipal();
                case "1":
                case "afegir": afegirUsuaris();
                case "2":
                case "modificar": modificarUsuaris();
                case "3":
                case "eliminar": eliminarUsuaris(); 
                case "4":
                case "llistar": llistarUsuaris();
                default: error();
            }
        }
    }

    public static void main(String[] args) {
        menuPrincipal();
    }

    // menu-functions
public static void afegirLlibres() {
        System.out.println("Afegir llibre");
        System.out.println("Introdueix l'ID del llibre: ");
        String id = scanner.nextLine();
        System.out.println("Introdueix el títol del llibre: ");
        String titol = scanner.nextLine();
        System.out.println("Introdueix l'autor del llibre: ");
        String autor = scanner.nextLine();
        System.out.println("Introdueix el genre del llibre: ");
        String genre = scanner.nextLine();

        // creamos un JSONObject con los datos del nuevo libro
        JSONObject nouLlibre = new JSONObject();
        nouLlibre.put("id", id);
        nouLlibre.put("titol", titol);
        nouLlibre.put("autor", autor);
        nouLlibre.put("genre", genre);


        // ruta del archivo json
        String ruta = "projecte-biblioteca/data/llibres.json";

        // leemos el archivo json y lo convertimos en un array
        JSONArray llibres = new JSONArray();

        // leemos el archivo json
        try {
            File file = new File(ruta);
            if (file.exists()) {
                String content = new String(Files.readAllBytes(Path.of(ruta)));
                llibres = new JSONArray(content);
            }

        } catch (Exception e) {
            System.out.println("Error al llegir el fitxer");
        }


        // añadimos el nuevo libro a la lista
        llibres.put(nouLlibre);

        // guardamos la lista actualizada en el archivo json
        try (FileWriter writer = new FileWriter(ruta)) { // usamos try with resources para cerrar el archivo
            writer.write(llibres.toString(4)); // usamos el 4 para que se vea bonito
        } catch (Exception e) {
            System.out.println("Error al escriure el fitxer");
        }

        System.out.println("Llibre afegit correctament");
    }
    public static void modificarLlibres() {
        System.out.println("Modificar llibre");
    }

    public static void eliminarLlibres() {
        System.out.println("Eliminar llibre");
    }

    public static void llistarLlibres() {
        System.out.println("Listar llibre");
    }

    public static void afegirPrestecs() {
        System.out.println("Afegir préstec");
    }

    public static void modificarPrestecs() {
        System.out.println("Modificar préstec");
    }

    public static void eliminarPrestecs() {
        System.out.println("Eliminar préstec");
    }

    public static void llistarPrestecs() {
        System.out.println("Listar préstecs");
    }

    public static void afegirUsuaris() {
        System.out.println("Afegir usuaris");
    }

    public static void modificarUsuaris() {
        System.out.println("Modificar usuaris");
    }

    public static void eliminarUsuaris() {
        System.out.println("Eliminar usuaris");
    }

    public static void llistarUsuaris() {
        System.out.println("Listar usuaris");
    }

    public static void error() {
        System.out.println("Opció no vàlida. Torna a provar.");
    }


}
