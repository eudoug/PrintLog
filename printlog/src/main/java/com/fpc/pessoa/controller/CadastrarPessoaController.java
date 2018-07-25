package com.fpc.pessoa.controller;

import com.fpc.model.PessoaModel;
import com.fpc.repository.PessoaRepository;
import com.fpc.usuario.controller.UsuarioController;
import com.fpc.uteis.Uteis;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;


@Named("cadastrarPessoaController")
@RequestScoped
public class CadastrarPessoaController
{
  @Inject
  PessoaModel pessoaModel;
  @Inject
  UsuarioController usuarioController;
  @Inject
  PessoaRepository pessoaRepository;
  
  public CadastrarPessoaController() {}
  
  public PessoaModel getPessoaModel()
  {
    return pessoaModel;
  }
  
  public void setPessoaModel(PessoaModel pessoaModel) {
    this.pessoaModel = pessoaModel;
  }
  



  public void SalvarNovaPessoa()
  {
    pessoaModel.setUsuarioModel(usuarioController.GetUsuarioSession());
    

    pessoaModel.setOrigemCadastro("I");
    
    pessoaRepository.SalvarNovoRegistro(pessoaModel);
    
    pessoaModel = null;
    
    Uteis.MensagemInfo("Registro cadastrado com sucesso");
  }
}