package com.example.Course.model;

import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import java.util.List;

@Embeddable
public class PresentationComment {

    @OneToMany
    @JoinColumn(name = "presentation_id")
    private List<Comment> comments;
}
