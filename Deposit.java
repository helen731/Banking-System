
/**
 *  Title      : Deposit.java
 *  Description: This class is the class for Deposit.
 *  @author  Han Zhang
 *  @version 1.0
 */
public class Deposit extends Transaction{
	private boolean cleared;
	private boolean isCash;
	private double waitMoney;
	/**
	 * This constructor initialize the initial state of a deposit.
	 * @param money		the money to be deposit
	 * @param isCash	whether the money is cash
	 */
	public Deposit(double money, boolean isCash) {
		super(money);
		this.cleared = false;
		this.isCash = isCash;

	}
	/**
	 * This method set cleared to true
	 */
	public void setCleared(){
		this.cleared = true;
	}
	
	/**
	 * This method get whether the account is cleared
	 * @return boolean		whether the account is cleared
	 */
	public boolean getCleared(){
		return this.cleared;
	}
	
	/**
	 * This method get the wait money
	 * @return double		money waited to be cleared
	 */
	public double getWaitMoney(){
		return waitMoney;
	}
	/**
	 * This method is used to return the balance to be changed
	 * @return double balance to be changed
	 */
	public double balanceChange(){
		if(isCash == true)
			return amount;
		else{
			System.out.println("waiting for the fund to be cleared");
			waitMoney = amount;
			return 0;
		}
	}
}
