package br.inatel.sistema_carrinho_compras.user;
import br.inatel.sistema_carrinho_compras.model.Carrinho;
import br.inatel.sistema_carrinho_compras.model.ItemCarrinho;
import br.inatel.sistema_carrinho_compras.exception.EstoqueInsufisienteException;

public class Cliente extends  Usuario{

    private String endereco;
    private Carrinho carrinho;

    public Cliente (int id,String nome, String email,String endereco)
    {
        super(id, nome, email);
        this.endereco = endereco;
        this.carrinho = new Carrinho();
    }

    public void adicionarCarrinho(ItemCarrinho item) throws EstoqueInsufisienteException {

        System.out.println("O cliente " + this.nome + " está tentando adicionar um item...");

        this.carrinho.adicionarItem(item);
    }

    public Carrinho getCarrinho() {
        return this.carrinho;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }
}
