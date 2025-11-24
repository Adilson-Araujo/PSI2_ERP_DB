/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package br.edu.ifsp.hto.cooperativa.notafiscal.controlador.API;

import br.edu.ifsp.hto.cooperativa.notafiscal.modelo.dto.NotaFiscalItemTO;
import java.util.List;

public interface INotaFiscalItemControlador {
    
    public NotaFiscalItemTO obter(int id);
    public List<NotaFiscalItemTO> buscar();
    public List<NotaFiscalItemTO> buscar(int notaFiscalEletronicaId);
}
