package br.edu.ifba.inf008.plugins.interfaces;

import java.util.List;

import br.edu.ifba.inf008.plugins.model.User;

public interface IUserService {
    
    public List<User> searchUsersByName(String name);

    public boolean registerUser(String name, String email);

    public User getUserById(int id);

    public boolean updateUser(User user);

    public boolean deleteUser(int id);
}
