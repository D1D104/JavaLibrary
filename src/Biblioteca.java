import java.io.*;
import java.util.*;

public class Biblioteca {
    private List<Livro> livros;
    private List<Autor> autores;

    public Biblioteca() {
        livros = new ArrayList<>();
        autores = new ArrayList<>();
    }

    public void adicionarLivro(Livro livro) throws IOException {
        livros.add(livro);
    }

    public List<Livro> listarLivros() {
        return livros;
    }

    public List<Autor> listarAutores() {
        return autores;
    }

    public Autor obterAutorPorNome(String nome) {
        for (Autor autor : autores) {
            if (autor.getNome().equalsIgnoreCase(nome)) {
                return autor;
            }
        }
        return null;
    }
    public List<Livro> listarLivrosPorAutor(String nomeAutor) {
        List<Livro> livrosDoAutor = new ArrayList<>();
        for (Livro livro : livros) {
            if (livro.getAutor().equalsIgnoreCase(nomeAutor)) {
                livrosDoAutor.add(livro);
            }
        }
        return livrosDoAutor;
    }
}