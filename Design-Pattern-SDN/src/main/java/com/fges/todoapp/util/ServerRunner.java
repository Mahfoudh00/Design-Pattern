package com.fges.todoapp.util;

import com.fges.todoapp.dto.TodoDTO;
import com.fges.todoapp.todo.TodoProvider;
import fr.anthonyquere.dumbcrud.DummyCrudEndpoint;

import java.io.IOException;
import java.util.Scanner;

/**
 * Classe pour exécuter un serveur CRUD simple.
 */
public class ServerRunner {
    private final int port;
    private final String domainName;

    /**
     * Constructeur pour initialiser le port et le nom de domaine.
     *
     * @param port       Le port sur lequel démarrer le serveur.
     * @param domainName Le nom de domaine pour le serveur.
     */
    public ServerRunner(int port, String domainName) {
        this.port = port;
        this.domainName = domainName;
    }

    /**
     * Méthode pour démarrer le serveur.
     *
     * @param provider Le fournisseur de tâches à utiliser avec le serveur.
     * @throws IOException Si une erreur d'E/S se produit lors du démarrage du serveur.
     */
    public void startServer(TodoProvider provider) throws IOException {
        boolean stopRequested = false;
        Scanner scanner = new Scanner(System.in);

        // Lancement du serveur CRUD
        DummyCrudEndpoint<TodoDTO> endpoint = new DummyCrudEndpoint<>(this.domainName, provider, TodoDTO.class);
        endpoint.run(port);
        System.err.println("Serveur démarré sur le port " + port + ".");
        System.err.println("'stop' pour arrêter le serveur");

        // Attendre une commande pour arrêter le serveur
        while (!stopRequested) {
            String input = scanner.nextLine();
            if (input.equals("stop")) {
                stopRequested = true;
            }
        }

        System.err.println("Serveur arrêté.");
    }
}
