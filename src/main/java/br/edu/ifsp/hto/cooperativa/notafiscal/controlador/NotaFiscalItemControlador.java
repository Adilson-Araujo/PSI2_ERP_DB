/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifsp.hto.cooperativa.notafiscal.controlador;

import br.edu.ifsp.hto.cooperativa.notafiscal.modelo.vo.NotaFiscalItemVO;

import java.util.List;

public class NotaFiscalItemControlador extends ControladorBase{

    public List<NotaFiscalItemVO> buscar(long notaFiscalEletronicaId) {
        return executarTransacao(() -> negociosFactory().getNotaFiscalItem().buscarPorNf(notaFiscalEletronicaId));
    }
    
}
