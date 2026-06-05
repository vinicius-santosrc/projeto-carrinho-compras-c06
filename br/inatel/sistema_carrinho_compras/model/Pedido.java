package br.inatel.sistema_carrinho_compras.model;

import br.inatel.sistema_carrinho_compras.payment.IPagamento;
import br.inatel.sistema_carrinho_compras.exception.PagamentoException;

import java.util.Date;

public class Pedido {
    private int id;
    private double valorTotal;

    public Pedido(int id, Date data, double valorTotal) {
        this.id = id;
        this.valorTotal = valorTotal;
    }

    public void finalizar(Carrinho carrinho, IPagamento formaPagamento) throws PagamentoException {

        this.valorTotal = carrinho.calcularTotal();

        System.out.println("Iniciando fechamento do Pedido ID: " + this.id);
        System.out.println("Valor total a ser pago: R$ " + this.valorTotal);


        boolean sucesso = formaPagamento.processarPagamento(this.valorTotal);

        if (sucesso) {
            System.out.println("[SUCESSO] Pedido #" + this.id + " finalizado e pago!");
            System.out.println("Obrigado pela compra!");
        }
    }

    public int getId() {
        return this.id;
    }

    public double getValorTotal() {
        return this.valorTotal;
    }

}
