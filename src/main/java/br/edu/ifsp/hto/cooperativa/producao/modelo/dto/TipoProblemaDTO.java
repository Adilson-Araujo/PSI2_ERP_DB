package br.edu.ifsp.hto.cooperativa.producao.modelo.dto;

public class TipoProblemaDTO {
    private Long id;
    private String descricao;

    public TipoProblemaDTO(Long id, String descricao) {
        this.id = id;
        this.descricao = descricao;
    }

    public Long getId() { return id; }
    
    @Override
    public String toString() {
        return descricao; // Importante para o JComboBox exibir o nome
    }
}
