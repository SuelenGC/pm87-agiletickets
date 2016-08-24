package br.com.caelum.agiletickets.models;

import java.util.ArrayList;
import java.util.List;

public class Trem {
	private List<Vagao> vagoes = new ArrayList<Vagao>();
	private int capacidade;

	public boolean podemosReservar(int lugaresRequisitados) {
		int reservas = 0;
		for (Vagao vagao : vagoes) {
			reservas += vagao.lugaresReservados();
		}
		return capacidade - reservas > lugaresRequisitados;
	}

}