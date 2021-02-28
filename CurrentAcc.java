/**
	 *  Title      : CurrentAccount.java
	 *  Description: This class is the class for CurrentAccount.
	 *  @author  Han Zhang
	 *  @version 1.0
	 */
public class CurrentAcc extends Account{
	/**
	 * This constructor is used to initialize the initial state of a current account.
	 * @param accNum		The account number of this account.
     *  @param customer		The customer who own this account.
	 */
	public CurrentAcc(String accNum, Customer customer) {
		super(accNum, customer);
		overdraftLimit = 500;
		noticeNeeded = false;
	}
}
