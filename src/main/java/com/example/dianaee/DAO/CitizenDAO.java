package com.example.dianaee.DAO;

import com.example.dianaee.DAO.Connection.ConnectionBuilder;
import com.example.dianaee.DAO.Connection.DbConnectionBuilder;
import com.example.dianaee.Models.Citizen;
import com.example.dianaee.Models.Document;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class CitizenDAO implements RepositoryDAO<Citizen> {
    public CitizenDAO() {};

    private static final String select_all = "SELECT citizen_id, person_id, document_id, shifer FROM citizen";
    private static final String select_city_ById = "SELECT citizen_id, person_id, document_id, shifer FROM citizen WHERE citizen_id = ?";
    private static final String insert_city = "INSERT INTO citizen(person_id, document_id, shifer) VALUES(?, ?, ?)";
    private static final String edit_city = "UPDATE citizen SET person_id = ?, document_id = ?, shifer = ? WHERE citizen_id = ? ";
    private static final String delete_city = "DELETE FROM citizen WHERE citizen_id = ?";

    private ConnectionBuilder builder = new DbConnectionBuilder();
    private Connection getConnection() throws SQLException {
        return builder.getConnection();
    }

    @Override
    public Long insert (Citizen citizen) throws Exception {
        try (Connection con = getConnection();
             PreparedStatement pst =
                     con.prepareStatement(insert_city, new String[] { "citizen_id" })) {
            long Id = -1L;
            pst.setLong(1, citizen.getPersonId());
            pst.setLong(2, citizen.getDocumentId());
            pst.setString(3, citizen.getShifer());
            pst.executeUpdate();
            ResultSet gk = pst.getGeneratedKeys();
            if (gk.next()) {
                Id = gk.getLong("citizen_id");
            }
            gk.close();
            return Id;
        } catch (Exception e) {
            System.out.println(e);
        }
        return -1L;
    }

    @Override
    public void update(Citizen citizen) {
        try (Connection con = getConnection(); PreparedStatement pst
                = con.prepareStatement(edit_city)) {
            pst.setLong(1, citizen.getPersonId());
            pst.setLong(2, citizen.getDocumentId());
            pst.setString(3, citizen.getShifer());
            pst.setLong(4, citizen.getCitizenId());
            pst.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Override
    public void delete(Long Id) {
        try (Connection con = getConnection(); PreparedStatement pst
                = con.prepareStatement(delete_city)) {
            pst.setLong(1, Id);
            pst.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Override
    public Citizen findById(Long Id) {
        Citizen citizen = null;
        try (Connection con = getConnection()) {
            PreparedStatement pst =
                    con.prepareStatement(select_city_ById);
            pst.setLong(1, Id);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                citizen = fillcity(rs);
            }
            rs.close();
            pst.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return citizen;
    }

    @Override
    public List<Citizen> findAll(){
        List<Citizen> list = new LinkedList<>();
        try (Connection con = getConnection();
             PreparedStatement pst =
                     con.prepareStatement(select_all);
             ResultSet rs = pst.executeQuery()) {
            while (rs.next()) {
                list.add(fillcity(rs));
            }
            rs.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }

    private Citizen fillcity(ResultSet rs) throws SQLException {
        Citizen citizen = new Citizen();
        citizen.setCitizenId(rs.getLong("citizen_id"));
        citizen.setPersonId(rs.getLong("person_id"));
        citizen.setDocumentId(rs.getLong("document_id"));
        citizen.setShifer(rs.getString("shifer"));
        return citizen;
    }

    public Citizen FindById(Long id, List<Citizen> citizens) {
        if (citizens != null) {
            for (Citizen r : citizens) {
                if ((r.getCitizenId()).equals(id)) {
                    return r;
                }
            }
        } else {
            return null;
        }
        return null;
    }
}
