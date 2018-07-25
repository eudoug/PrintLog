package com.fpc.uteis;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

public class Uteis
{
  public Uteis() {}
  
  public static javax.persistence.EntityManager JpaEntityManager()
  {
    FacesContext facesContext = FacesContext.getCurrentInstance();
    
    javax.faces.context.ExternalContext externalContext = facesContext.getExternalContext();
    
    HttpServletRequest request = (HttpServletRequest)externalContext.getRequest();
    
    return (javax.persistence.EntityManager)request.getAttribute("entityManager");
  }
  

  public static void Mensagem(String mensagem)
  {
    FacesContext facesContext = FacesContext.getCurrentInstance();
    
    facesContext.addMessage(null, new FacesMessage("Alerta", mensagem));
  }
  

  public static void MensagemAtencao(String mensagem)
  {
    FacesContext facesContext = FacesContext.getCurrentInstance();
    
    facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Atenção:", mensagem));
  }
  

  public static void MensagemInfo(String mensagem)
  {
    FacesContext facesContext = FacesContext.getCurrentInstance();
    
    facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", mensagem));
  }
}
