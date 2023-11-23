package com.example.dianaee.Servlets.PostServlets;

import com.example.dianaee.DAO.CitizenDAO;
import com.example.dianaee.DAO.Connection.ConnectionProperty;
import com.example.dianaee.DAO.DocumentDAO;
import com.example.dianaee.DAO.PersonDAO;
import com.example.dianaee.Models.Citizen;
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
import java.util.List;

@WebServlet("/citizen")
public class CitizenServlet extends HttpServlet {

    private final ConnectionProperty prop;
    private final PersonDAO personDAO;
    private final DocumentDAO documentDAO;
    private final CitizenDAO citizenDAO;
    public CitizenServlet() throws FileNotFoundException, IOException {
        super();
        prop = new ConnectionProperty();
        personDAO = new PersonDAO();
        documentDAO = new DocumentDAO();
        citizenDAO = new CitizenDAO();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Person> persons;
        List<Document> documents;
        List<Citizen> citizens;
        try {
            persons = personDAO.findAll();
            request.setAttribute("persons", persons);

            documents = documentDAO.findAll();
            request.setAttribute("documents", documents);

            citizens = citizenDAO.findAll();
            request.setAttribute("citizens", citizens);

            for (Citizen city:citizens){
                city.setPerson(personDAO.FindById(city.getPersonId(), persons));
                city.setDocument(documentDAO.FindById(city.getDocumentId(), documents));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        request.getRequestDispatcher("views/citizen.jsp").forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try{
            String person = request.getParameter("person");
            int index1 = person.indexOf('=');
            int index2 = person.indexOf(",");
            String r1 = person.substring(index1+1, index2);
            long personId = Long.parseLong(r1.trim());
            Person persons = personDAO.findById(personId);

            String document = request.getParameter("document");
            int index3 = document.indexOf('=');
            int index4 = document.indexOf(",");
            String r2 = document.substring(index3+1, index4);
            long documentId = Long.parseLong(r2.trim());
            Document documents = documentDAO.findById(documentId);

            citizenDAO.insert(new Citizen(personId, documentId, request.getParameter("shifer"), persons, documents));
        }catch (Exception e){
            e.printStackTrace();
        }
        doGet(request, response);
    }
}

