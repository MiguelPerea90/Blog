package com.jmp.blog.server;

import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.List;
import java.io.InputStream;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jmp.blog.database.Database;
import com.jmp.blog.model.Post;

public class SimpleHttpServer {

    public static void main(String[] args) throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);
        server.createContext("/api/posts", new PostHandler());
        server.setExecutor(null); // Crea un ejecutor por defecto
        server.start(); // Inicia el servidor
        System.out.println("Server started on port 8000");
    }

    static class PostHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            String method = exchange.getRequestMethod();
            ObjectMapper mapper = new ObjectMapper();

            if ("GET".equals(method)) {
                try {
                    List<Post> posts = Database.getAllPosts();
                    String response = mapper.writeValueAsString(posts);
                    exchange.sendResponseHeaders(200, response.getBytes().length);
                    OutputStream os = exchange.getResponseBody();
                    os.write(response.getBytes());
                    os.close();
                } catch (Exception e) {
                    e.printStackTrace();
                    exchange.sendResponseHeaders(500, -1); // Error interno del servidor
                }
            } else if ("POST".equals(method)) {
                try {
                    InputStream is = exchange.getRequestBody();
                    Post post = mapper.readValue(is, Post.class);
                    Database.createPost(post);
                    String response = "Post created";
                    exchange.sendResponseHeaders(201, response.getBytes().length);
                    OutputStream os = exchange.getResponseBody();
                    os.write(response.getBytes());
                    os.close();
                } catch (Exception e) {
                    e.printStackTrace();
                    exchange.sendResponseHeaders(500, -1); // Error interno del servidor
                }
            } else {
                exchange.sendResponseHeaders(405, -1); // Método no permitido
            }
        }
    }
}
