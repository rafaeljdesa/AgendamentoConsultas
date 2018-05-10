package br.com.unicarioca.agenda.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.cj.jdbc.PreparedStatement;

import br.com.unicarioca.agenda.model.Convenio;
import br.com.unicarioca.agenda.model.Paciente;
import br.com.unicarioca.agenda.util.DbUtil;

public class ConvenioDAO {
	
	private Connection conexao;

    public ConvenioDAO() {
        conexao = DbUtil.getConnection();
    }
    
    public Convenio buscaConvenioPorPaciente(Paciente paciente) {
    	Convenio convenio = new Convenio();
    	try {
            PreparedStatement preparedStatement = (PreparedStatement) conexao.prepareStatement("select * from convenio where id_paciente=?");
            preparedStatement.setInt(1, paciente.getId());
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
               convenio.setId(rs.getInt("id"));
               convenio.setNome(rs.getString("nome"));
               PacienteDAO pacienteDAO = new PacienteDAO();
               paciente = pacienteDAO.buscaPacientePorId(rs.getInt("id_paciente"));
               convenio.setPaciente(paciente);
         
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    	return convenio;
    }
    
    
    
}
