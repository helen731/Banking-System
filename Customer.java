import java.util.Date;
/**
 *  Title      : Customer.java
 *  Description: This class is the class for Customer.
 *  @author  Han Zhang
 *  @version 1.0
 */
public class Customer {
	String name;
	String addr;
	Date dateOfB;
	boolean creditStatus;
	/**
	 * This constructor is used to initialize the initial state of a customer
	 * @param name		customer's name
	 * @param addr		customer's address
	 * @param dateOfB	customer's date of birth
	 */
	public Customer(String name, String addr, Date dateOfB){
		this.name = name;
		this.addr = addr;
		this.dateOfB = dateOfB;
		creditStatus = false;
	}
	/**
	 * This method get the name of customer
	 * @return String	name of customer
	 */
	public String getName(){
		return this.name;
	}
	
	/**
	 * This method get the address of customer
	 * @return String	address of a customer
	 */
	public String getaddr(){
		return this.addr;
	}
	
	/**
	 * This method get the date of birth of customer
	 * @return	Date	date of birth of customer
	 */
	public Date getDateOfB(){
		return this.dateOfB;
	}
	
	/**
	 * This method get customer's credit status
	 * @return	boolean		customer's credit status
	 */
	public boolean getCreditStatus(){
		return this.creditStatus;
	}
	
	/**
	 * This method set the customer's name
	 * @param name		customer's name
	 */
	public void setName(String name){
		this.name = name;
	}
	
	/**
	 * This method set the address of customer
	 * @param addr		customer's address
	 */
	public void setAddr(String addr){
		this.addr = addr;
	}
	
	/**
	 * This method set the date of birth of the customer
	 * @param d		customer's date of birth
	 */
	public void setDate(Date d){
		this.dateOfB = d;
	}
	
	/**
	 * This method set credit status of the customer
	 * @param creditStatus		customer's credit status
	 */
	public void setCreditStatus(boolean creditStatus){
		this.creditStatus = creditStatus;
	}
}
