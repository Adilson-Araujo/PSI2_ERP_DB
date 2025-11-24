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
 * Tipo Diferimento
 * 
 * &lt;p&gt;Classe Java de TDif complex type.&lt;/p&gt;
 * 
 * &lt;p&gt;O seguinte fragmento do esquema especifica o conteúdo esperado contido dentro desta classe.&lt;/p&gt;
 * 
 * &lt;pre&gt;{&#064;code
 * &lt;complexType name="TDif"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="pDif" type="{http://www.portalfiscal.inf.br/nfe}TDec_0302_04RTC"/&gt;
 *         &lt;element name="vDif" type="{http://www.portalfiscal.inf.br/nfe}TDec1302RTC"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * }&lt;/pre&gt;
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TDif", propOrder = {
    "pDif",
    "vDif"
})
public class TDif {

    /**
     * Percentual do diferimento
     * 
     */
    @XmlElement(required = true)
    protected String pDif;
    /**
     * Valor do diferimento
     * 
     */
    @XmlElement(required = true)
    protected String vDif;

    /**
     * Percentual do diferimento
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPDif() {
        return pDif;
    }

    /**
     * Define o valor da propriedade pDif.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     * @see #getPDif()
     */
    public void setPDif(String value) {
        this.pDif = value;
    }

    /**
     * Valor do diferimento
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVDif() {
        return vDif;
    }

    /**
     * Define o valor da propriedade vDif.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     * @see #getVDif()
     */
    public void setVDif(String value) {
        this.vDif = value;
    }

}
