package ru.jru.quest.karaganov.filter;

import lombok.Data;

import javax.servlet.FilterChain;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.Enumeration;

@Data
@WebFilter(urlPatterns = "/gameServlet")
public class StartFilter extends HttpFilter {

    @Override
    public void init() throws ServletException {

    }

    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        String userIP = req.getRemoteAddr();
        HttpSession session = req.getSession();
        String servletPattern = "/gameServlet";
        System.out.println("stepID before filter:   " + req.getParameter("stepID"));
        Enumeration<String> attributeNames = session.getAttributeNames();
        if (req.getParameter("stepID") == null){
            servletPattern = "/gameServlet?stepID=1";
        }
        if (session.getAttribute("IP")==null){
            session.setAttribute("IP", userIP);
        }
        StringBuilder attrib = new StringBuilder();
        while (attributeNames.hasMoreElements()){
            attrib.append(attributeNames.nextElement()).append(" ");
        }
        if(session.getAttribute("name") == null) {
            session.setAttribute("name", req.getParameter("userName"));
        }
        RequestDispatcher dispatcher = req.getRequestDispatcher(servletPattern);
        dispatcher.forward(req,res);
    }

    @Override
    public void destroy() {
    }
}
