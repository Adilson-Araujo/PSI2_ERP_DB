/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifsp.hto.cooperativa.notafiscal.modelo.negocios;

public class NegociosFactory {
    
    private static NegociosFactory _instance;
    
    private Associado _associado;
    private NotaFiscalEletronica _notaFiscalEletronica;
    private Cliente _cliente;
    private NotaFiscalItem _notaFiscalItem;
    private NotaFiscalXml _notaFiscalXml;
    private Endereco _endereco;

    private NegociosFactory(){}
    
    public static NegociosFactory getInstance(){
        if (_instance == null)
            _instance = new NegociosFactory();
        return _instance;   
    }
    public Associado getAssociado(){
        if (_associado == null)
            _associado = new Associado();
        return _associado;
}
    public NotaFiscalEletronica getNotaFiscalEletronica(){
        if (_notaFiscalEletronica == null)
            _notaFiscalEletronica = new NotaFiscalEletronica();
        return _notaFiscalEletronica;
    }

    public Cliente getCliente(){
        if (_cliente == null)
            _cliente = new Cliente();
        return _cliente;
    }
    
    public NotaFiscalItem getNotaFiscalItem(){
        if (_notaFiscalItem == null)
            _notaFiscalItem = new NotaFiscalItem();
        return _notaFiscalItem;
    }
    
    public NotaFiscalXml getNotaFiscalXml(){
        if (_notaFiscalXml == null)
            _notaFiscalXml = new NotaFiscalXml();
        return _notaFiscalXml;
    }
    
    public Endereco getEndereco(){
        if (_endereco == null)
            _endereco = new Endereco();
        return _endereco;
    }
}
