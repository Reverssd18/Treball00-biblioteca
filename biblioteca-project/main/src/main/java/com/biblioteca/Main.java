package com.biblioteca;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    public static String menuPrincipal() {
        try {
            System.out.print("1. Llibres\n2. Llibres\n3. Préstecs\n0. Sortir\n");
            Integer opc = scanner.nextInt();
            switch (opc) {
                case 0 -> {
                }
                case 1 -> {
                    return "llibres";
                }
                case 2 -> {
                    return "usuaris";
                }
                case 3 -> {
                    return "prestecs";
                }
            }
            scanner.close();
        } catch (Exception e) {
            System.out.println("Opció no vàlida. Torna a probar.");
            return menuPrincipal();
        }
        
        return "";
    }
    public static void main (String[] args) { 
            menuPrincipal();
    }

    public static Runnable menuLlibres() {
        System.out.print("1. Afegir llibre\n2. Modificar llibre\n3. Eliminar llibre\n4. Listar llibre\n0. Tornar al menú principal\n");
        String opc = scanner.nextLine().toLowerCase();
        scanner.close();
        try {
            switch (opc) {
                case "0":
                case "atras":
                    return () -> atras();
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
    public static void atras() {
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




}