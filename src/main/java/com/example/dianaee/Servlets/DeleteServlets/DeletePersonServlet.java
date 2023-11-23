package com.example.dianaee.Servlets.DeleteServlets;

import com.example.dianaee.DAO.Connection.ConnectionProperty;
import com.example.dianaee.DAO.PersonDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/deletePerson")
public class DeletePersonServlet extends HttpServlet {
    private final ConnectionProperty prop;
    private final PersonDAO dao;

    public DeletePersonServlet() throws IOException {
        super();
        prop = new ConnectionProperty();
        dao = new PersonDAO();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String strId = request.getParameter("id");
        Long deleteid = (strId != null) ? Long.parseLong(strId) : null;
        try {
            dao.delete(deleteid);
        } catch (Exception e) {
            System.out.println(e);
        }
        response.sendRedirect("/DianaEE_war_exploded/person");
    }
}
