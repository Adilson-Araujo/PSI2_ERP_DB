package br.edu.ifsp.hto.cooperativa.notafiscal.visao.ClassesBase;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import br.edu.ifsp.hto.cooperativa.notafiscal.visao.ClassesBase.Tema;
import java.awt.*;

public class MenuLateralNotaFiscal extends JPanel {

    private final NavegadorNotaFiscal navegador;

    public MenuLateralNotaFiscal(NavegadorNotaFiscal navegador) {
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
        // Defina aqui os botões do SEU módulo
        adicionarBotao("Associado", navegador::abrirCadastrarAssociado);
        adicionarBotao("Venda (NF-e)", navegador::abrirCadastrarVenda);
        adicionarBotao("Gerar NF", navegador::abrirGerarNFe);
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