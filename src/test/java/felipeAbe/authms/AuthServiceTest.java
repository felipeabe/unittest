package felipeAbe.authms;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AuthServiceTest {

    @Mock
    private User user;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private AuthService authService;

    @Nested
    class authenticate{

        @Test
        @DisplayName("Should return false when user doesn't exists")
        void shouldReturnFalseWhenUserDoesntExists(){

            //arrange
            User user=new User("teste", "123");
            String username="teste";
            String password="123";
            doReturn(null).when(userRepository.findByUsername(eq(username)));

            //act
            var isAuth=authService.authenticate(username, password);

            //assert
            assertFalse(isAuth);
            verify(userRepository, times(1)).findByUsername(eq(username));
        }

        @Test
        @DisplayName("Should return false when auth fails")
        void shouldReturnFalseWhenAuthFails(){

            //arrange
            String username="teste";
            String password="123";
            doReturn(user).when(userRepository.findByUsername(eq(username)));
            doReturn(false).when(user).isValidPassword(eq(password));

            //act
            var isAuth=authService.authenticate(username, password);

            //assert
            assertFalse(isAuth);
            verify(userRepository, times(1)).findByUsername(eq(username));
            verify(user, times(1)).isValidPassword(eq(password));
        }
    }

}