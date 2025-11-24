package br.edu.ifsp.hto.cooperativa.sessao.modelo.vo;

public class UsuarioVO {
    private Long id;
    private Long associadoId;
    private String nomeUsuario;
    private String senha;
    private Short tipoUsuario;
    private Boolean ativo;

    public UsuarioVO() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getAssociadoId() { return associadoId; }
    public void setAssociadoId(Long associadoId) { this.associadoId = associadoId; }

    public String getNomeUsuario() { return nomeUsuario; }
    public void setNomeUsuario(String nomeUsuario) { this.nomeUsuario = nomeUsuario; }

    public String getSenha() { return senha; }
    public void setSenha(String senha) { this.senha = senha; }

    public Short getTipoUsuario() { return tipoUsuario; }
    public void setTipoUsuario(Short tipoUsuario) { this.tipoUsuario = tipoUsuario; }

    public Boolean getAtivo() { return ativo; }
    public void setAtivo(Boolean deletado) { this.ativo = deletado; }

    @Override
    public String toString() {
        return "UsuarioVO{id=" + id + ", nomeUsuario='" + nomeUsuario + "'}";
    }
}
