package br.com.project.checkskills.controllers.autenticacao;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.security.core.context.SecurityContextHolder;

import br.com.project.checkskills.entities.autenticacao.PermissaoEntity;
import br.com.project.checkskills.entities.autenticacao.UsuarioEntity;
import br.com.project.checkskills.repositories.autenticacao.IUsuarioRepository;

@ManagedBean(name = "usuarioBean")
@ViewScoped
public class UsuarioBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private static final Logger LOGGER = Logger.getLogger(UsuarioBean.class);

	private   boolean roleAdmin, roleAvaliado, roleLider;
	
	@ManagedProperty(value="#{usuarioRepository}")
	private IUsuarioRepository usuarioRepository;
	
		public UsuarioEntity usuarioLogado(){
			String username =  SecurityContextHolder.getContext().getAuthentication().getName();		
			return this.usuarioRepository.findByUsername(username);
		}
	
		public void carregarPermissoes(){
			usuarioLogado().getPermissions().forEach(item -> atribuiPermissao(item));
			System.out.println(roleAvaliado);
		}

		public void atribuiPermissao(PermissaoEntity item) {
			if (item.getRole().equals("ROLE_ADMIN")) 
				roleAdmin = true;
			if (item.getRole().equals("ROLE_AVALIADO")) 
				setRoleAvaliado(true);
			if (item.getRole().equals("ROLE_LIDER")) 
				roleLider = true;
		}

		public String logout() {
			HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
			session.invalidate();
			
			return "";
		}
		
		public boolean isRoleAvaliado() {
			return roleAvaliado;
		}

		public void setRoleAvaliado(boolean roleAvaliado) {
			this.roleAvaliado = roleAvaliado;
		}

		public boolean isRoleAdmin() {
			return roleAdmin;
		}

		public void setRoleAdmin(boolean roleAdmin) {
			this.roleAdmin = roleAdmin;
		}

		public boolean isRoleLider() {
			return roleLider;
		}

		public void setRoleLider(boolean roleLider) {
			this.roleLider = roleLider;
		}

		public IUsuarioRepository getUsuarioRepository() {
			return usuarioRepository;
		}

		public void setUsuarioRepository(IUsuarioRepository usuarioRepository) {
			this.usuarioRepository = usuarioRepository;
		}

	
		

		
}
