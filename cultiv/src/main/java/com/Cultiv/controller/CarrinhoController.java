package com.Cultiv.controller;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.Cultiv.model.Carrinho;
import com.Cultiv.model.Usuario;
import com.Cultiv.repository.UsuarioRepository;
import com.Cultiv.repository.CarrinhoRepository;

import com.Cultiv.controller.UsuarioController;
@Controller
public class CarrinhoController {
	
	@Autowired
	private CarrinhoRepository cr;
	
	@Autowired
	private UsuarioRepository ur;
	
	@RequestMapping(value = "/carrinho{id}", method = RequestMethod.GET)
	public ModelAndView mostrarItensCarrinho(@PathVariable("id") long id) {
		Carrinho carrinho = cr.findByIdCarrinho(id);
		ModelAndView mv = new ModelAndView("pgInternas/carrinho");

		return mv;
	}

}