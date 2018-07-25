package com.fpc.filters;

import java.io.IOException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;






@WebFilter(servletNames={"Faces Servlet"})
public class JPAFilter
  implements Filter
{
  private EntityManagerFactory entityManagerFactory;
  private String persistence_unit_name = "unit_app";
  

  public JPAFilter() {}
  

  public void destroy()
  {
    entityManagerFactory.close();
  }
  
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
    throws IOException, ServletException
  {
    EntityManager entityManager = entityManagerFactory.createEntityManager();
    

    request.setAttribute("entityManager", entityManager);
    

    entityManager.getTransaction().begin();
    

    chain.doFilter(request, response);
    

    try
    {
      entityManager.getTransaction().commit();

    }
    catch (Exception e)
    {
      entityManager.getTransaction().rollback();

    }
    finally
    {
      entityManager.close();
    }
  }
  
  public void init(FilterConfig fConfig)
    throws ServletException
  {
    entityManagerFactory = Persistence.createEntityManagerFactory(persistence_unit_name);
  }
}