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
 * Tipo Tributação Regular
 * 
 * &lt;p&gt;Classe Java de TTribRegular complex type.&lt;/p&gt;
 * 
 * &lt;p&gt;O seguinte fragmento do esquema especifica o conteúdo esperado contido dentro desta classe.&lt;/p&gt;
 * 
 * &lt;pre&gt;{&#064;code
 * &lt;complexType name="TTribRegular"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="CSTReg" type="{http://www.portalfiscal.inf.br/nfe}TCST"/&gt;
 *         &lt;element name="cClassTribReg" type="{http://www.portalfiscal.inf.br/nfe}TcClassTrib"/&gt;
 *         &lt;element name="pAliqEfetRegIBSUF" type="{http://www.portalfiscal.inf.br/nfe}TDec_0302_04RTC"/&gt;
 *         &lt;element name="vTribRegIBSUF" type="{http://www.portalfiscal.inf.br/nfe}TDec1302RTC"/&gt;
 *         &lt;element name="pAliqEfetRegIBSMun" type="{http://www.portalfiscal.inf.br/nfe}TDec_0302_04RTC"/&gt;
 *         &lt;element name="vTribRegIBSMun" type="{http://www.portalfiscal.inf.br/nfe}TDec1302RTC"/&gt;
 *         &lt;element name="pAliqEfetRegCBS" type="{http://www.portalfiscal.inf.br/nfe}TDec_0302_04RTC"/&gt;
 *         &lt;element name="vTribRegCBS" type="{http://www.portalfiscal.inf.br/nfe}TDec1302RTC"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * }&lt;/pre&gt;
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TTribRegular", propOrder = {
    "cstReg",
    "cClassTribReg",
    "pAliqEfetRegIBSUF",
    "vTribRegIBSUF",
    "pAliqEfetRegIBSMun",
    "vTribRegIBSMun",
    "pAliqEfetRegCBS",
    "vTribRegCBS"
})
public class TTribRegular {

    /**
     * Informar qual seria o CST caso não cumprida a condição resolutória/suspensiva
     * 
     */
    @XmlElement(name = "CSTReg", required = true)
    protected String cstReg;
    /**
     * Informar qual seria o cClassTrib caso não cumprida a condição resolutória/suspensiva
     * 
     */
    @XmlElement(required = true)
    protected String cClassTribReg;
    /**
     * Informar como seria a Alíquota caso não cumprida a condição resolutória/suspensiva
     * 
     */
    @XmlElement(required = true)
    protected String pAliqEfetRegIBSUF;
    /**
     * Informar como seria o valor do Tributo caso não cumprida a condição resolutória/suspensiva
     * 
     */
    @XmlElement(required = true)
    protected String vTribRegIBSUF;
    /**
     * Informar como seria a Alíquota caso não cumprida a condição resolutória/suspensiva
     * 
     */
    @XmlElement(required = true)
    protected String pAliqEfetRegIBSMun;
    /**
     * Informar como seria o valor do Tributo caso não cumprida a condição resolutória/suspensiva
     * 
     */
    @XmlElement(required = true)
    protected String vTribRegIBSMun;
    /**
     * Informar como seria a Alíquota caso não cumprida a condição resolutória/suspensiva
     * 
     */
    @XmlElement(required = true)
    protected String pAliqEfetRegCBS;
    /**
     * Informar como seria o valor do Tributo caso não cumprida a condição resolutória/suspensiva
     * 
     */
    @XmlElement(required = true)
    protected String vTribRegCBS;

    /**
     * Informar qual seria o CST caso não cumprida a condição resolutória/suspensiva
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCSTReg() {
        return cstReg;
    }

    /**
     * Define o valor da propriedade cstReg.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     * @see #getCSTReg()
     */
    public void setCSTReg(String value) {
        this.cstReg = value;
    }

    /**
     * Informar qual seria o cClassTrib caso não cumprida a condição resolutória/suspensiva
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCClassTribReg() {
        return cClassTribReg;
    }

    /**
     * Define o valor da propriedade cClassTribReg.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     * @see #getCClassTribReg()
     */
    public void setCClassTribReg(String value) {
        this.cClassTribReg = value;
    }

    /**
     * Informar como seria a Alíquota caso não cumprida a condição resolutória/suspensiva
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPAliqEfetRegIBSUF() {
        return pAliqEfetRegIBSUF;
    }

    /**
     * Define o valor da propriedade pAliqEfetRegIBSUF.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     * @see #getPAliqEfetRegIBSUF()
     */
    public void setPAliqEfetRegIBSUF(String value) {
        this.pAliqEfetRegIBSUF = value;
    }

    /**
     * Informar como seria o valor do Tributo caso não cumprida a condição resolutória/suspensiva
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVTribRegIBSUF() {
        return vTribRegIBSUF;
    }

    /**
     * Define o valor da propriedade vTribRegIBSUF.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     * @see #getVTribRegIBSUF()
     */
    public void setVTribRegIBSUF(String value) {
        this.vTribRegIBSUF = value;
    }

    /**
     * Informar como seria a Alíquota caso não cumprida a condição resolutória/suspensiva
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPAliqEfetRegIBSMun() {
        return pAliqEfetRegIBSMun;
    }

    /**
     * Define o valor da propriedade pAliqEfetRegIBSMun.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     * @see #getPAliqEfetRegIBSMun()
     */
    public void setPAliqEfetRegIBSMun(String value) {
        this.pAliqEfetRegIBSMun = value;
    }

    /**
     * Informar como seria o valor do Tributo caso não cumprida a condição resolutória/suspensiva
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVTribRegIBSMun() {
        return vTribRegIBSMun;
    }

    /**
     * Define o valor da propriedade vTribRegIBSMun.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     * @see #getVTribRegIBSMun()
     */
    public void setVTribRegIBSMun(String value) {
        this.vTribRegIBSMun = value;
    }

    /**
     * Informar como seria a Alíquota caso não cumprida a condição resolutória/suspensiva
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPAliqEfetRegCBS() {
        return pAliqEfetRegCBS;
    }

    /**
     * Define o valor da propriedade pAliqEfetRegCBS.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     * @see #getPAliqEfetRegCBS()
     */
    public void setPAliqEfetRegCBS(String value) {
        this.pAliqEfetRegCBS = value;
    }

    /**
     * Informar como seria o valor do Tributo caso não cumprida a condição resolutória/suspensiva
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVTribRegCBS() {
        return vTribRegCBS;
    }

    /**
     * Define o valor da propriedade vTribRegCBS.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     * @see #getVTribRegCBS()
     */
    public void setVTribRegCBS(String value) {
        this.vTribRegCBS = value;
    }

}
