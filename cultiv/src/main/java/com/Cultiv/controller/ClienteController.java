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

import com.Cultiv.model.Cliente;
import com.Cultiv.repository.CidadeRepository;
import com.Cultiv.repository.ClienteRepository;
import com.Cultiv.repository.EstadoRepository;

@Controller
public class ClienteController {

	@Autowired
	private ClienteRepository cr;
	
	@Autowired
	private CidadeRepository cdr;
	
	//ir para pagina de login do cliente
	@GetMapping("/cliente/loginCliente")
	public ModelAndView loginCliente() {
		ModelAndView mv = new ModelAndView("cliente/loginCliente");
		return mv;
	}
	@GetMapping("/cliente/cadastrar")
	public ModelAndView cadastrarCliente(Cliente cliente) {
		// pastar ainda nao criada nem arquivo
		ModelAndView mv = new ModelAndView("cliente/registrar");
		mv.addObject("listaCidades",cdr.findAll());
		mv.addObject("cliente", cliente);
		
		return mv;
	}


	@GetMapping("/cliente/editar/{id}")
	public ModelAndView editar(@PathVariable("id") Long id) {
		Optional<Cliente> cliente = cr.findById(id);
		return cadastrarCliente(cliente.get());
	}

	

	@PostMapping("/cliente/salvar")
	public ModelAndView salvar(@Valid Cliente cliente, BindingResult result) {
		if (result.hasErrors()) {

			return cadastrarCliente(cliente);
		}
		cliente.setSenha(new BCryptPasswordEncoder().encode(cliente.getSenha()));
		cr.saveAndFlush(cliente);

		return cadastrarCliente(new Cliente());
	}
}
