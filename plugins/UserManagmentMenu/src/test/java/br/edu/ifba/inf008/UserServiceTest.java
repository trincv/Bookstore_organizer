package br.edu.ifba.inf008;

import br.edu.ifba.inf008.plugins.dao.UserDao;
import br.edu.ifba.inf008.plugins.model.User;
import br.edu.ifba.inf008.plugins.service.UserService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Collections;
import java.util.List;

public class UserServiceTest {

    private UserService userService;
    private UserDao mockUserDao;

    @BeforeEach
    void setUp() {
        mockUserDao = new UserDao() {
            @Override
            public boolean insertUser(String name, String email) {
                return true;
            }
            @Override
            public List<User> searchUsersByName(String name) {
                if ("Luiz".equals(name)) {
                    return Collections.singletonList(new User(1, "Luiz Silva", "luiz@gmail.com"));
                }
                return Collections.emptyList();
            }
        };
        
        userService = new UserService(mockUserDao);
    }

    @Test
    void testRegisterUser_ValidData_ReturnsTrue() {
        boolean result = userService.registerUser("Maria", "maria@email.com");
        assertTrue(result);
    }

    @Test
    void testSearchUsersByName_ExistingUser_ReturnsListWithUser() {
        List<User> users = userService.searchUsersByName("Luiz");
        assertFalse(users.isEmpty());
        assertEquals("Luiz Silva", users.get(0).getName());
    }

    @Test
    void testSearchUsersByName_NonExistingUser_ReturnsEmptyList() {
        List<User> users = userService.searchUsersByName("NonExistent");
        assertTrue(users.isEmpty());
    }
}