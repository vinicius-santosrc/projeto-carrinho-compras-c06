package br.inatel.sistema_carrinho_compras.payment;
import br.inatel.sistema_carrinho_compras.exception.PagamentoException;

public class PagamentoCartao implements IPagamento {

    private String numeroCartao;


    public PagamentoCartao(String numeroCartao) {
        this.numeroCartao = numeroCartao;
    }

    @Override
    public boolean processarPagamento(double valor) throws PagamentoException {
        if (this.numeroCartao == null || this.numeroCartao.length() != 16) {
            throw new PagamentoException("Pagamento Cartão Recusado: Número de cartão inválido ou sem saldo.");
        }

        System.out.println("Conectando com a operadora de cartão...");
        System.out.println("Validando o cartão número: " + this.numeroCartao);
        System.out.println("Cobrança de R$ " + valor + " aprovada!");
        return true;
    }
}