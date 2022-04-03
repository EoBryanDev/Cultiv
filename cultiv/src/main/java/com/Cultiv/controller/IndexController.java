package com.Cultiv.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.Cultiv.repository.ProdutoRepository;

@Controller
public class IndexController {
	
	@Autowired
	private ProdutoRepository pr;
	
	// abrir index
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView abrirIndex() {
		ModelAndView mv = new ModelAndView("index");

		return mv;
	}

	// abrir sobrenos
	@RequestMapping(value = "/sobreNos", method = RequestMethod.GET)
	public ModelAndView abrirSobreNos() {
		ModelAndView mv = new ModelAndView("cliente/sobreNos");

		return mv;
	}

	// abrir contatos
	@RequestMapping(value = "/contatos", method = RequestMethod.GET)
	public ModelAndView abrirContatos() {
		ModelAndView mv = new ModelAndView("cliente/contatos");

		return mv;
	}

	// abrir produtos
	@RequestMapping(value = "/produtos", method = RequestMethod.GET)
	public ModelAndView abrirProdutos() {
		ModelAndView mv = new ModelAndView("produto");
		mv.addObject("listaProdutos",pr.findAll());
		return mv;
	}
	
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public ModelAndView abrirHome() {
		ModelAndView mv = new ModelAndView("administrativo/home");

		return mv;
	}


	

}
