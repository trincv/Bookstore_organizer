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
            stmt.setString(1, name + "%");

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                users.add(new User(rs.getInt("user_id"), rs.getString("name"), rs.getString("email")));
            }

        } catch(Exception e) {
            System.err.println("Erro ao listar os usuários: " + e.getMessage());
        }

        return users;
    }

    public static boolean insertUser(String name, String email) {

        String query = "INSERT INTO users (name, email) VALUES (?, ?)";

        try {
            Connection conn = DBConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query);
            
            stmt.setString(1, name);
            stmt.setString(2, email);

            stmt.executeQuery();

        } catch(Exception e) {
            System.err.println("Erro ao cadastrar o usuário: " + e.getMessage());
            return false;
        }

        return true;
    }

    public static User getUserById(int id) {

        String query = "SELECT * FROM users WHERE user_id = ?";

        try {
            Connection conn = DBConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query);
            
            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();
            rs.next();
            
            User user = new User(rs.getInt("user_id"), rs.getString("name"), rs.getString("email"));

            return user;

        } catch(Exception e) {
            System.err.println("Erro ao buscar o usuário: " + e.getMessage());
            return null;
        }

    }

    public static boolean updateUser(User user) {

        String query = "UPDATE users SET name = ?, email = ? WHERE user_id = ?";

        try {
            Connection conn = DBConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query);
            
            stmt.setString(1, user.getName());
            stmt.setString(2, user.getEmail());
            stmt.setInt(3, user.getId());

            stmt.executeQuery();

        } catch(Exception e) {
            System.err.println("Erro ao atualizar o usuário: " + e.getMessage());
            return false;
        }

        return true;
    }

    public static boolean deleteUser(int id) {

        String query = "DELETE FROM users WHERE user_id = ?";

        try {
            Connection conn = DBConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query);
        
            stmt.setInt(1, id);

            stmt.executeQuery();

        } catch(Exception e) {
            System.err.println("Erro ao deletar o usuário: " + e.getMessage());
            return false;
        }

        return true;
    }

}
