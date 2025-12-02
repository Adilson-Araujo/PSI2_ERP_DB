/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifsp.hto.cooperativa.notafiscal.modelo.negocios;

import br.edu.ifsp.hto.cooperativa.notafiscal.modelo.vo.NotaFiscalItemVO;
import br.edu.ifsp.hto.cooperativa.notafiscal.recursos.DbHelper;

import java.util.List;

public class NotaFiscalItem extends BaseNegocios{
    public void adicionar(NotaFiscalItemVO nfItem)
    {
        if (nfItem == null)
            return;
        nfItem.setId(DbHelper.gerarPk("nota_fiscal_item"));
        DAOFactory.getNotaFiscalItemDAO().adicionar(nfItem);
    }

    public List<NotaFiscalItemVO> buscarPorNf(Long id) {
        return DAOFactory.getNotaFiscalItemDAO().buscarPorNf(id);
    }
}
