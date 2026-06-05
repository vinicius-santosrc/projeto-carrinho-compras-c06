package br.inatel.sistema_carrinho_compras.model;

public abstract class Produto {

    protected int id;
    protected String nome;
    protected double preco;
    protected int quantidadeEmEstoque;

    public Produto(int id, String nome, double preco, int quantidadeEmEstoque) {
        this.id = id;
        this.nome = nome;
        this.preco = preco;
        this.quantidadeEmEstoque = quantidadeEmEstoque;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public double getPrecoBase() {
        return preco;
    }

    public int getQuantidadeEmEstoque() {
        return quantidadeEmEstoque;
    }

    public void verificarDisponibilidade(int quantidadeDesejada) throws br.inatel.sistema_carrinho_compras.exception.EstoqueInsufisienteException {
        if (quantidadeDesejada > quantidadeEmEstoque) {
            throw new br.inatel.sistema_carrinho_compras.exception.EstoqueInsufisienteException("Estoque insuficiente para o produto: " + nome);
        }
    }

    public void diminuirEstoque(int quantidade) {
        this.quantidadeEmEstoque -= quantidade;
    }

    public abstract double getPreco();
}