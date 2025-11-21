/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package br.edu.ifsp.hto.cooperativa.notafiscal.controlador.API;

import br.edu.ifsp.hto.cooperativa.notafiscal.modelo.dto.NotaFiscalEletronicaTO;
import br.edu.ifsp.hto.cooperativa.notafiscal.modelo.vo.AssociadoVO;
import java.util.List;

public interface INotaFiscalEletronicaControlador {
    
    public NotaFiscalEletronicaTO obter(int id);
    public NotaFiscalEletronicaTO obter(String chaveAcesso);
    public List<NotaFiscalEletronicaTO> buscar(AssociadoVO associado);
    public List<NotaFiscalEletronicaTO> buscar();
    //public NotaFiscalEletronicaTO emitirNotaFiscalEletronica(VendaTO venda)
    
}
