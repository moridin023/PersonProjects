import java.util.*;
import java.text.*;

public class RentalAgreement
{
	private static SimpleDateFormat sdf = new SimpleDateFormat("M/d/yy");
	private static NumberFormat cf = NumberFormat.getCurrencyInstance();

	private Tool tool;
	private int rentalDays;
	private Date checkout;
	private int discountPercent;

	public RentalAgreement(String toolCode, int rentalDays, int discountPercent, Date checkout)
	{
		this.tool = ToolManager.getTool(toolCode);
		this.rentalDays = rentalDays;
		this.discountPercent = discountPercent;
		this.checkout = checkout;
	}

	public int getRentalDays()
	{
		return rentalDays;
	}

	public Date getCheckoutDate()
	{
		return checkout;
	}

	public String getCheckoutString()
	{
		return sdf.format(checkout);
	}

	public int getDiscountPercent()
	{
		return discountPercent;
	}

	public String getDiscountPercentString()
	{
		return discountPercent + "%";
	}

	public String getToolCode()
	{
		return tool.getToolCode();
	}

	public String getToolBrand()
	{
		return tool.getBrand();
	}

	public String getToolType()
	{
		return tool.getType();
	}

	public Date getReturnDate()
	{
		Calendar returnDate = Calendar.getInstance();
		returnDate.setTime(checkout);
		returnDate.add(Calendar.DATE, rentalDays);

		return returnDate.getTime();
	}

	public String getReturnString()
	{
		return sdf.format(getReturnDate());
	}

	public double getDailyRentalCharge()
	{
		return tool.getDailyCost();
	}

	public String getDailyRentalChargeString()
	{
		return cf.format(tool.getDailyCost());
	}

	public int getChargeDays()
	{
		int chargeDays = rentalDays;

		List<Holiday> nonChargeDays = tool.getNonChargeDays();

		for(Holiday h : nonChargeDays)
		{
			chargeDays -= h.holidaysInRange(getCheckoutDate(), getReturnDate());
		}

		return chargeDays;
	}

	public double getPreDiscountCharge()
	{
		return getChargeDays() * getDailyRentalCharge();
	}

	public String getPreDiscountChargeString()
	{
		return cf.format(getPreDiscountCharge());
	}

	public double getDiscountAmount()
	{
		return Math.round((getPreDiscountCharge() * (getDiscountPercent()/100.0)) * 100) /100.0;
	}

	public String getDiscountAmountString()
	{
		return cf.format(getDiscountAmount());
	}

	public double getFinalCharge()
	{
		return getPreDiscountCharge() - getDiscountAmount();
	}

	public String getFinalChargeString()
	{
		return cf.format(getFinalCharge());
	}
}