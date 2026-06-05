package br.inatel.sistema_carrinho_compras.user;

public abstract class Usuario {
    protected int id;
    protected String nome;
    protected String email;
    protected boolean logado;

    public Usuario (int id, String nome, String email)
    {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.logado = false;
    }

    public void login (String nome, int id)
    {
        if (this.logado) {
            System.out.println("Aviso: O usuário " + this.nome + " já está logado no sistema.");
            return;
        }

        if (this.nome.equals(nome) && this.id == id) {
            this.logado = true;
            System.out.println("[SUCESSO] Usuário " + this.nome + " logado com sucesso!");
        } else {
            System.out.println("[ERRO] Falha no login: Nome ou ID incorretos.");
        }
    }

    public void logout(){
        if (this.logado) {
            this.logado = false; // <-- Altera o estado de volta para false
            System.out.println("Usuário " + this.nome + " deslogou com sucesso.");
        } else {
            System.out.println("O usuário já está deslogado.");
        }

    }
    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public boolean isLogado() {
        return logado;
    }
}
