package br.edu.ifsp.hto.cooperativa.vendas.visao;

import javax.swing.*;

public class MainWindow {

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            new ProdutorMainView().setVisible(true);
        });
    }
}
