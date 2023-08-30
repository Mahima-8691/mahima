package com.myblog10.controller;
import com.myblog10.payload.id_dto;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/id")
public class test {

    //http://localhost:8080/id/api
  @GetMapping("/api")
  @ResponseBody/// convert Json object to Json.
    public id_dto get(){
      id_dto id= new  id_dto();
       id.setId(1);
     id.setName("mahima");
      return id;

    }
}
