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
 * Tipo: Dados do IPI
 * 
 * &lt;p&gt;Classe Java de TIpi complex type.&lt;/p&gt;
 * 
 * &lt;p&gt;O seguinte fragmento do esquema especifica o conteúdo esperado contido dentro desta classe.&lt;/p&gt;
 * 
 * &lt;pre&gt;{&#064;code
 * &lt;complexType name="TIpi"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="CNPJProd" type="{http://www.portalfiscal.inf.br/nfe}TCnpj" minOccurs="0"/&gt;
 *         &lt;element name="cSelo" minOccurs="0"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.portalfiscal.inf.br/nfe}TString"&gt;
 *               &lt;minLength value="1"/&gt;
 *               &lt;maxLength value="60"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="qSelo" minOccurs="0"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;whiteSpace value="preserve"/&gt;
 *               &lt;pattern value="[0-9]{1,12}"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="cEnq"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.portalfiscal.inf.br/nfe}TString"&gt;
 *               &lt;minLength value="1"/&gt;
 *               &lt;maxLength value="3"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;choice&gt;
 *           &lt;element name="IPITrib"&gt;
 *             &lt;complexType&gt;
 *               &lt;complexContent&gt;
 *                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                   &lt;sequence&gt;
 *                     &lt;element name="CST"&gt;
 *                       &lt;simpleType&gt;
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *                           &lt;whiteSpace value="preserve"/&gt;
 *                           &lt;enumeration value="00"/&gt;
 *                           &lt;enumeration value="49"/&gt;
 *                           &lt;enumeration value="50"/&gt;
 *                           &lt;enumeration value="99"/&gt;
 *                         &lt;/restriction&gt;
 *                       &lt;/simpleType&gt;
 *                     &lt;/element&gt;
 *                     &lt;choice&gt;
 *                       &lt;sequence&gt;
 *                         &lt;element name="vBC" type="{http://www.portalfiscal.inf.br/nfe}TDec_1302"/&gt;
 *                         &lt;element name="pIPI" type="{http://www.portalfiscal.inf.br/nfe}TDec_0302a04"/&gt;
 *                       &lt;/sequence&gt;
 *                       &lt;sequence&gt;
 *                         &lt;element name="qUnid" type="{http://www.portalfiscal.inf.br/nfe}TDec_1204v"/&gt;
 *                         &lt;element name="vUnid" type="{http://www.portalfiscal.inf.br/nfe}TDec_1104"/&gt;
 *                       &lt;/sequence&gt;
 *                     &lt;/choice&gt;
 *                     &lt;element name="vIPI" type="{http://www.portalfiscal.inf.br/nfe}TDec_1302"/&gt;
 *                   &lt;/sequence&gt;
 *                 &lt;/restriction&gt;
 *               &lt;/complexContent&gt;
 *             &lt;/complexType&gt;
 *           &lt;/element&gt;
 *           &lt;element name="IPINT"&gt;
 *             &lt;complexType&gt;
 *               &lt;complexContent&gt;
 *                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                   &lt;sequence&gt;
 *                     &lt;element name="CST"&gt;
 *                       &lt;simpleType&gt;
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *                           &lt;whiteSpace value="preserve"/&gt;
 *                           &lt;enumeration value="01"/&gt;
 *                           &lt;enumeration value="02"/&gt;
 *                           &lt;enumeration value="03"/&gt;
 *                           &lt;enumeration value="04"/&gt;
 *                           &lt;enumeration value="05"/&gt;
 *                           &lt;enumeration value="51"/&gt;
 *                           &lt;enumeration value="52"/&gt;
 *                           &lt;enumeration value="53"/&gt;
 *                           &lt;enumeration value="54"/&gt;
 *                           &lt;enumeration value="55"/&gt;
 *                         &lt;/restriction&gt;
 *                       &lt;/simpleType&gt;
 *                     &lt;/element&gt;
 *                   &lt;/sequence&gt;
 *                 &lt;/restriction&gt;
 *               &lt;/complexContent&gt;
 *             &lt;/complexType&gt;
 *           &lt;/element&gt;
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
@XmlType(name = "TIpi", propOrder = {
    "cnpjProd",
    "cSelo",
    "qSelo",
    "cEnq",
    "ipiTrib",
    "ipint"
})
public class TIpi {

