import repository.DB;
import service.BookingService;
import view.View;

import java.util.ArrayList;
import java.util.TreeMap;

public class TaxiBooking
{
    DB DB = new DB(new ArrayList<>(),new TreeMap<>());
    View view = new View();
    BookingService service = new BookingService(DB, view);
    public void start()
    {
        service.init();
    }
}
