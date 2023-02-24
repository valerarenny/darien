package com.test.darien.entities;

import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(schema = "soccer", name = "team")
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "team")
    @SequenceGenerator(name = "team", schema = "soccer", sequenceName = "team_id_seq", allocationSize = 1)
    private Long id;
    private String name;
    private String city;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "team")
    private Set<Player> playerList;

}
