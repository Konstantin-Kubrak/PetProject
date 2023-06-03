package com.kubrak.petproject.entities;

import java.time.LocalDate;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "project_roles")
public class ProjectRole {

  @Id
  @GeneratedValue
  private Long id;

  @Column
  private String name;

  @Column
  private LocalDate startDate;

  @Column
  private LocalDate endDate;

  @ManyToOne(optional = false, cascade = CascadeType.REFRESH)
  @JoinColumn(name = "project_id")
  private Project project;

  @ManyToOne(optional = false, cascade = CascadeType.REFRESH)
  @JoinColumn(name = "user_id")
  private User user;
}
