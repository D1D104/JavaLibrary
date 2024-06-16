import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Biblioteca biblioteca = new Biblioteca();
        Scanner scanner = new Scanner(System.in);
        try {
            biblioteca.carregarLivrosDeArquivo();
            biblioteca.carregarAutoresDeArquivo();
            while (true) {
                System.out.println("Gerenciamento de Biblioteca:");
                System.out.println("1. Adicionar Livro");
                System.out.println("2. Listar Livros");
                System.out.println("3. Atualizar Livro");
                System.out.println("4. Remover Livro");
                System.out.println("5. Listar Livros por Autor");
                System.out.println("6. Listar Autores");
                System.out.println("0. Sair");
                System.out.print("Escolha uma opção: ");
                int opcao = scanner.nextInt();
                scanner.nextLine();
                switch (opcao) {
                    case 1:
                        System.out.print("Digite o título do livro: ");
                        String titulo = scanner.nextLine();
                        System.out.print("Digite o nome do autor do livro: ");
                        String nomeAutor = scanner.nextLine();
                        Autor autorExistente = biblioteca.obterAutorPorNome(nomeAutor);
                        if (autorExistente == null) {
                            int codigoAutor = biblioteca.listarAutores().size() + 1; // Gerar código único para o autor
                            Autor novoAutor = new Autor(nomeAutor, codigoAutor);
                            biblioteca.adicionarAutor(novoAutor);
                            System.out.println("Novo autor adicionado: " + nomeAutor);
                        } else {
                            System.out.println("Autor existente: " + nomeAutor);
                        }
                        System.out.print("Digite o ISBN do livro: ");
                        String isbn = scanner.nextLine();
                        System.out.print("Digite o ano de publicação: ");
                        int anoPublicacao = scanner.nextInt();
                        scanner.nextLine();
                        biblioteca.adicionarLivro(new Livro(titulo, nomeAutor, isbn, anoPublicacao));
                        System.out.println("Livro adicionado com sucesso.");
                        break;
                    case 2:
                        List<Livro> livros = biblioteca.listarLivros();
                        System.out.println("Lista de Livros:");
                        livros.forEach(livro -> System.out.println("Título: " + livro.getTitulo() + ", Autor: " + livro.getAutor() + ", ISBN: " + livro.getIsbn() + ", Ano: " + livro.getAnoPublicacao()));
                        break;
                    case 3:
                        System.out.print("Digite o ISBN do livro a ser atualizado: ");
                        String isbnParaAtualizar = scanner.nextLine();
                        System.out.print("Digite o novo título do livro: ");
                        String novoTitulo = scanner.nextLine();
                        System.out.print("Digite o novo autor do livro: ");
                        String novoAutor = scanner.nextLine();
                        System.out.print("Digite o novo ano de publicação: ");
                        int novoAnoPublicacao = scanner.nextInt();
                        scanner.nextLine();
                        biblioteca.atualizarLivro(isbnParaAtualizar, new Livro(novoTitulo, novoAutor, isbnParaAtualizar, novoAnoPublicacao));
                        System.out.println("Livro atualizado com sucesso.");
                        break;
                    case 4:
                        System.out.print("Digite o ISBN do livro a ser removido: ");
                        String isbnParaRemover = scanner.nextLine();
                        biblioteca.removerLivro(isbnParaRemover);
                        System.out.println("Livro removido com sucesso.");
                        break;
                    case 5:
                        System.out.print("Digite o nome do autor: ");
                        String nomeAutorParaListar = scanner.nextLine();
                        List<Livro> livrosDoAutor = biblioteca.listarLivrosPorAutor(nomeAutorParaListar);
                        if (livrosDoAutor.isEmpty()) {
                            System.out.println("Nenhum livro encontrado para o autor: " + nomeAutorParaListar);
                        } else {
                            System.out.println("Livros do autor " + nomeAutorParaListar + ":");
                            livrosDoAutor.forEach(livro -> System.out.println("Título: " + livro.getTitulo() + ", Autor: " + livro.getAutor() + ", ISBN: " + livro.getIsbn() + ", Ano: " + livro.getAnoPublicacao()));
                        }
                        break;
                    case 6:
                        List<Autor> autores = biblioteca.listarAutores();
                        System.out.println("Lista de Autores:");
                        autores.forEach(autorListagem -> System.out.println("Nome: " + autorListagem.getNome() + ", Código: " + autorListagem.getCodigo()));
                        break;
                    case 0:
                        return;
                    default:
                        System.out.println("Opção inválida. Tente novamente.");
                }
            }
        } catch (IOException e) {
            System.err.println("Erro ao carregar ou salvar dados: " + e.getMessage());
        } catch (NoSuchElementException e) {
            System.err.println("Erro: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
}
