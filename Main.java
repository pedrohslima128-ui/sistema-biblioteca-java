import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final String NOME_FICHEIRO = "biblioteca_dados.txt";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Livro> livros = new ArrayList<>();
        int opcao;

        do {
            System.out.println("\n=== 📚 SISTEMA DE GESTÃO DE BIBLIOTECA ===");
            System.out.println("1. Cadastrar Novo Livro");
            System.out.println("2. Listar Livros");
            System.out.println("3. Emprestar Livro");
            System.out.println("4. Devolver Livro");
            System.out.println("5. Guardar Dados em Ficheiro");
            System.out.println("6. Sair");
            System.out.print("Escolha uma opção: ");

            opcao = scanner.nextInt();
            scanner.nextLine(); // Limpar o buffer

            switch (opcao) {
                case 1:
                    System.out.print("Digite o título do livro: ");
                    String titulo = scanner.nextLine();
                    System.out.print("Digite o autor do livro: ");
                    String autor = scanner.nextLine();
                    livros.add(new Livro(titulo, autor));
                    System.out.println("✅ Livro cadastrado com sucesso!");
                    break;

                case 2:
                    System.out.println("\n--- CATÁLOGO DE LIVROS ---");
                    if (livros.isEmpty()) {
                        System.out.println("Nenhum livro registado.");
                    } else {
                        for (int i = 0; i < livros.size(); i++) {
                            System.out.println((i + 1) + ". " + livros.get(i));
                        }
                    }
                    break;

                case 3:
                    System.out.println("\n--- EMPRESTAR LIVRO ---");
                    listarDisponiveis(livros);
                    System.out.print("Digite o número do livro que deseja emprestar: ");
                    int idxEmp = scanner.nextInt() - 1;
                    if (idxEmp >= 0 && idxEmp < livros.size()) {
                        if (livros.get(idxEmp).isDisponivel()) {
                            livros.get(idxEmp).emprestar();
                            System.out.println("📖 Livro emprestado com sucesso!");
                        } else {
                            System.out.println("❌ Este livro já se encontra emprestado.");
                        }
                    } else {
                        System.out.println("❌ Número inválido!");
                    }
                    break;

                case 4:
                    System.out.println("\n--- DEVOLVER LIVRO ---");
                    System.out.print("Digite o número do livro que deseja devolver: ");
                    int idxDev = scanner.nextInt() - 1;
                    if (idxDev >= 0 && idxDev < livros.size()) {
                        if (!livros.get(idxDev).isDisponivel()) {
                            livros.get(idxDev).devolver();
                            System.out.println("🔄 Livro devolvido com sucesso!");
                        } else {
                            System.out.println("⚠️ Este livro já estava disponível.");
                        }
                    } else {
                        System.out.println("❌ Número inválido!");
                    }
                    break;

                case 5:
                    salvarDadosNoFicheiro(livros);
                    break;

                case 6:
                    System.out.println("A encerrar o sistema... Até logo!");
                    break;

                default:
                    System.out.println("❌ Opção inválida!");
            }
        } while (opcao != 6);

        scanner.close();
    }

    private static void listarDisponiveis(List<Livro> livros) {
        for (int i = 0; i < livros.size(); i++) {
            System.out.println((i + 1) + ". " + livros.get(i));
        }
    }

    private static void salvarDadosNoFicheiro(List<Livro> livros) {
        try (FileWriter writer = new FileWriter(NOME_FICHEIRO)) {
            for (Livro livro : livros) {
                writer.write(livro.getTitulo() + ";" + livro.getAutor() + ";" + livro.isDisponivel() + "\n");
            }
            System.out.println("💾 Dados salvos com sucesso no ficheiro '" + NOME_FICHEIRO + "'!");
        } catch (IOException e) {
            System.out.println("❌ Erro ao salvar o ficheiro: " + e.getMessage());
        }
    }
}