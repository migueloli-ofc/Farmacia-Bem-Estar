package com.farmacia.main;

import com.farmacia.dao.Conexao;
import com.farmacia.dao.ProdutoDAO;
import com.farmacia.dao.VendaDAO;
import com.farmacia.model.Funcionario;
import com.farmacia.model.ItemVenda;
import com.farmacia.model.Produto;
import com.farmacia.model.Venda;
import java.sql.Connection;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ProdutoDAO produtoDAO = new ProdutoDAO();
        VendaDAO vendaDAO = new VendaDAO();

        
        Funcionario atendente = new Funcionario(2, "João", "222.222.222-22", "ATENDENTE");

        System.out.println("=========================================");
        System.out.println("    SISTEMA FARMÁCIA BEM-ESTAR           ");
        System.out.println("=========================================");

        Connection conn = Conexao.conectar();
        if (conn != null) {
            System.out.println(" Status BD: Conectado com sucesso!");
        } else {
            System.out.println(" Status BD: Falha na conexão!");
        }

        int opcao = -1;

        while (opcao != 0) {
            System.out.println("\n--- MENU PRINCIPAL ---");
            System.out.println("1. Cadastrar Produto");
            System.out.println("2. Listar Produtos");
            System.out.println("3. Verificar Alertas de Estoque");
            System.out.println("4. Realizar Venda");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine(); 

            switch (opcao) {
                case 1:
                    System.out.print("Nome do produto: ");
                    String nome = scanner.nextLine();
                    
                    System.out.print("Preço: R$ ");
                    double preco = scanner.nextDouble();
                    
                    System.out.print("Quantidade em estoque: ");
                    int qtd = scanner.nextInt();
                    
                    System.out.print("Estoque mínimo (padrão 10): ");
                    int min = scanner.nextInt();
                    
                    Produto novo = new Produto(0, nome, preco, qtd, LocalDate.now().plusMonths(6), min, "Fornecedor Padrão");
                    produtoDAO.salvar(novo);
                    break;

                case 2:
                    System.out.println("\n--- CATÁLOGO DE PRODUTOS ---");
                    List<Produto> lista = produtoDAO.listarTodos();
                    if (lista.isEmpty()) {
                        System.out.println("Nenhum produto cadastrado.");
                    } else {
                        for (Produto p : lista) {
                            System.out.println("ID: " + p.getId() + " | Nome: " + p.getNome() + " | Preço: R$" + p.getPreco() + " | Qtd: " + p.getQtdEstoque());
                        }
                    }
                    break;

                case 3:
                    System.out.println("\n--- ALERTAS DE ESTOQUE BAIXO (<10) ---");
                    List<Produto> produtos = produtoDAO.listarTodos();
                    boolean encontrouAlerta = false;
                    for (Produto p : produtos) {
                        if (p.verificarEstoqueBaixo()) {
                            System.out.println("⚠️ ALERTA: " + p.getNome() + " tem apenas " + p.getQtdEstoque() + " unidades em estoque!");
                            encontrouAlerta = true;
                        }
                    }
                    if (!encontrouAlerta) {
                        System.out.println(" Todos os produtos estão com estoque regular.");
                    }
                    break;

                case 4:
                    System.out.println("\n--- REALIZAR VENDA ---");
                    System.out.print("Digite o ID do produto vendido: ");
                    int idProd = scanner.nextInt();
                    
                    System.out.print("Quantidade vendida: ");
                    int qtdVendida = scanner.nextInt();
                    scanner.nextLine(); 
                    
                    System.out.print("Forma de pagamento (DINHEIRO, CARTAO ou PIX): ");
                    String pagamento = scanner.nextLine().toUpperCase();


                    Produto produtoEscolhido = null;
                    for (Produto p : produtoDAO.listarTodos()) {
                        if (p.getId() == idProd) {
                            produtoEscolhido = p;
                            break;
                        }
                    }

                    if (produtoEscolhido == null) {
                        System.out.println("❌ Produto não encontrado!");
                    } else if (produtoEscolhido.getQtdEstoque() < qtdVendida) {
                        System.out.println("❌ Estoque insuficiente! Disponível: " + produtoEscolhido.getQtdEstoque());
                    } else {
                        Venda venda = new Venda(0, pagamento, atendente);
                        ItemVenda item = new ItemVenda(0, produtoEscolhido, qtdVendida, produtoEscolhido.getPreco());
                        venda.adicionarItem(item);

                        System.out.println("Total da venda: R$ " + venda.getValorTotal());
                        vendaDAO.registrarVenda(venda);

                        
                        if ((produtoEscolhido.getQtdEstoque() - qtdVendida) < produtoEscolhido.getEstoqueMinimo()) {
                            System.out.println("⚠️ AVISO: O estoque de " + produtoEscolhido.getNome() + " ficou abaixo do mínimo!");
                        }
                    }
                    break;

                case 0:
                    System.out.println("Encerrando o sistema...");
                    break;

                default:
                    System.out.println("Opção inválida!");
                    break;
            }
        }

        scanner.close();
    }
}