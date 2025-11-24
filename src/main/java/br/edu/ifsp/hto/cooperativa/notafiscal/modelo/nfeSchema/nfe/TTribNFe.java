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
 * Grupo de informações da Tributação da NFe
 * 
 * &lt;p&gt;Classe Java de TTribNFe complex type.&lt;/p&gt;
 * 
 * &lt;p&gt;O seguinte fragmento do esquema especifica o conteúdo esperado contido dentro desta classe.&lt;/p&gt;
 * 
 * &lt;pre&gt;{&#064;code
 * &lt;complexType name="TTribNFe"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="CST" type="{http://www.portalfiscal.inf.br/nfe}TCST"/&gt;
 *         &lt;element name="cClassTrib" type="{http://www.portalfiscal.inf.br/nfe}TcClassTrib"/&gt;
 *         &lt;element name="indDoacao" type="{http://www.portalfiscal.inf.br/nfe}TIndDoacao" minOccurs="0"/&gt;
 *         &lt;choice minOccurs="0"&gt;
 *           &lt;element name="gIBSCBS" type="{http://www.portalfiscal.inf.br/nfe}TCIBS"/&gt;
 *           &lt;element name="gIBSCBSMono" type="{http://www.portalfiscal.inf.br/nfe}TMonofasia"/&gt;
 *           &lt;element name="gTransfCred" type="{http://www.portalfiscal.inf.br/nfe}TTransfCred"/&gt;
 *           &lt;element name="gAjusteCompet" type="{http://www.portalfiscal.inf.br/nfe}TAjusteCompet"/&gt;
 *         &lt;/choice&gt;
 *         &lt;element name="gEstornoCred" type="{http://www.portalfiscal.inf.br/nfe}TEstornoCred" minOccurs="0"/&gt;
 *         &lt;choice minOccurs="0"&gt;
 *           &lt;element name="gCredPresOper" type="{http://www.portalfiscal.inf.br/nfe}TCredPresOper"/&gt;
 *           &lt;element name="gCredPresIBSZFM" type="{http://www.portalfiscal.inf.br/nfe}TCredPresIBSZFM"/&gt;
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
@XmlType(name = "TTribNFe", propOrder = {
    "cst",
    "cClassTrib",
    "indDoacao",
    "gibscbs",
    "gibscbsMono",
    "gTransfCred",
    "gAjusteCompet",
    "gEstornoCred",
    "gCredPresOper",
    "gCredPresIBSZFM"
})
public class TTribNFe {

    /**
     * Código Situação Tributária do IBS/CBS
     * 
     */
    @XmlElement(name = "CST", required = true)
    protected String cst;
    @XmlElement(required = true)
    protected String cClassTrib;
    /**
     * Indica se a operação é de doação
     * 
     */
    protected String indDoacao;
    @XmlElement(name = "gIBSCBS")
    protected TCIBS gibscbs;
    /**
     * Informar essa opção da Choice para Monofasia (CST 620)
     * 
     */
    @XmlElement(name = "gIBSCBSMono")
    protected TMonofasia gibscbsMono;
    /**
     * Informar essa opção da Choice para o CST 800
     * 
     */
    protected TTransfCred gTransfCred;
    /**
     * Informar essa opção da Choice para o CST 811
     * 
     */
    protected TAjusteCompet gAjusteCompet;
    /**
     * Informado conforme indicador no cClassTrib
     * 
     */
    protected TEstornoCred gEstornoCred;
    /**
     * Crédito Presumido da Operação. Informado conforme indicador no cClassTrib.
     * 
     */
    protected TCredPresOper gCredPresOper;
    /**
     * Classificação de acordo com o art. 450, § 1º, da LC 214/25 para o cálculo do crédito presumido na ZFM. Informado conforme indicador no cClassTrib.
     * 
     */
    protected TCredPresIBSZFM gCredPresIBSZFM;

    /**
     * Código Situação Tributária do IBS/CBS
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCST() {
        return cst;
    }

    /**
     * Define o valor da propriedade cst.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     * @see #getCST()
     */
    public void setCST(String value) {
        this.cst = value;
    }

    /**
     * Obtém o valor da propriedade cClassTrib.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCClassTrib() {
        return cClassTrib;
    }

    /**
     * Define o valor da propriedade cClassTrib.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCClassTrib(String value) {
        this.cClassTrib = value;
    }

    /**
     * Indica se a operação é de doação
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIndDoacao() {
        return indDoacao;
    }

    /**
     * Define o valor da propriedade indDoacao.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     * @see #getIndDoacao()
     */
    public void setIndDoacao(String value) {
        this.indDoacao = value;
    }

    /**
     * Obtém o valor da propriedade gibscbs.
     * 
     * @return
     *     possible object is
     *     {@link TCIBS }
     *     
     */
    public TCIBS getGIBSCBS() {
        return gibscbs;
    }

    /**
     * Define o valor da propriedade gibscbs.
     * 
     * @param value
     *     allowed object is
     *     {@link TCIBS }
     *     
     */
    public void setGIBSCBS(TCIBS value) {
        this.gibscbs = value;
    }

    /**
     * Informar essa opção da Choice para Monofasia (CST 620)
     * 
     * @return
     *     possible object is
     *     {@link TMonofasia }
     *     
     */
    public TMonofasia getGIBSCBSMono() {
        return gibscbsMono;
    }

    /**
     * Define o valor da propriedade gibscbsMono.
     * 
     * @param value
     *     allowed object is
     *     {@link TMonofasia }
     *     
     * @see #getGIBSCBSMono()
     */
    public void setGIBSCBSMono(TMonofasia value) {
        this.gibscbsMono = value;
    }

    /**
     * Informar essa opção da Choice para o CST 800
     * 
     * @return
     *     possible object is
     *     {@link TTransfCred }
     *     
     */
    public TTransfCred getGTransfCred() {
        return gTransfCred;
    }

    /**
     * Define o valor da propriedade gTransfCred.
     * 
     * @param value
     *     allowed object is
     *     {@link TTransfCred }
     *     
     * @see #getGTransfCred()
     */
    public void setGTransfCred(TTransfCred value) {
        this.gTransfCred = value;
    }

    /**
     * Informar essa opção da Choice para o CST 811
     * 
     * @return
     *     possible object is
     *     {@link TAjusteCompet }
     *     
     */
    public TAjusteCompet getGAjusteCompet() {
        return gAjusteCompet;
    }

    /**
     * Define o valor da propriedade gAjusteCompet.
     * 
     * @param value
     *     allowed object is
     *     {@link TAjusteCompet }
     *     
     * @see #getGAjusteCompet()
     */
    public void setGAjusteCompet(TAjusteCompet value) {
        this.gAjusteCompet = value;
    }

    /**
     * Informado conforme indicador no cClassTrib
     * 
     * @return
     *     possible object is
     *     {@link TEstornoCred }
     *     
     */
    public TEstornoCred getGEstornoCred() {
        return gEstornoCred;
    }

    /**
     * Define o valor da propriedade gEstornoCred.
     * 
     * @param value
     *     allowed object is
     *     {@link TEstornoCred }
     *     
     * @see #getGEstornoCred()
     */
    public void setGEstornoCred(TEstornoCred value) {
        this.gEstornoCred = value;
    }

    /**
     * Crédito Presumido da Operação. Informado conforme indicador no cClassTrib.
     * 
     * @return
     *     possible object is
     *     {@link TCredPresOper }
     *     
     */
    public TCredPresOper getGCredPresOper() {
        return gCredPresOper;
    }

    /**
     * Define o valor da propriedade gCredPresOper.
     * 
     * @param value
     *     allowed object is
     *     {@link TCredPresOper }
     *     
     * @see #getGCredPresOper()
     */
    public void setGCredPresOper(TCredPresOper value) {
        this.gCredPresOper = value;
    }

    /**
     * Classificação de acordo com o art. 450, § 1º, da LC 214/25 para o cálculo do crédito presumido na ZFM. Informado conforme indicador no cClassTrib.
     * 
     * @return
     *     possible object is
     *     {@link TCredPresIBSZFM }
     *     
     */
    public TCredPresIBSZFM getGCredPresIBSZFM() {
        return gCredPresIBSZFM;
    }

    /**
     * Define o valor da propriedade gCredPresIBSZFM.
     * 
     * @param value
     *     allowed object is
     *     {@link TCredPresIBSZFM }
     *     
     * @see #getGCredPresIBSZFM()
     */
    public void setGCredPresIBSZFM(TCredPresIBSZFM value) {
        this.gCredPresIBSZFM = value;
    }

}
