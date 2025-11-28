/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifsp.hto.cooperativa.notafiscal.modelo.dto;
import br.edu.ifsp.hto.cooperativa.notafiscal.modelo.vo.NotaFiscalEletronicaVO;
import br.edu.ifsp.hto.cooperativa.notafiscal.modelo.vo.NotaFiscalItemVO;
import br.edu.ifsp.hto.cooperativa.notafiscal.modelo.vo.NotaFiscalXmlVO;
import java.util.List;

public class NotaFiscalEletronicaTO {
    public NotaFiscalEletronicaVO notaFiscalEletronica;
    public NotaFiscalXmlVO notaFiscalXml;
    public List<NotaFiscalItemVO> notaFiscalItens;
    public AssociadoTO associadoTO;
    public ClienteTO clienteTO;
    
}
