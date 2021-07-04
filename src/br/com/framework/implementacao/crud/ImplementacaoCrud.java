package br.com.framework.implementacao.crud;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import br.com.framework.hibernate.session.HibernateUtil;
import br.com.framework.interfac.crud.InterfaceCrud;

@Component
@Transactional
public class ImplementacaoCrud<T> implements InterfaceCrud<T> {

	private static final long serialVersionUID = 1L;

	private static SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
	
	@Autowired
	private JdbcTemplateImpl jdbcTemplate;
	
	@Autowired
	private SimpleJdbcTemplateImpl simpleJdbcTemplate;
	
	@Autowired
	private SimpleJdbcInsertImpl simpleJdbcInsert;
	
	@Autowired
	private SimpleJdbcCallImpl simpleJdbcCall;
	
	@Override
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}
	
	public void setJdbcTemplate(JdbcTemplateImpl jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	

	@Override
	public SimpleJdbcTemplate getSimpleJdbcTemplate() {
		return simpleJdbcTemplate;
	}
	
	public void setSimpleJdbcTemplate(SimpleJdbcTemplateImpl simpleJdbcTemplate) {
		this.simpleJdbcTemplate = simpleJdbcTemplate;
	}
	
	@Override
	public SimpleJdbcInsert getSimpleJdbcInsert() {
		return simpleJdbcInsert;
	}
	
	public void setSimpleJdbcInsert(SimpleJdbcInsertImpl simpleJdbcInsert) {
		this.simpleJdbcInsert = simpleJdbcInsert;
	}
	
	public SimpleJdbcCallImpl getSimpleJdbcCall() {
		return simpleJdbcCall;
	}

	public void setSimpleJdbcCall(SimpleJdbcCallImpl simpleJdbcCall) {
		this.simpleJdbcCall = simpleJdbcCall;
	}

	@Override
	public void save(T obj) throws Exception {
		validaSessionFactory();
		
		sessionFactory.getCurrentSession().save(obj);
		
		executeFlushSession();
	}

	@Override
	public void persist(T obj) throws Exception {
		validaSessionFactory();
		
		sessionFactory.getCurrentSession().persist(obj);
		
		executeFlushSession();
	}

	@Override
	public void saveOrUpdate(T obj) throws Exception {
		validaSessionFactory();
		
		sessionFactory.getCurrentSession().saveOrUpdate(obj);
		
		executeFlushSession();
	}

	@Override
	public void update(T obj) throws Exception {
		validaSessionFactory();
		
		sessionFactory.getCurrentSession().update(obj);
		
		executeFlushSession();
	}

	@Override
	public void delete(T obj) throws Exception {
		validaSessionFactory();
		
		sessionFactory.getCurrentSession().delete(obj);
		
		executeFlushSession();
	}

	@SuppressWarnings("unchecked")
	@Override
	public T merge(T obj) throws Exception {
		validaSessionFactory();
		
		obj = (T) sessionFactory.getCurrentSession().merge(obj);
		
		executeFlushSession();
		
		return obj;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> findList(Class<T> entidade) throws Exception {
		validaSessionFactory();
		
		StringBuilder query = new StringBuilder();
		query.append(" SELECT DISTINCT(entity) FROM ");
		query.append(entidade.getSimpleName()).append(" entity");
		
		List<T> lista = sessionFactory.getCurrentSession().createQuery(query.toString()).list();
		
		return lista;
	}

	@SuppressWarnings("unchecked")
	@Override
	public T findById(Class<T> entidade, Long id) throws Exception {
		validaSessionFactory();
		
		T obj = (T) sessionFactory.getCurrentSession().load(entidade, id);
		
		return obj;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> findListByQueryDinamica(String s) throws Exception {
		validaSessionFactory();
		
		List<T> lista = new ArrayList<>();
		lista = sessionFactory.getCurrentSession().createQuery(s).list();
		
		return lista;
	}

	@Override
	public void executeUpdateQueryDinamica(String s) throws Exception {
		validaSessionFactory();
		
		sessionFactory.getCurrentSession().createQuery(s).executeUpdate();
		
		executeFlushSession();		
	}

	@Override
	public void executeUpdateSQLDinamica(String s) throws Exception {
		validaSessionFactory();
		
		sessionFactory.getCurrentSession().createSQLQuery(s).executeUpdate();
		
		executeFlushSession();
	}

	@Override
	public void clearSession() throws Exception {
		sessionFactory.getCurrentSession().clear();
	}

	@Override
	public void evict(Object obj) throws Exception {
		validaSessionFactory();
		
		sessionFactory.getCurrentSession().evict(obj);
	}

	@Override
	public Session getSession() throws Exception {
		validaSessionFactory();
		
		return sessionFactory.getCurrentSession();
	}

	@SuppressWarnings("unused")
	@Override
	public List<?> getListSQLDinamica(String sql) throws Exception {
		validaSessionFactory();
		
		List<?> lista = sessionFactory.getCurrentSession().createSQLQuery(sql).list();
		return lista;
	}

	@Override
	public Long totalRegistros(String table) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT COUNT(1) FROM ").append(table);
		
		return jdbcTemplate.queryForLong(sql.toString());
	}

	@Override
	public Query obterQuery(String query) throws Exception {
		validaSessionFactory();
		
		Query queryReturn = sessionFactory.getCurrentSession().createQuery(query.toString());
		
		return queryReturn;
	}
	

	@SuppressWarnings("unchecked")
	@Override
	public List<T[]> getListSQLDinamicaArray(String sql) throws Exception {
		validaSessionFactory();
		
		List<T[]> lista = (List<T[]>) sessionFactory.getCurrentSession().createSQLQuery(sql).list();
				
		return lista;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> findListByQueryDinamica(String query, 
			int iniciaNoRegistro, int maximoResultado) throws Exception {
		validaSessionFactory();
		
		List<T> lista = new ArrayList<>();
		lista = sessionFactory.getCurrentSession()
				.createQuery(query)
				.setFirstResult(iniciaNoRegistro)
				.setMaxResults(maximoResultado)
				.list();
		
		return lista;
	}

	private void validaSessionFactory() {
		if (sessionFactory == null)
			sessionFactory = HibernateUtil.getSessionFactory();
		
		validaTransaction();
	}
	
	private void validaTransaction() {
		if (!sessionFactory.getCurrentSession().getTransaction().isActive())
			sessionFactory.getCurrentSession().beginTransaction();
	}
	
	private void commitProcessoAjax() {
		sessionFactory.getCurrentSession().getTransaction().commit();
	}
	
	private void rollbackProcessoAjax() {
		sessionFactory.getCurrentSession().getTransaction().rollback();
	}
	
	private void executeFlushSession() {
		sessionFactory.getCurrentSession().flush();
	}
	
	@SuppressWarnings("unchecked")
	public T findUniqueByQueryDinamica(String query) throws Exception {
		validaSessionFactory();
		T obj = (T) sessionFactory.getCurrentSession().createQuery(query.toString()).uniqueResult();
		return obj;
	}

	public T findUniqueByProperty(Class<T> entidade, Object valor, String atributo, String condicao) throws Exception {
		validaSessionFactory();
		StringBuilder query = new StringBuilder();
		query.append("SELECT entity FROM ")
			.append(entidade.getSimpleName())
			.append(" entity WHERE entity.")
			.append(atributo)
			.append(" = '")
			.append(valor)
			.append("' ")
			.append(condicao);
		T obj = (T) this.findUniqueByQueryDinamica(query.toString());
		return obj;
	}

}
