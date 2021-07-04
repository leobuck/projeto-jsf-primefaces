package br.com.dao.implementacao;

import java.util.Date;

import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import br.com.framework.implementacao.crud.ImplementacaoCrud;
import br.com.project.model.classes.Entidade;
import br.com.repository.interfaces.EntidadeRepository;

@Repository
public class EntidadeDao extends ImplementacaoCrud<Entidade> implements EntidadeRepository {

	private static final long serialVersionUID = 1L;

	@Override
	public Date getUltimoAcessoEntidadeLogada(String login) {
		SqlRowSet rowSet = super.getJdbcTemplate()
				.queryForRowSet("SELECT ent_ultimoacesso FROM entidade WHERE ent_inativo IS FALSE AND ent_login = ?",
						new Object[]{login});
		return rowSet.next() ? rowSet.getDate("ent_ultimoacesso") : null;
	}

	@Override
	public void updateUltimoAcessoUser(String login) {
		String sql = "UPDATE entidade SET ent_ultimoacesso = CURRENT_TIMESTAMP WHERE ent_inativo IS FALSE AND ent_login = ?";
		super.getSimpleJdbcTemplate().update(sql, login);
	}

	@Override
	public boolean existeUsuario(String login) {
		StringBuilder builder = new StringBuilder();
		builder.append("SELECT COUNT(1) >= 1 FROM entidade WHERE ent_login = '")
			.append(login)
			.append("'");
		return super.getJdbcTemplate().queryForObject(builder.toString(), Boolean.class);
	}

}
