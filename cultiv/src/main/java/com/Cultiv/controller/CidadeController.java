package com.Cultiv.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.Cultiv.model.Cidade;
import com.Cultiv.repository.CidadeRepository;
import com.Cultiv.repository.EstadoRepository;

@Controller
public class CidadeController {

	@Autowired
	private CidadeRepository cr;
	
	@Autowired
	private EstadoRepository er;

	@GetMapping("/administrativo/cidades/cadastrar")
	public ModelAndView cadastrarCidade(Cidade cidade) {
		// pastar ainda nao criada nem arquivo
		ModelAndView mv = new ModelAndView("administrativo/cidades/cadastro");
		mv.addObject("listaEstados",er.findAll());
		mv.addObject("cidade", cidade);
		
		return mv;
	}

	@GetMapping("/administrativo/cidades/listar")
	public ModelAndView listar() {
		ModelAndView mv = new ModelAndView("administrativo/cidades/lista");
		mv.addObject("listaCidades", cr.findAll());
		return mv;
	}

	@GetMapping("/administrativo/cidades/editar/{id}")
	public ModelAndView editar(@PathVariable("id") Long id) {
		Optional<Cidade> cidade = cr.findById(id);
		return cadastrarCidade(cidade.get());
	}

	@GetMapping("/administrativo/cidades/remover/{id}")
	public ModelAndView remover(@PathVariable("id") Long id) {
		Optional<Cidade> cidade = cr.findById(id);
		cr.delete(cidade.get());
		return listar();
	}

	@PostMapping("/administrativo/cidades/salvar")
	public ModelAndView salvar(@Valid Cidade cidade, BindingResult result) {
		if (result.hasErrors()) {

			return cadastrarCidade(cidade);
		}
		cr.saveAndFlush(cidade);

		return cadastrarCidade(new Cidade());
	}
}
