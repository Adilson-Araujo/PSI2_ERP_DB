//
// Este arquivo foi gerado pela Eclipse Implementation of JAXB, v4.0.6 
// Consulte https://eclipse-ee4j.github.io/jaxb-ri 
// Todas as modificações neste arquivo serão perdidas após a recompilação do esquema de origem. 
//


package br.edu.ifsp.hto.cooperativa.notafiscal.modelo.nfeSchema.nfe;

import javax.xml.namespace.QName;
import jakarta.xml.bind.JAXBElement;
import jakarta.xml.bind.annotation.XmlElementDecl;
import jakarta.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the br.edu.ifsp.hto.cooperativa.notafiscal.modelo.nfeSchema.nfe package. 
 * <p>An ObjectFactory allows you to programmatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private static final QName _NFe_QNAME = new QName("http://www.portalfiscal.inf.br/nfe", "NFe");
    private static final QName _Signature_QNAME = new QName("http://www.w3.org/2000/09/xmldsig#", "Signature");
    private static final QName _TNFeInfNFeDetImpostoVTotTrib_QNAME = new QName("http://www.portalfiscal.inf.br/nfe", "vTotTrib");
    private static final QName _TNFeInfNFeDetImpostoICMS_QNAME = new QName("http://www.portalfiscal.inf.br/nfe", "ICMS");
    private static final QName _TNFeInfNFeDetImpostoIPI_QNAME = new QName("http://www.portalfiscal.inf.br/nfe", "IPI");
    private static final QName _TNFeInfNFeDetImpostoII_QNAME = new QName("http://www.portalfiscal.inf.br/nfe", "II");
    private static final QName _TNFeInfNFeDetImpostoISSQN_QNAME = new QName("http://www.portalfiscal.inf.br/nfe", "ISSQN");
    private static final QName _TNFeInfNFeDetImpostoPIS_QNAME = new QName("http://www.portalfiscal.inf.br/nfe", "PIS");
    private static final QName _TNFeInfNFeDetImpostoPISST_QNAME = new QName("http://www.portalfiscal.inf.br/nfe", "PISST");
    private static final QName _TNFeInfNFeDetImpostoCOFINS_QNAME = new QName("http://www.portalfiscal.inf.br/nfe", "COFINS");
    private static final QName _TNFeInfNFeDetImpostoCOFINSST_QNAME = new QName("http://www.portalfiscal.inf.br/nfe", "COFINSST");
    private static final QName _TNFeInfNFeDetImpostoICMSUFDest_QNAME = new QName("http://www.portalfiscal.inf.br/nfe", "ICMSUFDest");
    private static final QName _TNFeInfNFeDetImpostoIS_QNAME = new QName("http://www.portalfiscal.inf.br/nfe", "IS");
    private static final QName _TNFeInfNFeDetImpostoIBSCBS_QNAME = new QName("http://www.portalfiscal.inf.br/nfe", "IBSCBS");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: br.edu.ifsp.hto.cooperativa.notafiscal.modelo.nfeSchema.nfe
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ReferenceType }
     * 
     * @return
     *     the new instance of {@link ReferenceType }
     */
    public ReferenceType createReferenceType() {
        return new ReferenceType();
    }

    /**
     * Create an instance of {@link SignedInfoType }
     * 
     * @return
     *     the new instance of {@link SignedInfoType }
     */
    public SignedInfoType createSignedInfoType() {
        return new SignedInfoType();
    }

    /**
     * Create an instance of {@link TIpi }
     * 
     * @return
     *     the new instance of {@link TIpi }
     */
    public TIpi createTIpi() {
        return new TIpi();
    }

    /**
     * Create an instance of {@link TRetEnviNFe }
     * 
     * @return
     *     the new instance of {@link TRetEnviNFe }
     */
    public TRetEnviNFe createTRetEnviNFe() {
        return new TRetEnviNFe();
    }

    /**
     * Create an instance of {@link TProtNFe }
     * 
     * @return
     *     the new instance of {@link TProtNFe }
     */
    public TProtNFe createTProtNFe() {
        return new TProtNFe();
    }

    /**
     * Create an instance of {@link TCIBS }
     * 
     * @return
     *     the new instance of {@link TCIBS }
     */
    public TCIBS createTCIBS() {
        return new TCIBS();
    }

    /**
     * Create an instance of {@link TMonofasia }
     * 
     * @return
     *     the new instance of {@link TMonofasia }
     */
    public TMonofasia createTMonofasia() {
        return new TMonofasia();
    }

    /**
     * Create an instance of {@link TIBSCBSMonoTot }
     * 
     * @return
     *     the new instance of {@link TIBSCBSMonoTot }
     */
    public TIBSCBSMonoTot createTIBSCBSMonoTot() {
        return new TIBSCBSMonoTot();
    }

    /**
     * Create an instance of {@link TIBSCBSMonoTot.GIBS }
     * 
     * @return
     *     the new instance of {@link TIBSCBSMonoTot.GIBS }
     */
    public TIBSCBSMonoTot.GIBS createTIBSCBSMonoTotGIBS() {
        return new TIBSCBSMonoTot.GIBS();
    }

    /**
     * Create an instance of {@link TIBSCBSTot }
     * 
     * @return
     *     the new instance of {@link TIBSCBSTot }
     */
    public TIBSCBSTot createTIBSCBSTot() {
        return new TIBSCBSTot();
    }

    /**
     * Create an instance of {@link TIBSCBSTot.GIBS }
     * 
     * @return
     *     the new instance of {@link TIBSCBSTot.GIBS }
     */
    public TIBSCBSTot.GIBS createTIBSCBSTotGIBS() {
        return new TIBSCBSTot.GIBS();
    }

    /**
     * Create an instance of {@link TNFe }
     * 
     * @return
     *     the new instance of {@link TNFe }
     */
    public TNFe createTNFe() {
        return new TNFe();
    }

    /**
     * Create an instance of {@link TNFe.InfNFe }
     * 
     * @return
     *     the new instance of {@link TNFe.InfNFe }
     */
    public TNFe.InfNFe createTNFeInfNFe() {
        return new TNFe.InfNFe();
    }

    /**
     * Create an instance of {@link TNFe.InfNFe.Agropecuario }
     * 
     * @return
     *     the new instance of {@link TNFe.InfNFe.Agropecuario }
     */
    public TNFe.InfNFe.Agropecuario createTNFeInfNFeAgropecuario() {
        return new TNFe.InfNFe.Agropecuario();
    }

    /**
     * Create an instance of {@link TNFe.InfNFe.Cana }
     * 
     * @return
     *     the new instance of {@link TNFe.InfNFe.Cana }
     */
    public TNFe.InfNFe.Cana createTNFeInfNFeCana() {
        return new TNFe.InfNFe.Cana();
    }

    /**
     * Create an instance of {@link TNFe.InfNFe.InfAdic }
     * 
     * @return
     *     the new instance of {@link TNFe.InfNFe.InfAdic }
     */
    public TNFe.InfNFe.InfAdic createTNFeInfNFeInfAdic() {
        return new TNFe.InfNFe.InfAdic();
    }

    /**
     * Create an instance of {@link TNFe.InfNFe.Pag }
     * 
     * @return
     *     the new instance of {@link TNFe.InfNFe.Pag }
     */
    public TNFe.InfNFe.Pag createTNFeInfNFePag() {
        return new TNFe.InfNFe.Pag();
    }

    /**
     * Create an instance of {@link TNFe.InfNFe.Pag.DetPag }
     * 
     * @return
     *     the new instance of {@link TNFe.InfNFe.Pag.DetPag }
     */
    public TNFe.InfNFe.Pag.DetPag createTNFeInfNFePagDetPag() {
        return new TNFe.InfNFe.Pag.DetPag();
    }

    /**
     * Create an instance of {@link TNFe.InfNFe.Cobr }
     * 
     * @return
     *     the new instance of {@link TNFe.InfNFe.Cobr }
     */
    public TNFe.InfNFe.Cobr createTNFeInfNFeCobr() {
        return new TNFe.InfNFe.Cobr();
    }

    /**
     * Create an instance of {@link TNFe.InfNFe.Transp }
     * 
     * @return
     *     the new instance of {@link TNFe.InfNFe.Transp }
     */
    public TNFe.InfNFe.Transp createTNFeInfNFeTransp() {
        return new TNFe.InfNFe.Transp();
    }

    /**
     * Create an instance of {@link TNFe.InfNFe.Transp.Vol }
     * 
     * @return
     *     the new instance of {@link TNFe.InfNFe.Transp.Vol }
     */
    public TNFe.InfNFe.Transp.Vol createTNFeInfNFeTranspVol() {
        return new TNFe.InfNFe.Transp.Vol();
    }

    /**
     * Create an instance of {@link TNFe.InfNFe.Total }
     * 
     * @return
     *     the new instance of {@link TNFe.InfNFe.Total }
     */
    public TNFe.InfNFe.Total createTNFeInfNFeTotal() {
        return new TNFe.InfNFe.Total();
    }

    /**
     * Create an instance of {@link TNFe.InfNFe.Det }
     * 
     * @return
     *     the new instance of {@link TNFe.InfNFe.Det }
     */
    public TNFe.InfNFe.Det createTNFeInfNFeDet() {
        return new TNFe.InfNFe.Det();
    }

    /**
     * Create an instance of {@link TNFe.InfNFe.Det.ObsItem }
     * 
     * @return
     *     the new instance of {@link TNFe.InfNFe.Det.ObsItem }
     */
    public TNFe.InfNFe.Det.ObsItem createTNFeInfNFeDetObsItem() {
        return new TNFe.InfNFe.Det.ObsItem();
    }

    /**
     * Create an instance of {@link TNFe.InfNFe.Det.ImpostoDevol }
     * 
     * @return
     *     the new instance of {@link TNFe.InfNFe.Det.ImpostoDevol }
     */
    public TNFe.InfNFe.Det.ImpostoDevol createTNFeInfNFeDetImpostoDevol() {
        return new TNFe.InfNFe.Det.ImpostoDevol();
    }

    /**
     * Create an instance of {@link TNFe.InfNFe.Det.Imposto }
     * 
     * @return
     *     the new instance of {@link TNFe.InfNFe.Det.Imposto }
     */
    public TNFe.InfNFe.Det.Imposto createTNFeInfNFeDetImposto() {
        return new TNFe.InfNFe.Det.Imposto();
    }

    /**
     * Create an instance of {@link TNFe.InfNFe.Det.Imposto.COFINS }
     * 
     * @return
     *     the new instance of {@link TNFe.InfNFe.Det.Imposto.COFINS }
     */
    public TNFe.InfNFe.Det.Imposto.COFINS createTNFeInfNFeDetImpostoCOFINS() {
        return new TNFe.InfNFe.Det.Imposto.COFINS();
    }

    /**
     * Create an instance of {@link TNFe.InfNFe.Det.Imposto.PIS }
     * 
     * @return
     *     the new instance of {@link TNFe.InfNFe.Det.Imposto.PIS }
     */
    public TNFe.InfNFe.Det.Imposto.PIS createTNFeInfNFeDetImpostoPIS() {
        return new TNFe.InfNFe.Det.Imposto.PIS();
    }

    /**
     * Create an instance of {@link TNFe.InfNFe.Det.Imposto.ICMS }
     * 
     * @return
     *     the new instance of {@link TNFe.InfNFe.Det.Imposto.ICMS }
     */
    public TNFe.InfNFe.Det.Imposto.ICMS createTNFeInfNFeDetImpostoICMS() {
        return new TNFe.InfNFe.Det.Imposto.ICMS();
    }

    /**
     * Create an instance of {@link TNFe.InfNFe.Det.Prod }
     * 
     * @return
     *     the new instance of {@link TNFe.InfNFe.Det.Prod }
     */
    public TNFe.InfNFe.Det.Prod createTNFeInfNFeDetProd() {
        return new TNFe.InfNFe.Det.Prod();
    }

    /**
     * Create an instance of {@link TNFe.InfNFe.Det.Prod.Comb }
     * 
     * @return
     *     the new instance of {@link TNFe.InfNFe.Det.Prod.Comb }
     */
    public TNFe.InfNFe.Det.Prod.Comb createTNFeInfNFeDetProdComb() {
        return new TNFe.InfNFe.Det.Prod.Comb();
    }

    /**
     * Create an instance of {@link TNFe.InfNFe.Det.Prod.DetExport }
     * 
     * @return
     *     the new instance of {@link TNFe.InfNFe.Det.Prod.DetExport }
     */
    public TNFe.InfNFe.Det.Prod.DetExport createTNFeInfNFeDetProdDetExport() {
        return new TNFe.InfNFe.Det.Prod.DetExport();
    }

    /**
     * Create an instance of {@link TNFe.InfNFe.Det.Prod.DI }
     * 
     * @return
     *     the new instance of {@link TNFe.InfNFe.Det.Prod.DI }
     */
    public TNFe.InfNFe.Det.Prod.DI createTNFeInfNFeDetProdDI() {
        return new TNFe.InfNFe.Det.Prod.DI();
    }

    /**
     * Create an instance of {@link TNFe.InfNFe.Ide }
     * 
     * @return
     *     the new instance of {@link TNFe.InfNFe.Ide }
     */
    public TNFe.InfNFe.Ide createTNFeInfNFeIde() {
        return new TNFe.InfNFe.Ide();
    }

    /**
     * Create an instance of {@link TNFe.InfNFe.Ide.NFref }
     * 
     * @return
     *     the new instance of {@link TNFe.InfNFe.Ide.NFref }
     */
    public TNFe.InfNFe.Ide.NFref createTNFeInfNFeIdeNFref() {
        return new TNFe.InfNFe.Ide.NFref();
    }

    /**
     * Create an instance of {@link TTribNFCom }
     * 
     * @return
     *     the new instance of {@link TTribNFCom }
     */
    public TTribNFCom createTTribNFCom() {
        return new TTribNFCom();
    }

    /**
     * Create an instance of {@link TTribNF3E }
     * 
     * @return
     *     the new instance of {@link TTribNF3E }
     */
    public TTribNF3E createTTribNF3E() {
        return new TTribNF3E();
    }

    /**
     * Create an instance of {@link TTribNFAg }
     * 
     * @return
     *     the new instance of {@link TTribNFAg }
     */
    public TTribNFAg createTTribNFAg() {
        return new TTribNFAg();
    }

    /**
     * Create an instance of {@link TTribCTe }
     * 
     * @return
     *     the new instance of {@link TTribCTe }
     */
    public TTribCTe createTTribCTe() {
        return new TTribCTe();
    }

    /**
     * Create an instance of {@link TTribBPe }
     * 
     * @return
     *     the new instance of {@link TTribBPe }
     */
    public TTribBPe createTTribBPe() {
        return new TTribBPe();
    }

    /**
     * Create an instance of {@link TTribNFCe }
     * 
     * @return
     *     the new instance of {@link TTribNFCe }
     */
    public TTribNFCe createTTribNFCe() {
        return new TTribNFCe();
    }

    /**
     * Create an instance of {@link TTribNFe }
     * 
     * @return
     *     the new instance of {@link TTribNFe }
     */
    public TTribNFe createTTribNFe() {
        return new TTribNFe();
    }

    /**
     * Create an instance of {@link TTribNFGas }
     * 
     * @return
     *     the new instance of {@link TTribNFGas }
     */
    public TTribNFGas createTTribNFGas() {
        return new TTribNFGas();
    }

    /**
     * Create an instance of {@link TIS }
     * 
     * @return
     *     the new instance of {@link TIS }
     */
    public TIS createTIS() {
        return new TIS();
    }

    /**
     * Create an instance of {@link TISTot }
     * 
     * @return
     *     the new instance of {@link TISTot }
     */
    public TISTot createTISTot() {
        return new TISTot();
    }

    /**
     * Create an instance of {@link TRed }
     * 
     * @return
     *     the new instance of {@link TRed }
     */
    public TRed createTRed() {
        return new TRed();
    }

    /**
     * Create an instance of {@link TCredPres }
     * 
     * @return
     *     the new instance of {@link TCredPres }
     */
    public TCredPres createTCredPres() {
        return new TCredPres();
    }

    /**
     * Create an instance of {@link TDif }
     * 
     * @return
     *     the new instance of {@link TDif }
     */
    public TDif createTDif() {
        return new TDif();
    }

    /**
     * Create an instance of {@link TDevTrib }
     * 
     * @return
     *     the new instance of {@link TDevTrib }
     */
    public TDevTrib createTDevTrib() {
        return new TDevTrib();
    }

    /**
     * Create an instance of {@link TTribRegular }
     * 
     * @return
     *     the new instance of {@link TTribRegular }
     */
    public TTribRegular createTTribRegular() {
        return new TTribRegular();
    }

    /**
     * Create an instance of {@link TTribCompraGov }
     * 
     * @return
     *     the new instance of {@link TTribCompraGov }
     */
    public TTribCompraGov createTTribCompraGov() {
        return new TTribCompraGov();
    }

    /**
     * Create an instance of {@link TCompraGovReduzido }
     * 
     * @return
     *     the new instance of {@link TCompraGovReduzido }
     */
    public TCompraGovReduzido createTCompraGovReduzido() {
        return new TCompraGovReduzido();
    }

    /**
     * Create an instance of {@link TCompraGov }
     * 
     * @return
     *     the new instance of {@link TCompraGov }
     */
    public TCompraGov createTCompraGov() {
        return new TCompraGov();
    }

    /**
     * Create an instance of {@link TTransfCred }
     * 
     * @return
     *     the new instance of {@link TTransfCred }
     */
    public TTransfCred createTTransfCred() {
        return new TTransfCred();
    }

    /**
     * Create an instance of {@link TEstornoCred }
     * 
     * @return
     *     the new instance of {@link TEstornoCred }
     */
    public TEstornoCred createTEstornoCred() {
        return new TEstornoCred();
    }

    /**
     * Create an instance of {@link TAjusteCompet }
     * 
     * @return
     *     the new instance of {@link TAjusteCompet }
     */
    public TAjusteCompet createTAjusteCompet() {
        return new TAjusteCompet();
    }

    /**
     * Create an instance of {@link TCredPresOper }
     * 
     * @return
     *     the new instance of {@link TCredPresOper }
     */
    public TCredPresOper createTCredPresOper() {
        return new TCredPresOper();
    }

    /**
     * Create an instance of {@link TCredPresIBSZFM }
     * 
     * @return
     *     the new instance of {@link TCredPresIBSZFM }
     */
    public TCredPresIBSZFM createTCredPresIBSZFM() {
        return new TCredPresIBSZFM();
    }

    /**
     * Create an instance of {@link TEnviNFe }
     * 
     * @return
     *     the new instance of {@link TEnviNFe }
     */
    public TEnviNFe createTEnviNFe() {
        return new TEnviNFe();
    }

    /**
     * Create an instance of {@link TConsReciNFe }
     * 
     * @return
     *     the new instance of {@link TConsReciNFe }
     */
    public TConsReciNFe createTConsReciNFe() {
        return new TConsReciNFe();
    }

    /**
     * Create an instance of {@link TRetConsReciNFe }
     * 
     * @return
     *     the new instance of {@link TRetConsReciNFe }
     */
    public TRetConsReciNFe createTRetConsReciNFe() {
        return new TRetConsReciNFe();
    }

    /**
     * Create an instance of {@link TNfeProc }
     * 
     * @return
     *     the new instance of {@link TNfeProc }
     */
    public TNfeProc createTNfeProc() {
        return new TNfeProc();
    }

    /**
     * Create an instance of {@link TEndereco }
     * 
     * @return
     *     the new instance of {@link TEndereco }
     */
    public TEndereco createTEndereco() {
        return new TEndereco();
    }

    /**
     * Create an instance of {@link TEnderEmi }
     * 
     * @return
     *     the new instance of {@link TEnderEmi }
     */
    public TEnderEmi createTEnderEmi() {
        return new TEnderEmi();
    }

    /**
     * Create an instance of {@link TLocal }
     * 
     * @return
     *     the new instance of {@link TLocal }
     */
    public TLocal createTLocal() {
        return new TLocal();
    }

    /**
     * Create an instance of {@link TInfRespTec }
     * 
     * @return
     *     the new instance of {@link TInfRespTec }
     */
    public TInfRespTec createTInfRespTec() {
        return new TInfRespTec();
    }

    /**
     * Create an instance of {@link TVeiculo }
     * 
     * @return
     *     the new instance of {@link TVeiculo }
     */
    public TVeiculo createTVeiculo() {
        return new TVeiculo();
    }

    /**
     * Create an instance of {@link SignatureType }
     * 
     * @return
     *     the new instance of {@link SignatureType }
     */
    public SignatureType createSignatureType() {
        return new SignatureType();
    }

    /**
     * Create an instance of {@link SignatureValueType }
     * 
     * @return
     *     the new instance of {@link SignatureValueType }
     */
    public SignatureValueType createSignatureValueType() {
        return new SignatureValueType();
    }

    /**
     * Create an instance of {@link TransformsType }
     * 
     * @return
     *     the new instance of {@link TransformsType }
     */
    public TransformsType createTransformsType() {
        return new TransformsType();
    }

    /**
     * Create an instance of {@link TransformType }
     * 
     * @return
     *     the new instance of {@link TransformType }
     */
    public TransformType createTransformType() {
        return new TransformType();
    }

    /**
     * Create an instance of {@link KeyInfoType }
     * 
     * @return
     *     the new instance of {@link KeyInfoType }
     */
    public KeyInfoType createKeyInfoType() {
        return new KeyInfoType();
    }

    /**
     * Create an instance of {@link X509DataType }
     * 
     * @return
     *     the new instance of {@link X509DataType }
     */
    public X509DataType createX509DataType() {
        return new X509DataType();
    }

    /**
     * Create an instance of {@link ReferenceType.DigestMethod }
     * 
     * @return
     *     the new instance of {@link ReferenceType.DigestMethod }
     */
    public ReferenceType.DigestMethod createReferenceTypeDigestMethod() {
        return new ReferenceType.DigestMethod();
    }

    /**
     * Create an instance of {@link SignedInfoType.CanonicalizationMethod }
     * 
     * @return
     *     the new instance of {@link SignedInfoType.CanonicalizationMethod }
     */
    public SignedInfoType.CanonicalizationMethod createSignedInfoTypeCanonicalizationMethod() {
        return new SignedInfoType.CanonicalizationMethod();
    }

    /**
     * Create an instance of {@link SignedInfoType.SignatureMethod }
     * 
     * @return
     *     the new instance of {@link SignedInfoType.SignatureMethod }
     */
    public SignedInfoType.SignatureMethod createSignedInfoTypeSignatureMethod() {
        return new SignedInfoType.SignatureMethod();
    }

    /**
     * Create an instance of {@link TIpi.IPITrib }
     * 
     * @return
     *     the new instance of {@link TIpi.IPITrib }
     */
    public TIpi.IPITrib createTIpiIPITrib() {
        return new TIpi.IPITrib();
    }

    /**
     * Create an instance of {@link TIpi.IPINT }
     * 
     * @return
     *     the new instance of {@link TIpi.IPINT }
     */
    public TIpi.IPINT createTIpiIPINT() {
        return new TIpi.IPINT();
    }

    /**
     * Create an instance of {@link TRetEnviNFe.InfRec }
     * 
     * @return
     *     the new instance of {@link TRetEnviNFe.InfRec }
     */
    public TRetEnviNFe.InfRec createTRetEnviNFeInfRec() {
        return new TRetEnviNFe.InfRec();
    }

    /**
     * Create an instance of {@link TProtNFe.InfProt }
     * 
     * @return
     *     the new instance of {@link TProtNFe.InfProt }
     */
    public TProtNFe.InfProt createTProtNFeInfProt() {
        return new TProtNFe.InfProt();
    }

    /**
     * Create an instance of {@link TCIBS.GIBSUF }
     * 
     * @return
     *     the new instance of {@link TCIBS.GIBSUF }
     */
    public TCIBS.GIBSUF createTCIBSGIBSUF() {
        return new TCIBS.GIBSUF();
    }

    /**
     * Create an instance of {@link TCIBS.GIBSMun }
     * 
     * @return
     *     the new instance of {@link TCIBS.GIBSMun }
     */
    public TCIBS.GIBSMun createTCIBSGIBSMun() {
        return new TCIBS.GIBSMun();
    }

    /**
     * Create an instance of {@link TCIBS.GCBS }
     * 
     * @return
     *     the new instance of {@link TCIBS.GCBS }
     */
    public TCIBS.GCBS createTCIBSGCBS() {
        return new TCIBS.GCBS();
    }

    /**
     * Create an instance of {@link TMonofasia.GMonoPadrao }
     * 
     * @return
     *     the new instance of {@link TMonofasia.GMonoPadrao }
     */
    public TMonofasia.GMonoPadrao createTMonofasiaGMonoPadrao() {
        return new TMonofasia.GMonoPadrao();
    }

    /**
     * Create an instance of {@link TMonofasia.GMonoReten }
     * 
     * @return
     *     the new instance of {@link TMonofasia.GMonoReten }
     */
    public TMonofasia.GMonoReten createTMonofasiaGMonoReten() {
        return new TMonofasia.GMonoReten();
    }

    /**
     * Create an instance of {@link TMonofasia.GMonoRet }
     * 
     * @return
     *     the new instance of {@link TMonofasia.GMonoRet }
     */
    public TMonofasia.GMonoRet createTMonofasiaGMonoRet() {
        return new TMonofasia.GMonoRet();
    }

    /**
     * Create an instance of {@link TMonofasia.GMonoDif }
     * 
     * @return
     *     the new instance of {@link TMonofasia.GMonoDif }
     */
    public TMonofasia.GMonoDif createTMonofasiaGMonoDif() {
        return new TMonofasia.GMonoDif();
    }

    /**
     * Create an instance of {@link TIBSCBSMonoTot.GCBS }
     * 
     * @return
     *     the new instance of {@link TIBSCBSMonoTot.GCBS }
     */
    public TIBSCBSMonoTot.GCBS createTIBSCBSMonoTotGCBS() {
        return new TIBSCBSMonoTot.GCBS();
    }

    /**
     * Create an instance of {@link TIBSCBSMonoTot.GMono }
     * 
     * @return
     *     the new instance of {@link TIBSCBSMonoTot.GMono }
     */
    public TIBSCBSMonoTot.GMono createTIBSCBSMonoTotGMono() {
        return new TIBSCBSMonoTot.GMono();
    }

    /**
     * Create an instance of {@link TIBSCBSMonoTot.GEstornoCred }
     * 
     * @return
     *     the new instance of {@link TIBSCBSMonoTot.GEstornoCred }
     */
    public TIBSCBSMonoTot.GEstornoCred createTIBSCBSMonoTotGEstornoCred() {
        return new TIBSCBSMonoTot.GEstornoCred();
    }

    /**
     * Create an instance of {@link TIBSCBSMonoTot.GIBS.GIBSUF }
     * 
     * @return
     *     the new instance of {@link TIBSCBSMonoTot.GIBS.GIBSUF }
     */
    public TIBSCBSMonoTot.GIBS.GIBSUF createTIBSCBSMonoTotGIBSGIBSUF() {
        return new TIBSCBSMonoTot.GIBS.GIBSUF();
    }

    /**
     * Create an instance of {@link TIBSCBSMonoTot.GIBS.GIBSMun }
     * 
     * @return
     *     the new instance of {@link TIBSCBSMonoTot.GIBS.GIBSMun }
     */
    public TIBSCBSMonoTot.GIBS.GIBSMun createTIBSCBSMonoTotGIBSGIBSMun() {
        return new TIBSCBSMonoTot.GIBS.GIBSMun();
    }

    /**
     * Create an instance of {@link TIBSCBSTot.GCBS }
     * 
     * @return
     *     the new instance of {@link TIBSCBSTot.GCBS }
     */
    public TIBSCBSTot.GCBS createTIBSCBSTotGCBS() {
        return new TIBSCBSTot.GCBS();
    }

    /**
     * Create an instance of {@link TIBSCBSTot.GEstornoCred }
     * 
     * @return
     *     the new instance of {@link TIBSCBSTot.GEstornoCred }
     */
    public TIBSCBSTot.GEstornoCred createTIBSCBSTotGEstornoCred() {
        return new TIBSCBSTot.GEstornoCred();
    }

    /**
     * Create an instance of {@link TIBSCBSTot.GIBS.GIBSUF }
     * 
     * @return
     *     the new instance of {@link TIBSCBSTot.GIBS.GIBSUF }
     */
    public TIBSCBSTot.GIBS.GIBSUF createTIBSCBSTotGIBSGIBSUF() {
        return new TIBSCBSTot.GIBS.GIBSUF();
    }

    /**
     * Create an instance of {@link TIBSCBSTot.GIBS.GIBSMun }
     * 
     * @return
     *     the new instance of {@link TIBSCBSTot.GIBS.GIBSMun }
     */
    public TIBSCBSTot.GIBS.GIBSMun createTIBSCBSTotGIBSGIBSMun() {
        return new TIBSCBSTot.GIBS.GIBSMun();
    }

    /**
     * Create an instance of {@link TNFe.InfNFeSupl }
     * 
     * @return
     *     the new instance of {@link TNFe.InfNFeSupl }
     */
    public TNFe.InfNFeSupl createTNFeInfNFeSupl() {
        return new TNFe.InfNFeSupl();
    }

    /**
     * Create an instance of {@link TNFe.InfNFe.Emit }
     * 
     * @return
     *     the new instance of {@link TNFe.InfNFe.Emit }
     */
    public TNFe.InfNFe.Emit createTNFeInfNFeEmit() {
        return new TNFe.InfNFe.Emit();
    }

    /**
     * Create an instance of {@link TNFe.InfNFe.Avulsa }
     * 
     * @return
     *     the new instance of {@link TNFe.InfNFe.Avulsa }
     */
    public TNFe.InfNFe.Avulsa createTNFeInfNFeAvulsa() {
        return new TNFe.InfNFe.Avulsa();
    }

    /**
     * Create an instance of {@link TNFe.InfNFe.Dest }
     * 
     * @return
     *     the new instance of {@link TNFe.InfNFe.Dest }
     */
    public TNFe.InfNFe.Dest createTNFeInfNFeDest() {
        return new TNFe.InfNFe.Dest();
    }

    /**
     * Create an instance of {@link TNFe.InfNFe.AutXML }
     * 
     * @return
     *     the new instance of {@link TNFe.InfNFe.AutXML }
     */
    public TNFe.InfNFe.AutXML createTNFeInfNFeAutXML() {
        return new TNFe.InfNFe.AutXML();
    }

    /**
     * Create an instance of {@link TNFe.InfNFe.InfIntermed }
     * 
     * @return
     *     the new instance of {@link TNFe.InfNFe.InfIntermed }
     */
    public TNFe.InfNFe.InfIntermed createTNFeInfNFeInfIntermed() {
        return new TNFe.InfNFe.InfIntermed();
    }

    /**
     * Create an instance of {@link TNFe.InfNFe.Exporta }
     * 
     * @return
     *     the new instance of {@link TNFe.InfNFe.Exporta }
     */
    public TNFe.InfNFe.Exporta createTNFeInfNFeExporta() {
        return new TNFe.InfNFe.Exporta();
    }

    /**
     * Create an instance of {@link TNFe.InfNFe.Compra }
     * 
     * @return
     *     the new instance of {@link TNFe.InfNFe.Compra }
     */
    public TNFe.InfNFe.Compra createTNFeInfNFeCompra() {
        return new TNFe.InfNFe.Compra();
    }

    /**
     * Create an instance of {@link TNFe.InfNFe.InfSolicNFF }
     * 
     * @return
     *     the new instance of {@link TNFe.InfNFe.InfSolicNFF }
     */
    public TNFe.InfNFe.InfSolicNFF createTNFeInfNFeInfSolicNFF() {
        return new TNFe.InfNFe.InfSolicNFF();
    }

    /**
     * Create an instance of {@link TNFe.InfNFe.Agropecuario.Defensivo }
     * 
     * @return
     *     the new instance of {@link TNFe.InfNFe.Agropecuario.Defensivo }
     */
    public TNFe.InfNFe.Agropecuario.Defensivo createTNFeInfNFeAgropecuarioDefensivo() {
        return new TNFe.InfNFe.Agropecuario.Defensivo();
    }

    /**
     * Create an instance of {@link TNFe.InfNFe.Agropecuario.GuiaTransito }
     * 
     * @return
     *     the new instance of {@link TNFe.InfNFe.Agropecuario.GuiaTransito }
     */
    public TNFe.InfNFe.Agropecuario.GuiaTransito createTNFeInfNFeAgropecuarioGuiaTransito() {
        return new TNFe.InfNFe.Agropecuario.GuiaTransito();
    }

    /**
     * Create an instance of {@link TNFe.InfNFe.Cana.ForDia }
     * 
     * @return
     *     the new instance of {@link TNFe.InfNFe.Cana.ForDia }
     */
    public TNFe.InfNFe.Cana.ForDia createTNFeInfNFeCanaForDia() {
        return new TNFe.InfNFe.Cana.ForDia();
    }

    /**
     * Create an instance of {@link TNFe.InfNFe.Cana.Deduc }
     * 
     * @return
     *     the new instance of {@link TNFe.InfNFe.Cana.Deduc }
     */
    public TNFe.InfNFe.Cana.Deduc createTNFeInfNFeCanaDeduc() {
        return new TNFe.InfNFe.Cana.Deduc();
    }

    /**
     * Create an instance of {@link TNFe.InfNFe.InfAdic.ObsCont }
     * 
     * @return
     *     the new instance of {@link TNFe.InfNFe.InfAdic.ObsCont }
     */
    public TNFe.InfNFe.InfAdic.ObsCont createTNFeInfNFeInfAdicObsCont() {
        return new TNFe.InfNFe.InfAdic.ObsCont();
    }

    /**
     * Create an instance of {@link TNFe.InfNFe.InfAdic.ObsFisco }
     * 
     * @return
     *     the new instance of {@link TNFe.InfNFe.InfAdic.ObsFisco }
     */
    public TNFe.InfNFe.InfAdic.ObsFisco createTNFeInfNFeInfAdicObsFisco() {
        return new TNFe.InfNFe.InfAdic.ObsFisco();
    }

    /**
     * Create an instance of {@link TNFe.InfNFe.InfAdic.ProcRef }
     * 
     * @return
     *     the new instance of {@link TNFe.InfNFe.InfAdic.ProcRef }
     */
    public TNFe.InfNFe.InfAdic.ProcRef createTNFeInfNFeInfAdicProcRef() {
        return new TNFe.InfNFe.InfAdic.ProcRef();
    }

    /**
     * Create an instance of {@link TNFe.InfNFe.Pag.DetPag.Card }
     * 
     * @return
     *     the new instance of {@link TNFe.InfNFe.Pag.DetPag.Card }
     */
    public TNFe.InfNFe.Pag.DetPag.Card createTNFeInfNFePagDetPagCard() {
        return new TNFe.InfNFe.Pag.DetPag.Card();
    }

    /**
     * Create an instance of {@link TNFe.InfNFe.Cobr.Fat }
     * 
     * @return
     *     the new instance of {@link TNFe.InfNFe.Cobr.Fat }
     */
    public TNFe.InfNFe.Cobr.Fat createTNFeInfNFeCobrFat() {
        return new TNFe.InfNFe.Cobr.Fat();
    }

    /**
     * Create an instance of {@link TNFe.InfNFe.Cobr.Dup }
     * 
     * @return
     *     the new instance of {@link TNFe.InfNFe.Cobr.Dup }
     */
    public TNFe.InfNFe.Cobr.Dup createTNFeInfNFeCobrDup() {
        return new TNFe.InfNFe.Cobr.Dup();
    }

    /**
     * Create an instance of {@link TNFe.InfNFe.Transp.Transporta }
     * 
     * @return
     *     the new instance of {@link TNFe.InfNFe.Transp.Transporta }
     */
    public TNFe.InfNFe.Transp.Transporta createTNFeInfNFeTranspTransporta() {
        return new TNFe.InfNFe.Transp.Transporta();
    }

    /**
     * Create an instance of {@link TNFe.InfNFe.Transp.RetTransp }
     * 
     * @return
     *     the new instance of {@link TNFe.InfNFe.Transp.RetTransp }
     */
    public TNFe.InfNFe.Transp.RetTransp createTNFeInfNFeTranspRetTransp() {
        return new TNFe.InfNFe.Transp.RetTransp();
    }

    /**
     * Create an instance of {@link TNFe.InfNFe.Transp.Vol.Lacres }
     * 
     * @return
     *     the new instance of {@link TNFe.InfNFe.Transp.Vol.Lacres }
     */
    public TNFe.InfNFe.Transp.Vol.Lacres createTNFeInfNFeTranspVolLacres() {
        return new TNFe.InfNFe.Transp.Vol.Lacres();
    }

    /**
     * Create an instance of {@link TNFe.InfNFe.Total.ICMSTot }
     * 
     * @return
     *     the new instance of {@link TNFe.InfNFe.Total.ICMSTot }
     */
    public TNFe.InfNFe.Total.ICMSTot createTNFeInfNFeTotalICMSTot() {
        return new TNFe.InfNFe.Total.ICMSTot();
    }

    /**
     * Create an instance of {@link TNFe.InfNFe.Total.ISSQNtot }
     * 
     * @return
     *     the new instance of {@link TNFe.InfNFe.Total.ISSQNtot }
     */
    public TNFe.InfNFe.Total.ISSQNtot createTNFeInfNFeTotalISSQNtot() {
        return new TNFe.InfNFe.Total.ISSQNtot();
    }

    /**
     * Create an instance of {@link TNFe.InfNFe.Total.RetTrib }
     * 
     * @return
     *     the new instance of {@link TNFe.InfNFe.Total.RetTrib }
     */
    public TNFe.InfNFe.Total.RetTrib createTNFeInfNFeTotalRetTrib() {
        return new TNFe.InfNFe.Total.RetTrib();
    }

    /**
     * Create an instance of {@link TNFe.InfNFe.Det.DFeReferenciado }
     * 
     * @return
     *     the new instance of {@link TNFe.InfNFe.Det.DFeReferenciado }
     */
    public TNFe.InfNFe.Det.DFeReferenciado createTNFeInfNFeDetDFeReferenciado() {
        return new TNFe.InfNFe.Det.DFeReferenciado();
    }

    /**
     * Create an instance of {@link TNFe.InfNFe.Det.ObsItem.ObsCont }
     * 
     * @return
     *     the new instance of {@link TNFe.InfNFe.Det.ObsItem.ObsCont }
     */
    public TNFe.InfNFe.Det.ObsItem.ObsCont createTNFeInfNFeDetObsItemObsCont() {
        return new TNFe.InfNFe.Det.ObsItem.ObsCont();
    }

    /**
     * Create an instance of {@link TNFe.InfNFe.Det.ObsItem.ObsFisco }
     * 
     * @return
     *     the new instance of {@link TNFe.InfNFe.Det.ObsItem.ObsFisco }
     */
    public TNFe.InfNFe.Det.ObsItem.ObsFisco createTNFeInfNFeDetObsItemObsFisco() {
        return new TNFe.InfNFe.Det.ObsItem.ObsFisco();
    }

    /**
     * Create an instance of {@link TNFe.InfNFe.Det.ImpostoDevol.IPI }
     * 
     * @return
     *     the new instance of {@link TNFe.InfNFe.Det.ImpostoDevol.IPI }
     */
    public TNFe.InfNFe.Det.ImpostoDevol.IPI createTNFeInfNFeDetImpostoDevolIPI() {
        return new TNFe.InfNFe.Det.ImpostoDevol.IPI();
    }

    /**
     * Create an instance of {@link TNFe.InfNFe.Det.Imposto.II }
     * 
     * @return
     *     the new instance of {@link TNFe.InfNFe.Det.Imposto.II }
     */
    public TNFe.InfNFe.Det.Imposto.II createTNFeInfNFeDetImpostoII() {
        return new TNFe.InfNFe.Det.Imposto.II();
    }

    /**
     * Create an instance of {@link TNFe.InfNFe.Det.Imposto.ISSQN }
     * 
     * @return
     *     the new instance of {@link TNFe.InfNFe.Det.Imposto.ISSQN }
     */
    public TNFe.InfNFe.Det.Imposto.ISSQN createTNFeInfNFeDetImpostoISSQN() {
        return new TNFe.InfNFe.Det.Imposto.ISSQN();
    }

    /**
     * Create an instance of {@link TNFe.InfNFe.Det.Imposto.PISST }
     * 
     * @return
     *     the new instance of {@link TNFe.InfNFe.Det.Imposto.PISST }
     */
    public TNFe.InfNFe.Det.Imposto.PISST createTNFeInfNFeDetImpostoPISST() {
        return new TNFe.InfNFe.Det.Imposto.PISST();
    }

    /**
     * Create an instance of {@link TNFe.InfNFe.Det.Imposto.COFINSST }
     * 
     * @return
     *     the new instance of {@link TNFe.InfNFe.Det.Imposto.COFINSST }
     */
    public TNFe.InfNFe.Det.Imposto.COFINSST createTNFeInfNFeDetImpostoCOFINSST() {
        return new TNFe.InfNFe.Det.Imposto.COFINSST();
    }

    /**
     * Create an instance of {@link TNFe.InfNFe.Det.Imposto.ICMSUFDest }
     * 
     * @return
     *     the new instance of {@link TNFe.InfNFe.Det.Imposto.ICMSUFDest }
     */
    public TNFe.InfNFe.Det.Imposto.ICMSUFDest createTNFeInfNFeDetImpostoICMSUFDest() {
        return new TNFe.InfNFe.Det.Imposto.ICMSUFDest();
    }

    /**
     * Create an instance of {@link TNFe.InfNFe.Det.Imposto.COFINS.COFINSAliq }
     * 
     * @return
     *     the new instance of {@link TNFe.InfNFe.Det.Imposto.COFINS.COFINSAliq }
     */
    public TNFe.InfNFe.Det.Imposto.COFINS.COFINSAliq createTNFeInfNFeDetImpostoCOFINSCOFINSAliq() {
        return new TNFe.InfNFe.Det.Imposto.COFINS.COFINSAliq();
    }

    /**
     * Create an instance of {@link TNFe.InfNFe.Det.Imposto.COFINS.COFINSQtde }
     * 
     * @return
     *     the new instance of {@link TNFe.InfNFe.Det.Imposto.COFINS.COFINSQtde }
     */
    public TNFe.InfNFe.Det.Imposto.COFINS.COFINSQtde createTNFeInfNFeDetImpostoCOFINSCOFINSQtde() {
        return new TNFe.InfNFe.Det.Imposto.COFINS.COFINSQtde();
    }

    /**
     * Create an instance of {@link TNFe.InfNFe.Det.Imposto.COFINS.COFINSNT }
     * 
     * @return
     *     the new instance of {@link TNFe.InfNFe.Det.Imposto.COFINS.COFINSNT }
     */
    public TNFe.InfNFe.Det.Imposto.COFINS.COFINSNT createTNFeInfNFeDetImpostoCOFINSCOFINSNT() {
        return new TNFe.InfNFe.Det.Imposto.COFINS.COFINSNT();
    }

    /**
     * Create an instance of {@link TNFe.InfNFe.Det.Imposto.COFINS.COFINSOutr }
     * 
     * @return
     *     the new instance of {@link TNFe.InfNFe.Det.Imposto.COFINS.COFINSOutr }
     */
    public TNFe.InfNFe.Det.Imposto.COFINS.COFINSOutr createTNFeInfNFeDetImpostoCOFINSCOFINSOutr() {
        return new TNFe.InfNFe.Det.Imposto.COFINS.COFINSOutr();
    }

    /**
     * Create an instance of {@link TNFe.InfNFe.Det.Imposto.PIS.PISAliq }
     * 
     * @return
     *     the new instance of {@link TNFe.InfNFe.Det.Imposto.PIS.PISAliq }
     */
    public TNFe.InfNFe.Det.Imposto.PIS.PISAliq createTNFeInfNFeDetImpostoPISPISAliq() {
        return new TNFe.InfNFe.Det.Imposto.PIS.PISAliq();
    }

    /**
     * Create an instance of {@link TNFe.InfNFe.Det.Imposto.PIS.PISQtde }
     * 
     * @return
     *     the new instance of {@link TNFe.InfNFe.Det.Imposto.PIS.PISQtde }
     */
    public TNFe.InfNFe.Det.Imposto.PIS.PISQtde createTNFeInfNFeDetImpostoPISPISQtde() {
        return new TNFe.InfNFe.Det.Imposto.PIS.PISQtde();
    }

    /**
     * Create an instance of {@link TNFe.InfNFe.Det.Imposto.PIS.PISNT }
     * 
     * @return
     *     the new instance of {@link TNFe.InfNFe.Det.Imposto.PIS.PISNT }
     */
    public TNFe.InfNFe.Det.Imposto.PIS.PISNT createTNFeInfNFeDetImpostoPISPISNT() {
        return new TNFe.InfNFe.Det.Imposto.PIS.PISNT();
    }

    /**
     * Create an instance of {@link TNFe.InfNFe.Det.Imposto.PIS.PISOutr }
     * 
     * @return
     *     the new instance of {@link TNFe.InfNFe.Det.Imposto.PIS.PISOutr }
     */
    public TNFe.InfNFe.Det.Imposto.PIS.PISOutr createTNFeInfNFeDetImpostoPISPISOutr() {
        return new TNFe.InfNFe.Det.Imposto.PIS.PISOutr();
    }

    /**
     * Create an instance of {@link TNFe.InfNFe.Det.Imposto.ICMS.ICMS00 }
     * 
     * @return
     *     the new instance of {@link TNFe.InfNFe.Det.Imposto.ICMS.ICMS00 }
     */
    public TNFe.InfNFe.Det.Imposto.ICMS.ICMS00 createTNFeInfNFeDetImpostoICMSICMS00() {
        return new TNFe.InfNFe.Det.Imposto.ICMS.ICMS00();
    }

    /**
     * Create an instance of {@link TNFe.InfNFe.Det.Imposto.ICMS.ICMS02 }
     * 
     * @return
     *     the new instance of {@link TNFe.InfNFe.Det.Imposto.ICMS.ICMS02 }
     */
    public TNFe.InfNFe.Det.Imposto.ICMS.ICMS02 createTNFeInfNFeDetImpostoICMSICMS02() {
        return new TNFe.InfNFe.Det.Imposto.ICMS.ICMS02();
    }

    /**
     * Create an instance of {@link TNFe.InfNFe.Det.Imposto.ICMS.ICMS10 }
     * 
     * @return
     *     the new instance of {@link TNFe.InfNFe.Det.Imposto.ICMS.ICMS10 }
     */
    public TNFe.InfNFe.Det.Imposto.ICMS.ICMS10 createTNFeInfNFeDetImpostoICMSICMS10() {
        return new TNFe.InfNFe.Det.Imposto.ICMS.ICMS10();
    }

    /**
     * Create an instance of {@link TNFe.InfNFe.Det.Imposto.ICMS.ICMS15 }
     * 
     * @return
     *     the new instance of {@link TNFe.InfNFe.Det.Imposto.ICMS.ICMS15 }
     */
    public TNFe.InfNFe.Det.Imposto.ICMS.ICMS15 createTNFeInfNFeDetImpostoICMSICMS15() {
        return new TNFe.InfNFe.Det.Imposto.ICMS.ICMS15();
    }

    /**
     * Create an instance of {@link TNFe.InfNFe.Det.Imposto.ICMS.ICMS20 }
     * 
     * @return
     *     the new instance of {@link TNFe.InfNFe.Det.Imposto.ICMS.ICMS20 }
     */
    public TNFe.InfNFe.Det.Imposto.ICMS.ICMS20 createTNFeInfNFeDetImpostoICMSICMS20() {
        return new TNFe.InfNFe.Det.Imposto.ICMS.ICMS20();
    }

    /**
     * Create an instance of {@link TNFe.InfNFe.Det.Imposto.ICMS.ICMS30 }
     * 
     * @return
     *     the new instance of {@link TNFe.InfNFe.Det.Imposto.ICMS.ICMS30 }
     */
    public TNFe.InfNFe.Det.Imposto.ICMS.ICMS30 createTNFeInfNFeDetImpostoICMSICMS30() {
        return new TNFe.InfNFe.Det.Imposto.ICMS.ICMS30();
    }

    /**
     * Create an instance of {@link TNFe.InfNFe.Det.Imposto.ICMS.ICMS40 }
     * 
     * @return
     *     the new instance of {@link TNFe.InfNFe.Det.Imposto.ICMS.ICMS40 }
     */
    public TNFe.InfNFe.Det.Imposto.ICMS.ICMS40 createTNFeInfNFeDetImpostoICMSICMS40() {
        return new TNFe.InfNFe.Det.Imposto.ICMS.ICMS40();
    }

    /**
     * Create an instance of {@link TNFe.InfNFe.Det.Imposto.ICMS.ICMS51 }
     * 
     * @return
     *     the new instance of {@link TNFe.InfNFe.Det.Imposto.ICMS.ICMS51 }
     */
    public TNFe.InfNFe.Det.Imposto.ICMS.ICMS51 createTNFeInfNFeDetImpostoICMSICMS51() {
        return new TNFe.InfNFe.Det.Imposto.ICMS.ICMS51();
    }

    /**
     * Create an instance of {@link TNFe.InfNFe.Det.Imposto.ICMS.ICMS53 }
     * 
     * @return
     *     the new instance of {@link TNFe.InfNFe.Det.Imposto.ICMS.ICMS53 }
     */
    public TNFe.InfNFe.Det.Imposto.ICMS.ICMS53 createTNFeInfNFeDetImpostoICMSICMS53() {
        return new TNFe.InfNFe.Det.Imposto.ICMS.ICMS53();
    }

    /**
     * Create an instance of {@link TNFe.InfNFe.Det.Imposto.ICMS.ICMS60 }
     * 
     * @return
     *     the new instance of {@link TNFe.InfNFe.Det.Imposto.ICMS.ICMS60 }
     */
    public TNFe.InfNFe.Det.Imposto.ICMS.ICMS60 createTNFeInfNFeDetImpostoICMSICMS60() {
        return new TNFe.InfNFe.Det.Imposto.ICMS.ICMS60();
    }

    /**
     * Create an instance of {@link TNFe.InfNFe.Det.Imposto.ICMS.ICMS61 }
     * 
     * @return
     *     the new instance of {@link TNFe.InfNFe.Det.Imposto.ICMS.ICMS61 }
     */
    public TNFe.InfNFe.Det.Imposto.ICMS.ICMS61 createTNFeInfNFeDetImpostoICMSICMS61() {
        return new TNFe.InfNFe.Det.Imposto.ICMS.ICMS61();
    }

    /**
     * Create an instance of {@link TNFe.InfNFe.Det.Imposto.ICMS.ICMS70 }
     * 
     * @return
     *     the new instance of {@link TNFe.InfNFe.Det.Imposto.ICMS.ICMS70 }
     */
    public TNFe.InfNFe.Det.Imposto.ICMS.ICMS70 createTNFeInfNFeDetImpostoICMSICMS70() {
        return new TNFe.InfNFe.Det.Imposto.ICMS.ICMS70();
    }

    /**
     * Create an instance of {@link TNFe.InfNFe.Det.Imposto.ICMS.ICMS90 }
     * 
     * @return
     *     the new instance of {@link TNFe.InfNFe.Det.Imposto.ICMS.ICMS90 }
     */
    public TNFe.InfNFe.Det.Imposto.ICMS.ICMS90 createTNFeInfNFeDetImpostoICMSICMS90() {
        return new TNFe.InfNFe.Det.Imposto.ICMS.ICMS90();
    }

    /**
     * Create an instance of {@link TNFe.InfNFe.Det.Imposto.ICMS.ICMSPart }
     * 
     * @return
     *     the new instance of {@link TNFe.InfNFe.Det.Imposto.ICMS.ICMSPart }
     */
    public TNFe.InfNFe.Det.Imposto.ICMS.ICMSPart createTNFeInfNFeDetImpostoICMSICMSPart() {
        return new TNFe.InfNFe.Det.Imposto.ICMS.ICMSPart();
    }

    /**
     * Create an instance of {@link TNFe.InfNFe.Det.Imposto.ICMS.ICMSST }
     * 
     * @return
     *     the new instance of {@link TNFe.InfNFe.Det.Imposto.ICMS.ICMSST }
     */
    public TNFe.InfNFe.Det.Imposto.ICMS.ICMSST createTNFeInfNFeDetImpostoICMSICMSST() {
        return new TNFe.InfNFe.Det.Imposto.ICMS.ICMSST();
    }

    /**
     * Create an instance of {@link TNFe.InfNFe.Det.Imposto.ICMS.ICMSSN101 }
     * 
     * @return
     *     the new instance of {@link TNFe.InfNFe.Det.Imposto.ICMS.ICMSSN101 }
     */
    public TNFe.InfNFe.Det.Imposto.ICMS.ICMSSN101 createTNFeInfNFeDetImpostoICMSICMSSN101() {
        return new TNFe.InfNFe.Det.Imposto.ICMS.ICMSSN101();
    }

    /**
     * Create an instance of {@link TNFe.InfNFe.Det.Imposto.ICMS.ICMSSN102 }
     * 
     * @return
     *     the new instance of {@link TNFe.InfNFe.Det.Imposto.ICMS.ICMSSN102 }
     */
    public TNFe.InfNFe.Det.Imposto.ICMS.ICMSSN102 createTNFeInfNFeDetImpostoICMSICMSSN102() {
        return new TNFe.InfNFe.Det.Imposto.ICMS.ICMSSN102();
    }

    /**
     * Create an instance of {@link TNFe.InfNFe.Det.Imposto.ICMS.ICMSSN201 }
     * 
     * @return
     *     the new instance of {@link TNFe.InfNFe.Det.Imposto.ICMS.ICMSSN201 }
     */
    public TNFe.InfNFe.Det.Imposto.ICMS.ICMSSN201 createTNFeInfNFeDetImpostoICMSICMSSN201() {
        return new TNFe.InfNFe.Det.Imposto.ICMS.ICMSSN201();
    }

    /**
     * Create an instance of {@link TNFe.InfNFe.Det.Imposto.ICMS.ICMSSN202 }
     * 
     * @return
     *     the new instance of {@link TNFe.InfNFe.Det.Imposto.ICMS.ICMSSN202 }
     */
    public TNFe.InfNFe.Det.Imposto.ICMS.ICMSSN202 createTNFeInfNFeDetImpostoICMSICMSSN202() {
        return new TNFe.InfNFe.Det.Imposto.ICMS.ICMSSN202();
    }

    /**
     * Create an instance of {@link TNFe.InfNFe.Det.Imposto.ICMS.ICMSSN500 }
     * 
     * @return
     *     the new instance of {@link TNFe.InfNFe.Det.Imposto.ICMS.ICMSSN500 }
     */
    public TNFe.InfNFe.Det.Imposto.ICMS.ICMSSN500 createTNFeInfNFeDetImpostoICMSICMSSN500() {
        return new TNFe.InfNFe.Det.Imposto.ICMS.ICMSSN500();
    }

    /**
     * Create an instance of {@link TNFe.InfNFe.Det.Imposto.ICMS.ICMSSN900 }
     * 
     * @return
     *     the new instance of {@link TNFe.InfNFe.Det.Imposto.ICMS.ICMSSN900 }
     */
    public TNFe.InfNFe.Det.Imposto.ICMS.ICMSSN900 createTNFeInfNFeDetImpostoICMSICMSSN900() {
        return new TNFe.InfNFe.Det.Imposto.ICMS.ICMSSN900();
    }

    /**
     * Create an instance of {@link TNFe.InfNFe.Det.Prod.GCred }
     * 
     * @return
     *     the new instance of {@link TNFe.InfNFe.Det.Prod.GCred }
     */
    public TNFe.InfNFe.Det.Prod.GCred createTNFeInfNFeDetProdGCred() {
        return new TNFe.InfNFe.Det.Prod.GCred();
    }

    /**
     * Create an instance of {@link TNFe.InfNFe.Det.Prod.Rastro }
     * 
     * @return
     *     the new instance of {@link TNFe.InfNFe.Det.Prod.Rastro }
     */
    public TNFe.InfNFe.Det.Prod.Rastro createTNFeInfNFeDetProdRastro() {
        return new TNFe.InfNFe.Det.Prod.Rastro();
    }

    /**
     * Create an instance of {@link TNFe.InfNFe.Det.Prod.InfProdNFF }
     * 
     * @return
     *     the new instance of {@link TNFe.InfNFe.Det.Prod.InfProdNFF }
     */
    public TNFe.InfNFe.Det.Prod.InfProdNFF createTNFeInfNFeDetProdInfProdNFF() {
        return new TNFe.InfNFe.Det.Prod.InfProdNFF();
    }

    /**
     * Create an instance of {@link TNFe.InfNFe.Det.Prod.InfProdEmb }
     * 
     * @return
     *     the new instance of {@link TNFe.InfNFe.Det.Prod.InfProdEmb }
     */
    public TNFe.InfNFe.Det.Prod.InfProdEmb createTNFeInfNFeDetProdInfProdEmb() {
        return new TNFe.InfNFe.Det.Prod.InfProdEmb();
    }

    /**
     * Create an instance of {@link TNFe.InfNFe.Det.Prod.VeicProd }
     * 
     * @return
     *     the new instance of {@link TNFe.InfNFe.Det.Prod.VeicProd }
     */
    public TNFe.InfNFe.Det.Prod.VeicProd createTNFeInfNFeDetProdVeicProd() {
        return new TNFe.InfNFe.Det.Prod.VeicProd();
    }

    /**
     * Create an instance of {@link TNFe.InfNFe.Det.Prod.Med }
     * 
     * @return
     *     the new instance of {@link TNFe.InfNFe.Det.Prod.Med }
     */
    public TNFe.InfNFe.Det.Prod.Med createTNFeInfNFeDetProdMed() {
        return new TNFe.InfNFe.Det.Prod.Med();
    }

    /**
     * Create an instance of {@link TNFe.InfNFe.Det.Prod.Arma }
     * 
     * @return
     *     the new instance of {@link TNFe.InfNFe.Det.Prod.Arma }
     */
    public TNFe.InfNFe.Det.Prod.Arma createTNFeInfNFeDetProdArma() {
        return new TNFe.InfNFe.Det.Prod.Arma();
    }

    /**
     * Create an instance of {@link TNFe.InfNFe.Det.Prod.Comb.CIDE }
     * 
     * @return
     *     the new instance of {@link TNFe.InfNFe.Det.Prod.Comb.CIDE }
     */
    public TNFe.InfNFe.Det.Prod.Comb.CIDE createTNFeInfNFeDetProdCombCIDE() {
        return new TNFe.InfNFe.Det.Prod.Comb.CIDE();
    }

    /**
     * Create an instance of {@link TNFe.InfNFe.Det.Prod.Comb.Encerrante }
     * 
     * @return
     *     the new instance of {@link TNFe.InfNFe.Det.Prod.Comb.Encerrante }
     */
    public TNFe.InfNFe.Det.Prod.Comb.Encerrante createTNFeInfNFeDetProdCombEncerrante() {
        return new TNFe.InfNFe.Det.Prod.Comb.Encerrante();
    }

    /**
     * Create an instance of {@link TNFe.InfNFe.Det.Prod.Comb.OrigComb }
     * 
     * @return
     *     the new instance of {@link TNFe.InfNFe.Det.Prod.Comb.OrigComb }
     */
    public TNFe.InfNFe.Det.Prod.Comb.OrigComb createTNFeInfNFeDetProdCombOrigComb() {
        return new TNFe.InfNFe.Det.Prod.Comb.OrigComb();
    }

    /**
     * Create an instance of {@link TNFe.InfNFe.Det.Prod.DetExport.ExportInd }
     * 
     * @return
     *     the new instance of {@link TNFe.InfNFe.Det.Prod.DetExport.ExportInd }
     */
    public TNFe.InfNFe.Det.Prod.DetExport.ExportInd createTNFeInfNFeDetProdDetExportExportInd() {
        return new TNFe.InfNFe.Det.Prod.DetExport.ExportInd();
    }

    /**
     * Create an instance of {@link TNFe.InfNFe.Det.Prod.DI.Adi }
     * 
     * @return
     *     the new instance of {@link TNFe.InfNFe.Det.Prod.DI.Adi }
     */
    public TNFe.InfNFe.Det.Prod.DI.Adi createTNFeInfNFeDetProdDIAdi() {
        return new TNFe.InfNFe.Det.Prod.DI.Adi();
    }

    /**
     * Create an instance of {@link TNFe.InfNFe.Ide.GPagAntecipado }
     * 
     * @return
     *     the new instance of {@link TNFe.InfNFe.Ide.GPagAntecipado }
     */
    public TNFe.InfNFe.Ide.GPagAntecipado createTNFeInfNFeIdeGPagAntecipado() {
        return new TNFe.InfNFe.Ide.GPagAntecipado();
    }

    /**
     * Create an instance of {@link TNFe.InfNFe.Ide.NFref.RefNF }
     * 
     * @return
     *     the new instance of {@link TNFe.InfNFe.Ide.NFref.RefNF }
     */
    public TNFe.InfNFe.Ide.NFref.RefNF createTNFeInfNFeIdeNFrefRefNF() {
        return new TNFe.InfNFe.Ide.NFref.RefNF();
    }

    /**
     * Create an instance of {@link TNFe.InfNFe.Ide.NFref.RefNFP }
     * 
     * @return
     *     the new instance of {@link TNFe.InfNFe.Ide.NFref.RefNFP }
     */
    public TNFe.InfNFe.Ide.NFref.RefNFP createTNFeInfNFeIdeNFrefRefNFP() {
        return new TNFe.InfNFe.Ide.NFref.RefNFP();
    }

    /**
     * Create an instance of {@link TNFe.InfNFe.Ide.NFref.RefECF }
     * 
     * @return
     *     the new instance of {@link TNFe.InfNFe.Ide.NFref.RefECF }
     */
    public TNFe.InfNFe.Ide.NFref.RefECF createTNFeInfNFeIdeNFrefRefECF() {
        return new TNFe.InfNFe.Ide.NFref.RefECF();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TNFe }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link TNFe }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.portalfiscal.inf.br/nfe", name = "NFe")
    public JAXBElement<TNFe> createNFe(TNFe value) {
        return new JAXBElement<>(_NFe_QNAME, TNFe.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SignatureType }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link SignatureType }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.w3.org/2000/09/xmldsig#", name = "Signature")
    public JAXBElement<SignatureType> createSignature(SignatureType value) {
        return new JAXBElement<>(_Signature_QNAME, SignatureType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.portalfiscal.inf.br/nfe", name = "vTotTrib", scope = TNFe.InfNFe.Det.Imposto.class)
    public JAXBElement<String> createTNFeInfNFeDetImpostoVTotTrib(String value) {
        return new JAXBElement<>(_TNFeInfNFeDetImpostoVTotTrib_QNAME, String.class, TNFe.InfNFe.Det.Imposto.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TNFe.InfNFe.Det.Imposto.ICMS }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link TNFe.InfNFe.Det.Imposto.ICMS }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.portalfiscal.inf.br/nfe", name = "ICMS", scope = TNFe.InfNFe.Det.Imposto.class)
    public JAXBElement<TNFe.InfNFe.Det.Imposto.ICMS> createTNFeInfNFeDetImpostoICMS(TNFe.InfNFe.Det.Imposto.ICMS value) {
        return new JAXBElement<>(_TNFeInfNFeDetImpostoICMS_QNAME, TNFe.InfNFe.Det.Imposto.ICMS.class, TNFe.InfNFe.Det.Imposto.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TIpi }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link TIpi }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.portalfiscal.inf.br/nfe", name = "IPI", scope = TNFe.InfNFe.Det.Imposto.class)
    public JAXBElement<TIpi> createTNFeInfNFeDetImpostoIPI(TIpi value) {
        return new JAXBElement<>(_TNFeInfNFeDetImpostoIPI_QNAME, TIpi.class, TNFe.InfNFe.Det.Imposto.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TNFe.InfNFe.Det.Imposto.II }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link TNFe.InfNFe.Det.Imposto.II }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.portalfiscal.inf.br/nfe", name = "II", scope = TNFe.InfNFe.Det.Imposto.class)
    public JAXBElement<TNFe.InfNFe.Det.Imposto.II> createTNFeInfNFeDetImpostoII(TNFe.InfNFe.Det.Imposto.II value) {
        return new JAXBElement<>(_TNFeInfNFeDetImpostoII_QNAME, TNFe.InfNFe.Det.Imposto.II.class, TNFe.InfNFe.Det.Imposto.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TNFe.InfNFe.Det.Imposto.ISSQN }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link TNFe.InfNFe.Det.Imposto.ISSQN }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.portalfiscal.inf.br/nfe", name = "ISSQN", scope = TNFe.InfNFe.Det.Imposto.class)
    public JAXBElement<TNFe.InfNFe.Det.Imposto.ISSQN> createTNFeInfNFeDetImpostoISSQN(TNFe.InfNFe.Det.Imposto.ISSQN value) {
        return new JAXBElement<>(_TNFeInfNFeDetImpostoISSQN_QNAME, TNFe.InfNFe.Det.Imposto.ISSQN.class, TNFe.InfNFe.Det.Imposto.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TNFe.InfNFe.Det.Imposto.PIS }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link TNFe.InfNFe.Det.Imposto.PIS }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.portalfiscal.inf.br/nfe", name = "PIS", scope = TNFe.InfNFe.Det.Imposto.class)
    public JAXBElement<TNFe.InfNFe.Det.Imposto.PIS> createTNFeInfNFeDetImpostoPIS(TNFe.InfNFe.Det.Imposto.PIS value) {
        return new JAXBElement<>(_TNFeInfNFeDetImpostoPIS_QNAME, TNFe.InfNFe.Det.Imposto.PIS.class, TNFe.InfNFe.Det.Imposto.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TNFe.InfNFe.Det.Imposto.PISST }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link TNFe.InfNFe.Det.Imposto.PISST }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.portalfiscal.inf.br/nfe", name = "PISST", scope = TNFe.InfNFe.Det.Imposto.class)
    public JAXBElement<TNFe.InfNFe.Det.Imposto.PISST> createTNFeInfNFeDetImpostoPISST(TNFe.InfNFe.Det.Imposto.PISST value) {
        return new JAXBElement<>(_TNFeInfNFeDetImpostoPISST_QNAME, TNFe.InfNFe.Det.Imposto.PISST.class, TNFe.InfNFe.Det.Imposto.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TNFe.InfNFe.Det.Imposto.COFINS }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link TNFe.InfNFe.Det.Imposto.COFINS }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.portalfiscal.inf.br/nfe", name = "COFINS", scope = TNFe.InfNFe.Det.Imposto.class)
    public JAXBElement<TNFe.InfNFe.Det.Imposto.COFINS> createTNFeInfNFeDetImpostoCOFINS(TNFe.InfNFe.Det.Imposto.COFINS value) {
        return new JAXBElement<>(_TNFeInfNFeDetImpostoCOFINS_QNAME, TNFe.InfNFe.Det.Imposto.COFINS.class, TNFe.InfNFe.Det.Imposto.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TNFe.InfNFe.Det.Imposto.COFINSST }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link TNFe.InfNFe.Det.Imposto.COFINSST }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.portalfiscal.inf.br/nfe", name = "COFINSST", scope = TNFe.InfNFe.Det.Imposto.class)
    public JAXBElement<TNFe.InfNFe.Det.Imposto.COFINSST> createTNFeInfNFeDetImpostoCOFINSST(TNFe.InfNFe.Det.Imposto.COFINSST value) {
        return new JAXBElement<>(_TNFeInfNFeDetImpostoCOFINSST_QNAME, TNFe.InfNFe.Det.Imposto.COFINSST.class, TNFe.InfNFe.Det.Imposto.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TNFe.InfNFe.Det.Imposto.ICMSUFDest }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link TNFe.InfNFe.Det.Imposto.ICMSUFDest }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.portalfiscal.inf.br/nfe", name = "ICMSUFDest", scope = TNFe.InfNFe.Det.Imposto.class)
    public JAXBElement<TNFe.InfNFe.Det.Imposto.ICMSUFDest> createTNFeInfNFeDetImpostoICMSUFDest(TNFe.InfNFe.Det.Imposto.ICMSUFDest value) {
        return new JAXBElement<>(_TNFeInfNFeDetImpostoICMSUFDest_QNAME, TNFe.InfNFe.Det.Imposto.ICMSUFDest.class, TNFe.InfNFe.Det.Imposto.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TIS }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link TIS }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.portalfiscal.inf.br/nfe", name = "IS", scope = TNFe.InfNFe.Det.Imposto.class)
    public JAXBElement<TIS> createTNFeInfNFeDetImpostoIS(TIS value) {
        return new JAXBElement<>(_TNFeInfNFeDetImpostoIS_QNAME, TIS.class, TNFe.InfNFe.Det.Imposto.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TTribNFe }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link TTribNFe }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.portalfiscal.inf.br/nfe", name = "IBSCBS", scope = TNFe.InfNFe.Det.Imposto.class)
    public JAXBElement<TTribNFe> createTNFeInfNFeDetImpostoIBSCBS(TTribNFe value) {
        return new JAXBElement<>(_TNFeInfNFeDetImpostoIBSCBS_QNAME, TTribNFe.class, TNFe.InfNFe.Det.Imposto.class, value);
    }

}
