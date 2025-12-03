package br.edu.ifsp.hto.cooperativa.producao.modelo.vo;

import java.util.ArrayList;
import java.util.List;

public class DashboardVO {
    private int qtdTalhoes;
    private int qtdAtividades;
    private int qtdCanteiros;
    private int qtdProblemas;
    private List<AtividadeResumoVO> atividadesPendentes = new ArrayList<>();

    // Getters e Setters
    public int getQtdTalhoes() { return qtdTalhoes; }
    public void setQtdTalhoes(int qtdTalhoes) { this.qtdTalhoes = qtdTalhoes; }

    public int getQtdAtividades() { return qtdAtividades; }
    public void setQtdAtividades(int qtdAtividades) { this.qtdAtividades = qtdAtividades; }

    public int getQtdCanteiros() { return qtdCanteiros; }
    public void setQtdCanteiros(int qtdCanteiros) { this.qtdCanteiros = qtdCanteiros; }

    public int getQtdProblemas() { return qtdProblemas; }
    public void setQtdProblemas(int qtdProblemas) { this.qtdProblemas = qtdProblemas; }

    public List<AtividadeResumoVO> getAtividadesPendentes() { return atividadesPendentes; }
    public void setAtividadesPendentes(List<AtividadeResumoVO> atividadesPendentes) { this.atividadesPendentes = atividadesPendentes; }

    // Classe interna para a tabela de atividades
    public static class AtividadeResumoVO {
        private String nome;
        private double custoTotal; // Estimado ou 0 se não houver cálculo
        private String dataFinal;
        private String prioridade; // Baseado na urgência ou padrão

        public AtividadeResumoVO(String nome, double custoTotal, String dataFinal, String prioridade) {
            this.nome = nome;
            this.custoTotal = custoTotal;
            this.dataFinal = dataFinal;
            this.prioridade = prioridade;
        }

        public String getNome() { return nome; }
        public double getCustoTotal() { return custoTotal; }
        public String getDataFinal() { return dataFinal; }
        public String getPrioridade() { return prioridade; }
    }
}