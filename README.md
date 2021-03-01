# Banking-System
This is an individual java project that developed in 2018
=============================

How it is developed?
---
Agile methods are used, from requirements, through to analysis/design, implementation and testing. Each week one phase in the development lifecycle is carried out.

What functions it has? 
-------------------

1. **Open account**: Three types of accounts are supported by the system: 
	+ Saver account
	+ Junior account
	+ Current account

    Only customer under age 16 may open a Junior account. A customer may open more than one type of account 	       and must credit it with a minimum figure.

 2. **Deposit Funds**: Funds may be deposited in an account provided that the depositor provides the appropriate account number. When funds are deposited, they are either cleared (the funds have been fully credited, e.g Cash), or un-cleared (transfer of funds is pending, e.g. using Cheque). Cleared funds are immediately credited to the
account.

3. **Clear Funds**: An external bank clearing system periodically clears un-cleared funds. Once cleared, they are immediately credited to the account.


4. **Withdraw Funds**: Customers may withdraw funds from an account by supplying their account number, an appropriate identification (in this case, their PIN), and the amount to be withdrawn. A customer cannot withdraw more funds than their limit permits. The type of account the funds are being drawn from determines a customer’s limit. In the case of Junior and Saver accounts, no withdrawal should result in a negative balance. In the case of a Current account, a customer may withdraw additional funds, up to, but not exceeding, their overdraft limit. For a withdrawal from a Saver account, a minimum period of notice (in days) must be given before any withdrawal can be made.

5. **Suspend Account**: In some situations, accounts may be suspended and no further transactions may occur until the account is re-instated.

6. **Close Account**: A customer can choose to close their account provided that the balance has been cleared.


How to run it? 
-------------------

Put all documents in one folder. Open your cmd and reach the folder, enter "java Main" to run this bank system.

How to use it?
-------------------

You can choose to 
+ Open account
+ Deposit funds
+ Withdraw funds
+ Close account
+ Suspend account(For bank employee)
+ Re-instate(For bank employee)

following the instruction and enter the right information.
