package br.edu.ifsp.hto.cooperativa.notafiscal.modelo.nfeSchema;

import java.util.Objects;
import java.util.regex.Pattern;

/**
 * Representa e valida a Chave de Acesso da NFe (44 dígitos).
 *
 * Estrutura (44 dígitos):
 * cUF(2) + AAMM(4) + CNPJ(14) + mod(2) + serie(3) + nNF(9) + tpEmis(1) + cNF(8) + cDV(1)
 *
 * cDV = cálculo módulo 11 sobre os 43 primeiros dígitos usando pesos 2..9 repetidos.
 */
public final class ChaveAcesso {

    private static final Pattern ONLY_DIGITS = Pattern.compile("\\D+");
    private static final Pattern CHAVE_PATTERN = Pattern.compile("^(\\d{44})$");

    private final String chave44; // sempre 44 dígitos, sem máscara

    private ChaveAcesso(String chave44) {
        if (chave44 == null) throw new IllegalArgumentException("Chave nula");
        String cleaned = ONLY_DIGITS.matcher(chave44).replaceAll("");
        if (!CHAVE_PATTERN.matcher(cleaned).matches()) {
            throw new IllegalArgumentException("Chave deve ter 44 dígitos. Recebido: " + cleaned.length());
        }
        if (!isValidChecksum(cleaned)) {
            throw new IllegalArgumentException("DV inválido para chave: " + cleaned);
        }
        this.chave44 = cleaned;
    }

    /**
     * Cria a chave a partir dos componentes (sem o DV).
     * Retorna uma ChaveAcessoNFe com o DV calculado.
     *
     * @param cUF 2 dígitos (código da UF)
     * @param aaMm AAMM (4 dígitos)
     * @param cnpj 14 dígitos
     * @param modelo 2 dígitos (ex: 55)
     * @param serie 1-3 dígitos (será preenchido à esquerda com zeros para 3)
     * @param numero 1-9 dígitos (será preenchido à esquerda com zeros para 9)
     * @param tpEmis 1 dígito
     * @param cNF 8 dígitos (código numérico)
     * @return ChaveAcessoNFe completa (44 dígitos)
     */
    public static ChaveAcesso fromComponents(String cUF, String aaMm, String cnpj,
                                             String modelo, int serie, long numero,
                                             String tpEmis, String cNF) {
        String sUF = padLeftOnlyDigits(cUF, 2);
        String sAAMM = padLeftOnlyDigits(aaMm, 4);
        String sCnpj = padLeftOnlyDigits(cnpj, 14);
        String sModelo = padLeftOnlyDigits(modelo, 2);
        String sSerie = String.format("%03d", serie);
        String sNumero = String.format("%09d", numero);
        String sTpEmis = padLeftOnlyDigits(tpEmis, 1);
        String sCNF = padLeftOnlyDigits(cNF, 8);

        String partial = sUF + sAAMM + sCnpj + sModelo + sSerie + sNumero + sTpEmis + sCNF; // 43 dígitos
        if (partial.length() != 43) {
            throw new IllegalArgumentException("Componentes formaram " + partial.length() + " dígitos (esperado 43)");
        }
        char dv = calcularDV(partial);
        return new ChaveAcesso(partial + dv);
    }

    /**
     * Faz parse de uma string que contenha os 44 dígitos (aceita máscara/espacos).
     */
    public static ChaveAcesso parse(String chave) {
        return new ChaveAcesso(chave);
    }

    public String getChave44() {
        return chave44;
    }

    /**
     * Retorna a chave formatada em grupos (exemplo: 00000000000000000000000000000000000000000000)
     * Você pode customizar o formato se quiser.
     */
    public String formatPlain() {
        return chave44;
    }

    /**
     * Formata com máscara igual ao padrão que alguns sistemas exibem:
     * "XXXX XXXX XXXX XXXX XXXX XXXX XXXX XXXX XXXX XXXX XXXX" (4 em 4)
     */
    public String format4x11() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 44; i++) {
            sb.append(chave44.charAt(i));
            if ((i + 1) % 4 == 0 && i != 43) sb.append(' ');
        }
        return sb.toString();
    }

    public String getCUF() { return chave44.substring(0, 2); }
    public String getAAMM() { return chave44.substring(2, 6); }
    public String getCNPJ() { return chave44.substring(6, 20); }
    public String getModelo() { return chave44.substring(20, 22); }
    public int getSerie() { return Integer.parseInt(chave44.substring(22, 25)); }
    public long getNumero() { return Long.parseLong(chave44.substring(25, 34)); }
    public String getTpEmis() { return chave44.substring(34, 35); }
    public String getCNF() { return chave44.substring(35, 43); }
    public char getDV() { return chave44.charAt(43); }

    @Override
    public String toString() {
        return format4x11();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ChaveAcesso that = (ChaveAcesso) o;
        return chave44.equals(that.chave44);
    }

    @Override
    public int hashCode() {
        return Objects.hash(chave44);
    }

    /* ------------------ helpers / validação dv ------------------ */

    private static boolean isValidChecksum(String chave44) {
        if (chave44 == null || chave44.length() != 44) return false;
        String base = chave44.substring(0, 43);
        char esperado = calcularDV(base);
        return esperado == chave44.charAt(43);
    }

    /**
     * Calcula o DV módulo 11 (padrão NFe):
     * - pesos 2..9 aplicados da direita para a esquerda
     * - soma dos produtos
     * - resto = soma % 11
     * - dv = 11 - resto; se dv == 0,1,10 -> dv = 0
     */
    private static char calcularDV(String base43) {
        if (base43 == null || base43.length() != 43)
            throw new IllegalArgumentException("Base para DV deve ter 43 dígitos");

        int soma = 0;
        int peso = 2;
        // percorre da direita (posição 42) para a esquerda (pos 0)
        for (int i = base43.length() - 1; i >= 0; i--) {
            int dig = base43.charAt(i) - '0';
            soma += dig * peso;
            peso++;
            if (peso > 9) peso = 2;
        }
        int resto = soma % 11;
        int dv = 11 - resto;
        if (dv == 0 || dv == 1 || dv == 10) dv = 0;
        return (char) ('0' + dv);
    }

    private static String padLeftOnlyDigits(String s, int length) {
        if (s == null) throw new IllegalArgumentException("Campo nulo");
        String cleaned = ONLY_DIGITS.matcher(s).replaceAll("");
        if (cleaned.length() > length) {
            throw new IllegalArgumentException("Campo com mais dígitos do que o permitido: " + cleaned);
        }
        return String.format("%1$" + length + "s", cleaned).replace(' ', '0');
    }
}