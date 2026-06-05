package br.inatel.sistema_carrinho_compras.payment;
import br.inatel.sistema_carrinho_compras.exception.PagamentoException;

public class PagamentoPix implements IPagamento {

    private String chavePix;

    public PagamentoPix(String chavePix)
    {
        this.chavePix=chavePix;
    }

    @Override
    public boolean processarPagamento(double valor) throws PagamentoException
    {
        if (this.chavePix == null || this.chavePix.trim().isEmpty()) {
            throw new PagamentoException("Pagamento Pix Recusado: Chave Pix inválida.");
        }

        System.out.println("Gerando QR Code Pix no valor de R$ " + valor);
        System.out.println("Pagamento recebido via chave: " + this.chavePix);
        return true;
    }




}
