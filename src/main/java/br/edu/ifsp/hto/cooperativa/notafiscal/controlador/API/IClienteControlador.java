/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package br.edu.ifsp.hto.cooperativa.notafiscal.controlador.API;

import br.edu.ifsp.hto.cooperativa.notafiscal.modelo.dto.ClienteTO;
import java.util.List;

public interface IClienteControlador {
    
    public List<ClienteTO> buscar();
    public ClienteTO obter(long id);
    public ClienteTO obter(String cpfCnpj);
}
