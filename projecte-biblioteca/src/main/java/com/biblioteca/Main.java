package com.biblioteca;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Main {
    static Scanner scanner = new Scanner(System.in);

    public static void menuPrincipal() {
        while (true) {
            System.out.println("\n---Gestió de biblioteca---");
            System.out.print("1. Llibres\n2. Usuaris\n3. Préstecs\n0. Sortir\nEscull una opció:  ");
            String opc = scanner.nextLine().toLowerCase();
            switch (opc) {
                case "0", "sortir" -> System.exit(0);
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
            System.out.print(
                    "1. Afegir llibre\n2. Modificar llibre\n3. Eliminar llibre\n4. Llistar llibre\n0. Tornar al menú principal\nEscull una opció:  ");
            String opc = scanner.nextLine().toLowerCase();
            switch (opc) {
                case "0", "tornar" -> menuPrincipal();
                case "1", "afegir" -> afegirLlibres();
                case "2", "modificar" -> modificarLlibres();
                case "3", "eliminar" -> eliminarLlibres();
                case "4", "llistar" -> menuLlistarLlibres();
                default -> error();
            }
            break;
        }
    }

    public static void menuLlistarLlibres() {
        while (true) {
            System.out.println("\n---Llistar llibres---");
            System.out.print(
                    "1. Llistar llibres\n2. Llistar llibres en préstec\n3. Llistar llibres d'un autor\n4. Llistar llibres a partir de paraules al títol\n0. Tornar a Gestió de llibres\nEscull una opció:  ");
            String opc = scanner.nextLine().toLowerCase();
            switch (opc) {
                case "0", "tornar" -> menuLlibres();
                case "1", "llistar" -> llistarLlibres();
                case "2", "prestec", "préstec" -> System.out.println(llistarLlibresPrestec());
                case "3", "autor" -> llistarLlibresAutor();
                case "4", "paraules", "titol", "títol" -> llistarLlibresTitol();
                default -> error();
            }
            break;
        }
    }

    public static void menuPrestecs() {
        while (true) {
            System.out.println("\n---Gestió de Préstecs---");
            System.out.print(
                    "1. Afegir préstec\n2. Modificar préstec\n3. Eliminar préstec\n4. Llistar préstec\n0. Tornar al menú principal\nEscull una opció:  ");
            String opc = scanner.nextLine().toLowerCase();
            switch (opc) {
                case "0", "tornar" -> menuPrincipal();
                case "1", "afegir" -> afegirPrestecs();
                case "2", "modificar" -> modificarPrestecs();
                case "3", "eliminar" -> eliminarPrestecs();
                case "4", "llistar" -> menuLlistarPrestecs();
                default -> error();
            }
            break;
        }
    }

    public static void menuLlistarPrestecs() {
        while (true) {
            System.out.println("\n---Llistar préstecs---");
            System.out.print(
                    "1. Llistar préstecs\n2. LListar préstecs d'un usuari\n3. Llistar préstecs actius\n4. Llistar préstecs fora de termini\n0. Tornar a Gestió de préstecs\nEscull una opció:  ");
            String opc = scanner.nextLine().toLowerCase();
            switch (opc) {
                case "0", "tornar" -> menuPrestecs();
                case "1", "llistar" -> llistarPrestecs();
                case "2", "usuari" -> llistarPrestecsUsuari();
                case "3", "actiu" -> llistarPrestecsActius();
                case "4", "termini" -> llistarPrestecsForaTermini();
                default -> error();
            }
            break;
        }
    }

    public static void menuUsuaris() {
        while (true) {
            System.out.println("\n---Gestió de Usuaris---");
            System.out.print(
                    "1. Afegir usuaris\n2. Modificar usuaris\n3. Eliminar usuaris\n4. Llistar usuaris\n0. Tornar al menú principal\nEscull una opció:  ");
            String opc = scanner.nextLine().toLowerCase();
            switch (opc) {
                case "0", "tornar" -> menuPrincipal();
                case "1", "afegir" -> afegirUsuaris();
                case "2", "modificar" -> modificarUsuaris();
                case "3", "eliminar" -> eliminarUsuaris();
                case "4", "llistar" -> llistarUsuaris();
                default -> error();
            }
            break;
        }
    }

    public static void main(String[] args) {
        menuPrincipal();
    }

    // menu-functions

    public static JSONArray llegirJSONambPath(String path) {
        JSONArray jsonArray = new JSONArray();
        try {
            String contingut = new String(Files.readAllBytes(Paths.get(path)));
            jsonArray = new JSONArray(contingut);
        } catch (IOException | JSONException e) {
            System.err.println("Error llegint el JSON file: " + e.getMessage());
        }
        return jsonArray;
    }

    public static JSONArray getLlibres() {
        return llegirJSONambPath("projecte-biblioteca/data/llibres.json");
    }

    public static JSONArray getPrestecs() {
        return llegirJSONambPath("projecte-biblioteca/data/prestecs.json");
    }

    public static JSONArray getUsuaris() {
        return llegirJSONambPath("projecte-biblioteca/data/usuaris.json");
    }

    public static String llistarLlibres() {
        JSONArray llibres = getLlibres();
        StringBuilder result = new StringBuilder();
        if (llibres.length() == 0) {
            result.append("No hi ha llibres disponibles.\n");
        } else {
            String format = "%-5s %-30s %-30s %-20s%n"; // format per imprimir la taula indicant la mida de cada columna
            result.append(String.format(format, "Id", "Títol", "Autor", "Gènere"));
            result.append(String.format(format, "--", "-----", "-----", "------"));
            for (int i = 0; i < llibres.length(); i++) {
                result.append(String.format(format,
                        i + 1,
                        llibres.getJSONObject(i).getString("titol"),
                        llibres.getJSONObject(i).getString("autor"),
                        llibres.getJSONObject(i).getString("genre")));
            }
        }

        result.append("\n");
        System.out.println(result.toString());

        System.out.println(result.toString());
        System.out.println(result.toString());
        System.out.println(result.toString());
        return result.toString();
    }

    public static String llistarLlibresPrestec() {
        JSONArray llibres = getLlibres();
        JSONArray prestecs = getPrestecs();
        // Si tiene al menos un libro prestado mostramos la lista con informacion de los
        // prestamos añadido al final
        StringBuilder result = new StringBuilder();
        String format = "%-5s %-30s %-30s %-20s %-20s%n"; // format per imprimir la taula indicant la mida de cada
                                                          // columna
        result.append(String.format(format, "Id", "Títol", "Autor", "Gènere", "Data Préstec"));
        result.append(String.format(format, "--", "-----", "-----", "------", "------------"));
        for (int i = 0; i < llibres.length(); i++) {
            JSONObject llibre = llibres.getJSONObject(i);
            for (int j = 0; j < prestecs.length(); j++) {
                JSONObject prestec = prestecs.getJSONObject(j);
                if (prestec.getInt("id") == llibre.getInt("id")) {
                    result.append(String.format(format,
                            llibre.getInt("id"),
                            llibre.getString("titol"),
                            llibre.getString("autor"),
                            llibre.getString("genre"),
                            prestec.getString("dataPrestec")));
                }
            }
        }

        result.append("\n");
        return result.toString();
    }

    public static String llistarLlibresAutor() {
        JSONArray llibres = getLlibres();
        StringBuilder result = new StringBuilder();
        StringBuilder autorPreview = new StringBuilder();
        autorPreview.append("Autors disponibles: ");
        for (int i = 0; i < llibres.length(); i++) {
            JSONObject llibre = llibres.getJSONObject(i);
            if (!autorPreview.toString().contains(llibre.getString("autor"))) {
                if (autorPreview.length() > "Autores disponibles: ".length()) {
                    autorPreview.append(" | ");
                }
                autorPreview.append(llibre.getString("autor"));
            }
        }
        System.out.println(autorPreview.toString());
        System.out.println("Introdueix l'autor: ");
        String autor = scanner.nextLine();
        String format = "%-5s %-30s %-30s %-20s%n"; // format per imprimir la taula indicant la mida de cada columna
        result.append(String.format(format, "Id", "Títol", "Autor", "Gènere"));
        result.append(String.format(format, "--", "-----", "-----", "------"));
        boolean found = false;
        for (int i = 0; i < llibres.length(); i++) {
            JSONObject llibre = llibres.getJSONObject(i);
            if (llibre.getString("autor").equals(autor)) {
                result.append(String.format(format,
                        llibre.getInt("id"),
                        llibre.getString("titol"),
                        llibre.getString("autor"),
                        llibre.getString("genre")));
                found = true;
            }
        }
        if (!found) {
            System.out.println("No s'ha trobat cap llibre de l'autor " + autor);
            return result.toString();
        } else {
            System.out.println(result.toString());
        }

        return result.toString();
    }

    public static String llistarLlibresTitol() {
        JSONArray llibres = getLlibres();
        StringBuilder result = new StringBuilder();
        System.out.println("Introdueix les paraules del títol: ");
        String paraules = scanner.nextLine().toLowerCase();
        String format = "%-5s %-30s %-30s %-20s%n"; // format per imprimir la taula indicant la mida de cada columna
        result.append(String.format(format, "Id", "Títol", "Autor", "Gènere"));
        result.append(String.format(format, "--", "-----", "-----", "------"));
        boolean found = false;
        for (int i = 0; i < llibres.length(); i++) {
            JSONObject llibre = llibres.getJSONObject(i);
            if (llibre.getString("titol").toLowerCase().contains(paraules)) {
                result.append(String.format(format,
                        llibre.getInt("id"),
                        llibre.getString("titol"),
                        llibre.getString("autor"),
                        llibre.getString("genre")));
                found = true;
            }
        }
        if (!found) {
            System.out.println("No s'ha trobat cap llibre amb les paraules " + paraules);
            menuLlistarLlibres();
        } else {
            System.out.println(result.toString());
        }
        return result.toString();
    }

    public static void afegirLlibres() {
        System.out.println("Afegir llibre");
        System.out.println("Introdueix l'ID del llibre: ");
        Integer id = scanner.nextInt();
        scanner.nextLine(); // limpiamos el buffer
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

        if (titol.isEmpty() || autor.isEmpty() || genre.isEmpty()) {
            System.out.println("Error: Has d'omplir tots els camps");
            return;
        }
        if (id < 1000) {
            System.out.println("Error: L'ID ha de ser mes gran de 1000");
            return;
        }

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

        } catch (IOException e) {
            System.out.println("Error al llegir el fitxer");
        }

        // Comprobamos si el ID ya existe
        for (int i = 0; i < llibres.length(); i++) {
            JSONObject llibre = llibres.getJSONObject(i);
            if (llibre.getInt("id") == id) {
                System.out.println("Error: Ja existeix un llibre amb aquest ID");
                return;
            }
        }

        // leemos el archivo json
        try {
            File file = new File(ruta);
            if (file.exists()) {
                String content = new String(Files.readAllBytes(Path.of(ruta)));
                llibres = new JSONArray(content);
            }

        } catch (IOException | JSONException e) {
            System.out.println("Error al llegir el fitxer");
        }

        // añadimos el nuevo libro a la lista
        llibres.put(nouLlibre);

        // guardamos la lista actualizada en el archivo json
        try (FileWriter writer = new FileWriter(ruta)) { // usamos try with resources para cerrar el archivo
            writer.write(llibres.toString(4)); // usamos el 4 para que se vea bonito
        } catch (IOException e) {
            System.out.println("Error al escriure el fitxer");
        }

        System.out.println("Llibre afegit correctament");

        menuPrincipal();
    }

    public static void modificarLlibres() {

        System.out.println("Modificar llibre");
        System.out.println("Introdueix l'ID del llibre a modificar: ");
        Integer id = scanner.nextInt();
        scanner.nextLine(); // limpiamos el buffer

        // declaramos un array para guardar los llibres
        JSONArray llibres = getLlibres();

        // buscamos el libro con el id introducido
        for (int i = 0; i < llibres.length(); i++) { // recorremos el array de llibres con un bucle for
            // JSONObject llibre = llibres.getJSONObject(i); // obtenemos el objeto JSON de
            // la posición i
            // Integer llibresId = llibre.getInt("id"); // obtenemos el id del libro
            // if (llibresId.equals(id)) { // si el id del libro existente es igual al id
            // que el usuario a escrito
            JSONObject llibre = llibres.getJSONObject(i);
            if (llibre.getInt("id") == id) {
                System.out.println("Que vols modificar?");
                System.out.println("1. Títol");
                System.out.println("2. Autor");
                System.out.println("3. Gènere");
                System.out.println("0. Cancel·lar");
                String opc = scanner.nextLine().toLowerCase().trim();
                switch (opc) {
                    case "0", "cancel·lar" -> {
                        return;
                    }
                    case "1", "títol" -> {
                        System.out.println("Escriu el nou títol: ");
                        String nouTitol = scanner.nextLine();
                        llibre.put("titol", nouTitol); // actualizamos el valor del titulo
                    }
                    case "2", "autor" -> {
                        System.out.println("Escriu el nou autor: ");
                        String nouAutor = scanner.nextLine();
                        llibre.put("autor", nouAutor); // actualizamos el valor del autor
                    }
                    case "3", "gènere" -> {
                        System.out.println("Escriu el nou gènere: ");
                        String nouGènere = scanner.nextLine();
                        llibre.put("genre", nouGènere); // actualizamos el valor del gènere
                    }
                    default -> {
                        System.out.println("Opció no vàlida. Torna a provar.");
                        return;
                    }
                }
            }
        }

        // guardamos el array actualizado en el archivo json
        try {
            Files.write(Path.of("projecte-biblioteca/data/llibres.json"), llibres.toString(4).getBytes());
            System.out.println("Llibre modificat correctament");
        } catch (IOException e) {
            System.out.println("Error al escriure el fitxer");
        }
        menuPrincipal();
    }

    public static void eliminarLlibres() {
        System.out.println("Eliminar llibre");
        System.out.println("Introdueix l'ID del llibre a eliminar: ");
        Integer id = scanner.nextInt();
        scanner.nextLine(); // limpiamos el buffer

        // ruta del archivo json
        String ruta = "projecte-biblioteca/data/llibres.json";

        // declaramos un array para guardar los llibres
        JSONArray llibres = new JSONArray();

        // leemos el archivo json
        try {
            File file = new File(ruta);
            if (file.exists()) {
                String content = new String(Files.readAllBytes(Path.of(ruta))); // leemos el archivo json
                llibres = new JSONArray(content); // añadimos a libros el contenido del archivo json
            }
        } catch (IOException | JSONException e) {
            System.out.println("Error al llegir el fitxer");
        }

        // buscamos el libro con el id introducido
        for (int i = 0; i < llibres.length(); i++) { // recorremos el array de llibres con un bucle for
            JSONObject llibre = llibres.getJSONObject(i); // obtenemos el objeto JSON de la posición i
            if (llibre.getInt("id") == id) { // si el id del libro existente es igual al id que el usuario a escrito
                llibres.remove(i); // eliminamos el libro del array
                break; // salimos del bucle
            }
        }

        // guardamos el array actualizado en el archivo json
        try {
            Files.write(Path.of(ruta), llibres.toString(4).getBytes()); // escribimos el array en el archivo json
            System.out.println("Llibre eliminat correctament");
        } catch (IOException | JSONException e) {
        }

        menuPrincipal();
    } // volvemos al menú principal

    public static void afegirPrestecs() {
        System.out.println("Afegir préstec");
        System.out.println("Introdueix l'ID del préstec: ");
        Integer idPrestec = scanner.nextInt();
        scanner.nextLine(); // limpiamos el buffer
        System.out.println("Introdueix l'ID del llibre: ");
        Integer idLlibre = scanner.nextInt();
        scanner.nextLine(); // limpiamos el buffer
        System.out.println("Introdueix la data de préstec: ");
        String dataPrestec = scanner.nextLine();
        System.out.println("Introdueix la data de devolució: ");
        String dataDevolucio = scanner.nextLine();

        // ruta del archivo json
        String llibresRuta = "projecte-biblioteca/data/llibres.json";

        // declaramos un array para guardar los llibres
        JSONArray llibres = new JSONArray();

        // leemos el archivo json
        try {
            File llibresFile = new File(llibresRuta);
            if (llibresFile.exists()) {
                String llibresContent = new String(Files.readAllBytes(Path.of(llibresRuta)));
                llibres = new JSONArray(llibresContent);
            }
        } catch (IOException | JSONException e) {
            System.out.println("Error al llegir el fitxer");
            return;
        }

        JSONObject llibreExistent = null;

        // Comprobamos si el ID del préstec ya existe
        for (int i = 0; i < llibres.length(); i++) {
            JSONObject llibre = llibres.getJSONObject(i);
            if (llibre.getInt("id") == idLlibre) {
                llibreExistent = llibre;
                break;
            }
        }

        if (llibreExistent == null) {
            System.out.println("Error: No existeix un llibre amb aquest ID en el fitxer llibres.json");
            return;
        }

        // creamos un JSONObject con los datos del nuevo préstec
        JSONObject nouPrestec = new JSONObject();

        nouPrestec.put("idPrestec", idPrestec);
        nouPrestec.put("id", idLlibre);
        nouPrestec.put("titol", llibreExistent.getString("titol"));
        nouPrestec.put("autor", llibreExistent.getString("autor"));
        nouPrestec.put("genre", llibreExistent.getString("genre"));
        nouPrestec.put("dataPrestec", dataPrestec);
        nouPrestec.put("dataDevolucio", dataDevolucio);

        if (dataPrestec.isEmpty() || dataDevolucio.isEmpty()) {
            System.out.println("Error: Has d'omplir tots els camps");
            return;
        }
        // Validación del ID del préstec
        if (idPrestec < 2000) {
            System.out.println("Error: L'ID del préstec ha de ser més gran de 2000");
            return;
        }

        // Validación de formato de fechas
        if (!dataPrestec.matches("\\d{4}-\\d{2}-\\d{2}") || !dataDevolucio.matches("\\d{4}-\\d{2}-\\d{2}")) {
            System.out.println("Error: El format de la data ha de ser yyyy-mm-dd");
            return;
        }

        try {
            LocalDate.parse(dataPrestec);
            LocalDate.parse(dataDevolucio);
        } catch (DateTimeParseException e) {
            System.out.println("Error: El format de la data ha de ser yyyy-mm-dd");
            return;
        }

        // ruta del archivo json
        String prestecsRuta = "projecte-biblioteca/data/prestecs.json";
        JSONArray prestecs = new JSONArray();

        // leemos el archivo json
        try {
            File prestecsFile = new File(prestecsRuta); // ponemos el archivo en una variable
            if (prestecsFile.exists()) { // comprobamos si el archivo existe
                String prestecsContent = new String(Files.readAllBytes(Path.of(prestecsRuta))); // en caso de que
                                                                                                // exista, leemos el
                                                                                                // archivo
                prestecs = new JSONArray(prestecsContent); // añadimos a prestecs el contenido del archivo json
            }
        } catch (IOException | JSONException e) {
            System.out.println("Error al llegir el fitxer");
            return;
        }

        // Comprobamos si el ID del préstec ya existe
        for (int i = 0; i < prestecs.length(); i++) {
            JSONObject prestec = prestecs.getJSONObject(i);
            if (prestec.getInt("idPrestec") == idPrestec) {
                System.out.println("Error: Ja existeix un préstec amb aquest ID");
                return;
            }
        }

        // añadimos el nuevo préstec a la lista
        prestecs.put(nouPrestec);

        // guardamos la lista actualizada en el archivo json
        try (FileWriter writer = new FileWriter(prestecsRuta)) {
            writer.write(prestecs.toString(4));
        } catch (IOException e) {
            System.out.println("Error al escriure el fitxer");
            return;
        }

        System.out.println("Préstec afegit correctament");

        menuPrincipal();

    }

    public static void modificarPrestecs() {
        System.out.println("Modificar préstec");
        System.out.println("Introdueix l'ID del préstec a modificar: ");
        Integer idPrestec = scanner.nextInt();
        scanner.nextLine(); // limpiamos el buffer

        // ruta del archivo json
        String ruta = "projecte-biblioteca/data/prestecs.json";

        // declaramos un array para guardar los préstecs
        JSONArray prestecs = new JSONArray();

        // leemos el archivo json
        try {
            File file = new File(ruta);
            if (file.exists()) {
                String content = new String(Files.readAllBytes(Path.of(ruta)));
                prestecs = new JSONArray(content);
            }
        } catch (IOException | JSONException e) {
            System.out.println("Error al llegir el fitxer");
        }

        for (int i = 0; i < prestecs.length(); i++) {
            JSONObject prestec = prestecs.getJSONObject(i);
            if (prestec.getInt("idPrestec") == idPrestec) {
                System.out.println("Que vols modificar?");
                System.out.println("1. Data de préstec");
                System.out.println("2. Data de devolució");
                System.out.println("0. Cancel·lar");
                String opc = scanner.nextLine().toLowerCase().trim();
                switch (opc) {
                    case "0", "cancel·lar" -> {
                        return;
                    }
                    case "1", "data de préstec" -> {
                        System.out.println("Escriu la nova data de préstec: ");
                        String novaDataPrestec = scanner.nextLine();
                        prestec.put("dataPrestec", novaDataPrestec); // actualizamos el valor de la data de préstec
                    }
                    case "2", "data de devolució" -> {
                        System.out.println("Escriu la nova data de devolució: ");
                        String novaDataDevolucio = scanner.nextLine();
                        prestec.put("dataDevolucio", novaDataDevolucio); // actualizamos el valor de la data de
                                                                         // devolució
                    }
                    default -> {
                        System.out.println("Opció no vàlida. Torna a provar.");
                        return;
                    }
                }
            }
        }

        try {
            Files.write(Path.of(ruta), prestecs.toString(4).getBytes());
            System.out.println("Préstec modificat correctament");
        } catch (IOException | JSONException e) {
        }

        menuPrincipal();
    }

    public static void eliminarPrestecs() {
        System.out.println("Eliminar préstec");
        System.out.println("Introdueix l'ID del préstec a eliminar: ");
        Integer idPrestc = scanner.nextInt();

        String ruta = "projecte-biblioteca/data/prestecs.json";

        JSONArray prestecs = new JSONArray();

        try {
            File file = new File(ruta);
            if (file.exists()) {
                String content = new String(Files.readAllBytes(Path.of(ruta)));
                prestecs = new JSONArray(content);
            }
        } catch (IOException e) {
            System.out.println("Error al llegir el fitxer");
        }

        for (int i = 0; i < prestecs.length(); i++) {
            JSONObject prestec = prestecs.getJSONObject(i);
            if (prestec.getInt("idPrestec") == idPrestc) {
                prestecs.remove(i); // removemos el libro del array si el id coincide
                break;
            }
        }

        try {
            Files.write(Path.of(ruta), prestecs.toString(4).getBytes());
            System.out.println("Préstec eliminat correctament");
        } catch (IOException e) {
            System.out.println("Error al escriure el fitxer");
        }

        menuPrincipal();
    }

    public static String llistarPrestecs() {
        JSONArray prestecs = getPrestecs();
        StringBuilder result = new StringBuilder();
        if (prestecs.length() == 0) {
            result.append("No hi ha préstecs disponibles.\n");
        } else {
            String format = "%-10s %-30s %-30s %-15s %-15s %-10s%n"; // format
            result.append(String.format(format, "Id", "Títol", "Autor", "Data Préstec", "Data Devolució", "Id Usuari"));
            result.append(String.format(format, "--", "-----", "-----", "------------", "-------------", "--------"));
            for (int i = 0; i < prestecs.length(); i++) {
                result.append(String.format(format,
                        prestecs.getJSONObject(i).getInt("idPrestec"),
                        prestecs.getJSONObject(i).getString("titol"),
                        prestecs.getJSONObject(i).getString("autor"),
                        prestecs.getJSONObject(i).getString("dataPrestec"),
                        prestecs.getJSONObject(i).getString("dataDevolucio"),
                        prestecs.getJSONObject(i).getInt("id")));
                result.append("\n");
            }
        }
        System.out.println(result.toString());
        return result.toString();
    }

    public static String llistarPrestecsUsuari() {
        JSONArray prestecs = getPrestecs();
        StringBuilder result = new StringBuilder();

        System.out.print("Introdueix l'ID de l'usuari: ");
        Integer idUsuari;

        try {
            idUsuari = scanner.nextInt();
            scanner.nextLine();
        } catch (Exception e) {
            scanner.nextLine();
            return "Error: ID d'usuari no vàlid.\n";
        }

        if (prestecs.length() == 0) {
            return "No hi ha préstecs disponibles.\n";
        }

        String format = "%-10s %-30s %-30s %-15s %-15s %-10s%n";
        result.append(String.format(format, "Id", "Títol", "Autor", "Data Préstec", "Data Devolució", "Id Usuari"));
        result.append(String.format(format, "--", "-----", "-----", "------------", "-------------", "--------"));

        boolean found = false;
        for (int i = 0; i < prestecs.length(); i++) {
            JSONObject prestec = prestecs.getJSONObject(i);
            if (prestec.getInt("id") == idUsuari) {
                result.append(String.format(format,
                        prestec.getInt("idPrestec"),
                        prestec.getString("titol"),
                        prestec.getString("autor"),
                        prestec.getString("dataPrestec"),
                        prestec.getString("dataDevolucio"),
                        prestec.getInt("id")));
                found = true;
            }
        }

        if (!found) {
            return "No s'han trobat préstecs per a l'usuari amb ID " + idUsuari + ".\n";
        }
        System.out.println(result.toString());
        return result.toString();
    }

    public static String llistarPrestecsActius() {
        JSONArray prestecs = getPrestecs();
        StringBuilder result = new StringBuilder();

        if (prestecs.length() == 0) {
            return "No hi ha préstecs actius.\n";
        }

        String format = "%-10s %-30s %-30s %-15s %-15s %-10s%n";
        result.append(String.format(format, "Id", "Títol", "Autor", "Data Préstec", "Termini", "Id Usuari"));
        result.append(String.format(format, "--", "-----", "-----", "------------", "-------", "--------"));

        boolean found = false;
        for (int i = 0; i < prestecs.length(); i++) {
            JSONObject prestec = prestecs.getJSONObject(i);
            if (prestec.getString("dataDevolucio").isEmpty()
                    || LocalDate.parse(prestec.getString("dataDevolucio")).isAfter(LocalDate.now())) {
                result.append(String.format(format,
                        prestec.getInt("idPrestec"),
                        prestec.getString("titol"),
                        prestec.getString("autor"),
                        prestec.getString("dataPrestec"),
                        prestec.getString("termini"),
                        prestec.getInt("id")));
                found = true;
            }
        }

        if (!found) {
            return "No hi ha préstecs actius.\n";
        }
        System.out.println(result.toString());
        return result.toString();
    }

    public static String llistarPrestecsForaTermini() {
        JSONArray prestecs = getPrestecs();
        StringBuilder result = new StringBuilder();

        if (prestecs.length() == 0) {
            return "No hi ha préstecs fora de termini.\n";
        }

        String format = "%-10s %-30s %-30s %-15s %-15s %-10s%n";
        result.append(String.format(format, "Id", "Títol", "Autor", "Data Préstec", "Data Devolució", "Termini",
                "Id Usuari"));
        result.append(
                String.format(format, "--", "-----", "-----", "------------", "-------------", "-------", "--------"));

        boolean found = false;
        for (int i = 0; i < prestecs.length(); i++) {
            JSONObject prestec = prestecs.getJSONObject(i);

            if (!prestec.getString("dataDevolucio").isEmpty()) {
                LocalDate dataDevolucio = LocalDate.parse(prestec.getString("dataDevolucio"));
                if (dataDevolucio.isBefore(LocalDate.now())) {
                    result.append(String.format(format,
                            prestec.getInt("idPrestec"),
                            prestec.getString("titol"),
                            prestec.getString("autor"),
                            prestec.getString("dataPrestec"),
                            prestec.getString("dataDevolucio"),
                            prestec.getString("termini"),
                            prestec.getInt("id")));
                    found = true;
                }
            }
        }

        if (!found) {
            String noResult = "No hi ha préstecs fora de termini.\n";
            System.out.println(noResult);
            return noResult;
        }
        System.out.println(result.toString());
        return result.toString();
    }

    public static void afegirUsuaris() {
        System.out.println("Afegir usuaris");

        System.out.println("Afegir usuaris");
        System.out.println("Introdueix l'ID de l'usuari: ");
        if (!scanner.hasNextInt()) {
            System.out.println("Error: L'ID ha de ser un número");
            scanner.next();
            return;
        }
        Integer id = scanner.nextInt();
        scanner.nextLine(); // limpiamos el buffer

        System.out.println("Introdueix el nom de l'usuari: ");
        String nom = scanner.nextLine();
        System.out.println("Introdueix el cognom de l'usuari: ");
        String cognom = scanner.nextLine();
        System.out.println("Introdueix l'edat de l'usuari: ");
        if (!scanner.hasNextInt()) {
            System.out.println("Error: L'edat ha de ser un número");
            scanner.next();
            return;
        }
        Integer edat = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Introdueix el DNI de l'usuari: ");
        String DNI = scanner.nextLine();
        System.out.println("Introdueix el telèfon de l'usuari: ");
        if (!scanner.hasNextInt()) {
            System.out.println("Error: El telèfon ha de ser un número");
            scanner.next();
            return;
        }

        Integer tlf = scanner.nextInt();
        scanner.nextLine();

        if (edat < 10) {
            System.out.println("Error: L'edat ha de ser més gran de 0");
            return;
        }
        
        if (nom.isEmpty() || cognom.isEmpty() || DNI.isEmpty()) {
            System.out.println("Error: Has d'omplir tots els camps");
            return;
        }

        if (id < 0) {
            System.out.println("Error: L'ID ha de ser mes gran de 3000");
            return;
        }

        // creamos un JSONObject con los datos del nuevo usuario
        JSONObject nouUsuari = new JSONObject();
        nouUsuari.put("IdUsuari", id);
        nouUsuari.put("nom", nom);
        nouUsuari.put("cognom", cognom);
        nouUsuari.put("Age", edat);
        nouUsuari.put("DNI", DNI);
        nouUsuari.put("Tlf", tlf);

        // ruta del archivo json
        String ruta = "projecte-biblioteca/data/usuaris.json";

        // leemos el archivo json y lo convertimos en un array
        JSONArray usuaris = new JSONArray();

        // leemos el archivo json
        try {
            File file = new File(ruta);
            if (file.exists()) {
                String content = new String(Files.readAllBytes(Path.of(ruta)));
                usuaris = new JSONArray(content);
            }

        } catch (IOException e) {
            System.out.println("Error al llegir el fitxer");
        }

        // Comprobamos si el ID ya existe
        for (int i = 0; i < usuaris.length(); i++) {
            JSONObject usuari = usuaris.getJSONObject(i);
            if (usuari.getInt("IdUsuari") == id) {
                System.out.println("Error: Ja existeix un usuari amb aquest ID");
                return;
            }
        }

        usuaris.put(nouUsuari);

        try ( FileWriter writer = new FileWriter(ruta)) {
            writer.write(usuaris.toString(4));
        } catch (IOException e) {
            System.out.println("Error al escriure el fitxer");
        }
        
        System.out.println("Usuari afegit correctament");

        menuPrincipal();
    }

    public static void modificarUsuaris() {
        System.out.println("Modificar usuaris");

        System.out.println("Introdueix l'ID de l'usuari a modificar: ");
        Integer id = scanner.nextInt();
        scanner.nextLine(); // limpiamos el buffer

        // declaramos un array para guardar los usuaris
        JSONArray usuaris = new JSONArray();

        // leemos el archivo json
        try {
            File file = new File("projecte-biblioteca/data/usuaris.json");
            if (file.exists()) {
                String content = new String(Files.readAllBytes(Path.of("projecte-biblioteca/data/usuaris.json")));
                usuaris = new JSONArray(content);
            }
        } catch (IOException e) {
            System.out.println("Error al llegir el fitxer");
        }

        // buscamos el usuario con el id introducido
        for (int i = 0; i < usuaris.length(); i ++) { 
            JSONObject usuari = usuaris.getJSONObject(i); // getJSONObject(i) obtiene el objeto JSON de la posición i
            if (usuari.getInt("IdUsuari") == id) {
                System.out.println("Que vols modificar?");
                System.out.println("1. Nom");
                System.out.println("2. Cognom");
                System.out.println("3. Edat");
                System.out.println("4. DNI");
                System.out.println("5. Telèfon");
                System.out.println("0. Cancel·lar");
                String opc = scanner.nextLine().toLowerCase().trim(); 
                switch (opc) {
                    case "0", "cancel·lar" -> { return; }
                    case "1", "nom" -> {
                        System.out.println("Escriu el nou nom: ");
                        String nouNom = scanner.nextLine();
                        usuari.put("nom", nouNom); // actualizamos el valor del nom
                    }
                    case "2", "cognom" -> {
                        System.out.println("Escriu el nou cognom: ");
                        String nouCognom = scanner.nextLine();
                        usuari.put("cognom", nouCognom); 
                    }
                    case "3", "edat" -> {
                        System.out.println("Escriu la nova edat: ");
                        Integer novaEdat = scanner.nextInt();
                        scanner.nextLine();
                        usuari.put("Age", novaEdat); 
                    }
                    case "4", "dni" -> {
                        System.out.println("Escriu el nou DNI: ");
                        String nouDNI = scanner.nextLine();
                        usuari.put("DNI", nouDNI); 
                    }
                    case "5", "telèfon" -> {
                        System.out.println("Escriu el nou telèfon: ");
                        Integer nouTlf = scanner.nextInt();
                        scanner.nextLine();
                        usuari.put("Tlf", nouTlf); 
                    }
                    default -> {
                        System.out.println("Opció no vàlida. Torna a provar.");
                        return;
                    }
                }
            }
        }

        // guardamos el array actualizado en el archivo json
        try {
            Files.write(Path.of("projecte-biblioteca/data/usuaris.json"), usuaris.toString(4).getBytes());
            System.out.println("Usuari modificat correctament");
        } catch (IOException e) {
            System.out.println("Error al escriure el fitxer");
        }

        menuPrincipal();
    }

    public static void eliminarUsuaris() {
        System.out.println("Eliminar usuaris");

        System.out.println("Introdueix l'ID de l'usuari a eliminar: ");
        Integer id = scanner.nextInt();
        scanner.nextLine(); // limpiamos el buffer

        // ruta del archivo json
        String ruta = "projecte-biblioteca/data/usuaris.json";

        // declaramos un array para guardar los usuaris
        JSONArray usuaris = new JSONArray();

        // leemos el archivo json
        try {
            File file = new File(ruta);
            if (file.exists()) {
                String content = new String(Files.readAllBytes(Path.of(ruta)));
                usuaris = new JSONArray(content);
            }
        } catch (IOException e) {
            System.out.println("Error al llegir el fitxer");
        }

        // buscamos el usuario con el id introducido
        for (int i = 0; i < usuaris.length(); i++) {
            JSONObject usuari = usuaris.getJSONObject(i);
            if (usuari.getInt("IdUsuari") == id) {
                usuaris.remove(i); // eliminamos el usuario del array
                break; // salimos del bucle
            }
        }

        // guardamos el array actualizado en el archivo json
        try {
            Files.write(Path.of(ruta), usuaris.toString(4).getBytes());
            System.out.println("Usuari eliminat correctament");
        } catch (IOException e) {
            System.out.println("Error al escriure el fitxer");
        }

        menuPrincipal();
    }

    public static void llistarUsuaris() {
        System.out.println("Listar usuaris");
    }

    public static void error() {
        System.out.println("Opció no vàlida. Torna a provar.");
    }

}
