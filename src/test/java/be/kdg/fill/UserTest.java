package be.kdg.fill;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import be.kdg.fill.models.core.User;

import static org.junit.jupiter.api.Assertions.*;

// I wanted to mess around with testing a little bit to learn it.
// So if you're asking why there are no other tests, I simply didn't have time for it. 
// But i'm still leaving this here because it proves i spend some time learning it 👍.
// - Tim

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