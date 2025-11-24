//
// Este arquivo foi gerado pela Eclipse Implementation of JAXB, v4.0.6 
// Consulte https://eclipse-ee4j.github.io/jaxb-ri 
// Todas as modificações neste arquivo serão perdidas após a recompilação do esquema de origem. 
//


package br.edu.ifsp.hto.cooperativa.notafiscal.modelo.nfeSchema.nfe;

import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * Tipo Pedido de Concessão de Autorização da Nota Fiscal Eletrônica
 * 
 * &lt;p&gt;Classe Java de TEnviNFe complex type.&lt;/p&gt;
 * 
 * &lt;p&gt;O seguinte fragmento do esquema especifica o conteúdo esperado contido dentro desta classe.&lt;/p&gt;
 * 
 * &lt;pre&gt;{&#064;code
 * &lt;complexType name="TEnviNFe"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="idLote" type="{http://www.portalfiscal.inf.br/nfe}TIdLote"/&gt;
 *         &lt;element name="indSinc"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;whiteSpace value="preserve"/&gt;
 *               &lt;enumeration value="0"/&gt;
 *               &lt;enumeration value="1"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="NFe" type="{http://www.portalfiscal.inf.br/nfe}TNFe" maxOccurs="50"/&gt;
 *       &lt;/sequence&gt;
 *       &lt;attribute name="versao" use="required" type="{http://www.portalfiscal.inf.br/nfe}TVerNFe" /&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * }&lt;/pre&gt;
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TEnviNFe", propOrder = {
    "idLote",
    "indSinc",
    "nFe"
})
public class TEnviNFe {

    @XmlElement(required = true)
    protected String idLote;
    /**
     * Indicador de processamento síncrono. 0=NÃO; 1=SIM=Síncrono
     * 
     */
    @XmlElement(required = true)
    protected String indSinc;
    @XmlElement(name = "NFe", required = true)
    protected List<TNFe> nFe;
    @XmlAttribute(name = "versao", required = true)
    protected String versao;

    /**
     * Obtém o valor da propriedade idLote.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdLote() {
        return idLote;
    }

    /**
     * Define o valor da propriedade idLote.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdLote(String value) {
        this.idLote = value;
    }

    /**
     * Indicador de processamento síncrono. 0=NÃO; 1=SIM=Síncrono
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIndSinc() {
        return indSinc;
    }

    /**
     * Define o valor da propriedade indSinc.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     * @see #getIndSinc()
     */
    public void setIndSinc(String value) {
        this.indSinc = value;
    }

    /**
     * Gets the value of the nFe property.
     * 
     * <p>This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a {@code set} method for the nFe property.</p>
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * </p>
     * <pre>
     * getNFe().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TNFe }
     * </p>
     * 
     * 
     * @return
     *     The value of the nFe property.
     */
    public List<TNFe> getNFe() {
        if (nFe == null) {
            nFe = new ArrayList<>();
        }
        return this.nFe;
    }

    /**
     * Obtém o valor da propriedade versao.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVersao() {
        return versao;
    }

    /**
     * Define o valor da propriedade versao.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVersao(String value) {
        this.versao = value;
    }

}
