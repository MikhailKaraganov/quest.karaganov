package ru.jru.quest.karaganov.servlet;

import lombok.SneakyThrows;
import ru.jru.quest.karaganov.entitie.Action;
import ru.jru.quest.karaganov.entitie.Step;
import ru.jru.quest.karaganov.logic.Game;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.tagext.BodyContent;
import java.io.PrintWriter;
import java.util.List;


@WebServlet(name = "GameServlet", urlPatterns = "/gameServlet")
public class GameServlet extends HttpServlet {
    private Game game;

    @Override
    public void init() throws ServletException {
        super.init();
        game = new Game();
    }

    @Override
    @SneakyThrows
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        resp.setCharacterEncoding("UTF-8");
        try (PrintWriter writer = resp.getWriter()) {
            Integer stepID = Integer.parseInt(req.getParameter("stepID"));
            writer.println(game.htmlCreator(stepID,req));
        }
        catch (NumberFormatException e){
            e.printStackTrace();
        }
    }
}
