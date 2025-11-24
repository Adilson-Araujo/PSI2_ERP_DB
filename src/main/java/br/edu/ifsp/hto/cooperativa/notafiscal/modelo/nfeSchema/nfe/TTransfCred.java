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
 * Tipo Transferência de Crédito
 * 
 * &lt;p&gt;Classe Java de TTransfCred complex type.&lt;/p&gt;
 * 
 * &lt;p&gt;O seguinte fragmento do esquema especifica o conteúdo esperado contido dentro desta classe.&lt;/p&gt;
 * 
 * &lt;pre&gt;{&#064;code
 * &lt;complexType name="TTransfCred"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="vIBS" type="{http://www.portalfiscal.inf.br/nfe}TDec1302RTC"/&gt;
 *         &lt;element name="vCBS" type="{http://www.portalfiscal.inf.br/nfe}TDec1302RTC"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * }&lt;/pre&gt;
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TTransfCred", propOrder = {
    "vibs",
    "vcbs"
})
public class TTransfCred {

    /**
     * Valor do IBS a ser transferido
     * 
     */
    @XmlElement(name = "vIBS", required = true)
    protected String vibs;
    /**
     * Valor da CBS a ser transferida
     * 
     */
    @XmlElement(name = "vCBS", required = true)
    protected String vcbs;

    /**
     * Valor do IBS a ser transferido
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVIBS() {
        return vibs;
    }

    /**
     * Define o valor da propriedade vibs.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     * @see #getVIBS()
     */
    public void setVIBS(String value) {
        this.vibs = value;
    }

    /**
     * Valor da CBS a ser transferida
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVCBS() {
        return vcbs;
    }

    /**
     * Define o valor da propriedade vcbs.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     * @see #getVCBS()
     */
    public void setVCBS(String value) {
        this.vcbs = value;
    }

}
