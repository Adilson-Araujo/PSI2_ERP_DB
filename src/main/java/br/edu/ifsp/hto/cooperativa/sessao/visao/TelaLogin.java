package br.edu.ifsp.hto.cooperativa.sessao.visao;

import br.edu.ifsp.hto.cooperativa.Cooperativa;
import br.edu.ifsp.hto.cooperativa.notafiscal.visao.ClassesBase.Button;
import br.edu.ifsp.hto.cooperativa.notafiscal.visao.ClassesBase.PaletaCores;
import br.edu.ifsp.hto.cooperativa.sessao.controlador.SessaoControlador;
import br.edu.ifsp.hto.cooperativa.sessao.modelo.negocios.Sessao;

import javax.swing.*;
import java.awt.*;

public class TelaLogin extends JFrame {

    private JTextField txtUsuario;
    private JPasswordField txtSenha;

    private final SessaoControlador sessaoControlador = new SessaoControlador();

    public TelaLogin() {
        setTitle("Login - Sistema Cooperativa");
        setSize(450, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        add(criarHeader(), BorderLayout.NORTH);
        add(criarFormulario(), BorderLayout.CENTER);
    }

    private JPanel criarHeader() {
        JPanel header = new JPanel(new FlowLayout(FlowLayout.CENTER));
        header.setBackground(PaletaCores.verdeClaro);
        header.setPreferredSize(new Dimension(0, 50));

        JLabel titulo = new JLabel("Acesso ao Sistema");
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 20));
        titulo.setForeground(PaletaCores.verdeEscuro);

        header.add(titulo);
        return header;
    }

    private JPanel criarFormulario() {
        JPanel painel = new JPanel();
        painel.setLayout(new BoxLayout(painel, BoxLayout.Y_AXIS));
        painel.setBorder(BorderFactory.createEmptyBorder(25, 40, 20, 40));
        painel.setBackground(PaletaCores.branco);

        JLabel lblUsuario = new JLabel("Usuário:");
        lblUsuario.setAlignmentX(CENTER_ALIGNMENT);
        lblUsuario.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        txtUsuario = new JTextField();
        txtUsuario.setMaximumSize(new Dimension(Integer.MAX_VALUE, 35));

        JLabel lblSenha = new JLabel("Senha:");
        lblSenha.setAlignmentX(CENTER_ALIGNMENT);
        lblSenha.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        txtSenha = new JPasswordField();
        txtSenha.setMaximumSize(new Dimension(Integer.MAX_VALUE, 35));

        Button btnEntrar = new Button("Entrar");
        btnEntrar.setAlignmentX(CENTER_ALIGNMENT);
        btnEntrar.addActionListener(e -> realizarLogin());

        painel.add(lblUsuario);
        painel.add(txtUsuario);
        painel.add(Box.createVerticalStrut(15));
        painel.add(lblSenha);
        painel.add(txtSenha);
        painel.add(Box.createVerticalStrut(20));
        painel.add(btnEntrar);

        return painel;
    }

    private void realizarLogin() {
        String usuario = txtUsuario.getText().trim();
        String senha = new String(txtSenha.getPassword()).trim();
        try{
            sessaoControlador.login(usuario, senha);
            var usuarioLogado = sessaoControlador.obterUsuarioLogado();
            long associadoId = Sessao.getAssociadoIdLogado();
            if (usuarioLogado != null)
            {
                Cooperativa  cooperativa = new br.edu.ifsp.hto.cooperativa.Cooperativa();

                // NÃO MEXER EH A INTEGRAÇÃO DA TELA DE PRODUÇÃO PARA A GENTE TESTAR NOSSAS TELAS NO BD
                // new br.edu.ifsp.hto.cooperativa.producao.visao.TelaInicial(associadoId).setVisible(true);

                cooperativa.setResizable(true);
                cooperativa.setSize( 800, 1000 ); // set frame size
                cooperativa.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                cooperativa.setVisible(true);
                dispose(); // Fecha a tela de login
            }
        }
        catch(Exception ex)
        {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }


    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TelaLogin().setVisible(true));
    }
}
