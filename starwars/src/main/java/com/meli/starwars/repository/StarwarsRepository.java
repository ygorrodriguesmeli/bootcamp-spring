package com.meli.starwars.repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.meli.starwars.entity.Personagem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class StarwarsRepository {
    private static final File FILE = new File("starwars.json");

    @Autowired
    private ObjectMapper mapper;

    public List<Personagem> getList() {
        List<Personagem> personagens = new ArrayList<>();
        try {
            FileInputStream is = new FileInputStream(FILE);
            TypeReference<List<Personagem>> typeReference = new TypeReference<List<Personagem>>(){};
            personagens = mapper.readValue(is, typeReference);
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return personagens;
    }

}
