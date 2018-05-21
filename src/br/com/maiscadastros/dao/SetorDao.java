package br.com.maiscadastros.dao;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import br.com.maiscadastros.model.Setor;

public class SetorDao {
	private Session session;

	/**
	 * @param session
	 *            the session to set
	 */
	public void setSession(Session session) {
		this.session = session;
	}

	public Setor create(Setor setor) {

		this.session.save(setor);

		return null;
	}

	public Setor recovery(Setor setor) {

		return 	 (Setor) this.session.get(Setor.class, setor.getId());
	}

	public Setor recoveryByNome(String nome) {

		Setor setor = (Setor) this.session.get(Setor.class, nome);

		return setor;

	}

	public Setor update(Setor setor) {
		
		this.session.update(setor);
		
		return setor;
	}

	public void delete(Setor setor) {
		
		this.session.delete(setor);
	}

	@SuppressWarnings("unchecked")
	public List<Setor> search() {

		List<Setor> lista = new ArrayList<>();

		lista = this.session.createCriteria(Setor.class).list();

		return lista;
	}

	@SuppressWarnings("unchecked")
	public List<Setor> searchByNome(String nome) {

		List<Setor> lista = null;

		Criteria criteria = this.session.createCriteria(Setor.class).add(Restrictions.like("nome", nome));

		lista = (List<Setor>) criteria.list();

		return lista;

	}
}
