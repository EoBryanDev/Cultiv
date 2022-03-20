package com.Cultiv.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.Cultiv.model.Usuario;
import com.Cultiv.repository.UsuarioRepository;
import com.Cultiv.services.CookieService;

@Controller
public class UsuarioController {

	@Autowired
	private UsuarioRepository ur;
	


	// registrar
	@RequestMapping(value = "/registrar", method = RequestMethod.GET)
	public ModelAndView abrirRegistrar() {
		ModelAndView mv = new ModelAndView("pgInternas/registrar");

		return mv;
	}

	@RequestMapping(value = "/registrar", method = RequestMethod.POST)
	public String gravarUsuario(Usuario usuario) {
		
		ur.save(usuario);
		
		
		
		return "redirect:/produtos";
	}
	
	@RequestMapping(value = "/entrar", method = RequestMethod.GET)
	public ModelAndView abrirEntrar() {
		ModelAndView mv = new ModelAndView("pgExternas/entrar");

		return mv;
	}
	
	//rota para logar 
	@RequestMapping(value = "/entrar", method = RequestMethod.POST)
	public String logar(Usuario usuarioParam, String lembrar, HttpServletResponse response,
			RedirectAttributes attributes) throws IOException {
		
		Usuario user = this.ur.Login(usuarioParam.getEmail() , usuarioParam.getSenha());
		
		if(user != null) {
			int tempoLogado = 60*60;
			if(lembrar != null) tempoLogado = (60*60*24*365);
			CookieService.setCookie(response,"usuarioId",String.valueOf(user.getId()),tempoLogado);
			
			String id = "" + user.getId();

			return "redirect:/perfil?id=" + id;
		}
		
		
		attributes.addFlashAttribute("mensagem", "Usuário ou senha inválidos!");
		return "redirect:/entrar";
			
		
	}
	//rota para sair do login
	@RequestMapping(value = "/sair", method = RequestMethod.GET)
	public String sair(HttpServletResponse response) throws IOException {
			
		CookieService.setCookie(response,"usuarioId","",0);
		return "redirect:/produtos";
				
	}
	
	// perfil
		@RequestMapping(value = "/perfil", method = RequestMethod.GET)
		public ModelAndView abrirPerfil() {
			ModelAndView mv = new ModelAndView("pgInternas/perfil");

			return mv;
		}
	
		//passar pra todas as paginas o id do usuario logado
		
	
}
