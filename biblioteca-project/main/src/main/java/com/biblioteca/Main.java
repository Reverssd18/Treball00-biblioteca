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
            switch (opc) {
                case "0":
                case "sortir":
                    return () -> sortir();
                case "1":
                case "llibres":
                    return () -> menuLlibres();
                case "2":
                case "modificar":
                    return () -> sortir();
                case "3":
                case "prestecs":
                case "préstecs":
                    return () -> sortir();
                default:         
                    return () -> error();
            }
            
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
            switch (opc) {
                case "0":
                case "tornar":
                    return () -> tornar();
                case "1":
                case "afegir":
                    return () -> afegirLlibres();

                case "2":
                case "modificar":
                    return () -> modificarLibres();
                case "3":
                case "eliminar":
                    return () -> eliminarLlibres();
                case "4":
                case "listar":
                    return () -> llistarLlibres();
                default:
                    return () -> error();
            }
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
            switch (opc) {
                case "0":
                case "tornar":
                    return () -> tornar();
                case "1":
                case "afegir":
                    return () -> afegirPrestecs();
                case "2":
                case "modificar":
                    return () -> modificarPrestecs();
                case "3":
                case "eliminar":
                    return () -> eliminarPrestecs();
                case "4":
                case "listar":
                    return () -> llistarPrestecs();
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