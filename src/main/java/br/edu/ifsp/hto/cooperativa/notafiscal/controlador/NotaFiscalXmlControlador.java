/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifsp.hto.cooperativa.notafiscal.controlador;

import br.edu.ifsp.hto.cooperativa.notafiscal.modelo.vo.NotaFiscalXmlVO;
import java.util.List;

public class NotaFiscalXmlControlador extends ControladorBase {

    public NotaFiscalXmlVO obter(long id) {
        return executarTransacao(() -> negociosFactory().getNotaFiscalXml().obter(id));
    }
    
}
