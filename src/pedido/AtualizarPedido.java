package pedido;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class AtualizarPedido {
    public static void main(String[] args) throws ParseException {
        Connection conexao = null;
        PreparedStatement stmt = null;
        Scanner scanner = new Scanner(System.in);

        SimpleDateFormat formatoBrasileiro = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat formatoAmericano = new SimpleDateFormat("yyyy-MM-dd");

        try {
            conexao = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/LogoLogo", "root", "root");

            System.out.print("Digite o ID do pedido a ser atualizado: ");
            int id = scanner.nextInt();
            scanner.nextLine();  

            System.out.print("Deseja atualizar todos os campos? (s/n): ");
            String atualizarTodos = scanner.nextLine();

            String sql;

            if (atualizarTodos.equalsIgnoreCase("s")) {
                System.out.print("Data do Pedido (formato DD/MM/AAAA): ");
                String dataBrasileira = scanner.nextLine();
                java.util.Date data = formatoBrasileiro.parse(dataBrasileira);
                String dataAmericana = formatoAmericano.format(data);

                System.out.print("Total do Pedido: ");
                double total_pedido = scanner.nextDouble();
                scanner.nextLine();

                sql = "UPDATE pedido SET data_pedido = ?, total_pedido = ? WHERE id_pedido = ?";
                stmt = conexao.prepareStatement(sql);
                stmt.setString(1, dataAmericana);  
                stmt.setDouble(2, total_pedido);
                stmt.setInt(3, id);

            } else {
                System.out.println("Escolha o campo que deseja atualizar:");
                System.out.println("1 - Data do Pedido");
                System.out.println("2 - Total do Pedido");

                int opcao = scanner.nextInt();
                scanner.nextLine();  

                switch (opcao) {
                    case 1 -> {
                        System.out.print("Nova data do pedido (formato DD/MM/AAAA): ");
                        String dataBrasileira = scanner.nextLine();
                        java.util.Date data = formatoBrasileiro.parse(dataBrasileira);
                        String dataAmericana = formatoAmericano.format(data);

                        sql = "UPDATE pedido SET data_pedido = ? WHERE id_pedido = ?";
                        stmt = conexao.prepareStatement(sql);
                        stmt.setString(1, dataAmericana);  
                        stmt.setInt(2, id);
                    }
                    case 2 -> {
                        System.out.print("Novo total do pedido: ");
                        double total_pedido = scanner.nextDouble();
                        sql = "UPDATE pedido SET total_pedido = ? WHERE id_pedido = ?";
                        stmt = conexao.prepareStatement(sql);
                        stmt.setDouble(1, total_pedido);
                        stmt.setInt(2, id);
                    }
                    default -> {
                        System.out.println("Opção inválida.");
                        return;
                    }
                }
            }

            int linhasAfetadas = stmt.executeUpdate();
            if (linhasAfetadas > 0) {
                System.out.println("Pedido atualizado com sucesso!");
            } else {
                System.out.println("Pedido não encontrado.");
            }

        } catch (SQLException ex) {
            System.out.println("Erro ao acessar o Banco de Dados: " + ex.getMessage());
        } catch (ParseException ex) {
            System.out.println("Erro ao formatar a data: " + ex.getMessage());
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
                if (conexao != null) {
                    conexao.close();
                }
            } catch (SQLException e) {
                System.out.println("Erro ao fechar recursos: " + e.getMessage());
            }
            scanner.close();
        }
    }
}
