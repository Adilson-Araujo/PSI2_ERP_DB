package br.edu.ifsp.hto.cooperativa.notafiscal.controlador;

import br.edu.ifsp.hto.cooperativa.ConnectionFactory;
import br.edu.ifsp.hto.cooperativa.notafiscal.modelo.negocios.NegociosFactory;
import org.apache.commons.collections.functors.ConstantTransformer;

public class ControladorBase {
    private NegociosFactory _negociosFactory;
    protected NegociosFactory negociosFactory()
    {
        if (_negociosFactory == null)
            _negociosFactory = NegociosFactory.getInstance();
        return _negociosFactory;
    }

    protected <T> T executarTransacao(TransactionalAction<T> acao) {
        var conn = ConnectionFactory.getConnection();
        try {
            conn.setAutoCommit(false);

            T result = acao.execute();

            conn.commit();
            return result;

        } catch (Exception ex) {
            try { conn.rollback(); } catch (Exception e) {}
            throw new RuntimeException(ex);
        } finally {
            try { conn.setAutoCommit(true); } catch (Exception e) {}
        }
    }

    protected void executarTransacao(TransactionalVoidAction acao) {
        var conn = ConnectionFactory.getConnection();
        try {
            conn.setAutoCommit(false);

            acao.execute();

            conn.commit();

        } catch (Exception ex) {
            try { conn.rollback(); } catch (Exception e) {}
            throw new RuntimeException(ex);
        } finally {
            try { conn.setAutoCommit(true); } catch (Exception e) {}
        }
    }
}

@FunctionalInterface
interface TransactionalAction<T> {
    T execute() throws Exception;
}

@FunctionalInterface
interface TransactionalVoidAction {
    void execute() throws Exception;
}
