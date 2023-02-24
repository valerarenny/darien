package com.test.darien.entities;


import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(schema = "soccer", name = "player")
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "player")
    @SequenceGenerator(name = "player", schema = "soccer", sequenceName = "player_id_seq", allocationSize = 1)
    private Long id;
    private String name;
    private Integer totalGoals;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;
    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team;

}
