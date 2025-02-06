package com.ezamora.webfluxdemo.dto;

import java.util.Date;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
public class Response {

  private Date date = new Date();
  private int outPut;

  public Response(int outPut) {
    this.outPut = outPut;
  }

}
