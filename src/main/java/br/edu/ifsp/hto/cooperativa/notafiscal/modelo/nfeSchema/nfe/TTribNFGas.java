//
// Este arquivo foi gerado pela Eclipse Implementation of JAXB, v4.0.6 
// Consulte https://eclipse-ee4j.github.io/jaxb-ri 
// Todas as modificações neste arquivo serão perdidas após a recompilação do esquema de origem. 
//


package br.edu.ifsp.hto.cooperativa.notafiscal.modelo.nfeSchema.nfe;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * Grupo de informações da Tributação da NFGas
 * 
 * &lt;p&gt;Classe Java de TTribNFGas complex type.&lt;/p&gt;
 * 
 * &lt;p&gt;O seguinte fragmento do esquema especifica o conteúdo esperado contido dentro desta classe.&lt;/p&gt;
 * 
 * &lt;pre&gt;{&#064;code
 * &lt;complexType name="TTribNFGas"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="CST" type="{http://www.portalfiscal.inf.br/nfe}TCST"/&gt;
 *         &lt;element name="cClassTrib" type="{http://www.portalfiscal.inf.br/nfe}TcClassTrib"/&gt;
 *         &lt;element name="indDoacao" type="{http://www.portalfiscal.inf.br/nfe}TIndDoacao" minOccurs="0"/&gt;
 *         &lt;choice minOccurs="0"&gt;
 *           &lt;element name="gIBSCBS" type="{http://www.portalfiscal.inf.br/nfe}TCIBS"/&gt;
 *           &lt;element name="gIBSCBSMono" type="{http://www.portalfiscal.inf.br/nfe}TMonofasia"/&gt;
 *         &lt;/choice&gt;
 *         &lt;element name="gEstornoCred" type="{http://www.portalfiscal.inf.br/nfe}TEstornoCred" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * }&lt;/pre&gt;
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TTribNFGas", propOrder = {
    "cst",
    "cClassTrib",
    "indDoacao",
    "gibscbs",
    "gibscbsMono",
    "gEstornoCred"
})
public class TTribNFGas {

    /**
     * Código Situação Tributária do IBS/CBS
     * 
     */
    @XmlElement(name = "CST", required = true)
    protected String cst;
    @XmlElement(required = true)
    protected String cClassTrib;
    protected String indDoacao;
    @XmlElement(name = "gIBSCBS")
    protected TCIBS gibscbs;
    /**
     * Informar essa opção da Choice para Monofasia
     * 
     */
    @XmlElement(name = "gIBSCBSMono")
    protected TMonofasia gibscbsMono;
    /**
     * Informado conforme indicador no cClassTrib
     * 
     */
    protected TEstornoCred gEstornoCred;

    /**
     * Código Situação Tributária do IBS/CBS
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCST() {
        return cst;
    }

    /**
     * Define o valor da propriedade cst.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     * @see #getCST()
     */
    public void setCST(String value) {
        this.cst = value;
    }

    /**
     * Obtém o valor da propriedade cClassTrib.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCClassTrib() {
        return cClassTrib;
    }

    /**
     * Define o valor da propriedade cClassTrib.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCClassTrib(String value) {
        this.cClassTrib = value;
    }

    /**
     * Obtém o valor da propriedade indDoacao.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIndDoacao() {
        return indDoacao;
    }

    /**
     * Define o valor da propriedade indDoacao.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIndDoacao(String value) {
        this.indDoacao = value;
    }

    /**
     * Obtém o valor da propriedade gibscbs.
     * 
     * @return
     *     possible object is
     *     {@link TCIBS }
     *     
     */
    public TCIBS getGIBSCBS() {
        return gibscbs;
    }

    /**
     * Define o valor da propriedade gibscbs.
     * 
     * @param value
     *     allowed object is
     *     {@link TCIBS }
     *     
     */
    public void setGIBSCBS(TCIBS value) {
        this.gibscbs = value;
    }

    /**
     * Informar essa opção da Choice para Monofasia
     * 
     * @return
     *     possible object is
     *     {@link TMonofasia }
     *     
     */
    public TMonofasia getGIBSCBSMono() {
        return gibscbsMono;
    }

    /**
     * Define o valor da propriedade gibscbsMono.
     * 
     * @param value
     *     allowed object is
     *     {@link TMonofasia }
     *     
     * @see #getGIBSCBSMono()
     */
    public void setGIBSCBSMono(TMonofasia value) {
        this.gibscbsMono = value;
    }

    /**
     * Informado conforme indicador no cClassTrib
     * 
     * @return
     *     possible object is
     *     {@link TEstornoCred }
     *     
     */
    public TEstornoCred getGEstornoCred() {
        return gEstornoCred;
    }

    /**
     * Define o valor da propriedade gEstornoCred.
     * 
     * @param value
     *     allowed object is
     *     {@link TEstornoCred }
     *     
     * @see #getGEstornoCred()
     */
    public void setGEstornoCred(TEstornoCred value) {
        this.gEstornoCred = value;
    }

}
