package br.com.unicarioca.agenda.dao;

import java.sql.Connection;

import br.com.unicarioca.agenda.model.Convenio;
import br.com.unicarioca.agenda.util.DbUtil;

public class ConvenioDAO {
	
	private Connection conexao;

    public ConvenioDAO() {
        conexao = DbUtil.getConnection();
    }
    
    public void buscaConvenioPorId(int id) {
    	Convenio convenio = new Convenio();
    }
    
    
    
}
