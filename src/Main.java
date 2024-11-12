import cliente.AddCliente;
import cliente.AtualizarCliente;
import cliente.DeletarCliente;
import cliente.ListarClientes;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Scanner;
import pedido.AddPedido;
import pedido.AtualizarPedido;
import pedido.DeletarPedido;
import pedido.ListarPedidos;
import produtos.AddProduto;
import produtos.AtualizarProduto;
import produtos.DeletarProduto;
import produtos.ListarProdutos;

public class Main {
    public static void main(String[] args) throws SQLException, ParseException {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Bem-vindo ao sistema da LogoLogo");

        System.out.print("Digite o seu usuário: ");
        String usuario = scanner.nextLine();

        System.out.print("Digite a sua senha: ");
        String senha = scanner.nextLine();

        if ("admin".equals(usuario) && "1234".equals(senha)) {
            System.out.println("Login bem-sucedido!");
            menuPrincipal(scanner);
        } else {
            System.out.println("Usuário ou senha incorretos!");
        }
    }

    public static void menuPrincipal(Scanner scanner) throws SQLException, ParseException {
        while (true) {
            System.out.println("\nMENU PRINCIPAL - Escolha uma entidade:");
            System.out.println("1- Produtos");
            System.out.println("2- Clientes");
            System.out.println("3- Pedidos");
            System.out.println("4- Sair");
            int opcaoEntidade = scanner.nextInt();
            scanner.nextLine(); 

            switch (opcaoEntidade) {
                case 1:
                    menuProdutos(scanner);
                    break;
                case 2:
                    menuClientes(scanner);
                    break;
                case 3:
                    menuPedidos(scanner);
                    break;
                case 4:
                    System.out.println("Saindo do sistema...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Opção inválida! Tente novamente.");
            }
        }
    }

    public static void menuProdutos(Scanner scanner) throws SQLException {
        while (true) {
            System.out.println("\nMENU PRODUTOS - Escolha uma opção:");
            System.out.println("1- Listar produtos");
            System.out.println("2- Adicionar produto");
            System.out.println("3- Atualizar produto");
            System.out.println("4- Deletar produto");
            System.out.println("5- Voltar ao menu principal");
            int opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    ListarProdutos.main(new String[]{});
                    break;
                case 2:
                    AddProduto.main(new String[]{});
                    break;
                case 3:
                    AtualizarProduto.main(new String[]{});
                    break;
                case 4:
                    DeletarProduto.main(new String[]{});
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Opção inválida! Tente novamente.");
            }
        }
    }

    public static void menuClientes(Scanner scanner) throws SQLException {
        while (true) {
            System.out.println("\nMENU CLIENTES - Escolha uma opção:");
            System.out.println("1- Listar clientes");
            System.out.println("2- Adicionar cliente");
            System.out.println("3- Atualizar cliente");
            System.out.println("4- Deletar cliente");
            System.out.println("5- Voltar ao menu principal");
            int opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    ListarClientes.main(new String[]{});
                    break;
                case 2:
                    AddCliente.main(new String[]{});
                    break;
                case 3:
                    AtualizarCliente.main(new String[]{});
                    break;
                case 4:
                    DeletarCliente.main(new String[]{});
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Opção inválida! Tente novamente.");
            }
        }
    }

    public static void menuPedidos(Scanner scanner) throws SQLException, ParseException {
        while (true) {
            System.out.println("\nMENU PEDIDOS - Escolha uma opção:");
            System.out.println("1- Listar pedidos");
            System.out.println("2- Adicionar pedido");
            System.out.println("3- Atualizar pedido");
            System.out.println("4- Deletar pedido");
            System.out.println("5- Voltar ao menu principal");
            int opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    ListarPedidos.main(new String[]{});
                    break;
                case 2:
                    AddPedido.main(new String[]{});
                    break;
                case 3:
                    AtualizarPedido.main(new String[]{});
                    break;
                case 4:
                    DeletarPedido.main(new String[]{});
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Opção inválida! Tente novamente.");
            }
        }
    }
}