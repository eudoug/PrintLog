package com.fpc.print.controller;

import com.fpc.model.PrintModel;
import com.fpc.repository.PrintRepository;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.inject.Produces;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;












@Named("consultarPrintController")
@ViewScoped
public class ConsultarPrintController
  implements Serializable
{
  private static final long serialVersionUID = 1L;
  @Inject
  private transient PrintModel printModel;
  @Produces
  private List<PrintModel> prints;
  private List<PrintModel> allPrints;
  @Inject
  private transient PrintRepository printRepository;
  private List<String> users;
  private List<PrintModel> filteredLogs;
  private String selectedUser;
  
  @PostConstruct
  public void init()
  {
    allPrints = printRepository.GetPrints();
    filterByUser();
  }
  
  private void createUserList() {
    users = new ArrayList();
    if (prints != null) {
      for (PrintModel p : prints) {
        if (!users.contains(p.getUser()))
          users.add(p.getUser());
      }
    }
  }
  
  public List<String> getUsers() {
    return users;
  }
  
  public void setUsers(List<String> users) {
    this.users = users;
  }
  
  public void filterByUser() {
    prints = new ArrayList();
    if ((selectedUser != null) && (!selectedUser.trim().equals(""))) {
      for (PrintModel printModel : allPrints) {
        if (selectedUser.equals(printModel.getUser())) {
          prints.add(printModel);
        }
      }
    } else {
      prints = allPrints;
    }
    createUserList();
  }
  
  public String getSelectedUser() {
    return selectedUser;
  }
  
  public void setSelectedUser(String selectedUser) {
    this.selectedUser = selectedUser;
  }
  
  public List<PrintModel> getFilteredLogs() {
    return filteredLogs;
  }
  
  public void setFilteredLogs(List<PrintModel> filteredLogs) {
    this.filteredLogs = filteredLogs;
  }
  

  public ConsultarPrintController() {}
  
  public List<PrintModel> getPrints()
  {
    return prints;
  }
  
  public void setPrints(List<PrintModel> prints) {
    this.prints = prints;
  }
  
  public PrintModel getPrintModel() {
    return printModel;
  }
  
  public void setPrintModel(PrintModel printModel) {
    this.printModel = printModel;
  }
}