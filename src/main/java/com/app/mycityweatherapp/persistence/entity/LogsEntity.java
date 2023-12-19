package com.app.mycityweatherapp.persistence.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LogsEntity {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
//    @Column(name = "request_key")
    private String requestKey;
    private String response;
//    @Column(name = "updated_time")
    private LocalDateTime updatedTime;
//    @Column(name = "response_local_time")
    private LocalDateTime responseLocalTime;

}
