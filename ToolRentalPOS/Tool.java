import java.util.*;

public class Tool
{
	private String toolCode;
	private String brand;
	private String type;
	private double dailyCost;
	private List<Holiday> nonChargeDays;

	public Tool(String toolCode, String brand, String type, double dailyCost, List<Holiday> nonChargeDays)
	{
		this.toolCode = toolCode;
		this.brand = brand;
		this.type = type;
		this.dailyCost = dailyCost;
		this.nonChargeDays = nonChargeDays;
	}

	public String getToolCode()
	{
		return toolCode;
	}

	public String getBrand()
	{
		return brand;
	}

	public String getType()
	{
		return type;
	}

	public double getDailyCost()
	{
		return dailyCost;
	}

	public List<Holiday> getNonChargeDays()
	{
		return nonChargeDays;
	}
}