package com.fges.todoapp.commands;

import com.fges.todoapp.commands.local.InsertCommand;
import com.fges.todoapp.commands.local.ListCommand;
import com.fges.todoapp.commands.local.MigrateCommand;
import com.fges.todoapp.commands.web.WebCommand;
import com.fges.todoapp.storage.StorageHandler;
import com.fges.todoapp.storage.files.FileDataAccessor;
import com.fges.todoapp.todo.TaskState;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

/**
 * Classe responsable de la création des différentes commandes de l'application.
 * Elle utilise un mappage entre les noms de commandes et les créateurs de commandes pour cela.
 */
public class CommandFactory {

    // Map associant chaque nom de commande à un créateur de commande correspondant.
    private static final Map<String, CommandCreator> commandMap = new HashMap<>();

    // Initialisation du map avec les créateurs de commandes correspondants à chaque commande.
    static {
        commandMap.put("insert", (fileHandler, filePath, outputFileHandler, outputPath, taskState) ->
                new InsertCommand(new FileDataAccessor(fileHandler, filePath), taskState));
        commandMap.put("list", (fileHandler, filePath, outputFileHandler, outputPath, taskState) ->
                new ListCommand(new FileDataAccessor(fileHandler, filePath), taskState));
        commandMap.put("migrate", (fileHandler, filePath, outputFileHandler, outputPath, taskState) ->
                new MigrateCommand(new FileDataAccessor(fileHandler, filePath), new FileDataAccessor(outputFileHandler, outputPath)));
        commandMap.put("web", (fileHandler, filePath, outputFileHandler, outputPath, taskState) ->
                new WebCommand(new FileDataAccessor(fileHandler, filePath)));
    }

    /**
     * Crée une instance de la commande spécifiée avec les paramètres donnés.
     *
     * @param commandName    Le nom de la commande à créer.
     * @param inputHandler   Le gestionnaire de stockage pour l'entrée.
     * @param filePath       Le chemin du fichier pour l'entrée.
     * @param outputHandler  Le gestionnaire de stockage pour la sortie.
     * @param outputPath     Le chemin du fichier pour la sortie.
     * @param taskState      L'état des tâches pour la commande.
     * @return Une instance de la commande spécifiée avec les paramètres donnés, ou null si la commande n'existe pas.
     */
    public static Command createCommand(String commandName, StorageHandler inputHandler, Path filePath, StorageHandler outputHandler, Path outputPath, TaskState taskState) {
        CommandCreator creator = commandMap.get(commandName);
        if (creator != null) {
            return creator.create(inputHandler, filePath, outputHandler, outputPath, taskState);
        }
        return null; // Retourne null si la commande spécifiée n'existe pas.
    }
}
