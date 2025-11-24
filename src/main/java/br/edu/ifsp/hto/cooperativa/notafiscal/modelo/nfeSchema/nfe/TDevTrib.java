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
 * Tipo Devolução Tributo
 * 
 * &lt;p&gt;Classe Java de TDevTrib complex type.&lt;/p&gt;
 * 
 * &lt;p&gt;O seguinte fragmento do esquema especifica o conteúdo esperado contido dentro desta classe.&lt;/p&gt;
 * 
 * &lt;pre&gt;{&#064;code
 * &lt;complexType name="TDevTrib"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="vDevTrib" type="{http://www.portalfiscal.inf.br/nfe}TDec1302RTC"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * }&lt;/pre&gt;
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TDevTrib", propOrder = {
    "vDevTrib"
})
public class TDevTrib {

    /**
     * Valor do tributo devolvido. No fornecimento de energia elétrica, água, esgoto e
     * gás natural e em outras hipóteses definidas no regulamento
     * 
     */
    @XmlElement(required = true)
    protected String vDevTrib;

    /**
     * Valor do tributo devolvido. No fornecimento de energia elétrica, água, esgoto e
     * gás natural e em outras hipóteses definidas no regulamento
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVDevTrib() {
        return vDevTrib;
    }

    /**
     * Define o valor da propriedade vDevTrib.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     * @see #getVDevTrib()
     */
    public void setVDevTrib(String value) {
        this.vDevTrib = value;
    }

}
