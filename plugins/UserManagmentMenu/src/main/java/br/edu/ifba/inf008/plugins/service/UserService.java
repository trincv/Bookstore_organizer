package br.edu.ifba.inf008.plugins.service;

import br.edu.ifba.inf008.plugins.dao.UserDao;
import br.edu.ifba.inf008.plugins.interfaces.IUserService;
import br.edu.ifba.inf008.plugins.model.User;
import br.edu.ifba.inf008.utils.ValidationUtils;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

import java.util.Set;
import java.util.List;

public class UserService implements IUserService {

    private final UserDao userDao;
    private final Validator validator;

    public UserService(UserDao userDao) {
        this.userDao = userDao;
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        this.validator = factory.getValidator();
    }

    public List<User> searchUsersByName(String name) {
        return userDao.searchUsersByName(name);
    }

    public boolean registerUser(String name, String email) {

        User newUser = new User(name, email);

        Set<ConstraintViolation<User>> violations = validator.validate(newUser);

        if(ValidationUtils.handleViolations(violations) == false) return false;

        return userDao.insertUser(name, email);
    }

    public User getUserById(int id) {
        return userDao.getUserById(id);
    }

    public boolean updateUser(User user) {

        Set<ConstraintViolation<User>> violations = validator.validate(user);

        if(ValidationUtils.handleViolations(violations) == false) return false;

        return userDao.updateUser(user);
    }

    public boolean deleteUser(int id) {
        return userDao.deleteUser(id);
    }

}

