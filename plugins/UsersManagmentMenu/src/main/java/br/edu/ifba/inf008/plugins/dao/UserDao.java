package br.edu.ifba.inf008.plugins.dao;

import br.edu.ifba.inf008.entities.User;
import br.edu.ifba.inf008.utils.DBConnection;

import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDao {
    
    public static List<User> searchUsersByName(String name) {

        List<User> users = new ArrayList<>();
        String query = "SELECT user_id , name, email FROM users WHERE name LIKE ?";

        try {
            Connection conn = DBConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, "%" + name + "%");

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                users.add(new User(rs.getInt("id"), rs.getString("name"), rs.getString("email")));
            }

        } catch(Exception e) {
            System.err.println("Erro ao listar os usuários: " + e.getMessage());
        }

        return users;
    }
}
