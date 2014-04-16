package br.com.caelum.agiletickets.persistencia;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.caelum.agiletickets.domain.Agenda;
import br.com.caelum.agiletickets.domain.Relogio;
import br.com.caelum.agiletickets.models.Espetaculo;
import br.com.caelum.agiletickets.models.Sessao;
import br.com.caelum.vraptor.ioc.Component;

@Component
public class JPAEspetaculoDao implements Agenda {

	private final EntityManager manager;
	private final Relogio relogio;

	public JPAEspetaculoDao(EntityManager manager, Relogio relogio) {
		this.manager = manager;
		this.relogio = relogio;
	}

	@Override
	public List<Espetaculo> espetaculos() {
		return manager.createQuery("select e from Espetaculo e", Espetaculo.class).getResultList();
	}

	@Override
	public void cadastra(Espetaculo espetaculo) {
		manager.persist(espetaculo);
	}

	@Override
	public Espetaculo espetaculo(Long espetaculoId) {
		return manager.find(Espetaculo.class, espetaculoId);
	}
	
	@Override
	public void agende(List<Sessao> sessoes) {
		for (Sessao sessao : sessoes) {
			manager.persist(sessao);
		}
	}
	
	@Override
	public List<Sessao> proximasSessoes(int maximo) {
		return manager.createQuery("select s from Sessao s where s.inicio > :hoje order by s.inicio", Sessao.class)
					.setParameter("hoje", relogio.agora())
					.setMaxResults(maximo)
					.getResultList();
	}

}