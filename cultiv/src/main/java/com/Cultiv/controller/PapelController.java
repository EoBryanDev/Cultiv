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

import com.Cultiv.model.Papel;
import com.Cultiv.model.Papel;
import com.Cultiv.repository.PapelRepository;

@Controller
public class PapelController {

	@Autowired
	private PapelRepository pr;

	@GetMapping("/administrativo/papeis/cadastrar")
	public ModelAndView cadastrarPapel(Papel papel) {
		// pastar ainda nao criada nem arquivo
		ModelAndView mv = new ModelAndView("administrativo/papeis/cadastro");
		mv.addObject("papel", papel);
		return mv;
	}

	@GetMapping("/administrativo/papeis/listar")
	public ModelAndView listar() {
		ModelAndView mv = new ModelAndView("administrativo/papeis/lista");
		mv.addObject("listaPapeis", pr.findAll());
		return mv;
	}

	@GetMapping("/administrativo/papeis/editar/{id}")
	public ModelAndView editar(@PathVariable("id") Long id) {
		Optional<Papel> papel = pr.findById(id);
		return cadastrarPapel(papel.get());
	}

	@GetMapping("/administrativo/papeis/remover/{id}")
	public ModelAndView remover(@PathVariable("id") Long id) {
		Optional<Papel> papel = pr.findById(id);
		pr.delete(papel.get());
		return listar();
	}

	@PostMapping("/administrativo/papeis/salvar")
	public ModelAndView salvar(@Valid Papel papel, BindingResult result) {
		if (result.hasErrors()) {

			return cadastrarPapel(papel);
		}
		pr.saveAndFlush(papel);

		return cadastrarPapel(new Papel());
	}
}
