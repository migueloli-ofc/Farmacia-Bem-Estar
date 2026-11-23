package com.farmacia.dao;

import com.farmacia.model.Funcionario;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FuncionarioDAO {

    public void salvar(Funcionario f) {
        String sql = "INSERT INTO funcionario (nome, cpf, cargo) VALUES (?, ?, ?)";
        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, f.getNome());
            stmt.setString(2, "000.000.000-00");
            stmt.setString(3, f.getCargo());
            stmt.executeUpdate();
            System.out.println(" Funcionário cadastrado!");
            
        } catch (SQLException e) {
            System.err.println(" Erro ao salvar funcionário: " + e.getMessage());
        }
    }

    public List<Funcionario> listarTodos() {
        List<Funcionario> lista = new ArrayList<>();
        String sql = "SELECT * FROM funcionario";
        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            
            while (rs.next()) {
                lista.add(new Funcionario(
                    rs.getInt("id"),
                    rs.getString("nome"),
                    rs.getString("cpf"),
                    rs.getString("cargo")
                ));
            }
        } catch (SQLException e) {
            System.err.println(" Erro ao listar funcionários: " + e.getMessage());
        }
        return lista;
    }
}