package br.com.unicarioca.agenda.teste;

import java.text.ParseException;

import br.com.unicarioca.agenda.dao.ConsultaDAO;

public class Teste {

	public static void main(String[] args) throws ParseException {
		ConsultaDAO consulta = new ConsultaDAO();
		System.out.println(consulta.listaConsultas());

	}

}
