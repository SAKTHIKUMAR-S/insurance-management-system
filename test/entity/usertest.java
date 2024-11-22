package entity;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    @Test
    void testUserEntity() {
        User user = new User(1, "JohnDoe", "password123", "admin");
        assertEquals(1, user.getUserId());
        assertEquals("JohnDoe", user.getUsername());
        assertEquals("password123", user.getPassword());
        assertEquals("admin", user.getRole());
    }
}
