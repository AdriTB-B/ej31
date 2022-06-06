package com.adri.ej31.estudiante_estudio.domain;

import com.adri.ej31.estudiante.domain.EstudianteEntity;
import com.adri.ej31.profesor.domain.ProfesorEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "estudiantes_estudios")
@Data
@NoArgsConstructor
public class Estudiante_EstudioEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id_study;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_profesor")
    private ProfesorEntity profesor;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_estudiante")
    private EstudianteEntity student;

    @Column(name = "asignatura")
    private String asignatura;

    @Column(name = "comentarios")
    private String comment;

    @Column(name = "initial_date")
    @Temporal(TemporalType.DATE)
    private Date initial_date;

    @Column(name = "finish_date")
    @Temporal(TemporalType.DATE)
    private Date finish_date;
}