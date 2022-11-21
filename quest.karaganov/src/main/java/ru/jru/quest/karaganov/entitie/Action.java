package ru.jru.quest.karaganov.entitie;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class Action implements Serializable {
    private String text;
    private Integer ledToStep;
}
