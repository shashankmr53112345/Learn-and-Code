package CustomerSearchService;

import java.util.List;

public class CustomerDatabase {
	private List<CustomerDetails> customers;

	public CustomerDatabase(List<CustomerDetails> customers) {
		this.customers = customers;
	}

	public List<CustomerDetails> getCustomers() {
		return customers;
	}
}
