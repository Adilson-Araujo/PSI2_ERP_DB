/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifsp.hto.cooperativa.notafiscal.modelo.negocios;

import br.edu.ifsp.hto.cooperativa.notafiscal.modelo.vo.NotaFiscalXmlVO;
import br.edu.ifsp.hto.cooperativa.notafiscal.recursos.DbHelper;

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
        nfXml.setHash(DbHelper.md5(nfXml.getConteudo()));
        DAOFactory.getNotaFiscalXmlDAO().adicionar(nfXml);
    }
}
