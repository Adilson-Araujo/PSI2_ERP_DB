package br.edu.ifsp.hto.cooperativa.planejamento.visao.telas;

import java.awt.GridLayout;

import javax.swing.JPanel;

import br.edu.ifsp.hto.cooperativa.planejamento.visao.base.VisaoBase;
import br.edu.ifsp.hto.cooperativa.planejamento.visao.componentes.CardDashboard;
import br.edu.ifsp.hto.cooperativa.planejamento.visao.estilo.Tema;

public class VisaoHome extends VisaoBase {

    public VisaoHome() {
        super("Início");
    }

    @Override
    protected JPanel getPainelConteudo() {
        JPanel painel = new JPanel(new GridLayout(2, 2, 20, 20));
        painel.setBackground(Tema.COR_FUNDO);

        painel.add(new CardDashboard("Planos Criados: 5"));
        painel.add(new CardDashboard("Áreas Ativas: 2"));
        painel.add(new CardDashboard("Próximas Atividades"));
        painel.add(new CardDashboard("Alertas de Estoque"));

        return painel;
    }
}