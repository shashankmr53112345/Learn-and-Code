package NumberGuessingGame;

import java.util.List;
import java.util.stream.Collectors;

public class CustomerSearchService {

    private final CustomerDatabase customerDatabase;

    // Constructor to inject database dependency
    public CustomerSearchService(CustomerDatabase customerDatabase) {
        this.customerDatabase = customerDatabase;
    }

    // Search customers by country
    public List<CustomerDetails> searchByCountry(String country) {
        return customerDatabase.getCustomers().stream()
                .filter(customer -> customer.getCountry().contains(country))
                .sorted((c1, c2) -> c1.getCustomerID().compareTo(c2.getCustomerID()))
                .collect(Collectors.toList());
    }

    // Search customers by company name
    public List<CustomerDetails> searchByCompanyName(String companyName) {
        return customerDatabase.getCustomers().stream()
                .filter(customer -> customer.getCompanyName().contains(companyName))
                .sorted((c1, c2) -> c1.getCustomerID().compareTo(c2.getCustomerID()))
                .collect(Collectors.toList());
    }

    // Search customers by contact person
    public List<CustomerDetails> searchByContactPerson(String contactPerson) {
        return customerDatabase.getCustomers().stream()
                .filter(customer -> customer.getContactName().contains(contactPerson))
                .sorted((c1, c2) -> c1.getCustomerID().compareTo(c2.getCustomerID()))
                .collect(Collectors.toList());
    }

    // Export customer data to CSV format
    public String exportToCSV(List<CustomerDetails> customers) {
        StringBuilder csvBuilder = new StringBuilder();
        for (CustomerDetails customer : customers) {
            csvBuilder.append(String.format("%s,%s,%s,%s%n",
                    customer.getCustomerID(),
                    customer.getCompanyName(),
                    customer.getContactName(),
                    customer.getCountry()));
        }
        return csvBuilder.toString();
    }
}

