package com.myblog10.payload;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;


@Data
public class PostDto {



    private long id;

    @NotEmpty
    @Size(min=2 ,message = "atlest 2 letter")
    private String title;


    @NotEmpty
    @Size(min=4 )
    private String description;

  @NotEmpty(message = "should not be empty")
    private String content;
}

