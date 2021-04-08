package com.projeto.hospital.dao;

import com.projeto.hospital.configBD.ConexaoBD;
import com.projeto.hospital.models.Paciente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PacienteDAO {

    Connection conexao = new ConexaoBD().getConexao();

    public PacienteDAO() throws SQLException {}


    public void insert(Paciente paciente) throws SQLException {
        String sql = "INSERT INTO PACIENTE (NOME, CPF) VALUES (?,?)";
        PreparedStatement ps = conexao.prepareStatement(sql);
        ps.setString(1, paciente.getNome());
        ps.setString(2, paciente.getCpf());
        ps.execute();
        ps.close();
    }

    public void delete(Paciente paciente) throws SQLException {
        String sql = "DELETE FROM PACIENTE WHERE ID_PAC = ?";
        PreparedStatement ps = conexao.prepareStatement(sql);
        ps.setInt(1, paciente.getId());
        ps.execute();
        ps.close();
    }

    public List<Paciente> findAll() throws SQLException {
        List<Paciente> pacientes = new ArrayList<>();
        String sql = "SELECT * FROM PACIENTE";
        PreparedStatement ps = conexao.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            Paciente paciente = new Paciente();
            paciente.setId(rs.getInt("ID_PAC"));
            paciente.setNome(rs.getString("NOME"));
            paciente.setCpf(rs.getString("CPF"));
            pacientes.add(paciente);
        }
        return pacientes;
    }

    public Paciente findOne(Integer id) throws SQLException {
        Paciente paciente = new Paciente();
        String sql = "SELECT * FROM PACIENTE WHERE ID_PAC = ?";
        PreparedStatement ps = conexao.prepareStatement(sql);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            paciente.setId(rs.getInt("ID_PAC"));
            paciente.setNome(rs.getString("NOME"));
            paciente.setCpf(rs.getString("CPF"));
        }
        return paciente;
    }

    public void update(Paciente paciente) throws SQLException {
        String sql = "UPDATE PACIENTE " +
                " SET NOME = ?" +
                " SET CPF = ?"+
                " WHERE ID_PAC = ?";
        PreparedStatement ps = conexao.prepareStatement(sql);
        ps.setString(1, paciente.getNome());
        ps.setString(2, paciente.getCpf());
        ps.setInt(3, paciente.getId());
        ps.execute();
        ps.close();
    }
}
