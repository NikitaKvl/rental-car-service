package rental.car.service.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Renter {
    private Long id;
    private String name;
    private String driverLicenseNumber;
    private String login;
    private String password;
}
