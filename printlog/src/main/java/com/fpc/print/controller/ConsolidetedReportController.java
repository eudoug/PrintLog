package com.fpc.print.controller;

import com.fpc.model.UserLogPrint;
import com.fpc.repository.PrintRepository;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named("consolidatedController")
@ViewScoped
public class ConsolidetedReportController implements Serializable
{
  private static final long serialVersionUID = 1L;
  private Integer year;
  private List<Integer> years;
  private List<String> months;
  private Map<String, String> monthsNameMap;
  private Map<String, Set<UserLogPrint>> printsMap;
  @Inject
  private transient PrintRepository printRepository;
  
  public ConsolidetedReportController() {}
  
  @PostConstruct
  public void init()
  {
    initYears();
    months = new ArrayList();
    initMonthsNameMap();
  }
  
  private void initMonthsNameMap() {
    monthsNameMap = new HashMap();
    monthsNameMap.put("1", "Janeiro");
    monthsNameMap.put("2", "Fevereiro");
    monthsNameMap.put("3", "Março");
    monthsNameMap.put("4", "Abril");
    monthsNameMap.put("5", "Maio");
    monthsNameMap.put("6", "Junho");
    monthsNameMap.put("7", "Julho");
    monthsNameMap.put("8", "Agosto");
    monthsNameMap.put("9", "Setembro");
    monthsNameMap.put("10", "Outubro");
    monthsNameMap.put("11", "Novembro");
    monthsNameMap.put("12", "Dezembro");
  }
  
  private void initYears()
  {
    years = new ArrayList();
    LocalDateTime now = LocalDateTime.now();
    Integer currentYear = Integer.valueOf(now.getYear());
    Integer firstYear = Integer.valueOf(2015);
    now = now.minus(currentYear.intValue() - firstYear.intValue(), java.time.temporal.ChronoUnit.YEARS);
    for (Integer year = firstYear; year.intValue() <= currentYear.intValue(); year = Integer.valueOf(year.intValue() + 1)) {
      years.add(year);
    }
  }
  
  public Integer getYear() {
    return year;
  }
  
  public void setYear(Integer year) {
    this.year = year;
  }
  
  public List<Integer> getYears() {
    return years;
  }
  
  public void setYears(List<Integer> years) {
    this.years = years;
  }
  
  public PrintRepository getPrintRepository() {
    return printRepository;
  }
  
  public void setPrintRepository(PrintRepository printRepository) {
    this.printRepository = printRepository;
  }
  
  public List<String> getMonths() {
    return months;
  }
  
  public void setMonths(List<String> months) {
    this.months = months;
  }
  
  public void findPrintLogs() {
    if (printRepository != null) {
      printsMap = printRepository.getUserTotalPages(year.intValue());
      if ((printsMap != null) && (!printsMap.isEmpty())) {
        months = new ArrayList(printsMap.keySet());
      } else {
        months = new ArrayList();
      }
    }
  }
  
  public Integer totalPrintsByMonth(String month) {
    Integer total = Integer.valueOf(0);
    Set<UserLogPrint> prints = printsByMonth(month);
    if ((prints != null) && (!prints.isEmpty())) {
      for (UserLogPrint userLogPrint : prints) {
        total = Integer.valueOf(total.intValue() + userLogPrint.getTotal().intValue());
      }
    }
    return total;
  }
  
  public Set<UserLogPrint> printsByMonth(String month) {
    Set<UserLogPrint> prints = new HashSet();
    if ((printsMap != null) && (!printsMap.isEmpty())) {
      prints = (Set)printsMap.get(month);
    }
    return prints;
  }
  
  public String getMonthName(String month) {
    if (month != null) {
      return (String)monthsNameMap.get(month);
    }
    return "";
  }
}
