package com.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.model.ConnectionProvider;
import com.model.Gamer;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.annotation.WebServlet;

@WebServlet(
        name = "AngularJsServlet",
        description = "This is my first annotated servlet to fill table data",
        urlPatterns = "/AngularJsServlet"
)
public class AngularJsServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public AngularJsServlet() {
        super();
    }
    private String getSearchString(HttpServletRequest request) {
        String sql = null;
        String searchBy = request.getParameter("searchBy");
        String searchItem = request.getParameter("searchItem");
        if (searchItem == null || searchItem.isEmpty() || searchBy == null || searchBy.isEmpty()) {
            return sql;
        }
        if (searchBy.equalsIgnoreCase("name")) {
            sql = "select avatar,name,joined,game,result,email,state,flag  from gamers where name like '%" + searchItem + "%';";
        } else if (searchBy.equalsIgnoreCase("email")) {
            sql = "select avatar,name,joined,game,result,email,state,flag  from gamers where email like '%" + searchItem + "%';";
        } else if (searchBy.equalsIgnoreCase("game")) {
            sql = "select avatar,name,joined,game,result,email,state,flag  from gamers where game like '%" + searchItem + "%';";
        }
        return sql;
    }

    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        System.err.println(request.toString());

        List<Gamer> objList = new ArrayList<>();
        try {
            Statement st = ConnectionProvider.getConnection().createStatement();
           String sql = getSearchString(request);
            if (sql != null) {
             ResultSet rs0 = st.executeQuery(sql);
             for (int i = 0;  (rs0.next() && i < 10000); i++) {  
                Gamer gamer = new Gamer();
                gamer.setAvatar("img/avatar/"+rs0.getString(1));
                gamer.setName(rs0.getString(2));
                gamer.setJoined(rs0.getString(3));
                gamer.setGame(rs0.getString(4));
                gamer.setResult(rs0.getInt(5));
                gamer.setEmail(rs0.getString(6));
                gamer.setState(rs0.getString(7));                
                gamer.setFlag("img/flag/"+rs0.getString(8));   
                 objList.add(gamer);
             }
            }
        } catch (SQLException ex) {
            Logger.getLogger(AngularJsServlet.class.getName()).log(Level.SEVERE, "SQLException", ex);

        }

        String json = new Gson().toJson(objList);
        response.setContentType("application/json");
        response.getWriter().write(json);
    }

}
