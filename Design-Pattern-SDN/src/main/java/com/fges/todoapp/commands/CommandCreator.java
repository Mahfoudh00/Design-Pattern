package com.fges.todoapp.commands;

import com.fges.todoapp.storage.StorageHandler;
import com.fges.todoapp.todo.TaskState;

import java.nio.file.Path;

/**
 * Interface pour la création d'objets Command.
 * Cette interface définit une méthode de création qui permet de configurer des commandes
 * avec les ressources nécessaires pour leur exécution, comme les gestionnaires de stockage
 * pour l'entrée et la sortie, les chemins de fichiers concernés, et l'état des tâches à manipuler.
 */
public interface CommandCreator {

    /**
     * Crée une instance de la commande configurée avec les paramètres spécifiés.
     *
     * @param inputHandler Le gestionnaire de stockage pour lire les données.
     * @param filePath Le chemin du fichier d'entrée d'où lire les données.
     * @param outputHandler Le gestionnaire de stockage pour écrire les données.
     * @param outputPath Le chemin du fichier de sortie où écrire les données.
     * @param taskState L'état des tâches concernées par la commande.
     * @return Une instance de Command configurée avec les paramètres fournis.
     */
    Command create(StorageHandler inputHandler, Path filePath, StorageHandler outputHandler, Path outputPath, TaskState taskState);
}
