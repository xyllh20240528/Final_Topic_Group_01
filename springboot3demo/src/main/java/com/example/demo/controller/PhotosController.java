package com.example.demo.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.model.Photos;
import com.example.demo.model.PhotosRepository;

@Controller
public class PhotosController {
	
	@Autowired
	private PhotosRepository photosRepo;

	@GetMapping("/photos/upload")
	public String photoUpload() {
		return "photos/uploadPage";
	}
	
	@PostMapping("/photos/uploadPost")
	public String uploadPost(@RequestParam String name, @RequestParam MultipartFile file,Model model) throws IOException {
		
		Photos photos = new Photos();
		
		photos.setName(name);
		
		photos.setPhotoFile(file.getBytes());
		
		photosRepo.save(photos);
		
		model.addAttribute("okMsg", "上傳OK");
		
		return "photos/uploadPage";
	}
	
}
