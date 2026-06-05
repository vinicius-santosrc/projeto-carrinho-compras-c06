package br.inatel.sistema_carrinho_compras;

import br.inatel.sistema_carrinho_compras.exception.EstoqueInsufisienteException;
import br.inatel.sistema_carrinho_compras.exception.PagamentoException;
import br.inatel.sistema_carrinho_compras.model.ItemCarrinho;
import br.inatel.sistema_carrinho_compras.model.Pedido;
import br.inatel.sistema_carrinho_compras.model.Produto;
import br.inatel.sistema_carrinho_compras.model.ProdutoDigital;
import br.inatel.sistema_carrinho_compras.model.ProdutoFisico;
import br.inatel.sistema_carrinho_compras.payment.IPagamento;
import br.inatel.sistema_carrinho_compras.payment.PagamentoBoleto;
import br.inatel.sistema_carrinho_compras.payment.PagamentoCartao;
import br.inatel.sistema_carrinho_compras.payment.PagamentoPix;
import br.inatel.sistema_carrinho_compras.repository.PedidoArquivoRepository;
import br.inatel.sistema_carrinho_compras.repository.ProdutoArquivoRepository;
import br.inatel.sistema_carrinho_compras.user.Administrador;
import br.inatel.sistema_carrinho_compras.user.Cliente;

