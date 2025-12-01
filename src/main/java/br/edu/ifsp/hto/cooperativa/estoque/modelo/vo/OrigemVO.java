// Pacote, não faz diferença, da para mudar depois
package br.edu.ifsp.hto.cooperativa.estoque.modelo.vo;

// Declaração da classe
public class OrigemVO{
    // Atributos (Id final)
    private int id;
    private String nome;

    // Construtor, insere os valores.
    public OrigemVO(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    // Getters e Setters (sem setter para id)
    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }
    
    public void setId(int id){
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    // toString opcional para debug e exibição
    @Override
    public String toString() {
        return "Origem { " +
               "id = " + id +
               ", nome = '" + nome + '\'' +
               " }";
    }
}
