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
}