import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) throws IOException {
        Biblioteca biblioteca = new Biblioteca();
        Livro livro = new Livro("Harry Potter", "JK. Rowling", "123", 2002);

        biblioteca.adicionarLivro(livro);
        List<Livro> teste = biblioteca.listarLivros();
    }
}
