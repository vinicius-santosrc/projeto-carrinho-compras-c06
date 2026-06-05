package br.inatel.sistema_carrinho_compras.repository;

import br.inatel.sistema_carrinho_compras.model.Produto;

import java.util.ArrayList;
import java.util.List;

public class ProdutoArquivoRepository implements IRepository<Produto> {

    private List<Produto> produtos = new ArrayList<>();

    @Override
    public void salvar(Produto produto) {
        produtos.add(produto);
        System.out.println("Produto salvo com sucesso!");
    }

    @Override
    public Produto buscar(int id) {
        for (Produto produto : produtos) {
            if (produto.getId() == id) {
                return produto;
            }
        }

        return null;
    }

    @Override
    public void remover(int id) {
        Produto produtoEncontrado = buscar(id);

        if (produtoEncontrado != null) {
            produtos.remove(produtoEncontrado);
            System.out.println("Produto removido com sucesso!");
        } else {
            System.out.println("Produto não encontrado.");
        }
    }

    public List<Produto> listarTodos() {
        return produtos;
    }
}