    /**
     * CNPJ do produtor da mercadoria, quando diferente do emitente. Somente para os casos de exportação direta ou indireta.
     * 
     */
    @XmlElement(name = "CNPJProd")
    protected String cnpjProd;
    /**
     * Código do selo de controle do IPI
     * 
     */
    protected String cSelo;
    /**
     * Quantidade de selo de controle do IPI
     * 
     */
    protected String qSelo;
    /**
     * Código de Enquadramento Legal do IPI (tabela a ser criada pela RFB)
     * 
     */
    @XmlElement(required = true)
    protected String cEnq;
    @XmlElement(name = "IPITrib")
    protected TIpi.IPITrib ipiTrib;
    @XmlElement(name = "IPINT")
    protected TIpi.IPINT ipint;

    /**
     * CNPJ do produtor da mercadoria, quando diferente do emitente. Somente para os casos de exportação direta ou indireta.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCNPJProd() {
        return cnpjProd;
    }

    /**
     * Define o valor da propriedade cnpjProd.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     * @see #getCNPJProd()
     */
    public void setCNPJProd(String value) {
        this.cnpjProd = value;
    }

    /**
     * Código do selo de controle do IPI
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCSelo() {
        return cSelo;
    }

    /**
     * Define o valor da propriedade cSelo.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     * @see #getCSelo()
     */
    public void setCSelo(String value) {
        this.cSelo = value;
    }

    /**
     * Quantidade de selo de controle do IPI
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getQSelo() {
        return qSelo;
    }

    /**
     * Define o valor da propriedade qSelo.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     * @see #getQSelo()
     */
    public void setQSelo(String value) {
        this.qSelo = value;
    }

    /**
     * Código de Enquadramento Legal do IPI (tabela a ser criada pela RFB)
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCEnq() {
        return cEnq;
    }

    /**
     * Define o valor da propriedade cEnq.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     * @see #getCEnq()
     */
    public void setCEnq(String value) {
        this.cEnq = value;
    }

    /**
     * Obtém o valor da propriedade ipiTrib.
     * 
     * @return
     *     possible object is
     *     {@link TIpi.IPITrib }
     *     
     */
    public TIpi.IPITrib getIPITrib() {
        return ipiTrib;
    }

    /**
     * Define o valor da propriedade ipiTrib.
     * 
     * @param value
     *     allowed object is
     *     {@link TIpi.IPITrib }
     *     
     */
    public void setIPITrib(TIpi.IPITrib value) {
        this.ipiTrib = value;
    }

    /**
     * Obtém o valor da propriedade ipint.
     * 
     * @return
     *     possible object is
     *     {@link TIpi.IPINT }
     *     
     */
    public TIpi.IPINT getIPINT() {
        return ipint;
    }

