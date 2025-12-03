package br.edu.ifsp.hto.cooperativa.financeiro.modelo.dao;
// Java
import java.util.ArrayList;
import java.util.Date;
// Banco
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.sql.ResultSet;
// Package
import br.edu.ifsp.hto.cooperativa.ConnectionFactory;
import br.edu.ifsp.hto.cooperativa.financeiro.modelo.vo.DespesaVO;

public class DespesaDAO {
    private static DespesaDAO instancia = null;
    
    private DespesaDAO ( ) {}
    public static DespesaDAO getInstance() {
        if (instancia == null) {
            instancia = new DespesaDAO();
        }
        return instancia;
    }
    
    public DespesaVO buscarId (long id) {
        DespesaVO despesa = null;
        try {
            Connection connection = ConnectionFactory.getConnection();
            PreparedStatement stmt = connection.prepareStatement ("SELECT * FROM despesa WHERE id = ?");
            
            stmt.setLong(1, id);
            
            ResultSet rs = stmt.executeQuery();
            rs.next();

            despesa = new DespesaVO(                 
                    rs.getLong("id"),
                    rs.getLong("associado_Id"),
                    rs.getString("categoria_gasto"),
                    rs.getString("destinatario"),
                    rs.getDouble("valor_gasto"),
                    rs.getString("data_transacao"),
                    rs.getString("descricao_despesa")
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return despesa;
    }
    
    
    public ArrayList<DespesaVO> buscarAssociadoId (long associado_id) {
        ArrayList<DespesaVO> despesas = new ArrayList<DespesaVO>();
        try {
            Connection connection = ConnectionFactory.getConnection();
            PreparedStatement stmt = connection.prepareStatement ("SELECT * FROM despesa WHERE associado_id = ?");
            
            stmt.setLong(1, associado_id);
            
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                DespesaVO despesa = new DespesaVO(
                    rs.getLong("id"),
                    rs.getLong("associado_Id"),
                    rs.getString("categoria_gasto"),
                    rs.getString("destinatario"),
                    rs.getDouble("valor_gasto"),
                    rs.getString("data_transacao"),
                    rs.getString("descricao_despesa")
                );
                despesas.add(despesa);
            }                  
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return despesas;
    }
    
    public String adicionar (DespesaVO despesa) {
        try {
            Connection connection = ConnectionFactory.getConnection();
            PreparedStatement stmt = connection.prepareStatement ("INSERT INTO despesa VALUES (?,?,?,?,?,?,?)");
            
            SimpleDateFormat formatoData = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date dataProcessada = formatoData.parse(despesa.buscaData_transacao());
            Timestamp dataFormatada = new Timestamp(dataProcessada.getTime());

            stmt.setLong(1, despesa.buscaId());
            stmt.setLong(2, despesa.buscaAssociado_id());
            stmt.setString(3, despesa.buscaCategoria_gasto());
            stmt.setString(4, despesa.buscaDestinatario());
            stmt.setDouble(5, despesa.buscaValor_gasto());
            stmt.setTimestamp(6, dataFormatada);
            stmt.setString(7, despesa.buscaDescricao_despesa());
            
            stmt.execute();                    
        } catch (Exception e) {
            e.printStackTrace();
            return "Excessão ao tentar inserir nova despesa.";
        }
        return "Despesa inserida com sucesso.";
    }
    
    public String remover (long id) {
        try {
            Connection connection = ConnectionFactory.getConnection();
            PreparedStatement stmt = connection.prepareStatement ("DELETE FROM despesa WHERE id = ?");
            
            stmt.setLong(1, id);
            
            stmt.executeUpdate();                    
        } catch (SQLException e) {          
            e.printStackTrace();
            return "Excessão ao tentar remover despesa de id " + id + ".";
        }
        return "Despesa de id " + id + " removida com sucesso.";
    }
    
    // Atualiza todas as informações de uma determinada despesa EXCETO id e associado_id
    public String atualizar (DespesaVO despesa) {
        try {
            Connection connection = ConnectionFactory.getConnection();
            PreparedStatement stmt = connection.prepareStatement (
                    "UPDATE despesa SET "
                            + "categoria_gasto = ?,"
                            + "destinatario = ?,"
                            + "valor_gasto = ?,"
                            + "data_transacao = ?,"
                            + "descricao_despesa = ?"
                    + "WHERE id = ?"
                            
            );
            
            stmt.setString(1, despesa.buscaCategoria_gasto());
            stmt.setString(2, despesa.buscaDestinatario());
            stmt.setDouble(3, despesa.buscaValor_gasto());
            stmt.setString(4, despesa.buscaData_transacao());
            stmt.setString(5, despesa.buscaDescricao_despesa());
            stmt.setLong(6, despesa.buscaId());
            
            stmt.executeUpdate();                    
        } catch (SQLException e) {          
            e.printStackTrace();
            return "Exceção ao tentar atualizar despesa de id " + despesa.buscaId() + ".";
        }
        return "Despesa de id " + despesa.buscaId() + "atualizada com sucesso.";
    }
    
    /**
     * Método para teste, não estará disponível no controle da classe de Despesa.
     * @return um {@code ArrayList} contendo todas as despesas registradas no banco,
     * sem nenhum tipo de filtro.
     */
    public ArrayList<DespesaVO> buscarTodasDespesas () {
       ArrayList<DespesaVO> despesas = new ArrayList<DespesaVO>();
        try {
            Connection connection = ConnectionFactory.getConnection();
            PreparedStatement stmt = connection.prepareStatement ("SELECT * FROM despesa");
            
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                DespesaVO despesa = new DespesaVO(
                    rs.getLong("id"),
                    rs.getLong("associado_Id"),
                    rs.getString("categoria_gasto"),
                    rs.getString("destinatario"),
                    rs.getDouble("valor_gasto"),
                    rs.getString("data_transacao"),
                    rs.getString("descricao_despesa")
                );
                despesas.add(despesa);
            }                  
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return despesas;
    }
}
