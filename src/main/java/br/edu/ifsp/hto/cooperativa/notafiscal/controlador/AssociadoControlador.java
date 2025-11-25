/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifsp.hto.cooperativa.notafiscal.controlador;

import br.edu.ifsp.hto.cooperativa.notafiscal.controlador.API.IAssociadoControlador;
import br.edu.ifsp.hto.cooperativa.notafiscal.modelo.dto.AssociadoTO;
import br.edu.ifsp.hto.cooperativa.notafiscal.modelo.negocios.NegociosFactory;

import java.util.List;

public class AssociadoControlador implements IAssociadoControlador {

    @Override
    public List<AssociadoTO> buscar() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public AssociadoTO obter(long id) {
        var negociosFactory = NegociosFactory.getInstance();
        return negociosFactory.getAssociado().buscarId(id);
    }

    @Override
    public AssociadoTO obter(String cnpj) {
        var negociosFactory = NegociosFactory.getInstance();
        return negociosFactory.getAssociado().buscarCnpj(cnpj);    }

    @Override
    public void cadastrar(AssociadoTO associado) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
