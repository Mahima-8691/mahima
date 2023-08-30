package com.myblog10.service;

import com.myblog10.entity.Post;
import com.myblog10.exception.ResourceNotFoundException;
import com.myblog10.payload.PostDto;
import com.myblog10.repository.PostRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class PostServiceImpl implements PostService {



    private ModelMapper modelMapper;



    private PostRepository postRepo;

    public PostServiceImpl(PostRepository postRepo,ModelMapper modelMapper){

        this.postRepo=postRepo;
        this.modelMapper=modelMapper;
    }



   public  PostDto createPost(PostDto postDto){
          Post post = mapToEntity(postDto);
               Post savepost=postRepo.save(post);
               PostDto dto=mapToDto(savepost);
               return dto;
    }

    public PostDto getPost( long id ){
         Post post= postRepo.findById(id).orElseThrow(
                  ()-> new ResourceNotFoundException(id)
          );
                 PostDto dto=mapToDto(post);
                 return dto;

    }

    public List<PostDto> getAllPost(int pageNo, int pageSize, String sortBy, String sortDir) {

         Sort  sort=sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())?Sort.by(sortBy).ascending():Sort.by(sortBy).descending();

         Pageable pageable=PageRequest.of(pageNo,pageSize,Sort.by(sortBy));

         Page<Post> posts= postRepo.findAll(pageable);

             List<Post> content =posts.getContent();

        List<PostDto> postDtos= content.stream().map(post->mapToDto(post)).collect(Collectors.toList());

        return postDtos;

    }

   public  void deletePost(long id){
        Post post=postRepo.findById(id).orElseThrow(
                ()-> new ResourceNotFoundException(id)
        );
       postRepo.deleteById(id);
    }

   public  PostDto updatePost( long id, PostDto postDto){
       Post post=postRepo.findById(id).orElseThrow(
               ()-> new ResourceNotFoundException(id)
       );

       Post updateContent =mapToEntity(postDto);
       updateContent.setId(post.getId());
      Post updateInfo= postRepo.save(updateContent);
      return mapToDto(updateInfo);

    }












   Post mapToEntity(PostDto postDto){

        Post post=modelMapper.map(postDto,Post.class);
      // Post post = modelMapper.map(postDto, Post.class);

//        Post post= new Post();
//       post.setTitle(postDto.getTitle());
//       post.setDescription(postDto.getDescription());
//       post.setContent(postDto.getContent());
      return post;
   }


   PostDto mapToDto(Post post){
     PostDto dto= modelMapper.map(post,PostDto.class);


//        PostDto dto= new PostDto();
//        dto.setId(post.getId());
//       dto.setTitle(post.getTitle());
//       dto.setDescription(post.getDescription());
//       dto.setContent(post.getContent());
      return dto;

    }
}





