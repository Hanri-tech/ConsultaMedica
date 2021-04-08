package com.projeto.hospital.dao;

import com.projeto.hospital.configBD.ConexaoBD;
import com.projeto.hospital.models.Especialidade;
import com.projeto.hospital.models.Medico;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MedicoDAO {

    Connection conexao = new ConexaoBD().getConexao();

    public MedicoDAO() throws SQLException {
    }

    public void insert(Medico medico) throws SQLException {
        String sql = "INSERT INTO MEDICO (NOME, ID_ESP, CRM, CPF) VALUES (?, ?, ?, ?)";
        PreparedStatement ps = conexao.prepareStatement(sql);
        ps.setString(1, medico.getNome());
        ps.setInt(2, medico.getIdEspecialidade());
        ps.setInt(3, medico.getCrm());
        ps.setString(4, medico.getCpf());
        ps.execute();
        ps.close();
    }

    public void delete(Medico medico) throws SQLException {
        String sql = "DELETE FROM MEDICO WHERE ID_MED = ?";
        PreparedStatement ps = conexao.prepareStatement(sql);
        ps.setInt(1, medico.getId());
        ps.execute();
        ps.close();
    }

    public List<Medico> findAll() throws SQLException {
        List<Medico> medicos = new ArrayList<>();
        String sql = "SELECT M.ID_MED, M.NOME, E.ID_ESP, E.DESCRICAO, M.CRM, M.CPF" +
                " FROM MEDICO M INNER JOIN ESPECIALISTA E" +
                " ON M.ID_ESP = E.ID_ESP";
        PreparedStatement ps = conexao.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            Medico medico = new Medico();
            Especialidade especialidade = new Especialidade();
            medico.setId(rs.getInt("ID_MED"));
            medico.setNome(rs.getString("NOME"));
            especialidade.setId(rs.getInt("ID_ESP"));
            especialidade.setDescricao(rs.getString("DESCRICAO"));
            medico.setCrm(rs.getInt("CRM"));
            medico.setCpf(rs.getString("CPF"));
            medico.setEspecialidade(especialidade);
            medicos.add(medico);
        }
        return medicos;
    }

    public Medico getMedico(Integer id) throws SQLException {
        Medico medico = new Medico();
        String sql = "SELECT * FROM MEDICO WHERE ID_MED = ?";
        PreparedStatement ps = conexao.prepareStatement(sql);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            medico.setId(rs.getInt("ID_MED"));
            medico.setNome(rs.getString("NOME"));
            medico.setCrm(rs.getInt("CRM"));
            medico.setCpf(rs.getString("CPF"));
        }
        return medico;
    }
}
