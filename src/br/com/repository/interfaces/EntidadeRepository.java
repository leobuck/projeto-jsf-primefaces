package br.com.repository.interfaces;

import java.io.Serializable;
import java.util.Date;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public interface EntidadeRepository extends Serializable {

	Date getUltimoAcessoEntidadeLogada(String login);
	void updateUltimoAcessoUser(String login);
	boolean existeUsuario(String login);
}
