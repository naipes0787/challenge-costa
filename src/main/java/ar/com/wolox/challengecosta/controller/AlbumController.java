package ar.com.wolox.challengecosta.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ar.com.wolox.challengecosta.repository.AlbumRepository;

@RestController
@RequestMapping("/")
public class AlbumController {

    @Autowired
    AlbumRepository albumRepository;


}