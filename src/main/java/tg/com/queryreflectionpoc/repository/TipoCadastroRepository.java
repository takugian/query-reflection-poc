package tg.com.queryreflectionpoc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tg.com.queryreflectionpoc.model.TipoCadastro;

@Repository
public interface TipoCadastroRepository extends JpaRepository<TipoCadastro, Integer> {

}
