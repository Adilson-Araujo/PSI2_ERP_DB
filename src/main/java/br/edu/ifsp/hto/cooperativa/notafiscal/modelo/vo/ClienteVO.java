package br.edu.ifsp.hto.cooperativa.notafiscal.modelo.vo;

import java.time.LocalDateTime;

public class ClienteVO {
    private Long id;
    private String nomeFantasia;
    private String razaoSocial;
    private Long enderecoId;
    private String telefone;
    private String email;
    private LocalDateTime dataCadastro;
    private Boolean ativo;
    private String cpfCnpj;

    public ClienteVO() {}

    // getters e setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNomeFantasia() { return nomeFantasia; }
    public void setNomeFantasia(String nomeFantasia) { this.nomeFantasia = nomeFantasia; }
    public String getRazaoSocial() { return razaoSocial; }
    public void setRazaoSocial(String razaoSocial) { this.razaoSocial = razaoSocial; }
    public Long getEnderecoId() { return enderecoId; }
    public void setEnderecoId(Long enderecoId) { this.enderecoId = enderecoId; }
    public String getTelefone() { return telefone; }
    public void setTelefone(String telefone) { this.telefone = telefone; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public LocalDateTime getDataCadastro() { return dataCadastro; }
    public void setDataCadastro(LocalDateTime dataCadastro) { this.dataCadastro = dataCadastro; }
    public Boolean getAtivo() { return ativo; }
    public void setAtivo(Boolean ativo) { this.ativo = ativo; }
    public String getCpfCnpj() { return cpfCnpj; }
    public void setCpfCnpj(String cpfCnpj) { this.cpfCnpj = cpfCnpj; }

    @Override
    public String toString() {
        return "ClienteVO{id=" + id + ", razaoSocial=" + razaoSocial + "}";
    }
}
