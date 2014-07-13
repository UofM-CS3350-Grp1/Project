package persistence;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import objects.Client;
import objects.Contract;
import objects.Expense;
import objects.MonthReport;
import objects.Service;
import objects.ServiceType;
import objects.Storable;
import objects.TrackedFeature;
import objects.TrackedFeatureType;

public abstract class AbstractDBInterface 
{
	public abstract void connect();
	public abstract void disconnect();	
	public abstract DBController getController();

	public abstract Service getServiceByID(int id);
	public abstract Client getClientByID(int id);
	public abstract Contract getContractByID(int id);
	public abstract TrackedFeature getTrackedFeatureByID(int id);
	public abstract TrackedFeatureType getTrackedFeatureTypeByID(int id);
	public abstract Expense getExpenseByID(int id);
	public abstract ServiceType getServiceTypeByID(int id);
	public abstract ArrayList<ServiceType> getServiceTypesByType(String type);
	public abstract ArrayList<TrackedFeatureType> getTrackedFeatureTypesByTitle(String title);
	public abstract ArrayList<Contract> getContractsByBusiness(String business);
	public abstract ArrayList<TrackedFeature> getTrackedFeaturesByClient(Client input);
	public abstract Client getClientByFeature(TrackedFeature input);
	public abstract ArrayList<Service> getServiceByContract(Contract input);
	public abstract ArrayList<Service> getServiceByClient(Client input);
	public abstract ArrayList<Service> getServicesByType(ServiceType input);
	public abstract ArrayList<Expense> getExpensesByService(Service service);
	public abstract ArrayList<TrackedFeatureType> getFeatureTypeByClient(Client client);
	public abstract boolean insert(Storable element);
	public abstract boolean update(Storable element);
	public abstract boolean drop(Storable element);
	public abstract ArrayList<MonthReport> getLastYearReturns(Client element);
	public abstract double getClientCurrentRevenue(Client element);
	public abstract double getClientCurrentExpenses(Client element);
	public abstract ArrayList<MonthReport> getLastYearServiceExpenses(ServiceType element);
	public abstract ArrayList<MonthReport> getLastYearServiceRevenue(ServiceType element);
	public abstract ArrayList<MonthReport> getLastYearClientFeaturesByType(Client client, TrackedFeatureType feat);
	public abstract ArrayList<MonthReport> getSumFeatures(Client client, TrackedFeatureType feat);
	public abstract double getTotalAllFeatures(Client client, TrackedFeatureType type);
	public abstract double getAllClientReturns(Client element);
	public abstract ArrayList<Client> dumpClients();
	public abstract ArrayList<Service> dumpServices();
	public abstract ArrayList<Contract> dumpContracts();
	public abstract ArrayList<TrackedFeature> dumpTrackedFeatures();
	public abstract ArrayList<TrackedFeatureType> dumpTrackedFeatureTypes();
	public abstract ArrayList<ServiceType> dumpServiceTypes();
	public abstract ArrayList<Expense> dumpExpenses();
	public abstract boolean idExists(Storable storableTemplate);
	public abstract boolean userLogin(String name, String login);
	public abstract boolean batchMerge(ArrayList<Storable> batch);
	public abstract String toString();
	public abstract void errorMessage(String retrieve, String invalid, String instruction);
}
