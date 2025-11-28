package br.edu.ifsp.hto.cooperativa.planejamento.visao.componentes;

import javax.swing.*;

import br.edu.ifsp.hto.cooperativa.planejamento.visao.estilo.Tema;

import java.awt.*;

public class Cabecalho extends JPanel {
    
    private JLabel titulo;

    public JLabel getTitulo() {
        return titulo;
    }

    public Cabecalho(String tituloTexto) {
        setLayout(new BorderLayout());
        setBackground(Tema.COR_SECUNDARIA);
        setPreferredSize(new Dimension(0, 80));

        titulo = new JLabel(tituloTexto);
        titulo.setFont(Tema.FONTE_TITULO);
        titulo.setForeground(Tema.COR_PRIMARIA);
        
        JPanel margem = new JPanel(new FlowLayout(FlowLayout.LEFT, 30, 25));
        margem.setOpaque(false);
        margem.add(titulo);
        
        add(margem, BorderLayout.CENTER);
    }
}