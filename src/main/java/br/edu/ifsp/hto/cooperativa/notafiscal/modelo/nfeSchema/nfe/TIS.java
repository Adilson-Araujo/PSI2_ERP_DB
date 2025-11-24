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
 * Grupo de informações do Imposto Seletivo
 * 
 * &lt;p&gt;Classe Java de TIS complex type.&lt;/p&gt;
 * 
 * &lt;p&gt;O seguinte fragmento do esquema especifica o conteúdo esperado contido dentro desta classe.&lt;/p&gt;
 * 
 * &lt;pre&gt;{&#064;code
 * &lt;complexType name="TIS"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="CSTIS" type="{http://www.portalfiscal.inf.br/nfe}TCST"/&gt;
 *         &lt;element name="cClassTribIS" type="{http://www.portalfiscal.inf.br/nfe}TcClassTrib"/&gt;
 *         &lt;sequence minOccurs="0"&gt;
 *           &lt;element name="vBCIS" type="{http://www.portalfiscal.inf.br/nfe}TDec1302RTC"/&gt;
 *           &lt;element name="pIS" type="{http://www.portalfiscal.inf.br/nfe}TDec_0302_04RTC"/&gt;
 *           &lt;element name="pISEspec" type="{http://www.portalfiscal.inf.br/nfe}TDec_0302_04RTC" minOccurs="0"/&gt;
 *           &lt;sequence minOccurs="0"&gt;
 *             &lt;element name="uTrib"&gt;
 *               &lt;simpleType&gt;
 *                 &lt;restriction base="{http://www.portalfiscal.inf.br/nfe}TStringRTC"&gt;
 *                   &lt;minLength value="1"/&gt;
 *                   &lt;maxLength value="6"/&gt;
 *                 &lt;/restriction&gt;
 *               &lt;/simpleType&gt;
 *             &lt;/element&gt;
 *             &lt;element name="qTrib" type="{http://www.portalfiscal.inf.br/nfe}TDec_1104OpRTC"/&gt;
 *           &lt;/sequence&gt;
 *           &lt;element name="vIS" type="{http://www.portalfiscal.inf.br/nfe}TDec1302RTC"/&gt;
 *         &lt;/sequence&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * }&lt;/pre&gt;
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TIS", propOrder = {
    "cstis",
    "cClassTribIS",
    "vbcis",
    "pis",
    "pisEspec",
    "uTrib",
    "qTrib",
    "vis"
})
public class TIS {

    /**
     * Código Situação Tributária do Imposto Seletivo
     * 
     */
    @XmlElement(name = "CSTIS", required = true)
    protected String cstis;
    @XmlElement(required = true)
    protected String cClassTribIS;
    /**
     * Valor do BC
     * 
     */
    @XmlElement(name = "vBCIS")
    protected String vbcis;
    /**
     * Alíquota do Imposto Seletivo (percentual)
     * 
     */
    @XmlElement(name = "pIS")
    protected String pis;
    /**
     * Alíquota do Imposto Seletivo (por valor)
     * 
     */
    @XmlElement(name = "pISEspec")
    protected String pisEspec;
    /**
     * Unidade de medida apropriada especificada em Lei Ordinaria para fins de apuração do Imposto Seletivo
     * 
     */
    protected String uTrib;
    /**
     * Quantidade com abse no campo uTrib informado
     * 
     */
    protected String qTrib;
    /**
     * Valor do Imposto Seletivo calculado
     * 
     */
    @XmlElement(name = "vIS")
    protected String vis;

    /**
     * Código Situação Tributária do Imposto Seletivo
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCSTIS() {
        return cstis;
    }

    /**
     * Define o valor da propriedade cstis.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     * @see #getCSTIS()
     */
    public void setCSTIS(String value) {
        this.cstis = value;
    }

    /**
     * Obtém o valor da propriedade cClassTribIS.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCClassTribIS() {
        return cClassTribIS;
    }

    /**
     * Define o valor da propriedade cClassTribIS.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCClassTribIS(String value) {
        this.cClassTribIS = value;
    }

    /**
     * Valor do BC
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVBCIS() {
        return vbcis;
    }

    /**
     * Define o valor da propriedade vbcis.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     * @see #getVBCIS()
     */
    public void setVBCIS(String value) {
        this.vbcis = value;
    }

    /**
     * Alíquota do Imposto Seletivo (percentual)
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPIS() {
        return pis;
    }

    /**
     * Define o valor da propriedade pis.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     * @see #getPIS()
     */
    public void setPIS(String value) {
        this.pis = value;
    }

    /**
     * Alíquota do Imposto Seletivo (por valor)
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPISEspec() {
        return pisEspec;
    }

    /**
     * Define o valor da propriedade pisEspec.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     * @see #getPISEspec()
     */
    public void setPISEspec(String value) {
        this.pisEspec = value;
    }

    /**
     * Unidade de medida apropriada especificada em Lei Ordinaria para fins de apuração do Imposto Seletivo
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUTrib() {
        return uTrib;
    }

    /**
     * Define o valor da propriedade uTrib.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     * @see #getUTrib()
     */
    public void setUTrib(String value) {
        this.uTrib = value;
    }

    /**
     * Quantidade com abse no campo uTrib informado
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getQTrib() {
        return qTrib;
    }

    /**
     * Define o valor da propriedade qTrib.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     * @see #getQTrib()
     */
    public void setQTrib(String value) {
        this.qTrib = value;
    }

    /**
     * Valor do Imposto Seletivo calculado
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVIS() {
        return vis;
    }

    /**
     * Define o valor da propriedade vis.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     * @see #getVIS()
     */
    public void setVIS(String value) {
        this.vis = value;
    }

}
