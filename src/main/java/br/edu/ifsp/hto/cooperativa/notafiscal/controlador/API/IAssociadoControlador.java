/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package br.edu.ifsp.hto.cooperativa.notafiscal.controlador.API;
import br.edu.ifsp.hto.cooperativa.notafiscal.modelo.dto.AssociadoTO;
import java.util.List;

public interface IAssociadoControlador{

    public List<AssociadoTO> buscar();
    public AssociadoTO obter(long id);
    public AssociadoTO obter(String cnpj);
    public void cadastrar(AssociadoTO associado);
}
