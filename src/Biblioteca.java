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
        salvarLivrosEmArquivo();
    }

    public List<Livro> listarLivros() {
        return livros;
    }
    public void atualizarLivro(String isbn, Livro livroAtualizado) throws IOException {
        for (Livro livro : livros) {
            if (livro.getIsbn().equals(isbn)) {
                livro.setTitulo(livroAtualizado.getTitulo());
                livro.setAutor(livroAtualizado.getAutor());
                livro.setAnoPublicacao(livroAtualizado.getAnoPublicacao());
                salvarLivrosEmArquivo();
                return;
            }
        }
        throw new NoSuchElementException("Livro não encontrado");
    }

    public void removerLivro(String isbn) throws IOException {
        Livro livroRemovido = null;
        for (Livro livro : livros) {
            if (livro.getIsbn().equals(isbn)) {
                livroRemovido = livro;
                break;
            }
        }
        if (livroRemovido != null) {
            livros.remove(livroRemovido);
            salvarLivrosEmArquivo();
            String nomeAutor = livroRemovido.getAutor();
            if (listarLivrosPorAutor(nomeAutor).isEmpty()) {
                removerAutor(nomeAutor);
            }
        } else {
            throw new NoSuchElementException("Livro não encontrado");
        }
    }

    // Métodos CRUD para Autor
    public void adicionarAutor(Autor autor) throws IOException {
        autores.add(autor);
        salvarAutoresEmArquivo();
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
    public void atualizarAutor(String nome, Autor autorAtualizado) throws IOException {
        for (Autor autor : autores) {
            if (autor.getNome().equals(nome)) {
                autor.setNome(autorAtualizado.getNome());
                salvarAutoresEmArquivo();
                return;
            }
        }
        throw new NoSuchElementException("Autor não encontrado");
    }

    public void removerAutor(String nome) throws IOException {
        autores.removeIf(autor -> autor.getNome().equalsIgnoreCase(nome));
        salvarAutoresEmArquivo();
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
    private void salvarLivrosEmArquivo() throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("livros.txt"))) {
            for (Livro livro : livros) {
                writer.write(livro.getTitulo() + "," + livro.getAutor() + "," + livro.getIsbn() + "," + livro.getAnoPublicacao());
                writer.newLine();
            }
        }
    }

    public void carregarLivrosDeArquivo() throws IOException {
        livros.clear();
        File file = new File("livros.txt");
        if (file.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String linha;
                while ((linha = reader.readLine()) != null) {
                    String[] dados = linha.split(",");
                    Livro livro = new Livro(dados[0], dados[1], dados[2], Integer.parseInt(dados[3]));
                    livros.add(livro);
                }
            }
        }
    }

    private void salvarAutoresEmArquivo() throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("autores.txt"))) {
            for (Autor autor : autores) {
                writer.write(autor.getNome() + "," + autor.getCodigo());
                writer.newLine();
            }
        }
    }

    public void carregarAutoresDeArquivo() throws IOException {
        autores.clear();
        File file = new File("autores.txt");
        if (file.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String linha;
                while ((linha = reader.readLine()) != null) {
                    String[] dados = linha.split(",");
                    Autor autor = new Autor(dados[0], Integer.parseInt(dados[1]));
                    autores.add(autor);
                }
            }
        }
    }
}