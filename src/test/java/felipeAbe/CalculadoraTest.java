package felipeAbe;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalculadoraTest {

    private Calculadora calculadora=new Calculadora();

    @Nested
    class somar{

        @Test
        @DisplayName("Should add two numbers")
        public void shouldAddTwoNumbers(){

            //arrange
            int a=5;
            int b=3;

            //act
            var output=calculadora.somar(a,b);

            //assert
            assertEquals(8,output);
        }

        @Test
        @DisplayName("Should add when zero")
        void shouldAddWhentwoNumbersIsZero(){
            //arrange
            int a=0;
            int b=0;

            //act
            var output=calculadora.somar(a,b);

            //assert
            assertEquals(0,output);
        }
    }

    @Nested
    class subtair{

        @Test
        @DisplayName("Should subtract two numbers")
        void shoudlSubtractTwoNumbers(){

            //arrange
            int a =5;
            int b =3;

            //act
            var output = calculadora.subtrair(a,b);

            //assert
            assertEquals(2,output);
        }
    }

    @Nested
    class multiplicar{

        @Test
        @DisplayName("Should multiply two numbers")
        void shouldMultiplyTwoNumbers(){

            //arramge
            int a=10;
            int b=2;

            //act
            var output=calculadora.multiplicar(a,b);

            //asert
            assertEquals(20,output);
        }
    }

    @Nested
    class dividir{

        @Test
        @DisplayName("Should throw exception when divide to zero")
        void shouldThrowExceptionWhenDivideToZero(){

            int a=10;
            int b=0;

            var ex=assertThrows(ArithmeticException.class,()->{
                calculadora.dividir(a,b);
            });

            assertEquals("Divisão por zero não permitida.", ex.getMessage());
        }
    }





}