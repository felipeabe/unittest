package felipeAbe.authms;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
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

    @Captor
    private ArgumentCaptor<User> useraC;

    @InjectMocks
    private AuthService authService;

    @Nested
    class authenticate{

        @Test
        @DisplayName("Should return false when user doesn't exists")
        void shouldReturnFalseWhenUserDoesntExists(){

            //arrange
            String username="teste";
            String password="123";
            User user=new User(username, password);
            doReturn(null).when(userRepository).findByUsername(eq(username));

            //act
            boolean isAuth=authService.authenticate(username, password);

            //assert
            assertFalse(isAuth);
            verify(userRepository, times(1)).findByUsername(eq(username));
        }

        @Test
        @DisplayName("Should return false when auth fails")
        void shouldReturnFalseWhenAuthFails(){
            String username="teste";
            String password="123";
            doReturn(user).when(userRepository).findByUsername(eq(username));
            doReturn(false).when(user).isValidPassword(eq(password));

            //act
            boolean isAuth=authService.authenticate(username, password);

            //assert
            assertFalse(isAuth);
            verify(user,times(1)).isValidPassword(eq(password));
            verify(userRepository, times(1)).findByUsername(eq(username));
        }

        @Test
        @DisplayName("Should return true when auth complete")
        void shouldReturnTrueWhenAuthComplete(){

            //arrange
            String username="teste";
            String password="123";
            doReturn(user).when(userRepository).findByUsername(eq(username));
            doReturn(true).when(user).isValidPassword(eq(password));

            //act
            boolean isAuth=authService.authenticate(username, password);

            //assert
            assertTrue(isAuth);
            verify(user,times(1)).isValidPassword(eq(password));
            verify(userRepository, times(1)).findByUsername(eq(username));
        }
    }

    @Nested
    class register{

        @Test
        @DisplayName("Should register with success when user doesn't exist")
        void shouldSaveUser(){

            //arrange
            String username="teste";
            String password="123";
            doReturn(null).when(userRepository).findByUsername(eq(username));

            //act
            authService.register(username,password);

            //assert
            verify(userRepository,times(1)).findByUsername(eq(username));
            verify(userRepository,times(1)).save(useraC.capture());

            User userCaptured=useraC.getValue();
            assertEquals(username, userCaptured.getUsername());
            assertEquals(password, userCaptured.getPassword());
        }

        @Test
        @DisplayName("Should throw exception when user already exists")
        void shouldThrowExceptionWhenUserAlreadyExists(){

            //arrange
            String username="teste";
            String password="123";
            doReturn(user).when(userRepository).findByUsername(eq(username));

            //act
            var ex=assertThrows(IllegalArgumentException.class, ()->{
                authService.register(username,password);
            });

            //assert
            verify(userRepository,times(1)).findByUsername(eq(username));
            verify(userRepository,times(0)).save(any());

            assertEquals("Usuário já existe", ex.getMessage());
        }
    }

}