package br.inatel.sistema_carrinho_compras.payment;
import br.inatel.sistema_carrinho_compras.exception.PagamentoException;

public class PagamentoBoleto implements IPagamento {


    private String codigoBarras;

    public PagamentoBoleto(String codigoBarras) {
        this.codigoBarras = codigoBarras;
    }

    @Override
    public boolean processarPagamento(double valor) throws PagamentoException {
        if (this.codigoBarras == null || this.codigoBarras.length() < 10) {
            throw new PagamentoException("Pagamento Boleto Recusado: Código de barras corrompido ou inválido.");
        }

        System.out.println("Gerando boleto bancário...");
        System.out.println("Código de barras gerado: " + this.codigoBarras);
        System.out.println("Boleto emitido no valor de R$ " + valor + ". Aguardando compensação.");
        return true;
    }
}