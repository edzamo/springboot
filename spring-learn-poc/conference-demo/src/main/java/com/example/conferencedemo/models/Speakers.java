package com.example.conferencedemo.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table( "speakers")
@AllArgsConstructor
@Getter
@Setter
public class Speakers {
    @Id
    private  Long speaker_id;
    private String first_name;
    private String last_name;
    private String title;
    private String company;
    private String speaker_bio;

   /* @Lob
    @Type(type ="org.hibernate.type.BinaryType")
    private  byte[] speaker_photo;
*/
    public Speakers() {
    }


    /* @ManyToMany(mappedBy = "speakers")
    private List<Session> sessions;*/
}
