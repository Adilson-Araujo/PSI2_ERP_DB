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
 * Tipo CBS IBS Completo
 * 
 * &lt;p&gt;Classe Java de TCIBS complex type.&lt;/p&gt;
 * 
 * &lt;p&gt;O seguinte fragmento do esquema especifica o conteúdo esperado contido dentro desta classe.&lt;/p&gt;
 * 
 * &lt;pre&gt;{&#064;code
 * &lt;complexType name="TCIBS"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="vBC" type="{http://www.portalfiscal.inf.br/nfe}TDec1302RTC"/&gt;
 *         &lt;sequence&gt;
 *           &lt;element name="gIBSUF"&gt;
 *             &lt;complexType&gt;
 *               &lt;complexContent&gt;
 *                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                   &lt;sequence&gt;
 *                     &lt;element name="pIBSUF" type="{http://www.portalfiscal.inf.br/nfe}TDec_0302_04RTC"/&gt;
 *                     &lt;element name="gDif" type="{http://www.portalfiscal.inf.br/nfe}TDif" minOccurs="0"/&gt;
 *                     &lt;element name="gDevTrib" type="{http://www.portalfiscal.inf.br/nfe}TDevTrib" minOccurs="0"/&gt;
 *                     &lt;element name="gRed" type="{http://www.portalfiscal.inf.br/nfe}TRed" minOccurs="0"/&gt;
 *                     &lt;element name="vIBSUF" type="{http://www.portalfiscal.inf.br/nfe}TDec1302RTC"/&gt;
 *                   &lt;/sequence&gt;
 *                 &lt;/restriction&gt;
 *               &lt;/complexContent&gt;
 *             &lt;/complexType&gt;
 *           &lt;/element&gt;
 *           &lt;element name="gIBSMun"&gt;
 *             &lt;complexType&gt;
 *               &lt;complexContent&gt;
 *                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                   &lt;sequence&gt;
 *                     &lt;element name="pIBSMun" type="{http://www.portalfiscal.inf.br/nfe}TDec_0302_04RTC"/&gt;
 *                     &lt;element name="gDif" type="{http://www.portalfiscal.inf.br/nfe}TDif" minOccurs="0"/&gt;
 *                     &lt;element name="gDevTrib" type="{http://www.portalfiscal.inf.br/nfe}TDevTrib" minOccurs="0"/&gt;
 *                     &lt;element name="gRed" type="{http://www.portalfiscal.inf.br/nfe}TRed" minOccurs="0"/&gt;
 *                     &lt;element name="vIBSMun" type="{http://www.portalfiscal.inf.br/nfe}TDec1302RTC"/&gt;
 *                   &lt;/sequence&gt;
 *                 &lt;/restriction&gt;
 *               &lt;/complexContent&gt;
 *             &lt;/complexType&gt;
 *           &lt;/element&gt;
 *           &lt;element name="vIBS" type="{http://www.portalfiscal.inf.br/nfe}TDec1302RTC"/&gt;
 *         &lt;/sequence&gt;
 *         &lt;element name="gCBS"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="pCBS" type="{http://www.portalfiscal.inf.br/nfe}TDec_0302_04RTC"/&gt;
 *                   &lt;element name="gDif" type="{http://www.portalfiscal.inf.br/nfe}TDif" minOccurs="0"/&gt;
 *                   &lt;element name="gDevTrib" type="{http://www.portalfiscal.inf.br/nfe}TDevTrib" minOccurs="0"/&gt;
 *                   &lt;element name="gRed" type="{http://www.portalfiscal.inf.br/nfe}TRed" minOccurs="0"/&gt;
 *                   &lt;element name="vCBS" type="{http://www.portalfiscal.inf.br/nfe}TDec1302RTC"/&gt;
 *                 &lt;/sequence&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="gTribRegular" type="{http://www.portalfiscal.inf.br/nfe}TTribRegular" minOccurs="0"/&gt;
 *         &lt;element name="gTribCompraGov" type="{http://www.portalfiscal.inf.br/nfe}TTribCompraGov" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * }&lt;/pre&gt;
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TCIBS", propOrder = {
    "vbc",
    "gibsuf",
    "gibsMun",
    "vibs",
    "gcbs",
    "gTribRegular",
    "gTribCompraGov"
})
public class TCIBS {

