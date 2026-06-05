package br.inatel.sistema_carrinho_compras.repository;

import br.inatel.sistema_carrinho_compras.model.Pedido;

import java.util.ArrayList;
import java.util.List;

public class PedidoArquivoRepository implements IRepository<Pedido> {

    private List<Pedido> pedidos;

    public PedidoArquivoRepository() {
        this.pedidos = new ArrayList<>();
    }

    @Override
    public void salvar(Pedido pedido) {
        this.pedidos.add(pedido);
        System.out.println("Pedido salvo no repositório.");
    }

    @Override
    public Pedido buscar(int id) {
        for (Pedido pedido : pedidos) {
            if (pedido.getId() == id) {
                return pedido;
            }
        }

        return null;
    }

    @Override
    public void remover(int id) {
        Pedido pedidoEncontrado = buscar(id);

        if (pedidoEncontrado != null) {
            this.pedidos.remove(pedidoEncontrado);
            System.out.println("Pedido removido do repositório.");
        } else {
            System.out.println("Pedido não encontrado.");
        }
    }

    public List<Pedido> listarTodos() {
        return pedidos;
    }
}