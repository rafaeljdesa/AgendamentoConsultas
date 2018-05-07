package br.com.unicarioca.agenda.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.cj.jdbc.PreparedStatement;

import br.com.unicarioca.agenda.model.Paciente;
import br.com.unicarioca.agenda.util.DbUtil;

public class PacienteDAO {
private Connection conexao;
	
	public PacienteDAO() {
		conexao = DbUtil.getConnection();
	}
	
	public Paciente buscaPacientePorId(int id) {
		Paciente paciente = new Paciente();
		 try {
	            PreparedStatement preparedStatement = (PreparedStatement) conexao.prepareStatement("select * from paciente where id=?");
	            preparedStatement.setInt(1, id);
	            ResultSet rs = preparedStatement.executeQuery();

	            if (rs.next()) {
	                paciente.setId(rs.getInt("id"));
	                paciente.setNome(rs.getString("nome"));
	                paciente.setCpf(rs.getString("cpf"));
	         
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }

		
		return paciente;
	}
}
