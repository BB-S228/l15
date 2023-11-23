package com.example.dianaee.DAO;

import com.example.dianaee.DAO.Connection.ConnectionBuilder;
import com.example.dianaee.DAO.Connection.DbConnectionBuilder;
import com.example.dianaee.Models.Document;
import com.example.dianaee.Models.Person;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class DocumentDAO implements RepositoryDAO<Document>{
    public DocumentDAO() {};

    private static final String select_all = "SELECT document_id, name, serial_doc, num_doc, organ, date_doc FROM document";
    private static final String select_doc_ById = "SELECT document_id, name, serial_doc, num_doc, organ, date_doc FROM document WHERE document_id =?";
    private static final String insert_doc = "INSERT INTO document(name, serial_doc, num_doc, organ, date_doc) VALUES(?, ?, ?, ?, ?)";
    private static final String edit_doc = "UPDATE document SET name = ?, serial_doc = ?, num_doc = ?, organ = ?, date_doc = ? WHERE document_id = ? ";
    private static final String delete_doc = "DELETE FROM document WHERE document_id = ?";

    private ConnectionBuilder builder = new DbConnectionBuilder();
    private Connection getConnection() throws SQLException {
        return builder.getConnection();
    }

    @Override
    public Long insert (Document document) throws Exception {
        try (Connection con = getConnection();
             PreparedStatement pst =
                     con.prepareStatement(insert_doc, new String[] { "document_id" })) {
            long Id = -1L;
            pst.setString(1, document.getName());
            pst.setString(2, document.getSerial());
            pst.setString(3, document.getNumber());
            pst.setString(4, document.getOrgan());
            pst.setString(5, document.getDate());
            pst.executeUpdate();
            ResultSet gk = pst.getGeneratedKeys();
            if (gk.next()) {
                Id = gk.getLong("document_id");
            }
            gk.close();
            return Id;
        } catch (Exception e) {
            System.out.println(e);
        }
        return -1L;
    }

    @Override
    public void update(Document document) {
        try (Connection con = getConnection(); PreparedStatement pst
                = con.prepareStatement(edit_doc)) {
            pst.setString(1, document.getName());
            pst.setString(2, document.getSerial());
            pst.setString(3, document.getNumber());
            pst.setString(4, document.getOrgan());
            pst.setString(5, document.getDate());
            pst.setLong(6, document.getDocumentId());
            pst.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Override
    public void delete(Long Id) {
        try (Connection con = getConnection(); PreparedStatement pst
                = con.prepareStatement(delete_doc)) {
            pst.setLong(1, Id);
            pst.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Override
    public Document findById(Long Id) {
        Document document = null;
        try (Connection con = getConnection()) {
            PreparedStatement pst =
                    con.prepareStatement(select_doc_ById);
            pst.setLong(1, Id);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                document = filldocument(rs);
            }
            rs.close();
            pst.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return document;
    }

    @Override
    public List<Document> findAll(){
        List<Document> list = new LinkedList<>();
        try (Connection con = getConnection();
             PreparedStatement pst =
                     con.prepareStatement(select_all);
             ResultSet rs = pst.executeQuery()) {
            while (rs.next()) {
                list.add(filldocument(rs));
            }
            rs.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }

    private Document filldocument(ResultSet rs) throws SQLException {
        Document document = new Document();
        document.setDocumentId(rs.getLong("document_id"));
        document.setName(rs.getString("name"));
        document.setSerial(rs.getString("serial_doc"));
        document.setNumber(rs.getString("num_doc"));
        document.setOrgan(rs.getString("organ"));
        document.setDate(rs.getString("date_doc"));
        return document;
    }

    public Document FindById(Long id, List<Document> document) {
        if (document != null) {
            for (Document r : document) {
                if ((r.getDocumentId()).equals(id)) {
                    return r;
                }
            }
        } else {
            return null;
        }
        return null;
    }
}
