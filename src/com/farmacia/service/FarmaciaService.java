package com.farmacia.service;

import com.farmacia.dao.FarmaciaRepository;
import com.farmacia.model.*;
import java.time.LocalDate;

public class FarmaciaService{
    private FarmaciaRepository repo = new FarmaciaRepository();

    public FarmaciaRepository getRepo() { return repo; }

  
    public boolean realizarVenda(int produtoId, int qtd, String formaPagamento) {
        for (Produto p : repo.listarProdutos()) {
            if (p.getId() == produtoId) {
                if (p.getQtdEstoque() >= qtd) {
                    p.atualizarEstoque(qtd);
                    double total = p.getPreco() * qtd;
                    repo.registrarVendaHistorico("Item: " + p.getNome() + " | Qtd: " + qtd + " | Total: R$" + total + " | Pagamento: " + formaPagamento);
                    
                 
                    if (p.verificarEstoqueBaixo()) {
                        System.out.println(" [ALERTA DE ESTOQUE] O produto '" + p.getNome() + "' está abaixo do estoque mínimo (" + p.getQtdEstoque() + " unidades restantes)!");
                    }
                    return true;
                } else {
                    System.out.println(" Sem estoque suficiente!");
                    return false;
                }
            }
        }
        System.out.println(" Produto não encontrado!");
        return false;
    }

 
    public void registrarReceitaControlada(Funcionario usuario, Receita receita) {
        if (usuario.getCargo().equalsIgnoreCase("FARMACEUTICO") || usuario.getCargo().equalsIgnoreCase("GERENTE")) {
            repo.salvarReceita(receita);
            System.out.println(" Receita salva com sucesso por " + usuario.getNome());
        } else {
            System.out.println(" Acesso negado: Apenas a Farmacêutica (ou Gerente) pode registrar receitas!");
        }
    }

  
    public void exibirRelatorioFinanceiro(Funcionario usuario) {
        if (!usuario.getCargo().equalsIgnoreCase("GERENTE")) {
            System.out.println(" Acesso Negado: Apenas a Gerente (Dona Maria) pode visualizar o relatório financeiro!");
            return;
        }

        System.out.println("\n--- RELATÓRIO FINANCEIRO (GERÊNCIA) ---");
        for (String v : repo.listarVendas()) {
            System.out.println(v);
        }
    }
}