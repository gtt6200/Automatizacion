package gob.jmas;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;
import javax.annotation.PostConstruct;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

@EnableAutoConfiguration(exclude = {ErrorMvcAutoConfiguration.class})
@SpringBootApplication
public class JmasApplication {

	@PostConstruct
	void started()
	{
		givenUsingTimer_whenSchedulingDailyTask_thenCorrect();
	}


	public static void main(String[] args)
	{
		SpringApplication.run(JmasApplication.class, args);
		Logger logger = LoggerFactory.getLogger(JmasApplication.class);
 		logger.info("EL SERVICIO HA INICIADO CORRECTAMENTE");

	}

	@Test
	public void givenUsingTimer_whenSchedulingDailyTask_thenCorrect() {
		TimerTask TareaRepetitiva = new TimerTask() {
			public void run()
			{
				Logger logger = LoggerFactory.getLogger(JmasApplication.class);
				try {
					// METODO A EJECUTAR();


					logger.info("TAREA AUTOMATIZADA REALIZADA [" + new Date()+"]");
				}
				catch (Exception e) {
					logger.error("ERROR AL INTENTAR TAREA AUTOMATIZADA [" + new Date()+"] "+e.getMessage());
				}
			}
		};
		Timer timer = new Timer("TAREA AUTOMATIZADA");

		long delay = 1000L;
		long periodicidad = 1000L * 60L * 60L * 1L;
			timer.scheduleAtFixedRate(TareaRepetitiva, delay, periodicidad);
	}



}
