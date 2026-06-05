package br.inatel.sistema_carrinho_compras.model;

import br.inatel.sistema_carrinho_compras.exception.EstoqueInsufisienteException;

import java.util.ArrayList;
import java.util.List;

public class Carrinho {

    private List<ItemCarrinho> itens;

    public Carrinho() {
        this.itens = new ArrayList<>();
    }

    public void adicionarItem(ItemCarrinho item) throws EstoqueInsufisienteException {
        item.getProduto().verificarDisponibilidade(item.getQuantidade());
        item.getProduto().diminuirEstoque(item.getQuantidade());

        this.itens.add(item);
        System.out.println("Item adicionado ao carrinho com sucesso.");
    }

    public void removerItem(ItemCarrinho item) {
        this.itens.remove(item);
        System.out.println("Item removido do carrinho.");
    }

    public double calcularTotal() {
        double total = 0.0;

        for (ItemCarrinho item : itens) {
            total += item.subtotal();
        }

        return total;
    }

    public List<ItemCarrinho> getItens() {
        return itens;
    }
}
