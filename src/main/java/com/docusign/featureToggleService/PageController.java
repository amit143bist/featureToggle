package com.docusign.featureToggleService;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.ff4j.FF4j;
import org.ff4j.core.FlippingExecutionContext;
import org.ff4j.strategy.ClientFilterStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Handles requests for the application home page.
 */
@Controller
public class PageController {

	private static final Logger logger = LoggerFactory.getLogger(PageController.class);

	@Autowired
	FF4j ff4j;
	
	@Autowired
	FlippingExecutionContext flippingExecutionContext;

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);

		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);

		String formattedDate = dateFormat.format(date);

		model.addAttribute("serverTime", formattedDate);

		return "home";
	}

	@RequestMapping(value = "/clientCanView", method = RequestMethod.GET)
	@ResponseBody
	public String clientCanView(@RequestParam(value = "clientName") String clientName) {

		System.out.println("HomeController.clientCanView()- " + clientName);
		
		try{
			
			System.out.println("HomeController.clientCanView()- " + ff4j.exist("ClientFilterFeature"));
			if (ff4j.exist("ClientFilterFeature")) {

				// When invalid host provided, Then unavailable
				flippingExecutionContext.addValue(ClientFilterStrategy.CLIENT_HOSTNAME, clientName);

				/*// When correct hostname... OK
				fex.addValue(ClientFilterStrategy.CLIENT_HOSTNAME, clientName);*/
			}

			System.out.println("HomeController.clientCanView()1111111111");
			System.out.println("HomeController.clientCanView()22222 " + ff4j.check("ClientFilterFeature", flippingExecutionContext));
			if (ff4j.check("ClientFilterFeature", flippingExecutionContext)) {
				return "true";
			} else {
				return "false";
			}
		}catch(Exception exp){
			exp.printStackTrace();
		}

		return "false";
	}

}
