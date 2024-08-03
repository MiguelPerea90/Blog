package com.jmp.blog.controller;

import com.jmp.blog.database.Database;
import com.jmp.blog.model.Post;

import java.sql.SQLException;
import java.util.List;

public class PostController {

    public void createPost(String title, String content) {
        Post post = new Post(title, content);
        try {
            Database.createPost(post); // Guarda la publicación en la base de datos
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Post> getPosts() {
        try {
            return Database.getAllPosts(); // Recupera todas las publicaciones de la base de datos
        } catch (SQLException e) {
            e.printStackTrace();
            return List.of(); // Devuelve una lista vacía en caso de error
        }
    }
}
