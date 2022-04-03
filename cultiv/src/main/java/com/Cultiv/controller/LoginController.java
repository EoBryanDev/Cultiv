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
public class LoginController {


	@GetMapping("/negadoAdministrativo")
	public ModelAndView redirecionarNegadoAdministrativo() {
	
		ModelAndView mv = new ModelAndView("/negadoAdministrativo");
		
		
		return mv;
	}
	
	@GetMapping("/negadoCliente")
	public ModelAndView redirecionarNegadoCliente() {
	
		ModelAndView mv = new ModelAndView("/negadoCliente");
		
		
		return mv;
	}
	
	@GetMapping("/login")
	public ModelAndView redirecionarLogin() {
		ModelAndView mv = new ModelAndView("/login");
		
		
		return mv;
	}

}
