package br.edu.ifsp.hto.cooperativa.notafiscal.visao;

import br.edu.ifsp.hto.cooperativa.notafiscal.controlador.AssociadoControlador;
import br.edu.ifsp.hto.cooperativa.notafiscal.modelo.dto.AssociadoTO;
import br.edu.ifsp.hto.cooperativa.notafiscal.modelo.vo.AssociadoVO;
import br.edu.ifsp.hto.cooperativa.notafiscal.modelo.vo.EnderecoVO;
import br.edu.ifsp.hto.cooperativa.notafiscal.visao.ClassesBase.Button;
import br.edu.ifsp.hto.cooperativa.notafiscal.visao.ClassesBase.ViewBase;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDateTime;

public class TelaCadastroAssociado extends ViewBase {
    // --- Informações Pessoais
    private JTextField txtNomeCompleto, txtApelido, txtTelefone, txtEmail;

    // --- Endereço
    private JTextField txtAssentamento, txtRua, txtMunicipio, txtUf, txtCep, txtNumero;

    // --- Associado Produtor
    private JTextField txtCnpj, txtDap, txtCaf, txtPronaf, txtPaa, txtPnae, txtInscricaoEstadual, txtInscricaoMunicipal;

    public TelaCadastroAssociado(JDesktopPane desktop) {
        super("Cadastro de Associado", desktop); // Passa o título para o cabeçalho
    }
    @Override
    protected JPanel getPainelConteudo() {
        // O código que estava no construtor vem pra cá
        JTabbedPane abas = new JTabbedPane();
        abas.addTab("Informações Pessoais", criarPainelPessoal());
        abas.addTab("Endereço", criarPainelEndereco());
        abas.addTab("Associado Produtor", criarPainelProdutor());

        // Retorna o painel principal em vez de dar 'add()'
        JPanel painelRetorno = new JPanel(new BorderLayout());
        painelRetorno.add(abas, BorderLayout.CENTER);
        return painelRetorno;
    }

    // Painel de Informações Pessoais
    private JPanel criarPainelPessoal() {
        JPanel p = new JPanel(new GridBagLayout());
        p.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        GridBagConstraints gbc = baseGbc();

        txtNomeCompleto = new JTextField(25);
        txtApelido = new JTextField(20);
        txtTelefone = new JTextField(15);
        txtEmail = new JTextField(25);

        addField(p, gbc, 0, "Nome Completo:", txtNomeCompleto);
        addField(p, gbc, 1, "Apelido/Nome Social:", txtApelido);
        addField(p, gbc, 2, "Telefone:", txtTelefone);
        addField(p, gbc, 3, "E-mail:", txtEmail);

        addTopGlue(p, gbc, 11);
        return p;
    }

    // Painel de Endereço
    private JPanel criarPainelEndereco() {
        JPanel p = new JPanel(new GridBagLayout());
        p.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        GridBagConstraints gbc = baseGbc();

        txtAssentamento = new JTextField(20);
        txtRua = new JTextField(10);
        txtMunicipio = new JTextField(20);
        txtUf = new JTextField(2);
        txtCep = new JTextField(9);
        txtNumero = new JTextField(6);

        addField(p, gbc, 0, "Assentamento:", txtAssentamento);
        addField(p, gbc, 2, "Rua:", txtRua);
        addField(p, gbc, 3, "Município:", txtMunicipio);
        addField(p, gbc, 4, "UF:", txtUf);
        addField(p, gbc, 5, "CEP:", txtCep);
        addField(p, gbc, 6, "Número:", txtNumero);

        addTopGlue(p, gbc, 11);
        return p;
    }

    // Painel de Associado Produtor
    private JPanel criarPainelProdutor() {
        JPanel p = new JPanel(new GridBagLayout());
        p.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        GridBagConstraints gbc = baseGbc();

        txtCnpj = new JTextField(18);
        txtDap = new JTextField(15);
        txtCaf = new JTextField(15);
        txtPronaf = new JTextField(15);
        txtPaa = new JTextField(15);
        txtPnae = new JTextField(15);
        txtInscricaoEstadual = new JTextField(20);
        txtInscricaoMunicipal = new JTextField(20);

        addField(p, gbc, 0, "CNPJ:", txtCnpj);
        addField(p, gbc, 1, "DAP:", txtDap);
        addField(p, gbc, 2, "CAF:", txtCaf);
        addField(p, gbc, 3, "PRONAF:", txtPronaf);
        addField(p, gbc, 4, "PAA:", txtPaa);
        addField(p, gbc, 5, "PNAE:", txtPnae);
        addField(p, gbc, 6, "Inscrição Municipal", txtInscricaoMunicipal);
        addField(p, gbc, 7, "Inscrição Estadual", txtInscricaoEstadual);

        br.edu.ifsp.hto.cooperativa.notafiscal.visao.ClassesBase.Button btnSalvar = new Button("Salvar Associado");
        gbc.gridx = 1;
        gbc.gridy = 9;
        gbc.anchor = GridBagConstraints.WEST;
        btnSalvar.addActionListener(e -> btnSalvar_Click());
        p.add(btnSalvar, gbc);

        addTopGlue(p, gbc, 11);
        return p;
    }

    private void btnSalvar_Click()
    {
        try {
            var associadoTO = new AssociadoTO();
            var associado = new AssociadoVO();
            // Pessoal
            associado.setTelefone(txtTelefone.getText());
            associado.setEmail(txtEmail.getText());
            // Produtor
            associado.setRazaoSocial(txtNomeCompleto.getText());
            associado.setNomeFantasia(txtApelido.getText());
            associado.setCnpj(txtCnpj.getText());
            associado.setInscricaoEstadual(txtInscricaoEstadual.getText());
            associado.setInscricaoMunicipal(txtInscricaoMunicipal.getText());
            associado.setPnae(txtPnae.getText());
            associado.setPaa(txtPaa.getText());
            associado.setPronaf(txtPronaf.getText());
            associado.setCaf(txtCaf.getText());
            associado.setDaf(txtDap.getText());
            associado.setDataCadastrado(LocalDateTime.now());
            // Endereço
            var endereco = new EnderecoVO();
            endereco.setBairro(txtAssentamento.getText());
            endereco.setRua(txtRua.getText());
            endereco.setCidade(txtMunicipio.getText());
            endereco.setEstado(txtUf.getText());
            endereco.setNumero(Integer.parseInt(txtNumero.getText()));
            endereco.setCep(txtCep.getText());

            associado.setAtivo(true);
            associadoTO.associado = associado;
            associadoTO.endereco = endereco;
            AssociadoControlador controlador = new AssociadoControlador();
            controlador.cadastrar(associadoTO);
            JOptionPane.showMessageDialog(this, "ASSOCIADO CADASTRADO COM SUCESSO", "Sucesso", JOptionPane.PLAIN_MESSAGE);
            limparCampos();
        } catch(Exception ex)
        {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
    private void limparCampos() {
        limpar(
                // Dados pessoais
                txtNomeCompleto, txtApelido, txtTelefone, txtEmail,

                // Endereço
                txtAssentamento, txtRua, txtMunicipio, txtUf, txtCep, txtNumero,

                // Associado Produtor
                txtCnpj, txtDap, txtCaf, txtPronaf, txtPaa, txtPnae,
                txtInscricaoEstadual, txtInscricaoMunicipal
        );
    }

    private void limpar(JTextField... fields) {
        for (JTextField f : fields) {
            if (f != null) {
                f.setText("");
            }
        }
    }
}
