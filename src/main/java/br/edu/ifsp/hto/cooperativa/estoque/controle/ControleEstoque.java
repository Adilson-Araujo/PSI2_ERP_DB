package br.edu.ifsp.hto.cooperativa.estoque.controle;

import br.edu.ifsp.hto.cooperativa.estoque.modelo.dao.ArmazemDAO;
import br.edu.ifsp.hto.cooperativa.estoque.modelo.dao.EspecieDAO;
import br.edu.ifsp.hto.cooperativa.estoque.modelo.dao.EstoqueAtualDAO;
import br.edu.ifsp.hto.cooperativa.estoque.modelo.dao.MovimentacaoDAO;
import br.edu.ifsp.hto.cooperativa.estoque.modelo.dao.OrigemDAO;
import br.edu.ifsp.hto.cooperativa.estoque.modelo.dao.ProdutoDAO;
import br.edu.ifsp.hto.cooperativa.estoque.modelo.dao.TipoDAO;
import br.edu.ifsp.hto.cooperativa.estoque.modelo.to.EstoqueTO;
import br.edu.ifsp.hto.cooperativa.estoque.modelo.to.ProdutoPrecificadoTO;

import br.edu.ifsp.hto.cooperativa.estoque.modelo.vo.ArmazemVO;
import br.edu.ifsp.hto.cooperativa.estoque.modelo.vo.EspecieVO;
import br.edu.ifsp.hto.cooperativa.estoque.modelo.vo.MovimentacaoVO;
import br.edu.ifsp.hto.cooperativa.estoque.modelo.vo.OrigemVO;
import br.edu.ifsp.hto.cooperativa.estoque.modelo.vo.ProdutoVO;
import br.edu.ifsp.hto.cooperativa.estoque.modelo.vo.TipoVO;

import java.sql.Timestamp;
import java.util.List;

/**
 * Controlador central do módulo de estoque. Responsável por intermediar operações
 * entre os módulos de Produção, Vendas, Planejamento e outros, oferecendo
 * consultas de produtos, espécies e criação de movimentações de entrada e saída.
 *
 * <p>Implementa o padrão Singleton para garantir apenas uma instância durante
 * a execução do sistema.</p>
 */
public class ControleEstoque {
    /** Instância única do controlador de estoque. */
    private static ControleEstoque instancia = null;
    /** DAO responsável por operações com armazéns. */
    private final ArmazemDAO armazemDAO;
    /** DAO responsável por operações com especie. */
    private final EspecieDAO especieDAO;
    /** DAO responsável por operações com estoque atual. */
    private final EstoqueAtualDAO estoqueAtualDAO;
    /** DAO responsável por operações com movimentacao. */
    private final MovimentacaoDAO movimentacaoDAO;
    /** DAO responsável por operações com origem. */
    private final OrigemDAO origemDAO;
    /** DAO responsável por operações com produto. */
    private final ProdutoDAO produtoDAO;
    /** DAO responsável por operações com tipos. */
    private final TipoDAO tipoDAO;
    
    /**
     * Construtor privado do Singleton. Inicializa as referências aos DAOs.
     */
    private ControleEstoque(){
        this.armazemDAO       =  ArmazemDAO.getInstance();
        this.especieDAO       =  EspecieDAO.getInstance();
        this.estoqueAtualDAO  =  EstoqueAtualDAO.getInstance();
        this.movimentacaoDAO  =  MovimentacaoDAO.getInstance();
        this.origemDAO        =  OrigemDAO.getInstance();
        this.produtoDAO       =  ProdutoDAO.getInstance();
        this.tipoDAO          =  TipoDAO.getInstance();
    }
    
    /**
     * Retorna a instância única do controlador.
     *
     * @return instância de {@code controleEstoque}
     */
    public static ControleEstoque getInstance(){
        if(instancia == null) instancia = new ControleEstoque();
        return instancia;
    }
    
    // BÁSICO DA CLASS
    // //////////////////////////////////////////
    // READ GERAL

    /**
     * Lista todos as movimentações cadastrados.
     *
     * @param  associado_id referente ao associado escolhido.
     * @param  produto_id referente ao produto escolhido.
     * @return lista de {@code Movimentacao}
     */
    public List<MovimentacaoVO> listarMovimentacoes(int associado_id, int produto_id) {
        return movimentacaoDAO.listarPorProduto(associado_id, produto_id);
    }
    
    /**
     * Lista todos os armazens cadastrados.
     *
     * @return lista de {@code Armazem}
     */
    public List<ArmazemVO> listarArmazens() {
        return armazemDAO.listarTodos();
    }
    
    /**
     * Lista todos os produtos cadastrados.
     *
     * @return lista de {@code Produto}
     */
    public List<ProdutoVO> listarProdutos() {
        return produtoDAO.listarTodos();
    }
    /**
     * Busca um produto pelo seu identificador.
     *
     * @param id identificador do produto
     * @return produto correspondente
     * @throws Exception se o ID for inválido ou não existir
     */
    public ProdutoVO buscarProdutoPorId(int id) throws Exception {
        if (id <= 0) throw new Exception("ID inválido.");
        return produtoDAO.buscarPorId(id);
    }
    
