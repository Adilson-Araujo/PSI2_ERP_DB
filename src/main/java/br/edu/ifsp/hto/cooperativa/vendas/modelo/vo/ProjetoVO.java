package br.edu.ifsp.hto.cooperativa.vendas.modelo.vo;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class ProjetoVO {

    private Long id = null; // garante que nunca começa como 0
    private String nomeProjeto;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataFinal;
    private BigDecimal orcamento;

    public ProjetoVO() {}

    public Long getId() { 
        return id == null || id == 0 ? null : id; 
    }

    public void setId(Long id) { 
        this.id = (id != null && id == 0) ? null : id; 
    }

    public String getNomeProjeto() { return nomeProjeto; }
    public void setNomeProjeto(String nomeProjeto) { this.nomeProjeto = nomeProjeto; }

    public LocalDateTime getDataCriacao() { return dataCriacao; }
    public void setDataCriacao(LocalDateTime dataCriacao) { this.dataCriacao = dataCriacao; }

    public LocalDateTime getDataFinal() { return dataFinal; }
    public void setDataFinal(LocalDateTime dataFinal) { this.dataFinal = dataFinal; }

    public BigDecimal getOrcamento() { return orcamento; }
    public void setOrcamento(BigDecimal orcamento) { this.orcamento = orcamento; }
}
