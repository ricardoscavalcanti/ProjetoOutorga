package br.com.pgo.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Order;

import br.com.pgo.domain.Ua;
import br.com.pgo.domain.Venda;
import br.com.pgo.util.HibernateUtil;

public class VendaDAO extends GenericDAO<Venda> {

	@SuppressWarnings("unchecked")
	public List<Ua> buscarUa(int numeroUa) {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();

		try {
			
			Query consulta = sessao.createQuery("from Ua where numeroUa=?");
			consulta.setParameter(0, numeroUa);
			List<Ua> resultado = consulta.list();
			return resultado;

		} catch (RuntimeException erro) {

			throw erro;

		} finally {
			sessao.close();
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Venda> listarOrdenadoOutorgante() {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();

		try {
			Criteria consulta = sessao.createCriteria(Venda.class);
			consulta.createAlias("outorgante", "o");
			consulta.addOrder(Order.asc("o.processoApac"));
			List<Venda> resultado = consulta.list();
			return resultado;

		} catch (RuntimeException erro) {

			throw erro;

		} finally {
			sessao.close();
		}

	}
	

}
