package entity;

public class Policy {
    private int policyId;
    private String policyName;
    private String policyDetails;
    private double premiumAmount;

    // Default Constructor
    public Policy() {}

    // Parameterized Constructor
    public Policy(int policyId, String policyName, String policyDetails, double premiumAmount) {
        this.policyId = policyId;
        this.policyName = policyName;
        this.policyDetails = policyDetails;
        this.premiumAmount = premiumAmount;
    }

    // Getters and Setters
    public int getPolicyId() {
        return policyId;
    }

    public void setPolicyId(int policyId) {
        this.policyId = policyId;
    }

    public String getPolicyName() {
        return policyName;
    }

    public void setPolicyName(String policyName) {
        this.policyName = policyName;
    }

    public String getPolicyDetails() {
        return policyDetails;
    }

    public void setPolicyDetails(String policyDetails) {
        this.policyDetails = policyDetails;
    }

    public double getPremiumAmount() {
        return premiumAmount;
    }

    public void setPremiumAmount(double premiumAmount) {
        this.premiumAmount = premiumAmount;
    }

    @Override
    public String toString() {
        return "Policy{" +
                "policyId=" + policyId +
                ", policyName='" + policyName + '\'' +
                ", policyDetails='" + policyDetails + '\'' +
                ", premiumAmount=" + premiumAmount +
                '}';
    }
}
