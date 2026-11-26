package br.edu.ifsc.fln.dao;

import br.edu.ifsc.fln.model.Profissional;
import br.edu.ifsc.fln.util.Conexao;
import br.edu.ifsc.fln.util.DateFormatter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ProfissionalDAO {

    public void inserir(Profissional profissional) {

        String sql = "INSERT INTO profissional (nome, data_nascimento, telefone, especialidade, email, senha, cpf, endereco, " +
                "FK_UNIDADE_CLINICA_id_unidade_clinica) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try(Connection conn = Conexao.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, profissional.getNome());
            stmt.setObject(2, DateFormatter.formatarData(profissional.getDataNascimento()));
            stmt.setString(3, profissional.getTelefone());
            stmt.setString(4, profissional.getEspecialidade());
            stmt.setString(5, profissional.getEmail());
            stmt.setString(6, profissional.getSenha());
            stmt.setString(7, profissional.getCpf());
            stmt.setString(8, profissional.getEndereco());
            stmt.setInt(9, profissional.getUnidadeClinica().getIdUnidade());

            stmt.executeUpdate();
            System.out.println("Profissional inserido com sucesso!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
