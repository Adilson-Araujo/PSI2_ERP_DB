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
 * Tipo Monofasia
 * 
 * &lt;p&gt;Classe Java de TMonofasia complex type.&lt;/p&gt;
 * 
 * &lt;p&gt;O seguinte fragmento do esquema especifica o conteúdo esperado contido dentro desta classe.&lt;/p&gt;
 * 
 * &lt;pre&gt;{&#064;code
 * &lt;complexType name="TMonofasia"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="gMonoPadrao" minOccurs="0"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="qBCMono" type="{http://www.portalfiscal.inf.br/nfe}TDec1104RTC"/&gt;
 *                   &lt;element name="adRemIBS" type="{http://www.portalfiscal.inf.br/nfe}TDec_0302_04RTC"/&gt;
 *                   &lt;element name="adRemCBS" type="{http://www.portalfiscal.inf.br/nfe}TDec_0302_04RTC"/&gt;
 *                   &lt;element name="vIBSMono" type="{http://www.portalfiscal.inf.br/nfe}TDec1302RTC"/&gt;
 *                   &lt;element name="vCBSMono" type="{http://www.portalfiscal.inf.br/nfe}TDec1302RTC"/&gt;
 *                 &lt;/sequence&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="gMonoReten" minOccurs="0"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="qBCMonoReten" type="{http://www.portalfiscal.inf.br/nfe}TDec1104RTC"/&gt;
 *                   &lt;element name="adRemIBSReten" type="{http://www.portalfiscal.inf.br/nfe}TDec_0302_04RTC"/&gt;
 *                   &lt;element name="vIBSMonoReten" type="{http://www.portalfiscal.inf.br/nfe}TDec1302RTC"/&gt;
 *                   &lt;element name="adRemCBSReten" type="{http://www.portalfiscal.inf.br/nfe}TDec_0302_04RTC"/&gt;
 *                   &lt;element name="vCBSMonoReten" type="{http://www.portalfiscal.inf.br/nfe}TDec1302RTC"/&gt;
 *                 &lt;/sequence&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="gMonoRet" minOccurs="0"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="qBCMonoRet" type="{http://www.portalfiscal.inf.br/nfe}TDec1104RTC"/&gt;
 *                   &lt;element name="adRemIBSRet" type="{http://www.portalfiscal.inf.br/nfe}TDec_0302_04RTC"/&gt;
 *                   &lt;element name="vIBSMonoRet" type="{http://www.portalfiscal.inf.br/nfe}TDec1302RTC"/&gt;
 *                   &lt;element name="adRemCBSRet" type="{http://www.portalfiscal.inf.br/nfe}TDec_0302_04RTC"/&gt;
 *                   &lt;element name="vCBSMonoRet" type="{http://www.portalfiscal.inf.br/nfe}TDec1302RTC"/&gt;
 *                 &lt;/sequence&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="gMonoDif" minOccurs="0"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="pDifIBS" type="{http://www.portalfiscal.inf.br/nfe}TDec_0302_04RTC"/&gt;
 *                   &lt;element name="vIBSMonoDif" type="{http://www.portalfiscal.inf.br/nfe}TDec1302RTC"/&gt;
 *                   &lt;element name="pDifCBS" type="{http://www.portalfiscal.inf.br/nfe}TDec_0302_04RTC"/&gt;
 *                   &lt;element name="vCBSMonoDif" type="{http://www.portalfiscal.inf.br/nfe}TDec1302RTC"/&gt;
 *                 &lt;/sequence&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="vTotIBSMonoItem" type="{http://www.portalfiscal.inf.br/nfe}TDec1302RTC"/&gt;
 *         &lt;element name="vTotCBSMonoItem" type="{http://www.portalfiscal.inf.br/nfe}TDec1302RTC"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * }&lt;/pre&gt;
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TMonofasia", propOrder = {
    "gMonoPadrao",
    "gMonoReten",
    "gMonoRet",
    "gMonoDif",
    "vTotIBSMonoItem",
    "vTotCBSMonoItem"
})
public class TMonofasia {

