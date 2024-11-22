package mainmod;

import dao.IPolicyService;
import dao.InsuranceServiceImpl;
import entity.Policy;
import entity.Client;
import myexceptions.PolicyNotFoundException;
import java.util.Date;
import java.util.Scanner;
import java.text.SimpleDateFormat;
import java.util.Collection;

public class MainModule {
    private static Scanner scanner = new Scanner(System.in);
    private static IPolicyService policyService = new InsuranceServiceImpl();
    private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public static void main(String[] args) {
        while (true) {
            try {
                displayMenu();
                int choice = getIntInput("Enter your choice: ");
                
                switch (choice) {
                    case 1:
                        createNewPolicy();
                        break;
                    case 2:
                        viewPolicy();
                        break;
                    case 3:
                        viewAllPolicies();
                        break;
                    case 4:
                        updateExistingPolicy();
                        break;
                    case 5:
                        deleteExistingPolicy();
                        break;
                    case 6:
                        System.out.println("Thank you for using the Insurance Management System!");
                        return;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } catch (Exception e) {
                System.err.println("An error occurred: " + e.getMessage());
                e.printStackTrace();
            }
            
            System.out.println("\nPress Enter to continue...");
            scanner.nextLine();
        }
    }

    private static void displayMenu() {
        System.out.println("\n=== Insurance Management System ===");
        System.out.println("1. Create New Policy");
        System.out.println("2. View Policy Details");
        System.out.println("3. View All Policies");
        System.out.println("4. Update Policy");
        System.out.println("5. Delete Policy");
        System.out.println("6. Exit");
        System.out.println("================================");
    }

    private static void createNewPolicy() {
        try {
            System.out.println("\n=== Create New Policy ===");
            
            Policy policy = new Policy();
            
            // Get policy details
            policy.setPolicyNumber(getStringInput("Enter Policy Number: "));
            policy.setCoverageAmount(getDoubleInput("Enter Coverage Amount: "));
            
            System.out.println("Enter Start Date (yyyy-MM-dd): ");
            policy.setStartDate(dateFormat.parse(scanner.nextLine()));
            
            System.out.println("Enter End Date (yyyy-MM-dd): ");
            policy.setEndDate(dateFormat.parse(scanner.nextLine()));
            
            policy.setStatus("ACTIVE");

            // Create client object
            Client client = new Client();
            client.setClientId(getIntInput("Enter Client ID: "));
            client.setClientName(getStringInput("Enter Client Name: "));
            client.setContactInfo(getStringInput("Enter Contact Info: "));
            
            policy.setClient(client);

            // Save the policy
            boolean created = policyService.createPolicy(policy);
            if (created) {
                System.out.println("Policy created successfully!");
            } else {
                System.out.println("Failed to create policy.");
            }

        } catch (Exception e) {
            System.err.println("Error creating policy: " + e.getMessage());
        }
    }

    private static void viewPolicy() {
        try {
            System.out.println("\n=== View Policy Details ===");
            int policyId = getIntInput("Enter Policy ID: ");
            
            try {
                Policy policy = policyService.getPolicy(policyId);
                displayPolicyDetails(policy);
            } catch (PolicyNotFoundException e) {
                System.out.println("Policy not found: " + e.getMessage());
            }
            
        } catch (Exception e) {
            System.err.println("Error viewing policy: " + e.getMessage());
        }
    }

    private static void viewAllPolicies() {
        try {
            System.out.println("\n=== All Policies ===");
            Collection<Policy> policies = policyService.getAllPolicies();
            
            if (policies.isEmpty()) {
                System.out.println("No policies found.");
                return;
            }

            for (Policy policy : policies) {
                displayPolicyDetails(policy);
                System.out.println("------------------------");
            }
            
        } catch (Exception e) {
            System.err.println("Error retrieving policies: " + e.getMessage());
        }
    }

    private static void updateExistingPolicy() {
        try {
            System.out.println("\n=== Update Policy ===");
            int policyId = getIntInput("Enter Policy ID to update: ");
            
            try {
                Policy policy = policyService.getPolicy(policyId);
                System.out.println("\nCurrent Policy Details:");
                displayPolicyDetails(policy);
                
                // Get updated details
                System.out.println("\nEnter new details (press Enter to keep current value):");
                
                String input = getStringInput("Enter new Policy Number [" + policy.getPolicyNumber() + "]: ");
                if (!input.isEmpty()) policy.setPolicyNumber(input);
                
                input = getStringInput("Enter new Coverage Amount [" + policy.getCoverageAmount() + "]: ");
                if (!input.isEmpty()) policy.setCoverageAmount(Double.parseDouble(input));
                
                input = getStringInput("Enter new Status [" + policy.getStatus() + "]: ");
                if (!input.isEmpty()) policy.setStatus(input);
                
                boolean updated = policyService.updatePolicy(policy);
                if (updated) {
                    System.out.println("Policy updated successfully!");
                } else {
                    System.out.println("Failed to update policy.");
                }
                
            } catch (PolicyNotFoundException e) {
                System.out.println("Policy not found: " + e.getMessage());
            }
            
        } catch (Exception e) {
            System.err.println("Error updating policy: " + e.getMessage());
        }
    }

    private static void deleteExistingPolicy() {
        try {
            System.out.println("\n=== Delete Policy ===");
            int policyId = getIntInput("Enter Policy ID to delete: ");
            
            try {
                // Show policy details before deletion
                Policy policy = policyService.getPolicy(policyId);
                System.out.println("\nPolicy to be deleted:");
                displayPolicyDetails(policy);
                
                String confirmation = getStringInput("\nAre you sure you want to delete this policy? (yes/no): ");
                if (confirmation.equalsIgnoreCase("yes")) {
                    boolean deleted = policyService.deletePolicy(policyId);
                    if (deleted) {
                        System.out.println("Policy deleted successfully!");
                    } else {
                        System.out.println("Failed to delete policy.");
                    }
                } else {
                    System.out.println("Deletion cancelled.");
                }
                
            } catch (PolicyNotFoundException e) {
                System.out.println("Policy not found: " + e.getMessage());
            }
            
        } catch (Exception e) {
            System.err.println("Error deleting policy: " + e.getMessage());
        }
    }

    private static void displayPolicyDetails(Policy policy) {
        System.out.println("\nPolicy Details:");
        System.out.println("Policy ID: " + policy.getPolicyId());
        System.out.println("Policy Number: " + policy.getPolicyNumber());
        System.out.println("Coverage Amount: $" + policy.getCoverageAmount());
        System.out.println("Start Date: " + dateFormat.format(policy.getStartDate()));
        System.out.println("End Date: " + dateFormat.format(policy.getEndDate()));
        System.out.println("Status: " + policy.getStatus());
        
        Client client = policy.getClient();
        if (client != null) {
            System.out.println("\nClient Details:");
            System.out.println("Client ID: " + client.getClientId());
            System.out.println("Client Name: " + client.getClientName());
            System.out.println("Contact Info: " + client.getContactInfo());
        }
    }

    private static String getStringInput(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }

    private static int getIntInput(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
    }

    private static double getDoubleInput(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return Double.parseDouble(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
    }
}
