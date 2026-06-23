// Chapter 0 — OOP Fundamentals demo.
// Compile & run with:  java Pattern.java
//
// Demonstrates: encapsulation (private fields + getters/setters),
// a no-arg + parameterised constructor, method overloading,
// an @Override of toString(), and a subclass that overrides a method.

public class Pattern {

    public static void main(String[] args) {
        System.out.println("=== Encapsulation: getters / setters ===");
        Account a = new Account();              // no-arg constructor
        a.setId(1122);                          // mutator
        a.setBalance(20000.0);                  // mutator
        System.out.println("Account id  : " + a.getId());     // accessor
        System.out.println("Balance     : " + a.getBalance()); // accessor

        System.out.println("\n=== Parameterised constructor ===");
        Account b = new Account(2233, 500.0);
        System.out.println(b);                  // calls overridden toString()

        System.out.println("\n=== Method overloading (deposit) ===");
        b.deposit(100);                         // deposit(int)
        b.deposit(50.5);                        // deposit(double)
        System.out.println("After deposits: " + b.getBalance());

        System.out.println("\n=== Method overriding (subclass) ===");
        Account base = new Account(1, 1000.0);
        Account savings = new SavingsAccount(2, 1000.0, 6.0); // upcast reference
        System.out.println("Plain account interest : " + base.monthlyInterest());
        System.out.println("Savings account interest: " + savings.monthlyInterest());
        System.out.println(savings);            // SavingsAccount's toString()
    }
}

// Encapsulated class: all fields private, exposed only through methods.
class Account {
    private int id;
    private double balance;

    // No-arg constructor (default values)
    public Account() {
        this.id = 0;
        this.balance = 0.0;
    }

    // Parameterised constructor
    public Account(int id, double balance) {
        this.id = id;
        this.balance = balance;
    }

    // Accessors (getters)
    public int getId()        { return id; }
    public double getBalance(){ return balance; }

    // Mutators (setters)
    public void setId(int id)            { this.id = id; }
    public void setBalance(double bal)   { this.balance = bal; }

    // Method overloading: same name, different parameter types
    public void deposit(int amount)    { this.balance += amount; }
    public void deposit(double amount) { this.balance += amount; }

    // A plain account earns no interest; subclasses may override this.
    public double monthlyInterest() {
        return 0.0;
    }

    @Override
    public String toString() {
        return "Account[id=" + id + ", balance=" + balance + "]";
    }
}

// Subclass demonstrating inheritance + overriding.
class SavingsAccount extends Account {
    private double annualInterestRate; // a percentage, e.g. 6.0 means 6%

    public SavingsAccount(int id, double balance, double annualInterestRate) {
        super(id, balance);                       // call parent constructor
        this.annualInterestRate = annualInterestRate;
    }

    // OVERRIDES Account.monthlyInterest() with the same signature.
    @Override
    public double monthlyInterest() {
        // divide by 100 (percent) and by 12 (monthly)
        return getBalance() * (annualInterestRate / 100) / 12;
    }

    @Override
    public String toString() {
        return "SavingsAccount[id=" + getId()
             + ", balance=" + getBalance()
             + ", annualRate=" + annualInterestRate + "%]";
    }
}
