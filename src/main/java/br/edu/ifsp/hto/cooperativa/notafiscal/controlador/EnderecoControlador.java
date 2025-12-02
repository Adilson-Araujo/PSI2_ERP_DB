/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifsp.hto.cooperativa.notafiscal.controlador;

import br.edu.ifsp.hto.cooperativa.notafiscal.modelo.vo.EnderecoVO;
import java.util.List;

public class EnderecoControlador extends ControladorBase{

    public EnderecoVO obter(long id) {
        return executarTransacao(() -> negociosFactory().getEndereco().obter(id));
    }
    
}
