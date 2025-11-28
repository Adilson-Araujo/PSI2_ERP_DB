/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifsp.hto.cooperativa.notafiscal.controlador;

import br.edu.ifsp.hto.cooperativa.estoque.modelo.vo.Produto;
import br.edu.ifsp.hto.cooperativa.notafiscal.controlador.API.INotaFiscalEletronicaControlador;
import br.edu.ifsp.hto.cooperativa.notafiscal.modelo.dto.ClienteTO;
import br.edu.ifsp.hto.cooperativa.notafiscal.modelo.dto.NotaFiscalEletronicaTO;
import br.edu.ifsp.hto.cooperativa.notafiscal.modelo.negocios.BaseNegocios;
import br.edu.ifsp.hto.cooperativa.notafiscal.modelo.negocios.NegociosFactory;
import br.edu.ifsp.hto.cooperativa.notafiscal.modelo.negocios.NotaFiscalEletronica;
import br.edu.ifsp.hto.cooperativa.notafiscal.modelo.negocios.NotaFiscalItem;
import br.edu.ifsp.hto.cooperativa.notafiscal.modelo.vo.AssociadoVO;
import br.edu.ifsp.hto.cooperativa.notafiscal.modelo.vo.NotaFiscalEletronicaVO;
import br.edu.ifsp.hto.cooperativa.notafiscal.modelo.vo.NotaFiscalItemVO;
import br.edu.ifsp.hto.cooperativa.vendas.modelo.vo.ItemPedidoVO;
import br.edu.ifsp.hto.cooperativa.vendas.modelo.vo.VendaVO;
import com.lowagie.text.pdf.AcroFields;
import com.sun.codemodel.JForEach;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class NotaFiscalEletronicaControlador implements INotaFiscalEletronicaControlador {

    @Override
    public NotaFiscalEletronicaTO obter(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public NotaFiscalEletronicaTO obter(String chaveAcesso) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<NotaFiscalEletronicaTO> buscar(AssociadoVO associado) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<NotaFiscalEletronicaTO> buscar() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    @Override
    public NotaFiscalEletronicaTO emitirNotaFiscalEletronica(VendaVO venda, long clienteId, List<ItemPedidoVO> produtosVendidos, BigDecimal valorFrete, String dadosAdicionais){
        var instance = NegociosFactory.getInstance();
        var nfeTO = new NotaFiscalEletronicaTO();
        var nfeVO = new NotaFiscalEletronicaVO();
        nfeTO.notaFiscalEletronica = nfeVO;
        nfeVO.setAtivo(true);
        nfeVO.setDataEmissao(LocalDateTime.now());
        nfeVO.setClienteId(nfeVO.getClienteId());
        nfeVO.setAssociadoId(venda.getAssociadoId());
        nfeVO.setTipoAmbiente(1);
        nfeVO.setTipoStatusEnvioSefaz(0);
        nfeVO.setDataInclusao(LocalDateTime.now());
        nfeVO.setTipoFormaEmissao(1);
        nfeVO.setTipoOperacao(1);
        nfeVO.setValorTotal(venda.getValorTotal());
        nfeVO.setValorFrete(valorFrete);
        nfeVO.setClienteId(clienteId);
        nfeVO.setDadosAdicionais(dadosAdicionais);
        nfeVO.setNumeroProtocolo(0);
        nfeVO.setChaveAcesso(String.format("%044d", 0));
        nfeVO.setNumeroNotaFiscal("0");
        nfeVO.setNumeroSerie("001");
        instance.getNotaFiscalEletronica().gerarDanfeNfe(nfeTO, produtosVendidos);
        return nfeTO;
    }

    public static void main (String args[]){
        var venda = new VendaVO();
        venda.setDataCompra(LocalDateTime.now());
        venda.setAssociadoId(1);
        venda.setValorTotal(BigDecimal.valueOf(150));
        var clienteId = 1;
        var produtos = new ArrayList<ItemPedidoVO>();
        var produto = new ItemPedidoVO();
        produto.setProdutoId(1);
        produto.setValorUnitario(BigDecimal.valueOf(15));
        produto.setValorTotal(BigDecimal.valueOf(30));
        produto.setQuantidadeTotal(BigDecimal.valueOf(2));
        produtos.add(produto);
        var valorFrete = BigDecimal.valueOf(25);
        var dadosAdicionais = "Venda baseada no cupom fiscal xpto para o cliente João da Silva";
        new NotaFiscalEletronicaControlador().emitirNotaFiscalEletronica(venda,clienteId, produtos, valorFrete, dadosAdicionais);
    }
}































