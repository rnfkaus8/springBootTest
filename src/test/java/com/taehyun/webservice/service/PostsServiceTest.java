package com.taehyun.webservice.service;


import static org.junit.Assert.assertTrue;

import com.taehyun.webservice.domain.posts.Posts;
import com.taehyun.webservice.domain.posts.PostsRepository;
import com.taehyun.webservice.dto.posts.PostsSaveRequestDto;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PostsServiceTest {
    
    @Autowired
    private PostsService postsService;

    @Autowired
    private PostsRepository postsRepository;

    @After
    public void cleanup(){
        postsRepository.deleteAll();
    }

    @Test
    public void saveDtoDataToPostsTable(){
        //given
        PostsSaveRequestDto dto = PostsSaveRequestDto.builder()
                .author("kth4350@gmail.com")
                .content("테스트")
                .title("테스트 타이틀")
                .build();
        
        //when
        postsService.save(dto);

        //then
        Posts posts = postsRepository.findAll().get(0);
        assertTrue(posts.getAuthor().equals(dto.getAuthor()));
        assertTrue(posts.getTitle().equals(dto.getTitle()));
        assertTrue(posts.getContent().equals(dto.getContent()));



                
    }


}
