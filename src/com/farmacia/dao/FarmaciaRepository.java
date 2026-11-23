package com.farmacia.dao;

import com.farmacia.model.*;
import java.util.ArrayList;
import java.util.List;

public class FarmaciaRepository {
    private List<Produto> produtos = new ArrayList<>();
    private List<Receita> receitas = new ArrayList<>();
    private List<String> vendas = new ArrayList<>();

    public void salvarProduto(Produto produto) { produtos.add(produto); }
    public List<Produto> listarProdutos() { return produtos; }

    public void salvarReceita(Receita receita) { receitas.add(receita); }
    public List<Receita> listarReceitas() { return receitas; }

    public void registrarVendaHistorico(String detalheVenda) { vendas.add(detalheVenda); }
    public List<String> listarVendas() { return vendas; }
}