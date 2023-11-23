package com.example.dianaee.Servlets.EditServlets;

import com.example.dianaee.DAO.Connection.ConnectionProperty;
import com.example.dianaee.DAO.DocumentDAO;
import com.example.dianaee.DAO.PersonDAO;
import com.example.dianaee.Models.Document;
import com.example.dianaee.Models.Person;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

@WebServlet("/editDocument")
public class EditDocumentServlet extends HttpServlet {

    private final ConnectionProperty prop;
    private final DocumentDAO dao;

    public EditDocumentServlet() throws FileNotFoundException, IOException {
        super();
        prop = new ConnectionProperty();
        dao = new DocumentDAO();
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        List<Document> documents;
        try {
            documents = dao.findAll();
            request.setAttribute("documents", documents);
        } catch (Exception e) {
            System.out.println(e);
        }
        String strId = request.getParameter("id");
        Long editDocumentId = (strId != null) ? Long.parseLong(strId) : null;
        Document editDocument;
        try {
            editDocument = dao.findById(editDocumentId);
            request.setAttribute("docEdit", editDocument);
        } catch (Exception e) {
            System.out.println(e);
        }
        request.getRequestDispatcher("/views/EditJSPs/editDocument.jsp").forward(request, response);

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String strId = request.getParameter("id");
        Long editDocumentId = (strId != null) ? Long.parseLong(strId) : null;
        Document updateDocument = new Document(editDocumentId, request.getParameter("name"), request.getParameter("serial"),
                request.getParameter("number"), request.getParameter("organ"), request.getParameter("date"));
        try {
            dao.update(updateDocument);
        } catch (Exception e) {
            System.out.println(e);
        }
        response.sendRedirect("/DianaEE_war_exploded/document");
    }
}
