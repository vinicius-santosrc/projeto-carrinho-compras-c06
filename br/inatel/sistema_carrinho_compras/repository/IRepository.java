package br.inatel.sistema_carrinho_compras.repository;

public interface IRepository<T> {

    void salvar(T obj);

    T buscar(int id);

    void remover(int id);
}