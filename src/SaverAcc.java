
import java.util.Date;
/**
 *  Title      : SaverAcc.java
 *  Description: This class is the class for Saver Account.
 *  @author  Han Zhang
 *  @version 1.0
 */
public class SaverAcc extends Account{
	Date noticeDate;
	double noticeAmount;
	/** This constructor initialize the initial state of an Saver account.
     *  @param accNum		The account number of this account.
     *  @param customer		The customer who own this account.
     */
	public SaverAcc(String accNum, Customer customer) {
		super(accNum, customer);
		this.noticeNeeded = true;
		this.overdraftLimit = 0;
	}
	
}
