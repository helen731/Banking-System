package src;
/**
 *  Title      : JuniorAcc.java
 *  Description: This class is the class for Junior Account.
 *  @author  Han Zhang
 *  @version 1.0
 */
public class JuniorAcc extends Account{
	/** This constructor initialize the initial state of Junior account.
     *  @param accNum		The account number of this account.
     *  @param customer		The customer who own this account.
     */
	public JuniorAcc(String accNum, Customer customer) {
		super(accNum, customer);
		overdraftLimit = 0;
		noticeNeeded = false;
	}
}
