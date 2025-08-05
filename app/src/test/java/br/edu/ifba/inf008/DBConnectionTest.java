package br.edu.ifba.inf008;

import br.edu.ifba.inf008.utils.DBConnection;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class DBConnectionTest {

    @Test
    void testConexaoComBanco() throws SQLException { 

        try (Connection conn = DBConnection.getConnection()) {
            
            assertNotNull(conn, "The connection can't be null");
            assertFalse(conn.isClosed(), "The connection can't be closed");
            System.out.println("Connection succeed");
        }
        
    }

}