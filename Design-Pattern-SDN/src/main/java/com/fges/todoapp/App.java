// com.fges.todoapp.App.java
package com.fges.todoapp;

import com.fges.todoapp.cli.CommandLineProcessor;
import com.fges.todoapp.cli.CommandProcessor;
import com.fges.todoapp.commands.Command;
import com.fges.todoapp.commands.CommandFactory;
import com.fges.todoapp.storage.StorageHandler;
import com.fges.todoapp.storage.StorageFactory;
import org.apache.commons.cli.*;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Classe principale de l'application TodoApp.
 */
public class App {
    private static final StorageFactory fileRegistry = new StorageFactory();

    public static void main(String[] args) throws Exception {
        System.exit(exec(args));
    }

    /**
     * Exécute l'application avec les arguments de ligne de commande donnés.
     *
     * @param args Les arguments de la ligne de commande.
     * @return Le code de sortie de l'application.
     * @throws IOException Si une erreur d'E/S se produit lors de l'exécution de l'application.
     */
    public static int exec(String[] args) throws IOException {
        Options cliOptions = buildOptions();
        CommandLine cmd = CommandLineProcessor.parseCommandLine(args, cliOptions);
        if (cmd == null) return 1;

        // Traitement de la commande
        CommandProcessor commandProcessor = new CommandProcessor(cmd);
        commandProcessor.processCommand();

        // Récupération des gestionnaires de stockage
        StorageHandler inputHandler = fileRegistry.detectHandler(commandProcessor.getFileName());
        Path filePath = Paths.get(commandProcessor.getFileName());
        Path outputPath = commandProcessor.getOutputFile() != null ? Paths.get(commandProcessor.getOutputFile()) : null;
        StorageHandler outputHandler = outputPath != null ? fileRegistry.detectHandler(commandProcessor.getOutputFile()) : null;

        // Création et exécution de la commande
        String commandName = commandProcessor.getPositionalArgs().get(0);
        Command command = CommandFactory.createCommand(commandName, inputHandler, filePath, outputHandler, outputPath, commandProcessor.getTaskState());
        assert command != null;
        command.execute(commandProcessor.getPositionalArgs());

        System.err.println("Done.");
        return 0;
    }

    /**
     * Construit les options de la ligne de commande.
     *
     * @return Les options de la ligne de commande.
     */
    private static Options buildOptions() {
        Options cliOptions = new Options();
        cliOptions.addRequiredOption("s", "source", true, "File containing the todos");
        cliOptions.addOption("d", "done", false, "Mark a todo as done");
        cliOptions.addOption("o", "output", true, "The destination file");
        return cliOptions;
    }
}
