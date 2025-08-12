package felipeAbe.ecommerce;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RealOrderRepositoryTest {

    @Spy
    private HashMap<Integer, Order> ordersSpy;

    @Captor
    private ArgumentCaptor<Order> orderArgumentCaptor;

    @InjectMocks
    private RealOrderRepository realOrderRepository;

    @Nested
    class save {

        @Test
        @DisplayName("Should save order")
        void shouldSaveOrder() {

            //arrange
            var dummyOrder = new Order(1, "Bruno", 200);

            //act
            realOrderRepository.save(dummyOrder);

            //assert
            verify(ordersSpy, times(1)).put(eq(dummyOrder.getId()), orderArgumentCaptor.capture());
            var orderCaptured = orderArgumentCaptor.getValue();
            assertSame(dummyOrder, orderCaptured);
        }
    }

    @Nested
    class findById {

        @Test
        @DisplayName("Should find by id")
        void shouldFindById() {

            //arrange
            var id = 1;
            var dummyOrder = new Order(1, "Bruno", 200);
            doReturn(dummyOrder).when(ordersSpy).get(eq(id));

            //act
            var order = realOrderRepository.findById(id);

            //assert
            assertNotNull(order);
            assertSame(dummyOrder, order);
            verify(ordersSpy, times(1)).get(eq(id));

        }


        @Test
        @DisplayName("Should return null when order doesn't exists")
        void shouldFindByIdWhenOrderDoesntExists() {

            //arrange
            var id = 1;
            doReturn(null).when(ordersSpy).get(eq(id));

            //act
            var order = realOrderRepository.findById(id);

            //assert
            assertNull(order);
            verify(ordersSpy, times(1)).get(eq(id));

        }

    }
}






