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
 * Grupo de informações de totais do Imposto Seletivo
 * 
 * &lt;p&gt;Classe Java de TISTot complex type.&lt;/p&gt;
 * 
 * &lt;p&gt;O seguinte fragmento do esquema especifica o conteúdo esperado contido dentro desta classe.&lt;/p&gt;
 * 
 * &lt;pre&gt;{&#064;code
 * &lt;complexType name="TISTot"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="vIS" type="{http://www.portalfiscal.inf.br/nfe}TDec1302RTC"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * }&lt;/pre&gt;
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TISTot", propOrder = {
    "vis"
})
public class TISTot {

    /**
     * Valor Total do Imposto Seletivo
     * 
     */
    @XmlElement(name = "vIS", required = true)
    protected String vis;

    /**
     * Valor Total do Imposto Seletivo
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
