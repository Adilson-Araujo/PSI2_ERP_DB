package br.edu.ifsp.hto.cooperativa.planejamento.visao;

import javax.swing.JFrame;

import br.edu.ifsp.hto.cooperativa.sessao.controlador.SessaoControlador;
import br.edu.ifsp.hto.cooperativa.sessao.modelo.dto.UsuarioTO;

public class TelaPrincipal extends JFrame {
    private SessaoControlador sc = new SessaoControlador();

    public TelaPrincipal() {
        setSize(1280, 720);
        setVisible(true);
        UsuarioTO usuarioTO = sc.obterUsuarioLogado();
        System.out.println(usuarioTO.associadoTO.associado.getId());
    }
}
