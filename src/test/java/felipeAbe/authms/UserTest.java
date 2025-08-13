package felipeAbe.authms;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    @Nested
    class isValidPassword{

        @Test
        @DisplayName("Should return true when password is valid")
        void shouldReturnTrueWhenPasswordIsValid(){

            //arrange
            String password="123";
            var user=new User("admin", password);

            //act
            var isPasswordValid=user.isValidPassword(password);

            //assert
            assertTrue(isPasswordValid);
        }

        @Test
        @DisplayName("Should return false when password is invalid")
        void shouldReturnFalseWhenPasswordIsInvalid(){

            //arrange

            //act

            //assert

        }
    }

}