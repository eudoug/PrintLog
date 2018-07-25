package com.fpc.pessoa.controller;

import com.fpc.model.PessoaModel;
import com.fpc.repository.PessoaRepository;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.inject.Produces;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;



@Named("consultarPessoaController")
@ViewScoped
public class ConsultarPessoaController
  implements Serializable
{
  private static final long serialVersionUID = 1L;
  @Inject
  private transient PessoaModel pessoaModel;
  @Produces
  private List<PessoaModel> pessoas;
  @Inject
  private transient PessoaRepository pessoaRepository;
  
  public ConsultarPessoaController() {}
  
  public List<PessoaModel> getPessoas()
  {
    return pessoas;
  }
  
  public void setPessoas(List<PessoaModel> pessoas) { this.pessoas = pessoas; }
  
  public PessoaModel getPessoaModel() {
    return pessoaModel;
  }
  
  public void setPessoaModel(PessoaModel pessoaModel) { this.pessoaModel = pessoaModel; }
  






  @PostConstruct
  public void init()
  {
    pessoas = pessoaRepository.GetPessoas();
  }
}