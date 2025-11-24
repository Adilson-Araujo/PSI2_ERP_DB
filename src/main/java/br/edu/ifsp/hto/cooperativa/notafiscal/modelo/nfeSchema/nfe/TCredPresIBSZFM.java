//
// Este arquivo foi gerado pela Eclipse Implementation of JAXB, v4.0.6 
// Consulte https://eclipse-ee4j.github.io/jaxb-ri 
// Todas as modificações neste arquivo serão perdidas após a recompilação do esquema de origem. 
//


package br.edu.ifsp.hto.cooperativa.notafiscal.modelo.nfeSchema.nfe;

import javax.xml.datatype.XMLGregorianCalendar;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlType;


/**
 * Tipo Informações do crédito presumido de IBS para fornecimentos a partir da ZFM
 * 
 * &lt;p&gt;Classe Java de TCredPresIBSZFM complex type.&lt;/p&gt;
 * 
 * &lt;p&gt;O seguinte fragmento do esquema especifica o conteúdo esperado contido dentro desta classe.&lt;/p&gt;
 * 
 * &lt;pre&gt;{&#064;code
 * &lt;complexType name="TCredPresIBSZFM"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="competApur" type="{http://www.portalfiscal.inf.br/nfe}TCompetApur"/&gt;
 *         &lt;element name="tpCredPresIBSZFM" type="{http://www.portalfiscal.inf.br/nfe}TTpCredPresIBSZFM"/&gt;
 *         &lt;element name="vCredPresIBSZFM" type="{http://www.portalfiscal.inf.br/nfe}TDec1302RTC"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * }&lt;/pre&gt;
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TCredPresIBSZFM", propOrder = {
    "competApur",
    "tpCredPresIBSZFM",
    "vCredPresIBSZFM"
})
public class TCredPresIBSZFM {

    /**
     * Ano e mês referência do período de apuração (AAAA-MM)
     * 
     */
    @XmlElement(required = true)
    @XmlSchemaType(name = "gYearMonth")
    protected XMLGregorianCalendar competApur;
    /**
     *  0 - Sem crédito presumido;
     *  1 - Bens de consumo final (55%);
     *  2 - Bens de capital (75%);
     *  3 - Bens intermediários (90,25%);
     *  4 - Bens de informática e outros definidos em legislação (100%).
     * OBS: Percentuais definidos no art. 450, § 1º, da LC 214/25 para o cálculo do crédito presumido
     * 
     */
    @XmlElement(required = true)
    protected String tpCredPresIBSZFM;
    /**
     * Valor do crédito presumido calculado sobre o saldo devedor apurado
     * 
     */
    @XmlElement(required = true)
    protected String vCredPresIBSZFM;

    /**
     * Ano e mês referência do período de apuração (AAAA-MM)
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getCompetApur() {
        return competApur;
    }

    /**
     * Define o valor da propriedade competApur.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     * @see #getCompetApur()
     */
    public void setCompetApur(XMLGregorianCalendar value) {
        this.competApur = value;
    }

    /**
     *  0 - Sem crédito presumido;
     *  1 - Bens de consumo final (55%);
     *  2 - Bens de capital (75%);
     *  3 - Bens intermediários (90,25%);
     *  4 - Bens de informática e outros definidos em legislação (100%).
     * OBS: Percentuais definidos no art. 450, § 1º, da LC 214/25 para o cálculo do crédito presumido
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTpCredPresIBSZFM() {
        return tpCredPresIBSZFM;
    }

    /**
     * Define o valor da propriedade tpCredPresIBSZFM.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     * @see #getTpCredPresIBSZFM()
     */
    public void setTpCredPresIBSZFM(String value) {
        this.tpCredPresIBSZFM = value;
    }

    /**
     * Valor do crédito presumido calculado sobre o saldo devedor apurado
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVCredPresIBSZFM() {
        return vCredPresIBSZFM;
    }

    /**
     * Define o valor da propriedade vCredPresIBSZFM.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     * @see #getVCredPresIBSZFM()
     */
    public void setVCredPresIBSZFM(String value) {
        this.vCredPresIBSZFM = value;
    }

}
