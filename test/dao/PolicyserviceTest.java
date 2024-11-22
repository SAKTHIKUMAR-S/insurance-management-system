package dao;


	import static org.junit.jupiter.api.Assertions.*;
	import static org.mockito.Mockito.*;

	import org.junit.jupiter.api.BeforeEach;
	import org.junit.jupiter.api.Test;
	import dao.PolicyServiceImpl;
	import entity.Policy;
	import exception.PolicyNotFoundException;

	class PolicyServiceTest {

	    private PolicyServiceImpl policyService;
	    private Policy mockPolicy;

	    @BeforeEach
	    void setup() {
	        policyService = new PolicyServiceImpl();
	        mockPolicy = new Policy(1, "Health Insurance", 10000.0);
	    }

	    @Test
	    void testCreatePolicy_ValidPolicy_ReturnsTrue() {
	        boolean isCreated = policyService.createPolicy(mockPolicy);
	        assertTrue(isCreated, "Policy should be created successfully.");
	    }

	    @Test
	    void testGetPolicy_ValidId_ReturnsPolicy() {
	        when(policyService.getPolicy(1)).thenReturn(mockPolicy);

	        Policy policy = policyService.getPolicy(1);
	        assertNotNull(policy, "Policy should not be null.");
	        assertEquals(1, policy.getPolicyId(), "Policy ID should match.");
	    }

	    @Test
	    void testGetPolicy_InvalidId_ThrowsPolicyNotFoundException() {
	        when(policyService.getPolicy(999)).thenThrow(new PolicyNotFoundException("Policy not found"));

	        assertThrows(PolicyNotFoundException.class, () -> policyService.getPolicy(999));
	    }
	}


