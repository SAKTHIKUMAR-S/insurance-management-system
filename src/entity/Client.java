package entity;

public class Client {
    private int clientId;
    private String clientName;
    private String contactInfo;
    private int policyId; // Foreign Key

    // Default Constructor
    public Client() {}

    // Parameterized Constructor
    public Client(int clientId, String clientName, String contactInfo, int policyId) {
        this.clientId = clientId;
        this.clientName = clientName;
        this.contactInfo = contactInfo;
        this.policyId = policyId;
    }

    // Getters and Setters
    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }

    public int getPolicyId() {
        return policyId;
    }

    public void setPolicyId(int policyId) {
        this.policyId = policyId;
    }

    @Override
    public String toString() {
        return "Client{" +
                "clientId=" + clientId +
                ", clientName='" + clientName + '\'' +
                ", contactInfo='" + contactInfo + '\'' +
                ", policyId=" + policyId +
                '}';
    }
}
