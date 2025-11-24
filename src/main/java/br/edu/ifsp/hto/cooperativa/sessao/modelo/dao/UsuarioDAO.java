package br.edu.ifsp.hto.cooperativa.sessao.modelo.dao;

import br.edu.ifsp.hto.cooperativa.ConnectionFactory;
import br.edu.ifsp.hto.cooperativa.sessao.modelo.vo.UsuarioVO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {

    public UsuarioVO inserir(UsuarioVO usuario) throws SQLException {
        String sql = """
            INSERT INTO usuario (
                associado_id,
                nome_usuario,
                senha,
                tipo_usuario,
                ativo
            )
            VALUES (?, ?, ?, ?, ?)
            RETURNING
                id,
                associado_id,
                nome_usuario,
                senha,
                tipo_usuario,
                ativo
        """;

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setLong(1, usuario.getAssociadoId());
            ps.setString(2, usuario.getNomeUsuario());
            ps.setString(3, usuario.getSenha());
            ps.setShort(4, usuario.getTipoUsuario());
            ps.setBoolean(5, usuario.getAtivo());

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    usuario.setId(rs.getLong("id"));
                    usuario.setAssociadoId(rs.getLong("associado_id"));
                    usuario.setNomeUsuario(rs.getString("nome_usuario"));
                    usuario.setSenha(rs.getString("senha"));
                    usuario.setTipoUsuario(rs.getShort("tipo_usuario"));
                    usuario.setAtivo(rs.getBoolean("ativo"));
                }
            }
        }

        return usuario;
    }

    public UsuarioVO atualizar(UsuarioVO usuario) throws SQLException {
        String sql = """
            UPDATE usuario
            SET
                associado_id = ?,
                nome_usuario = ?,
                senha = ?,
                tipo_usuario = ?,
                ativo = ?
            WHERE id = ?
        """;

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setLong(1, usuario.getAssociadoId());
            ps.setString(2, usuario.getNomeUsuario());
            ps.setString(3, usuario.getSenha());
            ps.setShort(4, usuario.getTipoUsuario());
            ps.setBoolean(5, usuario.getAtivo());
            ps.setLong(6, usuario.getId());

            ps.executeUpdate();
        }

        return buscarPorId(usuario.getId());
    }

    public UsuarioVO buscarPorNomeUsuario(String nomeUsuario) throws SQLException {
        String sql = """
            SELECT
                id,
                associado_id,
                nome_usuario,
                senha,
                tipo_usuario,
                ativo
            FROM usuario
            WHERE nome_usuario = ?
        """;

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, nomeUsuario);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    UsuarioVO usuario = new UsuarioVO();
                    usuario.setId(rs.getLong("id"));
                    usuario.setAssociadoId(rs.getLong("associado_id"));
                    usuario.setNomeUsuario(rs.getString("nome_usuario"));
                    usuario.setSenha(rs.getString("senha"));
                    usuario.setTipoUsuario(rs.getShort("tipo_usuario"));
                    usuario.setAtivo(rs.getBoolean("ativo"));
                    return usuario;
                }
            }
        }
        return null;
    }

    public UsuarioVO buscarPorId(Long id) throws SQLException {
        String sql = """
            SELECT
                id,
                associado_id,
                nome_usuario,
                senha,
                tipo_usuario,
                ativo
            FROM usuario
            WHERE id = ?
        """;

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setLong(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    UsuarioVO usuario = new UsuarioVO();
                    usuario.setId(rs.getLong("id"));
                    usuario.setAssociadoId(rs.getLong("associado_id"));
                    usuario.setNomeUsuario(rs.getString("nome_usuario"));
                    usuario.setSenha(rs.getString("senha"));
                    usuario.setTipoUsuario(rs.getShort("tipo_usuario"));
                    usuario.setAtivo(rs.getBoolean("ativo"));
                    return usuario;
                }
            }
        }

        return null;
    }

    public List<UsuarioVO> listarTodos() throws SQLException {
        String sql = """
            SELECT
                id,
                associado_id,
                nome_usuario,
                senha,
                tipo_usuario,
                ativo
            FROM usuario
            ORDER BY id
        """;

        List<UsuarioVO> lista = new ArrayList<>();

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                UsuarioVO usuario = new UsuarioVO();
                usuario.setId(rs.getLong("id"));
                usuario.setAssociadoId(rs.getLong("associado_id"));
                usuario.setNomeUsuario(rs.getString("nome_usuario"));
                usuario.setSenha(rs.getString("senha"));
                usuario.setTipoUsuario(rs.getShort("tipo_usuario"));
                usuario.setAtivo(rs.getBoolean("ativo"));

                lista.add(usuario);
            }
        }

        return lista;
    }

    public void remover(Long id) throws SQLException {
        String sql = """
            UPDATE usuario
            SET ativo = false
            WHERE id = ?
        """;

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setLong(1, id);
            ps.executeUpdate();
        }
    }
}
