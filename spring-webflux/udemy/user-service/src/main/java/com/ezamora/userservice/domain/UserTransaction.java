package com.ezamora.userservice.domain;

import java.time.LocalDateTime;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Table("user_transaction")
public class UserTransaction {

  @Id
  private Integer id;
  private Integer userId;
  private Integer amount;
  @Column("transaction_date")
  private LocalDateTime time;


}
