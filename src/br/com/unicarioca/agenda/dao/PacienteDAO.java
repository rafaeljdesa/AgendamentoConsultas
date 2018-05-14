package br.com.unicarioca.agenda.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.mysql.cj.jdbc.PreparedStatement;

import br.com.unicarioca.agenda.model.Convenio;
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
	
	public List<Paciente> listaPacientes(){
		List<Paciente> pacientes = new ArrayList<Paciente>();
		ConvenioDAO convenioDAO = new ConvenioDAO();
		
		try {
            Statement statement = (Statement) conexao.createStatement();
            ResultSet rs = statement.executeQuery("select * from paciente");
            while (rs.next()) {
            	Paciente paciente = new Paciente();
            	paciente.setId(rs.getInt("id")); 
            	paciente.setNome(rs.getString("nome"));
            	paciente.setCpf(rs.getString("cpf"));   
            	paciente.setConvenio(convenioDAO.buscaConvenioPorPaciente(paciente));                               
            	pacientes.add(paciente);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
		
		return pacientes;
	}
}
