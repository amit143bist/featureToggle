/**
 * 
 */
package com.docusign.featureToggleService;

import static org.junit.Assert.fail;

import org.ff4j.FF4j;
import org.ff4j.exception.FeatureNotFoundException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author Amit.Bist
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:*applicationContext.xml" })
public class CoreSpringTest {

	@Autowired
	private FF4j ff4j;

	@Test
	public void testWithSpring() {

		try {
			if (ff4j.check("hello")) {
				// Feature ok !
				System.out.println("Only Hello Exists !");
			} else {
				fail();
			}

			// Test value at runtime
			if (ff4j.check("sayHello")) {
				// Feature ok !
				System.out.println("Hello World !");
			} else {
				fail();
			}
		} catch (FeatureNotFoundException fnfe) {

		}

	}

}
