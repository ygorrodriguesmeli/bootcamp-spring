package com.meli.linktracker.service;

import com.meli.linktracker.dto.LinkDTO;
import com.meli.linktracker.entity.Link;
import com.meli.linktracker.repository.LinkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LinkService {

    @Autowired
    private LinkRepository repository;

    public int create(String senha) {
        return repository.create(senha);
    }

    public int create() {
        return repository.create();
    }

    public void redirect(long id, String senha) {
        repository.redirect(id, senha);
    }

    public void redirect(long id) {
        repository.redirect(id);
    }

    public LinkDTO getLink(long id) {
        return new LinkDTO(repository.getLink(id));
    }

    public void invalidate(long id) {
        repository.invalidate(id);
    }

}
