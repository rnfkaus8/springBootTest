package com.taehyun.webservice.web;

import com.taehyun.webservice.domain.posts.PostsRepository;
import com.taehyun.webservice.dto.posts.PostsSaveRequestDto;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class WebRestController {

    private PostsRepository postsRepository;

    @GetMapping("/hello")
    public String hello(){
        return "HelloWorld";
    }

    public void savePosts(@RequestBody PostsSaveRequestDto dto){
        postsRepository.save(dto.toEntity());
    }

}