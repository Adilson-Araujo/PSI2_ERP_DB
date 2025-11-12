package br.edu.ifsp.hto.cooperativa.producao.modelo.vo;

public class PlanoEspecieVO {
    private Integer id;
    private Integer especieId;
    private String nomePlano;

    public PlanoEspecieVO() {}

    // Getters e Setters
    public Integer getId() { 
        return id; 
    }
    public void setId(Integer id) { 
        this.id = id; 
    }

    public Integer getEspecieId() { 
        return especieId; 
    }
    public void setEspecieId(Integer especieId) { 
        this.especieId = especieId; 
    }

    public String getNomePlano() { 
        return nomePlano; 
    }
    public void setNomePlano(String nomePlano) { 
        this.nomePlano = nomePlano; 
    }

    @Override
    public String toString() {
        return "PlanoEspecieVO{" +
                "id=" + id +
                ", especieId=" + especieId +
                ", nomePlano='" + nomePlano + '\'' +
                '}';
    }
}
