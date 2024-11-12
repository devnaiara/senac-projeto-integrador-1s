package produtos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class AddProduto {

    public static void main(String[] args) throws SQLException {
        Connection conexao = null;
        Scanner scanner = new Scanner(System.in);

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            conexao = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/LogoLogo", "root", "root");

            System.out.print("Nome do produto: ");
            String nome = scanner.nextLine();
            
            System.out.print("Descrição do produto: ");
            String descricao = scanner.nextLine();
            
            System.out.print("Preço do produto: ");
            double preco = scanner.nextDouble();
            scanner.nextLine(); 

            System.out.print("Cor do produto: ");
            String cor = scanner.nextLine();

            System.out.print("Estoque do produto: ");
            int estoque = scanner.nextInt();
            scanner.nextLine(); 

            System.out.print("ID da categoria: ");
            int idCat = scanner.nextInt();
            
            System.out.print("ID da subcategoria: ");
            int idSubcat = scanner.nextInt();
            scanner.nextLine(); 

            System.out.print("Tamanho do produto: ");
            String tamanho = scanner.nextLine(); 

            String sql = "INSERT INTO produto (nome_produto, descricao, preco, cor, estoque, id_subcategoria, id_categoria, tamanho) "
                       + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setString(1, nome);
            stmt.setString(2, descricao);
            stmt.setDouble(3, preco);
            stmt.setString(4, cor);
            stmt.setInt(5, estoque);
            stmt.setInt(6, idSubcat); 
            stmt.setInt(7, idCat);    
            stmt.setString(8, tamanho);


            int linhasAfetadas = stmt.executeUpdate();
            if (linhasAfetadas > 0) {
                System.out.println("Produto adicionado com sucesso!");
            } else {
                System.out.println("Falha ao adicionar o produto.");
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