    /**
     * Grupo de informações da Tributação Monofásica padrão
     * 
     */
    protected TMonofasia.GMonoPadrao gMonoPadrao;
    /**
     * Grupo de informações da Tributação Monofásica sujeita a retenção
     * 
     */
    protected TMonofasia.GMonoReten gMonoReten;
    /**
     * Grupo de informações da Tributação Monofásica retida anteriormente
     * 
     */
    protected TMonofasia.GMonoRet gMonoRet;
    /**
     * Grupo de informações do diferimento da Tributação Monofásica
     * 
     */
    protected TMonofasia.GMonoDif gMonoDif;
    /**
     * Total de IBS monofásico do item
     * 
     */
    @XmlElement(required = true)
    protected String vTotIBSMonoItem;
    /**
     * Total da CBS monofásica do item
     * 
     */
    @XmlElement(required = true)
    protected String vTotCBSMonoItem;

    /**
     * Grupo de informações da Tributação Monofásica padrão
     * 
     * @return
     *     possible object is
     *     {@link TMonofasia.GMonoPadrao }
     *     
     */
    public TMonofasia.GMonoPadrao getGMonoPadrao() {
        return gMonoPadrao;
    }

    /**
     * Define o valor da propriedade gMonoPadrao.
     * 
     * @param value
     *     allowed object is
     *     {@link TMonofasia.GMonoPadrao }
     *     
     * @see #getGMonoPadrao()
     */
    public void setGMonoPadrao(TMonofasia.GMonoPadrao value) {
        this.gMonoPadrao = value;
    }

    /**
     * Grupo de informações da Tributação Monofásica sujeita a retenção
     * 
     * @return
     *     possible object is
     *     {@link TMonofasia.GMonoReten }
     *     
     */
    public TMonofasia.GMonoReten getGMonoReten() {
        return gMonoReten;
    }

    /**
     * Define o valor da propriedade gMonoReten.
     * 
     * @param value
     *     allowed object is
     *     {@link TMonofasia.GMonoReten }
     *     
     * @see #getGMonoReten()
     */
    public void setGMonoReten(TMonofasia.GMonoReten value) {
        this.gMonoReten = value;
    }

    /**
     * Grupo de informações da Tributação Monofásica retida anteriormente
     * 
     * @return
     *     possible object is
     *     {@link TMonofasia.GMonoRet }
     *     
     */
    public TMonofasia.GMonoRet getGMonoRet() {
        return gMonoRet;
    }

    /**
     * Define o valor da propriedade gMonoRet.
     * 
     * @param value
     *     allowed object is
     *     {@link TMonofasia.GMonoRet }
     *     
     * @see #getGMonoRet()
     */
    public void setGMonoRet(TMonofasia.GMonoRet value) {
        this.gMonoRet = value;
    }

    /**
     * Grupo de informações do diferimento da Tributação Monofásica
     * 
     * @return
     *     possible object is
     *     {@link TMonofasia.GMonoDif }
     *     
     */
    public TMonofasia.GMonoDif getGMonoDif() {
        return gMonoDif;
    }

    /**
     * Define o valor da propriedade gMonoDif.
     * 
     * @param value
     *     allowed object is
     *     {@link TMonofasia.GMonoDif }
     *     
     * @see #getGMonoDif()
     */
    public void setGMonoDif(TMonofasia.GMonoDif value) {
        this.gMonoDif = value;
    }

    /**
     * Total de IBS monofásico do item
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVTotIBSMonoItem() {
        return vTotIBSMonoItem;
    }

    /**
     * Define o valor da propriedade vTotIBSMonoItem.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     * @see #getVTotIBSMonoItem()
     */
    public void setVTotIBSMonoItem(String value) {
        this.vTotIBSMonoItem = value;
    }

