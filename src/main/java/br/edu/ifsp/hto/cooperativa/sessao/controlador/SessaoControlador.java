package br.edu.ifsp.hto.cooperativa.sessao.controlador;

import br.edu.ifsp.hto.cooperativa.sessao.modelo.dto.UsuarioTO;
import br.edu.ifsp.hto.cooperativa.sessao.modelo.negocios.Sessao;
import br.edu.ifsp.hto.cooperativa.sessao.modelo.vo.UsuarioVO;

public class SessaoControlador {

    public void login(String nomeUsuario, String senha)
    {
        var usuario = new UsuarioVO();
        usuario.setNomeUsuario(nomeUsuario);
        usuario.setSenha(senha);
        Sessao.login(usuario);
    }

    public UsuarioTO obterUsuarioLogado()
    {
        return Sessao.getUsuarioLogado();
    }

}
