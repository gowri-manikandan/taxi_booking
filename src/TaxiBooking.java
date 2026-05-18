import db.Repository;
import service.BookingService;
import view.View;

import java.util.ArrayList;
import java.util.TreeMap;

public class TaxiBooking
{
    Repository repository = new Repository(new ArrayList<>(),new TreeMap<>());
    View view = new View();                  // View has no dependencies
    BookingService service = new BookingService(repository, view);
    public void start()
    {
        service.init();
    }
}
