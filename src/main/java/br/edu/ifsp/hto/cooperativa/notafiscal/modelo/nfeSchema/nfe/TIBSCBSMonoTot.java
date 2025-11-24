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
 * Grupo de informações de totais da CBS/IBS com monofasia
 * 
 * &lt;p&gt;Classe Java de TIBSCBSMonoTot complex type.&lt;/p&gt;
 * 
 * &lt;p&gt;O seguinte fragmento do esquema especifica o conteúdo esperado contido dentro desta classe.&lt;/p&gt;
 * 
 * &lt;pre&gt;{&#064;code
 * &lt;complexType name="TIBSCBSMonoTot"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="vBCIBSCBS" type="{http://www.portalfiscal.inf.br/nfe}TDec1302RTC"/&gt;
 *         &lt;element name="gIBS" minOccurs="0"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="gIBSUF"&gt;
 *                     &lt;complexType&gt;
 *                       &lt;complexContent&gt;
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                           &lt;sequence&gt;
 *                             &lt;element name="vDif" type="{http://www.portalfiscal.inf.br/nfe}TDec1302RTC"/&gt;
 *                             &lt;element name="vDevTrib" type="{http://www.portalfiscal.inf.br/nfe}TDec1302RTC"/&gt;
 *                             &lt;element name="vIBSUF" type="{http://www.portalfiscal.inf.br/nfe}TDec1302RTC"/&gt;
 *                           &lt;/sequence&gt;
 *                         &lt;/restriction&gt;
 *                       &lt;/complexContent&gt;
 *                     &lt;/complexType&gt;
 *                   &lt;/element&gt;
 *                   &lt;element name="gIBSMun"&gt;
 *                     &lt;complexType&gt;
 *                       &lt;complexContent&gt;
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                           &lt;sequence&gt;
 *                             &lt;element name="vDif" type="{http://www.portalfiscal.inf.br/nfe}TDec1302RTC"/&gt;
 *                             &lt;element name="vDevTrib" type="{http://www.portalfiscal.inf.br/nfe}TDec1302RTC"/&gt;
 *                             &lt;element name="vIBSMun" type="{http://www.portalfiscal.inf.br/nfe}TDec1302RTC"/&gt;
 *                           &lt;/sequence&gt;
 *                         &lt;/restriction&gt;
 *                       &lt;/complexContent&gt;
 *                     &lt;/complexType&gt;
 *                   &lt;/element&gt;
 *                   &lt;element name="vIBS" type="{http://www.portalfiscal.inf.br/nfe}TDec1302RTC"/&gt;
 *                   &lt;element name="vCredPres" type="{http://www.portalfiscal.inf.br/nfe}TDec1302RTC"/&gt;
 *                   &lt;element name="vCredPresCondSus" type="{http://www.portalfiscal.inf.br/nfe}TDec1302RTC"/&gt;
 *                 &lt;/sequence&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="gCBS" minOccurs="0"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="vDif" type="{http://www.portalfiscal.inf.br/nfe}TDec1302RTC"/&gt;
 *                   &lt;element name="vDevTrib" type="{http://www.portalfiscal.inf.br/nfe}TDec1302RTC"/&gt;
 *                   &lt;element name="vCBS" type="{http://www.portalfiscal.inf.br/nfe}TDec1302RTC"/&gt;
 *                   &lt;element name="vCredPres" type="{http://www.portalfiscal.inf.br/nfe}TDec1302RTC"/&gt;
 *                   &lt;element name="vCredPresCondSus" type="{http://www.portalfiscal.inf.br/nfe}TDec1302RTC"/&gt;
 *                 &lt;/sequence&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="gMono" minOccurs="0"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="vIBSMono" type="{http://www.portalfiscal.inf.br/nfe}TDec1302RTC"/&gt;
 *                   &lt;element name="vCBSMono" type="{http://www.portalfiscal.inf.br/nfe}TDec1302RTC"/&gt;
 *                   &lt;element name="vIBSMonoReten" type="{http://www.portalfiscal.inf.br/nfe}TDec1302RTC"/&gt;
 *                   &lt;element name="vCBSMonoReten" type="{http://www.portalfiscal.inf.br/nfe}TDec1302RTC"/&gt;
 *                   &lt;element name="vIBSMonoRet" type="{http://www.portalfiscal.inf.br/nfe}TDec1302RTC"/&gt;
 *                   &lt;element name="vCBSMonoRet" type="{http://www.portalfiscal.inf.br/nfe}TDec1302RTC"/&gt;
 *                 &lt;/sequence&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="gEstornoCred" minOccurs="0"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="vIBSEstCred" type="{http://www.portalfiscal.inf.br/nfe}TDec1302RTC"/&gt;
 *                   &lt;element name="vCBSEstCred" type="{http://www.portalfiscal.inf.br/nfe}TDec1302RTC"/&gt;
 *                 &lt;/sequence&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * }&lt;/pre&gt;
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TIBSCBSMonoTot", propOrder = {
    "vbcibscbs",
    "gibs",
    "gcbs",
    "gMono",
    "gEstornoCred"
})
public class TIBSCBSMonoTot {

