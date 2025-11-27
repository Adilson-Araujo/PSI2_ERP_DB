package br.edu.ifsp.hto.cooperativa.estoque.controle;

import br.edu.ifsp.hto.cooperativa.estoque.modelo.dao.ArmazemDAO;
import br.edu.ifsp.hto.cooperativa.estoque.modelo.dao.CategoriaDAO;
import br.edu.ifsp.hto.cooperativa.estoque.modelo.dao.EspecieDAO;
import br.edu.ifsp.hto.cooperativa.estoque.modelo.dao.EstoqueAtualDAO;
import br.edu.ifsp.hto.cooperativa.estoque.modelo.dao.MovimentacaoDAO;
import br.edu.ifsp.hto.cooperativa.estoque.modelo.dao.OrigemDAO;
import br.edu.ifsp.hto.cooperativa.estoque.modelo.dao.PrecoPPADAO;
import br.edu.ifsp.hto.cooperativa.estoque.modelo.dao.ProdutoDAO;
import br.edu.ifsp.hto.cooperativa.estoque.modelo.dao.TipoDAO;
import br.edu.ifsp.hto.cooperativa.estoque.modelo.to.EstoqueTO;
import br.edu.ifsp.hto.cooperativa.estoque.modelo.to.ProdutoPrecificadoTO;

