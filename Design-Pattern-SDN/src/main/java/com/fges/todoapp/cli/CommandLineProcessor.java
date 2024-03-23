package com.fges.todoapp.cli;

import org.apache.commons.cli.*;

/**
 * Classe utilitaire pour analyser les arguments de la ligne de commande.
 * Utilise la bibliothèque Apache Commons CLI pour faciliter l'analyse et la gestion
 * des options de ligne de commande dans l'application.
 */
public class CommandLineProcessor {

    /**
     * Analyse les arguments de la ligne de commande fournis à l'application.
     * Cette méthode statique utilise la bibliothèque Apache Commons CLI pour analyser
     * les arguments en fonction des options définies et retourne un objet CommandLine
     * qui facilite l'accès aux options spécifiées par l'utilisateur.
     *
     * @param args Les arguments de la ligne de commande passés à l'application.
     * @param options Les options de ligne de commande définies pour l'application.
     * @return Un objet CommandLine représentant les options de ligne de commande spécifiées,
     *         ou null si l'analyse échoue à cause d'une erreur de format ou d'options inconnues.
     */
    public static CommandLine parseCommandLine(String[] args, Options options) {
        CommandLineParser parser = new DefaultParser();
        try {
            // Tente d'analyser les arguments en fonction des options fournies.
            return parser.parse(options, args);
        } catch (ParseException ex) {
            // Affiche un message d'erreur en cas d'échec de l'analyse et retourne null.
            System.err.println("Fail to parse arguments: " + ex.getMessage());
            return null;
        }
    }
}
