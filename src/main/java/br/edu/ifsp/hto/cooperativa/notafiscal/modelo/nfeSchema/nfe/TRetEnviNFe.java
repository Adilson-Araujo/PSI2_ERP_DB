//
// Este arquivo foi gerado pela Eclipse Implementation of JAXB, v4.0.6 
// Consulte https://eclipse-ee4j.github.io/jaxb-ri 
// Todas as modificações neste arquivo serão perdidas após a recompilação do esquema de origem. 
//


package br.edu.ifsp.hto.cooperativa.notafiscal.modelo.nfeSchema.nfe;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * Tipo Retorno do Pedido de Autorização da Nota Fiscal Eletrônica
 * 
 * &lt;p&gt;Classe Java de TRetEnviNFe complex type.&lt;/p&gt;
 * 
 * &lt;p&gt;O seguinte fragmento do esquema especifica o conteúdo esperado contido dentro desta classe.&lt;/p&gt;
 * 
 * &lt;pre&gt;{&#064;code
 * &lt;complexType name="TRetEnviNFe"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="tpAmb" type="{http://www.portalfiscal.inf.br/nfe}TAmb"/&gt;
 *         &lt;element name="verAplic" type="{http://www.portalfiscal.inf.br/nfe}TVerAplic"/&gt;
 *         &lt;element name="cStat" type="{http://www.portalfiscal.inf.br/nfe}TStat"/&gt;
 *         &lt;element name="xMotivo" type="{http://www.portalfiscal.inf.br/nfe}TMotivo"/&gt;
 *         &lt;element name="cUF" type="{http://www.portalfiscal.inf.br/nfe}TCodUfIBGE"/&gt;
 *         &lt;element name="dhRecbto" type="{http://www.portalfiscal.inf.br/nfe}TDateTimeUTC"/&gt;
 *         &lt;choice&gt;
 *           &lt;element name="infRec" minOccurs="0"&gt;
 *             &lt;complexType&gt;
 *               &lt;complexContent&gt;
 *                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                   &lt;sequence&gt;
 *                     &lt;element name="nRec" type="{http://www.portalfiscal.inf.br/nfe}TRec"/&gt;
 *                     &lt;element name="tMed" type="{http://www.portalfiscal.inf.br/nfe}TMed"/&gt;
 *                   &lt;/sequence&gt;
 *                 &lt;/restriction&gt;
 *               &lt;/complexContent&gt;
 *             &lt;/complexType&gt;
 *           &lt;/element&gt;
 *           &lt;element name="protNFe" type="{http://www.portalfiscal.inf.br/nfe}TProtNFe" minOccurs="0"/&gt;
 *         &lt;/choice&gt;
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
@XmlType(name = "TRetEnviNFe", propOrder = {
    "tpAmb",
    "verAplic",
    "cStat",
    "xMotivo",
    "cuf",
    "dhRecbto",
    "infRec",
    "protNFe"
})
public class TRetEnviNFe {

    /**
     * Identificação do Ambiente:
     *  1 - Produção
     *  2 - Homologação
     * 
     */
    @XmlElement(required = true)
    protected String tpAmb;
    /**
     * Versão do Aplicativo que recebeu o Lote.
     * 
     */
    @XmlElement(required = true)
    protected String verAplic;
    /**
     * Código do status da mensagem enviada.
     * 
     */
    @XmlElement(required = true)
    protected String cStat;
    /**
     * Descrição literal do status do serviço solicitado.
     * 
     */
    @XmlElement(required = true)
    protected String xMotivo;
    /**
     * código da UF de atendimento
     * 
     */
    @XmlElement(name = "cUF", required = true)
    protected String cuf;
    /**
     * Data e hora do recebimento, no formato AAAA-MM-DDTHH:MM:SSTZD
     * 
     */
    @XmlElement(required = true)
    protected String dhRecbto;
    /**
     * Dados do Recibo do Lote
     * 
     */
    protected TRetEnviNFe.InfRec infRec;
    /**
     * Protocolo de status resultado do processamento sincrono da NFC-e
     * 
     */
    protected TProtNFe protNFe;
    @XmlAttribute(name = "versao", required = true)
    protected String versao;

    /**
     * Identificação do Ambiente:
     *  1 - Produção
     *  2 - Homologação
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTpAmb() {
        return tpAmb;
    }

    /**
     * Define o valor da propriedade tpAmb.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     * @see #getTpAmb()
     */
    public void setTpAmb(String value) {
        this.tpAmb = value;
    }

    /**
     * Versão do Aplicativo que recebeu o Lote.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVerAplic() {
        return verAplic;
    }

    /**
     * Define o valor da propriedade verAplic.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     * @see #getVerAplic()
     */
    public void setVerAplic(String value) {
        this.verAplic = value;
    }

    /**
     * Código do status da mensagem enviada.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCStat() {
        return cStat;
    }

    /**
     * Define o valor da propriedade cStat.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     * @see #getCStat()
     */
    public void setCStat(String value) {
        this.cStat = value;
    }

    /**
     * Descrição literal do status do serviço solicitado.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getXMotivo() {
        return xMotivo;
    }

    /**
     * Define o valor da propriedade xMotivo.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     * @see #getXMotivo()
     */
    public void setXMotivo(String value) {
        this.xMotivo = value;
    }

    /**
     * código da UF de atendimento
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCUF() {
        return cuf;
    }

    /**
     * Define o valor da propriedade cuf.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     * @see #getCUF()
     */
    public void setCUF(String value) {
        this.cuf = value;
    }

    /**
     * Data e hora do recebimento, no formato AAAA-MM-DDTHH:MM:SSTZD
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDhRecbto() {
        return dhRecbto;
    }

    /**
     * Define o valor da propriedade dhRecbto.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     * @see #getDhRecbto()
     */
    public void setDhRecbto(String value) {
        this.dhRecbto = value;
    }

    /**
     * Dados do Recibo do Lote
     * 
     * @return
     *     possible object is
     *     {@link TRetEnviNFe.InfRec }
     *     
     */
    public TRetEnviNFe.InfRec getInfRec() {
        return infRec;
    }

    /**
     * Define o valor da propriedade infRec.
     * 
     * @param value
     *     allowed object is
     *     {@link TRetEnviNFe.InfRec }
     *     
     * @see #getInfRec()
     */
    public void setInfRec(TRetEnviNFe.InfRec value) {
        this.infRec = value;
    }

    /**
     * Protocolo de status resultado do processamento sincrono da NFC-e
     * 
     * @return
     *     possible object is
     *     {@link TProtNFe }
     *     
     */
    public TProtNFe getProtNFe() {
        return protNFe;
    }

    /**
     * Define o valor da propriedade protNFe.
     * 
     * @param value
     *     allowed object is
     *     {@link TProtNFe }
     *     
     * @see #getProtNFe()
     */
    public void setProtNFe(TProtNFe value) {
        this.protNFe = value;
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
     *         &lt;element name="nRec" type="{http://www.portalfiscal.inf.br/nfe}TRec"/&gt;
     *         &lt;element name="tMed" type="{http://www.portalfiscal.inf.br/nfe}TMed"/&gt;
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
        "nRec",
        "tMed"
    })
    public static class InfRec {

        /**
         * Número do Recibo
         * 
         */
        @XmlElement(required = true)
        protected String nRec;
        /**
         * Tempo médio de resposta do serviço (em segundos) dos últimos 5 minutos
         * 
         */
        @XmlElement(required = true)
        protected String tMed;

        /**
         * Número do Recibo
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getNRec() {
            return nRec;
        }

        /**
         * Define o valor da propriedade nRec.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         * @see #getNRec()
         */
        public void setNRec(String value) {
            this.nRec = value;
        }

        /**
         * Tempo médio de resposta do serviço (em segundos) dos últimos 5 minutos
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getTMed() {
            return tMed;
        }

        /**
         * Define o valor da propriedade tMed.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         * @see #getTMed()
         */
        public void setTMed(String value) {
            this.tMed = value;
        }

    }

}