    /**
     * Define o valor da propriedade ipint.
     * 
     * @param value
     *     allowed object is
     *     {@link TIpi.IPINT }
     *     
     */
    public void setIPINT(TIpi.IPINT value) {
        this.ipint = value;
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
     *         &lt;element name="CST"&gt;
     *           &lt;simpleType&gt;
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
     *               &lt;whiteSpace value="preserve"/&gt;
     *               &lt;enumeration value="01"/&gt;
     *               &lt;enumeration value="02"/&gt;
     *               &lt;enumeration value="03"/&gt;
     *               &lt;enumeration value="04"/&gt;
     *               &lt;enumeration value="05"/&gt;
     *               &lt;enumeration value="51"/&gt;
     *               &lt;enumeration value="52"/&gt;
     *               &lt;enumeration value="53"/&gt;
     *               &lt;enumeration value="54"/&gt;
     *               &lt;enumeration value="55"/&gt;
     *             &lt;/restriction&gt;
     *           &lt;/simpleType&gt;
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
    @XmlType(name = "", propOrder = {
        "cst"
    })
    public static class IPINT {

        /**
         * Código da Situação Tributária do IPI:
         *  01-Entrada tributada com alíquota zero
         *  02-Entrada isenta
         *  03-Entrada não-tributada
         *  04-Entrada imune
         *  05-Entrada com suspensão
         *  51-Saída tributada com alíquota zero
         *  52-Saída isenta
         *  53-Saída não-tributada
         *  54-Saída imune
         *  55-Saída com suspensão
         * 
         */
        @XmlElement(name = "CST", required = true)
        protected String cst;

        /**
         * Código da Situação Tributária do IPI:
         *  01-Entrada tributada com alíquota zero
         *  02-Entrada isenta
         *  03-Entrada não-tributada
         *  04-Entrada imune
         *  05-Entrada com suspensão
         *  51-Saída tributada com alíquota zero
         *  52-Saída isenta
         *  53-Saída não-tributada
         *  54-Saída imune
         *  55-Saída com suspensão
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
     *         &lt;element name="CST"&gt;
     *           &lt;simpleType&gt;
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
     *               &lt;whiteSpace value="preserve"/&gt;
     *               &lt;enumeration value="00"/&gt;
     *               &lt;enumeration value="49"/&gt;
     *               &lt;enumeration value="50"/&gt;
     *               &lt;enumeration value="99"/&gt;
     *             &lt;/restriction&gt;
     *           &lt;/simpleType&gt;
     *         &lt;/element&gt;
     *         &lt;choice&gt;
     *           &lt;sequence&gt;
     *             &lt;element name="vBC" type="{http://www.portalfiscal.inf.br/nfe}TDec_1302"/&gt;
     *             &lt;element name="pIPI" type="{http://www.portalfiscal.inf.br/nfe}TDec_0302a04"/&gt;
     *           &lt;/sequence&gt;
     *           &lt;sequence&gt;
     *             &lt;element name="qUnid" type="{http://www.portalfiscal.inf.br/nfe}TDec_1204v"/&gt;
     *             &lt;element name="vUnid" type="{http://www.portalfiscal.inf.br/nfe}TDec_1104"/&gt;
     *           &lt;/sequence&gt;
     *         &lt;/choice&gt;
     *         &lt;element name="vIPI" type="{http://www.portalfiscal.inf.br/nfe}TDec_1302"/&gt;
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
        "cst",
        "vbc",
        "pipi",
        "qUnid",
        "vUnid",
        "vipi"
    })
    public static class IPITrib {

        /**
         * Código da Situação Tributária do IPI:
         *  00-Entrada com recuperação de crédito
         *  49 - Outras entradas
         *  50-Saída tributada
         *  99-Outras saídas
         * 
         */
        @XmlElement(name = "CST", required = true)
        protected String cst;
        /**
         * Valor da BC do IPI
         * 
         */
        @XmlElement(name = "vBC")
        protected String vbc;
        /**
         * Alíquota do IPI
         * 
         */
        @XmlElement(name = "pIPI")
        protected String pipi;
        /**
         * Quantidade total na unidade padrão para tributação
         * 
         */
        protected String qUnid;
        /**
         * Valor por Unidade Tributável. Informar o valor do imposto Pauta por unidade de medida.
         * 
         */
        protected String vUnid;
        /**
         * Valor do IPI
         * 
         */
        @XmlElement(name = "vIPI", required = true)
        protected String vipi;

        /**
         * Código da Situação Tributária do IPI:
         *  00-Entrada com recuperação de crédito
         *  49 - Outras entradas
         *  50-Saída tributada
         *  99-Outras saídas
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
         * Valor da BC do IPI
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
         * Alíquota do IPI
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getPIPI() {
            return pipi;
        }

        /**
         * Define o valor da propriedade pipi.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         * @see #getPIPI()
         */
        public void setPIPI(String value) {
            this.pipi = value;
        }

        /**
         * Quantidade total na unidade padrão para tributação
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getQUnid() {
            return qUnid;
        }

        /**
         * Define o valor da propriedade qUnid.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         * @see #getQUnid()
         */
        public void setQUnid(String value) {
            this.qUnid = value;
        }

        /**
         * Valor por Unidade Tributável. Informar o valor do imposto Pauta por unidade de medida.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getVUnid() {
            return vUnid;
        }

        /**
         * Define o valor da propriedade vUnid.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         * @see #getVUnid()
         */
        public void setVUnid(String value) {
            this.vUnid = value;
        }

        /**
         * Valor do IPI
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getVIPI() {
            return vipi;
        }

        /**
         * Define o valor da propriedade vipi.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         * @see #getVIPI()
         */
        public void setVIPI(String value) {
            this.vipi = value;
        }

    }

}
