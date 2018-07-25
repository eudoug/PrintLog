package com.fpc.model;

import java.time.LocalDateTime;

public class PrintModel {
  private Integer id;
  private Integer eventid;
  private Integer jobid;
  private String user;
  private String client;
  private LocalDateTime timecreated;
  private String filename;
  private String printer;
  private String address;
  private Integer jobbytes;
  private Integer pagecount;
  private Integer numberofcopies;
  private Integer totalpages;
  private PrintModel printModel;
  
  public PrintModel() {}
  
  public Integer getId() {
    return id;
  }
  
  public void setId(Integer id) { this.id = id; }
  
  public Integer getEventid() {
    return eventid;
  }
  
  public void setEventid(Integer eventid) { this.eventid = eventid; }
  
  public Integer getJobid() {
    return jobid;
  }
  
  public void setJobid(Integer jobid) { this.jobid = jobid; }
  
  public String getUser() {
    return user;
  }
  
  public void setUser(String user) { this.user = user; }
  
  public String getClient() {
    return client;
  }
  
  public void setClient(String client) { this.client = client; }
  
  public LocalDateTime getTimecreated() {
    return timecreated;
  }
  
  public void setTimecreated(LocalDateTime timecreated) { this.timecreated = timecreated; }
  
  public String getFilename() {
    return filename;
  }
  
  public void setFilename(String filename) { this.filename = filename; }
  
  public String getPrinter() {
    return printer;
  }
  
  public void setPrinter(String printer) { this.printer = printer; }
  
  public String getAddress() {
    return address;
  }
  
  public void setAddress(String address) { this.address = address; }
  
  public Integer getJobbytes() {
    return jobbytes;
  }
  
  public void setJobbytes(Integer jobbytes) { this.jobbytes = jobbytes; }
  
  public Integer getPagecount() {
    return pagecount;
  }
  
  public void setPagecount(Integer pagecount) { this.pagecount = pagecount; }
  
  public Integer getNumberofcopies() {
    return numberofcopies;
  }
  
  public void setNumberofcopies(Integer numberofcopies) { this.numberofcopies = numberofcopies; }
  
  public Integer getTotalpages() {
    return totalpages;
  }
  
  public void setTotalpages(Integer totalpages) { this.totalpages = totalpages; }
  
  public PrintModel getPrintModel() {
    return printModel;
  }
  
  public void setPrintModel(PrintModel printModel) { this.printModel = printModel; }
}