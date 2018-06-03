import static org.junit.Assert.*;
import org.junit.Test;

public class PointOfSaleTest 
{
  	@Test
  	public void test1() 
  	{
    	PointOfSale pos = new PointOfSale();
    	boolean exception = false;

    	try
    	{
    		pos.checkout("JAKR", 5, 101, "9/3/15");
    	}
    	catch(Exception ex)
    	{
    		exception = true;
    	}

    	assertTrue("Failure - should have thrown an exception", exception);
  	}

  	@Test
  	public void test2()
  	{
  		PointOfSale pos = new PointOfSale();
    	boolean exception = false;

    	try
    	{
    		RentalAgreement ra = pos.checkout("LADW", 5, 10, "9/3/15");

    		assertEquals("Failure - Tool code not equal", "LADW", ra.getToolCode());
    		assertEquals("Failure - Checkout date not equal", "9/3/15", ra.getCheckoutString());
    		assertEquals("Failure - Rental days not equal", 5, ra.getRentalDays());
    		assertEquals("Failure - Discount percent not equal", "10%", ra.getDiscountPercentString());
    		assertEquals("Failure - Return date not equal", "9/8/15", ra.getReturnString());
    		assertEquals("Failure - Rental charge not equal", "$1.99", ra.getDailyRentalChargeString());
    		assertEquals("Failure - Charge days not equal", 5, ra.getChargeDays());
    		assertEquals("Failure - Prediscount charge not equal", "$9.95", ra.getPreDiscountChargeString());
    		assertEquals("Failure - Discount amount not equal", "$1.00", ra.getDiscountAmountString());
    		assertEquals("Failure - Final charge not equal", "$8.95", ra.getFinalChargeString());
    	}
    	catch(Exception ex)
    	{
    		exception = true;
    	}

    	assertFalse("Failure - should not have thrown an exception", exception);
  	}

  	@Test
  	public void test3()
  	{
  		PointOfSale pos = new PointOfSale();
    	boolean exception = false;

    	try
    	{
    		RentalAgreement ra = pos.checkout("CHNS", 5, 25, "7/2/15");

    		assertEquals("Failure - Tool code not equal", "CHNS", ra.getToolCode());
    		assertEquals("Failure - Checkout date not equal", "7/2/15", ra.getCheckoutString());
    		assertEquals("Failure - Rental days not equal", 5, ra.getRentalDays());
    		assertEquals("Failure - Discount percent not equal", "25%", ra.getDiscountPercentString());
    		assertEquals("Failure - Return date not equal", "7/7/15", ra.getReturnString());
    		assertEquals("Failure - Rental charge not equal", "$1.49", ra.getDailyRentalChargeString());
    		assertEquals("Failure - Charge days not equal", 3, ra.getChargeDays());
    		assertEquals("Failure - Prediscount charge not equal", "$4.47", ra.getPreDiscountChargeString());
    		assertEquals("Failure - Discount amount not equal", "$1.12", ra.getDiscountAmountString());
    		assertEquals("Failure - Final charge not equal", "$3.35", ra.getFinalChargeString());
    	}
    	catch(Exception ex)
    	{
    		exception = true;
    	}

    	assertFalse("Failure - should not have thrown an exception", exception);
  	}

  	@Test
  	public void test4()
  	{
  		PointOfSale pos = new PointOfSale();
    	boolean exception = false;

    	try
    	{
    		RentalAgreement ra = pos.checkout("JAKD", 6, 0, "9/3/15");

    		assertEquals("Failure - Tool code not equal", "JAKD", ra.getToolCode());
    		assertEquals("Failure - Checkout date not equal", "9/3/15", ra.getCheckoutString());
    		assertEquals("Failure - Rental days not equal", 6, ra.getRentalDays());
    		assertEquals("Failure - Discount percent not equal", "0%", ra.getDiscountPercentString());
    		assertEquals("Failure - Return date not equal", "9/9/15", ra.getReturnString());
    		assertEquals("Failure - Rental charge not equal", "$2.99", ra.getDailyRentalChargeString());
    		assertEquals("Failure - Charge days not equal", 3, ra.getChargeDays());
    		assertEquals("Failure - Prediscount charge not equal", "$8.97", ra.getPreDiscountChargeString());
    		assertEquals("Failure - Discount amount not equal", "$0.00", ra.getDiscountAmountString());
    		assertEquals("Failure - Final charge not equal", "$8.97", ra.getFinalChargeString());
    	}
    	catch(Exception ex)
    	{
    		exception = true;
    	}

    	assertFalse("Failure - should not have thrown an exception", exception);
  	}

  	@Test
  	public void test5()
  	{
  		PointOfSale pos = new PointOfSale();
    	boolean exception = false;

    	try
    	{
    		RentalAgreement ra = pos.checkout("JAKR", 9, 0, "7/2/15");

    		assertEquals("Failure - Tool code not equal", "JAKR", ra.getToolCode());
    		assertEquals("Failure - Checkout date not equal", "7/2/15", ra.getCheckoutString());
    		assertEquals("Failure - Rental days not equal", 9, ra.getRentalDays());
    		assertEquals("Failure - Discount percent not equal", "0%", ra.getDiscountPercentString());
    		assertEquals("Failure - Return date not equal", "7/11/15", ra.getReturnString());
    		assertEquals("Failure - Rental charge not equal", "$2.99", ra.getDailyRentalChargeString());
    		assertEquals("Failure - Charge days not equal", 5, ra.getChargeDays());
    		assertEquals("Failure - Prediscount charge not equal", "$14.95", ra.getPreDiscountChargeString());
    		assertEquals("Failure - Discount amount not equal", "$0.00", ra.getDiscountAmountString());
    		assertEquals("Failure - Final charge not equal", "$14.95", ra.getFinalChargeString());
    	}
    	catch(Exception ex)
    	{
    		exception = true;
    	}

    	assertFalse("Failure - should not have thrown an exception", exception);
  	}

  	@Test
  	public void test6()
  	{
  		PointOfSale pos = new PointOfSale();
    	boolean exception = false;

    	try
    	{
    		RentalAgreement ra = pos.checkout("JAKR", 4, 50, "7/2/20");

    		assertEquals("Failure - Tool code not equal", "JAKR", ra.getToolCode());
    		assertEquals("Failure - Checkout date not equal", "7/2/20", ra.getCheckoutString());
    		assertEquals("Failure - Rental days not equal", 4, ra.getRentalDays());
    		assertEquals("Failure - Discount percent not equal", "50%", ra.getDiscountPercentString());
    		assertEquals("Failure - Return date not equal", "7/6/20", ra.getReturnString());
    		assertEquals("Failure - Rental charge not equal", "$2.99", ra.getDailyRentalChargeString());
    		assertEquals("Failure - Charge days not equal", 1, ra.getChargeDays());
    		assertEquals("Failure - Prediscount charge not equal", "$2.99", ra.getPreDiscountChargeString());
    		assertEquals("Failure - Discount amount not equal", "$1.50", ra.getDiscountAmountString());
    		assertEquals("Failure - Final charge not equal", "$1.49", ra.getFinalChargeString());
    	}
    	catch(Exception ex)
    	{
    		exception = true;
    	}

    	assertFalse("Failure - should not have thrown an exception", exception);
  	}
}