package com.Cultiv.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.Cultiv.model.Cartao;
import com.Cultiv.model.Cliente;
import com.Cultiv.repository.CartaoRepository;
import com.Cultiv.repository.CidadeRepository;
import com.Cultiv.repository.ClienteRepository;
import com.Cultiv.repository.EstadoRepository;

@Controller
public class CartaoController {

	@Autowired
	private CartaoRepository cr;
	
	@Autowired
	private ClienteRepository clr;
	
	private Cliente cliente;
	
	private void buscarUsuarioLogado() {
		Authentication autenticado = SecurityContextHolder.getContext().getAuthentication();
		if(!(autenticado instanceof AnonymousAuthenticationToken)) {
			String email = autenticado.getName();
			cliente = clr.buscarEmail(email).get(0);
		}
	}

	@GetMapping("/cliente/cadastrarCartao")
	public ModelAndView cadastrarCartao(Cartao cartao) {
		buscarUsuarioLogado();
		ModelAndView mv = new ModelAndView("cliente/cartao");
		mv.addObject("nome", cliente);
		return mv;
	}

	@PostMapping("/cliente/cadastrarCartao/salvar")
	public ModelAndView salvar(@Valid Cartao cartao, BindingResult result) {
		if (result.hasErrors()) {

			return cadastrarCartao(cartao);
		}
		
		buscarUsuarioLogado();
		cliente.setCartao(cartao);
				
		cartao.setCliente(cliente);
		
		cr.saveAndFlush(cartao);


		return cadastrarCartao(new Cartao());
	}
}
