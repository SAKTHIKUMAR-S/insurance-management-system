package mainmod;

import java.util.Scanner;
import dao.PolicyServiceImpl;
import entity.Policy;

public class MainModule {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        PolicyServiceImpl policyService = new PolicyServiceImpl();
        int choice;

        do {
            System.out.println("1. Create Policy");
            System.out.println("2. View Policy");
            System.out.println("3. Exit");
            System.out.print("Enter choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    // Create Policy logic
                    break;
                case 2:
                    // View Policy logic
                    break;
                case 3:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice!");
            }
        } while (choice != 3);

        scanner.close();
    }
}
