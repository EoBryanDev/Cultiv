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

import com.Cultiv.model.Estado;
import com.Cultiv.model.Estado;
import com.Cultiv.repository.EstadoRepository;

@Controller
public class EstadoController {

	@Autowired
	private EstadoRepository er;

	@GetMapping("/administrativo/estados/cadastrar")
	public ModelAndView cadastrarEstado(Estado estado) {
		// pastar ainda nao criada nem arquivo
		ModelAndView mv = new ModelAndView("administrativo/estados/cadastro");
		mv.addObject("estado", estado);
		return mv;
	}

	@GetMapping("/administrativo/estados/listar")
	public ModelAndView listar() {
		ModelAndView mv = new ModelAndView("administrativo/estados/lista");
		mv.addObject("listaEstados", er.findAll());
		return mv;
	}

	@GetMapping("/administrativo/estados/editar/{id}")
	public ModelAndView editar(@PathVariable("id") Long id) {
		Optional<Estado> estado = er.findById(id);
		return cadastrarEstado(estado.get());
	}

	@GetMapping("/administrativo/estados/remover/{id}")
	public ModelAndView remover(@PathVariable("id") Long id) {
		Optional<Estado> estado = er.findById(id);
		er.delete(estado.get());
		return listar();
	}

	@PostMapping("/administrativo/estados/salvar")
	public ModelAndView salvar(@Valid Estado estado, BindingResult result) {
		if (result.hasErrors()) {

			return cadastrarEstado(estado);
		}
		er.saveAndFlush(estado);

		return cadastrarEstado(new Estado());
	}
}
