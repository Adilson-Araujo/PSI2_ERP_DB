/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifsp.hto.cooperativa.notafiscal.modelo.negocios;

import br.edu.ifsp.hto.cooperativa.notafiscal.modelo.vo.EnderecoVO;


public class Endereco extends BaseNegocios{

    public EnderecoVO obter(long id){
        return DAOFactory.getEnderecoDAO().buscarId(id);

    }

    public void adicionar(EnderecoVO endereco) {
        if (endereco == null)
            return;
        DAOFactory.getEnderecoDAO().adicionar(endereco);
    }
}
