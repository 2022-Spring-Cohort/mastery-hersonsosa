package com.survivingcodingbootcamp.blog.controller;


import com.survivingcodingbootcamp.blog.model.Post;
import com.survivingcodingbootcamp.blog.repository.HashtagRepository;
import com.survivingcodingbootcamp.blog.repository.PostRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import com.survivingcodingbootcamp.blog.model.Hashtag;

import java.util.Optional;

@Controller
public class HashtagController{
    private PostRepository postRepo;
    private HashtagRepository hashtagRepository;

    public HashtagController(PostRepository postRepo, HashtagRepository hashtagRepository) {
        this.postRepo = postRepo;
        this.hashtagRepository = hashtagRepository;
    }

    @GetMapping("/hashtags")
    public String showAllHashtags(Model model){
        model.addAttribute("hashtag", hashtagRepository.findAll());
        return "all-hashtags-template";
    }
    @RequestMapping("/hashtags/{id}")
    public String displayHashtag(Model model,@PathVariable Long id) {
        model.addAttribute("hashtag", hashtagRepository.findById(id).get());
        model.addAttribute("post",hashtagRepository.findById(id).get().getPostCollection());
        return "single-hashtag-template";
    }
    @PostMapping("/add-hashtags/{id}")
    public String addHashtag(@PathVariable Long id,@RequestParam String hashtagName) {
        Post post = postRepo.findById(id).get();
        Hashtag hashtagToAdd;
        Optional<Hashtag> hashtagOptional = hashtagRepository.findByHashtagNameIgnoreCase(hashtagName);
        if (hashtagOptional.isEmpty()){
            Hashtag hashtag1 = new Hashtag(hashtagName);
            hashtagRepository.save(hashtag1);
            hashtagToAdd = hashtag1;}
        else {
            hashtagToAdd = hashtagOptional.get();
        }
        post.addHashtag(hashtagToAdd);
        postRepo.save(post);
        return "redirect:/posts/"+id;
    }
}
