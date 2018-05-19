package com.catalog.example.customer;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CustomerServiceIntegrationTest {

    @Autowired
    private CustomerRepository subject;

    @After
    public void tearDown() throws Exception {
        subject.deleteAll();
    }

    @Test
    public void shouldSaveAndFetchCustomer() throws Exception {
        Customer peter = new Customer("Peter Pan", "ppan@fantasy.com");
        subject.save(peter);

        Customer maybePeter = subject.findByEmail(peter.getEmail());

        assertThat(maybePeter, is(peter));
    }
}