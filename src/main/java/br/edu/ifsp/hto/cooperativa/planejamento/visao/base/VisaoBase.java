package br.edu.ifsp.hto.cooperativa.planejamento.visao.base;

import javax.swing.*;
import java.awt.*;

import br.edu.ifsp.hto.cooperativa.planejamento.visao.componentes.Cabecalho;
import br.edu.ifsp.hto.cooperativa.planejamento.visao.componentes.MenuLateral;
import br.edu.ifsp.hto.cooperativa.planejamento.visao.estilo.Tema;
import br.edu.ifsp.hto.cooperativa.planejamento.visao.telas.VisaoAreas;
import br.edu.ifsp.hto.cooperativa.planejamento.visao.telas.VisaoHome;
import br.edu.ifsp.hto.cooperativa.planejamento.visao.telas.VisaoMateriais;

public abstract class VisaoBase extends JInternalFrame implements NavegadorTelas {

    private Cabecalho cabecalho;
    public JDesktopPane parent;

    public VisaoBase(String tituloPagina, JDesktopPane parent) {
        super("Planejamento de Produção"); // Título da Janela
        this.parent = parent;
        configurarJanela();
        montarLayoutBase(tituloPagina);
    }

    private void configurarJanela() {
        setSize(1024, 768); // Tamanho HD padrão
        setClosable(true);
        setLayout(new BorderLayout());

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (screenSize.width - getWidth()) / 2;
        int y = (screenSize.height - getHeight()) / 2;

        setLocation(x, y);
        setVisible(true);
    }

    public void setTitulo(String titulo) {
        cabecalho.getTitulo().setText(titulo);
    }

    private void montarLayoutBase(String tituloPagina) {
        // 1. Menu Lateral (Esquerda)
        add(new MenuLateral(this), BorderLayout.WEST);

        // 2. Painel Central (Direita)
        JPanel painelDireito = new JPanel(new BorderLayout());
        
        cabecalho = new Cabecalho(tituloPagina);

        // 2.1 Cabeçalho (Topo do painel direito)
        painelDireito.add(cabecalho, BorderLayout.NORTH);

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

    // --- Navegação Otimizada ---
    
    @Override
    public void abrirInicio() {
        // Verifica se JÁ ESTOU na Home para não recarregar à toa
        if (!(this instanceof VisaoHome)) {
            this.dispose();
            new VisaoHome(parent).setVisible(true);
        }
    }

    @Override
    public void abrirAreas() {
        // Verifica se JÁ ESTOU em Áreas
        if (!(this instanceof VisaoAreas)) {
            this.dispose();
            new VisaoAreas(parent).setVisible(true);
        }
    }

    // --- Outras navegações (Futuro) ---
    @Override public void abrirMateriais() {
        if (!(this instanceof VisaoMateriais)) {
            this.dispose();
            new VisaoMateriais(parent).setVisible(true);
        }
    }

    @Override public void abrirTalhoes() {}
    @Override public void abrirPlanos() {}
    @Override public void abrirAtividades() {}
}