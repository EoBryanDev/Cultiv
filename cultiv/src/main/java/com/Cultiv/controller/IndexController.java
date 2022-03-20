package com.Cultiv.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class IndexController {

	// abrir index
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView abrirIndex() {
		ModelAndView mv = new ModelAndView("index");

		return mv;
	}

	// abrir sobrenos
	@RequestMapping(value = "/sobreNos", method = RequestMethod.GET)
	public ModelAndView abrirSobreNos() {
		ModelAndView mv = new ModelAndView("pgExternas/sobreNos");

		return mv;
	}

	// abrir contatos
	@RequestMapping(value = "/contatos", method = RequestMethod.GET)
	public ModelAndView abrirContatos() {
		ModelAndView mv = new ModelAndView("pgExternas/contatos");

		return mv;
	}

	// abrir produtos
	@RequestMapping(value = "/produtos", method = RequestMethod.GET)
	public ModelAndView abrirProdutos() {
		ModelAndView mv = new ModelAndView("pgExternas/produtos");

		return mv;
	}


	

}
