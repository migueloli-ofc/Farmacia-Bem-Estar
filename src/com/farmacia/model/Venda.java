package com.farmacia.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Venda {
    private int id;
    private LocalDateTime dataVenda;
    private double valorTotal;
    private String formaPagamento; 
    private Funcionario funcionario;
    private List<ItemVenda> itens = new ArrayList<>();

    public Venda(int id, String formaPagamento, Funcionario funcionario) {
        this.id = id;
        this.dataVenda = LocalDateTime.now();
        this.formaPagamento = formaPagamento;
        this.funcionario = funcionario;
    }

    public void adicionarItem(ItemVenda item) {
        this.itens.add(item);
        calcularTotal();
    }

    public double calcularTotal() {
        this.valorTotal = 0;
        for (ItemVenda item : itens) {
            this.valorTotal += item.calcularSubtotal();
        }
        return this.valorTotal;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public double getValorTotal() { return valorTotal; }
    public String getFormaPagamento() { return formaPagamento; }
    public Funcionario getFuncionario() { return funcionario; }
    public List<ItemVenda> getItens() { return itens; }
}