import java.util.Date;
import java.util.Scanner;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);

    private static final ProdutoArquivoRepository produtoRepository = new ProdutoArquivoRepository();
    private static final PedidoArquivoRepository pedidoRepository = new PedidoArquivoRepository();

    private static final Administrador administrador = new Administrador(1, "Administrador", "admin@email.com");
    private static final Cliente cliente = new Cliente(2, "Vinicius", "vinicius@email.com", "Rua A");

    public static void main(String[] args) {
        boolean executando = true;

        criarProdutosIniciais();

        while (executando) {
            mostrarMenu();
            int opcao = lerInteiro("Escolha uma opção: ");

            switch (opcao) {
                case 1:
                    testarLogin();
                    break;
                case 2:
                    listarProdutos();
                    break;
                case 3:
                    buscarProdutoPorId();
                    break;
                case 4:
                    adicionarProdutoNoCarrinho();
                    break;
                case 5:
                    mostrarCarrinho();
                    break;
                case 6:
                    finalizarPedidoComPagamento();
                    break;
                case 7:
                    buscarPedidoPorId();
                    break;
                case 8:
                    administrador.gerarRelatorio(pedidoRepository.listarTodos());
                    break;
                case 9:
                    removerProdutoPorId();
                    break;
                case 10:
                    testarEstoqueInsuficiente();
                    break;
                case 11:
                    testarTodosOsPagamentos();
                    break;
                case 12:
                    testarLogout();
                    break;
                case 0:
                    executando = false;
                    System.out.println("Sistema finalizado.");
                    break;
                default:
                    System.out.println("Opção inválida.");
                    break;
            }
        }

        scanner.close();
    }

    private static void mostrarMenu() {
        System.out.println("========== SISTEMA DE CARRINHO DE COMPRAS ==========");
        System.out.println("1 - Testar login do administrador e cliente");
        System.out.println("2 - Listar produtos cadastrados");
        System.out.println("3 - Buscar produto por ID");
        System.out.println("4 - Adicionar produto no carrinho");
        System.out.println("5 - Mostrar carrinho e total");
        System.out.println("6 - Finalizar pedido com pagamento");
        System.out.println("7 - Buscar pedido por ID");
        System.out.println("8 - Gerar relatório de vendas");
        System.out.println("9 - Remover produto por ID");
        System.out.println("10 - Testar erro de estoque insuficiente");
        System.out.println("11 - Testar todos os tipos de pagamento");
        System.out.println("12 - Testar logout");
        System.out.println("0 - Sair");
        System.out.println("====================================================");
    }

    private static void criarProdutosIniciais() {
        Produto produto1 = new ProdutoFisico(1, "Teclado Gamer", 250.00, 10, 0.8);
        Produto produto2 = new ProdutoFisico(2, "Mouse Gamer", 120.00, 15, 0.3);
        Produto produto3 = new ProdutoDigital(3, "Curso de Java", 99.90, 50, "www.download.com/java");
        Produto produto4 = new ProdutoDigital(4, "E-book de POO", 39.90, 100, "www.download.com/poo");

        produtoRepository.salvar(produto1);
        produtoRepository.salvar(produto2);
        produtoRepository.salvar(produto3);
        produtoRepository.salvar(produto4);

        administrador.cadastrarProduto(produto1);
        administrador.cadastrarProduto(produto2);
        administrador.cadastrarProduto(produto3);
        administrador.cadastrarProduto(produto4);
    }

    private static void testarLogin() {
        administrador.login("Administrador", 1);
        cliente.login("Vinicius", 2);

        System.out.println("Administrador logado? " + administrador.isLogado());
        System.out.println("Cliente logado? " + cliente.isLogado());
    }

    private static void testarLogout() {
        administrador.logout();
        cliente.logout();

        System.out.println("Administrador logado? " + administrador.isLogado());
        System.out.println("Cliente logado? " + cliente.isLogado());
    }

    private static void listarProdutos() {
        System.out.println("--- PRODUTOS CADASTRADOS ---");

        for (Produto produto : produtoRepository.listarTodos()) {
            System.out.println("ID: " + produto.getId());
            System.out.println("Nome: " + produto.getNome());
            System.out.println("Preço: R$ " + produto.getPreco());
            System.out.println("Estoque: " + produto.getQuantidadeEmEstoque());
            System.out.println("----------------------------");
        }
    }

    private static void buscarProdutoPorId() {
        int id = lerInteiro("Digite o ID do produto: ");
        Produto produto = produtoRepository.buscar(id);

        if (produto != null) {
            System.out.println("Produto encontrado: " + produto.getNome());
            System.out.println("Preço: R$ " + produto.getPreco());
            System.out.println("Estoque: " + produto.getQuantidadeEmEstoque());
        } else {
            System.out.println("Produto não encontrado.");
        }
    }

    private static void adicionarProdutoNoCarrinho() {
        int id = lerInteiro("Digite o ID do produto que deseja adicionar: ");
        Produto produto = produtoRepository.buscar(id);

        if (produto == null) {
            System.out.println("Produto não encontrado.");
            return;
        }

        int quantidade = lerInteiro("Digite a quantidade: ");
        ItemCarrinho item = new ItemCarrinho(produto, quantidade);

        try {
            cliente.adicionarCarrinho(item);
        } catch (EstoqueInsufisienteException e) {
            System.out.println("Erro ao adicionar item no carrinho: " + e.getMessage());
        }
    }

    private static void mostrarCarrinho() {
        System.out.println("--- CARRINHO DO CLIENTE ---");

        if (cliente.getCarrinho().getItens().isEmpty()) {
            System.out.println("Carrinho vazio.");
            return;
        }

        for (ItemCarrinho item : cliente.getCarrinho().getItens()) {
            System.out.println("Produto: " + item.getProduto().getNome());
            System.out.println("Quantidade: " + item.getQuantidade());
            System.out.println("Subtotal: R$ " + item.subtotal());
            System.out.println("---------------------------");
        }

        System.out.println("Total: R$ " + cliente.getCarrinho().calcularTotal());
    }

    private static void finalizarPedidoComPagamento() {
        if (cliente.getCarrinho().getItens().isEmpty()) {
            System.out.println("Não é possível finalizar um pedido com carrinho vazio.");
            return;
        }

        System.out.println("Escolha a forma de pagamento:");
        System.out.println("1 - Pix");
        System.out.println("2 - Cartão");
        System.out.println("3 - Boleto");

        int opcao = lerInteiro("Opção: ");
        IPagamento formaPagamento = escolherFormaPagamento(opcao);

        if (formaPagamento == null) {
            System.out.println("Forma de pagamento inválida.");
            return;
        }

        try {
            int idPedido = pedidoRepository.listarTodos().size() + 1;
            Pedido pedido = new Pedido(idPedido, 0);
            pedido.finalizar(cliente.getCarrinho(), formaPagamento);

            pedidoRepository.salvar(pedido);

            System.out.println("Pedido salvo com sucesso.");
            System.out.println("ID do pedido: " + pedido.getId());
            System.out.println("Valor total: R$ " + pedido.getValorTotal());
        } catch (PagamentoException e) {
            System.out.println("Erro ao finalizar pagamento: " + e.getMessage());
        }
    }

    private static IPagamento escolherFormaPagamento(int opcao) {
        switch (opcao) {
            case 1:
                return new PagamentoPix("vinicius@email.com");
            case 2:
                return new PagamentoCartao("1234567890123456");
            case 3:
                return new PagamentoBoleto("00190.00009 01234.567891 23456.789012 3 12340000010000");
            default:
                return null;
        }
    }

    private static void buscarPedidoPorId() {
        int id = lerInteiro("Digite o ID do pedido: ");
        Pedido pedido = pedidoRepository.buscar(id);

        if (pedido != null) {
            System.out.println("Pedido encontrado: #" + pedido.getId());
            System.out.println("Valor total: R$ " + pedido.getValorTotal());
        } else {
            System.out.println("Pedido não encontrado.");
        }
    }

    private static void removerProdutoPorId() {
        int id = lerInteiro("Digite o ID do produto que deseja remover: ");
        produtoRepository.remover(id);
    }

    private static void testarEstoqueInsuficiente() {
        Produto produto = produtoRepository.buscar(1);

        if (produto == null) {
            System.out.println("Produto de teste não encontrado.");
            return;
        }

        ItemCarrinho itemComQuantidadeAlta = new ItemCarrinho(produto, 999);

        try {
            cliente.adicionarCarrinho(itemComQuantidadeAlta);
        } catch (EstoqueInsufisienteException e) {
            System.out.println("Teste de estoque insuficiente funcionando.");
            System.out.println("Mensagem: " + e.getMessage());
        }
    }

    private static void testarTodosOsPagamentos() {
        double valorTeste = 100.00;

        IPagamento pix = new PagamentoPix("vinicius@email.com");
        IPagamento cartao = new PagamentoCartao("1234567890123456");
        IPagamento boleto = new PagamentoBoleto("00190.00009 01234.567891 23456.789012 3 12340000010000");

        testarPagamento("Pix", pix, valorTeste);
        testarPagamento("Cartão", cartao, valorTeste);
        testarPagamento("Boleto", boleto, valorTeste);
    }

    private static void testarPagamento(String nomePagamento, IPagamento pagamento, double valor) {
        try {
            boolean sucesso = pagamento.processarPagamento(valor);

            if (sucesso) {
                System.out.println(nomePagamento + " processado com sucesso.");
            } else {
                System.out.println(nomePagamento + " recusado.");
            }
        } catch (PagamentoException e) {
            System.out.println("Erro no pagamento " + nomePagamento + ": " + e.getMessage());
        }
    }

    private static int lerInteiro(String mensagem) {
        System.out.print(mensagem);

        while (!scanner.hasNextInt()) {
            System.out.println("Digite um número válido.");
            scanner.nextLine();
            System.out.print(mensagem);
        }

        int numero = scanner.nextInt();
        scanner.nextLine();
        return numero;
    }
}
