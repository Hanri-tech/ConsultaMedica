package com.projeto.hospital.dao;

import com.projeto.hospital.configBD.ConexaoBD;
import com.projeto.hospital.models.Especialidade;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EspecialidadeDAO {

    Connection conexao = new ConexaoBD().getConexao();

    public EspecialidadeDAO() throws SQLException {}


    public void insert(Especialidade especialidade) throws SQLException {
        String sql = "INSERT INTO ESPECIALISTA (DESCRICAO) VALUES (?)";
        PreparedStatement ps = conexao.prepareStatement(sql);
        ps.setString(1, especialidade.getDescricao());
        ps.execute();
        ps.close();
    }

    public void delete(Especialidade especialidade) throws SQLException {
        String sql = "DELETE FROM ESPECIALISTA WHERE ID_ESP = ?";
        PreparedStatement ps = conexao.prepareStatement(sql);
        ps.setInt(1,especialidade.getId());
        ps.execute();
        ps.close();
    }

    public List<Especialidade> findAll() throws SQLException {
        List<Especialidade> especialidades = new ArrayList<>();
        String sql = "SELECT * FROM ESPECIALISTA";
        PreparedStatement ps = conexao.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            Especialidade especialidade = new Especialidade();
            especialidade.setId(rs.getInt("ID_ESP"));
            especialidade.setDescricao(rs.getString("DESCRICAO"));
            especialidades.add(especialidade);
        }
        return especialidades;
    }

    public Especialidade findOne(Integer id) throws SQLException {
        Especialidade especialidade = new Especialidade();
        String sql = "SELECT * FROM ESPECIALISTA WHERE ID_ESP = ?";
        PreparedStatement ps = conexao.prepareStatement(sql);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            especialidade.setId(rs.getInt("ID_ESP"));
            especialidade.setDescricao(rs.getString("DESCRICAO"));
        }
        return especialidade;
    }

    public void update(Especialidade especialidade) throws SQLException {
        String sql = "UPDATE ESPECIALISTA " +
                "SET DESCRICAO = ?" +
                " WHERE IDCAND = ?";
        PreparedStatement ps = conexao.prepareStatement(sql);
        ps.setString(1, especialidade.getDescricao());
        ps.setInt(2, especialidade.getId());
        ps.execute();
        ps.close();
    }
}
