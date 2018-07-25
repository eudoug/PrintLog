package com.fpc.repository;

import java.util.HashSet;
import java.util.HashMap;
import com.fpc.model.UserLogPrint;
import java.util.Set;
import java.util.Map;
import java.util.Iterator;
import java.util.Collection;
import javax.persistence.Query;
import com.fpc.uteis.Uteis;
import java.util.ArrayList;
import com.fpc.model.PrintModel;
import java.util.List;
import javax.persistence.EntityManager;
import javax.inject.Inject;
import com.fpc.repository.entity.PrintEntity;

public class PrintRepository
{
    @Inject
    PrintEntity printEntity;
    EntityManager entityManager;
    
    public List<PrintModel> GetPrints() {
        final List<PrintModel> printsModel = new ArrayList<PrintModel>();
        this.entityManager = Uteis.JpaEntityManager();
        final Query query = this.entityManager.createNamedQuery("PrintEntity.findAll");
        final Collection<PrintEntity> printsEntity = (Collection<PrintEntity>)query.getResultList();
        PrintModel printModel = null;
        for (final PrintEntity printEntity : printsEntity) {
            printModel = new PrintModel();
            printModel.setId(printEntity.getId());
            printModel.setEventid(printEntity.getEventid());
            printModel.setJobid(printEntity.getJobid());
            printModel.setUser(printEntity.getUser());
            printModel.setClient(printEntity.getClient());
            printModel.setTimecreated(printEntity.getTimecreated());
            printModel.setFilename(printEntity.getFilename());
            printModel.setPrinter(printEntity.getPrinter());
            printModel.setAddress(printEntity.getAddress());
            printModel.setJobbytes(printEntity.getJobbytes());
            printModel.setPagecount(printEntity.getPagecount());
            printModel.setNumberofcopies(printEntity.getNumberofcopies());
            printModel.setTotalpages(printEntity.getTotalpages());
            printsModel.add(printModel);
        }
        return printsModel;
    }
    
    public Map<String, Set<UserLogPrint>> getUserTotalPages(final int ano) {
        final List<PrintModel> prints = new ArrayList<PrintModel>();
        this.entityManager = Uteis.JpaEntityManager();
        final Query query = this.entityManager.createQuery("from PrintEntity where year(timecreated) = :year");
        query.setParameter("year", (Object)ano);
        final List<PrintEntity> l = (List<PrintEntity>)query.getResultList();
        final Map<String, Set<UserLogPrint>> map2 = new HashMap<String, Set<UserLogPrint>>();
        for (final PrintEntity log : l) {
            final String m = String.valueOf(log.getTimecreated().getMonthValue());
            if (map2.containsKey(m)) {
                final Set<UserLogPrint> logsOnMonth = map2.get(m);
                final UserLogPrint userLog = this.getObject(logsOnMonth, log.getUser());
                userLog.incrementPrintLog(log.getTotalpages());
            }
            else {
                final Set<UserLogPrint> logsOnMonth = new HashSet<UserLogPrint>();
                logsOnMonth.add(new UserLogPrint(log.getUser(), log.getTotalpages()));
                map2.put(m, logsOnMonth);
            }
        }
        final Set<String> meses = map2.keySet();
        for (final String mes : meses) {
            final Set<UserLogPrint> set = map2.get(mes);
        }
        return map2;
    }
    
    private UserLogPrint getObject(final Set<UserLogPrint> collection, final String user) {
        for (final UserLogPrint object : collection) {
            if (object.equals(new UserLogPrint(user, null))) {
                return object;
            }
        }
        final UserLogPrint newUserLogPrint = new UserLogPrint(user, 0);
        collection.add(newUserLogPrint);
        return newUserLogPrint;
    }
}