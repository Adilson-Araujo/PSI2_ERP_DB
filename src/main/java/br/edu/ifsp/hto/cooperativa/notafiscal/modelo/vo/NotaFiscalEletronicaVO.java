package br.edu.ifsp.hto.cooperativa.notafiscal.modelo.vo;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class NotaFiscalEletronicaVO {
    private Long id;
    private Long associadoId;
    private Long clienteId;
    private String chaveAcesso;
    private String razaoSocial;
    private LocalDateTime dataEmissao;
    private BigDecimal valorTotal;
    private Integer tipoAmbiente;
    private Integer tipoOperacao;
    private Integer tipoFormaEmissao;
    private Integer tipoStatusEnvioSefaz;
    private Integer numeroProtocolo;
    private LocalDateTime dataInclusao;
    private Boolean ativo;
    private String numeroNotaFiscal;
    private String numeroSerie;
    private String dadosAdicionais;
    private BigDecimal valorFrete;

    public NotaFiscalEletronicaVO() {}

    // getters e setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getAssociadoId() { return associadoId; }
    public void setAssociadoId(Long associadoId) { this.associadoId = associadoId; }
    public Long getClienteId() { return clienteId; }
    public void setClienteId(Long clienteId) { this.clienteId = clienteId; }
    public String getChaveAcesso() { return chaveAcesso; }
    public void setChaveAcesso(String chaveAcesso) { this.chaveAcesso = chaveAcesso; }
    public String getRazaoSocial() { return razaoSocial; }
    public void setRazaoSocial(String razaoSocial) { this.razaoSocial = razaoSocial; }
    public LocalDateTime getDataEmissao() { return dataEmissao; }
    public void setDataEmissao(LocalDateTime dataEmissao) { this.dataEmissao = dataEmissao; }
    public BigDecimal getValorTotal() { return valorTotal; }
    public void setValorTotal(BigDecimal valorTotal) { this.valorTotal = valorTotal; }
    public Integer getTipoAmbiente() { return tipoAmbiente; }
    public void setTipoAmbiente(Integer tipoAmbiente) { this.tipoAmbiente = tipoAmbiente; }
    public Integer getTipoOperacao() { return tipoOperacao; }
    public void setTipoOperacao(Integer tipoOperacao) { this.tipoOperacao = tipoOperacao; }
    public Integer getTipoFormaEmissao() { return tipoFormaEmissao; }
    public void setTipoFormaEmissao(Integer tipoFormaEmissao) { this.tipoFormaEmissao = tipoFormaEmissao; }
    public Integer getTipoStatusEnvioSefaz() { return tipoStatusEnvioSefaz; }
    public void setTipoStatusEnvioSefaz(Integer tipoStatusEnvioSefaz) { this.tipoStatusEnvioSefaz = tipoStatusEnvioSefaz; }
    public Integer getNumeroProtocolo() { return numeroProtocolo; }
    public void setNumeroProtocolo(Integer numeroProtocolo) { this.numeroProtocolo = numeroProtocolo; }
    public LocalDateTime getDataInclusao() { return dataInclusao; }
    public void setDataInclusao(LocalDateTime dataInclusao) { this.dataInclusao = dataInclusao; }
    public Boolean getAtivo() { return ativo; }
    public void setAtivo(Boolean ativo) { this.ativo = ativo; }
    public String getNumeroNotaFiscal() { return numeroNotaFiscal; }
    public void setNumeroNotaFiscal(String numeroNotaFiscal) { this.numeroNotaFiscal = numeroNotaFiscal; }
    public String getNumeroSerie() { return numeroSerie; }
    public void setNumeroSerie(String numeroSerie) { this.numeroSerie = numeroSerie; }
    public String getDadosAdicionais() { return dadosAdicionais; }
    public void setDadosAdicionais(String dadosAdicionais) { this.dadosAdicionais = dadosAdicionais; }
    public BigDecimal getValorFrete() { return valorFrete; }
    public void setValorFrete(BigDecimal valorFrete) { this.valorFrete = valorFrete; }

    @Override
    public String toString() {
        return "NotaFiscalEletronicaVO{id=" + id + ", chaveAcesso=" + chaveAcesso + "}";
    }
}
