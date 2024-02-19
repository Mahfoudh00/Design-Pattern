// App.java

package com.fges.todoapp;

import org.apache.commons.cli.*;

import java.io.IOException;

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

        // Créer le gestionnaire de tâches en fonction du type de fichier
        TodoManager todoManager = createTodoManager(fileName);

        // Récupérer la commande à partir des arguments
        String command = cmd.getArgList().get(0);

        // Gérer la commande en fonction de son type
        if (command.equals("insert")) {
            // Vérifier si l'option --done est présente
            boolean isDone = cmd.hasOption("d");
            // Gérer la commande 'insert'
            handleInsertCommand(cmd.getArgs(), todoManager, isDone);
        } else if (command.equals("list")) {
            // Vérifier si l'option --done est présente
            boolean showDone = cmd.hasOption("d");
            // Gérer la commande 'list'
            handleListCommand(todoManager, showDone);
        } else if (command.equals("migrate")) {
            // Récupérer les noms des fichiers source et de sortie
            String sourceFileName = cmd.getOptionValue("source");
            String outputFileName = cmd.getOptionValue("output");
            // Gérer la commande 'migrate'
            handleMigrateCommand(sourceFileName, outputFileName);
        } else {
            // Commande inconnue
            System.err.println("Commande inconnue");
            return 1;
        }

        // Afficher un message indiquant que l'opération est terminée
        System.err.println("Terminé.");
        return 0;
    }

    // Créer les options de la ligne de commande
    private static Options createCliOptions() {
        Options cliOptions = new Options();
        cliOptions.addRequiredOption("s", "source", true, "Fichier contenant les tâches");
        cliOptions.addOption("d", "done", false, "Afficher uniquement les tâches terminées");
        cliOptions.addOption("o", "output", true, "Fichier de sortie pour la commande migrate");
        return cliOptions;
    }

    // Gérer les exceptions lors de l'analyse des arguments
    private static void handleParseException(ParseException ex) {
        System.err.println("Échec de l'analyse des arguments : " + ex.getMessage());
    }

    // Créer le gestionnaire de tâches en fonction du type de fichier
    private static TodoManager createTodoManager(String fileName) {
        if (fileName.endsWith(".json")) {
            return new JsonTodoManager(fileName);
        } else if (fileName.endsWith(".csv")) {
            return new CsvTodoManager(fileName);
        } else {
            throw new IllegalArgumentException("Format de fichier non pris en charge");
        }
    }

    // Gérer la commande 'insert'
    private static void handleInsertCommand(String[] args, TodoManager todoManager, boolean isDone) throws IOException {
        // Gérer la commande 'insert'
        if (args.length < 3) {
            System.err.println("Nom de tâche manquant");
            return;
        }

        // Récupérer le nom de la tâche à partir des arguments
        String todo = args[1];
        // Insérer la tâche avec l'option --done
        todoManager.insertTodo(todo, isDone);
    }

    // Gérer la commande 'list'
    private static void handleListCommand(TodoManager todoManager, boolean showDone) {
        // Gérer la commande 'list'
        todoManager.listTodos(showDone);
    }

    // Gérer la commande 'migrate'
    private static void handleMigrateCommand(String sourceFileName, String outputFileName) throws IOException {
        // Créer le gestionnaire de tâches pour le fichier source et de sortie
        TodoManager sourceManager = createTodoManager(sourceFileName);
        TodoManager outputManager = createTodoManager(outputFileName);

        // Migrer les tâches du fichier source vers le fichier de sortie
        outputManager.migrateFrom(sourceManager);
    }
}
