package application;

import model.entities.Autor;
import model.entities.Livro;
import model.utils.LivroManager;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in).useLocale(Locale.US);
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        Autor autor = new Autor("J.K. Rowling");
        List<Livro> livros = new ArrayList<>();


        livros.add(new Livro(1, "Harry Potter e a Pedra Filosofal", autor, true, "01/01/1997", "12/11/2023"));
        livros.add(new Livro(2, "Harry Potter e a Câmara Secreta", autor, true, "01/01/1998", "12/11/2023"));
        livros.add(new Livro(3, "Harry Potter e o Prisioneiro de Azkaban", autor, false, "01/01/1999", "12/11/2023"));
        livros.add(new Livro(4, "Harry Potter e o Cálice de Fogo", autor, true, "01/01/2000", "12/11/2023"));
        livros.add(new Livro(5, "Harry Potter e o Ordem da Fênix", autor, true, "01/01/2003", "12/11/2023"));
        livros.add(new Livro(6, "Harry Potter e o Enigma do Príncipe", autor, true, "01/01/2005", "12/11/2023"));
        livros.add(new Livro(7, "Harry Potter e as Relíquias da Mortes", autor, true, "01/01/2007", "12/11/2023"));


        System.out.println("-----* Biblioteca *-----");
        try {
            LivroManager.salvarLivros(livros);

            System.out.println("Você gostaria de ver os livros disponíveis? ");
            String resposta = scanner.next().toLowerCase();

            while(!resposta.equals("não")) {
                List<Livro> livrosNaBiblioteca = LivroManager.lerLivros();


                System.out.println("Livros disponíveis:");
                for (Livro livro : livrosNaBiblioteca) {
                    if(livro.isDisponivel()) {
                        System.out.printf("ID: %d | Título: %s, Autor: %s, Dísponivel: %s, Data de Cadastro: %s\n",
                                livro.getId(),
                                livro.getTitulo(),
                                livro.getAutor(),
                                livro.isDisponivel() ? "Sim" : "Não",
                                fmt.format(livro.getDataCadastro()));
                    }
                }

                System.out.print("Deseja pegar algum livro emprestado? ");
                resposta = scanner.next().toLowerCase();

                if(resposta.equals("sim")) {
                    System.out.println("Selecione o ID do livro que deseja pegar emprestado: ");
                    int id = scanner.nextInt();

                    Livro livroEmprestado =  LivroManager.emprestarLivro(livrosNaBiblioteca, id);

                    if(livroEmprestado != null) {
                        System.out.println("Livro Emprestado com sucesso! " + livroEmprestado.getTitulo());
                    }
                }

                System.out.println("Deseja continuar? ");
                resposta = scanner.next().toLowerCase();
            }

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        scanner.close();
    }

}
