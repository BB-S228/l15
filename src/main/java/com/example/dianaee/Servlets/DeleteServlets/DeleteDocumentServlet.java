package com.example.dianaee.Servlets.DeleteServlets;

import com.example.dianaee.DAO.Connection.ConnectionProperty;
import com.example.dianaee.DAO.DocumentDAO;
import com.example.dianaee.DAO.PersonDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/deleteDocument")
public class DeleteDocumentServlet extends HttpServlet {
    private final ConnectionProperty prop;
    private final DocumentDAO dao;

    public DeleteDocumentServlet() throws IOException {
        super();
        prop = new ConnectionProperty();
        dao = new DocumentDAO();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String strId = request.getParameter("id");
        Long deleteid = (strId != null) ? Long.parseLong(strId) : null;
        try {
            dao.delete(deleteid);
        } catch (Exception e) {
            System.out.println(e);
        }
        response.sendRedirect("/DianaEE_war_exploded/document");
    }
}
