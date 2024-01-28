package com.fges.todoapp;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.MissingNode;
import org.apache.commons.cli.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class App {

    public static void main(String[] args) throws Exception {
        System.exit(exec(args));
    }

    // Fonction principale pour l'exécution des commandes
    public static int exec(String[] args) throws IOException {
        Options cliOptions = createCliOptions(); // Appel de la méthode pour créer les options de la ligne de commande
        CommandLineParser parser = new DefaultParser();

        CommandLine cmd;
        try {
            cmd = parser.parse(cliOptions, args);
        } catch (ParseException ex) {
            handleParseException(ex); // Gestion des erreurs liées à la ligne de commande
            return 1;
        }

        String fileName = cmd.getOptionValue("s");

        List<String> positionalArgs = cmd.getArgList();
        if (positionalArgs.isEmpty()) {
            System.err.println("Missing Command");
            return 1;
        }

        String command = positionalArgs.get(0);

        Path filePath = Paths.get(fileName);

        String fileContent = readFileContent(filePath);

        TodoFileManager todoFileManager = new TodoFileManager(filePath, fileContent);

        if (command.equals("insert")) {
            // Gestion de la commande 'insert'
            handleInsertCommand(positionalArgs, fileName, fileContent, filePath, todoFileManager);
        }

        if (command.equals("list")) {
            // Gestion de la commande 'list'
            handleListCommand(fileName, fileContent, todoFileManager);
        }

        System.err.println("Done.");
        return 0;
    }

    // Options de la ligne de commande
    private static Options createCliOptions() {
        Options cliOptions = new Options();
        cliOptions.addRequiredOption("s", "source", true, "File containing the todos");
        return cliOptions;
    }

    // Gestion des exceptions lors de l'analyse des arguments
    private static void handleParseException(ParseException ex) {
        System.err.println("Fail to parse arguments: " + ex.getMessage());
    }

    // Lecture du contenu du fichier
    private static String readFileContent(Path filePath) throws IOException {
        String fileContent = "";
        if (Files.exists(filePath)) {
            fileContent = Files.readString(filePath);
        }
        return fileContent;
    }

    // Gestion de la commande 'insert'
    private static void handleInsertCommand(List<String> positionalArgs, String fileName, String fileContent, Path filePath, TodoFileManager todoFileManager) throws IOException {
        if (positionalArgs.size() < 2) {
            System.err.println("Missing TODO name");
            return;
        }

        String todo = positionalArgs.get(1);
        todoFileManager.insertTodo(todo);
    }

    // Gestion de la commande 'list'
    private static void handleListCommand(String fileName, String fileContent, TodoFileManager todoFileManager) {
        todoFileManager.listTodos();
    }

    // Classe interne pour gérer les opérations liées aux fichiers de todos
    private static class TodoFileManager {
        private final Path filePath;
        private String fileContent;  // Retirer le mot-clé 'final'

        // Constructeur de TodoFileManager
        public TodoFileManager(Path filePath, String fileContent) {
            this.filePath = filePath;
            this.fileContent = fileContent;
        }

        // Méthode pour l'insertion de tâches dans le fichier
        public void insertTodo(String todo) throws IOException {
            if (filePath.toString().endsWith(".json")) {
                // Insertion pour les fichiers JSON
                handleJsonInsert(todo);
            }
            if (filePath.toString().endsWith(".csv")) {
                // Insertion pour les fichiers CSV
                handleCsvInsert(todo);
            }
        }

        // Méthode privée pour gérer l'insertion dans les fichiers JSON
        private void handleJsonInsert(String todo) throws IOException {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode actualObj = mapper.readTree(fileContent);
            if (actualObj instanceof MissingNode) {
                actualObj = JsonNodeFactory.instance.arrayNode();
            }

            if (actualObj instanceof ArrayNode arrayNode) {
                arrayNode.add(todo);
            }

            fileContent = actualObj.toString();  // Mettre à jour la variable fileContent
            Files.writeString(filePath, fileContent);
        }

        // Méthode privée pour gérer l'insertion dans les fichiers CSV
        private void handleCsvInsert(String todo) throws IOException {
            if (!fileContent.endsWith("\n") && !fileContent.isEmpty()) {
                fileContent += "\n";
            }
            fileContent += todo;

            Files.writeString(filePath, fileContent);
        }

        // Méthode pour lister les tâches dans le fichier
        public void listTodos() {
            if (filePath.toString().endsWith(".json")) {
                // Liste pour les fichiers JSON
                handleJsonList();
            }
            if (filePath.toString().endsWith(".csv")) {
                // Liste pour les fichiers CSV
                handleCsvList();
            }
        }

        // Méthode privée pour gérer la liste dans les fichiers JSON
        private void handleJsonList() {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode actualObj = parseJsonNode();
            if (actualObj instanceof ArrayNode arrayNode) {
                arrayNode.forEach(node -> System.out.println("- " + node.toString()));
            }
        }

        // Méthode privée pour lire et parser le contenu JSON
        private JsonNode parseJsonNode() {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode actualObj = null;
            try {
                actualObj = mapper.readTree(fileContent);
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (actualObj instanceof MissingNode) {
                actualObj = JsonNodeFactory.instance.arrayNode();
            }
            return actualObj;
        }

        // Méthode privée pour gérer la liste dans les fichiers CSV
        private void handleCsvList() {
            System.out.println(Arrays.stream(fileContent.split("\n"))
                    .map(todo -> "- " + todo)
                    .collect(Collectors.joining("\n"))
            );
        }
    }
}
