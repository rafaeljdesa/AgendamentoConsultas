package br.com.unicarioca.agenda.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.unicarioca.agenda.dao.ConsultaDAO;
import br.com.unicarioca.agenda.dao.MedicoDAO;
import br.com.unicarioca.agenda.dao.PacienteDAO;
import br.com.unicarioca.agenda.model.Consulta;
import br.com.unicarioca.agenda.model.Medico;
import br.com.unicarioca.agenda.model.Paciente;

/**
 * Servlet implementation class ConsultaController
 */
@WebServlet("/ConsultaController")
public class ConsultaController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Consulta consulta;
	private ConsultaDAO consultaDAO;
	private static String inserir_editar = "consulta.jsp";
	private static String lista_consulta = "listaConsultas.jsp";
    
    public ConsultaController() {
        super();
        consulta = new Consulta();
        consultaDAO = new ConsultaDAO();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String forward = "";
		String action = request.getParameter("action");
		
		if(action.equalsIgnoreCase("delete")) {
			int idConsulta = Integer.parseInt(request.getParameter("id"));
			consulta.setId(idConsulta);
			consultaDAO.deletaConsulta(consulta);
			forward = lista_consulta;
			request.setAttribute("consultas", consultaDAO.listaConsultas());
		}else if(action.equalsIgnoreCase("edit")) {
			forward = inserir_editar;
			int idConsulta = Integer.parseInt(request.getParameter("id"));
			consulta = consultaDAO.buscaconsultaPorId(idConsulta);
			request.setAttribute("consulta", consulta);
			
		}else if(action.equalsIgnoreCase("listaConsultas")) {
			forward = lista_consulta;
			request.setAttribute("consultas", consultaDAO.listaConsultas());
		}else {
			forward = inserir_editar;
		}
		
		RequestDispatcher view = request.getRequestDispatcher(forward);
		view.forward(request, response);
		
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		consulta = new Consulta();
		consulta.setId(Integer.parseInt(request.getParameter("id")));
		
		try {
			Date data = new SimpleDateFormat("MM/dd/yyyy").parse(request.getParameter("data"));
			consulta.setData(data);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		Medico medico = new Medico();
		MedicoDAO medicoDAO = new MedicoDAO();
		medico = medicoDAO.buscaMedicoPorId(Integer.parseInt(request.getParameter("id_medico")));
		
		consulta.setMedico(medico);
		
		Paciente paciente = new Paciente();
		PacienteDAO pacienteDAO = new PacienteDAO();
		
		paciente = pacienteDAO.buscaPacientePorId(Integer.parseInt(request.getParameter("id_paciente")));
		
		consulta.setPaciente(paciente);
		
		if(request.getParameter("id") == ""){
			consultaDAO.adicionaConsulta(consulta, medico, paciente);
		}else {
			consultaDAO.atualizaConsulta(consulta, medico, paciente);
		}
		
		RequestDispatcher view = request.getRequestDispatcher(lista_consulta);
        request.setAttribute("consultas", consultaDAO.listaConsultas());
        view.forward(request, response);
		
		//doGet(request, response);
	}

}
