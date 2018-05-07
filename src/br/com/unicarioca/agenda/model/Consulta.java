package br.com.unicarioca.agenda.model;

import java.util.Date;

public class Consulta {
	private int id;
	private Medico medico;
	private Paciente paciente;
	private Date data;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Medico getMedico() {
		return medico;
	}
	public void setMedico(Medico medico) {
		this.medico = medico;
	}
	public Paciente getPaciente() {
		return paciente;
	}
	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	@Override
	public String toString() {
		return "Consulta [id=" + id + ", medico=" + medico + ", paciente=" + paciente + ", data=" + data + ", getId()="
				+ getId() + ", getMedico()=" + getMedico() + ", getPaciente()=" + getPaciente() + ", getData()="
				+ getData() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()="
				+ super.toString() + "]";
	}
	
	
	
	
}
