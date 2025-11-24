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
 * Grupo de informações do responsável técnico pelo sistema de emissão de DF-e
 * 
 * &lt;p&gt;Classe Java de TInfRespTec complex type.&lt;/p&gt;
 * 
 * &lt;p&gt;O seguinte fragmento do esquema especifica o conteúdo esperado contido dentro desta classe.&lt;/p&gt;
 * 
 * &lt;pre&gt;{&#064;code
 * &lt;complexType name="TInfRespTec"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="CNPJ" type="{http://www.portalfiscal.inf.br/nfe}TCnpjOpc"/&gt;
 *         &lt;element name="xContato"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.portalfiscal.inf.br/nfe}TString"&gt;
 *               &lt;maxLength value="60"/&gt;
 *               &lt;minLength value="2"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="email"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.portalfiscal.inf.br/nfe}TString"&gt;
 *               &lt;whiteSpace value="preserve"/&gt;
 *               &lt;minLength value="6"/&gt;
 *               &lt;maxLength value="60"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="fone"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;whiteSpace value="preserve"/&gt;
 *               &lt;pattern value="[0-9]{6,14}"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;sequence minOccurs="0"&gt;
 *           &lt;element name="idCSRT"&gt;
 *             &lt;simpleType&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *                 &lt;whiteSpace value="preserve"/&gt;
 *                 &lt;pattern value="[0-9]{2}"/&gt;
 *               &lt;/restriction&gt;
 *             &lt;/simpleType&gt;
 *           &lt;/element&gt;
 *           &lt;element name="hashCSRT"&gt;
 *             &lt;simpleType&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}base64Binary"&gt;
 *                 &lt;length value="20"/&gt;
 *               &lt;/restriction&gt;
 *             &lt;/simpleType&gt;
 *           &lt;/element&gt;
 *         &lt;/sequence&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * }&lt;/pre&gt;
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TInfRespTec", propOrder = {
    "cnpj",
    "xContato",
    "email",
    "fone",
    "idCSRT",
    "hashCSRT"
})
public class TInfRespTec {

    /**
     * CNPJ
     * 
     */
    @XmlElement(name = "CNPJ", required = true)
    protected String cnpj;
    /**
     * Informar o nome da pessoa a ser contatada na empresa desenvolvedora do sistema utilizado na emissão do documento fiscal eletrônico.
     * 
     */
    @XmlElement(required = true)
    protected String xContato;
    /**
     * Informar o e-mail da pessoa a ser contatada na empresa desenvolvedora do sistema.
     * 
     */
    @XmlElement(required = true)
    protected String email;
    /**
     * Informar o telefone da pessoa a ser contatada na empresa desenvolvedora do sistema. Preencher com o Código DDD + número do telefone.
     * 
     */
    @XmlElement(required = true)
    protected String fone;
    /**
     * Identificador do CSRT utilizado para montar o hash do CSRT
     * 
     */
    protected String idCSRT;
    /**
     * O hashCSRT é o resultado da função hash (SHA-1 – Base64) do CSRT fornecido pelo fisco mais a Chave de Acesso da NFe.
     * 
     */
    protected byte[] hashCSRT;

    /**
     * CNPJ
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCNPJ() {
        return cnpj;
    }

    /**
     * Define o valor da propriedade cnpj.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     * @see #getCNPJ()
     */
    public void setCNPJ(String value) {
        this.cnpj = value;
    }

    /**
     * Informar o nome da pessoa a ser contatada na empresa desenvolvedora do sistema utilizado na emissão do documento fiscal eletrônico.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getXContato() {
        return xContato;
    }

    /**
     * Define o valor da propriedade xContato.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     * @see #getXContato()
     */
    public void setXContato(String value) {
        this.xContato = value;
    }

    /**
     * Informar o e-mail da pessoa a ser contatada na empresa desenvolvedora do sistema.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEmail() {
        return email;
    }

    /**
     * Define o valor da propriedade email.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     * @see #getEmail()
     */
    public void setEmail(String value) {
        this.email = value;
    }

    /**
     * Informar o telefone da pessoa a ser contatada na empresa desenvolvedora do sistema. Preencher com o Código DDD + número do telefone.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFone() {
        return fone;
    }

    /**
     * Define o valor da propriedade fone.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     * @see #getFone()
     */
    public void setFone(String value) {
        this.fone = value;
    }

    /**
     * Identificador do CSRT utilizado para montar o hash do CSRT
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdCSRT() {
        return idCSRT;
    }

    /**
     * Define o valor da propriedade idCSRT.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     * @see #getIdCSRT()
     */
    public void setIdCSRT(String value) {
        this.idCSRT = value;
    }

    /**
     * O hashCSRT é o resultado da função hash (SHA-1 – Base64) do CSRT fornecido pelo fisco mais a Chave de Acesso da NFe.
     * 
     * @return
     *     possible object is
     *     byte[]
     */
    public byte[] getHashCSRT() {
        return hashCSRT;
    }

    /**
     * Define o valor da propriedade hashCSRT.
     * 
     * @param value
     *     allowed object is
     *     byte[]
     * @see #getHashCSRT()
     */
    public void setHashCSRT(byte[] value) {
        this.hashCSRT = value;
    }

}
