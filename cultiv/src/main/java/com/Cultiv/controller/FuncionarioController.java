package com.Cultiv.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.Cultiv.model.Funcionario;
import com.Cultiv.repository.CidadeRepository;
import com.Cultiv.repository.FuncionarioRepository;

@Controller
public class FuncionarioController {
	
	@Autowired
	private FuncionarioRepository fr;
	
	@Autowired
	private CidadeRepository cr;

	@GetMapping("/administrativo/funcionario/cadastrar")
	public ModelAndView cadastrarFuncionario(Funcionario funcionario) {
		
		ModelAndView mv = new ModelAndView("administrativo/funcionario/cadastro");
		mv.addObject("funcionario", funcionario);
		mv.addObject("listaCidades", cr.findAll());
		return mv;
	}

	@GetMapping("/administrativo/funcionario/listar")
	public ModelAndView listar() {
		ModelAndView mv = new ModelAndView("administrativo/funcionario/lista");
		mv.addObject("listaFuncionario", fr.findAll());
		return mv;
	}
	
	@GetMapping("/administrativo/funcionario/editar/{id}")
	public ModelAndView editar(@PathVariable("id") Long id) {
		Optional<Funcionario> funcionario = fr.findById(id);
		return cadastrarFuncionario(funcionario.get());
	}
	
	@GetMapping("/administrativo/funcionario/remover/{id}")
	public ModelAndView remover(@PathVariable("id") Long id) {
		Optional<Funcionario> funcionario = fr.findById(id);
		fr.delete(funcionario.get());
		return listar();
	}
	
	
	@PostMapping("/administrativo/funcionario/salvar")
	public ModelAndView salvar(@Valid Funcionario funcionario, BindingResult result) {
		if(result.hasErrors()) {
			
			return cadastrarFuncionario(funcionario);
		}
		funcionario.setSenha(new BCryptPasswordEncoder().encode(funcionario.getSenha()));
		fr.saveAndFlush(funcionario);
		
		return cadastrarFuncionario(new Funcionario());
	}
}
