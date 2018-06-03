import java.util.*;
import java.text.*;

public class PointOfSale
{
	public RentalAgreement checkout(String toolCode, int rentalDayCount, int discountPercent, String checkoutDate) throws Exception
	{
		//Validate info and error if necessary
		if(rentalDayCount < 1)
		{
			throw new Exception("The rental days must be 1 or greater");
		}

		if(discountPercent < 0 || discountPercent > 100)
		{
			throw new Exception("The discount percentage has to be between 0 and 100");
		}

		DateFormat format = new SimpleDateFormat("M/d/yy");
		Date checkout = format.parse(checkoutDate);

		return new RentalAgreement(toolCode, rentalDayCount, discountPercent, checkout);
	}
}