package br.edu.ifsp.hto.cooperativa.estoque.modelo.vo;

import java.sql.Timestamp;

public class PrecoPPAVO{
    private Timestamp data_inicio;
    private EspecieVO especie;
    private Timestamp data_final;
    private float valor;
    
    public PrecoPPAVO(Timestamp data_inicio, EspecieVO especie, Timestamp data_final, float valor){
        this.data_inicio = data_inicio;
        this.especie = especie;
        this.data_final = data_final;
        this.valor = valor;
    }
    
    public Timestamp getData_inicio() {
        return data_inicio;
    }

    public Timestamp getData_final() {
        return data_final;
    }

    public EspecieVO getEspecie() {
        return especie;
    }

    public float getValor() {
        return valor;
    }

    public void setData_inicio(Timestamp data_inicio) {
        this.data_inicio = data_inicio;
    }

    public void setData_final(Timestamp data_final) {
        this.data_final = data_final;
    }

    public void setEspecie(EspecieVO especie) {
        this.especie = especie;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }
}