    /**
     * Lista todas as espécies cadastradas.
     *
     * @return lista de espécies
     */
    public List<EspecieVO> listarEspecies() {
        return especieDAO.listarTodas();
    }
    /**
     * Busca uma espécie pelo seu identificador.
     *
     * @param id identificador da espécie
     * @return espécie correspondente
     * @throws Exception se o ID for inválido ou não existir
     */
    public EspecieVO buscarEspeciePorId(int id) throws Exception {
        if (id <= 0) throw new Exception("ID inválido.");
        return especieDAO.buscarPorId(id);
    }
    
    // READ GERAL
    // //////////////////////////////////////////
    // AUXILIAR DE OUTROS MÓDULOS
    
    /**
     * Retorna um lista de TOs que contem Produto e Especie
     * com Preço do PPA em uma Data definida.
     * 
     * @param data    Timestamp a qual se quer o preço.
     * @return        List contendo ProdutoPreficadoTO s.
     */
    public List<ProdutoPrecificadoTO> listarPrecos(Timestamp data){
        return produtoDAO.listarTodosPrecificados(data);
    }
    
    /**
     * Retorna um TO que contem Produto e Especie
     * com Preço do PPA em uma Data definida.
     * 
     * @param especie_id id da especie a qual se quer o preço.
     * @param data       Timestamp a qual se quer o preço.
     * @return           List contendo ProdutoPreficadoTO s.
     */
    public ProdutoPrecificadoTO buscarPrecos(int especie_id, Timestamp data){
        return produtoDAO.buscarPrecificadoPorEspecieId(especie_id, data);
    }

    /**
     * Retorna o estoque Produto e Quantidade de um dado Associado.
     *
     * @param associado_id    identificador do associado
     * @return List de AssociadoProdutoTO com relação Produto e Quantidade
     */    
    public List<EstoqueTO> listarEstoque(int associado_id){
        return estoqueAtualDAO.listarEstoque(associado_id);
    }
    
    /**
     * Retorna o estoque Produto e Quantidade de um dado Associado e Produto.
     *
     * @param associado_id    identificador do associado
     * @param produto_id      identificador do produto
     * @return List de AssociadoProdutoTO com relação Produto e Quantidade
     */    
    public EstoqueTO buscarEstoque(int associado_id, int produto_id){
        return estoqueAtualDAO.buscarEstoque(associado_id, produto_id);
    }
    
    /**
     * Calcula a quantidade produzida com base na espécie e área cultivada.
     *
     * @param especie_id    identificador da espécie
     * @param area_produzida área produzida em m²
     * @return quantidade estimada em kg
     */
    public float calcularQuantidade(int especie_id, float area_produzida){
        return area_produzida * especieDAO.buscarPorId(especie_id).getRendimento_kg_m2();
    }

    // AUXILIAR DE OUTROS MÓDULOS
    // //////////////////////////////////////////
    // OPERAÇÕES DE PRODUÇÃO E VENDA
    
    /**
     * Cria uma nova movimentação genérica com base nos parâmetros informados.
     *
     * @param tipo_id      identificador do tipo de movimentação
     * @param origem_id    identificador da origem da movimentação
     * @param produto_id   identificador do produto
     * @param armazem_id   identificador do armazém
     * @param associado_id identificador do associado
     * @param quantidade   quantidade movimentada
     * @return nova movimentação não persistida
     * @throws RuntimeException se algum dos IDs for inválido ou não encontrado
     */
    private MovimentacaoVO novaMovimentacao(int tipo_id, int origem_id, int produto_id, int armazem_id, int associado_id, float quantidade){
        TipoVO tipo = tipoDAO.buscarPorId(tipo_id);
        OrigemVO origem = origemDAO.buscarPorId(origem_id);
        ProdutoVO produto = produtoDAO.buscarPorId(produto_id);
        ArmazemVO armazem = armazemDAO.buscarPorId(armazem_id);
        Timestamp horacriacao = new Timestamp(System.currentTimeMillis());
        MovimentacaoVO nova_movimentacao = new MovimentacaoVO(-1, tipo, origem, produto, armazem, associado_id, quantidade, horacriacao);
        return nova_movimentacao;
    }
    
    /**
     * Gera uma movimentação de produção, associada a um produto derivado
     * da espécie informada.
     *
     * @param especie_id    identificador da espécie
     * @param associado_id  identificador do associado
     * @param area_produzida área produzida
     * @return movimentação de produção não persistida
     */
    public MovimentacaoVO novaProducao(int especie_id, int associado_id, float area_produzida){
        int produto_id = produtoDAO.buscarPorEspecieId(especie_id).getId();
        float quantidade = calcularQuantidade(especie_id, area_produzida);
        return novaMovimentacao(1, 1, produto_id, 1, associado_id, quantidade);
    }
    
