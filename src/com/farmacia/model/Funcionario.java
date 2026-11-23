package com.farmacia.model;

public class Funcionario {
    private int id;
    private String nome;
    private String cpf;
    private String cargo; 

    public Funcionario(int id, String nome, String cpf, String cargo) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.cargo = cargo;
    }

    public int getId() { 
    	return id; }
    public String getNome() { 
    	return nome; }
    public String getCargo() { 
    	return cargo; }
    public String getCpf() {
    	return cpf;
    }
    
}