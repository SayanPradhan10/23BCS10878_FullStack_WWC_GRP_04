#include <iostream>
using namespace std;

class BankAccount {
private:
    string accountNumber;
    string accountHolder;

protected:
    double balance;

public:
    BankAccount(string accNo, string accHolder, double bal) {
        accountNumber = accNo;
        accountHolder = accHolder;
        balance = bal;
    }

    virtual void deposit(double amount) {
        balance += amount;
        cout << amount << " deposited successfully!" << endl;
    }

    virtual void withdraw(double amount) {
        balance -= amount;
        cout << amount << " withdrawn successfully!" << endl;
    }

    virtual void printDetails() {
        cout << "\n--- Account Details ---" << endl;
        cout << "Account Number : " << accountNumber << endl;
        cout << "Account Holder : " << accountHolder << endl;
        cout << "Balance        : " << balance << endl;
    }

    virtual ~BankAccount() {}
};

class SavingsAccount : public BankAccount {
private:
    double interestRate;

public:
    SavingsAccount(string accNo, string accHolder, double bal, double rate)
        : BankAccount(accNo, accHolder, bal) {
        interestRate = rate;
    }

    void withdraw(double amount) override {
        if (amount > balance) {
            cout << "Withdrawal failed! Not enough balance." << endl;
        } else {
            balance -= amount;
            cout << amount << " withdrawn successfully from Savings Account!" << endl;
        }
    }

    void applyInterest() {
        double interest = balance * (interestRate / 100);
        balance += interest;
        cout << "Interest of " << interest << " applied!" << endl;
    }

    void printDetails() override {
        cout << "\n--- Savings Account Details ---" << endl;
        BankAccount::printDetails();
        cout << "Interest Rate  : " << interestRate << "%" << endl;
    }
};

int main() {
    BankAccount acc1("A101", "Sayan", 50000);
    acc1.deposit(2000);
    acc1.withdraw(3000);
    acc1.printDetails();

    SavingsAccount sAcc("S202", "Aryan", 30000, 5);
    sAcc.deposit(5000);
    sAcc.withdraw(40000);
    sAcc.withdraw(2000);
    sAcc.applyInterest();
    sAcc.printDetails();

    return 0;
}
