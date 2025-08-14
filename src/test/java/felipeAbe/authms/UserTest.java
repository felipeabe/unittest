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
            String password="123";
            String otherPassword="456";
            var user=new User("admin", password);


            //act
            var isPasswordValid=user.isValidPassword(otherPassword);

            //assert
            assertFalse(isPasswordValid);
        }
    }

    @Nested
    class changePassword{

        @Test
        @DisplayName("Should change password when success")
        void shouldChangePasswordWhenPasswordIsValid(){

            //arrange
            String password="123";
            String newPassword="12345";
            User user=new User("teste", password);

            //act
            user.changePassword(newPassword);

            //assert
            assertEquals(newPassword, user.getPassword());
        }

        @Test
        @DisplayName("should throw exception when password is null")
        void shouldThrowExceptionWhenNull(){

            //arrange
            String password="123";
            String newPassword=null;
            User user=new User("teste", password);

            //act
            var ex=assertThrows(IllegalArgumentException.class, ()->{
                user.changePassword(newPassword);
            });

            //assert
            assertEquals("A senha não pode ser vazia", ex.getMessage());
        }

        @Test
        @DisplayName("should throw exception when is empty")
        void shouldThrowExceptionWhenEmpty(){

            //arrange
            String password="123";
            String newPassword="";
            User user=new User("teste", password);

            //act
            var ex=assertThrows(IllegalArgumentException.class, ()->{
                user.changePassword(newPassword);
            });

            //assert
            assertEquals("A senha não pode ser vazia", ex.getMessage());
        }
    }

}