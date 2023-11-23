package com.example.dianaee.Servlets.EditServlets;

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
import java.util.List;

@WebServlet("/editPerson")
public class EditPersonServlet extends HttpServlet {

    private final ConnectionProperty prop;
    private final PersonDAO dao;

    public EditPersonServlet() throws FileNotFoundException, IOException {
        super();
        prop = new ConnectionProperty();
        dao = new PersonDAO();
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        List<Person> persons;
        try {
            persons = dao.findAll();
            request.setAttribute("persons", persons);
        } catch (Exception e) {
            System.out.println(e);
        }
        String strId = request.getParameter("id");
        Long editPersonId = (strId != null) ? Long.parseLong(strId) : null;
        Person editPerson;
        try {
            editPerson = dao.findById(editPersonId);
            request.setAttribute("personEdit", editPerson);
        } catch (Exception e) {
            System.out.println(e);
        }
        request.getRequestDispatcher("/views/EditJSPs/editPerson.jsp").forward(request, response);

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String strId = request.getParameter("id");
        Long editPersonId = (strId != null) ? Long.parseLong(strId) : null;
        Person updatePerson = new Person(editPersonId, request.getParameter("fio"), request.getParameter("inn"),
                request.getParameter("type"), request.getParameter("date"));
        try {
            dao.update(updatePerson);
        } catch (Exception e) {
            System.out.println(e);
        }
        response.sendRedirect("/DianaEE_war_exploded/person");
    }
}