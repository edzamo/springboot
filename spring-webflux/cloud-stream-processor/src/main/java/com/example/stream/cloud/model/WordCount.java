package com.example.stream.cloud.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.util.Date;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WordCount {
  private String word;
  private Long count;
  private Date start;
  private Date end;
}
