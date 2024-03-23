package com.fges.todoapp.commands.local;

import com.fges.todoapp.commands.Command;
import com.fges.todoapp.storage.DataAccessor;
import com.fges.todoapp.todo.Todo;

import java.io.IOException;
import java.util.List;

/**
 * Commande pour migrer des tâches d'une source de données à une autre.
 * Cette commande lit les tâches depuis une source spécifiée et les écrit dans une destination spécifiée,
 * facilitant ainsi la migration des données entre différents supports ou formats de stockage.
 */
public class MigrateCommand implements Command {
    private final DataAccessor readFileAccessor; // Accesseur de données pour lire les tâches.
    private final DataAccessor writeFileAccessor; // Accesseur de données pour écrire les tâches.

    /**
     * Constructeur de la commande de migration.
     *
     * @param readFileAccessor L'accesseur de données pour lire les tâches à migrer.
     * @param writeFileAccessor L'accesseur de données pour écrire les tâches migrées.
     */
    public MigrateCommand(DataAccessor readFileAccessor, DataAccessor writeFileAccessor) {
        this.readFileAccessor = readFileAccessor;
        this.writeFileAccessor = writeFileAccessor;
    }

    /**
     * Exécute la commande de migration en lisant les tâches depuis la source
     * et en les écrivant dans la destination.
     *
     * @param positionalArgs Les arguments positionnels de la commande, non utilisés dans cette implémentation.
     * @return 0 indiquant que la migration a été effectuée avec succès.
     * @throws IOException Si une erreur d'entrée/sortie se produit lors de la lecture ou de l'écriture des tâches.
     */
    @Override
    public int execute(List<String> positionalArgs) throws IOException {
        List<Todo> todos = this.readFileAccessor.read(); // Lit les tâches depuis la source.
        for(Todo todo : todos) {
            this.writeFileAccessor.insert(todo); // Écrit chaque tâche dans la destination.
        }
        return 0; // Retourne 0 pour indiquer le succès de l'opération.
    }
}
