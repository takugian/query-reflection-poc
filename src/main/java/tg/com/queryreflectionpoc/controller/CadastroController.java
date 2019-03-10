package tg.com.queryreflectionpoc.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import tg.com.queryreflectionpoc.core.reflection.Pagination;
import tg.com.queryreflectionpoc.model.Cadastro;
import tg.com.queryreflectionpoc.repository.CadastroRepositoryPoc;

@RestController
@RequestMapping(path = "/cadastro")
public class CadastroController {

	@Autowired
	private CadastroRepositoryPoc cadastroRepositoryPoc;

	@RequestMapping(method = RequestMethod.POST, path = "/persist")
	public Cadastro persist(@RequestBody Cadastro cadastro) {
		cadastroRepositoryPoc.persist(cadastro);
		return cadastro;
	}

	@RequestMapping(method = RequestMethod.PUT, path = "/merge/{idCadastro}")
	public Cadastro merge(@PathVariable("idCadastro") Integer idCadastro, @RequestBody Cadastro cadastro) {
		cadastro.setIdCadastro(idCadastro);
		cadastroRepositoryPoc.merge(cadastro);
		return cadastro;
	}

	@RequestMapping(method = RequestMethod.PUT, path = "/updateNmCadastro/{idCadastro}")
	public Integer updateNmCadastro(@PathVariable("idCadastro") Integer idCadastro, @RequestBody Cadastro cadastro) {
		cadastro.setIdCadastro(idCadastro);
		return cadastroRepositoryPoc.updateNmCadastro(cadastro.getNmCadastro(), idCadastro);
	}

	@RequestMapping(method = RequestMethod.DELETE, path = "/remove/{idCadastro}")
	public void remove(@PathVariable("idCadastro") Integer idCadastro) {
		Cadastro cadastro = new Cadastro();
		cadastro.setIdCadastro(idCadastro);
		cadastroRepositoryPoc.remove(cadastro);
	}

	@RequestMapping(method = RequestMethod.GET, path = "/find/{idCadastro}")
	public Cadastro persist(@PathVariable("idCadastro") Integer idCadastro) {
		return cadastroRepositoryPoc.find(idCadastro);
	}

	@RequestMapping(method = RequestMethod.GET, path = "/listAll")
	public Iterable<Cadastro> listar() {
		return cadastroRepositoryPoc.listAll();
	}

	@RequestMapping(method = RequestMethod.GET, path = "/listAllPagination")
	public Iterable<Cadastro> listar(@RequestParam("page") Integer page, @RequestParam("size") Integer size) {
		Pagination pagination = new Pagination();
		pagination.setPageNumber(page);
		pagination.setPageSize(size);
		return cadastroRepositoryPoc.listAll(pagination);
	}

	@RequestMapping(method = RequestMethod.GET, path = "/listByNmCadastro")
	public Collection<Cadastro> listByNmCadastro(@RequestParam("nmCadastro") String nmCadastro) {
		return cadastroRepositoryPoc.listByNmCadastro(nmCadastro);
	}

	@RequestMapping(method = RequestMethod.GET, path = "/listByNmCadastroAndCdFuncao")
	public Collection<Cadastro> listByNmCadastroAndCdFuncao(@RequestParam("nmCadastro") String nmCadastro,
			@RequestParam("cdFuncao") String cdFuncao) {
		return cadastroRepositoryPoc.listByNmCadastroAndCdFuncao(nmCadastro, cdFuncao);
	}

	@RequestMapping(method = RequestMethod.GET, path = "/listByNmCadastroOrCdFuncao")
	public Collection<Cadastro> listByNmCadastroOrCdFuncao(@RequestParam("nmCadastro") String nmCadastro,
			@RequestParam("cdFuncao") String cdFuncao) {
		return cadastroRepositoryPoc.listByNmCadastroOrCdFuncao(nmCadastro, cdFuncao);
	}

	@RequestMapping(method = RequestMethod.GET, path = "/listByDtCadastro")
	public Collection<Cadastro> listByDtCadastro(@RequestParam("dtCadastro") String dtCadastro) throws ParseException {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").parse(dtCadastro));
		return cadastroRepositoryPoc.listByDtCadastro(calendar);
	}

	@RequestMapping(method = RequestMethod.GET, path = "/listByDtCadastroLessThanAndCdFuncao")
	public Collection<Cadastro> listByDtCadastroLessThanAndCdFuncao(@RequestParam("dtCadastro") String dtCadastro,
			@RequestParam("cdFuncao") String cdFuncao) throws ParseException {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").parse(dtCadastro));
		return cadastroRepositoryPoc.listByDtCadastroLessThanAndCdFuncao(calendar, cdFuncao);
	}

	@RequestMapping(method = RequestMethod.GET, path = "/listByCdFuncaoLikeOrderByIdCadastroDesc")
	public Collection<Cadastro> listByCdFuncaoLikeOrderByIdCadastroDesc(@RequestParam("cdFuncao") String cdFuncao) {
		return cadastroRepositoryPoc.listByCdFuncaoLikeOrderByIdCadastroDesc("%" + cdFuncao + "%");
	}

	@RequestMapping(method = RequestMethod.GET, path = "/listOrderByIdCadastroDesc")
	public Collection<Cadastro> listOrderByIdCadastroDesc() {
		return cadastroRepositoryPoc.listOrderByIdCadastroDesc();
	}

	@RequestMapping(method = RequestMethod.GET, path = "/listByQuery")
	public Collection<Cadastro> listByQuery(@RequestParam("dtCadastro") String dtCadastro) throws ParseException {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").parse(dtCadastro));
		return cadastroRepositoryPoc.listByQuery(calendar);
	}

	@RequestMapping(method = RequestMethod.GET, path = "/listByNativeQuery")
	public Collection<Cadastro> listByNativeQuery(@RequestParam("dtCadastro") String dtCadastro) throws ParseException {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").parse(dtCadastro));
		return cadastroRepositoryPoc.listByNativeQuery(calendar);
	}

}
