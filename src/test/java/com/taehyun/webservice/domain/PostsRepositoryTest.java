package com.taehyun.webservice.domain;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.List;

import com.taehyun.webservice.domain.posts.Posts;
import com.taehyun.webservice.domain.posts.PostsRepository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class PostsRepositoryTest {

    @Autowired
    PostsRepository postsRepository;
    
    @AfterEach
    public void cleanup(){
        postsRepository.deleteAll();
    }

    @Test
    public void save_load_content(){
        //given
        postsRepository.save(Posts.builder()
                    .title("테스트 게시물")
                    .content("테스트 본문")
                    .author("kth4350@gmail.com")
                    .build());
        
        //when
        List<Posts> postsList = postsRepository.findAll();

        //then
        Posts posts = postsList.get(0);
        assertThat(posts.getTitle(), is("테스트 게시물"));
        assertThat(posts.getContent(), is("테스트 본문"));
    }
}
