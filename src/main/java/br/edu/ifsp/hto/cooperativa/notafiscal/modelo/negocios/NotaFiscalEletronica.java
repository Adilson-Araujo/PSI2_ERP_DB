/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifsp.hto.cooperativa.notafiscal.modelo.negocios;

import br.edu.ifsp.hto.cooperativa.notafiscal.modelo.dto.*;
import br.edu.ifsp.hto.cooperativa.notafiscal.modelo.nfeSchema.ChaveAcesso;
import br.edu.ifsp.hto.cooperativa.notafiscal.modelo.nfeSchema.nfe.*;
import br.edu.ifsp.hto.cooperativa.notafiscal.modelo.vo.*;
import br.edu.ifsp.hto.cooperativa.vendas.modelo.vo.ItemPedidoVO;
import br.edu.ifsp.hto.cooperativa.vendas.modelo.vo.VendaVO;
import jakarta.xml.bind.*;
import br.com.swconsultoria.impressao.model.Impressao;
import br.com.swconsultoria.impressao.service.ImpressaoService;
import br.com.swconsultoria.impressao.util.ImpressaoUtil;

import java.io.File;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.awt.Desktop;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class NotaFiscalEletronica extends BaseNegocios {

    public void gerarNfeVenda(VendaVO venda)
    {

    }
    public void gerarDanfeNfe(NotaFiscalEletronicaTO nfe, List<ItemPedidoVO> produtos)
    {
        nfe.associadoTO = Factory.getAssociado().buscarId(nfe.notaFiscalEletronica.getAssociadoId());
        nfe.clienteTO = Factory.getCliente().buscarId(nfe.notaFiscalEletronica.getClienteId());
        nfe.notaFiscalEletronica.setRazaoSocial(nfe.associadoTO.associado.getRazaoSocial());
        incluirNotaFiscalEletronica(nfe.notaFiscalEletronica);
        nfe.notaFiscalItens = new ArrayList<>();
        for (var prod : produtos)
        {
            var item = new NotaFiscalItemVO();
            item.setCfop("5101");
            item.setNcm("0");
            item.setQuantidade(Integer.parseInt(prod.getQuantidadeTotal().toString()));
            item.setValorTotal(prod.getValorTotal());
            item.setValorUnitario(prod.getValorUnitario());
            item.setProdutoId((int)prod.getProdutoId());
            item.setNotaFiscalEletronicaId(nfe.notaFiscalEletronica.getId());
            Factory.getNotaFiscalItem().adicionar(item);
            nfe.notaFiscalItens.add(item);
        }

        nfe.notaFiscalXml = new NotaFiscalXmlVO();
        nfe.notaFiscalXml.setId(nfe.notaFiscalEletronica.getId());
        nfe.notaFiscalEletronica.setNumeroNotaFiscal(String.valueOf(nfe.notaFiscalEletronica.getId()));
        gerarChaveAcesso(nfe);
        alterarNotaFiscalEletronica(nfe.notaFiscalEletronica);
        gerarXml(nfe);
        imprimirDanfePdf(nfe);
    }

    private void alterarNotaFiscalEletronica(NotaFiscalEletronicaVO nfe) {
        if (nfe == null)
            return;
        DAOFactory.getNotaFiscalEletronicaDAO().atualizar(nfe);
    }

    public void gerarChaveAcesso(NotaFiscalEletronicaTO nfe)
    {
        var cUf = "35";
        var mes = nfe.notaFiscalEletronica.getDataEmissao().getMonth().toString();
        var ano = nfe.notaFiscalEletronica.getDataEmissao().getYear();
        var cnpj = nfe.associadoTO.associado.getCnpj();
        var serie = 1;
        var modelo = "55";
        var numeroNf = nfe.notaFiscalEletronica.getNumeroNotaFiscal();
        var tpEmis = "1";
        var cNf = gerarCodigoNotaFiscal();

        var chaveAcesso = ChaveAcesso.fromComponents(cUf, ano + mes, cnpj, modelo, serie, Long.parseLong(numeroNf), tpEmis, cNf);
        nfe.notaFiscalEletronica.setChaveAcesso(chaveAcesso.getChave44());
    }

    public String gerarCodigoNotaFiscal()
    {
        long seed = System.nanoTime() ^ (long) (Math.random() * 1000000000L);
        long cnf = Math.abs(seed) % 100_000_000L; // garante 8 dígitos
        return String.format("%08d", cnf);
    }

    public void incluirNotaFiscalEletronica(NotaFiscalEletronicaVO nfe){
        if (nfe == null)
            return;

        DAOFactory.getNotaFiscalEletronicaDAO().adicionar(nfe);
    }

    public byte[] imprimirDanfeByte(String xml){
        try {
            Impressao impressao = ImpressaoUtil.impressaoPadraoNFe(xml);
            return ImpressaoService.impressaoPdfByte(impressao);
        }
        catch(Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public void imprimirDanfePdf(NotaFiscalEletronicaTO nfe){
        try {
            Impressao impressao = ImpressaoUtil.impressaoPadraoNFe(nfe.notaFiscalXml.getConteudo());
            var caminho = Paths.get("c:/Temp/nfe/");
            if (Files.notExists(caminho))
                Files.createDirectories(caminho);
            var destino = caminho + "/nfe-" + nfe.notaFiscalEletronica.getNumeroNotaFiscal() + ".pdf";
            File arquivo = new File(destino);
            arquivo.createNewFile();
            ImpressaoService.impressaoPdfArquivo(impressao, destino);
            Desktop.getDesktop().open(arquivo);
        }
        catch(Exception ex) {
            ex.printStackTrace();
        }
    }
    public void gerarXml(NotaFiscalEletronicaTO nfe) {
        try {
            var context = JAXBContext.newInstance(TNFe.class);
            var marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            TNFe tnFe = new TNFe();
            TNFe.InfNFe inf = new TNFe.InfNFe();
            tnFe.setInfNFe(inf);

            // Ajustar id e versão, id deve ser a sigla NFe seguida da chave de acesso
            // Se for possivel assinar no futuro, deverá ser usado na tag <Signature>
            inf.setId("NFe" + nfe.notaFiscalEletronica.getChaveAcesso());
            inf.setVersao("4.00");

            inf.setIde(buildIde(nfe));
            inf.setEmit(buildEmit(nfe));
            inf.setDest(buildDest(nfe));

            buildItens(nfe, inf);
            buildTotais(nfe, inf);

            var sw = new StringWriter();
            marshaller.marshal(tnFe, sw);
            nfe.notaFiscalXml.setConteudo(sw.toString());

        } catch (Exception e) {
            throw new RuntimeException("Erro ao gerar XML da NFe", e);
        }
    }

    private TNFe.InfNFe.Ide buildIde(NotaFiscalEletronicaTO nfe) {
        var vo = nfe.notaFiscalEletronica;

        TNFe.InfNFe.Ide ide = new TNFe.InfNFe.Ide();
        ide.setCUF("35");
        ide.setNatOp("VENDA");
        ide.setMod("55");
        ide.setSerie(vo.getNumeroSerie());
        ide.setNNF(vo.getNumeroNotaFiscal());
        ide.setDhEmi(vo.getDataEmissao() + "-03:00");
        ide.setTpAmb(vo.getTipoAmbiente().toString());
        ide.setTpEmis(vo.getTipoFormaEmissao().toString());
        ide.setTpImp("1");
        ide.setTpNF(vo.getTipoOperacao().toString());
        ide.setFinNFe("1");
        ide.setProcEmi("0");
        return ide;
    }

    private TNFe.InfNFe.Emit buildEmit(NotaFiscalEletronicaTO nfe) {
        var a = nfe.associadoTO.associado;
        var e = nfe.associadoTO.endereco;

        var emit = new TNFe.InfNFe.Emit();
        emit.setCNPJ(a.getCnpj());
        emit.setXNome(a.getRazaoSocial());
        emit.setXFant(a.getNomeFantasia());
        emit.setIE(a.getInscricaoEstadual());

        var ender = new TEnderEmi();
        ender.setXLgr(e.getRua());
        ender.setNro(String.valueOf(e.getNumero()));
        ender.setXBairro(e.getBairro());
        ender.setCMun("3550308");
        ender.setXMun(e.getCidade());
        ender.setUF(TUfEmi.valueOf(e.getEstado()));
        ender.setCEP(e.getCep().replace("-", ""));
        emit.setEnderEmit(ender);

        return emit;
    }

    private TNFe.InfNFe.Dest buildDest(NotaFiscalEletronicaTO nfe) {
        var c = nfe.clienteTO.cliente;
        var e = nfe.clienteTO.endereco;

        TNFe.InfNFe.Dest dest = new TNFe.InfNFe.Dest();

        if (c.getCpfCnpj().length() == 11)
            dest.setCPF(c.getCpfCnpj());
        else
            dest.setCNPJ(c.getCpfCnpj());

        dest.setXNome(c.getRazaoSocial());

        var ender = new TEndereco();
        ender.setXLgr(e.getRua());
        ender.setNro(String.valueOf(e.getNumero()));
        ender.setXBairro(e.getBairro());
        ender.setCMun("3550308");
        ender.setXMun(e.getCidade());
        ender.setUF(TUf.valueOf(e.getEstado()));
        ender.setCEP(e.getCep().replace("-", ""));
        dest.setEnderDest(ender);

        return dest;
    }

    private void buildItens(NotaFiscalEletronicaTO nfe, TNFe.InfNFe inf) {

        int n = 1;
        for (var it : nfe.notaFiscalItens) {

            TNFe.InfNFe.Det det = new TNFe.InfNFe.Det();
            det.setNItem(String.valueOf(n++));

            TNFe.InfNFe.Det.Prod prod = new TNFe.InfNFe.Det.Prod();
            prod.setCFOP(it.getCfop());
            prod.setNCM(it.getNcm());
            prod.setQCom(it.getQuantidade().toString());
            prod.setVUnCom(it.getValorUnitario().toString());
            prod.setVProd(it.getValorTotal().toString());
            prod.setXProd("PRODUTO " + it.getProdutoId());

            det.setProd(prod);

            TNFe.InfNFe.Det.Imposto imposto = new TNFe.InfNFe.Det.Imposto();
            det.setImposto(imposto);

            inf.getDet().add(det);
        }
    }

    private void buildTotais(NotaFiscalEletronicaTO nfe, TNFe.InfNFe inf) {

        var total = BigDecimal.ZERO;

        for (var item : nfe.notaFiscalItens) {
            total = total.add(item.getValorTotal());
        }

        var totalNode = new TNFe.InfNFe.Total();
        var icms = new TNFe.InfNFe.Total.ICMSTot();

        icms.setVProd(total.toString());
        icms.setVNF(total.toString());

        totalNode.setICMSTot(icms);
        inf.setTotal(totalNode);
    }
}
