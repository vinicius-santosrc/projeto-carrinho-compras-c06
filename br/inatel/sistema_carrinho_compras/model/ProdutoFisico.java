package br.inatel.sistema_carrinho_compras.model;

public class ProdutoFisico extends Produto {

    private double peso;

    public ProdutoFisico(int id, String nome, double preco, int quantidadeEmEstoque, double peso) {
        super(id, nome, preco, quantidadeEmEstoque);
        this.peso = peso;
    }

    public double getPeso() {
        return peso;
    }

    @Override
    public double getPreco() {
        return preco;
    }
}