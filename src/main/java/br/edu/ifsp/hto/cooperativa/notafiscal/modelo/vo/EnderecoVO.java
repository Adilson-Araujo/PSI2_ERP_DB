package br.edu.ifsp.hto.cooperativa.notafiscal.modelo.vo;

public class EnderecoVO {
    private Long id;
    private String estado;
    private String cidade;
    private String bairro;
    private String rua;
    private Integer numero;
    private String cep;

    public EnderecoVO() {}

    public EnderecoVO(String estado, String cidade, String bairro, String rua, Integer numero, String cep) {
        this.id = id; this.estado = estado; this.cidade = cidade; this.bairro = bairro;
        this.rua = rua; this.numero = numero; this.cep = cep;
    }

    // getters e setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
    public String getCidade() { return cidade; }
    public void setCidade(String cidade) { this.cidade = cidade; }
    public String getBairro() { return bairro; }
    public void setBairro(String bairro) { this.bairro = bairro; }
    public String getRua() { return rua; }
    public void setRua(String rua) { this.rua = rua; }
    public Integer getNumero() { return numero; }
    public void setNumero(Integer numero) { this.numero = numero; }
    public String getCep() { return cep; }
    public void setCep(String cep) { this.cep = cep; }

    @Override
    public String toString() {
        return "EnderecoVO{id=" + id + ", estado=" + estado + ", cidade=" + cidade + "}";
    }
}
