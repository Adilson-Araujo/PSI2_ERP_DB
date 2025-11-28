/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifsp.hto.cooperativa.notafiscal.modelo.negocios;

import br.edu.ifsp.hto.cooperativa.notafiscal.modelo.vo.NotaFiscalItemVO;

/**
 *
 * @author ht3036979
 */
public class NotaFiscalItem extends BaseNegocios{
    public void adicionar(NotaFiscalItemVO nfItem)
    {
        if (nfItem == null)
            return;
        DAOFactory.getNotaFiscalItemDAO().adicionar(nfItem);
    }
}
