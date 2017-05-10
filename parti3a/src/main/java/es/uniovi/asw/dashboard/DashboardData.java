package es.uniovi.asw.dashboard;

import java.util.List;

import es.uniovi.asw.model.Comment;
import es.uniovi.asw.model.Suggestion;

public class DashboardData {
	private List<Suggestion> sugerencias;
	private List<Comment> comentarios;
	
	public DashboardData(List<Suggestion> sugerencias, List<Comment> comentarios) {
		this.sugerencias = sugerencias;
		this.comentarios = comentarios;
	}
	
	public List<Suggestion> getSugerencias() {
		return sugerencias;
	}
	
	public List<Comment> getComentarios() {
		return comentarios;
	}
}
