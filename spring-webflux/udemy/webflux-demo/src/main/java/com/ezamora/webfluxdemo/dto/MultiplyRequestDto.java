package com.ezamora.webfluxdemo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
@ToString
public class MultiplyRequestDto {

  private int first;
  private int second;
}
