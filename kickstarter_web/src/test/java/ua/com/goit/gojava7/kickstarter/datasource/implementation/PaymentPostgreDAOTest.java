package ua.com.goit.gojava7.kickstarter.datasource.implementation;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import ua.com.goit.gojava7.kickstarter.datasource.IntegrationTest;
import ua.com.goit.gojava7.kickstarter.datasource.implementation.PaymentPostgreDAO;
import ua.com.goit.gojava7.kickstarter.datasource.implementation.ProjectPostgreDAO;
import ua.com.goit.gojava7.kickstarter.domain.Payment;
import ua.com.goit.gojava7.kickstarter.domain.Project;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:applicationContext*.xml")
public class PaymentPostgreDAOTest  implements IntegrationTest{

    List<Payment> list;
    
    @Autowired
    ProjectPostgreDAO projectPostgreDAO; 
    
    @Autowired
    PaymentPostgreDAO paymentPostgreDAO;

	private List<Project> projects;

    @Before
    public void setUp() throws Exception {
    	projects = projectPostgreDAO.getAll();
    	
    	paymentPostgreDAO.clear();
        list = new ArrayList<>();
        list.add(new Payment(projects.get(0), "u1", 21312312, 10, null));
        list.add(new Payment(projects.get(0), "u2", 21312312, 20, null));
        list.add(new Payment(projects.get(1), "u2", 21312312, 20, null));
    }


    @Test
    public void testAddGetAll() {
    	paymentPostgreDAO.addAll(list);
        assertThat(paymentPostgreDAO.getAll(), is(list));
    }
    
    @Test
    public void testAddGet() {
        list.forEach(paymentPostgreDAO::add);
        Payment payment = list.get(1);
        assertThat(paymentPostgreDAO.get(payment.getId()), is(payment));
    }
    
    @Test
    public void testGetByProject() {
    	paymentPostgreDAO.addAll(list);
    	Long id = projects.get(0).getId();
        paymentPostgreDAO.getByProject(id).forEach(p -> assertThat(p.getProject().getId(), is(id)));
    }

}
