package tests.objects;

import static org.junit.Assert.*;

import java.util.Date;

import objects.Contract;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;

public class ContractTest 
{
	@Rule
	public TestName testName = new TestName();
	
	@Before
	public void before()
	{
		System.out.println("Running test: " + this.getClass().toString() + "::" + testName.getMethodName());
	}
	
	@After
	public void after()
	{
		System.out.println("Finished test.\n");
	}
	
    /** Testing Contract Object Creation **/
    
    @Test
    public void testContract1() 
    {
        Contract contract = new Contract("Nickelback", "A band", 1000000.01, new Date(1402194845), new Date(1402194845),new Date(1402194845));
        
        assertNotNull("Contract is null", contract);
        assertTrue("Business name is incorrect", contract.getBusinessName().equals("Nickelback"));
        assertTrue("Details are incorrect", contract.getDetails().equals("A band"));
        assertTrue("Value is incorrect", contract.getValue() == 1000000.01);
        assertTrue("Date is incorrect", contract.getPeriod().getTime() == 1402194845);
    }
    
    @Test(expected=IllegalArgumentException.class)
    public void testContract2() 
    {
        new Contract(null, "A band", 1000000.01, new Date(1402194845), new Date(1402194845),new Date(1402194845));
    }
    
    @Test(expected=IllegalArgumentException.class)
    public void testContract3() 
    {
        new Contract("", "A band", 1000000.01, new Date(1402194845), new Date(1402194845),new Date(1402194845));
    }
    
    @Test(expected=IllegalArgumentException.class)
    public void testContract4() 
    {
        new Contract("Nickelback", null, 1000000.01, new Date(1402194845), new Date(1402194845),new Date(1402194845));
    }
    
    @Test(expected=IllegalArgumentException.class)
    public void testContract5() 
    {
        new Contract("Nickelback", "", 1000000.01, new Date(1402194845), new Date(1402194845),new Date(1402194845));
    }
    
    @Test(expected=IllegalArgumentException.class)
    public void testContract6() 
    {
        new Contract("Nickelback", "A band", -1, new Date(1402194845), new Date(1402194845),new Date(1402194845));
    }
    
    @Test(expected=IllegalArgumentException.class)
    public void testContract7() 
    {
        new Contract("Nickelback", "A band", 1000000.01, null, null, null);
    }
    
    @Test(expected=IllegalArgumentException.class)
    public void testContract8() 
    {
        new Contract(null, null, -1, null, null, null);
    }
    
    @Test(expected=IllegalArgumentException.class)
    public void testContract9() 
    {
        new Contract("", null, -1, null, null, null);
    }
    
    @Test(expected=IllegalArgumentException.class)
    public void testContract10() 
    {
        new Contract(null, null, 0.0, new Date(1402194845),  null, null);
    }   

    @Test
    public void testContract11() 
    {
        Contract contract = new Contract("N", "A", 0.01, new Date(1402194845),  new Date(1402194845),  new Date(1402194845));
        
        assertNotNull("Contract is null", contract);
        assertTrue("Business name is incorrect", contract.getBusinessName().equals("N"));
        assertTrue("Details are incorrect", contract.getDetails().equals("A"));
        assertTrue("Value is incorrect", contract.getValue() == 0.01);
        assertTrue("Date is incorrect", contract.getPeriod().getTime() == 1402194845);
    }
    
    @Test
    public void testContract12() 
    {
        Contract contract = new Contract("Nickelback", "A band", 1000000.01, new Date(1402194820), new Date(1402194845),  new Date(1402194845));
        
        assertNotNull("Contract is null", contract);
        assertTrue("Business name is incorrect", contract.getBusinessName().equals("Nickelback"));
        assertTrue("Details are incorrect", contract.getDetails().equals("A band"));
        assertTrue("Value is incorrect", contract.getValue() == 1000000.01);
        assertTrue("Date is incorrect", contract.getPeriod().getTime() != 1402194845);
    }
    
    @Test(expected=IllegalArgumentException.class)
    public void testContract13()
    {
    	Contract contract = new Contract(500, "TESTBUSINESS", "NODETAILS", 4.1, new Date(),"Head","Foot", new Date(), new Date(),"dsadasd");
    }
    
    
    /** Testing Contract Mutators **/
    
    @Test
    public void testMutator1() 
    {
        Contract contract = new Contract("Nickelback", "A band", 1000000.01, new Date(1402194845), new Date(1402194845),  new Date(1402194845));
        assertNotNull("Contract is null", contract);
        
        contract.setBusinessName(null);
        assertTrue("Business name is null", contract.getBusinessName() != null);
        assertTrue("Business name has changed", contract.getBusinessName().equals("Nickelback"));
    }
    
