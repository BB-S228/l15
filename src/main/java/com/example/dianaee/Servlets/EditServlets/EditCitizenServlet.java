package com.example.dianaee.Servlets.EditServlets;

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

import java.io.IOException;
import java.util.List;

@WebServlet("/editCitizen")
public class EditCitizenServlet extends HttpServlet {
    private final ConnectionProperty prop;
    private final PersonDAO personDAO;
    private final DocumentDAO documentDAO;
    private final CitizenDAO citizenDAO;

    public EditCitizenServlet() throws IOException {
        super();
        prop = new ConnectionProperty();
        personDAO = new PersonDAO();
        documentDAO = new DocumentDAO();
        citizenDAO = new CitizenDAO();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Person> persons;
        List<Document> documents;
        List<Citizen> citizens;

        String strId = request.getParameter("id");
        Long editCitizenId = (strId != null) ? Long.parseLong(strId) : null;
        Citizen editCitizen;
        try {
            persons = personDAO.findAll();
            documents = documentDAO.findAll();
            citizens = citizenDAO.findAll();
            for (Citizen city:citizens){
                city.setPerson(personDAO.FindById(city.getPersonId(), persons));
                city.setDocument(documentDAO.FindById(city.getDocumentId(), documents));
            }

            editCitizen = citizenDAO.findById(editCitizenId);
            editCitizen.setPerson(personDAO.findById(editCitizen.getPersonId()));
            persons.removeIf(people -> people.getPersonId() == editCitizen.getPersonId());
            editCitizen.setDocument(documentDAO.findById(editCitizen.getDocumentId()));
            documents.removeIf(doc -> doc.getDocumentId() == editCitizen.getDocumentId());
            request.setAttribute("persons", persons);
            request.setAttribute("documents", documents);
            request.setAttribute("citizens", citizens);
            request.setAttribute("cityEdit", editCitizen);
        } catch (Exception e) {
            System.out.println(e);
        }
        request.getRequestDispatcher("/views/EditJSPs/editCitizen.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String strId = request.getParameter("id");
            Long editCitizenId = (strId != null) ? Long.parseLong(strId) : null;

            String person = request.getParameter("personField");
            int index1 = person.indexOf('=');
            int index2 = person.indexOf(",");
            String r1 = person.substring(index1+1, index2);
            long personId = Long.parseLong(r1.trim());
            Person persons = personDAO.findById(personId);

            String document = request.getParameter("documentField");
            int index3 = document.indexOf('=');
            int index4 = document.indexOf(",");
            String r2 = document.substring(index3+1, index4);
            long docId = Long.parseLong(r2.trim());
            Document documents = documentDAO.findById(docId);

            citizenDAO.update(new Citizen(editCitizenId, personId, docId, request.getParameter("shifer"), persons, documents));
        } catch (Exception e) {
            System.out.println(e);
        }
        response.sendRedirect("/DianaEE_war_exploded/citizen");
    }
}
