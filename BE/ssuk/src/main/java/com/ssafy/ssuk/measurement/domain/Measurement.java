package com.ssafy.ssuk.measurement.domain;


import com.ssafy.ssuk.pot.domain.Pot;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "MEASUREMENT")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Setter
public class Measurement {
    @Id @GeneratedValue
    @Column(name = "MEASUREMENT_ID")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "POT_ID")
    private Pot pot;

    @Column(name = "MEASUREMENT_VALUE")
    private Double measurementValue;

    @Column(name = "MEASUREMENT_TIME")
    private LocalDate measurementTime;

    @Column(name = "SENSOR_TYPE")
    private SensorType sensorType;
}
