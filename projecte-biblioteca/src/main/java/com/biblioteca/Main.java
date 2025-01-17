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
            System.out.print("1. Afegir llibre\n2. Modificar llibre\n3. Eliminar llibre\n4. Llistar llibre\n0. Tornar al menú principal\nEscull una opció:  ");
            String opc = scanner.nextLine().toLowerCase();
            switch (opc) {
                case "0", "tornar" -> menuPrincipal();
                case "1", "afegir" -> afegirLlibres();
                case "2", "modificar" -> modificarLlibres();
                case "3", "eliminar" -> eliminarLlibres();
                case "4", "llistar" -> System.out.println(llistarLlibres());
                default -> error();
            }
            break;
        }
    }

    public static void menuPrestecs() {
        while (true) {
            System.out.println("\n---Gestió de Préstecs---");
            System.out.print("1. Afegir préstec\n2. Modificar préstec\n3. Eliminar préstec\n4. Llistar préstec\n0. Tornar al menú principal\nEscull una opció:  ");
            String opc = scanner.nextLine().toLowerCase();
            switch (opc) {
                case "0", "tornar" -> menuPrincipal();
                case "1", "afegir" -> afegirPrestecs();
                case "2", "modificar" -> modificarPrestecs();
                case "3", "eliminar" -> eliminarPrestecs();
                case "4", "llistar" -> llistarPrestecs();
                default -> error();
            }
            break;
        }
    }

    public static void menuUsuaris() {
        while (true) {
            System.out.println("\n---Gestió de Usuaris---");
            System.out.print("1. Afegir usuaris\n2. Modificar usuaris\n3. Eliminar usuaris\n4. Llistar usuaris\n0. Tornar al menú principal\nEscull una opció:  ");
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
        public static void main(String[] args)  {
        menuPrincipal();
    }

    // menu-functions

    public static JSONArray getLlibres()  {
        String path = "projecte-biblioteca/data/llibres.json";
        String llibresArray = "";
        try {
            llibresArray = new String(Files.readAllBytes(Paths.get(path)));
        } catch (IOException e) {
            System.err.println(e);
        }

        JSONArray llibresResult = new JSONArray(llibresArray);
        return llibresResult;
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
                    llibres.getJSONObject(i).getString("genre")
                ));
            }
        }
        
        result.append("\n");
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

        } catch (Exception e) {
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

        // ruta del archivo json
        String ruta = "projecte-biblioteca/data/llibres.json";

        // declaramos un array para guardar los llibres
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


        // buscamos el libro con el id introducido
        for (int i = 0; i < llibres.length(); i ++) { // recorremos el array de llibres con un bucle for 
            // JSONObject llibre = llibres.getJSONObject(i); // obtenemos el objeto JSON de la posición i 
            // Integer llibresId = llibre.getInt("id"); // obtenemos el id del libro
            // if (llibresId.equals(id)) { // si el id del libro existente es igual al id que el usuario a escrito 
            JSONObject llibre = llibres.getJSONObject(i); 
            if (llibre.getInt("id") == id) {
                System.out.println("Que vols modificar?");
                System.out.println("1. Títol");
                System.out.println("2. Autor");
                System.out.println("3. Gènere");
                System.out.println("0. Cancel·lar");
                String opc = scanner.nextLine().toLowerCase().trim(); 
                switch (opc) {
                    case "0", "cancel·lar" -> { return; }
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
        
        try {
            Files.write(Path.of(ruta), llibres.toString(4).getBytes());
            System.out.println("Llibre modificat correctament");
        } catch (Exception e) {
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
        } catch (Exception e) {
            System.out.println("Error al llegir el fitxer");
        }

        // buscamos el libro con el id introducido
        for (int i = 0; i < llibres.length(); i ++) { // recorremos el array de llibres con un bucle for 
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
        } catch (Exception e) {
        }

        menuPrincipal();} // volvemos al menú principal

        public static JSONArray getPrestecs()  {
            String path = "projecte-biblioteca/data/prestecs.json";
            String prestecsArray = "";
            try {
                prestecsArray = new String(Files.readAllBytes(Paths.get(path)));
            } catch (IOException e) {
                System.err.println(e);
            }
    
            JSONArray prestecsResult = new JSONArray(prestecsArray);
            return prestecsResult;
        }

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
        } catch (Exception e) {
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
                String prestecsContent = new String(Files.readAllBytes(Path.of(prestecsRuta))); // en caso de que exista, leemos el archivo
                prestecs = new JSONArray(prestecsContent); // añadimos a prestecs el contenido del archivo json
            }
        } catch (Exception e) {
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
