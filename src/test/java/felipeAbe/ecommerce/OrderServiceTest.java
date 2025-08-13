package felipeAbe.ecommerce;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OrderServiceTest {

    @Mock
    private OrderRepository repository;

    @Captor
    private ArgumentCaptor<Order> orderArgumentCaptor;

    @InjectMocks
    private OrderService orderService;

    @Nested
    class placeOrder {

        @Test
        @DisplayName("place order when total is greater than zero")
        void shouldPlaceAnOrderWhenTotalIsGreaterThanZero(){

            //arrange
            Order dummyOrder=new Order(1,"random",5);

            //act
            orderService.placeOrder(dummyOrder);

            //asert
            verify(repository, times(1)).save(orderArgumentCaptor.capture());
            var orderCaptured=orderArgumentCaptor.getValue();
            assertSame(dummyOrder, orderCaptured);
        }

        @ParameterizedTest
        @ValueSource(doubles = {0, -2.0, -50.0})
        @DisplayName("place order when total is below or equal to zero")
        void shouldThrowExceptionWhenTotalIsZero(double total){

            //arrange
            var dummyOrder=new Order(1,"random",total);

            //act
            assertThrows(IllegalArgumentException.class, ()->{
                orderService.placeOrder(dummyOrder);
            });

            //asert
            verify(repository, times(0)).save(any());
        }

        @Test
        @DisplayName("should throw exception when place order")
        void shouldThrowExceptionWhenPlaceOrder(){

            //arrange
            Order dummyOrder=new Order(1,"random",5);
            doThrow(new RuntimeException()).when(repository).save(any());

            //act
            assertThrows(RuntimeException.class,()->{
                orderService.placeOrder(dummyOrder);
            });

        }
    }

    @Nested
    class getOrder{

        @Test
        @DisplayName("Should return order when exists")
        void shouldGReturnOrderWhenExists(){

            //arrange
            int idOrder=1;
            Order dummyOrder=new Order(idOrder,"random",5);
            doReturn(dummyOrder).when(repository).findById(eq(idOrder));

            //act
            var output=orderService.getOrder(idOrder);

            //assert
            assertNotNull(output);
            assertSame(dummyOrder, output);
            verify(repository, times(1)).findById(eq(idOrder));
        }

        @Test
        @DisplayName("Should return null when order doesn't exists")
        void shouldGReturnNullWhenOrderDoesntExists(){

            //arrange
            int idOrder=1;
            doReturn(null).when(repository).findById(eq(idOrder));

            //act
            var output=orderService.getOrder(idOrder);

            //assert
            assertNull(output);
            verify(repository, times(1)).findById(eq(idOrder));
        }
    }

}