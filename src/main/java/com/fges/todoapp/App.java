package com.fges.todoapp;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.MissingNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
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
        Options cliOptions = createCliOptions();
        CommandLineParser parser = new DefaultParser();

        CommandLine cmd;
        try {
            cmd = parser.parse(cliOptions, args);
        } catch (ParseException ex) {
            handleParseException(ex);
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
            boolean isDone = cmd.hasOption("d");
            handleInsertCommand(positionalArgs, fileName, fileContent, filePath, todoFileManager, isDone);
        }

        if (command.equals("list")) {
            boolean showDone = cmd.hasOption("d");
            handleListCommand(fileName, fileContent, todoFileManager, showDone);
        }

        System.err.println("Done.");
        return 0;
    }

    // Options de la ligne de commande
    private static Options createCliOptions() {
        Options cliOptions = new Options();
        cliOptions.addRequiredOption("s", "source", true, "File containing the todos");
        cliOptions.addOption("d", "done", false, "Show only done todos");
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
    private static void handleInsertCommand(List<String> positionalArgs, String fileName, String fileContent, Path filePath, TodoFileManager todoFileManager, boolean isDone) throws IOException {
        if (positionalArgs.size() < 2) {
            System.err.println("Missing TODO name");
            return;
        }

        String todo = positionalArgs.get(1);
        todoFileManager.insertTodo(todo, isDone);
    }

    // Gestion de la commande 'list'
    private static void handleListCommand(String fileName, String fileContent, TodoFileManager todoFileManager, boolean showDone) {
        todoFileManager.listTodos(showDone);
    }

    // Classe interne pour gérer les opérations liées aux fichiers de todos
    private static class TodoFileManager {
        private final Path filePath;
        private String fileContent;

        // Constructeur de TodoFileManager
        public TodoFileManager(Path filePath, String fileContent) {
            this.filePath = filePath;
            this.fileContent = fileContent;
        }

        // Méthode pour l'insertion de tâches dans le fichier
        public void insertTodo(String todo, boolean isDone) throws IOException {
            if (filePath.toString().endsWith(".json")) {
                // Insertion pour les fichiers JSON
                handleJsonInsert(todo, isDone);
            }
            // ... (autres formats de fichiers)
        }

        // Méthode privée pour gérer l'insertion dans les fichiers JSON
        private void handleJsonInsert(String todo, boolean isDone) throws IOException {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode actualObj = mapper.readTree(fileContent);
            if (actualObj instanceof MissingNode) {
                actualObj = JsonNodeFactory.instance.arrayNode();
            }

            if (actualObj instanceof ArrayNode arrayNode) {
                ObjectNode todoNode = JsonNodeFactory.instance.objectNode();
                todoNode.put("task", todo);
                todoNode.put("done", isDone);
                arrayNode.add(todoNode);
            }

            fileContent = actualObj.toString();
            Files.writeString(filePath, fileContent);
        }

        // Méthode privée pour gérer la liste dans les fichiers JSON avec l'option --done
        private void handleJsonList(boolean showDone) {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode actualObj = parseJsonNode();
            if (actualObj instanceof ArrayNode arrayNode) {
                arrayNode.forEach(node -> {
                    boolean isDone = node.get("done").asBoolean();
                    String task = node.get("task").asText();

                    if (showDone || (!showDone && !isDone)) {
                        System.out.println("- " + (isDone ? "[Done] " : "") + task);
                    }
                });
            }
        }

        // Méthode pour lister les tâches dans le fichier
        public void listTodos(boolean showDone) {
            if (filePath.toString().endsWith(".json")) {
                // Liste pour les fichiers JSON
                handleJsonList(showDone);
            }
            // ... (autres formats de fichiers)
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
    }
}
