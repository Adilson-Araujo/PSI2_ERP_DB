package br.edu.ifsp.hto.cooperativa.vendas.controlador;

import br.edu.ifsp.hto.cooperativa.vendas.modelo.dao.AssociadoItemPedidoDAO;
import br.edu.ifsp.hto.cooperativa.vendas.modelo.vo.AssociadoItemPedidoVO;

import java.util.List;

public class AssociacaoController {

    private final AssociadoItemPedidoDAO dao;

    public AssociacaoController() {
        this.dao = new AssociadoItemPedidoDAO();
    }

    public String salvarAtribuicoes(Long itemPedidoId, List<AssociadoItemPedidoVO> atribuicoes) {

        if (itemPedidoId == null || itemPedidoId <= 0) {
            return "ID do item de pedido inválido.";
        }

        if (atribuicoes == null) {
            return "Lista de atribuições inválida.";
        }

        dao.deletarAtribuicoesPorItem(itemPedidoId);

        if (atribuicoes.isEmpty()) {
            return "Atribuições removidas. Nenhum produtor definido.";
        }

        int sucesso = 0;
        StringBuilder erros = new StringBuilder();

        for (AssociadoItemPedidoVO vo : atribuicoes) {

            if (vo == null) {
                erros.append("Valor de atribuição nulo.\n");
                continue;
            }

            vo.setItemPedidoId(itemPedidoId);

            String res = dao.salvarAtribuicao(vo);

            if ("sucesso".equalsIgnoreCase(res)) {
                sucesso++;
            } else {
                erros.append(res).append("\n");
            }
        }

        if (sucesso == 0) {
            return "Nenhuma atribuição salva:\n" + erros;
        }

        if (erros.length() > 0) {
            return "Parcialmente salvo: " + sucesso + " atribuições.\nFalhas:\n" + erros;
        }

        return "Atribuições salvas com sucesso. " + sucesso + " produtor(es).";
    }
}
