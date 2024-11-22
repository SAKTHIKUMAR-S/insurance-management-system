package dao;

import entity.Policy;
import util.DBConnectionUtil;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PolicyServiceImpl implements IPolicyService {
    @Override
    public boolean createPolicy(Policy policy) {
        try (Connection conn = DBConnectionUtil.getConnection()) {
            String query = "INSERT INTO policy (policyName, policyDetails, premiumAmount) VALUES (?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, policy.getPolicyName());
            ps.setString(2, policy.getPolicyDetails());
            ps.setDouble(3, policy.getPremiumAmount());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Policy getPolicy(int policyId) {
        try (Connection conn = DBConnectionUtil.getConnection()) {
            String query = "SELECT * FROM policy WHERE policyId = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, policyId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Policy(
                    rs.getInt("policyId"),
                    rs.getString("policyName"),
                    rs.getString("policyDetails"),
                    rs.getDouble("premiumAmount")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Policy> getAllPolicies() {
        List<Policy> policies = new ArrayList<>();
        try (Connection conn = DBConnectionUtil.getConnection()) {
            String query = "SELECT * FROM policy";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                policies.add(new Policy(
                    rs.getInt("policyId"),
                    rs.getString("policyName"),
                    rs.getString("policyDetails"),
                    rs.getDouble("premiumAmount")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return policies;
    }

    @Override
    public boolean updatePolicy(Policy policy) {
        try (Connection conn = DBConnectionUtil.getConnection()) {
            String query = "UPDATE policy SET policyName = ?, policyDetails = ?, premiumAmount = ? WHERE policyId = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, policy.getPolicyName());
            ps.setString(2, policy.getPolicyDetails());
            ps.setDouble(3, policy.getPremiumAmount());
            ps.setInt(4, policy.getPolicyId());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deletePolicy(int policyId) {
        try (Connection conn = DBConnectionUtil.getConnection()) {
            String query = "DELETE FROM policy WHERE policyId = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, policyId);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