    @Test
    public void testMutator2() 
    {
        Contract contract = new Contract("Nickelback", "A band", 1000000.01, new Date(1402194845), new Date(1402194845),  new Date(1402194845));
        assertNotNull("Contract is null", contract);
        
        contract.setBusinessName("");
        assertTrue("Business name is empty", !contract.getBusinessName().isEmpty());
        assertTrue("Business name has changed", contract.getBusinessName().equals("Nickelback"));
    }
    
    @Test
    public void testMutator3() 
    {
        Contract contract = new Contract("Nickelback", "A band", 1000000.01, new Date(1402194845), new Date(1402194845),  new Date(1402194845));
        assertNotNull("Contract is null", contract);
        
        contract.setBusinessName("Boston");
        assertTrue("Business name is null", contract.getBusinessName() != null);
        assertTrue("Business name has changed", contract.getBusinessName().equals("Boston"));
    }
    
    @Test
    public void testMutator4() 
    {
        Contract contract = new Contract("Nickelback", "A band", 1000000.01, new Date(1402194845), new Date(1402194845),  new Date(1402194845));
        assertNotNull("Contract is null", contract);
        
        contract.setDetails(null);
        assertTrue("Details are null", contract.getDetails() != null);
        assertTrue("Details have changed", contract.getDetails().equals("A band"));
    }
    
    @Test
    public void testMutator5() 
    {
        Contract contract = new Contract("Nickelback", "A band", 1000000.01, new Date(1402194845), new Date(1402194845),  new Date(1402194845));
        assertNotNull("Contract is null", contract);
        
        contract.setDetails("");
        assertTrue("Details are empty", !contract.getDetails().isEmpty());
        assertTrue("Details have changed", contract.getDetails().equals("A band"));
    }
    
    @Test
    public void testMutator6() 
    {
        Contract contract = new Contract("Nickelback", "A band", 1000000.01, new Date(1402194845), new Date(1402194845),  new Date(1402194845));
        assertNotNull("Contract is null", contract);
        
        contract.setDetails("Another Band");
        assertTrue("Details are null", contract.getDetails() != null);
        assertTrue("Details have changed", contract.getDetails().equals("Another Band"));
    }
    
    @Test
    public void testMutator7() 
    {
        Contract contract = new Contract("Nickelback", "A band", 1000000.01, new Date(1402194845), new Date(1402194845),  new Date(1402194845));
        assertNotNull("Contract is null", contract);
        
        contract.setValue(-1.1);
        assertTrue("Value is invalid", contract.getValue() == 1000000.01);
    }
    
    @Test
    public void testMutator8() 
    {
        Contract contract = new Contract("Nickelback", "A band", 1000000.01, new Date(1402194845), new Date(1402194845),  new Date(1402194845));
        assertNotNull("Contract is null", contract);
        
        contract.setValue(0);
        assertTrue("Value is invalid", contract.getValue() == 0.0);
    }
    
    @Test
    public void testMutator9() 
    {
        Contract contract = new Contract("Nickelback", "A band", 1000000.01, new Date(1402194845), new Date(1402194845),  new Date(1402194845));
        assertNotNull("Contract is null", contract);
        
        contract.setValue(10);
        assertTrue("Value is invalid", contract.getValue() == 10);
    }
    
    @Test
    public void testMutator10() 
    {
        Contract contract = new Contract("Nickelback", "A band", 1000000.01, new Date(1402194845), new Date(1402194845),  new Date(1402194845));
        assertNotNull("Contract is null", contract);
        
        contract.setPeriod(null);
        assertTrue("Period is null", contract.getPeriod() != null);
        assertTrue("Period has changed", contract.getPeriod().getTime() == 1402194845);
    }
    
    @Test
    public void testMutator11() 
    {
        Contract contract = new Contract("Nickelback", "A band", 1000000.01, new Date(1402194845), new Date(1402194845),  new Date(1402194845));
        assertNotNull("Contract is null", contract);
        
        contract.setPeriod(new Date(187893225));
        assertTrue("Period is null", contract.getPeriod() != null);
        assertTrue("Period has changed", contract.getPeriod().getTime() == 187893225);
    }
    
    @Test
    public void testMutator12() 
    {
        Contract contract = new Contract("Nickelback", "A band", 1000000.01, new Date(1402194845), new Date(1402194845),  new Date(1402194845));
        assertNotNull("Contract is null", contract);
        
        contract.setStatus("Signed");
        assertTrue("Contract is signed", contract.getStatus().compareTo("Signed") == 0);
        contract.setStatus("Cancelled");
        assertTrue("Contract is cancelled", contract.getStatus().compareTo("Cancelled") == 0);
        contract.setStatus("Terminated");
        assertTrue("Contract is Terminated", contract.getStatus().compareTo("Terminated") == 0);
        contract.setStatus("adsada");
        assertTrue("Contract is still Terminated", contract.getStatus().compareTo("Terminated") == 0);
    }
}
