/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package br.edu.ifsp.hto.cooperativa.notafiscal.controlador.API;

import br.edu.ifsp.hto.cooperativa.notafiscal.modelo.vo.EnderecoVO;
import java.util.List;

public interface IEnderecoControlador {
    
    public List<EnderecoVO> buscarTodos();
    public EnderecoVO obter(int id);
}
