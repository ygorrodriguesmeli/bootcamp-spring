package com.meli.linktracker.repository;

import com.meli.linktracker.entity.Link;
import com.meli.linktracker.exception.LinkForbiddenException;
import com.meli.linktracker.exception.LinkNotFoundException;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class LinkRepository {

    private static List<Link> links = new ArrayList<>();
    private static int numberOfLinks = 0;

    public int create(String senha) {
        numberOfLinks++;
        links.add(new Link(numberOfLinks, senha));
        return numberOfLinks;
    }

    public int create() {
        numberOfLinks++;
        links.add(new Link(numberOfLinks));
        return numberOfLinks;
    }

    public void redirect(long id, String senha) {
        Optional<Link> link = links.stream().filter(l-> l.getId() == id).findFirst();
        if(link.isPresent()) {
            if(link.get().getSenha().isEmpty() || link.get().getSenha().equals(senha)) {
                link.get().addNumberOfRedirects();
            }
            else {
                throw new LinkForbiddenException("Senha incorreta!");
            }
        } else {
            throw new LinkNotFoundException("Link não encontrado!");
        }
    }

    public void redirect(long id) {
        Optional<Link> link = links.stream().filter(l-> l.getId() == id).findFirst();
        if(link.isPresent()) {
            if(link.get().getSenha().isEmpty()) {
                link.get().addNumberOfRedirects();
            }
            else {
                throw new LinkForbiddenException("Senha incorreta!");
            }
        } else {
            throw new LinkNotFoundException("Link não encontrado!");
        }
    }

    public Link getLink(long id) {
        Optional<Link> link = links.stream().filter(l-> l.getId() == id).findFirst();
        if(link.isPresent())
            return link.get();
        else
            throw new LinkNotFoundException("Link não encontrado!");
    }

    public void invalidate(long id) {
        Optional<Link> link = links.stream().filter(l-> l.getId() == id).findFirst();
        if(link.isPresent())
            links.removeIf(l -> l.equals(link.get()));
        else
            throw new LinkNotFoundException("Link não encontrado!");
    }
}
