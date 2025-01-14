package com.biblioteca;

import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);

    public static void menuPrincipal() {
        while (true) {
            System.out.println("\n---Gestió de biblioteca---");
            System.out.print("1. Llibres\n2. Usuaris\n3. Préstecs\n0. Sortir\nEscull una opció:  ");
            String opc = scanner.nextLine().toLowerCase();
            switch (opc) {
                case "0", "sortir" -> sortir();
                case "1", "llibres" -> menuLlibres();
                case "2", "usuaris" -> menuUsuaris();
                case "3", "prestecs", "préstecs" -> menuPrestecs();
                default -> error();
            }
        }
    }

    public static void menuLlibres() {
        while (true) {
            System.out.println("\n---Gestió de llibres---");
            System.out.print("1. Afegir llibre\n2. Modificar llibre\n3. Eliminar llibre\n4. Listar llibre\n0. Tornar al menú principal\nEscull una opció:  ");
            String opc = scanner.nextLine().toLowerCase();
            switch (opc) {
                case "0", "tornar" -> { return; } // Regresa al menú principal
                case "1", "afegir" -> afegirLlibres();
                case "2", "modificar" -> modificarLlibres();
                case "3", "eliminar" -> eliminarLlibres();
                case "4", "llistar" -> llistarLlibres();
                default -> error();
            }
        }
    }

    public static void menuPrestecs() {
        while (true) {
            System.out.println("\n---Gestió de Préstecs---");
            System.out.print("1. Afegir préstec\n2. Modificar préstec\n3. Eliminar préstec\n4. Listar préstec\n0. Tornar al menú principal\nEscull una opció:  ");
            String opc = scanner.nextLine().toLowerCase();
            switch (opc) {
                case "0", "tornar" -> { return; } // Regresa al menú principal
                case "1", "afegir" -> afegirPrestecs();
                case "2", "modificar" -> modificarPrestecs();
                case "3", "eliminar" -> eliminarPrestecs();
                case "4", "llistar" -> llistarPrestecs();
                default -> error();
            }
        }
    }

    public static void menuUsuaris() {
        while (true) {
            System.out.println("Gestió de Usuaris");
            System.out.print("1. Afegir usuaris\n2. Modificar usuaris\n3. Eliminar usuaris\n4. Listar usuaris\n0. Tornar al menú principal\nEscull una opció:  ");
            String opc = scanner.nextLine().toLowerCase();
            switch (opc) {
                case "0", "tornar" -> { return; } // Regresa al menú principal
                case "1", "afegir" -> afegirUsuaris();
                case "2", "modificar" -> modificarUsuaris();
                case "3", "eliminar" -> eliminarUsuaris();
                case "4", "listar" -> llistarUsuaris();
                default -> error();
            }
        }
    }

    public static void main(String[] args) {
        menuPrincipal();
    }

    // menu-functions
    public static void afegirLlibres() {
        System.out.println("Afegir llibre");
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

    public static void sortir() {
        System.out.println("Surtint del programa...");
        System.exit(0);
    }
}
