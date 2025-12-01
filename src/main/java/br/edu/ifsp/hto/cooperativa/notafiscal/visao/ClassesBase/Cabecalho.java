package br.edu.ifsp.hto.cooperativa.notafiscal.visao.ClassesBase;

import javax.swing.*;
import br.edu.ifsp.hto.cooperativa.notafiscal.visao.ClassesBase.Tema;
import java.awt.*;

public class Cabecalho extends JPanel {
    private JLabel titulo;

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

    public void setTextoTitulo(String texto) {
        this.titulo.setText(texto);
    }
}