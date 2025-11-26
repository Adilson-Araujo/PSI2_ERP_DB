package br.edu.ifsp.hto.cooperativa.sessao.modelo.negocios;

import br.edu.ifsp.hto.cooperativa.notafiscal.controlador.AssociadoControlador;
import br.edu.ifsp.hto.cooperativa.sessao.modelo.dao.UsuarioDAO;
import br.edu.ifsp.hto.cooperativa.sessao.modelo.dto.UsuarioTO;
import br.edu.ifsp.hto.cooperativa.sessao.modelo.vo.UsuarioVO;

import java.sql.SQLException;

public class Sessao {
    private static UsuarioTO usuarioLogado;

    public static void login (UsuarioVO usuarioVO)
    {
        if (usuarioVO.getNomeUsuario() == null|| usuarioVO.getNomeUsuario().isEmpty())
            throw new IllegalArgumentException("Nome de usuário deve ser informado");
        if (usuarioVO.getSenha() == null || usuarioVO.getSenha().isEmpty())
            throw new IllegalArgumentException("Senha deve ser informada");
        var usuarioDAO = new UsuarioDAO();
        try {
            var usuarioEncontrado = usuarioDAO.buscarPorNomeUsuario(usuarioVO.getNomeUsuario());
            if (usuarioVO.getSenha().equals(usuarioEncontrado.getSenha()))
            {
                usuarioLogado = new UsuarioTO();
                usuarioLogado.usuarioVO = usuarioEncontrado;
                var associadoControlador = new AssociadoControlador();
                usuarioLogado.associadoTO = associadoControlador.obter(usuarioLogado.usuarioVO.getId());

            }
            else
                throw new IllegalArgumentException("Senha incorreta");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static UsuarioTO getUsuarioLogado()
    {
        if (usuarioLogado == null)
            throw new RuntimeException("Nenhum usuário logado no sistema");

        return usuarioLogado;
    }

    public static long getAssociadoIdLogado() {
        if (usuarioLogado == null || usuarioLogado.usuarioVO == null)
            throw new RuntimeException("Nenhum usuário logado no sistema");

        return usuarioLogado.usuarioVO.getAssociadoId();  
    }

}
