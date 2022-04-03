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

import com.Cultiv.model.Permissao;
import com.Cultiv.repository.PermissaoRepository;
import com.Cultiv.repository.FuncionarioRepository;
import com.Cultiv.repository.PapelRepository;

@Controller
public class PermissaoController {

	@Autowired
	private PermissaoRepository pmr;
	
	@Autowired
	private FuncionarioRepository fr;
	
	@Autowired
	private PapelRepository pr;

	@GetMapping("/administrativo/permissoes/cadastrar")
	public ModelAndView cadastrarPermissao(Permissao permissao) {
		// pastar ainda nao criada nem arquivo
		ModelAndView mv = new ModelAndView("administrativo/permissoes/cadastro");
		
		mv.addObject("permissao", permissao);
		mv.addObject("listaFuncionarios",fr.findAll());
		mv.addObject("listaPapeis",pr.findAll());
		
		return mv;
	}

	@GetMapping("/administrativo/permissoes/listar")
	public ModelAndView listar() {
		ModelAndView mv = new ModelAndView("administrativo/permissoes/lista");
		mv.addObject("listaPermissoes", pmr.findAll());
		return mv;
	}

	@GetMapping("/administrativo/permissoes/editar/{id}")
	public ModelAndView editar(@PathVariable("id") Long id) {
		Optional<Permissao> permissao = pmr.findById(id);
		return cadastrarPermissao(permissao.get());
	}

	@GetMapping("/administrativo/permissoes/remover/{id}")
	public ModelAndView remover(@PathVariable("id") Long id) {
		Optional<Permissao> permissao = pmr.findById(id);
		pmr.delete(permissao.get());
		return listar();
	}

	@PostMapping("/administrativo/permissoes/salvar")
	public ModelAndView salvar(@Valid Permissao permissao, BindingResult result) {
		if (result.hasErrors()) {

			return cadastrarPermissao(permissao);
		}
		pmr.saveAndFlush(permissao);

		return cadastrarPermissao(new Permissao());
	}
}
