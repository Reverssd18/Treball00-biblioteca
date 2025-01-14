package com.biblioteca;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Files;


public class Main {
    static Scanner scanner = new Scanner(System.in);
    public static Runnable menuPrincipal() {
       System.out.println("Gestió de biblioteca");
       System.out.print("1. Llibres\n2. Usuaris\n3. Préstecs\n0. Sortir\nEscull una opció:  ");
       String opc = scanner.nextLine().toLowerCase();
       scanner.close();
       try {
           return switch (opc) {
               case "0", "sortir" -> () -> sortir();
               case "1", "llibres" -> () -> menuLlibres();
               case "2", "modificar" -> () -> sortir();
               case "3", "prestecs", "préstecs" -> () -> menuPrestecs();
               default -> () -> error();
           };
            
        } catch (Exception e) {
            System.out.println("Opció no vàlida. Torna a probar.");
            return menuPrincipal();
        }
    }

    public static Runnable menuLlibres() {
        System.out.println("Gestió de llibres");
        System.out.print("1. Afegir llibre\n2. Modificar llibre\n3. Eliminar llibre\n4. Listar llibre\n0. Tornar al menú principal\nEscull una opció:  ");
        String opc = scanner.nextLine().toLowerCase();
        scanner.close();
        try {
            return switch (opc) {
                case "0", "tornar" -> () -> tornar();
                case "1", "afegir" -> () -> afegirLlibres();
                case "2", "modificar" -> () -> modificarLibres();
                case "3", "eliminar" -> () -> eliminarLlibres();
                case "4", "listar" -> () -> llistarLlibres();
                default -> () -> error();
            };
        } catch (Exception e) {
            System.out.println("Opció no vàlida. Torna a probar.");
            return menuLlibres();
        }
    }

    public static Runnable menuPrestecs() {
        System.out.println("Gestió de Prestecs");
        System.out.print("1. Afegir préstec\n2. Modificar préstec\n3. Eliminar préstec\n4. Listar préstec\n0. Tornar al menú principal\nEscull una opció:  ");
        String opc = scanner.nextLine().toLowerCase();
        scanner.close();
        try {
            return switch (opc) {
                case "0", "tornar" -> () -> tornar();
                case "1", "afegir" -> () -> afegirPrestecs();
                case "2", "modificar" -> () -> modificarPrestecs();
                case "3", "eliminar" -> () -> eliminarPrestecs();
                case "4", "listar" -> () -> llistarPrestecs();
                default -> () -> error();
            };
        } catch (Exception e) {
            System.out.println("Opció no vàlida. Torna a probar.");
            return menuLlibres();
        }
    }

    public static Runnable menuUsuaris() {
        System.out.println("Gestió de Prestecs");
        System.out.print("1. Afegir préstec\n2. Modificar préstec\n3. Eliminar préstec\n4. Listar préstec\n0. Tornar al menú principal\nEscull una opció:  ");
        String opc = scanner.nextLine().toLowerCase();
        scanner.close();
        try {
            switch (opc) {
                case "0":
                case "tornar":
                    return () -> tornar();
                case "1":
                case "afegir":
                    return () -> afegirUsuaris();
                case "2":
                case "modificar":
                    return () -> modificarUsuaris();
                case "3":
                case "eliminar":
                    return () -> eliminarUsuaris();
                case "4":
                case "listar":
                    return () -> llistarUsuaris();
                default:
                    return () -> error();
            }
        } catch (Exception e) {
            System.out.println("Opció no vàlida. Torna a probar.");
            return menuLlibres();
        }
    }

    public static void main (String[] args) { 
        menuPrincipal();
}

    

    // LLIBRES //
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

        // creamos un objecto json para el nuevo libro
        JSONObject nouLlibre = new JSONObject();
        nouLlibre.put("id", id);
        nouLlibre.put("titol", titol);
        nouLlibre.put("autor", autor);
        nouLlibre.put("genre", genre);


        // ruta del archivo json
        String ruta = "biblioteca-project/main/data/llibres.json";

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

    public static void modificarLibres() {
        System.out.println("Modificar llibre");
    }

    public static void eliminarLlibres() {
        System.out.println("Eliminar llibre");
    }

    public static void llistarLlibres() {
        System.out.println("Listar llibre");
    }

    // PRESTECS //
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
    // GENERAL //
    public static void error() {
        System.out.println("Opció no vàlida. Torna a probar.");
    }
    public static void sortir() {
        System.exit(0);
    }
    public static void tornar() {
        menuPrincipal();
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

}