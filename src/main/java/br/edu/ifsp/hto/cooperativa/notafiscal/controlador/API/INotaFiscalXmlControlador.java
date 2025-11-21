/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package br.edu.ifsp.hto.cooperativa.notafiscal.controlador.API;

import br.edu.ifsp.hto.cooperativa.notafiscal.modelo.vo.NotaFiscalXmlVO;
import java.util.List;

public interface INotaFiscalXmlControlador {
    
    public NotaFiscalXmlVO obter(int id);
    public List<NotaFiscalXmlVO> buscar(); // Deve ser lento
    
}
