/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gestorlibreria;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class LibreriaGUI extends javax.swing.JFrame {
    private GestorLibreria gestor;

    public LibreriaGUI() {
        gestor = new GestorLibreria();
        initComponents();
    }

    private void initComponents() {
        // Inicializa los componentes aquí
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Gestión de Inventario de Librería");

        // Panel para los campos de entrada
        JPanel inputPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0;
        gbc.gridy = 0;
        inputPanel.add(new JLabel("Título:"), gbc);
        tituloField = new JTextField();
        gbc.gridx = 1;
        gbc.gridwidth = 2;
        inputPanel.add(tituloField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        inputPanel.add(new JLabel("Autor:"), gbc);
        autorField = new JTextField();
        gbc.gridx = 1;
        gbc.gridwidth = 2;
        inputPanel.add(autorField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        inputPanel.add(new JLabel("ISBN:"), gbc);
        isbnField = new JTextField();
        gbc.gridx = 1;
        gbc.gridwidth = 2;
        inputPanel.add(isbnField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        inputPanel.add(new JLabel("Precio:"), gbc);
        precioField = new JTextField();
        gbc.gridx = 1;
        gbc.gridwidth = 2;
        inputPanel.add(precioField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 1;
        inputPanel.add(new JLabel("Buscar:"), gbc);
        buscarField = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridwidth = 1;
        inputPanel.add(buscarField, gbc);

        searchButton = new JButton("Buscar");
        searchButton.setPreferredSize(new Dimension(100, 30));
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarLibro();
            }
        });
        gbc.gridx = 2;
        gbc.gridwidth = 1;
        inputPanel.add(searchButton, gbc);

        getContentPane().add(inputPanel, BorderLayout.NORTH);

        // Panel para los botones en el lado derecho
        JPanel buttonPanel = new JPanel(new GridLayout(4, 1, 5, 5));

        addButton = new JButton("Agregar");
        addButton.setPreferredSize(new Dimension(100, 30));
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                agregarLibro();
            }
        });
        buttonPanel.add(addButton);

        listButton = new JButton("Listar");
        listButton.setPreferredSize(new Dimension(100, 30));
        listButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listarLibros();
            }
        });
        buttonPanel.add(listButton);

        deleteButton = new JButton("Eliminar");
        deleteButton.setPreferredSize(new Dimension(100, 30));
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eliminarLibro();
            }
        });
        buttonPanel.add(deleteButton);

        updateButton = new JButton("Actualizar");
        updateButton.setPreferredSize(new Dimension(100, 30));
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actualizarLibro();
            }
        });
        buttonPanel.add(updateButton);

        getContentPane().add(buttonPanel, BorderLayout.EAST);

        outputArea = new JTextArea();
        outputArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        outputArea.setPreferredSize(new Dimension(400, 300));
        getContentPane().add(new JScrollPane(outputArea), BorderLayout.CENTER);

        pack();
    }

    private void agregarLibro() {
        String titulo = tituloField.getText();
        String autor = autorField.getText();
        String isbn = isbnField.getText();
        double precio = Double.parseDouble(precioField.getText());
        Libro libro = new Libro();
        libro.setTitulo(titulo);
        libro.setAutor(autor);
        libro.setIsbn(isbn);
        libro.setPrecio(precio);
        gestor.agregarLibro(libro);
        outputArea.setText("Libro agregado: " + libro.getTitulo());
    }

    private void listarLibros() {
        List<Libro> libros = gestor.listarLibros();
        outputArea.setText("");
        for (Libro libro : libros) {
            outputArea.append(libro.getTitulo() + " - " + libro.getAutor() + " - " + libro.getIsbn() + " - $"
                    + libro.getPrecio() + "\n");
        }
    }

    private void buscarLibro() {
        String busqueda = buscarField.getText();
        List<Libro> libros = gestor.listarLibros();
        for (Libro libro : libros) {
            if (libro.getTitulo().equalsIgnoreCase(busqueda) ||
                    libro.getAutor().equalsIgnoreCase(busqueda) ||
                    libro.getIsbn().equalsIgnoreCase(busqueda) ||
                    String.valueOf(libro.getPrecio()).equalsIgnoreCase(busqueda)) {
                tituloField.setText(libro.getTitulo());
                autorField.setText(libro.getAutor());
                isbnField.setText(libro.getIsbn());
                precioField.setText(String.valueOf(libro.getPrecio()));
                outputArea.setText("Libro encontrado:\n" + libro.getTitulo() + " - " + libro.getAutor() + " - "
                        + libro.getIsbn() + " - $" + libro.getPrecio());
                return;
            }
        }
        outputArea.setText("Libro no encontrado.");
    }

    private void eliminarLibro() {
        String isbn = isbnField.getText();
        gestor.eliminarLibro(isbn);
        outputArea.setText("Libro eliminado con ISBN: " + isbn);
    }

    private void actualizarLibro() {
        String titulo = tituloField.getText();
        String autor = autorField.getText();
        String isbn = isbnField.getText();
        double precio = Double.parseDouble(precioField.getText());
        Libro libro = new Libro();
        libro.setTitulo(titulo);
        libro.setAutor(autor);
        libro.setIsbn(isbn);
        libro.setPrecio(precio);
        gestor.editarLibro(isbn, libro);
        outputArea.setText("Libro actualizado: " + libro.getTitulo());
    }

    // Variables
    private javax.swing.JTextField tituloField;
    private javax.swing.JTextField autorField;
    private javax.swing.JTextField isbnField;
    private javax.swing.JTextField precioField;
    private javax.swing.JTextField buscarField;
    private javax.swing.JTextArea outputArea;
    private javax.swing.JButton addButton;
    private javax.swing.JButton listButton;
    private javax.swing.JButton searchButton;
    private javax.swing.JButton deleteButton;
    private javax.swing.JButton updateButton;
}