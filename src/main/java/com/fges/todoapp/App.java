package com.fges.todoapp;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.apache.commons.cli.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class App {

    public static void main(String[] args) throws Exception {
        System.exit(exec(args));
    }

    // Fonction principale pour l'exécution des commandes
    public static int exec(String[] args) throws IOException {
        // Créer les options de la ligne de commande
        Options cliOptions = createCliOptions();
        // Initialiser le parseur de ligne de commande
        CommandLineParser parser = new DefaultParser();

        CommandLine cmd;
        try {
            // Parser les arguments de la ligne de commande
            cmd = parser.parse(cliOptions, args);
        } catch (ParseException ex) {
            // Gestion des erreurs liées à la ligne de commande
            handleParseException(ex);
            return 1;
        }

        // Récupérer le nom du fichier à partir des options
        String fileName = cmd.getOptionValue("s");

        // Récupérer les arguments positionnels
        List<String> positionalArgs = cmd.getArgList();
        if (positionalArgs.isEmpty()) {
            System.err.println("Missing Command");
            return 1;
        }

        // Récupérer la commande à partir des arguments
        String command = positionalArgs.get(0);

        // Créer le chemin du fichier à partir du nom du fichier
        Path filePath = Paths.get(fileName);

        // Lire le contenu du fichier
        String fileContent = readFileContent(filePath);

        // Initialiser le gestionnaire de tâches
        TodoFileManager todoFileManager = new TodoFileManager(filePath, fileContent);

        if (command.equals("insert")) {
            // Vérifier si l'option --done est présente
            boolean isDone = cmd.hasOption("d");
            // Gérer la commande 'insert'
            handleInsertCommand(positionalArgs, todoFileManager, isDone);
        }

        if (command.equals("list")) {
            // Vérifier si l'option --done est présente
            boolean showDone = cmd.hasOption("d");
            // Gérer la commande 'list'
            handleListCommand(todoFileManager, showDone);
        }

        System.err.println("Done.");
        return 0;
    }

    // Créer les options de la ligne de commande
    private static Options createCliOptions() {
        Options cliOptions = new Options();
        cliOptions.addRequiredOption("s", "source", true, "File containing the todos");
        cliOptions.addOption("d", "done", false, "Show only done todos");
        return cliOptions;
    }

    // Gérer les exceptions lors de l'analyse des arguments
    private static void handleParseException(ParseException ex) {
        System.err.println("Fail to parse arguments: " + ex.getMessage());
    }

    // Lire le contenu du fichier
    private static String readFileContent(Path filePath) throws IOException {
        String fileContent = "";
        if (Files.exists(filePath)) {
            fileContent = Files.readString(filePath);
        }
        return fileContent;
    }

    // Gérer la commande 'insert'
    private static void handleInsertCommand(List<String> positionalArgs, TodoFileManager todoFileManager, boolean isDone) throws IOException {
        if (positionalArgs.size() < 2) {
            System.err.println("Missing TODO name");
            return;
        }

        String todo = positionalArgs.get(1);
        todoFileManager.insertTodo(todo, isDone);
    }

    // Gérer la commande 'list'
    private static void handleListCommand(TodoFileManager todoFileManager, boolean showDone) {
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
            JsonNode actualObj = new ObjectMapper().readTree(fileContent);
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
            JsonNode actualObj = parseJsonNode();
            if (actualObj instanceof ArrayNode arrayNode) {
                arrayNode.forEach(node -> {
                    boolean isDone = node.get("done").asBoolean();
                    String task = node.get("task").asText();

                    if (showDone && isDone) {
                        System.out.println("- [Done] " + task);
                    } else if (!showDone && !isDone) {
                        System.out.println("- " + task);
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
            try {
                return new ObjectMapper().readTree(fileContent);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return JsonNodeFactory.instance.arrayNode(); // Par défaut, retourne un tableau JSON vide en cas d'erreur
        }
    }
}
