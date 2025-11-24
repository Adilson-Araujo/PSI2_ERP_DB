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
 * Grupo de informações de totais da CBS/IBS
 * 
 * &lt;p&gt;Classe Java de TIBSCBSTot complex type.&lt;/p&gt;
 * 
 * &lt;p&gt;O seguinte fragmento do esquema especifica o conteúdo esperado contido dentro desta classe.&lt;/p&gt;
 * 
 * &lt;pre&gt;{&#064;code
 * &lt;complexType name="TIBSCBSTot"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="vBCIBSCBS" type="{http://www.portalfiscal.inf.br/nfe}TDec1302RTC"/&gt;
 *         &lt;element name="gIBS"&gt;
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
 *                 &lt;/sequence&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="gCBS"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="vDif" type="{http://www.portalfiscal.inf.br/nfe}TDec1302RTC"/&gt;
 *                   &lt;element name="vDevTrib" type="{http://www.portalfiscal.inf.br/nfe}TDec1302RTC"/&gt;
 *                   &lt;element name="vCBS" type="{http://www.portalfiscal.inf.br/nfe}TDec1302RTC"/&gt;
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
@XmlType(name = "TIBSCBSTot", propOrder = {
    "vbcibscbs",
    "gibs",
    "gcbs",
    "gEstornoCred"
})
public class TIBSCBSTot {

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
    @XmlElement(name = "gIBS", required = true)
    protected TIBSCBSTot.GIBS gibs;
    /**
     * Totalização da CBS
     * 
     */
    @XmlElement(name = "gCBS", required = true)
    protected TIBSCBSTot.GCBS gcbs;
    /**
     * Totalização do estorno de crédito
     * 
     */
    protected TIBSCBSTot.GEstornoCred gEstornoCred;

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
     *     {@link TIBSCBSTot.GIBS }
     *     
     */
    public TIBSCBSTot.GIBS getGIBS() {
        return gibs;
    }

    /**
     * Define o valor da propriedade gibs.
     * 
     * @param value
     *     allowed object is
     *     {@link TIBSCBSTot.GIBS }
     *     
     * @see #getGIBS()
     */
    public void setGIBS(TIBSCBSTot.GIBS value) {
        this.gibs = value;
    }

    /**
     * Totalização da CBS
     * 
     * @return
     *     possible object is
     *     {@link TIBSCBSTot.GCBS }
     *     
     */
    public TIBSCBSTot.GCBS getGCBS() {
        return gcbs;
    }

    /**
     * Define o valor da propriedade gcbs.
     * 
     * @param value
     *     allowed object is
     *     {@link TIBSCBSTot.GCBS }
     *     
     * @see #getGCBS()
     */
    public void setGCBS(TIBSCBSTot.GCBS value) {
        this.gcbs = value;
    }

    /**
     * Totalização do estorno de crédito
     * 
     * @return
     *     possible object is
     *     {@link TIBSCBSTot.GEstornoCred }
     *     
     */
    public TIBSCBSTot.GEstornoCred getGEstornoCred() {
        return gEstornoCred;
    }

    /**
     * Define o valor da propriedade gEstornoCred.
     * 
     * @param value
     *     allowed object is
     *     {@link TIBSCBSTot.GEstornoCred }
     *     
     * @see #getGEstornoCred()
     */
    public void setGEstornoCred(TIBSCBSTot.GEstornoCred value) {
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
        "vcbs"
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
        "vibs"
    })
    public static class GIBS {

        /**
         * Totalização do IBS de competência da UF
         * 
         */
        @XmlElement(name = "gIBSUF", required = true)
        protected TIBSCBSTot.GIBS.GIBSUF gibsuf;
        /**
         * Totalização do IBS de competência Municipal
         * 
         */
        @XmlElement(name = "gIBSMun", required = true)
        protected TIBSCBSTot.GIBS.GIBSMun gibsMun;
        /**
         * Valor total do IBS
         * 
         */
        @XmlElement(name = "vIBS", required = true)
        protected String vibs;

        /**
         * Totalização do IBS de competência da UF
         * 
         * @return
         *     possible object is
         *     {@link TIBSCBSTot.GIBS.GIBSUF }
         *     
         */
        public TIBSCBSTot.GIBS.GIBSUF getGIBSUF() {
            return gibsuf;
        }

        /**
         * Define o valor da propriedade gibsuf.
         * 
         * @param value
         *     allowed object is
         *     {@link TIBSCBSTot.GIBS.GIBSUF }
         *     
         * @see #getGIBSUF()
         */
        public void setGIBSUF(TIBSCBSTot.GIBS.GIBSUF value) {
            this.gibsuf = value;
        }

        /**
         * Totalização do IBS de competência Municipal
         * 
         * @return
         *     possible object is
         *     {@link TIBSCBSTot.GIBS.GIBSMun }
         *     
         */
        public TIBSCBSTot.GIBS.GIBSMun getGIBSMun() {
            return gibsMun;
        }

        /**
         * Define o valor da propriedade gibsMun.
         * 
         * @param value
         *     allowed object is
         *     {@link TIBSCBSTot.GIBS.GIBSMun }
         *     
         * @see #getGIBSMun()
         */
        public void setGIBSMun(TIBSCBSTot.GIBS.GIBSMun value) {
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

}
