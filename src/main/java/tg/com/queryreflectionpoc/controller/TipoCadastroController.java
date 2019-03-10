package tg.com.queryreflectionpoc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import tg.com.queryreflectionpoc.model.TipoCadastro;
import tg.com.queryreflectionpoc.repository.TipoCadastroRepository;

@RestController
@RequestMapping(path = "/tipo-cadastro")
public class TipoCadastroController {

	@Autowired
	private TipoCadastroRepository tipoCadastroRepository;

	@RequestMapping(method = RequestMethod.GET)
	public Iterable<TipoCadastro> listar() {
		return tipoCadastroRepository.findAll();
	}

}
