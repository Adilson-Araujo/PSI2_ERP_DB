package br.edu.ifsp.hto.cooperativa.planejamento.visao.telas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import br.edu.ifsp.hto.cooperativa.planejamento.visao.base.VisaoBase;
import br.edu.ifsp.hto.cooperativa.planejamento.visao.estilo.Tema;

public class VisaoAreas extends VisaoBase {

    public VisaoAreas() {
        super("Gerenciamento de Áreas");
    }

    @Override
    protected JPanel getPainelConteudo() {
        JPanel painel = new JPanel(new BorderLayout());
        painel.setBackground(Tema.COR_BRANCA);

        JLabel labelMock = new JLabel("Aqui entrará o CRUD de Áreas (Tabela e Formulário)", SwingConstants.CENTER);
        labelMock.setFont(new Font("SansSerif", Font.PLAIN, 18));
        labelMock.setForeground(Color.GRAY);

        painel.add(labelMock, BorderLayout.CENTER);

        return painel;
    }
}