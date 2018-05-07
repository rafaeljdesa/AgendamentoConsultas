package br.com.unicarioca.agenda.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.cj.api.jdbc.Statement;
import com.mysql.cj.jdbc.PreparedStatement;

import br.com.unicarioca.agenda.model.Consulta;
import br.com.unicarioca.agenda.model.Medico;
import br.com.unicarioca.agenda.model.Paciente;
import br.com.unicarioca.agenda.util.DbUtil;

public class ConsultaDAO {
	private Connection conexao;
	
	public ConsultaDAO() {
		conexao = DbUtil.getConnection();
	}
	
	public void adicionaConsulta(Consulta consulta, Medico medico, Paciente paciente) {
		try {
            PreparedStatement preparedStatement = (PreparedStatement) conexao.prepareStatement("insert into consulta(id_medico,id_paciente,data) values (?, ?, ?)");
            // Parameters start with 1
            preparedStatement.setInt(1, medico.getId());
            preparedStatement.setInt(2, paciente.getId());
            preparedStatement.setDate(3, (Date) (consulta.getData()));
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
	
	public void atualizaConsulta(Consulta consulta, Medico medico, Paciente paciente) {
		try {
            PreparedStatement preparedStatement = (PreparedStatement) conexao.prepareStatement("update consulta set id_medico=?, id_paciente=?, data=?" + "where id=?");
            // Parameters start with 1
            preparedStatement.setInt(1, medico.getId());
            preparedStatement.setInt(2, paciente.getId());
            preparedStatement.setDate(3, (Date) consulta.getData());
            preparedStatement.setInt(5, consulta.getId());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
	}
	
	public List<Consulta> listaConsultas(){
		List<Consulta> consultas = new ArrayList<Consulta>();
		MedicoDAO medicoDAO = new MedicoDAO();
		Medico medico = new Medico();
		PacienteDAO pacienteDAO = new PacienteDAO();
		Paciente paciente = new Paciente(); 
		
		try {
            Statement statement = (Statement) conexao.createStatement();
            ResultSet rs = statement.executeQuery("select * from consulta");
            while (rs.next()) {
                Consulta consulta = new Consulta();
                consulta.setId(rs.getInt("id"));              
                medico = medicoDAO.buscaMedicoPorId(rs.getInt("id_medico"));                
                consulta.setMedico(medico);
                paciente = pacienteDAO.buscaPacientePorId(rs.getInt("id_paciente"));
                consulta.setPaciente(paciente);
                consulta.setData(rs.getDate("data"));
                consultas.add(consulta);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
		
		return consultas;
		
	}
}
