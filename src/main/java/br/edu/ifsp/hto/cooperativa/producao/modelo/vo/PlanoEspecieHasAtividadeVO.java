package br.edu.ifsp.hto.cooperativa.producao.modelo.vo;

public class PlanoEspecieHasAtividadeVO {

    private long planoEspecieId;
    private long atividadeId;

    public PlanoEspecieHasAtividadeVO() {
    }

    public PlanoEspecieHasAtividadeVO(long planoEspecieId, long atividadeId) {
        this.planoEspecieId = planoEspecieId;
        this.atividadeId = atividadeId;
    }

    public long getPlanoEspecieId() {
        return planoEspecieId;
    }

    public void setPlanoEspecieId(long planoEspecieId) {
        this.planoEspecieId = planoEspecieId;
    }

    public long getAtividadeId() {
        return atividadeId;
    }

    public void setAtividadeId(long atividadeId) {
        this.atividadeId = atividadeId;
    }

    @Override
    public String toString() {
        return "PlanoEspecieHasAtividadeVO{" +
                "planoEspecieId=" + planoEspecieId +
                ", atividadeId=" + atividadeId +
                '}';
    }
}

