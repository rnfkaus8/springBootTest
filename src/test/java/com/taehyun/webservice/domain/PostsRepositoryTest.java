package com.taehyun.webservice.domain;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.time.LocalDateTime;
import java.util.List;

import com.taehyun.webservice.domain.posts.Posts;
import com.taehyun.webservice.domain.posts.PostsRepository;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PostsRepositoryTest {

    @Autowired
    PostsRepository postsRepository;
    
    @After
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

    @Test
    public void saveBaseTimeEntity(){
        LocalDateTime now = LocalDateTime.now();
        postsRepository.save(Posts.builder()
                .title("테스트 게시글")
                .content("테스트 본문")
                .author("kth4350@gmail.com")
                .build());
        List<Posts> postsList = postsRepository.findAll();

        Posts posts = postsList.get(0);
        assertTrue(posts.getCreatedDate().isAfter(now));
        assertTrue(posts.getModifiedDate().isAfter(now));

    }
}
