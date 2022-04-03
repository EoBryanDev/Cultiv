package com.Cultiv.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.Cultiv.model.Cliente;
import com.Cultiv.model.Compra;
import com.Cultiv.model.ItensCompra;
import com.Cultiv.model.Produto;
import com.Cultiv.repository.CidadeRepository;
import com.Cultiv.repository.ClienteRepository;
import com.Cultiv.repository.CompraRepository;
import com.Cultiv.repository.FuncionarioRepository;
import com.Cultiv.repository.ItensCompraRepository;
import com.Cultiv.repository.ProdutoRepository;

@Controller
public class CarrinhoController {
	
	private List<ItensCompra> itensCompra = new ArrayList<ItensCompra>();
	@Autowired
	private ProdutoRepository pr;
	
	@Autowired
	private ClienteRepository cr;
	
	@Autowired
	private CompraRepository cmpr;
	
	@Autowired
	private ItensCompraRepository icr;
	
	private Compra compra = new Compra();
	
	private Cliente cliente;
	
	private void calcularTotal() {
		compra.setValorTotal(0.0);
		for(ItensCompra it: itensCompra) {
			compra.setValorTotal(compra.getValorTotal() + it.getValorTotal());
		}
	}
	
	@GetMapping("/carrinho")	
	public ModelAndView carrinho(){
		ModelAndView mv = new ModelAndView("cliente/carrinho");
		calcularTotal();
		mv.addObject("compra",compra);
		mv.addObject("listaItens",itensCompra);
		
		return mv;
	}
	
	private void buscarUsuarioLogado() {
		Authentication autenticado = SecurityContextHolder.getContext().getAuthentication();
		if(!(autenticado instanceof AnonymousAuthenticationToken)) {
			String email = autenticado.getName();
			cliente = cr.buscarEmail(email).get(0);
		}
	}
	@GetMapping("/finalizar")	
	public ModelAndView finalizar(){
		buscarUsuarioLogado();
		ModelAndView mv = new ModelAndView("cliente/finalizar");
		calcularTotal();
		mv.addObject("compra",compra);
		mv.addObject("listaItens",itensCompra);
		mv.addObject("cliente",cliente);
		
		return mv;
	}
	@PostMapping("/finalizar/confirmar")
	public ModelAndView confirmarCompra(String formaPagamento) {
		buscarUsuarioLogado();
		ModelAndView mv = new ModelAndView("cliente/compraFinalizada");
		compra.setCliente(cliente);
		compra.setFormaPagamento(formaPagamento);
		cmpr.saveAndFlush(compra);
		
		for(ItensCompra c:itensCompra) {
			c.setCompra(compra);
			icr.saveAndFlush(c);
		}
		
		itensCompra = new ArrayList<>();
		compra = new Compra();
		mv.addObject("cliente", cliente);
		return mv;
		
		
	}
	
	@GetMapping("/alterarQuantidade/{id}/{acao}")	
	public String alterarQuantidade(@PathVariable long id, @PathVariable Integer acao){
		
		
		for(ItensCompra it:itensCompra) {
			if(it.getProduto().getId().equals(id)) {
				if(acao.equals(1)) {
					it.setQuantidade(it.getQuantidade() + 0.01);
					it.setValorTotal(0.0);
					it.setValorTotal(it.getValorTotal() +(it.getQuantidade()*it.getValorUnitario()));
				} else if(acao == 0) {
					
					
					it.setQuantidade(it.getQuantidade() - 0.01);
					it.setValorTotal(0.0);
					it.setValorTotal(it.getValorTotal() +(it.getQuantidade()*it.getValorUnitario()));
					
				}
				
				
				
				break;
			}
		}
		return "redirect:/carrinho";
		
	}
	@GetMapping("/removerItem/{id}")	
	public String removerItem(@PathVariable long id){
		
		
		for(ItensCompra it:itensCompra) {
			if(it.getProduto().getId().equals(id)) {
				itensCompra.remove(it);
				break;
			}
		}
		return "redirect:/carrinho";
		
	}
	
	@GetMapping("/adicionarCarrinho/{id}")	
	public String adicionarCarrinho(@PathVariable long id){
		
		
		Optional<Produto> prod = pr.findById(id);
		Produto produto = prod.get();
		
		int controle = 0;
		for(ItensCompra it:itensCompra) {
			if(it.getProduto().getId().equals(produto.getId())) {
				it.setQuantidade(it.getQuantidade()+0.1);
				it.setValorTotal(0.0);
				it.setValorTotal(it.getValorTotal() +(it.getQuantidade()*it.getValorUnitario()));
				controle =1;
				break;
			}
		}
		if(controle == 0 ) {
			ItensCompra item = new ItensCompra();
			item.setProduto(produto);		
			item.setValorUnitario(produto.getPreco());
			item.setQuantidade(item.getQuantidade()+0.1);
			
			item.setValorTotal(item.getValorTotal() +(item.getQuantidade()*item.getValorUnitario()));
			itensCompra.add(item);
		}
		return "redirect:/carrinho";
	}
	
}
