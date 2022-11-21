package ru.jru.quest.karaganov;


import lombok.Getter;
import lombok.Setter;

import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;


@Getter
@Setter
public class Repository {
    private final String pathToJsonProp;
    private final String pathToRes;
    private final String pathToSessionInfo;

    
    public Repository (){
        String pathRes = "";
        pathRes = URLDecoder.decode(Repository.class
                .getProtectionDomain()
                .getCodeSource()
                .getLocation()
                .getPath(), StandardCharsets.UTF_8).substring(1);
        System.out.println("pathRes:  "+ pathRes);
        pathToRes=pathRes;
        pathToJsonProp = pathToRes + "stepsInfoRu.json";
        pathToSessionInfo = pathToRes.substring(0,pathRes.indexOf("classes")) + "session_info.jsp";
        System.out.println(this.getPathToJsonProp());
    }
}

