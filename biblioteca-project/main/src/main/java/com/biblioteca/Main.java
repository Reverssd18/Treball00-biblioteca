package com.biblioteca;

import java.util.Scanner;

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
    public static void main (String[] args) { 
        menuPrincipal();
}

    

    // LLIBRES //
    public static void afegirLlibres() {
        System.out.println("Afegir llibre");
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


}