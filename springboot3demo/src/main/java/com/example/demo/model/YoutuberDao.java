package com.example.demo.model;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

@Repository
public class YoutuberDao {
	
	@PersistenceContext
	private EntityManager em;
	
	@Transactional  // AOP 模式的一種實作
	public void saveYtr(Youtuber ytr) {
		
//		EntityTransaction tx = em.getTransaction();
//		
//		tx.begin();
		
		em.persist(ytr);
		
//		tx.commit();
	}
	
	
	@Transactional(readOnly = true)
	public Youtuber findById(Integer id) {
		return em.find(Youtuber.class, id);
	}
	
	@Transactional
	public Youtuber updateSubCount(Integer ytrId) {
		Youtuber youtuber = em.find(Youtuber.class, ytrId);
		
		if(youtuber != null) {
			youtuber.setSubscribeCount(youtuber.getSubscribeCount() + 1);
			return youtuber;
		}
		
		return null;
	}
	
	@Transactional
	public boolean deleteById(Integer id) {
		Youtuber youtuber = em.find(Youtuber.class, id);
		
		if(youtuber != null) {
			em.remove(youtuber);
			return true;
		}
		
		return false;
		
	}
	
	@Transactional(readOnly = true)
	public List<Youtuber> findByName(String name){
		
		String hql = "from Youtuber where channelName = :name";
		
		Query query = em.createQuery(hql, Youtuber.class);
		
		query.setParameter("name", name);
		
		List<Youtuber> resultList = query.getResultList();
		
		return resultList;
	}
	
	

}
