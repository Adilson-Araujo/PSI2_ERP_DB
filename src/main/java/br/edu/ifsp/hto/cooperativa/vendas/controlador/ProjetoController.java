package br.edu.ifsp.hto.cooperativa.vendas.controlador;

import br.edu.ifsp.hto.cooperativa.vendas.modelo.dao.ProjetoDAO;
import br.edu.ifsp.hto.cooperativa.vendas.modelo.vo.ProjetoVO;
import java.math.BigDecimal;
import java.time.ZoneId;
import java.util.Date;
import javax.swing.JOptionPane;

public class ProjetoController {

    private ProjetoDAO dao;

    public ProjetoController() {
        this.dao = new ProjetoDAO();
    }

    // ASSINATURA DO MÉTODO ALTERADA: Removemos empresa e cnpj
    public void salvarProjeto(String nome, Date inicio, Date fim, String orcamentoStr) {
        try {
            ProjetoVO vo = new ProjetoVO();
            vo.setNomeProjeto(nome);

            // Conversão Datas
            if (inicio != null) {
                vo.setDataCriacao(inicio.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
            }
            if (fim != null) {
                vo.setDataFinal(fim.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
            }

            // Conversão Dinheiro
            String valorLimpo = orcamentoStr.replace("R$", "").replace(".", "").replace(",", ".").trim();
            if(valorLimpo.isEmpty()) valorLimpo = "0";
            vo.setOrcamento(new BigDecimal(valorLimpo));

            // Chama o DAO
            String resultado = dao.adicionar(vo);
            JOptionPane.showMessageDialog(null, resultado);

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Valor do orçamento inválido! Digite apenas números.");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro inesperado: " + e.getMessage());
            e.printStackTrace();
        }
    }
}