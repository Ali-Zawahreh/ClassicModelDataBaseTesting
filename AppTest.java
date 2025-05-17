package DataBaseTesting.DataBaseTesting;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class AppTest {
	Connection con;
	Statement stmt;
	ResultSet rs;
	String CustomerName;
	String contactLastName;
	
	WebDriver driver = new ChromeDriver();

	@BeforeTest
	public void MySetup() throws SQLException {
		// Corrected 'localhost'
		con = DriverManager.getConnection("jdbc:mysql://localhost:3306/classicmodels", "root", "1234");
		driver.get("https://smartbuy-me.com/ar/account/register");
	}

	@Test(priority = 1, enabled = false)
	public void InsertIntoDataBase() throws SQLException {
		String query = "INSERT INTO customers (customerNumber, customerName, contactLastName, contactFirstName, phone, addressLine1, city, country, salesRepEmployeeNumber, creditLimit) "
				+ "VALUES (999, 'New Corp', 'Smith', 'John', '123456789', '123 Main St', 'Los Angeles', 'USA', 1370, 50000.00);";
		stmt = con.createStatement();
		int rowAffected = stmt.executeUpdate(query);
		System.out.println(rowAffected);
	}

	@Test(priority = 2, enabled = false)
	public void UpdateData() throws SQLException {
		String query = "UPDATE customers SET creditLimit = 75000 WHERE customerNumber = 999;";
		stmt = con.createStatement();
		int rowAffected = stmt.executeUpdate(query);
		System.out.println(rowAffected);
	}
	@Test(priority = 2, enabled = false)
	public void DeletData() throws SQLException {
		String query = "DELETE FROM customers WHERE customerNumber = 999;";
		stmt = con.createStatement();
		int rowAffected = stmt.executeUpdate(query);
		System.out.println(rowAffected);
	}

	@Test(priority = 2, enabled = false)
	public void ReadData() throws SQLException {
		String query = "SELECT * FROM customers WHERE customerNumber = 999;";
		stmt = con.createStatement();
		rs = stmt.executeQuery(query);
		while (rs.next()) {

			CustomerName = rs.getNString("contactFirstName");
			contactLastName= rs.getNString("contactLastName");
			System.out.println(CustomerName);
		}
		String Email ="@gmail.com";
		driver.findElement(By.id("customer[first_name]")).sendKeys(CustomerName);
		driver.findElement(By.id("customer[last_name]")).sendKeys(contactLastName);
		driver.findElement(By.id("customer[email]")).sendKeys(CustomerName+contactLastName+Email);

	}

}
