package com.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.model.ConnectionProvider;
import com.model.Gamer;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.annotation.WebServlet;

@WebServlet(
        name = "TypeaheadServlet",
        description = "This is my second annotated servlet to search typeahead",
        urlPatterns = "/TypeaheadServlet"
)
public class TypeaheadServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public TypeaheadServlet() {
        super();
    }

    class TypeaheadItem {

        String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

    }

    private String getSearchString(HttpServletRequest request) {
        String sql = null;
        String searchBy = request.getParameter("searchBy");
        String searchItem = request.getParameter("searchItem");
        if (searchItem == null || searchItem.isEmpty() || searchBy == null) {
            return sql;
        }
        if (searchBy.equalsIgnoreCase("name")) {
            sql = "select name  from gamers where name like '%" + searchItem + "%';";
        } else if (searchBy.equalsIgnoreCase("email")) {
            sql = "select email  from gamers where email like '%" + searchItem + "%';";
        } else if (searchBy.equalsIgnoreCase("game")) {
            sql = "select game  from gamers where game like '%" + searchItem + "%';";
        }
        return sql;
    }

    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        System.err.println(request.toString());

        List<TypeaheadItem> objList = new ArrayList<>();
        try {
            Statement st = ConnectionProvider.getConnection().createStatement();
            String sql = getSearchString(request);
            if (sql != null) {
                ResultSet rs0 = st.executeQuery(sql);
                for (int i = 0; rs0.next() && i < 100; i++) {
                    TypeaheadItem ti = new TypeaheadItem();
                    ti.setName(rs0.getString(1));
                    objList.add(ti);
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
