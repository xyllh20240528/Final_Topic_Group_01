package com.example.demo.service;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.demo.model.Messages;
import com.example.demo.model.MessagesRepository;

@Service
public class MessageService {
	
	@Autowired
	private MessagesRepository msgRepo;
	
	public Messages saveMessages(Messages msg) {
		return msgRepo.save(msg);	
	}
	
	public Messages findMsgById(Integer id) {
		Optional<Messages> optional = msgRepo.findById(id);
		
		if(optional.isPresent()) {
			return optional.get();
		}
		
		return null;
		
	}
	
	public void deleteMsgById(Integer id) {
		msgRepo.deleteById(id);
	}
	
	public Messages findLatest() {
		return msgRepo.findLatest();
	}
	
	public Page<Messages> findByPage(Integer pageNumber){
		Pageable pgb = PageRequest.of(pageNumber-1, 3,Sort.Direction.DESC,"added");
		Page<Messages> page = msgRepo.findAll(pgb);
		return page;
	}

}
