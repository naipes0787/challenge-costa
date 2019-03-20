package ar.com.wolox.challengecosta.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ar.com.wolox.challengecosta.repository.FotoRepository;

@RestController
@RequestMapping("/")
public class FotoController {

    @Autowired
    FotoRepository fotoRepository;


}