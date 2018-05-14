package br.com.unicarioca.agenda.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.mysql.cj.jdbc.PreparedStatement;

import br.com.unicarioca.agenda.model.Medico;
import br.com.unicarioca.agenda.util.DbUtil;

public class MedicoDAO {
	private Connection conexao;
	
	public MedicoDAO() {
		conexao = DbUtil.getConnection();
	}
	
	public Medico buscaMedicoPorId(int id) {
		Medico medico = new Medico();
		 try {
	            PreparedStatement preparedStatement = (PreparedStatement) conexao.prepareStatement("select * from medico where id=?");
	            preparedStatement.setInt(1, id);
	            ResultSet rs = preparedStatement.executeQuery();

	            if (rs.next()) {
	                medico.setId(rs.getInt("id"));
	                medico.setNome(rs.getString("nome"));
	                medico.setCrm(rs.getString("crm"));
	                medico.setEspecialidade(rs.getString("especialidade"));
	         
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }

		
		return medico;
	}
	
	public List<Medico> listaMedicos(){
		List<Medico> medicos = new ArrayList<Medico>();
		
		try {
            Statement statement = (Statement) conexao.createStatement();
            ResultSet rs = statement.executeQuery("select * from medico");
            while (rs.next()) {
                Medico medico = new Medico();
                medico.setId(rs.getInt("id")); 
                medico.setNome(rs.getString("nome"));
                medico.setCrm(rs.getString("crm"));     
                medico.setEspecialidade(rs.getString("especialidade"));                               
                medicos.add(medico);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
		
		return medicos;
	}
}
