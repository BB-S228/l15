package com.example.dianaee.DAO;

import com.example.dianaee.DAO.Connection.ConnectionBuilder;
import com.example.dianaee.DAO.Connection.DbConnectionBuilder;
import com.example.dianaee.Models.Person;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class PersonDAO implements RepositoryDAO<Person>{

    public PersonDAO() {};

    private static final String select_all = "SELECT person_id, fio, inn, type_person, date_person FROM person";
    private static final String select_person_ById = "SELECT person_id, fio, inn, type_person, date_person FROM person WHERE person_id =?";
    private static final String insert_person = "INSERT INTO person(fio, inn, type_person, date_person) VALUES(?, ?, ?, ?)";
    private static final String edit_person = "UPDATE person SET fio = ?, inn = ?, type_person = ?, date_person = ? WHERE person_id = ? ";
    private static final String delete_person = "DELETE FROM person WHERE person_id = ?";

    private ConnectionBuilder builder = new DbConnectionBuilder();
    private Connection getConnection() throws SQLException {
        return builder.getConnection();
    }

    @Override
    public Long insert (Person person) throws Exception {
        try (Connection con = getConnection();
             PreparedStatement pst =
                     con.prepareStatement(insert_person, new String[] { "person_id" })) {
            long Id = -1L;
            pst.setString(1, person.getFio());
            pst.setString(2, person.getInn());
            pst.setString(3, person.getType());
            pst.setString(4, person.getDate());
            pst.executeUpdate();
            ResultSet gk = pst.getGeneratedKeys();
            if (gk.next()) {
                Id = gk.getLong("person_id");
            }
            gk.close();
            return Id;
        } catch (Exception e) {
            System.out.println(e);
        }
        return -1L;
    }

    @Override
    public void update(Person person) {
        try (Connection con = getConnection(); PreparedStatement pst
                = con.prepareStatement(edit_person)) {
            pst.setString(1, person.getFio());
            pst.setString(2, person.getInn());
            pst.setString(3, person.getType());
            pst.setString(4, person.getDate());
            pst.setLong(5, person.getPersonId());
            pst.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Override
    public void delete(Long Id) {
        try (Connection con = getConnection(); PreparedStatement pst
                = con.prepareStatement(delete_person)) {
            pst.setLong(1, Id);
            pst.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Override
    public Person findById(Long Id) {
        Person person = null;
        try (Connection con = getConnection()) {
            PreparedStatement pst =
                    con.prepareStatement(select_person_ById);
            pst.setLong(1, Id);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                person = fillperson(rs);
            }
            rs.close();
            pst.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return person;
    }

    @Override
    public List<Person> findAll(){
        List<Person> list = new LinkedList<>();
        try (Connection con = getConnection();
             PreparedStatement pst =
                     con.prepareStatement(select_all);
             ResultSet rs = pst.executeQuery()) {
            while (rs.next()) {
                list.add(fillperson(rs));
            }
            rs.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }

    private Person fillperson(ResultSet rs) throws SQLException {
        Person person = new Person();
        person.setPersonId(rs.getLong("person_id"));
        person.setFio(rs.getString("fio"));
        person.setInn(rs.getString("inn"));
        person.setType(rs.getString("type_person"));
        person.setDate(rs.getString("date_person"));
        return person;
    }

    public Person FindById(Long id, List<Person> people) {
        if (people != null) {
            for (Person r : people) {
                if ((r.getPersonId()).equals(id)) {
                    return r;
                }
            }
        } else {
            return null;
        }
        return null;
    }
}
