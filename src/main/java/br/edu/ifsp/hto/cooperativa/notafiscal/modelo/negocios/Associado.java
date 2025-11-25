/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifsp.hto.cooperativa.notafiscal.modelo.negocios;

import java.util.ArrayList;
import java.util.List;

import br.edu.ifsp.hto.cooperativa.notafiscal.modelo.dto.AssociadoTO;
import br.edu.ifsp.hto.cooperativa.notafiscal.modelo.vo.AssociadoVO;

public class Associado extends BaseNegocios{
    
    public void cadastrar(AssociadoTO associado)
    {
        if (associado == null)
            return;
        //if (associado.endereco != null)    
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
            resultado.endereco = DAOFactory.getEnderecoDAO().buscarId(associado.getEnderecoId());

        return resultado;
    }
    
    public List<AssociadoTO> obterTodos()
    {
        var resultado = new ArrayList<AssociadoTO>();
        List<AssociadoVO> associados = DAOFactory.getAssociadoDAO().obterTodos();
        for (var associado : associados)
        {
            var associadoTO = new AssociadoTO();

            var endereco = DAOFactory.getEnderecoDAO().buscarId(associado.getEnderecoId());
            if (endereco != null)
                associadoTO.endereco = endereco;
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
            resultado.endereco = DAOFactory.getEnderecoDAO().buscarId(associado.getEnderecoId());

        return resultado;
    }
}
