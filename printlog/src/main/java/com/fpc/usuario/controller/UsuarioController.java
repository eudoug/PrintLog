package com.fpc.usuario.controller;

import com.fpc.model.UsuarioModel;
import com.fpc.repository.UsuarioRepository;
import com.fpc.repository.entity.UsuarioEntity;
import com.fpc.uteis.Uteis;
import java.io.Serializable;
import java.util.Map;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import org.apache.commons.lang3.StringUtils;

@Named("usuarioController")
@SessionScoped
public class UsuarioController
  implements Serializable
{
  private static final long serialVersionUID = 1L;
  @Inject
  private UsuarioModel usuarioModel;
  @Inject
  private UsuarioRepository usuarioRepository;
  @Inject
  private UsuarioEntity usuarioEntity;
  
  public UsuarioController() {}
  
  public UsuarioModel getUsuarioModel()
  {
    return usuarioModel;
  }
  
  public void setUsuarioModel(UsuarioModel usuarioModel) { this.usuarioModel = usuarioModel; }
  

  public UsuarioModel GetUsuarioSession()
  {
    FacesContext facesContext = FacesContext.getCurrentInstance();
    
    return (UsuarioModel)facesContext.getExternalContext().getSessionMap().get("usuarioAutenticado");
  }
  
  public String Logout()
  {
    FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
    
    return "/index.xhtml?faces-redirect=true";
  }
  
  public String EfetuarLogin() {
    if ((StringUtils.isEmpty(usuarioModel.getUsuario())) || (StringUtils.isBlank(usuarioModel.getUsuario())))
    {
      Uteis.Mensagem("Favor informar o login!");
      return null;
    }
    if ((StringUtils.isEmpty(usuarioModel.getSenha())) || (StringUtils.isBlank(usuarioModel.getSenha())))
    {
      Uteis.Mensagem("Favor informara senha!");
      return null;
    }
    

    usuarioEntity = usuarioRepository.ValidaUsuario(usuarioModel);
    
    if (usuarioEntity != null)
    {
      usuarioModel.setSenha(null);
      usuarioModel.setCodigo(usuarioEntity.getCodigo());
      

      FacesContext facesContext = FacesContext.getCurrentInstance();
      
      facesContext.getExternalContext().getSessionMap().put("usuarioAutenticado", usuarioModel);
      

      return "sistema/home?faces-redirect=true";
    }
    

    Uteis.Mensagem("Não foi possível efetuar o login com esse usuário e senha!");
    return null;
  }
}
