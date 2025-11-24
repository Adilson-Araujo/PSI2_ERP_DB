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
 * Tipo Redução Base de Cálculo
 * 
 * &lt;p&gt;Classe Java de TRed complex type.&lt;/p&gt;
 * 
 * &lt;p&gt;O seguinte fragmento do esquema especifica o conteúdo esperado contido dentro desta classe.&lt;/p&gt;
 * 
 * &lt;pre&gt;{&#064;code
 * &lt;complexType name="TRed"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="pRedAliq" type="{http://www.portalfiscal.inf.br/nfe}TDec_0302_04RTC"/&gt;
 *         &lt;element name="pAliqEfet" type="{http://www.portalfiscal.inf.br/nfe}TDec_0302_04RTC"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * }&lt;/pre&gt;
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TRed", propOrder = {
    "pRedAliq",
    "pAliqEfet"
})
public class TRed {

    /**
     * Percentual de redução de aliquota do cClassTrib
     * 
     */
    @XmlElement(required = true)
    protected String pRedAliq;
    /**
     * Aliquota Efetiva que será aplicada a Base de Calculo (em percentual)
     * 
     */
    @XmlElement(required = true)
    protected String pAliqEfet;

    /**
     * Percentual de redução de aliquota do cClassTrib
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPRedAliq() {
        return pRedAliq;
    }

    /**
     * Define o valor da propriedade pRedAliq.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     * @see #getPRedAliq()
     */
    public void setPRedAliq(String value) {
        this.pRedAliq = value;
    }

    /**
     * Aliquota Efetiva que será aplicada a Base de Calculo (em percentual)
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPAliqEfet() {
        return pAliqEfet;
    }

    /**
     * Define o valor da propriedade pAliqEfet.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     * @see #getPAliqEfet()
     */
    public void setPAliqEfet(String value) {
        this.pAliqEfet = value;
    }

}
