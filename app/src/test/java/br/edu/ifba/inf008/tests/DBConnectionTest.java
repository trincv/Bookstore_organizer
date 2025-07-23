package br.edu.ifba.inf008.tests;

import br.edu.ifba.inf008.utils.DBConnection;
import org.junit.jupiter.api.Test;

import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.*;

class DBConnectionTest {

    @Test
    void testConexaoComBanco() {

        try (Connection conn = DBConnection.getConnection()) {
            assertNotNull(conn);
            assertFalse(conn.isClosed());
            System.out.println("Conexão com o banco bem-sucedida!");
        } catch (Exception e) {
            System.err.println("Falha ao conectar com o banco: " + e.getMessage());
        }

    }
}