package com.myblog10.service;

import com.myblog10.payload.PostDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface PostService {

    public PostDto createPost(PostDto postDto);

    public PostDto getPost( long id );

    public List<PostDto> getAllPost(int pageNo, int pageSize, String sortBy,String sortDir);

    void deletePost(long id);

    PostDto updatePost( long id, PostDto postDto);

}

