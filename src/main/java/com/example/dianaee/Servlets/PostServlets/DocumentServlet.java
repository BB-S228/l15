package com.example.dianaee.Servlets.PostServlets;

import com.example.dianaee.DAO.Connection.ConnectionProperty;
import com.example.dianaee.DAO.DocumentDAO;
import com.example.dianaee.Models.Document;
import com.example.dianaee.Models.Person;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.List;

@WebServlet("/document")
public class DocumentServlet extends HttpServlet {

    private final ConnectionProperty prop;
    private final DocumentDAO dao;
    public DocumentServlet() throws FileNotFoundException, IOException {
        super();
        prop = new ConnectionProperty();
        dao = new DocumentDAO();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Document> documents;
        try{
            documents = dao.findAll();
            request.setAttribute("documents", documents);
        }catch (Exception e){
            e.printStackTrace();
        }
        request.getRequestDispatcher("views/document.jsp").forward(request,response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try{
            dao.insert(new Document(request.getParameter("name"), request.getParameter("serial"),
                    request.getParameter("number"), request.getParameter("organ"), request.getParameter("date")));
        }catch (Exception e){
            e.printStackTrace();
        }
        doGet(request, response);
    }
}
