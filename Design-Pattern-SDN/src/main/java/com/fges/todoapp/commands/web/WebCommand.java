package com.fges.todoapp.commands.web;

import com.fges.todoapp.commands.Command;
import com.fges.todoapp.storage.DataAccessor;
import com.fges.todoapp.todo.TodoProvider;
import com.fges.todoapp.util.ServerRunner;

import java.io.IOException;
import java.util.List;

/**
 * Commande pour démarrer un serveur web et exposer les tâches via une interface web.
 * Cette commande utilise un fournisseur de tâches (TodoProvider) pour accéder aux données
 * et un serveur (ServerRunner) pour démarrer le service web.
 */
public class WebCommand implements Command {
    private final TodoProvider provider; // Fournisseur de tâches pour accéder aux données.

    /**
     * Constructeur de la commande web.
     * Initialise le fournisseur de tâches avec l'accesseur de données fourni.
     *
     * @param dataAccessor L'accesseur de données pour lire et écrire les tâches.
     */
    public WebCommand(DataAccessor dataAccessor) {
        this.provider = new TodoProvider(dataAccessor); // Initialise le fournisseur avec l'accesseur de données.
    }

    /**
     * Exécute la commande web en démarrant le serveur web.
     * Le serveur exposera les tâches via une interface web sur le port spécifié.
     *
     * @param positionalArgs Les arguments positionnels de la commande, non utilisés dans cette implémentation.
     * @return 0 indiquant que le serveur a été démarré avec succès.
     * @throws IOException Si une erreur se produit lors du démarrage du serveur.
     */
    @Override
    public int execute(List<String> positionalArgs) throws IOException {
        // Démarrage du serveur sur le port 8888 avec un contexte 'todo' pour l'API web.
        new ServerRunner(8888, "todo").startServer(this.provider);
        return 0; // Retourne 0 pour indiquer le succès du démarrage du serveur.
    }
}
