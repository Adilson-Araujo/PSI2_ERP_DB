//
// Este arquivo foi gerado pela Eclipse Implementation of JAXB, v4.0.6 
// Consulte https://eclipse-ee4j.github.io/jaxb-ri 
// Todas as modificações neste arquivo serão perdidas após a recompilação do esquema de origem. 
//


package br.edu.ifsp.hto.cooperativa.notafiscal.modelo.nfeSchema.nfe;

import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlType;


/**
 * Tipo Sigla da UF de emissor // acrescentado em 24/10/08
 * 
 * &lt;p&gt;Classe Java de TUfEmi.&lt;/p&gt;
 * 
 * &lt;p&gt;O seguinte fragmento do esquema especifica o conteúdo esperado contido dentro desta classe.&lt;/p&gt;
 * &lt;pre&gt;{&#064;code
 * &lt;simpleType name="TUfEmi"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;whiteSpace value="preserve"/&gt;
 *     &lt;enumeration value="AC"/&gt;
 *     &lt;enumeration value="AL"/&gt;
 *     &lt;enumeration value="AM"/&gt;
 *     &lt;enumeration value="AP"/&gt;
 *     &lt;enumeration value="BA"/&gt;
 *     &lt;enumeration value="CE"/&gt;
 *     &lt;enumeration value="DF"/&gt;
 *     &lt;enumeration value="ES"/&gt;
 *     &lt;enumeration value="GO"/&gt;
 *     &lt;enumeration value="MA"/&gt;
 *     &lt;enumeration value="MG"/&gt;
 *     &lt;enumeration value="MS"/&gt;
 *     &lt;enumeration value="MT"/&gt;
 *     &lt;enumeration value="PA"/&gt;
 *     &lt;enumeration value="PB"/&gt;
 *     &lt;enumeration value="PE"/&gt;
 *     &lt;enumeration value="PI"/&gt;
 *     &lt;enumeration value="PR"/&gt;
 *     &lt;enumeration value="RJ"/&gt;
 *     &lt;enumeration value="RN"/&gt;
 *     &lt;enumeration value="RO"/&gt;
 *     &lt;enumeration value="RR"/&gt;
 *     &lt;enumeration value="RS"/&gt;
 *     &lt;enumeration value="SC"/&gt;
 *     &lt;enumeration value="SE"/&gt;
 *     &lt;enumeration value="SP"/&gt;
 *     &lt;enumeration value="TO"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * }&lt;/pre&gt;
 * 
 */
@XmlType(name = "TUfEmi")
@XmlEnum
public enum TUfEmi {

    AC,
    AL,
    AM,
    AP,
    BA,
    CE,
    DF,
    ES,
    GO,
    MA,
    MG,
    MS,
    MT,
    PA,
    PB,
    PE,
    PI,
    PR,
    RJ,
    RN,
    RO,
    RR,
    RS,
    SC,
    SE,
    SP,
    TO;

    public String value() {
        return name();
    }

    public static TUfEmi fromValue(String v) {
        return valueOf(v);
    }

}
