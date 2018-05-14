package br.com.unicarioca.agenda.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.unicarioca.agenda.model.Consulta;
import br.com.unicarioca.agenda.model.Convenio;
import br.com.unicarioca.agenda.model.Medico;
import br.com.unicarioca.agenda.model.Paciente;
import br.com.unicarioca.agenda.util.DbUtil;

public class ConsultaDAO {
	private Connection conexao;
	
	public ConsultaDAO() {
		conexao = DbUtil.getConnection();
	}
	
	public void adicionaConsulta(Consulta consulta) {
		try {
            PreparedStatement preparedStatement = (PreparedStatement) conexao.prepareStatement("insert into consulta(id_medico,id_paciente,data) values (?, ?, ?)");
            // Parameters start with 1
            preparedStatement.setInt(1, consulta.getMedico().getId());
            preparedStatement.setInt(2, consulta.getPaciente().getId());
            java.sql.Timestamp d = new java.sql.Timestamp(consulta.getData().getTime());
            preparedStatement.setString(3, d.toString());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
	}
	
	public void deletaConsulta(Consulta consulta) {
	    try {
            PreparedStatement preparedStatement = (PreparedStatement) conexao.prepareStatement("delete from consulta where id=?");
            // Parameters start with 1
            preparedStatement.setInt(1, consulta.getId());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
	}
	
	public void atualizaConsulta(Consulta consulta) {
		try {
            PreparedStatement preparedStatement = (PreparedStatement) conexao.prepareStatement("update consulta set id_medico=?, id_paciente=?, data=?" + "where id=?");
            // Parameters start with 1
            preparedStatement.setInt(1, consulta.getMedico().getId());
            preparedStatement.setInt(2, consulta.getPaciente().getId());
            java.sql.Timestamp d = new java.sql.Timestamp(consulta.getData().getTime());
            preparedStatement.setString(3, d.toString());
            preparedStatement.setInt(4, consulta.getId());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
	}
	
	public Consulta buscaconsultaPorId(int id) throws ParseException {
		Consulta consulta = new Consulta();
		 try {
	            PreparedStatement preparedStatement = (PreparedStatement) conexao.prepareStatement("select * from consulta where id=?");
	            preparedStatement.setInt(1, id);
	            ResultSet rs = preparedStatement.executeQuery();

	            if (rs.next()) {
	            	consulta.setId(rs.getInt("id"));
	            	Date data = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(rs.getString("data"));
	                consulta.setData(data);
	            	MedicoDAO medicoDAO = new MedicoDAO();
	        		Medico medico = new Medico();
	        		medico = medicoDAO.buscaMedicoPorId(rs.getInt("id_medico"));                
	                consulta.setMedico(medico);	        		
	        		PacienteDAO pacienteDAO = new PacienteDAO();
	        		Paciente paciente = new Paciente(); 
	        		paciente = pacienteDAO.buscaPacientePorId(rs.getInt("id_paciente"));
	        		ConvenioDAO convenioDAO = new ConvenioDAO();
	        		paciente.setConvenio(convenioDAO.buscaConvenioPorPaciente(paciente));
	                consulta.setPaciente(paciente);
	         
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }

		
		return consulta;
	}
	
	public List<Consulta> listaConsultas() throws ParseException {
		List<Consulta> consultas = new ArrayList<Consulta>();
		MedicoDAO medicoDAO = new MedicoDAO();
		Medico medico = new Medico();
		PacienteDAO pacienteDAO = new PacienteDAO();
		Paciente paciente = new Paciente(); 
		Convenio convenio = new Convenio();
		ConvenioDAO convenioDAO = new ConvenioDAO();
		
		try {
            Statement statement = (Statement) conexao.createStatement();
            ResultSet rs = statement.executeQuery("select * from consulta");
            while (rs.next()) {
                Consulta consulta = new Consulta();
                consulta.setId(rs.getInt("id"));              
                medico = medicoDAO.buscaMedicoPorId(rs.getInt("id_medico"));                
                consulta.setMedico(medico);
                paciente = pacienteDAO.buscaPacientePorId(rs.getInt("id_paciente"));
                paciente.setConvenio(convenioDAO.buscaConvenioPorPaciente(paciente));
                consulta.setPaciente(paciente);
                Date data = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(rs.getString("data"));
                consulta.setData(data);
                consultas.add(consulta);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
		
		return consultas;
		
	}
}
