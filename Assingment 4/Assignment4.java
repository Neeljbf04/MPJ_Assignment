import java.io.*;
import java.util.*;

// Custom Exceptions
class InvalidAmountException extends Exception {
    InvalidAmountException(String msg) { super(msg); }
}

class InvalidCIDException extends Exception {
    InvalidCIDException(String msg) { super(msg); }
}

class InsufficientBalanceException extends Exception {
    InsufficientBalanceException(String msg) { super(msg); }
}

// Customer Class
class Customer {
    int cid;
    String name;
    double amount;

    Customer(int cid, String name, double amount) {
        this.cid = cid;
        this.name = name;
        this.amount = amount;
    }

    public String toString() {
        return cid + "," + name + "," + amount;
    }
}

public class Assignment4 {

    static final String FILE_NAME = "customers.txt";
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        while (true) {
            System.out.println("\n1. Create Account");
            System.out.println("2. Withdraw");
            System.out.println("3. Display All");
            System.out.println("4. Exit");

            int ch = sc.nextInt();

            try {
                switch (ch) {
                    case 1: createAccount(); break;
                    case 2: withdraw(); break;
                    case 3: displayAll(); break;
                    case 4: System.exit(0);
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    // ✅ CREATE ACCOUNT
    static void createAccount() throws Exception {

        System.out.print("Enter CID (1-20): ");
        int cid = sc.nextInt();

        if (cid < 1 || cid > 20)
            throw new InvalidCIDException("CID must be 1–20");

        // Check duplicate CID
        List<Customer> customers = readCustomers();
        for (Customer c : customers) {
            if (c.cid == cid)
                throw new Exception("CID already exists!");
        }

        System.out.print("Enter Name: ");
        String name = sc.next();

        System.out.print("Enter Amount: ");
        double amt = sc.nextDouble();

        if (amt < 1000)
            throw new InvalidAmountException("Minimum balance = 1000");

        if (amt <= 0)
            throw new InvalidAmountException("Amount must be positive");

        Customer c = new Customer(cid, name, amt);

        FileWriter fw = new FileWriter(FILE_NAME, true);
        fw.write(c.toString() + "\n");
        fw.close();

        System.out.println("Account Created Successfully!");
    }

    // ✅ WITHDRAW
    static void withdraw() throws Exception {

        System.out.print("Enter CID: ");
        int cid = sc.nextInt();

        List<Customer> customers = readCustomers();
        boolean found = false;

        for (Customer c : customers) {
            if (c.cid == cid) {
                found = true;

                System.out.print("Enter withdrawal amount: ");
                double w = sc.nextDouble();

                if (w <= 0)
                    throw new InvalidAmountException("Invalid amount");

                if (w > c.amount)
                    throw new InsufficientBalanceException("Not enough balance");

                c.amount -= w;
                System.out.println("Withdrawal successful!");
                break;
            }
        }

        if (!found)
            throw new InvalidCIDException("Customer not found");

        writeCustomers(customers);
    }

    // ✅ DISPLAY ALL
    static void displayAll() throws Exception {
        List<Customer> customers = readCustomers();

        System.out.println("\n--- Customer Records ---");
        for (Customer c : customers) {
            System.out.println("CID: " + c.cid +
                    " Name: " + c.name +
                    " Balance: " + c.amount);
        }
    }

    // ✅ READ FROM FILE
    static List<Customer> readCustomers() throws Exception {

        List<Customer> list = new ArrayList<>();

        File file = new File(FILE_NAME);
        if (!file.exists()) return list;

        BufferedReader br = new BufferedReader(new FileReader(file));
        String line;

        while ((line = br.readLine()) != null) {
            String[] data = line.split(",");
            list.add(new Customer(
                    Integer.parseInt(data[0]),
                    data[1],
                    Double.parseDouble(data[2])
            ));
        }

        br.close();
        return list;
    }

    // ✅ WRITE TO FILE (overwrite)
    static void writeCustomers(List<Customer> list) throws Exception {

        BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_NAME));

        for (Customer c : list) {
            bw.write(c.toString());
            bw.newLine();
        }

        bw.close();
    }
}