package br.inatel.sistema_carrinho_compras.payment;
import br.inatel.sistema_carrinho_compras.exception.PagamentoException;

public interface IPagamento {
     boolean processarPagamento(double valor) throws PagamentoException;
}
