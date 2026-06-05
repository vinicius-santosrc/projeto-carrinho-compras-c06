package br.inatel.sistema_carrinho_compras.user;

import br.inatel.sistema_carrinho_compras.model.Produto;
import br.inatel.sistema_carrinho_compras.model.Pedido;
import java.util.List;

public class Administrador extends Usuario{


    public Administrador(int id,String nome,String email){
        super(id, nome, email);
    }

    public void cadastrarProduto(Produto produto) {

        System.out.println("[SISTEMA] ADM " + this.nome + " está cadastrando um produto.");

        // Simulação do cadastro exibindo os dados do produto na tela
        System.out.println("ID do Produto: " + produto.getId());
        System.out.println("Nome: " + produto.getNome());
        System.out.println("Preço Base: R$ " + produto.getPrecoBase());
        System.out.println("Quantidade em Estoque: " + produto.getQuantidadeEmEstoque());

        System.out.println("[SUCESSO] Produto '" + produto.getNome() + "' cadastrado com sucesso!");
    }

    public void gerarRelatorio(List<Pedido> listaPedidos) {

        System.out.println("RELATÓRIO DE VENDAS");
        System.out.println("Gerado por: ADM " + this.nome);


        double faturamentoTotal = 0.0;
        int totalPedidos = listaPedidos.size();


        for (Pedido pedido : listaPedidos) {
            System.out.println("Pedido ID: #" + pedido.getId() + " | Valor: R$ " + pedido.getValorTotal());
            faturamentoTotal += pedido.getValorTotal();
        }


        System.out.println("Total de Pedidos Processados: " + totalPedidos);
        System.out.println("Faturamento Bruto Total: R$ " + faturamentoTotal);

    }



}