    /**
     * Total Base de Calculo
     * 
     */
    @XmlElement(name = "vBCIBSCBS", required = true)
    protected String vbcibscbs;
    /**
     * Totalização do IBS
     * 
     */
    @XmlElement(name = "gIBS")
    protected TIBSCBSMonoTot.GIBS gibs;
    /**
     * Totalização da CBS
     * 
     */
    @XmlElement(name = "gCBS")
    protected TIBSCBSMonoTot.GCBS gcbs;
    /**
     * Só deverá ser utilizado para DFe modelos 55 e 65
     * 
     */
    protected TIBSCBSMonoTot.GMono gMono;
    /**
     * Totalização do estorno de crédito
     * 
     */
    protected TIBSCBSMonoTot.GEstornoCred gEstornoCred;

    /**
     * Total Base de Calculo
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVBCIBSCBS() {
        return vbcibscbs;
    }

    /**
     * Define o valor da propriedade vbcibscbs.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     * @see #getVBCIBSCBS()
     */
    public void setVBCIBSCBS(String value) {
        this.vbcibscbs = value;
    }

    /**
     * Totalização do IBS
     * 
     * @return
     *     possible object is
     *     {@link TIBSCBSMonoTot.GIBS }
     *     
     */
    public TIBSCBSMonoTot.GIBS getGIBS() {
        return gibs;
    }

    /**
     * Define o valor da propriedade gibs.
     * 
     * @param value
     *     allowed object is
     *     {@link TIBSCBSMonoTot.GIBS }
     *     
     * @see #getGIBS()
     */
    public void setGIBS(TIBSCBSMonoTot.GIBS value) {
        this.gibs = value;
    }

    /**
     * Totalização da CBS
     * 
     * @return
     *     possible object is
     *     {@link TIBSCBSMonoTot.GCBS }
     *     
     */
    public TIBSCBSMonoTot.GCBS getGCBS() {
        return gcbs;
    }

    /**
     * Define o valor da propriedade gcbs.
     * 
     * @param value
     *     allowed object is
     *     {@link TIBSCBSMonoTot.GCBS }
     *     
     * @see #getGCBS()
     */
    public void setGCBS(TIBSCBSMonoTot.GCBS value) {
        this.gcbs = value;
    }

    /**
     * Só deverá ser utilizado para DFe modelos 55 e 65
     * 
     * @return
     *     possible object is
     *     {@link TIBSCBSMonoTot.GMono }
     *     
     */
    public TIBSCBSMonoTot.GMono getGMono() {
        return gMono;
    }

    /**
     * Define o valor da propriedade gMono.
     * 
     * @param value
     *     allowed object is
     *     {@link TIBSCBSMonoTot.GMono }
     *     
     * @see #getGMono()
     */
    public void setGMono(TIBSCBSMonoTot.GMono value) {
        this.gMono = value;
    }

    /**
     * Totalização do estorno de crédito
     * 
     * @return
     *     possible object is
     *     {@link TIBSCBSMonoTot.GEstornoCred }
     *     
     */
    public TIBSCBSMonoTot.GEstornoCred getGEstornoCred() {
        return gEstornoCred;
    }

