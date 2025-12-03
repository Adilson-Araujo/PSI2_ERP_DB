package br.edu.ifsp.hto.cooperativa.vendas.visao;

import br.edu.ifsp.hto.cooperativa.vendas.modelo.dao.PedidoDAO;
import br.edu.ifsp.hto.cooperativa.vendas.modelo.vo.PedidoVO;
// 1. NOVOS IMPORTS DA SESSÃO
import br.edu.ifsp.hto.cooperativa.sessao.modelo.negocios.Sessao;
import br.edu.ifsp.hto.cooperativa.sessao.modelo.dto.UsuarioTO;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class ConsultaPedidosView extends BaseView {

    private JTable tabela;
    private DefaultTableModel model;
    private JTextField txtBusca; // Campo de Filtro
    
    // Constantes para facilitar leitura (Confirme os IDs no seu BD)
    private static final short TIPO_ASSOCIACAO = 1;
    private static final short TIPO_PRODUTOR = 2;
    
    private final PedidoDAO pedidoDAO = new PedidoDAO();

    public ConsultaPedidosView() {
        super("Consultar Pedidos");
        add(criarPainel(), BorderLayout.CENTER);
        carregarPedidos(null); // Carrega tudo ao abrir
    }

    private JPanel criarPainel() {

        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(0xE9, 0xE9, 0xE9));

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(new Color(0xE9, 0xE9, 0xE9));

        JLabel titulo = new JLabel("Pedidos", SwingConstants.CENTER);
        titulo.setFont(new Font("SansSerif", Font.BOLD, 22));
        titulo.setBorder(BorderFactory.createEmptyBorder(20, 0, 10, 0));
        topPanel.add(titulo, BorderLayout.NORTH);

        // Barra de Busca
        JPanel buscaPanel = new JPanel(new FlowLayout());
        buscaPanel.setBackground(new Color(0xE9, 0xE9, 0xE9));
        
        txtBusca = new JTextField(30);
        
        JButton btnBuscar = new JButton("Buscar");
        btnBuscar.addActionListener(e -> carregarPedidos(txtBusca.getText()));

        buscaPanel.add(new JLabel("Filtrar (Cliente/Projeto/Status): "));
        buscaPanel.add(txtBusca);
        buscaPanel.add(btnBuscar);
        
        topPanel.add(buscaPanel, BorderLayout.CENTER);
        panel.add(topPanel, BorderLayout.NORTH);

        model = new DefaultTableModel(new Object[]{
                "ID", "Associado (ID)", "Projeto (ID)", "Data", "Valor Total", "Status"
        }, 0);

        tabela = new JTable(model);
        tabela.setRowHeight(28);

        JScrollPane scroll = new JScrollPane(tabela);
        panel.add(scroll, BorderLayout.CENTER);

        return panel;
    }

    private void carregarPedidos(String termo) {
        model.setRowCount(0);
        
        Long idProdutor = null;

        // 2. REFATORAÇÃO DA LÓGICA DE FILTRO POR TIPO DE USUÁRIO
        try {
            UsuarioTO usuario = Sessao.getUsuarioLogado();
            
            // Se o usuário for do tipo PRODUTOR, ele só pode ver os próprios pedidos
            if (usuario.usuarioVO.getTipoUsuario() == TIPO_PRODUTOR) {
                idProdutor = Sessao.getAssociadoIdLogado();
            }
            // Se for TIPO_ASSOCIACAO (null), o idProdutor continua null e o DAO traz tudo

        } catch (Exception e) {
            // Se der erro na sessão (ex: sessão expirada), logamos o erro
            // Em produção, talvez fosse melhor redirecionar para o Login
            System.err.println("Erro ao obter sessão: " + e.getMessage());
        }

        // Usa o método de filtro do DAO que já criamos
        List<PedidoVO> lista = pedidoDAO.filtrarPedidos(termo, idProdutor);

        for (PedidoVO p : lista) {
            model.addRow(new Object[]{
                    p.getId(),
                    p.getAssociadoId(),
                    p.getProjetoId() == null ? "Sem projeto" : p.getProjetoId(),
                    p.getDataCriacao(),
                    p.getValorTotal(),
                    p.getStatusDescricao() != null ? p.getStatusDescricao() : p.getStatusPedidoId()
            });
        }
    }
}