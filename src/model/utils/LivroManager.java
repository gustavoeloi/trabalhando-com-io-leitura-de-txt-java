package model.utils;

import model.entities.Livro;
import model.exceptions.BookReservationException;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class LivroManager {

    private static final String FILE_PATH = "livros.txt";

    public static void salvarLivros(List<Livro> livros) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for(Livro livro : livros) {
                writer.write(livro.toString());
                writer.newLine();
            }
        }
    }

    public static List<Livro> lerLivros() throws IOException {
        List<Livro> livros = new ArrayList<>();
       try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
           String line;
           while((line = reader.readLine()) != null ) {
               livros.add(Livro.fromString(line));
           }
       }

       return livros;
    }

    public static Livro emprestarLivro(List<Livro> livros, int id) throws IOException {
        Livro livroEmprestado = null;

        for(Livro livro : livros) {
            if(livro.getId() == id ){
                if(livro.isDisponivel()) {
                    livro.setDisponivel(false);
                    livroEmprestado = livro;
                    break;
                } else {
                    throw new BookReservationException("O Livro " + livro.getTitulo() + " já está emprestado.");
                }
            }
        }

        if(livroEmprestado == null) {
            throw new BookReservationException("Livro não encontrado ou não disponível!");
        }
        salvarLivros(livros);
        return livroEmprestado;
    }

}
