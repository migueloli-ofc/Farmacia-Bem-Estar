package com.farmacia.dao;

import com.farmacia.model.Produto;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDAO {

      public void salvar(Produto produto) {
        String sql = "INSERT INTO produto (nome, preco, qtd_estoque, estoque_minimo, data_validade, fornecedor_id) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, produto.getNome());
            stmt.setDouble(2, produto.getPreco());
            stmt.setInt(3, produto.getQtdEstoque());
            stmt.setInt(4, produto.getEstoqueMinimo());
            stmt.setDate(5, Date.valueOf(produto.getDataValidade()));
            stmt.setInt(6, 1);

            stmt.executeUpdate();
            System.out.println("✅ Produto salvo no banco de dados!");

        } catch (SQLException e) {
            System.err.println("❌ Erro ao salvar produto: " + e.getMessage());
        }
    }

 
    public List<Produto> listarTodos() {
        List<Produto> produtos = new ArrayList<>();
        String sql = "SELECT * FROM produto ORDER BY id ASC";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Produto p = new Produto(
                    rs.getInt("id"),
                    rs.getString("nome"),
                    rs.getDouble("preco"),
                    rs.getInt("qtd_estoque"),
                    rs.getDate("data_validade").toLocalDate(),
                    rs.getInt("estoque_minimo"),
                    "Fornecedor ID: " + rs.getInt("fornecedor_id")
                );
                produtos.add(p);
            }

        } catch (SQLException e) {
            System.err.println("❌ Erro ao listar produtos: " + e.getMessage());
        }

        return produtos;
    }

   
    public void atualizarEstoque(int idProduto, int novaQuantidade) {
        String sql = "UPDATE produto SET qtd_estoque = ? WHERE id = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, novaQuantidade);
            stmt.setInt(2, idProduto);

            stmt.executeUpdate();

        } catch (SQLException e) {
            System.err.println("❌ Erro ao atualizar estoque: " + e.getMessage());
        }
    }
}