package epf;

import static org.junit.Assert.assertTrue;

import com.epf.rentmanager.Exception.ServiceException;
import com.epf.rentmanager.model.Reservation;
import com.epf.rentmanager.service.ClientService;
import com.epf.rentmanager.service.VehicleService;
import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest {

    public static void main(String[] args) {
        final long ID_QUE_JE_VEUX = 2;
        try {
            System.out.println(ClientService.getInstance().findById(ID_QUE_JE_VEUX));
            System.out.println(VehicleService.getInstance().findById(2));
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }
    }
}