    /**
     * Total da CBS monofásica do item
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVTotCBSMonoItem() {
        return vTotCBSMonoItem;
    }

    /**
     * Define o valor da propriedade vTotCBSMonoItem.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     * @see #getVTotCBSMonoItem()
     */
    public void setVTotCBSMonoItem(String value) {
        this.vTotCBSMonoItem = value;
    }


    /**
     * &lt;p&gt;Classe Java de anonymous complex type.&lt;/p&gt;
     * 
     * &lt;p&gt;O seguinte fragmento do esquema especifica o conteúdo esperado contido dentro desta classe.&lt;/p&gt;
     * 
     * &lt;pre&gt;{&#064;code
     * &lt;complexType&gt;
     *   &lt;complexContent&gt;
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *       &lt;sequence&gt;
     *         &lt;element name="pDifIBS" type="{http://www.portalfiscal.inf.br/nfe}TDec_0302_04RTC"/&gt;
     *         &lt;element name="vIBSMonoDif" type="{http://www.portalfiscal.inf.br/nfe}TDec1302RTC"/&gt;
     *         &lt;element name="pDifCBS" type="{http://www.portalfiscal.inf.br/nfe}TDec_0302_04RTC"/&gt;
     *         &lt;element name="vCBSMonoDif" type="{http://www.portalfiscal.inf.br/nfe}TDec1302RTC"/&gt;
     *       &lt;/sequence&gt;
     *     &lt;/restriction&gt;
     *   &lt;/complexContent&gt;
     * &lt;/complexType&gt;
     * }&lt;/pre&gt;
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "pDifIBS",
        "vibsMonoDif",
        "pDifCBS",
        "vcbsMonoDif"
    })
    public static class GMonoDif {

        /**
         * Percentual do diferimento do imposto monofásico
         * 
         */
        @XmlElement(required = true)
        protected String pDifIBS;
        /**
         * Valor do IBS monofásico diferido
         * 
         */
        @XmlElement(name = "vIBSMonoDif", required = true)
        protected String vibsMonoDif;
        /**
         * Percentual do diferimento do imposto monofásico
         * 
         */
        @XmlElement(required = true)
        protected String pDifCBS;
        /**
         * Valor da CBS monofásica diferida
         * 
         */
        @XmlElement(name = "vCBSMonoDif", required = true)
        protected String vcbsMonoDif;

        /**
         * Percentual do diferimento do imposto monofásico
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getPDifIBS() {
            return pDifIBS;
        }

        /**
         * Define o valor da propriedade pDifIBS.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         * @see #getPDifIBS()
         */
        public void setPDifIBS(String value) {
            this.pDifIBS = value;
        }

        /**
         * Valor do IBS monofásico diferido
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getVIBSMonoDif() {
            return vibsMonoDif;
        }

        /**
         * Define o valor da propriedade vibsMonoDif.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         * @see #getVIBSMonoDif()
         */
        public void setVIBSMonoDif(String value) {
            this.vibsMonoDif = value;
        }

        /**
         * Percentual do diferimento do imposto monofásico
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getPDifCBS() {
            return pDifCBS;
        }

        /**
         * Define o valor da propriedade pDifCBS.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         * @see #getPDifCBS()
         */
        public void setPDifCBS(String value) {
            this.pDifCBS = value;
        }

