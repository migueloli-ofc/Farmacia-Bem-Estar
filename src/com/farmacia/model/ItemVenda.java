package com.farmacia.model;

public class ItemVenda {
    private int id;
    private Produto produto;
    private int quantidade;
    private double precoUnitario;

    public ItemVenda(int id, Produto produto, int quantidade, double precoUnitario) {
        this.id = id;
        this.produto = produto;
        this.quantidade = quantidade;
        this.precoUnitario = precoUnitario;
    }

    public double calcularSubtotal() {
        return this.quantidade * this.precoUnitario;
    }
    
    
    public Produto getProduto() { return produto; }
    public int getQuantidade() { return quantidade; }
    public double getPrecoUnitario() { return precoUnitario; }
}