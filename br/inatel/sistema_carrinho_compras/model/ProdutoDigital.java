package br.inatel.sistema_carrinho_compras.model;

public class ProdutoDigital extends Produto {

    private String linkDownload;

    public ProdutoDigital(int id, String nome, double preco, int quantidadeEmEstoque, String linkDownload) {
        super(id, nome, preco, quantidadeEmEstoque);
        this.linkDownload = linkDownload;
    }

    public String getLinkDownload() {
        return linkDownload;
    }

    @Override
    public double getPreco() {
        return preco;
    }
}