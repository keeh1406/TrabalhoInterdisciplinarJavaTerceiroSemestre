package br.com.maiscadastros.dao;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import br.com.maiscadastros.model.Marca;

public class MarcaDao {
	private Session session;

	/**
	 * @param session
	 *            the session to set
	 */
	public void setSession(Session session) {
		this.session = session;
	}

	public Marca create(Marca marca) {

		this.session.save(marca);

		return null;
	}

	public Marca recovery(Marca marca) {

		return 	 (Marca) this.session.get(Marca.class, marca.getId());
	}

	public Marca recoveryByNome(String nome) {

		Marca marca = (Marca) this.session.get(Marca.class, nome);

		return marca;

	}

	public Marca update(Marca marca) {
		
		this.session.update(marca);
		
		return marca;
	}

	public void delete(Marca marca) {
		
		this.session.delete(marca);
	}

	@SuppressWarnings("unchecked")
	public List<Marca> search() {

		List<Marca> lista = new ArrayList<>();

		lista = this.session.createCriteria(Marca.class).list();

		return lista;
	}

	@SuppressWarnings("unchecked")
	public List<Marca> searchByNome(String nome) {

		List<Marca> lista = null;

		Criteria criteria = this.session.createCriteria(Marca.class).add(Restrictions.like("nome", nome));

		lista = (List<Marca>) criteria.list();

		return lista;

	}
}
