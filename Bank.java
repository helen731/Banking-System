import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.Scanner;
/**
 *  Title      : Bank.java
 *  Description: This class is the class for Bank.
 *  @author  Han Zhang
 *  @version 1.0
 */
public class Bank {
	private ArrayList<Account> bank = new ArrayList<Account>();
	Scanner sc = new Scanner(System.in); 
	
	/**
	 * This method is used to read file and put the details in the corresponding position 
	 * of the ArrayList
	 */
	public void readFromFile(){
		bank.clear();
		try {
			File f = new File("account.txt");
			try{    
				   if(!f.exists())    
				    f.createNewFile();      
			  }
			  catch(Exception e){    
				   e.printStackTrace();    
				  } 
			BufferedReader br = new BufferedReader(new FileReader(f));
			String line = null;
			try {
				while((line = br.readLine())!= null){
					String[] split = line.split(";") ;
					Account acc = new Account();
					acc.setAccNo(split[0]);
					int pin = Integer.parseInt(split[1]);
					acc.setPin(pin);
					double balance = Double.parseDouble(split[2]);
					acc.setBalance(balance);
					double over = Double.parseDouble(split[3]);
					acc.setOver(over);
					boolean active = Boolean.parseBoolean(split[4]);
					acc.setActive(active);
					boolean suspend = Boolean.parseBoolean(split[5]);
					acc.setSuspend(suspend);
					boolean notice = Boolean.parseBoolean(split[6]); 
					acc.setNotice(notice);
					double wait = Double.parseDouble(split[7]);
					acc.setWait(wait);
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");   
					Date date = null; //≥ı ºªØdate 
					try {
						date = sdf.parse(split[10]);
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					Customer customer = new Customer(split[8],split[9],date);
					acc.setCustomer(customer);;
					bank.add(acc);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	/**
	 * This method is used to add customer to the bank and check whether he is in the bank
	 * @param name		the name user entered
	 * @param addr		the address user entered
	 * @param birth		the birth user entered
	 * @return Customer	if he is a new customer, a customer constructor is returned; 
	 * if he is a old customer, his customer constructor is returned
	 */
	public Customer addCustomer(String name, String addr, Date birth){
		for(Account acc: bank){
			if(acc.getCustomer().getName().equals(name)&& acc.getCustomer().getaddr().equals(addr)&& acc.getCustomer().getDateOfB().equals(birth)){
				System.out.println("You are already in the system!");
				return acc.getCustomer();
			}
		}
		Customer newc = new Customer(name, addr, birth);
		System.out.println("Congratulations! You are in the system now!");
		return newc;
	}
	
	/**
	 * This method is used to confirm the credit status of a customer
	 * @param c The customer need to confirm the credit status
	 */
	public void confirmCreditStatus(Customer c){
		c.setCreditStatus(true);
	}
	
	/**
	 * This method is used to generate a unique and random account number
	 * @return String	the account number
	 */
	public String generateAccNo(){
		Random r = new Random();
		String accno = String.valueOf(100000+r.nextInt(900000));
		for(Account a: bank){
			while(a.getAccNum().equals(accno))
				accno = String.valueOf(100000+r.nextInt(900000));
		}
		return accno;
	}
	
	/**
	 * This method is used to get the age of a birthday
	 * @param birth		the birthday need to be calculate
	 * @return int		the age in years it is
	 */
	public int getAge(Date birth){ 
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd"); 
		long nowTime=System.currentTimeMillis(); 
		try {
			birth = df.parse(df.format(birth));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	     long time2 = birth.getTime();
	     long between=(nowTime-time2);
	     long between_1 = between/3600000;
	     long between_years = between_1/8760;
	     return Integer.parseInt(String.valueOf(between_years)); 
	}
	
	/**
	 * This method is used to save the ArrayList to the file
	 */
	public void saveToFile(){
		
		try {
			File f = new File("account.txt");
			BufferedWriter os = new BufferedWriter(new FileWriter(f));
			for(int i=0; i<bank.size(); i++){
				os.write(bank.get(i).getAccNum()+";");
				os.write(bank.get(i).getPin()+";");
				Double dou_obj = new Double(bank.get(i).getBalance());
		        NumberFormat nf = NumberFormat.getInstance();
		        nf.setGroupingUsed(false);
		        String balance = nf.format(dou_obj);
				os.write(balance+";");
				dou_obj = new Double(bank.get(i).getOverdraftLimit());
				String overdraft = nf.format(dou_obj);
				os.write(overdraft+";");
				String active = String.valueOf(bank.get(i).isActive());
				os.write(active+";");
				String suspended = String.valueOf(bank.get(i).isSuspended());
				os.write(suspended+";");
				String notice = String.valueOf(bank.get(i).noticeNeeded());
				os.write(notice+";");
				Double wait = new Double(bank.get(i).getWait());
		        NumberFormat nf1 = NumberFormat.getInstance();
		        nf1.setGroupingUsed(false);
		        String waitMoney = nf.format(wait);
				os.write(waitMoney+";");
				os.write(bank.get(i).getCustomer().getName()+";");
				os.write(bank.get(i).getCustomer().getaddr()+";");
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				String date = sdf.format(bank.get(i).getCustomer().getDateOfB());
				os.write(date);
				os.newLine();
			}
			os.close();
		} catch (IOException e) {
			System.out.println("Failed save to file file.");
			e.printStackTrace();
		}
		
	}
	
	/**
	 * This method is used to deposit money from an account. 
	 * @param accNo		the account number
	 * @param money		the money that want to be deposited
	 * @param isCash	whether the money need to be cleared
	 */
	public void deposit(String accNo, double money, boolean isCash){
		int n = 0;
		for(int i=0; i<bank.size(); i++){
			if(bank.get(i).getAccNum().equals(accNo)){
				if(!bank.get(i).isSuspended)
					System.out.println("The account is suspended! You can't do anything.");
				else{
					bank.get(i).addDeposit(money, isCash);
					System.out.println(bank.get(i));
				}
				n++;
			}
		}	
		if(n == 0)
			System.out.println("The account number doesn't exist!\n");
	}
	
	/**
	 * This method is used to clear funds
	 */
	public void clearFunds(){
		for(int i=0; i<bank.size(); i++){
			bank.get(i).clearFunds();
		}
	}
	
	/**
	 * This method is used to withdraw money from the account
	 * @param accNo		the account number
	 * @param pin		the pin number
	 * @param money		the money want to withdraw
	 */
	public void withdraw(String accNo, int pin, double money){
		int n = 0;
		for(int i=0; i<bank.size(); i++){
			if(bank.get(i).getAccNum().equals(accNo)){
				n++;
				if(!bank.get(i).isSuspended)
					System.out.println("The account is suspended! You can't do anything.");
				else{
					if(bank.get(i).getPin()== pin){
						if(bank.get(i).getBalance()+bank.get(i).getOverdraftLimit() >= money){
							if(!bank.get(i).noticeNeeded){
								bank.get(i).addWithdraw(money);
								System.out.println(bank.get(i));
							}
							else{
								giveNotice();
							}
						}
						else
							System.out.println("Withdraw " + money
									+ " unsuccessfull. Do not have enough available funds.");
					}
					else{
						System.out.println("The PIN number is incorrect! Please try again.\n");
					}
				}
					
			}
		}
		if (n == 0)
			System.out.println("The account number doesn't exist!\n");
	}
	
	/**
	 * This method is used to give a notice for saver account.
	 */
	public void giveNotice(){
		Calendar calendar2 = Calendar.getInstance();
		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyyMMdd");
		calendar2.add(Calendar.DATE, 7);
		String seven_days_after = sdf2.format(calendar2.getTime());
		System.out.println("The withdrawal will be available at " + seven_days_after);
	}
	
	/**
	 * This method is used to open an account and print the detail for user.
	 * @param b		the account need to open
	 */
	public void openAccount(Account b) {
		bank.add(b);
		System.out.println("Your account is open now!\n");
		System.out.println("Account number: " + b.getAccNum() +"\n");
		System.out.println("PIN: " + b.getPin() + "\n");
		System.out.println("Please remember your account number and PIN carefully! \n");
	}
	
	/**
	 * This method is used to close a customer's account that can be closed.
	 * @param name		name of the customer
	 * @param addr		address of the customer
	 * @param birth		birthday of a customer
	 */
	public void closeAccount(String name, String addr, Date birth){
		for(Account acc: bank){
			System.out.println("You have the following account:\n");
			if(acc.getCustomer().getName().equals(name)&& acc.getCustomer().getaddr().equals(addr)&& acc.getCustomer().getDateOfB().equals(birth)){
				System.out.println(acc);
			}
		}
		System.out.println("Please input the account number that you want to close:\n");		
		String accno = sc.next();
		int c = bank.size();
		for(int i =0; i<c; i++){
			if(bank.get(i).getAccNum().equals(accno)){
				if(bank.get(i).getBalance()==0){
					bank.remove(bank.get(i));
					i--;
					c--;
				}
				else if(bank.get(i).getBalance()>0){
					System.out.println("The balance haven't been cleared. Account can't close now.\n");
				}
				else
					System.out.println("This account is in overdraft, can't close now.\n");
			}
		}
	}
	
	/**
	 * This method is used to suspend an account
	 * @param accNo		the account number of the account
	 */
	public void suspended(String accNo){
		for(Account acc: bank){
			if(acc.getAccNum().equals(accNo))
				acc.setSuspend(true);
		}
	}
	
	/**
	 * This method is used to re-instated a suspended account
	 * @param accNo		the account that we want to re-instated
	 */
	public void reinstate(String accNo){
		for(Account acc: bank){
			if(acc.getAccNum().equals(accNo))
				acc.setSuspend(false);
		}
	}
}
