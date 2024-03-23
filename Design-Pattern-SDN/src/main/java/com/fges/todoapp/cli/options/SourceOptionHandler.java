package com.fges.todoapp.cli.options;

/**
 * Classe gérant l'option de source dans l'application CLI de gestion de tâches.
 * Cette classe implémente l'interface {@link OptionHandler} pour traiter l'option
 * permettant de spécifier un fichier source à partir duquel des informations peuvent être lues.
 */
public class SourceOptionHandler implements OptionHandler {
    // Chemin du fichier source à partir duquel les données seront lues.
    private String fileName;

    /**
     * Traite la valeur de l'option spécifiée par l'utilisateur pour définir le fichier source.
     * L'option passée en paramètre est supposée être le chemin du fichier source à lire.
     *
     * @param optionValue La valeur de l'option, ici le chemin vers le fichier source.
     */
    @Override
    public void handleOption(String optionValue) {
        fileName = optionValue;
    }

    /**
     * Retourne le chemin du fichier source configuré par l'utilisateur.
     * Cette méthode permet d'accéder au chemin du fichier spécifié pour la lecture des données.
     *
     * @return Le chemin du fichier source.
     */
    public String getFileName() {
        return fileName;
    }
}
