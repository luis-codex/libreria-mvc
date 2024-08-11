package com.mycompany.gestorlibreria.cotrollers;

import java.util.List;

import com.mycompany.gestorlibreria.models.GestorLibreria;
import com.mycompany.gestorlibreria.models.Libro;

public class LibroController {
    private GestorLibreria gestorLibreria;

    public LibroController() {
        this.gestorLibreria = new GestorLibreria();
    }

    public void agregarLibro(Libro libro) {
        gestorLibreria.agregarLibro(libro);
    }

    public void editarLibro(String isbn, Libro libro) {
        gestorLibreria.editarLibro(isbn, libro);
    }

    public void eliminarLibro(String isbn) {
        gestorLibreria.eliminarLibro(isbn);
    }

    public Libro buscarLibro(String isbn) {
        return gestorLibreria.buscarLibro(isbn);
    }

    public List<Libro> listarLibros() {
        return gestorLibreria.listarLibros();
    }

}
