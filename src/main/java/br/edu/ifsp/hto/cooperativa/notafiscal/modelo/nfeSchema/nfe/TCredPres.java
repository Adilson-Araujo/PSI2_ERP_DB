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
 * Tipo Crédito Presumido
 * 
 * &lt;p&gt;Classe Java de TCredPres complex type.&lt;/p&gt;
 * 
 * &lt;p&gt;O seguinte fragmento do esquema especifica o conteúdo esperado contido dentro desta classe.&lt;/p&gt;
 * 
 * &lt;pre&gt;{&#064;code
 * &lt;complexType name="TCredPres"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="pCredPres" type="{http://www.portalfiscal.inf.br/nfe}TDec_0302_04RTC"/&gt;
 *         &lt;choice&gt;
 *           &lt;element name="vCredPres" type="{http://www.portalfiscal.inf.br/nfe}TDec1302RTC"/&gt;
 *           &lt;element name="vCredPresCondSus" type="{http://www.portalfiscal.inf.br/nfe}TDec1302RTC"/&gt;
 *         &lt;/choice&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * }&lt;/pre&gt;
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TCredPres", propOrder = {
    "pCredPres",
    "vCredPres",
    "vCredPresCondSus"
})
public class TCredPres {

    /**
     * Percentual do Crédito Presumido
     * 
     */
    @XmlElement(required = true)
    protected String pCredPres;
    /**
     * Valor do Crédito Presumido
     * 
     */
    protected String vCredPres;
    /**
     * Valor do Crédito Presumido Condição Suspensiva, preencher apenas para cCredPres que possui indicação de Condição Suspensiva
     * 
     */
    protected String vCredPresCondSus;

    /**
     * Percentual do Crédito Presumido
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPCredPres() {
        return pCredPres;
    }

    /**
     * Define o valor da propriedade pCredPres.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     * @see #getPCredPres()
     */
    public void setPCredPres(String value) {
        this.pCredPres = value;
    }

    /**
     * Valor do Crédito Presumido
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVCredPres() {
        return vCredPres;
    }

    /**
     * Define o valor da propriedade vCredPres.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     * @see #getVCredPres()
     */
    public void setVCredPres(String value) {
        this.vCredPres = value;
    }

    /**
     * Valor do Crédito Presumido Condição Suspensiva, preencher apenas para cCredPres que possui indicação de Condição Suspensiva
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVCredPresCondSus() {
        return vCredPresCondSus;
    }

    /**
     * Define o valor da propriedade vCredPresCondSus.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     * @see #getVCredPresCondSus()
     */
    public void setVCredPresCondSus(String value) {
        this.vCredPresCondSus = value;
    }

}
