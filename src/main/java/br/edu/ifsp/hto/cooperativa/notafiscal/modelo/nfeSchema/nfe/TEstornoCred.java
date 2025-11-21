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
 * Tipo Estorno de Crédito
 * 
 * &lt;p&gt;Classe Java de TEstornoCred complex type.&lt;/p&gt;
 * 
 * &lt;p&gt;O seguinte fragmento do esquema especifica o conteúdo esperado contido dentro desta classe.&lt;/p&gt;
 * 
 * &lt;pre&gt;{&#064;code
 * &lt;complexType name="TEstornoCred"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="vIBSEstCred" type="{http://www.portalfiscal.inf.br/nfe}TDec1302RTC"/&gt;
 *         &lt;element name="vCBSEstCred" type="{http://www.portalfiscal.inf.br/nfe}TDec1302RTC"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * }&lt;/pre&gt;
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TEstornoCred", propOrder = {
    "vibsEstCred",
    "vcbsEstCred"
})
public class TEstornoCred {

    /**
     * Valor do IBS a ser estornado
     * 
     */
    @XmlElement(name = "vIBSEstCred", required = true)
    protected String vibsEstCred;
    /**
     * Valor da CBS a ser estornada
     * 
     */
    @XmlElement(name = "vCBSEstCred", required = true)
    protected String vcbsEstCred;

    /**
     * Valor do IBS a ser estornado
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVIBSEstCred() {
        return vibsEstCred;
    }

    /**
     * Define o valor da propriedade vibsEstCred.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     * @see #getVIBSEstCred()
     */
    public void setVIBSEstCred(String value) {
        this.vibsEstCred = value;
    }

    /**
     * Valor da CBS a ser estornada
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVCBSEstCred() {
        return vcbsEstCred;
    }

    /**
     * Define o valor da propriedade vcbsEstCred.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     * @see #getVCBSEstCred()
     */
    public void setVCBSEstCred(String value) {
        this.vcbsEstCred = value;
    }

}
