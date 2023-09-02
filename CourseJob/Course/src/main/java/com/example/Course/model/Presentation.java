package com.example.Course.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "presentations")
public class Presentation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String file;

    @Column(name = "mime_type")
    private String mimeType;
    private String description;
    private LocalDateTime date;
    private String place;

    @ManyToOne
    @JoinColumn(name = "seminar_id")
    private Seminar seminar;

    @ManyToMany
    @JoinTable(name = "presentations_themes",
            joinColumns = @JoinColumn(name = "presentation_id"),
            inverseJoinColumns = @JoinColumn(name = "theme_id"))
    private List<Theme> themes;

    @ManyToMany
    @JoinTable(name = "presentations_speakers",
            joinColumns = @JoinColumn(name = "presentation_id"),
            inverseJoinColumns = @JoinColumn(name = "speaker_id"))
    private List<Speaker> speakers;

    @Embedded
    private PresentationComment comment;

}
