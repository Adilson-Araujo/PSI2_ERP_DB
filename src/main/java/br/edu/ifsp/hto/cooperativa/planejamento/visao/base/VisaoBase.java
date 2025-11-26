package br.edu.ifsp.hto.cooperativa.planejamento.visao.base;

import javax.swing.*;

import br.edu.ifsp.hto.cooperativa.planejamento.visao.componentes.Cabecalho;
import br.edu.ifsp.hto.cooperativa.planejamento.visao.componentes.MenuLateral;
import br.edu.ifsp.hto.cooperativa.planejamento.visao.estilo.Tema;
import br.edu.ifsp.hto.cooperativa.planejamento.visao.telas.VisaoAreas;

import java.awt.*;

public abstract class VisaoBase extends JFrame implements NavegadorTelas {

    public VisaoBase(String tituloPagina) {
        super("Planejamento de Produção"); // Título da Janela do SO
        configurarJanela();
        montarLayoutBase(tituloPagina);
    }

    private void configurarJanela() {
        setSize(1024, 768); // Tamanho HD padrão
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null); // Centraliza na tela
    }

    private void montarLayoutBase(String tituloPagina) {
        // 1. Menu Lateral (Esquerda)
        add(new MenuLateral(this), BorderLayout.WEST);

        // 2. Painel Central (Direita)
        JPanel painelDireito = new JPanel(new BorderLayout());
        
        // 2.1 Cabeçalho (Topo do painel direito)
        painelDireito.add(new Cabecalho(tituloPagina), BorderLayout.NORTH);

        // 2.2 Conteúdo Específico (Centro do painel direito)
        JPanel containerConteudo = new JPanel(new BorderLayout());
        containerConteudo.setBackground(Tema.COR_FUNDO);
        containerConteudo.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Margem interna
        
        // Aqui injetamos o conteúdo da tela filha
        containerConteudo.add(getPainelConteudo(), BorderLayout.CENTER);
        
        painelDireito.add(containerConteudo, BorderLayout.CENTER);

        add(painelDireito, BorderLayout.CENTER);
    }

    // Cada tela filha deve implementar isso para desenhar seu miolo
    protected abstract JPanel getPainelConteudo();

    // --- Navegação (Pode ser melhorada futuramente) ---
    @Override
    public void abrirAreas() {
        trocarTela(new VisaoAreas());
    }

    // Implemente as outras...
    @Override public void abrirMateriais() {}
    @Override public void abrirTalhoes() {}
    @Override public void abrirPlanos() {}
    @Override public void abrirAtividades() {}

    private void trocarTela(JFrame novaTela) {
        this.dispose();
        novaTela.setVisible(true);
    }
}