import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class DerivativeTest {

    @Test
    public void calculateSIRModel() {

        Derivative derivative = new Derivative();

        assertThat(derivative.calculation(0.99, 0.01, 0, 3.2, 0.23, 30))
                .isNotEmpty();

        assertThat(derivative.calculation(0.99, 0.01, 0, 3, 0.5, 30))
                .isNotEmpty();

        assertThat(derivative.calculation(45400, 2100, 2500, 0.00001, 0.07142857143, 30))
                .isNotEmpty();
    }
}