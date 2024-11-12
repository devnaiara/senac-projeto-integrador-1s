package produtos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class AtualizarProduto {
    public static void main(String[] args) throws SQLException {
        Connection conexao = null;
        Scanner scanner = new Scanner(System.in);

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            conexao = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/LogoLogo", "root", "root");

            System.out.print("Digite o ID do produto a ser atualizado: ");
            int id = scanner.nextInt();
            scanner.nextLine(); 

            System.out.print("Deseja atualizar todos os campos? (s/n): ");
            String atualizarTodos = scanner.nextLine();

            String sql;
            PreparedStatement stmt;

            if (atualizarTodos.equalsIgnoreCase("s")) {
                System.out.print("Novo nome do produto: ");
                String nome = scanner.nextLine();

                System.out.print("Nova descrição do produto: ");
                String descricao = scanner.nextLine();

                System.out.print("Novo preço do produto: ");
                double preco = scanner.nextDouble();
                scanner.nextLine();

                System.out.print("Nova cor do produto: ");
                String cor = scanner.nextLine();

                System.out.print("Novo estoque do produto: ");
                int estoque = scanner.nextInt();

                System.out.print("Novo tamanho do produto: ");
                String tamanho = scanner.nextLine(); 

                System.out.print("ID da categoria do produto: ");
                int idCat = scanner.nextInt();

                System.out.print("ID da subcategoria do produto: ");
                int idSubcat = scanner.nextInt();

                sql = "UPDATE produto SET nome_produto = ?, descricao = ?, preco = ?, cor = ?, estoque = ?, tamanho = ?, id_categoria = ?, id_subcategoria = ? WHERE id_produto = ?";
                stmt = conexao.prepareStatement(sql);
                stmt.setString(1, nome);
                stmt.setString(2, descricao);
                stmt.setDouble(3, preco);
                stmt.setString(4, cor);
                stmt.setInt(5, estoque);
                stmt.setString(6, tamanho);  
                stmt.setInt(7, idCat);       
                stmt.setInt(8, idSubcat);    
                stmt.setInt(9, id);          

            } else {
                System.out.println("Escolha o campo que deseja atualizar:");
                System.out.println("1 - Nome");
                System.out.println("2 - Descrição");
                System.out.println("3 - Preço");
                System.out.println("4 - Cor");
                System.out.println("5 - Estoque");
                System.out.println("6 - Tamanho");
                System.out.println("7 - Categoria");
                System.out.println("8 - Subcategoria");

                int opcao = scanner.nextInt();
                scanner.nextLine(); 

                switch (opcao) {
                    case 1 -> {
                        System.out.print("Novo nome do produto: ");
                        String nome = scanner.nextLine();
                        sql = "UPDATE produto SET nome_produto = ? WHERE id_produto = ?";
                        stmt = conexao.prepareStatement(sql);
                        stmt.setString(1, nome);
                        stmt.setInt(2, id);
                    }
                    case 2 -> {
                        System.out.print("Nova descrição do produto: ");
                        String descricao = scanner.nextLine();
                        sql = "UPDATE produto SET descricao = ? WHERE id_produto = ?";
                        stmt = conexao.prepareStatement(sql);
                        stmt.setString(1, descricao);
                        stmt.setInt(2, id);
                    }
                    case 3 -> {
                        System.out.print("Novo preço do produto: ");
                        double preco = scanner.nextDouble();
                        sql = "UPDATE produto SET preco = ? WHERE id_produto = ?";
                        stmt = conexao.prepareStatement(sql);
                        stmt.setDouble(1, preco);
                        stmt.setInt(2, id);
                    }
                    case 4 -> {
                        System.out.print("Nova cor do produto: ");
                        String cor = scanner.nextLine();
                        sql = "UPDATE produto SET cor = ? WHERE id_produto = ?";
                        stmt = conexao.prepareStatement(sql);
                        stmt.setString(1, cor);
                        stmt.setInt(2, id);
                    }
                    case 5 -> {
                        System.out.print("Novo estoque do produto: ");
                        int estoque = scanner.nextInt();
                        sql = "UPDATE produto SET estoque = ? WHERE id_produto = ?";
                        stmt = conexao.prepareStatement(sql);
                        stmt.setInt(1, estoque);
                        stmt.setInt(2, id);
                    }
                    case 6 -> {
                        System.out.print("Novo tamanho do produto: ");
                        String tamanho = scanner.nextLine();
                        sql = "UPDATE produto SET tamanho = ? WHERE id_produto = ?";
                        stmt = conexao.prepareStatement(sql);
                        stmt.setString(1, tamanho);
                        stmt.setInt(2, id);
                    }
                    case 7 -> {
                        System.out.print("Novo ID da categoria do produto: ");
                        int idCat = scanner.nextInt();
                        sql = "UPDATE produto SET id_categoria = ? WHERE id_produto = ?";
                        stmt = conexao.prepareStatement(sql);
                        stmt.setInt(1, idCat);
                        stmt.setInt(2, id);
                    }
                    case 8 -> {
                        System.out.print("Novo ID da subcategoria do produto: ");
                        int idSubcat = scanner.nextInt();
                        sql = "UPDATE produto SET id_subcategoria = ? WHERE id_produto = ?";
                        stmt = conexao.prepareStatement(sql);
                        stmt.setInt(1, idSubcat);
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
                System.out.println("Produto atualizado com sucesso!");
            } else {
                System.out.println("Produto não encontrado.");
            }

        } catch (ClassNotFoundException ex) {
            System.out.println("Driver do banco de dados não encontrado.");
        } catch (SQLException ex) {
            System.out.println("Erro ao acessar o Banco de Dados: " + ex.getMessage());
        } finally {
            if (conexao != null) {
                conexao.close();
            }
            scanner.close();
        }
    }
}
