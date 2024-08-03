package com.jmp.blog.model;

public class Post {
    private int id;
    private String title;   // Título de la publicación
    private String content; // Contenido de la publicación

    // Constructor sin el id, ya que el id será generado automáticamente
    public Post(String title, String content) {
        this.title = title;
        this.content = content;
    }

    // Constructor sin parámetros
    public Post() {}

    // Getter y Setter para el id
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    // Getter y Setter para el título
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    // Getter y Setter para el contenido
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}


