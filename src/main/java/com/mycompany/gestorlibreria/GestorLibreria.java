

package com.mycompany.gestorlibreria;


import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

public class GestorLibreria {
    private MongoDatabase database;
    private MongoCollection<Document> collection;

    public GestorLibreria() {
        MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017");
        database = mongoClient.getDatabase("libreria");
        collection = database.getCollection("libros");
    }

    public void agregarLibro(Libro libro) {
        Document doc = new Document("titulo", libro.getTitulo())
                .append("autor", libro.getAutor())
                .append("isbn", libro.getIsbn())
                .append("precio", libro.getPrecio());
        collection.insertOne(doc);
    }

    public void editarLibro(String isbn, Libro libro) {
        Document query = new Document("isbn", isbn);
        Document newDoc = new Document("$set", new Document("titulo", libro.getTitulo())
                .append("autor", libro.getAutor())
                .append("precio", libro.getPrecio()));
        collection.updateOne(query, newDoc);
    }

    public void eliminarLibro(String isbn) {
        Document query = new Document("isbn", isbn);
        collection.deleteOne(query);
    }

    public Libro buscarLibro(String isbn) {
        Document query = new Document("isbn", isbn);
        Document doc = collection.find(query).first();
        if (doc != null) {
            return new Libro(doc.getString("titulo"), doc.getString("autor"), doc.getString("isbn"), doc.getDouble("precio"));
        }
        return null;
    }

    public List<Libro> listarLibros() {
        List<Libro> libros = new ArrayList<>();
        for (Document doc : collection.find()) {
            libros.add(new Libro(doc.getString("titulo"), doc.getString("autor"), doc.getString("isbn"), doc.getDouble("precio")));
        }
        return libros;
    }
}