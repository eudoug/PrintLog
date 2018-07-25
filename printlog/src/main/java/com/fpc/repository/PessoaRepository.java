package com.fpc.repository;

import com.fpc.model.PessoaModel;
import com.fpc.model.UsuarioModel;
import com.fpc.repository.entity.PessoaEntity;
import com.fpc.repository.entity.UsuarioEntity;
import com.fpc.uteis.Uteis;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;






public class PessoaRepository
{
  @Inject
  PessoaEntity pessoaEntity;
  EntityManager entityManager;
  
  public PessoaRepository() {}
  
  public void SalvarNovoRegistro(PessoaModel pessoaModel)
  {
    entityManager = Uteis.JpaEntityManager();
    
    pessoaEntity = new PessoaEntity();
    pessoaEntity.setDataCadastro(LocalDateTime.now());
    pessoaEntity.setEmail(pessoaModel.getEmail());
    pessoaEntity.setEndereco(pessoaModel.getEndereco());
    pessoaEntity.setNome(pessoaModel.getNome());
    pessoaEntity.setOrigemCadastro(pessoaModel.getOrigemCadastro());
    pessoaEntity.setSexo(pessoaModel.getSexo());
    
    UsuarioEntity usuarioEntity = (UsuarioEntity)entityManager.find(UsuarioEntity.class, pessoaModel.getUsuarioModel().getCodigo());
    
    pessoaEntity.setUsuarioEntity(usuarioEntity);
    
    entityManager.persist(pessoaEntity);
  }
  





  public List<PessoaModel> GetPessoas()
  {
    List<PessoaModel> pessoasModel = new ArrayList();
    
    entityManager = Uteis.JpaEntityManager();
    
    Query query = entityManager.createNamedQuery("PessoaEntity.findAll");
    

    Collection<PessoaEntity> pessoasEntity = query.getResultList();
    
    PessoaModel pessoaModel = null;
    
    for (PessoaEntity pessoaEntity : pessoasEntity)
    {
      pessoaModel = new PessoaModel();
      pessoaModel.setCodigo(pessoaEntity.getCodigo());
      pessoaModel.setDataCadastro(pessoaEntity.getDataCadastro());
      pessoaModel.setEmail(pessoaEntity.getEmail());
      pessoaModel.setEndereco(pessoaEntity.getEndereco());
      pessoaModel.setNome(pessoaEntity.getNome());
      
      if (pessoaEntity.getOrigemCadastro().equals("X")) {
        pessoaModel.setOrigemCadastro("XML");
      } else {
        pessoaModel.setOrigemCadastro("INPUT");
      }
      
      if (pessoaEntity.getSexo().equals("M")) {
        pessoaModel.setSexo("Masculino");
      } else {
        pessoaModel.setSexo("Feminino");
      }
      UsuarioEntity usuarioEntity = pessoaEntity.getUsuarioEntity();
      
      UsuarioModel usuarioModel = new UsuarioModel();
      usuarioModel.setUsuario(usuarioEntity.getUsuario());
      
      pessoaModel.setUsuarioModel(usuarioModel);
      
      pessoasModel.add(pessoaModel);
    }
    
    return pessoasModel;
  }
}