        /**
         * Valor da CBS monofásica diferida
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getVCBSMonoDif() {
            return vcbsMonoDif;
        }

        /**
         * Define o valor da propriedade vcbsMonoDif.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         * @see #getVCBSMonoDif()
         */
        public void setVCBSMonoDif(String value) {
            this.vcbsMonoDif = value;
        }

    }


    /**
     * &lt;p&gt;Classe Java de anonymous complex type.&lt;/p&gt;
     * 
     * &lt;p&gt;O seguinte fragmento do esquema especifica o conteúdo esperado contido dentro desta classe.&lt;/p&gt;
     * 
     * &lt;pre&gt;{&#064;code
     * &lt;complexType&gt;
     *   &lt;complexContent&gt;
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *       &lt;sequence&gt;
     *         &lt;element name="qBCMono" type="{http://www.portalfiscal.inf.br/nfe}TDec1104RTC"/&gt;
     *         &lt;element name="adRemIBS" type="{http://www.portalfiscal.inf.br/nfe}TDec_0302_04RTC"/&gt;
     *         &lt;element name="adRemCBS" type="{http://www.portalfiscal.inf.br/nfe}TDec_0302_04RTC"/&gt;
     *         &lt;element name="vIBSMono" type="{http://www.portalfiscal.inf.br/nfe}TDec1302RTC"/&gt;
     *         &lt;element name="vCBSMono" type="{http://www.portalfiscal.inf.br/nfe}TDec1302RTC"/&gt;
     *       &lt;/sequence&gt;
     *     &lt;/restriction&gt;
     *   &lt;/complexContent&gt;
     * &lt;/complexType&gt;
     * }&lt;/pre&gt;
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "qbcMono",
        "adRemIBS",
        "adRemCBS",
        "vibsMono",
        "vcbsMono"
    })
    public static class GMonoPadrao {

        /**
         * Quantidade tributada na monofasia
         * 
         */
        @XmlElement(name = "qBCMono", required = true)
        protected String qbcMono;
        /**
         * Alíquota ad rem do IBS
         * 
         */
        @XmlElement(required = true)
        protected String adRemIBS;
        /**
         * Alíquota ad rem da CBS
         * 
         */
        @XmlElement(required = true)
        protected String adRemCBS;
        /**
         * Valor do IBS monofásico
         * 
         */
        @XmlElement(name = "vIBSMono", required = true)
        protected String vibsMono;
        /**
         * Valor da CBS monofásica
         * 
         */
        @XmlElement(name = "vCBSMono", required = true)
        protected String vcbsMono;

        /**
         * Quantidade tributada na monofasia
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getQBCMono() {
            return qbcMono;
        }

        /**
         * Define o valor da propriedade qbcMono.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         * @see #getQBCMono()
         */
        public void setQBCMono(String value) {
            this.qbcMono = value;
        }

        /**
         * Alíquota ad rem do IBS
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getAdRemIBS() {
            return adRemIBS;
        }

        /**
         * Define o valor da propriedade adRemIBS.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         * @see #getAdRemIBS()
         */
        public void setAdRemIBS(String value) {
            this.adRemIBS = value;
        }

        /**
         * Alíquota ad rem da CBS
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getAdRemCBS() {
            return adRemCBS;
        }

        /**
         * Define o valor da propriedade adRemCBS.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         * @see #getAdRemCBS()
         */
        public void setAdRemCBS(String value) {
            this.adRemCBS = value;
        }

        /**
         * Valor do IBS monofásico
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getVIBSMono() {
            return vibsMono;
        }

        /**
         * Define o valor da propriedade vibsMono.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         * @see #getVIBSMono()
         */
        public void setVIBSMono(String value) {
            this.vibsMono = value;
        }

        /**
         * Valor da CBS monofásica
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getVCBSMono() {
            return vcbsMono;
        }

        /**
         * Define o valor da propriedade vcbsMono.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         * @see #getVCBSMono()
         */
        public void setVCBSMono(String value) {
            this.vcbsMono = value;
        }

    }


    /**
     * &lt;p&gt;Classe Java de anonymous complex type.&lt;/p&gt;
     * 
     * &lt;p&gt;O seguinte fragmento do esquema especifica o conteúdo esperado contido dentro desta classe.&lt;/p&gt;
     * 
     * &lt;pre&gt;{&#064;code
     * &lt;complexType&gt;
     *   &lt;complexContent&gt;
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *       &lt;sequence&gt;
     *         &lt;element name="qBCMonoRet" type="{http://www.portalfiscal.inf.br/nfe}TDec1104RTC"/&gt;
     *         &lt;element name="adRemIBSRet" type="{http://www.portalfiscal.inf.br/nfe}TDec_0302_04RTC"/&gt;
     *         &lt;element name="vIBSMonoRet" type="{http://www.portalfiscal.inf.br/nfe}TDec1302RTC"/&gt;
     *         &lt;element name="adRemCBSRet" type="{http://www.portalfiscal.inf.br/nfe}TDec_0302_04RTC"/&gt;
     *         &lt;element name="vCBSMonoRet" type="{http://www.portalfiscal.inf.br/nfe}TDec1302RTC"/&gt;
     *       &lt;/sequence&gt;
     *     &lt;/restriction&gt;
     *   &lt;/complexContent&gt;
     * &lt;/complexType&gt;
     * }&lt;/pre&gt;
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "qbcMonoRet",
        "adRemIBSRet",
        "vibsMonoRet",
        "adRemCBSRet",
        "vcbsMonoRet"
    })
    public static class GMonoRet {

        /**
         * Quantidade tributada retida anteriormente
         * 
         */
        @XmlElement(name = "qBCMonoRet", required = true)
        protected String qbcMonoRet;
        /**
         * Alíquota ad rem do IBS retido anteriormente
         * 
         */
        @XmlElement(required = true)
        protected String adRemIBSRet;
        /**
         * Valor do IBS retido anteriormente
         * 
         */
        @XmlElement(name = "vIBSMonoRet", required = true)
        protected String vibsMonoRet;
        /**
         * Alíquota ad rem da CBS retida anteriormente
         * 
         */
        @XmlElement(required = true)
        protected String adRemCBSRet;
        /**
         * Valor da CBS retida anteriormente
         * 
         */
        @XmlElement(name = "vCBSMonoRet", required = true)
        protected String vcbsMonoRet;

        /**
         * Quantidade tributada retida anteriormente
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getQBCMonoRet() {
            return qbcMonoRet;
        }

        /**
         * Define o valor da propriedade qbcMonoRet.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         * @see #getQBCMonoRet()
         */
        public void setQBCMonoRet(String value) {
            this.qbcMonoRet = value;
        }

        /**
         * Alíquota ad rem do IBS retido anteriormente
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getAdRemIBSRet() {
            return adRemIBSRet;
        }

        /**
         * Define o valor da propriedade adRemIBSRet.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         * @see #getAdRemIBSRet()
         */
        public void setAdRemIBSRet(String value) {
            this.adRemIBSRet = value;
        }

        /**
         * Valor do IBS retido anteriormente
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getVIBSMonoRet() {
            return vibsMonoRet;
        }

        /**
         * Define o valor da propriedade vibsMonoRet.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         * @see #getVIBSMonoRet()
         */
        public void setVIBSMonoRet(String value) {
            this.vibsMonoRet = value;
        }

        /**
         * Alíquota ad rem da CBS retida anteriormente
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getAdRemCBSRet() {
            return adRemCBSRet;
        }

        /**
         * Define o valor da propriedade adRemCBSRet.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         * @see #getAdRemCBSRet()
         */
        public void setAdRemCBSRet(String value) {
            this.adRemCBSRet = value;
        }

        /**
         * Valor da CBS retida anteriormente
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getVCBSMonoRet() {
            return vcbsMonoRet;
        }

        /**
         * Define o valor da propriedade vcbsMonoRet.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         * @see #getVCBSMonoRet()
         */
        public void setVCBSMonoRet(String value) {
            this.vcbsMonoRet = value;
        }

    }


    /**
     * &lt;p&gt;Classe Java de anonymous complex type.&lt;/p&gt;
     * 
     * &lt;p&gt;O seguinte fragmento do esquema especifica o conteúdo esperado contido dentro desta classe.&lt;/p&gt;
     * 
     * &lt;pre&gt;{&#064;code
     * &lt;complexType&gt;
     *   &lt;complexContent&gt;
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *       &lt;sequence&gt;
     *         &lt;element name="qBCMonoReten" type="{http://www.portalfiscal.inf.br/nfe}TDec1104RTC"/&gt;
     *         &lt;element name="adRemIBSReten" type="{http://www.portalfiscal.inf.br/nfe}TDec_0302_04RTC"/&gt;
     *         &lt;element name="vIBSMonoReten" type="{http://www.portalfiscal.inf.br/nfe}TDec1302RTC"/&gt;
     *         &lt;element name="adRemCBSReten" type="{http://www.portalfiscal.inf.br/nfe}TDec_0302_04RTC"/&gt;
     *         &lt;element name="vCBSMonoReten" type="{http://www.portalfiscal.inf.br/nfe}TDec1302RTC"/&gt;
     *       &lt;/sequence&gt;
     *     &lt;/restriction&gt;
     *   &lt;/complexContent&gt;
     * &lt;/complexType&gt;
     * }&lt;/pre&gt;
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "qbcMonoReten",
        "adRemIBSReten",
        "vibsMonoReten",
        "adRemCBSReten",
        "vcbsMonoReten"
    })
    public static class GMonoReten {

        /**
         * Quantidade tributada sujeita a retenção.
         * 
         */
        @XmlElement(name = "qBCMonoReten", required = true)
        protected String qbcMonoReten;
        /**
         * Alíquota ad rem do IBS sujeito a retenção
         * 
         */
        @XmlElement(required = true)
        protected String adRemIBSReten;
        /**
         * Valor do IBS monofásico sujeito a retenção
         * 
         */
        @XmlElement(name = "vIBSMonoReten", required = true)
        protected String vibsMonoReten;
        /**
         * Alíquota ad rem da CBS sujeita a retenção
         * 
         */
        @XmlElement(required = true)
        protected String adRemCBSReten;
        /**
         * Valor da CBS monofásica sujeita a retenção
         * 
         */
        @XmlElement(name = "vCBSMonoReten", required = true)
        protected String vcbsMonoReten;

        /**
         * Quantidade tributada sujeita a retenção.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getQBCMonoReten() {
            return qbcMonoReten;
        }

        /**
         * Define o valor da propriedade qbcMonoReten.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         * @see #getQBCMonoReten()
         */
        public void setQBCMonoReten(String value) {
            this.qbcMonoReten = value;
        }

        /**
         * Alíquota ad rem do IBS sujeito a retenção
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getAdRemIBSReten() {
            return adRemIBSReten;
        }

        /**
         * Define o valor da propriedade adRemIBSReten.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         * @see #getAdRemIBSReten()
         */
        public void setAdRemIBSReten(String value) {
            this.adRemIBSReten = value;
        }

        /**
         * Valor do IBS monofásico sujeito a retenção
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getVIBSMonoReten() {
            return vibsMonoReten;
        }

        /**
         * Define o valor da propriedade vibsMonoReten.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         * @see #getVIBSMonoReten()
         */
        public void setVIBSMonoReten(String value) {
            this.vibsMonoReten = value;
        }

        /**
         * Alíquota ad rem da CBS sujeita a retenção
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getAdRemCBSReten() {
            return adRemCBSReten;
        }

        /**
         * Define o valor da propriedade adRemCBSReten.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         * @see #getAdRemCBSReten()
         */
        public void setAdRemCBSReten(String value) {
            this.adRemCBSReten = value;
        }

        /**
         * Valor da CBS monofásica sujeita a retenção
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getVCBSMonoReten() {
            return vcbsMonoReten;
        }

        /**
         * Define o valor da propriedade vcbsMonoReten.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         * @see #getVCBSMonoReten()
         */
        public void setVCBSMonoReten(String value) {
            this.vcbsMonoReten = value;
        }

    }

}
