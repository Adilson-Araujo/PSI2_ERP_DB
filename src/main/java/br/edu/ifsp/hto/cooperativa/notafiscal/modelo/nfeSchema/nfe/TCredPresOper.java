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
 * Tipo Crédito Presumido da Operação
 * 
 * &lt;p&gt;Classe Java de TCredPresOper complex type.&lt;/p&gt;
 * 
 * &lt;p&gt;O seguinte fragmento do esquema especifica o conteúdo esperado contido dentro desta classe.&lt;/p&gt;
 * 
 * &lt;pre&gt;{&#064;code
 * &lt;complexType name="TCredPresOper"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="vBCCredPres" type="{http://www.portalfiscal.inf.br/nfe}TDec1302RTC"/&gt;
 *         &lt;element name="cCredPres" type="{http://www.portalfiscal.inf.br/nfe}TcCredPres"/&gt;
 *         &lt;element name="gIBSCredPres" type="{http://www.portalfiscal.inf.br/nfe}TCredPres" minOccurs="0"/&gt;
 *         &lt;element name="gCBSCredPres" type="{http://www.portalfiscal.inf.br/nfe}TCredPres" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * }&lt;/pre&gt;
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TCredPresOper", propOrder = {
    "vbcCredPres",
    "cCredPres",
    "gibsCredPres",
    "gcbsCredPres"
})
public class TCredPresOper {

    /**
     * Valor da Base de Cálculo do Crédito Presumido da Operação
     * 
     */
    @XmlElement(name = "vBCCredPres", required = true)
    protected String vbcCredPres;
    /**
     * Código de Classificação do Crédito Presumido do IBS e da CBS
     * 
     */
    @XmlElement(required = true)
    protected String cCredPres;
    /**
     * Grupo de Informações do Crédito Presumido referente ao IBS, quando aproveitado pelo emitente do documento.
     * 
     */
    @XmlElement(name = "gIBSCredPres")
    protected TCredPres gibsCredPres;
    /**
     * Grupo de Informações do Crédito Presumido referente a CBS, quando aproveitado pelo emitente do documento.
     * 
     */
    @XmlElement(name = "gCBSCredPres")
    protected TCredPres gcbsCredPres;

    /**
     * Valor da Base de Cálculo do Crédito Presumido da Operação
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVBCCredPres() {
        return vbcCredPres;
    }

    /**
     * Define o valor da propriedade vbcCredPres.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     * @see #getVBCCredPres()
     */
    public void setVBCCredPres(String value) {
        this.vbcCredPres = value;
    }

    /**
     * Código de Classificação do Crédito Presumido do IBS e da CBS
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCCredPres() {
        return cCredPres;
    }

    /**
     * Define o valor da propriedade cCredPres.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     * @see #getCCredPres()
     */
    public void setCCredPres(String value) {
        this.cCredPres = value;
    }

    /**
     * Grupo de Informações do Crédito Presumido referente ao IBS, quando aproveitado pelo emitente do documento.
     * 
     * @return
     *     possible object is
     *     {@link TCredPres }
     *     
     */
    public TCredPres getGIBSCredPres() {
        return gibsCredPres;
    }

    /**
     * Define o valor da propriedade gibsCredPres.
     * 
     * @param value
     *     allowed object is
     *     {@link TCredPres }
     *     
     * @see #getGIBSCredPres()
     */
    public void setGIBSCredPres(TCredPres value) {
        this.gibsCredPres = value;
    }

    /**
     * Grupo de Informações do Crédito Presumido referente a CBS, quando aproveitado pelo emitente do documento.
     * 
     * @return
     *     possible object is
     *     {@link TCredPres }
     *     
     */
    public TCredPres getGCBSCredPres() {
        return gcbsCredPres;
    }

    /**
     * Define o valor da propriedade gcbsCredPres.
     * 
     * @param value
     *     allowed object is
     *     {@link TCredPres }
     *     
     * @see #getGCBSCredPres()
     */
    public void setGCBSCredPres(TCredPres value) {
        this.gcbsCredPres = value;
    }

}
