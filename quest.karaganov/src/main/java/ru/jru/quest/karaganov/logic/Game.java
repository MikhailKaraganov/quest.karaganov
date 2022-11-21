package ru.jru.quest.karaganov.logic;


import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import ru.jru.quest.karaganov.Repository;
import ru.jru.quest.karaganov.entitie.Action;
import ru.jru.quest.karaganov.entitie.Step;
import ru.jru.quest.karaganov.service.HTMLCreator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.*;


@Getter
public class Game {
    static final String PATH_TO_STEPS = "src/main/resources/stepsInfoRu.json";
    private ArrayList<Step> steps;
    private final Repository repository;
    private HTMLCreator htmlCreator;

    public Game(){
        repository = new Repository();
        steps = readSteps();
        htmlCreator = new HTMLCreator();
    }

    private ArrayList<Step> readSteps() {
        ObjectMapper mapper = new ObjectMapper();
        String pathToJsonProp = repository.getPathToJsonProp();
        File file = new File(pathToJsonProp);
        ArrayList<Step> result = new ArrayList<>();

        try {
            result =  mapper.readValue(file, mapper.getTypeFactory().constructCollectionType(ArrayList.class, Step.class));
        }
        catch (IOException e){
            e.printStackTrace();
        }
        return result;
    }

    public Step getStepById(Integer id){
        if(steps == null){
            return null;
        }
        else {
            Optional<Step> optionalStep = steps.stream().filter(step -> Objects.equals(step.getStepId(), id))
                    .findAny();
            return optionalStep.orElse(null);
        }
    }

    public String htmlCreator(Integer stepID, HttpServletRequest request){
        Step step = getStepById(stepID);
        String stepText = step.getCurrentQuestion();
        List<Action> answersForCurrentQuestion = step.getAnswersForCurrentQuestion();
        StringBuilder result = new StringBuilder("<html>\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "</head>" +
                "<body>\n" +
                "<h1 \">"+stepText +"</h1>\n" +
                 "\n");
        if(answersForCurrentQuestion != null){
            result.append("<form action=\"gameServlet\">\n" +
                    "  <div>\n");
            int counter = 1;
            for (Action action : answersForCurrentQuestion){
                result.append(
                        "    <input type=\"radio\" id=\"actionChoice"+counter+"\"\n" +
                                "     name=\"stepID\" value="+action.getLedToStep()+">\n" +
                                "    <label for=\"actionChoice1\">"+action.getText()+"</label>\n"+
                        "  </div>");
            }
            result.append("<div>\n" +
                            "    <button type=\"submit\">Submit</button>\n" +
                            "  </div>\n" +
                            "</form>");
        }
        if (step.getIsWin()){
            result.append("<div>" +
                    "<h2>Win!</h2>"+
                    "</div>"+
                    "<form action=\"gameServlet?stepID=1\" target=\"_blank\">\n" +
                    "   <button>Play again</button>\n" +
                    "  </form>");
        }
        if (!step.getHasNextStep() && !step.getIsWin()){
            result.append("<div>" +
                    "<h2>Lose!</h2>"+
                    "</div>"+
                    "<form action=\"gameServlet?stepID=1\" target=\"_blank\">\n" +
                    "   <button>Play again</button>\n" +
                    "</form>");
        }

        result.append("<hr>" +
                 sessionInfo(request) +
                "</body>\n" +
                "</html>\n");
        return result.toString();
    }

    private String sessionInfo (HttpServletRequest req){
        HttpSession session = req.getSession();
        ZonedDateTime zonedDateTime = Instant.ofEpochMilli(session.getCreationTime()).atZone(ZoneId.systemDefault());
        String sessionCreationTime = zonedDateTime.format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM));
        String IP = session.getAttribute("IP").toString();
        String name = session.getAttribute("name").toString();
        String id = session.getId();
        return "<table frame=\"border\">\n" +
                "  <caption>Session info</caption>\n" +
                "  <tr>\n" +
                "    <td>Name:</td>\n" +
                "    <td>"+name+"</td>\n" +
                "  </tr>\n" +
                "  <tr>\n" +
                "    <td>ID:</td>\n" +
                "    <td>"+id+"</td>\n" +
                "  </tr>\n" +
                "  <tr>\n" +
                "    <td>IP:</td>\n" +
                "    <td>"+IP+"</td>\n" +
                "  </tr>\n" +
                "  <tr>\n" +
                "    <td>Init date:</td>\n" +
                "    <td>"+sessionCreationTime+"</td>\n" +
                "  </tr>\n" +
                "</table>";
    }
}
