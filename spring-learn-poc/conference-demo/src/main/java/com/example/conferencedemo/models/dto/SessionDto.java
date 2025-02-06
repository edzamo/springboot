package com.example.conferencedemo.models.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SessionDto implements Serializable {

    private static final long serialVersionUID = 74458L;

   // @JsonProperty("session_id")
    private String session_id;
    //@JsonProperty("session_name")
    private String session_name;
    //@JsonProperty("session_description")
    private String session_description;
    //@JsonProperty("session_length")
    private String session_length;



}
