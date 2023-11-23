package com.example.dianaee.Servlets.PostServlets;

import com.example.dianaee.DAO.Connection.ConnectionProperty;
import com.example.dianaee.DAO.PersonDAO;
import com.example.dianaee.Models.Person;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.PublicKey;
import java.util.List;

@WebServlet("/person")
public class PersonServlet extends HttpServlet {

    private final ConnectionProperty prop;
    private final PersonDAO dao;
    public PersonServlet() throws FileNotFoundException, IOException {
        super();
        prop = new ConnectionProperty();
        dao = new PersonDAO();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Person> persons;
        try{
            persons = dao.findAll();
            request.setAttribute("persons", persons);
        }catch (Exception e) {
            e.printStackTrace();
        }
        request.getRequestDispatcher("views/person.jsp").forward(request,response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            dao.insert(new Person(request.getParameter("fio"), request.getParameter("inn"),
                    request.getParameter("type"), request.getParameter("date")));
        }catch (Exception e){
            e.printStackTrace();
        }
        doGet(request, response);
    }
}
