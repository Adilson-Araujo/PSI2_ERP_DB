/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifsp.hto.cooperativa.notafiscal.controlador;

import br.edu.ifsp.hto.cooperativa.notafiscal.controlador.API.IClienteControlador;
import br.edu.ifsp.hto.cooperativa.notafiscal.modelo.dto.ClienteTO;
import br.edu.ifsp.hto.cooperativa.notafiscal.modelo.negocios.NegociosFactory;

import java.util.List;

public class ClienteControlador implements IClienteControlador {

    @Override
    public List<ClienteTO> buscar() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ClienteTO obter(long id) {
        var negociosFactory = NegociosFactory.getInstance();
        return negociosFactory.getCliente().buscarId(id);    }

    @Override
    public ClienteTO obter(String cpfCnpj) {
        var negociosFactory = NegociosFactory.getInstance();
        return negociosFactory.getCliente().buscarCpfCnpj(cpfCnpj);
    }
    
}
