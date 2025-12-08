/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifsp.hto.cooperativa.notafiscal.modelo.negocios;

import br.edu.ifsp.hto.cooperativa.ConnectionFactory;
import br.edu.ifsp.hto.cooperativa.notafiscal.modelo.dto.ClienteTO;
import br.edu.ifsp.hto.cooperativa.notafiscal.modelo.vo.ClienteVO;
import br.edu.ifsp.hto.cooperativa.notafiscal.recursos.DbHelper;

import java.time.LocalDateTime;

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
            resultado.endereco = Factory.getEndereco().obter(cliente.getEnderecoId());

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
            resultado.endereco = Factory.getEndereco().obter(cliente.getEnderecoId());

        return resultado;
    }

    public void adicionar(ClienteVO cliente){
        if (cliente == null)
            return;
        cliente.setId(DbHelper.gerarPk("cliente"));
        cliente.setDataCadastro(LocalDateTime.now());
        DAOFactory.getClienteDAO().adicionar(cliente);
    }

    public void cadastrar(ClienteTO cliente) {
        if (cliente == null || cliente.cliente == null)
            return;
        if (cliente.endereco != null)
        {
            Factory.getEndereco().adicionar(cliente.endereco);
            cliente.cliente.setEnderecoId(cliente.endereco.getId());
        }
        adicionar(cliente.cliente);
    }
}
