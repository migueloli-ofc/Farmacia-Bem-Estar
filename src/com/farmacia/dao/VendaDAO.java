package com.farmacia.dao;

import com.farmacia.model.ItemVenda;
import com.farmacia.model.Venda;
import java.sql.*;

public class VendaDAO {

    public void registrarVenda(Venda venda) {
        String sqlVenda = "INSERT INTO venda (valor_total, forma_pagamento, funcionario_id) VALUES (?, ?, ?) RETURNING id";
        String sqlItem = "INSERT INTO item_venda (venda_id, produto_id, quantidade, preco_unitario) VALUES (?, ?, ?, ?)";
        String sqlBaixaEstoque = "UPDATE produto SET qtd_estoque = qtd_estoque - ? WHERE id = ?";

        try (Connection conn = Conexao.conectar()) {
            
            PreparedStatement stmtVenda = conn.prepareStatement(sqlVenda);
            stmtVenda.setDouble(1, venda.getValorTotal());
            stmtVenda.setString(2, venda.getFormaPagamento());
            stmtVenda.setInt(3, venda.getFuncionario().getId());
            
            ResultSet rs = stmtVenda.executeQuery();
            int idVendaGerado = 0;
            if (rs.next()) {
                idVendaGerado = rs.getInt(1);
            }

           
            PreparedStatement stmtItem = conn.prepareStatement(sqlItem);
            PreparedStatement stmtEstoque = conn.prepareStatement(sqlBaixaEstoque);

            for (ItemVenda item : venda.getItens()) {
               
                stmtItem.setInt(1, idVendaGerado);
                stmtItem.setInt(2, item.getProduto().getId());
                stmtItem.setInt(3, item.getQuantidade());
                stmtItem.setDouble(4, item.getPrecoUnitario());
                stmtItem.executeUpdate();

                
                stmtEstoque.setInt(1, item.getQuantidade());
                stmtEstoque.setInt(2, item.getProduto().getId());
                stmtEstoque.executeUpdate();
            }

            System.out.println("Venda registrada e estoque atualizado com sucesso!");

        } catch (SQLException e) {
            System.err.println("Erro ao registrar venda: " + e.getMessage());
        }
    }
}