package model.entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Livro implements Serializable {
    private int id;
    private String titulo;
    private Autor autor;
    private boolean disponivel;
    private LocalDate dataCadastro;
    private LocalDate dataAtualizacao;

    private static DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public Livro(int id, String titulo, Autor autor, boolean disponivel, String dataCadastro, String dataAtualizacao) {
        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
        this.disponivel = disponivel;
        this.dataCadastro = LocalDate.parse(dataCadastro, fmt);
        this.dataAtualizacao = LocalDate.parse(dataAtualizacao, fmt);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public boolean isDisponivel() {
        return disponivel;
    }

    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }

    public LocalDate getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(LocalDate dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public LocalDate getDataAtualizacao() {
        return dataAtualizacao;
    }

    public void setDataAtualizacao(LocalDate dataAtualizacao) {
        this.dataAtualizacao = dataAtualizacao;
    }

    @Override
    public String toString() {
        return id +
                ";"
                + titulo
                + ";"
                + autor
                + ";"
                + disponivel
                + ";"
                + dataCadastro.format(fmt)
                + ";"
                + dataAtualizacao.format(fmt);

    }

    public static Livro fromString(String str) {
        String[] parts = str.split(";");
        int id = Integer.parseInt(parts[0]);
        String titulo = parts[1];
        Autor autor = new Autor(parts[2]);
        boolean disponivel = Boolean.parseBoolean(parts[3]);
        String dataCadastro = parts[4];
        String dataAtualizacao = parts[5];
        return new Livro(id, titulo, autor, disponivel, dataCadastro, dataAtualizacao);
    }
}
