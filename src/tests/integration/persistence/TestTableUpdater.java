package tests.integration.persistence;

import static org.junit.Assert.*;

import java.util.ArrayList;

import objects.Contract;
import objects.Expense;
import objects.Service;
import objects.TrackedFeature;

import org.junit.Test;

import persistence.IDQueryBuilder;
import persistence.NewDBInterface;
import persistence.TableUpdater;

public class TestTableUpdater {

	@Test
	public void testValid() 
	{
		TableUpdater tUp = new TableUpdater();
		IDQueryBuilder idB = new IDQueryBuilder();
		NewDBInterface newIF;
		
		idB.connect();
		TrackedFeature tracky = idB.getTrackedFeatureByID(5);
		Service servo = idB.getServiceByID(1);
		Contract tracty = idB.getContractByID(1);
		Expense exy = idB.getExpenseByID(1);
		TrackedFeature newTracking = idB.getTrackedFeatureByID(4);
		idB.disconnect();
		
		tUp.connect();
		
		
		tracky.setNotes("NEW NOTES");
		servo.setDescription("NEW DESCRIPTION");
		tracty.setDetails("NEW DETAILS");
		
		assertTrue("TrackedFeature update", tUp.update(newTracking));
		assertTrue("Trackedfeature drop", tUp.drop(newTracking));
		assertTrue("Update serv", tUp.update(servo));
		assertTrue("Update tracked", tUp.update(tracky));
		assertTrue("Update contract", tUp.update(tracty));
		assertTrue("Insert serv", tUp.insert(servo));
		assertTrue("Insert tracked", tUp.insert(tracky));
		assertTrue("Insert contract", tUp.insert(tracty));
		assertTrue("Insert expense", tUp.insert(exy));
		
		ArrayList<Service> servL = new ArrayList<Service>();
		ArrayList<TrackedFeature> featL = new ArrayList<TrackedFeature>();
		tUp.disconnect();
	}

}
