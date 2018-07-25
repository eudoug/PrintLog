package com.fpc.repository;

import com.fpc.model.UsuarioModel;
import com.fpc.repository.entity.UsuarioEntity;
import com.fpc.uteis.Uteis;
import java.io.Serializable;
import javax.persistence.EntityManager;
import javax.persistence.Query;




public class UsuarioRepository
  implements Serializable
{
  private static final long serialVersionUID = 1L;
  
  public UsuarioRepository() {}
  
  public UsuarioEntity ValidaUsuario(UsuarioModel usuarioModel)
  {
    try
    {
      Query query = Uteis.JpaEntityManager().createNamedQuery("UsuarioEntity.findUser");
      

      query.setParameter("usuario", usuarioModel.getUsuario());
      query.setParameter("senha", usuarioModel.getSenha());
      

      return (UsuarioEntity)query.getSingleResult();
    }
    catch (Exception e) {}
    
    return null;
  }
}