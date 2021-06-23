package com.hitex.evn.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

@RequestMapping("/api/")
public class TagController extends BaseController {

    @PostMapping("addTag")
    public ResponseEntity<?> addTagName(@RequestBody String tag){
return new ResponseEntity<>(null, HttpStatus.OK);
    }

}
