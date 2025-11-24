/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifsp.hto.cooperativa.notafiscal.controlador;

import br.edu.ifsp.hto.cooperativa.notafiscal.controlador.API.INotaFiscalEletronicaControlador;
import br.edu.ifsp.hto.cooperativa.notafiscal.modelo.dto.NotaFiscalEletronicaTO;
import br.edu.ifsp.hto.cooperativa.notafiscal.modelo.vo.AssociadoVO;
import java.util.List;

public class NotaFiscalEletronicaControlador implements INotaFiscalEletronicaControlador {

    @Override
    public NotaFiscalEletronicaTO obter(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public NotaFiscalEletronicaTO obter(String chaveAcesso) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<NotaFiscalEletronicaTO> buscar(AssociadoVO associado) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<NotaFiscalEletronicaTO> buscar() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    //@Override
    //public NotaFiscalEletronicaTO emitirNotaFiscalEletronica(VendaTO venda){}
}
