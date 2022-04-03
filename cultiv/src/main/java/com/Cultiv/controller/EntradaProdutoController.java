package com.Cultiv.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.Cultiv.model.EntradaProduto;
import com.Cultiv.model.Produto;
import com.Cultiv.model.EntradaItens;
import com.Cultiv.repository.EntradaItensRepository;
import com.Cultiv.repository.EntradaProdutoRepository;
import com.Cultiv.repository.EstadoRepository;
import com.Cultiv.repository.FuncionarioRepository;
import com.Cultiv.repository.ProdutoRepository;

@Controller
public class EntradaProdutoController {
	
	
	private List<EntradaItens> listaEntrada = new ArrayList<EntradaItens>();
	
	@Autowired
	private EntradaProdutoRepository epr;
	
	@Autowired
	private EntradaItensRepository eir;
	
	@Autowired
	private FuncionarioRepository fr;
	
	@Autowired
	private ProdutoRepository pr;

	@GetMapping("/administrativo/entrada/cadastrar")
	public ModelAndView cadastrarEntrada(EntradaProduto entrada, EntradaItens entradaItens) {
		// pastar ainda nao criada nem arquivo
		ModelAndView mv = new ModelAndView("administrativo/entrada/cadastro");
		mv.addObject("entrada", entrada);
		mv.addObject("listaEntradaItens", this.listaEntrada);
		mv.addObject("entradaItens", entradaItens);
		mv.addObject("listaFuncionarios", fr.findAll());
		mv.addObject("listaProdutos", pr.findAll());
		return mv;
	}

	@GetMapping("/administrativo/entrada/listar")
	public ModelAndView listar() {
		ModelAndView mv = new ModelAndView("administrativo/entrada/lista");
		
		mv.addObject("listaEntradas", eir.findAll());
		return mv;
	}

	@GetMapping("/administrativo/entrada/editar/{id}")
	public ModelAndView editar(@PathVariable("id") Long id) {
		Optional<EntradaItens> itens = eir.findById(id);
		Optional<EntradaProduto> produto = epr.findById(id);
		return cadastrarEntrada(produto.get(),itens.get());
	}

	@GetMapping("/administrativo/entrada/remover/{id}")
	public ModelAndView remover(@PathVariable("id") Long id) {
		Optional<EntradaItens> entradaItens = eir.findById(id);
		eir.delete(entradaItens.get());
		return listar();
	}

	@PostMapping("/administrativo/entrada/salvar")
	public ModelAndView salvar(String acao, EntradaProduto entrada, EntradaItens entradaItens) {
		
		if(acao.equals("itens")) {
			this.listaEntrada.add(entradaItens);
		} else if (acao.equals("salvar")) {
			epr.saveAndFlush(entrada);
			for(EntradaItens it : listaEntrada) {
				
				it.setEntrada(entrada);
				eir.saveAndFlush(it);
				Optional<Produto> prod = pr.findById(it.getProduto().getId());
				Produto produto = prod.get();
				
				produto.setQuantidadeEstoque(produto.getQuantidadeEstoque() + it.getQuantidade());
				produto.setPreco(it.getValorVenda());
				pr.saveAndFlush(produto);
				
				this.listaEntrada = new ArrayList<>();
				
				
			}
			return cadastrarEntrada(new EntradaProduto(), new EntradaItens());
		}
			
		//System.out.println(this.listaEntrada.size());
	
		return cadastrarEntrada(entrada, new EntradaItens());
	}
}
