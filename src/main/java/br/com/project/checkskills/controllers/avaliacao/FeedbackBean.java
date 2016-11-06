package br.com.project.checkskills.controllers.avaliacao;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.apache.log4j.Logger;
import org.omnifaces.util.Messages;
import org.springframework.security.core.context.SecurityContextHolder;

import br.com.project.checkskills.entities.dadosbasicos.FeedbackEntity;
import br.com.project.checkskills.entities.dadosbasicos.FuncionarioEntity;
import br.com.project.checkskills.repositories.autenticacao.IUsuarioRepository;
import br.com.project.checkskills.repositories.avaliacao.IFeedbackRepository;
import br.com.project.checkskills.repositories.dadosbasicos.IFuncionarioRepository;
import br.com.project.checkskills.utils.BaseEntity;

@ManagedBean(name = "feedbackBean")
@ViewScoped
public class FeedbackBean extends BaseEntity<Long> {

	private static final long serialVersionUID = 1L;

	private static final Logger LOGGER = Logger.getLogger(FeedbackBean.class);

	@ManagedProperty(value = "#{feedbackRepository}")
	private IFeedbackRepository feedbackRepository;

	@ManagedProperty(value = "#{feedbackEntity}")
	private FeedbackEntity feedbackEntity;

	@ManagedProperty(value = "#{usuarioRepository}")
	private IUsuarioRepository usuarioRepository;

	@ManagedProperty(value = "#{funcionarioRepository}")
	private IFuncionarioRepository funcionarioRepository;

	private List<FuncionarioEntity> funcionarioColecao;

	private List<FeedbackEntity> feedbacks;

	private Long id;

	private String codigo;
	private String acao;

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public void onLoad() {
		this.gerarMatrizFunPorDepartamento();
		carregarFeedbacksPorUsuario();
	}

	public void carregarFeedbacksPorUsuario() {
		feedbacks = new ArrayList<>();
		Long valor = isLider().getAvaliacao().getId();
		this.feedbackRepository.findAll().forEach(item -> filtrarFeedUsuario(item,valor));
	}

	public void filtrarFeedUsuario(FeedbackEntity entity, Long valor) {
	if (entity.getAvaliacaoEntity() != null)	
		if(valor == entity.getAvaliacaoEntity().getId())	
		feedbacks.add(entity);
	}

	// Obtem o funcionario Lider
	public FuncionarioEntity isLider() {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		Long valor = this.usuarioRepository.findByUsername(username).getId();

		return this.funcionarioRepository.findOne(valor);
	}

	public void gerarMatrizFunPorDepartamento() {
		if (isLider() != null && isLider().getId() != null) {

			FuncionarioEntity entity = this.funcionarioRepository.findOne(isLider().getId());

			entity.getCargo().getDepartamento();

			funcionarioColecao = this.funcionarioRepository
					.procuraEmDepartamentos(entity.getCargo().getDepartamento().getId());
		}

		funcionarioColecao = funcionarioColecao == null ? funcionarioColecao = new ArrayList<>()
				: new ArrayList<>(new HashSet<FuncionarioEntity>(funcionarioColecao));

	}

	public void carregarColecaoFun() {
		funcionarioColecao = new ArrayList<>();
		funcionarioColecao.add(isLider());
	}

	public String salvarOuDeletar() {
		if (this.feedbackEntity.getId() == null) {
			Date hoje = new Date();
			feedbackEntity.setDataCadastro(hoje);
			System.out.println((Date) hoje);
			this.feedbackRepository.save(feedbackEntity);
			Messages.addFlashGlobalInfo("Feed Back salva com sucesso");
		} else {
			this.feedbackRepository.save(feedbackEntity);
			Messages.addFlashGlobalInfo("Feed Back editada com sucesso");
		}
		LOGGER.info(feedbackEntity);
		return "/pages/feedback/feedbackDashboard.xhtml?faces-redirect=true";
	}

	public String deletar() {
		if (this.feedbackEntity.getId() != null)
			this.feedbackRepository.delete(this.feedbackEntity.getId());

		return "/pages/feedback/feedbackList.xhtml?faces-redirect=true";
	}

	public void loadCadastro() {
		try {
			if (codigo != null) {
				Long codigo = Long.parseLong(this.codigo);
				feedbackEntity = new FeedbackEntity();
				feedbackEntity = this.feedbackRepository.findOne(codigo);
				LOGGER.info(feedbackEntity);
				Messages.addFlashGlobalInfo("Dados carregados com sucesso");
			}
			if (acao.equals("ADICIONAR")) {
				this.feedbackEntity = new FeedbackEntity();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// botão cancelar
	public String cancel() {
		this.setFeedbackEntity(null);
		return "/pages/feedback/feedbackList.xhtml?faces-redirect=true";
	}

	// botão adicionar
	public void add() {
		this.feedbackEntity = new FeedbackEntity();
		this.feedbackEntity
				.setAvaliacaoEntity(this.funcionarioRepository.findOne(Long.parseLong(codigo)).getAvaliacao());
	}

	// botão editar
	public String editar() {
		return "/pages/feedback/feedbackAddEdit.xhtml?faces-redirect=true";
	}

	public String excluir() {
		return "/pages/feedback/feedbackAddEdit.xhtml?faces-redirect=true";
	}

	// todos get e set
	public IFeedbackRepository getFeedbackRepository() {
		return feedbackRepository;
	}

	public void setFeedbackRepository(IFeedbackRepository feedbackRepository) {
		this.feedbackRepository = feedbackRepository;
	}

	public List<FeedbackEntity> getFeedbacks() {
		return feedbacks;
	}

	public void setFeedbacks(List<FeedbackEntity> feedbacks) {
		this.feedbacks = feedbacks;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public FeedbackEntity getFeedbackEntity() {
		return feedbackEntity;
	}

	public void setFeedbackEntity(FeedbackEntity feedbackEntity) {
		this.feedbackEntity = feedbackEntity;
	}

	public String getAcao() {
		return acao;
	}

	public void setAcao(String acao) {
		this.acao = acao;
	}

	public IFuncionarioRepository getFuncionarioRepository() {
		return funcionarioRepository;
	}

	public void setFuncionarioRepository(IFuncionarioRepository funcionarioRepository) {
		this.funcionarioRepository = funcionarioRepository;
	}

	public List<FuncionarioEntity> getFuncionarioColecao() {
		return funcionarioColecao;
	}

	public void setFuncionarioColecao(List<FuncionarioEntity> funcionarioColecao) {
		this.funcionarioColecao = funcionarioColecao;
	}

	public IUsuarioRepository getUsuarioRepository() {
		return usuarioRepository;
	}

	public void setUsuarioRepository(IUsuarioRepository usuarioRepository) {
		this.usuarioRepository = usuarioRepository;
	}

}
