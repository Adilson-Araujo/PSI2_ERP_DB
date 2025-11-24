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
 * Tipo Tributação Compra Governamental
 * 
 * &lt;p&gt;Classe Java de TTribCompraGov complex type.&lt;/p&gt;
 * 
 * &lt;p&gt;O seguinte fragmento do esquema especifica o conteúdo esperado contido dentro desta classe.&lt;/p&gt;
 * 
 * &lt;pre&gt;{&#064;code
 * &lt;complexType name="TTribCompraGov"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="pAliqIBSUF" type="{http://www.portalfiscal.inf.br/nfe}TDec_0302_04RTC"/&gt;
 *         &lt;element name="vTribIBSUF" type="{http://www.portalfiscal.inf.br/nfe}TDec1302RTC"/&gt;
 *         &lt;element name="pAliqIBSMun" type="{http://www.portalfiscal.inf.br/nfe}TDec_0302_04RTC"/&gt;
 *         &lt;element name="vTribIBSMun" type="{http://www.portalfiscal.inf.br/nfe}TDec1302RTC"/&gt;
 *         &lt;element name="pAliqCBS" type="{http://www.portalfiscal.inf.br/nfe}TDec_0302_04RTC"/&gt;
 *         &lt;element name="vTribCBS" type="{http://www.portalfiscal.inf.br/nfe}TDec1302RTC"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * }&lt;/pre&gt;
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TTribCompraGov", propOrder = {
    "pAliqIBSUF",
    "vTribIBSUF",
    "pAliqIBSMun",
    "vTribIBSMun",
    "pAliqCBS",
    "vTribCBS"
})
public class TTribCompraGov {

    @XmlElement(required = true)
    protected String pAliqIBSUF;
    /**
     * Valor que seria devido a UF, sem aplicação do Art. 473. da LC 214/2025
     * 
     */
    @XmlElement(required = true)
    protected String vTribIBSUF;
    @XmlElement(required = true)
    protected String pAliqIBSMun;
    /**
     * Valor que seria devido ao município, sem aplicação do Art. 473. da LC 214/2025
     * 
     */
    @XmlElement(required = true)
    protected String vTribIBSMun;
    @XmlElement(required = true)
    protected String pAliqCBS;
    /**
     * Valor que seria devido a CBS, sem aplicação do Art. 473. da LC 214/2025
     * 
     */
    @XmlElement(required = true)
    protected String vTribCBS;

    /**
     * Obtém o valor da propriedade pAliqIBSUF.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPAliqIBSUF() {
        return pAliqIBSUF;
    }

    /**
     * Define o valor da propriedade pAliqIBSUF.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPAliqIBSUF(String value) {
        this.pAliqIBSUF = value;
    }

    /**
     * Valor que seria devido a UF, sem aplicação do Art. 473. da LC 214/2025
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVTribIBSUF() {
        return vTribIBSUF;
    }

    /**
     * Define o valor da propriedade vTribIBSUF.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     * @see #getVTribIBSUF()
     */
    public void setVTribIBSUF(String value) {
        this.vTribIBSUF = value;
    }

    /**
     * Obtém o valor da propriedade pAliqIBSMun.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPAliqIBSMun() {
        return pAliqIBSMun;
    }

    /**
     * Define o valor da propriedade pAliqIBSMun.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPAliqIBSMun(String value) {
        this.pAliqIBSMun = value;
    }

    /**
     * Valor que seria devido ao município, sem aplicação do Art. 473. da LC 214/2025
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVTribIBSMun() {
        return vTribIBSMun;
    }

    /**
     * Define o valor da propriedade vTribIBSMun.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     * @see #getVTribIBSMun()
     */
    public void setVTribIBSMun(String value) {
        this.vTribIBSMun = value;
    }

    /**
     * Obtém o valor da propriedade pAliqCBS.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPAliqCBS() {
        return pAliqCBS;
    }

    /**
     * Define o valor da propriedade pAliqCBS.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPAliqCBS(String value) {
        this.pAliqCBS = value;
    }

    /**
     * Valor que seria devido a CBS, sem aplicação do Art. 473. da LC 214/2025
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVTribCBS() {
        return vTribCBS;
    }

    /**
     * Define o valor da propriedade vTribCBS.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     * @see #getVTribCBS()
     */
    public void setVTribCBS(String value) {
        this.vTribCBS = value;
    }

}
