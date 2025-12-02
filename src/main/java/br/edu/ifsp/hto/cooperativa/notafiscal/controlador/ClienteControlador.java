/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifsp.hto.cooperativa.notafiscal.controlador;

import br.edu.ifsp.hto.cooperativa.notafiscal.modelo.dto.ClienteTO;
import br.edu.ifsp.hto.cooperativa.notafiscal.modelo.negocios.NegociosFactory;

import java.util.List;

public class ClienteControlador extends ControladorBase {

    public ClienteTO obter(long id) {
        return executarTransacao(() -> negociosFactory().getCliente().buscarId(id));
    }

    public ClienteTO obter(String cpfCnpj) {
        return executarTransacao(() -> negociosFactory().getCliente().buscarCpfCnpj(cpfCnpj));
    }

    public void cadastrar(ClienteTO cliente){
        executarTransacao(() -> negociosFactory().getCliente().cadastrar(cliente));
    }
    
}
