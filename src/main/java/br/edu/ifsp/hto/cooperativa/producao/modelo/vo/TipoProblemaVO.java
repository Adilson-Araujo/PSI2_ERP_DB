package br.edu.ifsp.hto.cooperativa.producao.modelo.vo;

public class TipoProblemaVO {

    private long id;
    private String descricao;

    public TipoProblemaVO() {
    }

    public TipoProblemaVO(long id, String descricao) {
        this.id = id;
        this.descricao = descricao;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public String toString() {
        return "TipoProblemaVO{" +
                "id=" + id +
                ", descricao='" + descricao + '\'' +
                '}';
    }
}
