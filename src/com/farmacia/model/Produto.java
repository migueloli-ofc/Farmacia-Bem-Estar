package com.farmacia.model;

import java.time.LocalDate;

public class Produto {
    private int id;
    private String nome;
    private double preco;
    private int qtdEstoque;
    private LocalDate dataValidade;
    private int estoqueMinimo;
    private String fornecedor;

    public Produto(int id, String nome, double preco, int qtdEstoque, LocalDate dataValidade, int estoqueMinimo, String fornecedor) {
        this.id = id;
        this.nome = nome;
        this.preco = preco;
        this.qtdEstoque = qtdEstoque;
        this.dataValidade = dataValidade;
        this.estoqueMinimo = estoqueMinimo;
        this.fornecedor = fornecedor;
    }

    public int getId() { 
    	return id; }
    public String getNome() { 
    	return nome; }
    public double getPreco() { 
    	return preco; }
    public int getQtdEstoque() { 
    	return qtdEstoque; }
    
    public void setQtdEstoque(int qtdEstoque) { 
    	this.qtdEstoque = qtdEstoque; }
    
    public LocalDate getDataValidade() { 
    	return dataValidade; }
    
    public int getEstoqueMinimo() { 
    	return estoqueMinimo; }
    
    public String getFornecedor() { 
    	return fornecedor; }

    public boolean verificarEstoqueBaixo() {
        return this.qtdEstoque < this.estoqueMinimo;
    }

    public void atualizarEstoque(int quantidadeVendida) {
        this.qtdEstoque -= quantidadeVendida;
    }
}