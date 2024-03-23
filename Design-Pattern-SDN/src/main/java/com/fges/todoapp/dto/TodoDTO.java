package com.fges.todoapp.dto;

import java.util.Objects;

/**
 * Classe représentant un objet de transfert de données (DTO) pour les tâches.
 * Cette classe est utilisée pour encapsuler les informations sur une tâche
 * afin de faciliter leur transfert entre les différentes parties de l'application.
 */
public class TodoDTO {
    private String content; // Contenu de la tâche.
    private TaskState status; // État de la tâche.

    /**
     * Constructeur par défaut.
     */
    public TodoDTO() {}

    /**
     * Constructeur avec paramètres pour initialiser le contenu et l'état de la tâche.
     *
     * @param content Le contenu de la tâche.
     * @param status L'état de la tâche.
     */
    public TodoDTO(String content, TaskState status) {
        this.content = content;
        this.status = status;
    }

    /**
     * Getter pour le contenu de la tâche.
     *
     * @return Le contenu de la tâche.
     */
    public String getContent() {
        return content;
    }

    /**
     * Setter pour le contenu de la tâche.
     *
     * @param content Le nouveau contenu de la tâche.
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * Getter pour l'état de la tâche.
     *
     * @return L'état de la tâche.
     */
    public TaskState getStatus() {
        return status;
    }

    /**
     * Setter pour l'état de la tâche.
     *
     * @param status Le nouvel état de la tâche.
     */
    public void setStatus(TaskState status) {
        this.status = status;
    }

    /**
     * Redéfinition de la méthode equals pour comparer deux objets TodoDTO.
     *
     * @param o L'objet à comparer.
     * @return true si les objets sont égaux, false sinon.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TodoDTO todoDTO = (TodoDTO) o;
        return Objects.equals(content, todoDTO.content) && status == todoDTO.status;
    }

    /**
     * Redéfinition de la méthode hashCode pour générer un code de hachage unique pour chaque objet TodoDTO.
     *
     * @return Le code de hachage de l'objet.
     */
    @Override
    public int hashCode() {
        return Objects.hash(content, status);
    }
}
