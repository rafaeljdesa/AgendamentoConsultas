package br.com.unicarioca.agenda.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.unicarioca.agenda.dao.ConsultaDAO;
import br.com.unicarioca.agenda.dao.ConvenioDAO;
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
	private Medico medico;
	private Paciente paciente;
	private MedicoDAO medicoDAO;
	private PacienteDAO pacienteDAO;
	private ConvenioDAO convenioDAO;
	private static String inserir_editar = "consulta.jsp";
	private static String lista_consulta = "listaConsultas.jsp";
    
    public ConsultaController() {
        super();
        consulta = new Consulta();
        consultaDAO = new ConsultaDAO();
        medicoDAO = new MedicoDAO();
        medico = new Medico();
        paciente = new Paciente();
        pacienteDAO = new PacienteDAO();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String forward = "";
		String action = request.getParameter("action");
		
		if(action.equalsIgnoreCase("delete")) {
			consulta = new Consulta();
			int idConsulta = Integer.parseInt(request.getParameter("id"));
			consulta.setId(idConsulta);
			consultaDAO.deletaConsulta(consulta);
			forward = lista_consulta;
			try {
				request.setAttribute("consultas", consultaDAO.listaConsultas());
			} catch (ParseException e) {
				
				e.printStackTrace();
			}
		}else if(action.equalsIgnoreCase("edit")) {
			forward = inserir_editar;
			int idConsulta = Integer.parseInt(request.getParameter("id"));
			try {
				consulta = consultaDAO.buscaconsultaPorId(idConsulta);
			} catch (ParseException e) {
				
				e.printStackTrace();
			}
			request.setAttribute("medicos", medicoDAO.listaMedicos());
			request.setAttribute("pacientes", pacienteDAO.listaPacientes());
			request.setAttribute("consulta", consulta);
			
		}else if(action.equalsIgnoreCase("listaConsultas")) {
			consulta = new Consulta();
			forward = lista_consulta;
			try {
				request.setAttribute("consultas", consultaDAO.listaConsultas());
			} catch (ParseException e) {
				
				e.printStackTrace();
			}
		}else if(action.equalsIgnoreCase("selecionaMedico")) {			
			medico = medicoDAO.buscaMedicoPorId(Integer.parseInt(request.getParameter("id")));
			consulta.setMedico(medico);
			request.setAttribute("consulta", consulta);
		}else if(action.equalsIgnoreCase("selecionaPaciente")) {
			paciente = pacienteDAO.buscaPacientePorId(Integer.parseInt(request.getParameter("id")));
			convenioDAO = new ConvenioDAO();
			paciente.setConvenio(convenioDAO.buscaConvenioPorPaciente(paciente));
			consulta.setPaciente(paciente);
			request.setAttribute("consulta", consulta);
		}else {
			
			forward = inserir_editar;
			request.setAttribute("medicos", medicoDAO.listaMedicos());
			request.setAttribute("pacientes", pacienteDAO.listaPacientes());
		}
			
		RequestDispatcher view = request.getRequestDispatcher(forward);
		view.forward(request, response);
		
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//		consulta.setId(Integer.parseInt(request.getParameter("id")));
		
		try {
			Date data = (Date) new SimpleDateFormat("dd/MM/yyyy HH:mm").parse(request.getParameter("data"));
			consulta.setData(data);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if(request.getParameter("id") == "" || request.getParameter("id").equals("0")){
			consultaDAO.adicionaConsulta(consulta);
		}else {
			consulta.setId(Integer.parseInt(request.getParameter("id")));
			consultaDAO.atualizaConsulta(consulta);
		}
		consulta = new Consulta();
		RequestDispatcher view = request.getRequestDispatcher(lista_consulta);
        try {
			request.setAttribute("consultas", consultaDAO.listaConsultas());
		} catch (ParseException e) {
			
			e.printStackTrace();
		}
        view.forward(request, response);
		
		//doGet(request, response);
	}

}
