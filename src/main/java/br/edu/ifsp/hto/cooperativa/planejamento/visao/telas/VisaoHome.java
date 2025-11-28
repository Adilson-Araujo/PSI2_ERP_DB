package br.edu.ifsp.hto.cooperativa.planejamento.visao.telas;

import java.awt.GridLayout;

import java.util.List;

import javax.swing.JPanel;

import br.edu.ifsp.hto.cooperativa.planejamento.controle.PlanejamentoControle;
import br.edu.ifsp.hto.cooperativa.planejamento.visao.base.VisaoBase;
import br.edu.ifsp.hto.cooperativa.planejamento.visao.componentes.CardDashboard;
import br.edu.ifsp.hto.cooperativa.planejamento.visao.estilo.Tema;
import br.edu.ifsp.hto.cooperativa.sessao.controlador.SessaoControlador;

import br.edu.ifsp.hto.cooperativa.planejamento.modelo.VO.AreaVO;
import br.edu.ifsp.hto.cooperativa.planejamento.modelo.VO.AtividadeVO;

public class VisaoHome extends VisaoBase {

    public VisaoHome() {
        super("Início");  // Título da Janela
    }

    @Override
    protected JPanel getPainelConteudo() { 
        JPanel painel = new JPanel(new GridLayout(2, 2, 20, 20));
        painel.setBackground(Tema.COR_FUNDO);

        SessaoControlador sc = new SessaoControlador();
        int associadoId = sc.obterUsuarioLogado().associadoTO.associado.getId().intValue();

        PlanejamentoControle pc = new PlanejamentoControle();
        List<AreaVO> areas = pc.buscarAreasDoAssociado(associadoId);

        List<AtividadeVO> atividades = pc.listarAtividades();


        painel.add(new CardDashboard("Planos Criados: " + pc.qtdPlanoAssociado(associadoId)));
        painel.add(new CardDashboard("Áreas Ativas: " + areas.size()));
        painel.add(new CardDashboard("Próximas Atividades: " + atividades.get(0).getNomeAtividade()));
        painel.add(new CardDashboard("Alertas de Estoque"));

        return painel;
    }

}