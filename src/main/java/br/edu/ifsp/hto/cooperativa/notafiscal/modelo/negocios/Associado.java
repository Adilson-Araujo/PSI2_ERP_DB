/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifsp.hto.cooperativa.notafiscal.modelo.negocios;

import java.util.ArrayList;
import java.util.List;

import br.edu.ifsp.hto.cooperativa.notafiscal.modelo.dto.AssociadoTO;
import br.edu.ifsp.hto.cooperativa.notafiscal.modelo.vo.AssociadoVO;
import br.edu.ifsp.hto.cooperativa.notafiscal.recursos.DbHelper;

public class Associado extends BaseNegocios{
    
    public void cadastrar(AssociadoTO associado)
    {
        if (associado == null)
            return;

        if (associado.endereco != null) {
            Factory.getEndereco().adicionar(associado.endereco);
            associado.associado.setEnderecoId(associado.endereco.getId());
        }
        adicionar(associado.associado);
    }

    public void adicionar(AssociadoVO associado){
        if (associado == null)
            return;
        associado.setId(DbHelper.gerarPk("associado"));
        DAOFactory.getAssociadoDAO().adicionar(associado);
    }

    public AssociadoTO buscarId(long id){
        if (id == 0)
            return null;
        var resultado = new AssociadoTO();
        var associado = DAOFactory.getAssociadoDAO().buscarId(id);
        if (associado == null)
            return null;

        resultado.associado = associado;
        if (associado.getEnderecoId() != 0)
            resultado.endereco = Factory.getEndereco().obter(associado.getEnderecoId());

        return resultado;
    }
    
    public List<AssociadoTO> obterTodos()
    {
        var resultado = new ArrayList<AssociadoTO>();
        List<AssociadoVO> associados = DAOFactory.getAssociadoDAO().obterTodos();
        for (var associado : associados)
        {
            var associadoTO = new AssociadoTO();
            associadoTO.associado = associado;
            var endereco = Factory.getEndereco().obter(associado.getEnderecoId());
            if (endereco != null)
                associadoTO.endereco = endereco;
            resultado.add(associadoTO);
        }
        return resultado;
    }

    public AssociadoTO buscarCnpj(String cnpj) {
        if (cnpj == null)
            return null;
        var resultado = new AssociadoTO();
        var associado = DAOFactory.getAssociadoDAO().buscarCnpj(cnpj);
        if (associado == null)
            return null;

        resultado.associado = associado;
        if (associado.getEnderecoId() != 0)
            resultado.endereco = Factory.getEndereco().obter(associado.getEnderecoId());

        return resultado;
    }
}
