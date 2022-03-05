package com.survivingcodingbootcamp.blog.controller;

import com.survivingcodingbootcamp.blog.model.Post;
import com.survivingcodingbootcamp.blog.model.Topic;
import com.survivingcodingbootcamp.blog.repository.PostRepository;
import com.survivingcodingbootcamp.blog.repository.TopicRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/topics")
public class TopicController {
    private TopicRepository topicRepo;
    private PostRepository postRepository;

    public TopicController(TopicRepository topicRepo, PostRepository postRepository) {
        this.postRepository = postRepository;
        this.topicRepo = topicRepo;
    }
    @GetMapping("/{id}")
    public String displaySingleTopic(@PathVariable long id, Model model) {
        model.addAttribute("topic", topicRepo.findById(id).get());
        return "single-topic-template";
    }
    @PostMapping("{id}/add-post")
    public String addNewPost(@PathVariable long id, @RequestParam String title, @RequestParam String author, @RequestParam String content) {
        Topic topic = topicRepo.findById(id).get();
        Post post = new Post(title,topic,author,content);
        postRepository.save(post);
        topic.addPost(post);
        topicRepo.save(topic);

        return "redirect:/topics/" + id;
    }

}
