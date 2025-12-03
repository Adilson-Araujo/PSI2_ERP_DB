package br.edu.ifsp.hto.cooperativa.financeiro.controle;

import java.util.ArrayList;

import br.edu.ifsp.hto.cooperativa.financeiro.modelo.dao.DespesaDAO;
import br.edu.ifsp.hto.cooperativa.financeiro.modelo.vo.DespesaVO;

/**
 * Controlador do módulo de finanças. Responsável somente pelo intermédio das ações relacionadas
 * com o registro e 'exclusão' de ídices de despesa.
 * 
 * <p>Implementa o padrão singleton para garantir a existência de apenas uma instância em tempo
 * de execução.<p>
 */
public class ControleDespesa {
    /** Instância única do controlador. */
    private static ControleDespesa instancia = null;
    /** Instância do DAO responsável pela gerência de despesas */
    private final DespesaDAO despesaDAO;

    /** Construtor privado do Controle. */
    private ControleDespesa () {
        despesaDAO = DespesaDAO.getInstance();
        
    }

    /** 
     * Método público que retorna a instância única do controlador.
     * 
     * @return instância única de {@code ControleDespesa}
    */
    public static ControleDespesa getInstance() {
        if (instancia == null) {
            instancia = new ControleDespesa();
        }
        return instancia;
    }

    /**
     * Método que busca uma despesa pelo seu id de registro no banco de dados.
     * 
     * @param id representa o id da despesa dentro do banco
     * @return um objeto {@code DespesaVO} correspondendo ao registro encontrado no banco;
     * {@code null}caso não seja encontrado nenhum registro de id correspondente no banco.
     */
    public DespesaVO buscarDespesaPorId (long id) {
        try {
            return despesaDAO.buscarId(id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Retorna todas as depesas que estão ligadas ao id de um associado.
     * 
     * @param associado_id representa o id de registro de um associado no banco.
     * @return um {@code ArrayList} do tipo de DespesaVO com todas as despesas associadas
     * ao id de um determinado associado; {@code null} caso nada seja encontrado para o 
     * id indicado.
     */
    public ArrayList<DespesaVO> buscarDespesaPorIdDeAssociado (long associado_id) {
        try {
            return despesaDAO.buscarAssociadoId(associado_id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Retorna todas as depesas.
     * 
     * @return um {@code ArrayList} do tipo de DespesaVO com todas as despesas; 
     * {@code null} caso nenhuma despesa seja encontrada.
     */
    public ArrayList<DespesaVO> buscarTodasDespesas () {
        try {
            return despesaDAO.buscarTodasDespesas();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Inseri um determinado objeto de tipo {@code DespesaVO} no banco de dados.
     * 
     * @param despesaVO uma instância de DespesaVO que irá ser persistida no banco.
     * @return uma string correspondendo ao o que ocorreu no processo de inserção.
     */
    public String inserirDespesa (DespesaVO despesaVO) {
        try {
            return despesaDAO.adicionar(despesaVO);
        } catch (Exception e) {
            e.printStackTrace();
            return "Falha de execução";
        }
    }

    /**
     * Atualiza os dados de um registro de despesa no banco, com base no id do objeto passado
     * como parâmetro. Importante notar que esse método atualiza todos os campos EXCETO
     * {@code id} e {@code associado_id}.
     * 
     * @param despesaVO um instância de DespesaVO que irá ser usada como fonte dos dados
     * que serão atualizados no banco.
     * @return uma string correspondendo ao o que ocorreu no processo de atualização.
     */
    public String atualizarDespesa (DespesaVO despesaVO) {
        try {
            return despesaDAO.atualizar(despesaVO);
        } catch (Exception e) {
            e.printStackTrace();
            return "Falha de execução";
        }
    }

    /**
     * Remove o determinado registro de uma despesa com base no id passado como parâmetro.
     * Importante notar que esse método pode se tornar inútil de acordo com definições de
     * negócio onde a remoção de um dado no banco seja representada pela atualização de um
     * atributo específico ou anulação de diversos atributos ao invés da sua remoção completa.
     * 
     * @param id representa o id do registro de despesa que deve ser removido.
     * @return uma string correspondendo ao o que ocorreu no processo de atualização.
     */
    public String removerDespesa (long id) {
        try {
            return despesaDAO.remover(id);
        } catch (Exception e) {
            e.printStackTrace();
            return "Falha de execução";
        }
    }
}