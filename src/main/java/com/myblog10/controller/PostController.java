package com.myblog10.controller;


import com.myblog10.payload.PostDto;
import com.myblog10.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {


    private PostService postService;

    public PostController(PostService postService){
        this.postService=postService;

    }



//http://localHost:8080/api/posts
    //http://localhost:8080/api/posts
    @PostMapping
   public ResponseEntity<?> createPost(@Valid @RequestBody PostDto postDto, BindingResult result) {

        if(result.hasErrors()){
           return new ResponseEntity<>(result.getFieldError().getDefaultMessage(),HttpStatus.INTERNAL_SERVER_ERROR);

        }
        PostDto dto=postService.createPost(postDto);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);


   }

   @GetMapping("/{id}")
   public ResponseEntity<PostDto> getPost(@PathVariable("id") long id ){
      PostDto  dto=postService.getPost(id );
      return new ResponseEntity<>(dto,HttpStatus.OK);


   }

   @GetMapping
   public List<PostDto> getAllPost(
          @RequestParam(value="pageNo", defaultValue ="0", required=false)int pageNo,
           @RequestParam(value="pageSize", defaultValue = "5", required=false)int pageSize,
          @RequestParam(value="sortBy", defaultValue="title", required = false) String sortBy,
          @RequestParam(value="sortDir", defaultValue="asc", required=false) String sortDir



   ){
       List<PostDto> dto=postService.getAllPost(pageNo,pageSize,sortBy,sortDir);
            return dto;

   }
   @DeleteMapping("/{id}")
   public ResponseEntity<String> deletePost(@PathVariable("id") long id){
       postService.deletePost(id);
       return new ResponseEntity<>("post is delete!!",HttpStatus.OK);

   }

   @PutMapping("/{id}")
   public ResponseEntity<PostDto> updatePost(@PathVariable("id") long id,@RequestBody PostDto postDto){
      PostDto dto= postService.updatePost(id,postDto);
        return new ResponseEntity<>(dto,HttpStatus.OK);

   }




}
