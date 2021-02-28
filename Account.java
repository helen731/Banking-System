import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
/**
 *  Title      : Account.java
 *  Description: This class is the class for Account.
 *  @author  Han Zhang
 *  @version 1.0
 */

public class Account {
	protected String accNum;
	protected int pin;
	protected double balance;
	protected double overdraftLimit;
	protected boolean isActive;
	protected boolean isSuspended;
	protected boolean noticeNeeded;
	protected double waitMoney;
	protected Customer customer;
	ArrayList<Transaction> tran = new ArrayList<Transaction>();
	
	
	/** This constructor initialize the initial state of an account.
     *  @param accNum		The account number of this account.
     *  @param customer		The customer who own this account.
     */
	public Account(String accNum, Customer customer){
		this.accNum = accNum;
		this.customer = customer;
		this.balance = 0.0;
		this.isActive = true;
		this.waitMoney = 0.0;
		generatePin();
	}
	
	/** This constructor initialize the initial state of an empty account.
     */
	public Account(){
		
	}

	 /** This method generate the pin number for this account. 
     */
	public void generatePin(){
		Random r = new Random();
		pin = 100000+r.nextInt(900000);
	}
	
    /** This method get the account number.
     *  @return String		account number
     */
	public String getAccNum(){
		return accNum;
	}
	
	/** This method get the owner of the account.
     *  @return Customer	customer constructor
     */
	public Customer getCustomer(){
		return customer;
	}
	
	/** This method get the balance the account.
     *  @return double	balance
     */
	public double getBalance(){
		return balance;
	}
	
	/** This method get the money waiting to be cleared of the account.
     *  @return double	the money waiting to be cleared
     */
	public double getWait(){
		return waitMoney;
	}
	
	/** This method get the pin number of the account.
     *  @return int	pin number
     */
	public int getPin(){
		return pin;
	}
	
	/** This method get the state of suspend of the account.
     *  @return boolean	whether the account is suspended
     */
	public boolean isSuspended(){
		return this.isSuspended;
	}
	
	/** This method get the overdraft limit of the account.
     *  @return double	overdraft limit of the account
     */
	public double getOverdraftLimit(){
		return this.overdraftLimit;
	}
	
	/** This method get the active state of the account.
     *  @return boolean	active state of the account
     */
	public boolean isActive(){
		return this.isActive;
	}
	
	/** This method get whether the account need notice.
     *  @return boolean	whether the account need notice
     */
	public boolean noticeNeeded(){
		return this.noticeNeeded;
	}
	
	 /** This method set the account number of the account. 
     *  @param accno  the account number
     */
	public void setAccNo(String accno){
		accNum = accno;
	}
	
	/** This method set the pin number of the account. 
     *  @param Pin  the pin number
     */
	public void setPin(int Pin){
		pin = Pin;
	}
	
	/** This method set the balance of the account. 
     *  @param balance  the balance
     */
	public void setBalance(double Balance){
		balance = Balance;
	}
	
	/** This method set the money waiting to be cleared of the account. 
     *  @param wait  the money waiting to be cleared 
     */
	public void setWait(double wait){
		waitMoney = wait;
	}
	
	/** This method set the overdraft limit of the account. 
     *  @param limit  the overdraft limit
     */
	public void setOver(double limit){
		overdraftLimit = limit;
	}
	
	/** This method set the active state of the account. 
     *  @param active  the active state of the account
     */
	public void setActive(boolean active){
		isActive = active;
	}
	
	/** This method set whether the account is suspended. 
     *  @param suspend  whether the account is suspended
     */
	public void setSuspend(boolean suspend){
		isSuspended = suspend;
	}
	
	/** This method set whether the account need notice. 
     *  @param notice   whether the account need notice
     */
	public void setNotice(boolean notice){
		noticeNeeded = notice;
	}
	
	/** This method set the customer of this account. 
     *  @param c   the customer
     */
	public void setCustomer(Customer c){
		customer = c;
	}
	
	/**
	 * This method add a deposit transaction
	 * @param money		money need to be deposited
	 * @param isCash	it is cash or cheque
	 */
	public void addDeposit(double money, boolean isCash){
		Deposit depo = new Deposit(money,isCash);
		this.balance = this.getBalance() + depo.balanceChange();
		waitMoney = depo.getWaitMoney();
		tran.add(depo);
	}
	
	/**
	 * This method add a withdraw transaction to the account
	 * @param money	Money to be withdrawn
	 */
	public void addWithdraw(double money){
		Withdrawal withdr =new Withdrawal(money, overdraftLimit, balance);
		this.balance = this.getBalance() - withdr.doWithdraw();
		tran.add(withdr);
	}
	
	/**
	 * This method is used to clear funds
	 */
	public void clearFunds(){
		this.balance = getBalance() + waitMoney;
		this.waitMoney = 0;
	}
	/**
	 * This method is used to print the account details
	 */
	public String toString() {
		return "Account number: " + accNum + "\n" 
    + "Balance: " + balance + "\n"
    + "OverdraftLimit:" + overdraftLimit + "\n"
    + "Is suspended: " + isSuspended + "\n" 
    + "Money waiting to be cleared:" + waitMoney + "\n" ;
      }
}
