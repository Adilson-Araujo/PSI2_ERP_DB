/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifsp.hto.cooperativa.notafiscal.modelo.negocios;
import br.edu.ifsp.hto.cooperativa.notafiscal.modelo.dao.AssociadoDAO;
import br.edu.ifsp.hto.cooperativa.notafiscal.modelo.dao.ClienteDAO;
import br.edu.ifsp.hto.cooperativa.notafiscal.modelo.dao.EnderecoDAO;
import br.edu.ifsp.hto.cooperativa.notafiscal.modelo.dao.NotaFiscalEletronicaDAO;
import br.edu.ifsp.hto.cooperativa.notafiscal.modelo.dao.NotaFiscalItemDAO;
import br.edu.ifsp.hto.cooperativa.notafiscal.modelo.dao.NotaFiscalXmlDAO;

public class DaoFactory {

    private static DaoFactory _instance;

    private AssociadoDAO _associadoDAO;
    private ClienteDAO _clienteDAO;
    private EnderecoDAO _enderecoDAO;
    private NotaFiscalEletronicaDAO _notaFiscalEletronicaDAO;
    private NotaFiscalItemDAO _notaFiscalItemDAO;
    private NotaFiscalXmlDAO _notaFiscalXmlDAO;

    private DaoFactory(){}

    public static DaoFactory getInstance(){
        if (_instance == null)
            _instance = new DaoFactory();
        return _instance;
    }
    
    public AssociadoDAO getAssociadoDAO() {
        if (_associadoDAO == null) {
            _associadoDAO = new AssociadoDAO();
        }
        return _associadoDAO;
    }

    public ClienteDAO getClienteDAO() {
        if (_clienteDAO == null) {
            _clienteDAO = new ClienteDAO();
        }
        return _clienteDAO;
    }

    public EnderecoDAO getEnderecoDAO() {
        if (_enderecoDAO == null) {
            _enderecoDAO = new EnderecoDAO();
        }
        return _enderecoDAO;
    }

    public NotaFiscalEletronicaDAO getNotaFiscalEletronicaDAO() {
        if (_notaFiscalEletronicaDAO == null) {
            _notaFiscalEletronicaDAO = new NotaFiscalEletronicaDAO();
        }
        return _notaFiscalEletronicaDAO;
    }

    public NotaFiscalItemDAO getNotaFiscalItemDAO() {
        if (_notaFiscalItemDAO == null) {
            _notaFiscalItemDAO = new NotaFiscalItemDAO();
        }
        return _notaFiscalItemDAO;
    }

    public NotaFiscalXmlDAO getNotaFiscalXmlDAO() {
        if (_notaFiscalXmlDAO == null) {
            _notaFiscalXmlDAO = new NotaFiscalXmlDAO();
        }
        return _notaFiscalXmlDAO;
    }
}
