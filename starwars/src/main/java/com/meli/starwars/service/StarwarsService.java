package com.meli.starwars.service;

import com.meli.starwars.entity.Personagem;
import com.meli.starwars.repository.StarwarsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StarwarsService {

    @Autowired
    private StarwarsRepository starwarsRepository;

    public List<Personagem> getListByName(String nome) {
        List<Personagem> listOfCharacters = starwarsRepository.getList();

        return listOfCharacters.stream()
                .filter(personagem -> personagem.getName().toLowerCase().contains(nome.toLowerCase())).collect(Collectors.toList());
    }
}
