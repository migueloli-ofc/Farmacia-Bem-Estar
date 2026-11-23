package com.farmacia.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
    
    private static final String URL = "jdbc:postgresql://aws-1-us-east-1.pooler.supabase.com:5432/postgres?sslmode=require";
    private static final String USUARIO = "postgres.joxxvondvnqvlwvqcwjo";
    private static final String SENHA = "[SUA_SENHA]"; 

    public static Connection conectar() {
        try {
            Class.forName("org.postgresql.Driver");
            return DriverManager.getConnection(URL, USUARIO, SENHA);
        } catch (ClassNotFoundException e) {
            System.err.println("❌ Erro: Driver JDBC não encontrado!");
            return null;
        } catch (SQLException e) {
            System.err.println("❌ Erro ao conectar ao Supabase: " + e.getMessage());
            return null;
        }
    }
}