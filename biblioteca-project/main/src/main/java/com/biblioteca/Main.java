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
                    return () -> menuUsuaris();
                case "3":
                case "prestecs":
                case "préstecs":
                    return () -> menuPrestecs();
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
                case "atras":
                    return () -> tornar();
                case "1":
                case "afegir":
                    return () -> afegir();

                case "2":
                case "modificar":
                    return () -> modificar();
                case "3":
                case "eliminar":
                    return () -> eliminar();
                case "4":
                case "listar":
                    return () -> listar();
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

    public static void tornar() {
        menuPrincipal();
    }

    public static void afegir() {
        System.out.println("Afegir llibre");
    }

    public static void modificar() {
        System.out.println("Modificar llibre");
    }

    public static void eliminar() {
        System.out.println("Eliminar llibre");
    }

    public static void listar() {
        System.out.println("Listar llibre");
    }

    public static void error() {
        System.out.println("Opció no vàlida. Torna a probar.");
    }
    public static void sortir() {
        System.exit(0);
    }



}