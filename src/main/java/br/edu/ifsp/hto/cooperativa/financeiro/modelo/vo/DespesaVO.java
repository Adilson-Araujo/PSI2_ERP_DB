package br.edu.ifsp.hto.cooperativa.financeiro.modelo.vo;

public class DespesaVO {
    private long id = -1; // -1 por padrão para facilitar casos de atualização de dados no banco
    private long associado_id = -1; // seguindo o padrão do id do objeto acima
    private String categoria_gasto;
    private String destinatario;
    private double valor_gasto;
    private String data_transacao;
    private String descricao_despesa;
    
    // Versão onde passa id e usuario_id, possívelmente para objetos que serão inseridos no banco
    public DespesaVO (
        long id,
        long associado_id,
        String categoria_gasto,
        String destinatario,
        double valor_gasto,
        String data_transacao,
        String descricao_despesa
    ) {
        this.id = id;
        this.associado_id = associado_id;
        this.categoria_gasto = categoria_gasto;
        this.destinatario = destinatario;
        this.valor_gasto = valor_gasto;
        this.data_transacao = data_transacao;
        this.descricao_despesa = descricao_despesa;
    }
    
    // Getters (Busca de atributos)
    public long buscaId () {return this.id;}
    public long buscaAssociado_id () {return this.associado_id;}
    public String buscaCategoria_gasto () {return this.categoria_gasto;}
    public String buscaDestinatario () {return this.destinatario;}
    public double buscaValor_gasto () {return this.valor_gasto;}
    public String buscaData_transacao () {return this.data_transacao;}
    public String buscaDescricao_despesa () {return this.descricao_despesa;}
    
    // Setters (Redefinição de atributos)
    public void atualizarId (long id) {this.id = id;}
    public void atualizarAssociado_id (long associado_id) {this.associado_id = associado_id;}
    public void atualizarCategoria_gasto (String categoria_gasto) {this.categoria_gasto = categoria_gasto;}
    public void atualizarDestinatario (String destinatario) {this.destinatario = destinatario;}
    public void atualizarValor_gasto (double valor_gasto) {this.valor_gasto = valor_gasto;}
    public void atualizarData_transacao (String data_transacao) {this.data_transacao = data_transacao;}
    public void atualizarDescricao_despesa (String descricao_despesa) {this.descricao_despesa = descricao_despesa;}
}
