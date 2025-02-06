package com.example.conferencedemo.models;


import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.io.Serializable;

@Table("sessions")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class Session implements Serializable {
    @Id
  //  @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long session_id;
    private String session_name;
    private String session_description;
    private Integer session_length;


/*
    @ManyToMany
    @JoinTable(
            name = "session_speakers",
            joinColumns = @JoinColumn(name = "session_id"),
            inverseJoinColumns = @JoinColumn(name = "speaker_id")
    )
    private List<Speakers> speakers;*/
}
