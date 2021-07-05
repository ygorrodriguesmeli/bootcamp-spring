package com.meli.linktracker.dto;

import com.meli.linktracker.entity.Link;

public class LinkDTO {
    private long linkId;
    private int numberOfRedirects;

    public LinkDTO(long id) {
        this.linkId = id;
    }

    public LinkDTO(Link link) {
        this.linkId = link.getId();
        this.numberOfRedirects = link.getNumberOfRedirects();
    }

    public LinkDTO converte(Link link) {
        return new LinkDTO(link);
    }

    public long getId() {
        return linkId;
    }

    public int getNumberOfRedirects() {
        return numberOfRedirects;
    }
}