import br.edu.ifsp.hto.cooperativa.estoque.modelo.vo.Armazem;
import br.edu.ifsp.hto.cooperativa.estoque.modelo.vo.Categoria;
import br.edu.ifsp.hto.cooperativa.estoque.modelo.vo.Especie;
import br.edu.ifsp.hto.cooperativa.estoque.modelo.vo.EstoqueAtual;
import br.edu.ifsp.hto.cooperativa.estoque.modelo.vo.Movimentacao;
import br.edu.ifsp.hto.cooperativa.estoque.modelo.vo.Origem;
import br.edu.ifsp.hto.cooperativa.estoque.modelo.vo.PrecoPPA;
import br.edu.ifsp.hto.cooperativa.estoque.modelo.vo.Produto;
import br.edu.ifsp.hto.cooperativa.estoque.modelo.vo.Tipo;

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
    /** DAO responsável por operações com categoria. */
    private final CategoriaDAO categoriaDAO;
    /** DAO responsável por operações com especie. */
    private final EspecieDAO especieDAO;
    /** DAO responsável por operações com estoque atual. */
    private final EstoqueAtualDAO estoqueAtualDAO;
    /** DAO responsável por operações com movimentacao. */
    private final MovimentacaoDAO movimentacaoDAO;
    /** DAO responsável por operações com origem. */
    private final OrigemDAO origemDAO;
    /** DAO responsável por operações com preco ppa. */
    private final PrecoPPADAO precoPPADAO;
    /** DAO responsável por operações com produto. */
    private final ProdutoDAO produtoDAO;
    /** DAO responsável por operações com tipos. */
    private final TipoDAO tipoDAO;
    
    /**
     * Construtor privado do Singleton. Inicializa as referências aos DAOs.
     */
    private ControleEstoque(){
        this.armazemDAO       =  ArmazemDAO.getInstance();
        this.categoriaDAO     =  CategoriaDAO.getInstance();
        this.especieDAO       =  EspecieDAO.getInstance();
        this.estoqueAtualDAO  =  EstoqueAtualDAO.getInstance();
        this.movimentacaoDAO  =  MovimentacaoDAO.getInstance();
        this.origemDAO        =  OrigemDAO.getInstance();
        this.precoPPADAO      =  PrecoPPADAO.getInstance();
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
    public List<Movimentacao> listarMovimentacoes(int associado_id, int produto_id) {
        return movimentacaoDAO.listarPorProduto(associado_id, produto_id);
    }
    
    /**
     * Lista todos os armazens cadastrados.
     *
     * @return lista de {@code Armazem}
     */
    public List<Armazem> listarArmazens() {
        return armazemDAO.listarTodos();
    }
    
    /**
     * Lista todos os produtos cadastrados.
     *
     * @return lista de {@code Produto}
     */
    public List<Produto> listarProdutos() {
        return produtoDAO.listarTodos();
    }
    /**
     * Busca um produto pelo seu identificador.
     *
     * @param id identificador do produto
     * @return produto correspondente
     * @throws Exception se o ID for inválido ou não existir
     */
    public Produto buscarProdutoPorId(int id) throws Exception {
        if (id <= 0) throw new Exception("ID inválido.");
        return produtoDAO.buscarPorId(id);
    }
    
    /**
     * Lista todas as espécies cadastradas.
     *
     * @return lista de espécies
     */
    public List<Especie> listarEspecies() {
        return especieDAO.listarTodas();
    }
    /**
     * Busca uma espécie pelo seu identificador.
     *
     * @param id identificador da espécie
     * @return espécie correspondente
     * @throws Exception se o ID for inválido ou não existir
     */
    public Especie buscarEspeciePorId(int id) throws Exception {
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
     * @param produto_id int do id do produto a qual se quer o preço.
     * @param data       Timestamp a qual se quer o preço.
     * @return           List contendo ProdutoPreficadoTO s.
     */
    public ProdutoPrecificadoTO buscarPrecos(int produto_id, Timestamp data){
        return produtoDAO.buscarPrecificadoPorId(produto_id, data);
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
    private Movimentacao novaMovimentacao(int tipo_id, int origem_id, int produto_id, int armazem_id, int associado_id, float quantidade){
        Tipo tipo = tipoDAO.buscarPorId(tipo_id);
        Origem origem = origemDAO.buscarPorId(origem_id);
        Produto produto = produtoDAO.buscarPorId(produto_id);
        Armazem armazem = armazemDAO.buscarPorId(armazem_id);
        Timestamp horacriacao = new Timestamp(System.currentTimeMillis());
        Movimentacao nova_movimentacao = new Movimentacao(-1, tipo, origem, produto, armazem, associado_id, quantidade, horacriacao);
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
    public Movimentacao novaProducao(int especie_id, int associado_id, float area_produzida){
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
    public Movimentacao novaVenda(int produto_id, int associado_id, float quantidade){
        return novaMovimentacao(2, 2, produto_id, 2, associado_id, quantidade);
    }

    /**
     * Insere uma movimentação de produção no banco de dados,
     * esta passa a ser persistida.
     *
     * @param movimentacao movimentação não persistida a registrar
     * @throws Exception se a movimentação não pertencer ao módulo de Produção
     */
    public void inserirProducao(Movimentacao movimentacao)throws Exception {
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
    public void inserirVenda(Movimentacao movimentacao)throws Exception {
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
    public void atualizarProducao(Movimentacao movimentacao) throws Exception {
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
    public void atualizarVenda(Movimentacao movimentacao) throws Exception {
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
    public void excluirProducao(Movimentacao movimentacao) throws Exception {
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
    public void excluirVenda(Movimentacao movimentacao) throws Exception {
        if (movimentacao.getId() <= 0) throw new Exception("Objeto não foi registrado ou tem id invalido.");
        if (movimentacao.getOrigem().getId() != 2 || movimentacao.getTipo().getId() != 2){
            throw new Exception("A Origem ou Tipo da Movimentação não condizem com o Módulo de Venda.");
        }
        excluirMovimentacao(movimentacao.getId());
    }
    
    // OPERAÇÕES DE PRODUÇÃO E VENDA
    // //////////////////////////////////////////
    // VERIFICAR

    /*
    
    private void validarArmazem(Armazem armazem) throws Exception {
        if (armazem == null) throw new Exception("Armazém inválido.");
        if (armazem.getNome() == null || armazem.getNome().trim().isEmpty()) {
            throw new Exception("Nome do armazém é obrigatório.");
        }
        if (armazem.getEndereco() == null || armazem.getEndereco().trim().isEmpty()) {
            throw new Exception("Endereço do armazém é obrigatório.");
        }
    }

    public void inserirArmazem(Armazem armazem) throws Exception {
        validarArmazem(armazem);
        if (!armazemDAO.inserir(armazem)) throw new Exception("Erro ao inserir armazém.");
    }

    public void atualizarArmazem(Armazem armazem) throws Exception {
        validarArmazem(armazem);
        if (armazem.getId() <= 0) throw new Exception("ID inválido para atualização.");
        if (!armazemDAO.atualizar(armazem)) throw new Exception("Erro ao atualizar armazém.");
    }

    public void excluirArmazem(int id) throws Exception {
        if (id <= 0) throw new Exception("ID inválido.");
        if (!armazemDAO.excluir(id)) throw new Exception("Erro ao excluir armazém.");
    }

    public List<Armazem> listarArmazens() {
        return armazemDAO.listarTodos();
    }

    public Armazem buscarArmazemPorId(int id) throws Exception {
        if (id <= 0) throw new Exception("ID inválido.");
        return armazemDAO.buscarPorId(id);
    }
    
    private void validarCategoria(Categoria categoria) throws Exception {
        if (categoria == null) throw new Exception("Categoria inválida.");
        if (categoria.getNome() == null || categoria.getNome().trim().isEmpty()) {
            throw new Exception("Nome da categoria é obrigatório.");
        }
    }
    
    public void inserirCategoria(Categoria categoria) throws Exception {
        validarCategoria(categoria);
        if (!categoriaDAO.inserir(categoria)) throw new Exception("Erro ao inserir categoria.");
    }

    public void atualizarCategoria(Categoria categoria) throws Exception {
        validarCategoria(categoria);
        if (categoria.getId() <= 0) throw new Exception("ID inválido para atualização.");
        if (!categoriaDAO.atualizar(categoria)) throw new Exception("Erro ao atualizar categoria.");
    }

    public void excluirCategoria(int id) throws Exception {
        if (id <= 0) throw new Exception("ID inválido.");
        if (!categoriaDAO.excluir(id)) throw new Exception("Erro ao excluir categoria.");
    }

    public List<Categoria> listarCategorias() {
        return categoriaDAO.listarTodas();
    }

    public Categoria buscarCategoriaPorId(int id) throws Exception {
        if (id <= 0) throw new Exception("ID inválido.");
        return categoriaDAO.buscarPorId(id);
    }
    
    private void validarEspecie(Especie especie) throws Exception {
        if (especie == null) throw new Exception("Espécie inválida.");
        if (especie.getNome() == null || especie.getNome().trim().isEmpty()) {
            throw new Exception("Nome da espécie é obrigatório.");
        }
        if (especie.getCategoria() == null || especie.getCategoria().getId() <= 0) {
            throw new Exception("Selecione uma categoria válida.");
        }
    }

    public void inserirEspecie(Especie especie) throws Exception {
        validarEspecie(especie);
        if (!especieDAO.inserir(especie)) throw new Exception("Erro ao inserir espécie.");
    }

    public void atualizarEspecie(Especie especie) throws Exception {
        validarEspecie(especie);
        if (especie.getId() <= 0) throw new Exception("ID inválido para atualização.");
        if (!especieDAO.atualizar(especie)) throw new Exception("Erro ao atualizar espécie.");
    }

    public void excluirEspecie(int id) throws Exception {
        if (id <= 0) throw new Exception("ID inválido.");
        if (!especieDAO.excluir(id)) throw new Exception("Erro ao excluir espécie.");
    }

    public List<Especie> listarEspecies() {
        return especieDAO.listarTodas();
    }

    public Especie buscarEspeciePorId(int id) throws Exception {
        if (id <= 0) throw new Exception("ID inválido.");
        return especieDAO.buscarPorId(id);
    }
    
    private void validarEstoqueAtual(EstoqueAtual estoqueAtual) throws Exception {
       if (estoqueAtual == null || estoqueAtual.getAssociado() == null || estoqueAtual.getProduto() == null || estoqueAtual.getArmazem() == null) {
            throw new Exception("Dados de estoque inválidos.");
        }
        if (estoqueAtual.getAssociado().getId() <= 0 || estoqueAtual.getProduto().getId() <= 0 || estoqueAtual.getArmazem().getId() <= 0) {
             throw new Exception("IDs de Associado, Produto ou Armazém inválidos.");
        }
        if (estoqueAtual.getQuantidade() < 0) throw new Exception("Quantidade não pode ser negativa.");
    }
    
    public void inserirEstoqueAtual(EstoqueAtual estoqueAtual) throws Exception {
        validarEstoqueAtual(estoqueAtual);
        if (!estoqueAtualDAO.inserir(estoqueAtual)) throw new Exception("Erro ao inserir estoque.");
    }
    
    public void atualizarEstoqueAtual(EstoqueAtual estoqueAtual) throws Exception {
        validarEstoqueAtual(estoqueAtual);
        if (!estoqueAtualDAO.atualizar(estoqueAtual)) throw new Exception("Erro ao atualizar estoque.");
    }
    
    public void excluirEstoqueAtual(int id_associado, int id_produto, int id_armazem) throws Exception {
         if (id_associado <= 0 || id_produto <= 0 || id_armazem <= 0) throw new Exception("IDs inválidos.");
         if (!estoqueAtualDAO.excluir(id_associado, id_produto, id_armazem)) {
             throw new Exception("Erro ao excluir estoque.");
         }
    }
    
    public List<EstoqueAtual> listarEstoqueAtual() {
        return estoqueAtualDAO.listarTodos();
    }
    
    public EstoqueAtual buscarEstoqueAtual(int id_associado, int id_produto, int id_armazem) throws Exception {
        if (id_associado <= 0 || id_produto <= 0 || id_armazem <= 0) throw new Exception("IDs inválidos.");
        return estoqueAtualDAO.buscarPorId(id_associado, id_produto, id_armazem);
    }
    
    private void validarMovimentacao(Movimentacao movimentacao) throws Exception {
        if (movimentacao == null) throw new Exception("Movimentação inválida.");
        if (movimentacao.getTipo() == null || movimentacao.getTipo().getId() <= 0) throw new Exception("Tipo inválido.");
        if (movimentacao.getOrigem() == null || movimentacao.getOrigem().getId() <= 0) throw new Exception("Origem inválida.");
        if (movimentacao.getProduto() == null || movimentacao.getProduto().getId() <= 0) throw new Exception("Produto inválido.");
        if (movimentacao.getQuantidade() <= 0) throw new Exception("Quantidade deve ser maior que zero.");
    }
    
    public void inserirMovimentacao(Movimentacao movimentacao) throws Exception {
        validarMovimentacao(movimentacao);
        if (!movimentacaoDAO.inserir(movimentacao)) throw new Exception("Erro ao inserir movimentação.");
    }

    public void atualizarMovimentacao(Movimentacao movimentacao) throws Exception {
        validarMovimentacao(movimentacao);
        if (movimentacao.getId() <= 0) throw new Exception("ID inválido para atualização.");
        if (!movimentacaoDAO.atualizar(movimentacao)) throw new Exception("Erro ao atualizar movimentação.");
    }

    public void excluirMovimentacao(int id) throws Exception {
        if (id <= 0) throw new Exception("ID inválido.");
        if (!movimentacaoDAO.excluir(id)) throw new Exception("Erro ao excluir movimentação.");
    }

    public List<Movimentacao> listarMovimentacoes() {
        return movimentacaoDAO.listarTodas();
    }

    public Movimentacao buscarMovimentacaoPorId(int id) throws Exception {
        if (id <= 0) throw new Exception("ID inválido.");
        return movimentacaoDAO.buscarPorId(id);
    }
    
    private void validarOrigem(Origem origem) throws Exception {
        if (origem == null) throw new Exception("Origem inválida.");
        if (origem.getNome() == null || origem.getNome().trim().isEmpty()) {
            throw new Exception("Nome da origem é obrigatório.");
        }
    }

    public void inserirOrigem(Origem origem) throws Exception {
        validarOrigem(origem);
        if (!origemDAO.inserir(origem)) throw new Exception("Erro ao inserir origem.");
    }

    public void atualizarOrigem(Origem origem) throws Exception {
        validarOrigem(origem);
        if (origem.getId() <= 0) throw new Exception("ID inválido para atualização.");
        if (!origemDAO.atualizar(origem)) throw new Exception("Erro ao atualizar origem.");
    }

    public void excluirOrigem(int id) throws Exception {
        if (id <= 0) throw new Exception("ID inválido.");
        if (!origemDAO.excluir(id)) throw new Exception("Erro ao excluir origem.");
    }

    public List<Origem> listarOrigens() {
        return origemDAO.listarTodas();
    }

    public Origem buscarOrigemPorId(int id) throws Exception {
        if (id <= 0) throw new Exception("ID inválido.");
        return origemDAO.buscarPorId(id);
    }
    
    private void validarPlanoEspecie(PlanoEspecie planoEspecie) throws Exception {
        if (planoEspecie == null) throw new Exception("Plano inválido.");
        if (planoEspecie.getNome_plano() == null || planoEspecie.getNome_plano().trim().isEmpty()) {
            throw new Exception("Nome do plano é obrigatório.");
        }
        if (planoEspecie.getEspecie() == null || planoEspecie.getEspecie().getId() <= 0) {
            throw new Exception("Espécie inválida.");
        }
    }

    public void inserirPlanoEspecie(PlanoEspecie planoEspecie) throws Exception {
        validarPlanoEspecie(planoEspecie);
        if (!planoEspecieDAO.inserir(planoEspecie)) throw new Exception("Erro ao inserir plano.");
    }

    public void atualizarPlanoEspecie(PlanoEspecie planoEspecie) throws Exception {
        validarPlanoEspecie(planoEspecie);
        if (planoEspecie.getId() <= 0) throw new Exception("ID inválido para atualização.");
        if (!planoEspecieDAO.atualizar(planoEspecie)) throw new Exception("Erro ao atualizar plano.");
    }

    public void excluirPlanoEspecie(int id) throws Exception {
        if (id <= 0) throw new Exception("ID inválido.");
        if (!planoEspecieDAO.excluir(id)) throw new Exception("Erro ao excluir plano.");
    }

    public List<PlanoEspecie> listarPlanosEspecie() {
        return planoEspecieDAO.listarTodas();
    }

    public PlanoEspecie buscarPlanoEspeciePorId(int id) throws Exception {
        if (id <= 0) throw new Exception("ID inválido.");
        return planoEspecieDAO.buscarPorId(id);
    }
    
    private void validarPrecoPPA(PrecoPPA precoPPA) throws Exception {
        if (precoPPA == null || precoPPA.getEspecie() == null || precoPPA.getData_inicio() == null) {
            throw new Exception("Dados de Preço PPA inválidos.");
        }
        if (precoPPA.getEspecie().getId() <= 0) throw new Exception("Espécie inválida.");
        if (precoPPA.getValor() < 0) throw new Exception("Valor não pode ser negativo.");
    }

    public void inserirPrecoPPA(PrecoPPA precoPPA) throws Exception {
        validarPrecoPPA(precoPPA);
        if (!precoPPADAO.inserir(precoPPA)) throw new Exception("Erro ao inserir preço PPA.");
    }

    public void atualizarPrecoPPA(PrecoPPA precoPPA) throws Exception {
        validarPrecoPPA(precoPPA);
        if (!precoPPADAO.atualizar(precoPPA)) throw new Exception("Erro ao atualizar preço PPA.");
    }

    public void excluirPrecoPPA(int id_Especie, Timestamp data_Inicio) throws Exception {
        if (id_Especie <= 0 || data_Inicio == null) throw new Exception("Dados inválidos para exclusão.");
        if (!precoPPADAO.excluir(id_Especie, data_Inicio)) throw new Exception("Erro ao excluir preço PPA.");
    }

    public List<PrecoPPA> listarPrecosPPA() {
        return precoPPADAO.listarTodos();
    }

    public PrecoPPA buscarPrecoPPA(Timestamp data, int id_Especie) throws Exception {
        if (id_Especie <= 0 || data == null) throw new Exception("Dados inválidos para busca.");
        return precoPPADAO.buscarPorId(data, id_Especie);
    }
    
    private void validarProduto(Produto produto) throws Exception {
        if (produto == null) throw new Exception("Produto inválido.");
        if (produto.getNome() == null || produto.getNome().trim().isEmpty()) {
            throw new Exception("Nome do produto é obrigatório.");
        }
        if (produto.getEspecie() == null || produto.getEspecie().getId() <= 0) {
            throw new Exception("Selecione uma espécie válida.");
        }
    }

    public void inserirProduto(Produto produto) throws Exception {
        validarProduto(produto);
        if (!produtoDAO.inserir(produto)) throw new Exception("Erro ao inserir produto.");
    }

    public void atualizarProduto(Produto produto) throws Exception {
        validarProduto(produto);
        if (produto.getId() <= 0) throw new Exception("ID inválido para atualização.");
        if (!produtoDAO.atualizar(produto)) throw new Exception("Erro ao atualizar produto.");
    }

    public void excluirProduto(int id) throws Exception {
        if (id <= 0) throw new Exception("ID inválido.");
        if (!produtoDAO.excluir(id)) throw new Exception("Erro ao excluir produto.");
    }

    public List<Produto> listarProdutos() {
        return produtoDAO.listarTodos();
    }

    public Produto buscarProdutoPorId(int id) throws Exception {
        if (id <= 0) throw new Exception("ID inválido.");
        return produtoDAO.buscarPorId(id);
    }
    
    private void validarTipo(Tipo tipo) throws Exception {
        if (tipo == null) throw new Exception("Tipo inválido.");
        if (tipo.getNome() == null || tipo.getNome().trim().isEmpty()) {
            throw new Exception("Nome do tipo é obrigatório.");
        }
    }

    public void inserirTipo(Tipo tipo) throws Exception {
        validarTipo(tipo);
        if (!tipoDAO.inserir(tipo)) throw new Exception("Erro ao inserir tipo.");
    }

    public void atualizarTipo(Tipo tipo) throws Exception {
        validarTipo(tipo);
        if (tipo.getId() <= 0) throw new Exception("ID inválido para atualização.");
        if (!tipoDAO.atualizar(tipo)) throw new Exception("Erro ao atualizar tipo.");
    }

    public void excluirTipo(int id) throws Exception {
        if (id <= 0) throw new Exception("ID inválido.");
        if (!tipoDAO.excluir(id)) throw new Exception("Erro ao excluir tipo.");
    }

    public List<Tipo> listarTipos() {
        return tipoDAO.listarTodos();
    }

    public Tipo buscarTipoPorId(int id) throws Exception {
        if (id <= 0) throw new Exception("ID inválido.");
        return tipoDAO.buscarPorId(id);
    }

    */
}