    /**
     * Valor do BC
     * 
     */
    @XmlElement(name = "vBC", required = true)
    protected String vbc;
    /**
     * Grupo de informações do IBS na UF
     * 
     */
    @XmlElement(name = "gIBSUF", required = true)
    protected TCIBS.GIBSUF gibsuf;
    /**
     * Grupo de Informações do IBS no Município
     * 
     */
    @XmlElement(name = "gIBSMun", required = true)
    protected TCIBS.GIBSMun gibsMun;
    /**
     * Valor do IBS
     * 
     */
    @XmlElement(name = "vIBS", required = true)
    protected String vibs;
    /**
     * Grupo de Tributação da CBS
     * 
     */
    @XmlElement(name = "gCBS", required = true)
    protected TCIBS.GCBS gcbs;
    /**
     * Grupo de informações da Tributação Regular. Informar como seria a tributação caso não cumprida a condição resolutória/suspensiva. Exemplo 1: Art. 442, §4. Operações com ZFM e ALC. Exemplo 2: Operações com suspensão do tributo.
     * 
     */
    protected TTribRegular gTribRegular;
    /**
     * Grupo de informações da composição do valor do IBS e da CBS em compras governamental
     * 
     */
    protected TTribCompraGov gTribCompraGov;

    /**
     * Valor do BC
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVBC() {
        return vbc;
    }

    /**
     * Define o valor da propriedade vbc.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     * @see #getVBC()
     */
    public void setVBC(String value) {
        this.vbc = value;
    }

    /**
     * Grupo de informações do IBS na UF
     * 
     * @return
     *     possible object is
     *     {@link TCIBS.GIBSUF }
     *     
     */
    public TCIBS.GIBSUF getGIBSUF() {
        return gibsuf;
    }

    /**
     * Define o valor da propriedade gibsuf.
     * 
     * @param value
     *     allowed object is
     *     {@link TCIBS.GIBSUF }
     *     
     * @see #getGIBSUF()
     */
    public void setGIBSUF(TCIBS.GIBSUF value) {
        this.gibsuf = value;
    }

    /**
     * Grupo de Informações do IBS no Município
     * 
     * @return
     *     possible object is
     *     {@link TCIBS.GIBSMun }
     *     
     */
    public TCIBS.GIBSMun getGIBSMun() {
        return gibsMun;
    }

    /**
     * Define o valor da propriedade gibsMun.
     * 
     * @param value
     *     allowed object is
     *     {@link TCIBS.GIBSMun }
     *     
     * @see #getGIBSMun()
     */
    public void setGIBSMun(TCIBS.GIBSMun value) {
        this.gibsMun = value;
    }

    /**
     * Valor do IBS
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVIBS() {
        return vibs;
    }

    /**
     * Define o valor da propriedade vibs.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     * @see #getVIBS()
     */
    public void setVIBS(String value) {
        this.vibs = value;
    }

    /**
     * Grupo de Tributação da CBS
     * 
     * @return
     *     possible object is
     *     {@link TCIBS.GCBS }
     *     
     */
    public TCIBS.GCBS getGCBS() {
        return gcbs;
    }

    /**
     * Define o valor da propriedade gcbs.
     * 
     * @param value
     *     allowed object is
     *     {@link TCIBS.GCBS }
     *     
     * @see #getGCBS()
     */
    public void setGCBS(TCIBS.GCBS value) {
        this.gcbs = value;
    }

