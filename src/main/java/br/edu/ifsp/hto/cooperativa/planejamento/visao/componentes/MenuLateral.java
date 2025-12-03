package br.edu.ifsp.hto.cooperativa.planejamento.visao.componentes;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import br.edu.ifsp.hto.cooperativa.planejamento.visao.base.NavegadorTelas;
import br.edu.ifsp.hto.cooperativa.planejamento.visao.estilo.Tema;

import java.awt.*;

public class MenuLateral extends JPanel {

    private final NavegadorTelas navegador;

    public MenuLateral(NavegadorTelas navegador) {
        this.navegador = navegador;
        configurarLayout();
        montarBotoes();
    }

    private void configurarLayout() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(new EmptyBorder(30, 10, 15, 10));
        setBackground(Tema.COR_PRIMARIA);
        setPreferredSize(new Dimension(180, 0));
    }

    private void montarBotoes() {
        // --- ALTERAÇÃO AQUI: Chama o método do navegador ---
        adicionarBotao("Início", navegador::abrirInicio);
        
        adicionarBotao("Áreas", navegador::abrirAreas);
        adicionarBotao("Materiais", navegador::abrirMateriais);
    }

    private void adicionarBotao(String texto, Runnable acao) {
        JButton botao = new JButton(texto);
        
        botao.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        botao.setAlignmentX(Component.CENTER_ALIGNMENT);
        botao.setBackground(Tema.COR_BRANCA);
        botao.setForeground(Tema.COR_TEXTO);
        botao.setFocusPainted(false);
        botao.setFont(new Font("SansSerif", Font.BOLD, 12));

        botao.addActionListener(e -> acao.run());

        add(botao);
        add(Box.createVerticalStrut(15));
    }
}