package br.edu.ifsp.hto.cooperativa.planejamento.visao.componentes;

import java.awt.Color;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

import br.edu.ifsp.hto.cooperativa.planejamento.visao.estilo.Tema;

public class CardDashboard extends JPanel {

    public CardDashboard(String texto) {
        setLayout(new GridBagLayout());
        setBackground(Tema.COR_BRANCA);
        setBorder(BorderFactory.createLineBorder(Color.GRAY));
        
        if (texto != null && !texto.isEmpty()) {
            JLabel label = new JLabel(texto);
            label.setFont(Tema.FONTE_TEXTO);
            add(label);
        }
    }
}