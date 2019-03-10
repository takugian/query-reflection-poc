package tg.com.queryreflectionpoc.repository;

import java.util.Calendar;
import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import tg.com.queryreflectionpoc.core.reflection.Pagination;
import tg.com.queryreflectionpoc.model.Cadastro;

@Transactional
public interface CadastroRepositoryPoc {

	Cadastro persist(Cadastro cadastro);

	Cadastro merge(Cadastro cadastro);

	Cadastro remove(Cadastro cadastro);

	Cadastro find(Integer id);

	Collection<Cadastro> listAll();

	Collection<Cadastro> listAll(Pagination pagination);

	Collection<Cadastro> listByNmCadastro(@Param("nmCadastro") String nmCadastro);

	Collection<Cadastro> listByNmCadastroAndCdFuncao(@Param("nmCadastro") String nmCadastro,
			@Param("cdFuncao") String cdFuncao);

	Collection<Cadastro> listByNmCadastroOrCdFuncao(@Param("nmCadastro") String nmCadastro,
			@Param("cdFuncao") String cdFuncao);

	Collection<Cadastro> listByDtCadastro(@Param("dtCadastro") Calendar dtCadastro);

	Collection<Cadastro> listByDtCadastroLessThanAndCdFuncao(@Param("dtCadastro") Calendar dtCadastro,
			@Param("cdFuncao") String cdFuncao);

	Collection<Cadastro> listOrderByIdCadastroDesc();

	Collection<Cadastro> listByCdFuncaoLikeOrderByIdCadastroDesc(@Param("cdFuncao") String cdFuncao);

	@Query(name = "SELECT c FROM Cadastro c WHERE c.dtCadastro > :dtCadastro ")
	Collection<Cadastro> listByQuery(@Param("dtCadastro") Calendar dtCadastro);

	// TODO Pendente
	@Query(name = "SELECT c.* FROM Cadastro c WHERE c.dt_Cadastro > :dtCadastro ", nativeQuery = true)
	Collection<Cadastro> listByNativeQuery(@Param("dtCadastro") Calendar dtCadastro);

	// TODO SORT

	@Query(name = "UPDATE Cadastro SET nmCadastro = ?1 WHERE idCadastro = ?2 ")
	Integer updateNmCadastro(@Param("nmCadastro") String nmCadastro, @Param("idCadastro") Integer idCadastro);

}
