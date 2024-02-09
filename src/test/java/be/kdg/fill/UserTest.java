package be.kdg.fill;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import be.kdg.fill.models.core.User;

import static org.junit.jupiter.api.Assertions.*;

public class UserTest {
    private User user;

    @BeforeEach
    public void setUp() {
        try {
            user = new User("username", "password");
        } catch (Exception e) {
            fail("User constructor should not throw an exception");
        }
    }

    @Test
    public void testGetUsername() {
        assertEquals("username", user.getUsername());
    }

    @Test
    public void testSetUsername() {
        assertThrows(IllegalArgumentException.class, () -> user.setUsername(null));
        assertThrows(IllegalArgumentException.class, () -> user.setUsername("ab"));
        assertThrows(IllegalArgumentException.class, () -> user.setUsername("a".repeat(21)));
        user.setUsername("newUsername");
        assertEquals("newUsername", user.getUsername());
    }

    @Test
    public void testSetPassword() {
        assertThrows(IllegalArgumentException.class, () -> user.setPassword(null));
        assertThrows(IllegalArgumentException.class, () -> user.setPassword("1234567"));
        assertThrows(IllegalArgumentException.class, () -> user.setPassword("a".repeat(21)));
    }
}