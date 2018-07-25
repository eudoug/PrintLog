package com.fpc.repository.entity;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.Table;


@Entity
@Table(name="printlog")
@NamedQueries({@javax.persistence.NamedQuery(name="PrintEntity.findAll", query="SELECT e FROM PrintEntity e")})
public class PrintEntity
{
  @Id
  @Column(name="id")
  private Integer id;
  @Column(name="eventid")
  private Integer eventid;
  @Column(name="jobid")
  private Integer jobid;
  @Column(name="user")
  private String user;
  @Column(name="client")
  private String client;
  @Column(name="timecreated")
  private LocalDateTime timecreated;
  @Column(name="filename")
  private String filename;
  @Column(name="printer")
  private String printer;
  @Column(name="address")
  private String address;
  @Column(name="jobbytes")
  private Integer jobbytes;
  @Column(name="pagecount")
  private Integer pagecount;
  @Column(name="numberofcopies")
  private Integer numberofcopies;
  @Column(name="totalpages")
  private Integer totalpages;
  
  public PrintEntity() {}
  
  public Integer getId()
  {
    return id;
  }
  
  public void setId(Integer id) {
    this.id = id;
  }
  
  public Integer getEventid() {
    return eventid;
  }
  
  public void setEventid(Integer eventid) {
    this.eventid = eventid;
  }
  
  public Integer getJobid() {
    return jobid;
  }
  
  public void setJobid(Integer jobid) {
    this.jobid = jobid;
  }
  
  public String getUser() {
    return user;
  }
  
  public void setUser(String user) {
    this.user = user;
  }
  
  public String getClient() {
    return client;
  }
  
  public void setClient(String client) {
    this.client = client;
  }
  
  public LocalDateTime getTimecreated() {
    return timecreated;
  }
  
  public void setTimecreated(LocalDateTime timecreated) {
    this.timecreated = timecreated;
  }
  
  public String getFilename() {
    return filename;
  }
  
  public void setFilename(String filename) {
    this.filename = filename;
  }
  
  public String getPrinter() {
    return printer;
  }
  
  public void setPrinter(String printer) {
    this.printer = printer;
  }
  
  public String getAddress() {
    return address;
  }
  
  public void setAddress(String address) {
    this.address = address;
  }
  
  public Integer getJobbytes() {
    return jobbytes;
  }
  
  public void setJobbytes(Integer jobbytes) {
    this.jobbytes = jobbytes;
  }
  
  public Integer getPagecount() {
    return pagecount;
  }
  
  public void setPagecount(Integer pagecount) {
    this.pagecount = pagecount;
  }
  
  public Integer getNumberofcopies() {
    return numberofcopies;
  }
  
  public void setNumberofcopies(Integer numberofcopies) {
    this.numberofcopies = numberofcopies;
  }
  
  public Integer getTotalpages() {
    return totalpages;
  }
  
  public void setTotalpages(Integer totalpages) {
    this.totalpages = totalpages;
  }
}