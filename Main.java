import java.util.Scanner;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *  Title      : Main.java
 *  Description: This class is the main function for bank system.
 *  @author  Han Zhang
 *  @version 1.0
 */
public class Main {
	public static void main(String[] args){
		Bank myBank = new Bank();
		myBank.readFromFile();
		Scanner sc = new Scanner(System.in); 
		System.out.println("Welcome to Smile Bank!\n");
		while(true){
			System.out.println("Please choose what you want to do:\n");
			System.out.println("1. Open account\n");
			System.out.println("2. Deposit funds\n");
			System.out.println("3. Withdraw funds\n");
			System.out.println("4. Close account\n");
			System.out.println("5. Suspend account(For bank employee)\n");
			System.out.println("6. Re-instate(For bank employee)\n");
			System.out.println("7. Clear funds(Just for test)\n");
			
		
			String initial = sc.next(); 
			while(true){
				try{
					int choice = Integer.parseInt(initial);
					if(choice == 1|| choice == 2|| choice == 3|| choice == 4|| choice == 5|| choice == 6)
						break;
					else{
						System.out.println("Invalid input! Only 1~6 are expected!");
						initial = sc.next();
					}
				}catch(Exception e){
					System.out.println("Invalid input! Only 1~6 are expected!");
					initial = sc.next(); 
				}
			}
		
			switch (Integer.parseInt(initial)){
			case 1:
				System.out.println("Please input your name and address and date of birth.\n");
				System.out.println("Name: \n");
				String name = sc.next();
				
				sc.nextLine();
				System.out.println("Address: \n");
				String addr = sc.nextLine();
				System.out.println("Date of birth(in format of yyyy-mm-dd): \n");
				String birth = sc.next();
				
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");   
				Date date = null; //初始化date  
				while(true){
					try {   
						date = sdf.parse(birth); //Mon Jan 14 00:00:00 CST 2013   
						break;
					} catch (Exception e) {   
						System.out.println("Invalid input! Please input as yyyy-MM-dd");  
						birth = sc.next();
					}   
				}
				Customer cus = myBank.addCustomer(name, addr, date);
				myBank.confirmCreditStatus(cus);
				if(!cus.getCreditStatus()){
					System.out.println("You are in bad credit status! You can't open an account!");
				}
				else{
					System.out.println("Please choose which type of account you want to open: \n");
					System.out.println("1. Current account\n");
					System.out.println("2. Saver account\n");
					System.out.println("3. Junior account\n");
					
					String chosenAcc = sc.next();
					while(true){
						try{
							int chosen = Integer.parseInt(chosenAcc);
							if(chosen == 1|| chosen == 2|| chosen == 3)
								break;
							else{
								System.out.println("Invalid input! Only 1~3 are expected!");
								chosenAcc = sc.next();
							}
						}catch(Exception e){
							System.out.println("Invalid input! Only 1~3 are expected!");
							chosenAcc = sc.next(); 
						}
					}
				
					switch(Integer.parseInt(chosenAcc)){
					case 1:
						String accNoCur = myBank.generateAccNo();
						CurrentAcc cur = new CurrentAcc(accNoCur,cus);
						System.out.println("Type: Current account\n");
						myBank.openAccount(cur);
						myBank.saveToFile();
						break;
					case 2:
						String accNoSav = myBank.generateAccNo();
						SaverAcc sav = new SaverAcc(accNoSav,cus);
						System.out.println("Type: Saver account\n");
						myBank.openAccount(sav);
						myBank.saveToFile();
						break;
					case 3:
						
						if(myBank.getAge(cus.getDateOfB())<16){
							String accNoJu = myBank.generateAccNo();
							JuniorAcc jun = new JuniorAcc(accNoJu, cus);
							System.out.println("Type: Junior account\n");
							myBank.openAccount(jun);
							myBank.saveToFile();
						}
						
						else{
							System.out.println("Only age under 16 can open junior account.");
						}
						break;
					}
				}
				
				break;
				
			case 2:
				myBank.readFromFile();
				System.out.println("Please input the account number:\n");
				String accNo = sc.next();
				while(true){
					if(accNo.equals("")){
						System.out.println("Invalid input! Please input the account number:\n");
						accNo = sc.next();
					}
					else
						break;	
				}
				System.out.println("Please input the amount of money that you want to deposit:\n");
				String money = sc.next();
				while(true){
					try{
						double amount = Double.parseDouble(money);
						break;
					}catch(Exception e){
						System.out.println("Invalid input! Only number is expected!");
						money = sc.next(); 
					}
				}
				
				double amount = Double.parseDouble(money);
				System.out.println("Please input whether it is cash(Y/N):\n");
				String isCash = sc.next();
				while(true){
					if(isCash.equals("Y")||isCash.equals("N"))
						break;
					else{
						System.out.println("Invalid input! Only (Y/N) is expected.");
						isCash = sc.next();
					}
				}
				boolean iscash;
				if(isCash.equals("Y"))
					 iscash = true;
				else
					iscash = false;
				myBank.deposit(accNo, amount, iscash);
				myBank.saveToFile();
				break;
			case 3:
				myBank.readFromFile();
				System.out.println("Please input the account number:\n");
				String accNoWithdraw = sc.next();
				while(true){
					if(accNoWithdraw.equals("")){
						System.out.println("Invalid input! Please input the account number:\n");
						accNoWithdraw = sc.next();
					}
					else
						break;	
				}
				System.out.println("Please input the PIN number:\n");
				String pin = sc.next();
				while(true){
					try{
						int pinnum = Integer.parseInt(pin);
						break;
					}catch(Exception e){
						System.out.println("Invalid input! Only number is expected!");
						pin = sc.next(); 
					}
				}
				int pinnum = Integer.parseInt(pin);
				System.out.println("Please input the amount of money that you want to withdraw:\n");
				String withdraw = sc.next();
				while(true){
					try{
						double withmoney = Double.parseDouble(withdraw);
						break;
					}catch(Exception e){
						System.out.println("Invalid input! Only number is expected!");
						withdraw = sc.next(); 
					}
				}
				double withmoney = Double.parseDouble(withdraw);
				myBank.withdraw(accNoWithdraw, pinnum, withmoney);
				myBank.saveToFile();
				break;
			
			case 4:
				myBank.readFromFile();
				System.out.println("Please input your name and address and date of birth.\n");
				System.out.println("Name: \n");
				String namec = sc.next();
				while(true){
					if(namec.equals("")){
						System.out.println("Invalid input! Please input the name:\n");
						namec = sc.next();
					}
					else
						break;	
				}
				sc.nextLine();
				System.out.println("Address: \n");
				String addrc = sc.nextLine();
				while(true){
					if(addrc.equals("")){
						System.out.println("Invalid input! Please input the address:\n");
						addrc = sc.next();
					}
					else
						break;	
				}
				System.out.println("Date of birth(in format of yyyy-mm-dd): \n");
				String birthc = sc.next();
				
				SimpleDateFormat sdfc = new SimpleDateFormat("yyyy-MM-dd");   
				Date datec = null; //初始化date  
				while(true){
					try {   
						datec = sdfc.parse(birthc); //Mon Jan 14 00:00:00 CST 2013   
						break;
					} catch (Exception e) {   
						System.out.println("Invalid input! Please input as yyyy-MM-dd");  
						birth = sc.next();
					}   
				}
				myBank.closeAccount(namec, addrc, datec);
				myBank.saveToFile();
				break;
			case 5:
				myBank.readFromFile();
				System.out.println("Please input the account number:\n");
				String accNosus = sc.next();
				while(true){
					if(accNosus.equals("")){
						System.out.println("Invalid input! Please input the account number:\n");
						accNosus = sc.next();
					}
					else
						break;	
				}
				myBank.suspended(accNosus);
				myBank.saveToFile();
				break;
			case 6:
				myBank.readFromFile();
				System.out.println("Please input the account number:\n");
				String accNore = sc.next();
				while(true){
					if(accNore.equals("")){
						System.out.println("Invalid input! Please input the account number:\n");
						accNore = sc.next();
					}
					else
						break;	
				}
				myBank.reinstate(accNore);;
				myBank.saveToFile();
				break;
			case 7:
				myBank.readFromFile();
				myBank.clearFunds();
				myBank.saveToFile();
				break;
			}
		}	
	}
}
