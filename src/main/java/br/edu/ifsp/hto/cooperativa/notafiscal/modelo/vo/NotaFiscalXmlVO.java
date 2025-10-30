package br.edu.ifsp.hto.cooperativa.notafiscal.modelo.vo;

public class NotaFiscalXmlVO {
    private Long id;
    private String hash;
    private String conteudo;

    public NotaFiscalXmlVO() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getHash() { return hash; }
    public void setHash(String hash) { this.hash = hash; }
    public String getConteudo() { return conteudo; }
    public void setConteudo(String conteudo) { this.conteudo = conteudo; }

    @Override
    public String toString() {
        return "NotaFiscalXmlVO{id=" + id + "}";
    }
}
