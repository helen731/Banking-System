
import java.util.Date;

/**
 *  Title      : Transaction.java
 *  Description: This class is the class for Transaction.
 *  @author  Han Zhang
 *  @version 1.0
 */
public class Transaction {
	protected double amount;
	protected Date day;
	protected Date time;
	/**
	 * This constructor is used to initialize state of transaction
	 * @param money		money to be transact
	 */
	public Transaction(double money){
		this.amount = money;
	}
}
