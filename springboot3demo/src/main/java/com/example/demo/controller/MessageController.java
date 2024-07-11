package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.model.Messages;
import com.example.demo.service.MessageService;

@Controller
public class MessageController {
	
	@Autowired
	private MessageService msgService;
	
	@GetMapping("/messages/add")
	public String addMsg(Model model) {
        Messages lastestMsg = msgService.findLatest();
		
		model.addAttribute("lastestMsg", lastestMsg);
		
		return "messages/addMsgPage";
	}
	
	@PostMapping("/messages/addPost")
	public String addMsgPost(@RequestParam String text,Model model) {
		
		Messages msg = new Messages();
		msg.setText(text);
		
		msgService.saveMessages(msg);
		
		Messages lastestMsg = msgService.findLatest();
		
		model.addAttribute("lastestMsg", lastestMsg);
		
		return "messages/addMsgPage";
	}
	
	@GetMapping("/messages/show")
	public String showMessages(@RequestParam(value="p", defaultValue = "1") Integer pageNum, Model model) {
		Page<Messages> page = msgService.findByPage(pageNum);
		
		model.addAttribute("page", page);
		
		return "messages/showMsgPage";
	}
	
	@GetMapping("/messages/update")
	public String editMsg(@RequestParam Integer id, Model model) {
		Messages messages = msgService.findMsgById(id);
		
		model.addAttribute("messages", messages);
		
		return "messages/editMsgPage";
	}
	
	@PostMapping("/messages/updatePost")
	public String editMsgPost(@ModelAttribute Messages msg) {
		msgService.saveMessages(msg);
		
		return "redirect:/messages/show";
	}
	
	@GetMapping("/messages/delete")
    public String deleteMsg(@RequestParam Integer id) {
		msgService.deleteMsgById(id);
		return "redirect:/messages/show";
	}
}
