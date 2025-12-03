package br.edu.ifsp.hto.cooperativa.producao.modelo.dto;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CulturaDTO {
    private Long idOrdemProducao;
    private String nomeEspecie;
    private Date dataInicio;
    private Date dataFim;

    public CulturaDTO(Long id, String nome, Date inicio, Date fim) {
        this.idOrdemProducao = id;
        this.nomeEspecie = nome;
        this.dataInicio = inicio;
        this.dataFim = fim;
    }

    public Long getIdOrdemProducao() { return idOrdemProducao; }

    @Override
    public String toString() {
        // Formata para o Renderer da View (com quebra de linha)
        SimpleDateFormat sdf = new SimpleDateFormat("MM/yyyy");
        String inicio = (dataInicio != null) ? sdf.format(dataInicio) : "?";
        String fim = (dataFim != null) ? sdf.format(dataFim) : "?";
        return nomeEspecie.toUpperCase() + "\n" + inicio + " - " + fim;
    }
}