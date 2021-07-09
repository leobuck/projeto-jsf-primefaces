package br.com.dao.implementacao;

import org.springframework.stereotype.Repository;

import br.com.framework.implementacao.crud.ImplementacaoCrud;
import br.com.project.model.classes.Cidade;
import br.com.repository.interfaces.CidadeRepository;

@Repository
public class CidadeDao extends ImplementacaoCrud<Cidade> implements CidadeRepository {

	private static final long serialVersionUID = 1L;

}