    /**
     * Grupo de informações da Tributação Regular. Informar como seria a tributação caso não cumprida a condição resolutória/suspensiva. Exemplo 1: Art. 442, §4. Operações com ZFM e ALC. Exemplo 2: Operações com suspensão do tributo.
     * 
     * @return
     *     possible object is
     *     {@link TTribRegular }
     *     
     */
    public TTribRegular getGTribRegular() {
        return gTribRegular;
    }

    /**
     * Define o valor da propriedade gTribRegular.
     * 
     * @param value
     *     allowed object is
     *     {@link TTribRegular }
     *     
     * @see #getGTribRegular()
     */
    public void setGTribRegular(TTribRegular value) {
        this.gTribRegular = value;
    }

    /**
     * Grupo de informações da composição do valor do IBS e da CBS em compras governamental
     * 
     * @return
     *     possible object is
     *     {@link TTribCompraGov }
     *     
     */
    public TTribCompraGov getGTribCompraGov() {
        return gTribCompraGov;
    }

    /**
     * Define o valor da propriedade gTribCompraGov.
     * 
     * @param value
     *     allowed object is
     *     {@link TTribCompraGov }
     *     
     * @see #getGTribCompraGov()
     */
    public void setGTribCompraGov(TTribCompraGov value) {
        this.gTribCompraGov = value;
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
     *         &lt;element name="pCBS" type="{http://www.portalfiscal.inf.br/nfe}TDec_0302_04RTC"/&gt;
     *         &lt;element name="gDif" type="{http://www.portalfiscal.inf.br/nfe}TDif" minOccurs="0"/&gt;
     *         &lt;element name="gDevTrib" type="{http://www.portalfiscal.inf.br/nfe}TDevTrib" minOccurs="0"/&gt;
     *         &lt;element name="gRed" type="{http://www.portalfiscal.inf.br/nfe}TRed" minOccurs="0"/&gt;
     *         &lt;element name="vCBS" type="{http://www.portalfiscal.inf.br/nfe}TDec1302RTC"/&gt;
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
        "pcbs",
        "gDif",
        "gDevTrib",
        "gRed",
        "vcbs"
    })
    public static class GCBS {

        /**
         * Aliquota da CBS (em percentual)
         * 
         */
        @XmlElement(name = "pCBS", required = true)
        protected String pcbs;
        /**
         * Grupo de campos do Diferimento
         * 
         */
        protected TDif gDif;
        /**
         * Grupo de Informações da devolução de tributos
         * 
         */
        protected TDevTrib gDevTrib;
        /**
         * Grupo de campos da redução de aliquota
         * 
         */
        protected TRed gRed;
        /**
         * Valor da CBS
         * 
         */
        @XmlElement(name = "vCBS", required = true)
        protected String vcbs;

        /**
         * Aliquota da CBS (em percentual)
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getPCBS() {
            return pcbs;
        }

        /**
         * Define o valor da propriedade pcbs.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         * @see #getPCBS()
         */
        public void setPCBS(String value) {
            this.pcbs = value;
        }

        /**
         * Grupo de campos do Diferimento
         * 
         * @return
         *     possible object is
         *     {@link TDif }
         *     
         */
        public TDif getGDif() {
            return gDif;
        }

        /**
         * Define o valor da propriedade gDif.
         * 
         * @param value
         *     allowed object is
         *     {@link TDif }
         *     
         * @see #getGDif()
         */
        public void setGDif(TDif value) {
            this.gDif = value;
        }

        /**
         * Grupo de Informações da devolução de tributos
         * 
         * @return
         *     possible object is
         *     {@link TDevTrib }
         *     
         */
        public TDevTrib getGDevTrib() {
            return gDevTrib;
        }

        /**
         * Define o valor da propriedade gDevTrib.
         * 
         * @param value
         *     allowed object is
         *     {@link TDevTrib }
         *     
         * @see #getGDevTrib()
         */
        public void setGDevTrib(TDevTrib value) {
            this.gDevTrib = value;
        }

        /**
         * Grupo de campos da redução de aliquota
         * 
         * @return
         *     possible object is
         *     {@link TRed }
         *     
         */
        public TRed getGRed() {
            return gRed;
        }

        /**
         * Define o valor da propriedade gRed.
         * 
         * @param value
         *     allowed object is
         *     {@link TRed }
         *     
         * @see #getGRed()
         */
        public void setGRed(TRed value) {
            this.gRed = value;
        }

        /**
         * Valor da CBS
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getVCBS() {
            return vcbs;
        }

        /**
         * Define o valor da propriedade vcbs.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         * @see #getVCBS()
         */
        public void setVCBS(String value) {
            this.vcbs = value;
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
     *         &lt;element name="pIBSMun" type="{http://www.portalfiscal.inf.br/nfe}TDec_0302_04RTC"/&gt;
     *         &lt;element name="gDif" type="{http://www.portalfiscal.inf.br/nfe}TDif" minOccurs="0"/&gt;
     *         &lt;element name="gDevTrib" type="{http://www.portalfiscal.inf.br/nfe}TDevTrib" minOccurs="0"/&gt;
     *         &lt;element name="gRed" type="{http://www.portalfiscal.inf.br/nfe}TRed" minOccurs="0"/&gt;
     *         &lt;element name="vIBSMun" type="{http://www.portalfiscal.inf.br/nfe}TDec1302RTC"/&gt;
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
        "pibsMun",
        "gDif",
        "gDevTrib",
        "gRed",
        "vibsMun"
    })
    public static class GIBSMun {

        /**
         * Aliquota do IBS Municipal (em percentual)
         * 
         */
        @XmlElement(name = "pIBSMun", required = true)
        protected String pibsMun;
        /**
         * Grupo de campos do Diferimento
         * 
         */
        protected TDif gDif;
        /**
         * Grupo de Informações da devolução de tributos
         * 
         */
        protected TDevTrib gDevTrib;
        /**
         * Grupo de campos da redução de aliquota
         * 
         */
        protected TRed gRed;
        /**
         * Valor do IBS Municipal
         * 
         */
        @XmlElement(name = "vIBSMun", required = true)
        protected String vibsMun;

        /**
         * Aliquota do IBS Municipal (em percentual)
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getPIBSMun() {
            return pibsMun;
        }

        /**
         * Define o valor da propriedade pibsMun.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         * @see #getPIBSMun()
         */
        public void setPIBSMun(String value) {
            this.pibsMun = value;
        }

        /**
         * Grupo de campos do Diferimento
         * 
         * @return
         *     possible object is
         *     {@link TDif }
         *     
         */
        public TDif getGDif() {
            return gDif;
        }

        /**
         * Define o valor da propriedade gDif.
         * 
         * @param value
         *     allowed object is
         *     {@link TDif }
         *     
         * @see #getGDif()
         */
        public void setGDif(TDif value) {
            this.gDif = value;
        }

        /**
         * Grupo de Informações da devolução de tributos
         * 
         * @return
         *     possible object is
         *     {@link TDevTrib }
         *     
         */
        public TDevTrib getGDevTrib() {
            return gDevTrib;
        }

        /**
         * Define o valor da propriedade gDevTrib.
         * 
         * @param value
         *     allowed object is
         *     {@link TDevTrib }
         *     
         * @see #getGDevTrib()
         */
        public void setGDevTrib(TDevTrib value) {
            this.gDevTrib = value;
        }

        /**
         * Grupo de campos da redução de aliquota
         * 
         * @return
         *     possible object is
         *     {@link TRed }
         *     
         */
        public TRed getGRed() {
            return gRed;
        }

        /**
         * Define o valor da propriedade gRed.
         * 
         * @param value
         *     allowed object is
         *     {@link TRed }
         *     
         * @see #getGRed()
         */
        public void setGRed(TRed value) {
            this.gRed = value;
        }

        /**
         * Valor do IBS Municipal
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getVIBSMun() {
            return vibsMun;
        }

        /**
         * Define o valor da propriedade vibsMun.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         * @see #getVIBSMun()
         */
        public void setVIBSMun(String value) {
            this.vibsMun = value;
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
     *         &lt;element name="pIBSUF" type="{http://www.portalfiscal.inf.br/nfe}TDec_0302_04RTC"/&gt;
     *         &lt;element name="gDif" type="{http://www.portalfiscal.inf.br/nfe}TDif" minOccurs="0"/&gt;
     *         &lt;element name="gDevTrib" type="{http://www.portalfiscal.inf.br/nfe}TDevTrib" minOccurs="0"/&gt;
     *         &lt;element name="gRed" type="{http://www.portalfiscal.inf.br/nfe}TRed" minOccurs="0"/&gt;
     *         &lt;element name="vIBSUF" type="{http://www.portalfiscal.inf.br/nfe}TDec1302RTC"/&gt;
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
        "pibsuf",
        "gDif",
        "gDevTrib",
        "gRed",
        "vibsuf"
    })
    public static class GIBSUF {

        /**
         * Aliquota do IBS de competência das UF (em percentual)
         * 
         */
        @XmlElement(name = "pIBSUF", required = true)
        protected String pibsuf;
        /**
         * Grupo de campos do Diferimento
         * 
         */
        protected TDif gDif;
        /**
         * Grupo de Informações da devolução de tributos
         * 
         */
        protected TDevTrib gDevTrib;
        /**
         * Grupo de campos da redução de aliquota
         * 
         */
        protected TRed gRed;
        /**
         * Valor do IBS de competência das UF
         * 
         */
        @XmlElement(name = "vIBSUF", required = true)
        protected String vibsuf;

        /**
         * Aliquota do IBS de competência das UF (em percentual)
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getPIBSUF() {
            return pibsuf;
        }

        /**
         * Define o valor da propriedade pibsuf.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         * @see #getPIBSUF()
         */
        public void setPIBSUF(String value) {
            this.pibsuf = value;
        }

        /**
         * Grupo de campos do Diferimento
         * 
         * @return
         *     possible object is
         *     {@link TDif }
         *     
         */
        public TDif getGDif() {
            return gDif;
        }

        /**
         * Define o valor da propriedade gDif.
         * 
         * @param value
         *     allowed object is
         *     {@link TDif }
         *     
         * @see #getGDif()
         */
        public void setGDif(TDif value) {
            this.gDif = value;
        }

        /**
         * Grupo de Informações da devolução de tributos
         * 
         * @return
         *     possible object is
         *     {@link TDevTrib }
         *     
         */
        public TDevTrib getGDevTrib() {
            return gDevTrib;
        }

        /**
         * Define o valor da propriedade gDevTrib.
         * 
         * @param value
         *     allowed object is
         *     {@link TDevTrib }
         *     
         * @see #getGDevTrib()
         */
        public void setGDevTrib(TDevTrib value) {
            this.gDevTrib = value;
        }

        /**
         * Grupo de campos da redução de aliquota
         * 
         * @return
         *     possible object is
         *     {@link TRed }
         *     
         */
        public TRed getGRed() {
            return gRed;
        }

        /**
         * Define o valor da propriedade gRed.
         * 
         * @param value
         *     allowed object is
         *     {@link TRed }
         *     
         * @see #getGRed()
         */
        public void setGRed(TRed value) {
            this.gRed = value;
        }

        /**
         * Valor do IBS de competência das UF
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getVIBSUF() {
            return vibsuf;
        }

        /**
         * Define o valor da propriedade vibsuf.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         * @see #getVIBSUF()
         */
        public void setVIBSUF(String value) {
            this.vibsuf = value;
        }

    }

}
