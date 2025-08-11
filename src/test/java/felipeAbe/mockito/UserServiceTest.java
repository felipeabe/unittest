package felipeAbe.mockito;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private Database databaseMock;


    @InjectMocks //injeta os mocks (dataBaseMock na userService),
    // pois o UserService depebde de Database
    private UserService userService;


    @Nested
    class getUserStatus{

        @Test
        @DisplayName("Should return an active user")
        void shouldReturnAnActiveUser(){

            //arrange
            int userid=1;
            String expectedStatus="ACTIVE";
            doReturn(expectedStatus).when(databaseMock).getStatus(userid); //stub

            //act
            var output=userService.getUserStatus(userid);

            //assert
            assertEquals(expectedStatus, output);
            verify(databaseMock, times(1)).getStatus(userid);
        }

    }

}