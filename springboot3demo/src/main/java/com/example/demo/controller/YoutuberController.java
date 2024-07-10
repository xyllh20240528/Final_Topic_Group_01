package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Youtuber;
import com.example.demo.model.YoutuberDao;

@RestController
public class YoutuberController {
	
	@Autowired
	private YoutuberDao ytDao;
	
	@GetMapping("/ytr/insert")
	public String insertYtr() {
		Youtuber ytr = new Youtuber();
		ytr.setChannelName("蛇丸");
		ytr.setSubscribeCount(1000000);
		
		ytDao.saveYtr(ytr);
		
		return "新增OK";
		
	}
	
	@GetMapping("/ytr/find")
	public Youtuber findYtrById(@RequestParam("id") Integer id) {
		return ytDao.findById(id);
	}
	
	@GetMapping("/ytr/addCount")
	public String updateCountById(@RequestParam("id") Integer id) {
		Youtuber youtuber = ytDao.updateSubCount(id);
		
		if(youtuber != null) {
			return "加了一個訂閱者";
		}	
		
		return "沒有這個 id 的頻道";
	}
	
	@GetMapping("/ytr/remove")
	public String deleteYtrById(@RequestParam Integer id) {
		boolean result = ytDao.deleteById(id);
		
		if(result) {
			return "有資料，已刪除";
		}
		
		return "沒有這筆資料";
	}
	
//	http://localhost:8080/myapp/ytr/findByName?name=%E8%9B%87%E4%B8%B8
	@GetMapping("/ytr/findByName")
	public List<Youtuber> findYtrByName(@RequestParam("name") String name){
		return ytDao.findByName(name);
	}

}
