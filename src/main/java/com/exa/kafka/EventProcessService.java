package com.exa.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableAutoConfiguration
@ComponentScan("com")
@SpringBootApplication
public class EventProcessService {

  public static void main(String[] args) {
	  
	  final Logger log = LoggerFactory.getLogger(EventProcessService.class); 
	  
	  log.info("Inside main method of EventProessService");
	  
    SpringApplication.run(EventProcessService.class, args);
    
   /* Properties threadConfigurations = ConfigReader
			.ReadConfig(Constants.THREAD_CONFIGURATION_FILE);
    
    boolean enable = Boolean
			.parseBoolean(threadConfigurations
					.getProperty(Constants.ENABLE_THREADS));
    
    int noOfThread = Integer
			.parseInt(threadConfigurations
					.getProperty(Constants.THREADS_COUNT));
    
    long interval = Long
			.parseLong(threadConfigurations
					.getProperty(Constants.INTERVAL_IN_SECONDS));

    
    if (enable) {

		ExecutorService service = Executors
				.newFixedThreadPool(noOfThread);
		List<Future<Boolean>> lst = new ArrayList<Future<Boolean>>();

		for (int t = 0; t < noOfThread; t++) {

			//lst.add(service.submit(new EventContainerClass()));
		}
	}*/
  }
}
