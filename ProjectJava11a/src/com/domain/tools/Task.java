package com.domain.tools;

import java.util.Date;
import java.util.Map;
import java.util.Map.Entry;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.transaction.annotation.Transactional;

import com.domain.interfaces.ITask;
import com.domain.model.TenderRequest;

public class Task implements ITask {
	@PersistenceContext(unitName = "springHibernate")
	EntityManager em;
	
	private int maxDaysForSave = 30;

	public Task() {
		// TODO Auto-generated constructor stub
	}

	public int getMaxDaysForSave() {
		return maxDaysForSave;
	}

	public void setMaxDaysForSave(int maxDaysForSave) {
		this.maxDaysForSave = maxDaysForSave;
	}

	@Override
	@Transactional
	@Async
	@Scheduled(cron = "1 0 0 * * ?")
	public void checkTenderStatus() {
		Date now = new Date();
		String tenderQuery = "select t from TenderRequest t";
		Query query = em.createQuery(tenderQuery);
		Iterable<TenderRequest> tenders = query.getResultList();
		if (tenders != null) {
			for (TenderRequest tender : tenders) {
				if (!tender.getStatus()) {
					if (now.after(tender.getCloseDate())) {
						Map.Entry<String, Float> winner = tender.getTenderResult();
						tender.setStatus(true);
						sendResultToClient(tender,winner);
					}
				} else {
					sendTenderToArchive(tender);
				}
			}
		}
	}

	private void sendResultToClient(TenderRequest tender, Entry<String, Float> winner) {
		// TODO Auto-generated method stub
		
	}

	private void sendTenderToArchive(TenderRequest tender) {
		long difference = tender.getCloseDate().getTime() - tender.getStartDate().getTime();
		int difDays = (int) (difference/(24*60*60*1000));
		if(difDays > maxDaysForSave){
			em.remove(tender);
		}

	}

}
