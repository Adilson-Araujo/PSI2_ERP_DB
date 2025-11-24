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
 * Cada DFe que utilizar deverá utilizar esses tipo no grupo ide
 * 
 * &lt;p&gt;Classe Java de TCompraGovReduzido complex type.&lt;/p&gt;
 * 
 * &lt;p&gt;O seguinte fragmento do esquema especifica o conteúdo esperado contido dentro desta classe.&lt;/p&gt;
 * 
 * &lt;pre&gt;{&#064;code
 * &lt;complexType name="TCompraGovReduzido"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="tpEnteGov" type="{http://www.portalfiscal.inf.br/nfe}TEnteGov"/&gt;
 *         &lt;element name="pRedutor" type="{http://www.portalfiscal.inf.br/nfe}TDec_0302_04RTC"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * }&lt;/pre&gt;
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TCompraGovReduzido", propOrder = {
    "tpEnteGov",
    "pRedutor"
})
public class TCompraGovReduzido {

    /**
     * Para administração pública direta e suas autarquias e fundações:
     *  1=União
     *  2=Estados
     *  3=Distrito Federal
     *  4=Municípios
     * 
     */
    @XmlElement(required = true)
    protected String tpEnteGov;
    /**
     * Percentual de redução de aliquota em compra governamental
     * 
     */
    @XmlElement(required = true)
    protected String pRedutor;

    /**
     * Para administração pública direta e suas autarquias e fundações:
     *  1=União
     *  2=Estados
     *  3=Distrito Federal
     *  4=Municípios
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTpEnteGov() {
        return tpEnteGov;
    }

    /**
     * Define o valor da propriedade tpEnteGov.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     * @see #getTpEnteGov()
     */
    public void setTpEnteGov(String value) {
        this.tpEnteGov = value;
    }

    /**
     * Percentual de redução de aliquota em compra governamental
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPRedutor() {
        return pRedutor;
    }

    /**
     * Define o valor da propriedade pRedutor.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     * @see #getPRedutor()
     */
    public void setPRedutor(String value) {
        this.pRedutor = value;
    }

}
