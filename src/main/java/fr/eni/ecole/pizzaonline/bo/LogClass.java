package fr.eni.ecole.pizzaonline.bo;

import java.util.logging.Logger;

import org.slf4j.LoggerFactory;





public class LogClass {

	
	private static final Logger logger = (Logger) LoggerFactory.getLogger(LogClass.class);

    public void doSomething() {
        ((org.slf4j.Logger) logger).debug("Ceci est un message de débogage.");
        logger.info("Ceci est un message d'information.");
        logger.warning("Ceci est un avertissement.");
        ((org.slf4j.Logger) logger).error("Ceci est une erreur.");
    }
}