    /**
     * Gera uma movimentação de venda.
     *
     * @param produto_id    identificador do produto
     * @param associado_id  identificador do associado
     * @param quantidade    quantidade vendida
     * @return movimentação de venda não persistida
     */
    public MovimentacaoVO novaVenda(int produto_id, int associado_id, float quantidade){
        return novaMovimentacao(2, 2, produto_id, 2, associado_id, quantidade);
    }

    /**
     * Insere uma movimentação de produção no banco de dados,
     * esta passa a ser persistida.
     *
     * @param movimentacao movimentação não persistida a registrar
     * @throws Exception se a movimentação não pertencer ao módulo de Produção
     */
    public void inserirProducao(MovimentacaoVO movimentacao)throws Exception {
        if (movimentacao.getOrigem().getId() != 1 || movimentacao.getTipo().getId() != 1){
            throw new Exception("A Origem ou Tipo da Movimentação não condizem com o Módulo de Produção.");
        }
        movimentacaoDAO.inserir(movimentacao);
    }
    
    /**
     * Insere uma movimentação de venda no banco de dados,
     * esta passa a ser persistida.
     *
     * @param movimentacao movimentação não persistida a registrar
     * @throws Exception se a movimentação não pertencer ao módulo de Venda
     */
    public void inserirVenda(MovimentacaoVO movimentacao)throws Exception {
        if (movimentacao.getOrigem().getId() != 2 || movimentacao.getTipo().getId() != 2){
            throw new Exception("A Origem ou Tipo da Movimentação não condizem com o Módulo de Venda.");
        }
        movimentacaoDAO.inserir(movimentacao);
    }
    
    /**
     * Atualiza uma movimentação de produção existente.
     *
     * @param movimentacao movimentação a atualizar
     * @throws Exception se o ID for inválido ou se a movimentação não pertencer a produção
     */
    public void atualizarProducao(MovimentacaoVO movimentacao) throws Exception {
        if (movimentacao.getId() <= 0) throw new Exception("ID inválido para atualização.");
        if (movimentacao.getOrigem().getId() != 1 || movimentacao.getTipo().getId() != 1){
            throw new Exception("A Origem ou Tipo da Movimentação não condizem com o Módulo de Produção.");
        }
        if (!movimentacaoDAO.atualizar(movimentacao)) throw new Exception("Erro ao atualizar movimentação.");
    }
    
    /**
     * Atualiza uma movimentação de venda existente.
     *
     * @param movimentacao movimentação a atualizar
     * @throws Exception se o ID for inválido ou se a movimentação não pertencer a vendas
     */
    public void atualizarVenda(MovimentacaoVO movimentacao) throws Exception {
        if (movimentacao.getId() <= 0) throw new Exception("ID inválido para atualização.");
        if (movimentacao.getOrigem().getId() != 2 || movimentacao.getTipo().getId() != 2){
            throw new Exception("A Origem ou Tipo da Movimentação não condizem com o Módulo de Venda.");
        }
        if (!movimentacaoDAO.atualizar(movimentacao)) throw new Exception("Erro ao atualizar movimentação.");
    }
    
    /**
     * Remove uma movimentação pelo identificador.
     *
     * @param id identificador da movimentação
     * @throws Exception se o DAO não conseguir excluir a movimentação
     */
    private void excluirMovimentacao(int id) throws Exception {
        if (!movimentacaoDAO.excluir(id)) throw new Exception("DAO não conseguiu excluir a movimentação.");
    }
    
    /**
     * Exclui uma movimentação de produção.
     *
     * @param movimentacao movimentação a remover
     * @throws Exception se o ID for inválido ou a movimentação não for de produção
     */
    public void excluirProducao(MovimentacaoVO movimentacao) throws Exception {
        if (movimentacao.getId() <= 0) throw new Exception("Objeto não foi registrado ou tem id invalido.");
        if (movimentacao.getOrigem().getId() != 1 || movimentacao.getTipo().getId() != 1){
            throw new Exception("A Origem ou Tipo da Movimentação não condizem com o Módulo de Produção.");
        }
        excluirMovimentacao(movimentacao.getId());
    }
    
    /**
     * Exclui uma movimentação de venda.
     *
     * @param movimentacao movimentação a remover
     * @throws Exception se o ID for inválido ou a movimentação não for de venda
     */
    public void excluirVenda(MovimentacaoVO movimentacao) throws Exception {
        if (movimentacao.getId() <= 0) throw new Exception("Objeto não foi registrado ou tem id invalido.");
        if (movimentacao.getOrigem().getId() != 2 || movimentacao.getTipo().getId() != 2){
            throw new Exception("A Origem ou Tipo da Movimentação não condizem com o Módulo de Venda.");
        }
        excluirMovimentacao(movimentacao.getId());
    }
    
    // OPERAÇÕES DE PRODUÇÃO E VENDA
    // //////////////////////////////////////////
}
