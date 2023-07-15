package com.example.test.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@EqualsAndHashCode
@Table(name = "CounterTable")
@NoArgsConstructor
public class Counter {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    private long countValue;

    public String getValue() {
        return String.valueOf(getCountValue());
    }

    public void setValue(String value) {
        setCountValue(Long.parseLong(value));
    }

    public Counter(long countValue) {
        this.countValue = countValue;
    }
}
