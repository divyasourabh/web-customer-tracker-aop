package com.ds.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ds.entity.Customer;

@Repository
public class CustomerDAOImpl implements CustomerDAO {

	//NEED TO INJECT THE SESSION FACTORY
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
//	@Transactional this Move to Service Layer
	public List<Customer> getCustomers() {
		
		Session session = sessionFactory.getCurrentSession();
		
		Query<Customer> query = session.createQuery("from Customer order by lastName", Customer.class);
		
		List<Customer> customers = query.getResultList();
		
		return customers;
	}

	@Override
	public void saveCustomer(Customer customer) {
		Session session = sessionFactory.getCurrentSession();
		
//		session.save(customer);
		session.saveOrUpdate(customer);
		
	}

	@Override
	public Customer getCustomer(int customerId) {
		Session session = sessionFactory.getCurrentSession();
		
		Customer customer = session.get(Customer.class, customerId);
		
		return customer;
	}

	@Override
	public void deleteCustomer(int customerId) {
		Session session = sessionFactory.getCurrentSession();
		
		Query<Customer> query = session.createQuery("delete from Customer where id=:customerId", Customer.class);
		
		query.setParameter("customerId", customerId);
		
		query.executeUpdate();
		
	}

 @Override
    public List<Customer> searchCustomers(String theSearchName) {

        // get the current hibernate session
        Session currentSession = sessionFactory.getCurrentSession();
        
        Query theQuery = null;
        
        //
        // only search by name if theSearchName is not empty
        //
        if (theSearchName != null && theSearchName.trim().length() > 0) {

            // search for firstName or lastName ... case insensitive
            theQuery =currentSession.createQuery("from Customer where lower(firstName) like :theName or lower(lastName) like :theName", Customer.class);
            theQuery.setParameter("theName", "%" + theSearchName.toLowerCase() + "%");

        }
        else {
            // theSearchName is empty ... so just get all customers
            theQuery =currentSession.createQuery("from Customer", Customer.class);            
        }
        
        // execute query and get result list
        List<Customer> customers = theQuery.getResultList();
                
        // return the results        
        return customers;
        
    }

}
