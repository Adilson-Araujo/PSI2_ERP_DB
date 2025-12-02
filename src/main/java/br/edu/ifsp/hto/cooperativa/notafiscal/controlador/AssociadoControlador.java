/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifsp.hto.cooperativa.notafiscal.controlador;

import br.edu.ifsp.hto.cooperativa.notafiscal.modelo.dto.AssociadoTO;
import br.edu.ifsp.hto.cooperativa.notafiscal.modelo.negocios.NegociosFactory;

import java.util.List;

public class AssociadoControlador extends ControladorBase {

    public AssociadoTO obter(long id) {
        return executarTransacao(() -> negociosFactory().getAssociado().buscarId(id));
    }

    public AssociadoTO obter(String cnpj) {
        return executarTransacao(() -> negociosFactory().getAssociado().buscarCnpj(cnpj));    }

    public void cadastrar(AssociadoTO associado) {
        executarTransacao(() -> negociosFactory().getAssociado().cadastrar(associado));
    }
}
