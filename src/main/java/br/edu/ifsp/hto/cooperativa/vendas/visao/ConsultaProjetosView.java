package br.edu.ifsp.hto.cooperativa.vendas.visao;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import br.edu.ifsp.hto.cooperativa.vendas.modelo.dao.ProjetoDAO;
import br.edu.ifsp.hto.cooperativa.vendas.modelo.vo.ProjetoVO;

public class ConsultaProjetosView extends BaseView {

    private JTable tabela;
    private DefaultTableModel model;
    private JTextField txtBusca;

    public ConsultaProjetosView() {
        super("Consulta de Projetos");
        add(criarPainel(), BorderLayout.CENTER);
        carregarProjetos(null);
    }

    private JPanel criarPainel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(0xE9, 0xE9, 0xE9));
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));

        // --- TOPO (Título + Busca) ---
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(new Color(0xE9, 0xE9, 0xE9));
        
        JLabel titulo = new JLabel("Consulta de Projetos", SwingConstants.CENTER);
        titulo.setFont(new Font("SansSerif", Font.BOLD, 22));
        titulo.setBorder(BorderFactory.createEmptyBorder(10, 0, 15, 0));
        topPanel.add(titulo, BorderLayout.NORTH);

        // Barra de Busca
        JPanel buscaPanel = new JPanel(new FlowLayout());
        buscaPanel.setBackground(new Color(0xE9, 0xE9, 0xE9));
        
        txtBusca = new JTextField(20);
        JButton btnBuscar = new JButton("Buscar");
        btnBuscar.addActionListener(e -> carregarProjetos(txtBusca.getText()));

        buscaPanel.add(new JLabel("Nome do Projeto: "));
        buscaPanel.add(txtBusca);
        buscaPanel.add(btnBuscar);
        
        topPanel.add(buscaPanel, BorderLayout.CENTER);
        panel.add(topPanel, BorderLayout.NORTH);

        // --- TABELA ---
        model = new DefaultTableModel(new Object[]{
                "ID", "Nome", "Data Criação", "Data Final", "Orçamento (R$)"
        }, 0);

        tabela = new JTable(model);
        tabela.setRowHeight(26);

        panel.add(new JScrollPane(tabela), BorderLayout.CENTER);
        return panel;
    }

    private void carregarProjetos(String termo) {
        model.setRowCount(0);
        
        if (termo == null) termo = ""; 

        List<ProjetoVO> lista = new ProjetoDAO().filtrarProjetos(termo);
        
        for (ProjetoVO p : lista) {
            model.addRow(new Object[]{
                    p.getId(),
                    p.getNomeProjeto(),
                    p.getDataCriacao(),
                    p.getDataFinal(),
                    p.getOrcamento()
            });
        }
    }
}