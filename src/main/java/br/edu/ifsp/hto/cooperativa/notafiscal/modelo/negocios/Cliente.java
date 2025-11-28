/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifsp.hto.cooperativa.notafiscal.modelo.negocios;

import br.edu.ifsp.hto.cooperativa.notafiscal.modelo.dto.ClienteTO;

public class Cliente extends BaseNegocios {
    public ClienteTO buscarCpfCnpj(String cpfCnpj){
        if (cpfCnpj == null)
            return null;
        var resultado = new ClienteTO();
        var cliente = DAOFactory.getClienteDAO().buscarCnpj(cpfCnpj);
        if (cliente == null)
            return null;

        resultado.cliente = cliente;
        if (cliente.getEnderecoId() != 0)
            resultado.endereco = DAOFactory.getEnderecoDAO().buscarId(cliente.getEnderecoId());

        return resultado;
    }

    public ClienteTO buscarId(long id) {
        if (id == 0)
            return null;
        var resultado = new ClienteTO();
        var cliente = DAOFactory.getClienteDAO().buscarId(id);
        if (cliente == null)
            return null;

        resultado.cliente = cliente;
        if (cliente.getEnderecoId() != 0)
            resultado.endereco = DAOFactory.getEnderecoDAO().buscarId(cliente.getEnderecoId());

        return resultado;
    }
}
