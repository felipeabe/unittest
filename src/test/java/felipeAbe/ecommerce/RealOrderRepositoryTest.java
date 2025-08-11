package felipeAbe.ecommerce;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;


class RealOrderRepositoryTest {

    @Spy
    private HashMap<Integer,Order> ordersSpy;

    @InjectMocks
    private RealOrderRepository realOrderRepository;

    @Nested
    class save{

        @Test
        @DisplayName("Should save order")
        void shouldSaveOrder(){

            //arrange
            var dummyOrder=new Order(1,"Bruno", 200);
            assertNull(realOrderRepository.findById(dummyOrder.getId()));

            //act
            realOrderRepository.save(dummyOrder);

            //assert
            verify(ordersSpy)
        }
    }




}