package com.fges.todoapp.cli.options;

/**
 * Classe gérant l'option de sortie dans l'application CLI de gestion de tâches.
 * Cette classe implémente l'interface {@link OptionHandler} pour gérer l'option
 * permettant de spécifier un fichier de sortie où les informations seront sauvegardées.
 */
public class OutputOptionHandler implements OptionHandler {
    // Chemin du fichier de sortie où les informations seront écrites.
    private String outputFile;

    /**
     * Traite la valeur de l'option spécifiée par l'utilisateur pour définir le fichier de sortie.
     * L'option passée en paramètre est supposée être le chemin du fichier où les données doivent être écrites.
     *
     * @param optionValue La valeur de l'option, ici le chemin vers le fichier de sortie.
     */
    @Override
    public void handleOption(String optionValue) {
        outputFile = optionValue;
    }

    /**
     * Retourne le chemin du fichier de sortie configuré par l'utilisateur.
     *
     * @return Le chemin du fichier de sortie.
     */
    public String getOutputFile() {
        return outputFile;
    }
}
