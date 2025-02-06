package com.example.conferencedemo.models.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class SessionDto implements Serializable {

    //   @JsonProperty("session_id")
    private Long session_id;
    //  @JsonProperty("session_name")
    private String session_name;
    // @JsonProperty("session_description")
    private String session_description;
    // @JsonProperty("session_length")
    private Integer session_length;

}
