package com.survivingcodingbootcamp.blog.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.*;

//POJO

@Entity
public class Hashtag {

    @Id
    @GeneratedValue
    private Long id;
    public String hashtagName;
    @ManyToMany(mappedBy = "hashtags")
    private Collection<Post> posts;

    public Hashtag(String hashtagName, Post... posts) {
        this.hashtagName = hashtagName;

    }

    public Hashtag(){}

    public Long getId() {
        return id;
    }

    public String getHashtagName() {
        return hashtagName;
    }

    public Collection<Post> getPostCollection() {
        return posts;
    }

    public void addPost(Post post){
        posts.add(post);
    }

    @Override
    public String toString(){
        return "Hashtag {id="+id+", hashtagName="+hashtagName+"}";
    }
}

