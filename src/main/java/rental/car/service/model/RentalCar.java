package rental.car.service.model;

import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RentalCar {
    private Long id;
    private String model;
    private Manufacturer manufacturer;
    private List<Renter> renters = new ArrayList<>();
}