    /**
     * Define o valor da propriedade gEstornoCred.
     * 
     * @param value
     *     allowed object is
     *     {@link TIBSCBSMonoTot.GEstornoCred }
     *     
     * @see #getGEstornoCred()
     */
    public void setGEstornoCred(TIBSCBSMonoTot.GEstornoCred value) {
        this.gEstornoCred = value;
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
     *         &lt;element name="vDif" type="{http://www.portalfiscal.inf.br/nfe}TDec1302RTC"/&gt;
     *         &lt;element name="vDevTrib" type="{http://www.portalfiscal.inf.br/nfe}TDec1302RTC"/&gt;
     *         &lt;element name="vCBS" type="{http://www.portalfiscal.inf.br/nfe}TDec1302RTC"/&gt;
     *         &lt;element name="vCredPres" type="{http://www.portalfiscal.inf.br/nfe}TDec1302RTC"/&gt;
     *         &lt;element name="vCredPresCondSus" type="{http://www.portalfiscal.inf.br/nfe}TDec1302RTC"/&gt;
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
        "vDif",
        "vDevTrib",
        "vcbs",
        "vCredPres",
        "vCredPresCondSus"
    })
    public static class GCBS {

        /**
         * Total do Diferimento
         * 
         */
        @XmlElement(required = true)
        protected String vDif;
        /**
         * Total de devoluções de tributos
         * 
         */
        @XmlElement(required = true)
        protected String vDevTrib;
        /**
         * Valor total da CBS
         * 
         */
        @XmlElement(name = "vCBS", required = true)
        protected String vcbs;
        /**
         * Total do Crédito Presumido
         * 
         */
        @XmlElement(required = true)
        protected String vCredPres;
        /**
         * Total do Crédito Presumido Condição Suspensiva
         * 
         */
        @XmlElement(required = true)
        protected String vCredPresCondSus;

        /**
         * Total do Diferimento
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getVDif() {
            return vDif;
        }

        /**
         * Define o valor da propriedade vDif.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         * @see #getVDif()
         */
        public void setVDif(String value) {
            this.vDif = value;
        }

        /**
         * Total de devoluções de tributos
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

        /**
         * Valor total da CBS
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

        /**
         * Total do Crédito Presumido
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getVCredPres() {
            return vCredPres;
        }

        /**
         * Define o valor da propriedade vCredPres.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         * @see #getVCredPres()
         */
        public void setVCredPres(String value) {
            this.vCredPres = value;
        }

        /**
         * Total do Crédito Presumido Condição Suspensiva
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getVCredPresCondSus() {
            return vCredPresCondSus;
        }

        /**
         * Define o valor da propriedade vCredPresCondSus.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         * @see #getVCredPresCondSus()
         */
        public void setVCredPresCondSus(String value) {
            this.vCredPresCondSus = value;
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
    @XmlType(name = "", propOrder = {
        "vibsEstCred",
        "vcbsEstCred"
    })
    public static class GEstornoCred {

        /**
         * Valor total do IBS estornado
         * 
         */
        @XmlElement(name = "vIBSEstCred", required = true)
        protected String vibsEstCred;
        /**
         * Valor total da CBS estornada
         * 
         */
        @XmlElement(name = "vCBSEstCred", required = true)
        protected String vcbsEstCred;

        /**
         * Valor total do IBS estornado
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
         * Valor total da CBS estornada
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
     *         &lt;element name="gIBSUF"&gt;
     *           &lt;complexType&gt;
     *             &lt;complexContent&gt;
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *                 &lt;sequence&gt;
     *                   &lt;element name="vDif" type="{http://www.portalfiscal.inf.br/nfe}TDec1302RTC"/&gt;
     *                   &lt;element name="vDevTrib" type="{http://www.portalfiscal.inf.br/nfe}TDec1302RTC"/&gt;
     *                   &lt;element name="vIBSUF" type="{http://www.portalfiscal.inf.br/nfe}TDec1302RTC"/&gt;
     *                 &lt;/sequence&gt;
     *               &lt;/restriction&gt;
     *             &lt;/complexContent&gt;
     *           &lt;/complexType&gt;
     *         &lt;/element&gt;
     *         &lt;element name="gIBSMun"&gt;
     *           &lt;complexType&gt;
     *             &lt;complexContent&gt;
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *                 &lt;sequence&gt;
     *                   &lt;element name="vDif" type="{http://www.portalfiscal.inf.br/nfe}TDec1302RTC"/&gt;
     *                   &lt;element name="vDevTrib" type="{http://www.portalfiscal.inf.br/nfe}TDec1302RTC"/&gt;
     *                   &lt;element name="vIBSMun" type="{http://www.portalfiscal.inf.br/nfe}TDec1302RTC"/&gt;
     *                 &lt;/sequence&gt;
     *               &lt;/restriction&gt;
     *             &lt;/complexContent&gt;
     *           &lt;/complexType&gt;
     *         &lt;/element&gt;
     *         &lt;element name="vIBS" type="{http://www.portalfiscal.inf.br/nfe}TDec1302RTC"/&gt;
     *         &lt;element name="vCredPres" type="{http://www.portalfiscal.inf.br/nfe}TDec1302RTC"/&gt;
     *         &lt;element name="vCredPresCondSus" type="{http://www.portalfiscal.inf.br/nfe}TDec1302RTC"/&gt;
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
        "gibsuf",
        "gibsMun",
        "vibs",
        "vCredPres",
        "vCredPresCondSus"
    })
    public static class GIBS {

        /**
         * Totalização do IBS de competência da UF
         * 
         */
        @XmlElement(name = "gIBSUF", required = true)
        protected TIBSCBSMonoTot.GIBS.GIBSUF gibsuf;
        /**
         * Totalização do IBS de competência Municipal
         * 
         */
        @XmlElement(name = "gIBSMun", required = true)
        protected TIBSCBSMonoTot.GIBS.GIBSMun gibsMun;
        /**
         * Valor total do IBS
         * 
         */
        @XmlElement(name = "vIBS", required = true)
        protected String vibs;
        /**
         * Total do Crédito Presumido
         * 
         */
        @XmlElement(required = true)
        protected String vCredPres;
        /**
         * Total do Crédito Presumido Condição Suspensiva
         * 
         */
        @XmlElement(required = true)
        protected String vCredPresCondSus;

        /**
         * Totalização do IBS de competência da UF
         * 
         * @return
         *     possible object is
         *     {@link TIBSCBSMonoTot.GIBS.GIBSUF }
         *     
         */
        public TIBSCBSMonoTot.GIBS.GIBSUF getGIBSUF() {
            return gibsuf;
        }

        /**
         * Define o valor da propriedade gibsuf.
         * 
         * @param value
         *     allowed object is
         *     {@link TIBSCBSMonoTot.GIBS.GIBSUF }
         *     
         * @see #getGIBSUF()
         */
        public void setGIBSUF(TIBSCBSMonoTot.GIBS.GIBSUF value) {
            this.gibsuf = value;
        }

        /**
         * Totalização do IBS de competência Municipal
         * 
         * @return
         *     possible object is
         *     {@link TIBSCBSMonoTot.GIBS.GIBSMun }
         *     
         */
        public TIBSCBSMonoTot.GIBS.GIBSMun getGIBSMun() {
            return gibsMun;
        }

        /**
         * Define o valor da propriedade gibsMun.
         * 
         * @param value
         *     allowed object is
         *     {@link TIBSCBSMonoTot.GIBS.GIBSMun }
         *     
         * @see #getGIBSMun()
         */
        public void setGIBSMun(TIBSCBSMonoTot.GIBS.GIBSMun value) {
            this.gibsMun = value;
        }

        /**
         * Valor total do IBS
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
         * Total do Crédito Presumido
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getVCredPres() {
            return vCredPres;
        }

        /**
         * Define o valor da propriedade vCredPres.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         * @see #getVCredPres()
         */
        public void setVCredPres(String value) {
            this.vCredPres = value;
        }

        /**
         * Total do Crédito Presumido Condição Suspensiva
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getVCredPresCondSus() {
            return vCredPresCondSus;
        }

        /**
         * Define o valor da propriedade vCredPresCondSus.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         * @see #getVCredPresCondSus()
         */
        public void setVCredPresCondSus(String value) {
            this.vCredPresCondSus = value;
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
         *         &lt;element name="vDif" type="{http://www.portalfiscal.inf.br/nfe}TDec1302RTC"/&gt;
         *         &lt;element name="vDevTrib" type="{http://www.portalfiscal.inf.br/nfe}TDec1302RTC"/&gt;
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
            "vDif",
            "vDevTrib",
            "vibsMun"
        })
        public static class GIBSMun {

            /**
             * Total do Diferimento
             * 
             */
            @XmlElement(required = true)
            protected String vDif;
            /**
             * Total de devoluções de tributos
             * 
             */
            @XmlElement(required = true)
            protected String vDevTrib;
            /**
             * Valor total do IBS Municipal
             * 
             */
            @XmlElement(name = "vIBSMun", required = true)
            protected String vibsMun;

            /**
             * Total do Diferimento
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getVDif() {
                return vDif;
            }

            /**
             * Define o valor da propriedade vDif.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             * @see #getVDif()
             */
            public void setVDif(String value) {
                this.vDif = value;
            }

            /**
             * Total de devoluções de tributos
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

            /**
             * Valor total do IBS Municipal
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
         *         &lt;element name="vDif" type="{http://www.portalfiscal.inf.br/nfe}TDec1302RTC"/&gt;
         *         &lt;element name="vDevTrib" type="{http://www.portalfiscal.inf.br/nfe}TDec1302RTC"/&gt;
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
            "vDif",
            "vDevTrib",
            "vibsuf"
        })
        public static class GIBSUF {

            /**
             * Total do Diferimento
             * 
             */
            @XmlElement(required = true)
            protected String vDif;
            /**
             * Total de devoluções de tributos
             * 
             */
            @XmlElement(required = true)
            protected String vDevTrib;
            /**
             * Valor total do IBS Estadual
             * 
             */
            @XmlElement(name = "vIBSUF", required = true)
            protected String vibsuf;

            /**
             * Total do Diferimento
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getVDif() {
                return vDif;
            }

            /**
             * Define o valor da propriedade vDif.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             * @see #getVDif()
             */
            public void setVDif(String value) {
                this.vDif = value;
            }

            /**
             * Total de devoluções de tributos
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

            /**
             * Valor total do IBS Estadual
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
     *         &lt;element name="vIBSMono" type="{http://www.portalfiscal.inf.br/nfe}TDec1302RTC"/&gt;
     *         &lt;element name="vCBSMono" type="{http://www.portalfiscal.inf.br/nfe}TDec1302RTC"/&gt;
     *         &lt;element name="vIBSMonoReten" type="{http://www.portalfiscal.inf.br/nfe}TDec1302RTC"/&gt;
     *         &lt;element name="vCBSMonoReten" type="{http://www.portalfiscal.inf.br/nfe}TDec1302RTC"/&gt;
     *         &lt;element name="vIBSMonoRet" type="{http://www.portalfiscal.inf.br/nfe}TDec1302RTC"/&gt;
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
        "vibsMono",
        "vcbsMono",
        "vibsMonoReten",
        "vcbsMonoReten",
        "vibsMonoRet",
        "vcbsMonoRet"
    })
    public static class GMono {

        /**
         * Valor total do IBS monofásico
         * 
         */
        @XmlElement(name = "vIBSMono", required = true)
        protected String vibsMono;
        /**
         * Valor total da CBS monofásica
         * 
         */
        @XmlElement(name = "vCBSMono", required = true)
        protected String vcbsMono;
        /**
         * Valor total do IBS monofásico sujeito a retenção
         * 
         */
        @XmlElement(name = "vIBSMonoReten", required = true)
        protected String vibsMonoReten;
        /**
         * Valor total da CBS monofásica sujeita a retenção
         * 
         */
        @XmlElement(name = "vCBSMonoReten", required = true)
        protected String vcbsMonoReten;
        /**
         * Valor do IBS monofásico retido anteriormente
         * 
         */
        @XmlElement(name = "vIBSMonoRet", required = true)
        protected String vibsMonoRet;
        /**
         * Valor da CBS monofásica retida anteriormente
         * 
         */
        @XmlElement(name = "vCBSMonoRet", required = true)
        protected String vcbsMonoRet;

        /**
         * Valor total do IBS monofásico
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
         * Valor total da CBS monofásica
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

        /**
         * Valor total do IBS monofásico sujeito a retenção
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
         * Valor total da CBS monofásica sujeita a retenção
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

        /**
         * Valor do IBS monofásico retido anteriormente
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
         * Valor da CBS monofásica retida anteriormente
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

}
