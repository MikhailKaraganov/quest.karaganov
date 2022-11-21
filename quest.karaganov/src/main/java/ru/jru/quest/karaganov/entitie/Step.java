package ru.jru.quest.karaganov.entitie;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Step implements Serializable  {
    private String currentQuestion;
    private Integer stepId;
    private Boolean hasNextStep;
    private Boolean isWin;
    private List<Action> answersForCurrentQuestion;
}
