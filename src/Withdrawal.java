package src;
/**
 *  Title      : Withdrawal.java
 *  Description: This class is the class for Withdrawal.
 *  @author  Han Zhang
 *  @version 1.0
 */
public class Withdrawal extends Transaction{
	private double overlimit;
	private double balance;
	/**
	 * This constructor initialize the initial state of a withdrawal.
	 * @param money		money to be withdraw
	 * @param over		the overdraft limit
	 * @param balance	balance of account
	 */
	public Withdrawal(double money, double over, double balance) {
		super(money);
		this.overlimit = over;
		this.balance = balance;
	}
	/**
	 * This method is used to do the withdraw
	 * @return	double		the amount can withdraw
	 */
	public double doWithdraw(){
		if(balance+overlimit>=amount){
			return amount;
		}
		else{
			System.out.println("Withdraw " + amount
					+ " unsuccessfull. Do not have enough available funds.");
			return 0;
		}
	}

}
