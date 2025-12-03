/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifsp.hto.cooperativa.notafiscal.modelo.negocios;

import br.edu.ifsp.hto.cooperativa.notafiscal.modelo.vo.NotaFiscalXmlVO;

import java.sql.SQLException;

/**
 *
 * @author ht3036979
 */
public class NotaFiscalXml extends BaseNegocios {

    public NotaFiscalXmlVO obter(long id)
    {
        return DAOFactory.getNotaFiscalXmlDAO().buscarPorId(id);
    }

    public void adicionar(NotaFiscalXmlVO nfXml){
        if (nfXml == null)
            return;
        DAOFactory.getNotaFiscalXmlDAO().adicionar(nfXml);
    }
}
