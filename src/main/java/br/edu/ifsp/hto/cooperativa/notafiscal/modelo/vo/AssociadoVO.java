package br.edu.ifsp.hto.cooperativa.notafiscal.modelo.vo;

import java.time.LocalDateTime;

public class AssociadoVO {
    private Long id;
    private String cnpj;
    private String razaoSocial;
    private String nomeFantasia;
    private String inscricaoEstadual;
    private String inscricaoMunicipal;
    private Long enderecoId;
    private String telefone;
    private String email;
    private LocalDateTime dataCadastrado;
    private Boolean ativo;

    public AssociadoVO() {}

    // getters e setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getCnpj() { return cnpj; }
    public void setCnpj(String cnpj) { this.cnpj = cnpj; }
    public String getRazaoSocial() { return razaoSocial; }
    public void setRazaoSocial(String razaoSocial) { this.razaoSocial = razaoSocial; }
    public String getNomeFantasia() { return nomeFantasia; }
    public void setNomeFantasia(String nomeFantasia) { this.nomeFantasia = nomeFantasia; }
    public String getInscricaoEstadual() { return inscricaoEstadual; }
    public void setInscricaoEstadual(String inscricaoEstadual) { this.inscricaoEstadual = inscricaoEstadual; }
    public String getInscricaoMunicipal() { return inscricaoMunicipal; }
    public void setInscricaoMunicipal(String inscricaoMunicipal) { this.inscricaoMunicipal = inscricaoMunicipal; }
    public Long getEnderecoId() { return enderecoId; }
    public void setEnderecoId(Long enderecoId) { this.enderecoId = enderecoId; }
    public String getTelefone() { return telefone; }
    public void setTelefone(String telefone) { this.telefone = telefone; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public LocalDateTime getDataCadastrado() { return dataCadastrado; }
    public void setDataCadastrado(LocalDateTime dataCadastrado) { this.dataCadastrado = dataCadastrado; }
    public Boolean getAtivo() { return ativo; }
    public void setAtivo(Boolean ativo) { this.ativo = ativo; }

    @Override
    public String toString() {
        return "AssociadoVO{id=" + id + ", razaoSocial=" + razaoSocial + "}";
    }
}
