package sh.pancake.dnt;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/*
 * Created on Sat Sep 12 2020
 *
 * Copyright (c) storycraft. Licensed under the MIT Licence.
 */

import net.fabricmc.api.ModInitializer;

public class Mod implements ModInitializer {

	public static Logger logger;

	@Override
	public void onInitialize() {
		logger = LogManager.getLogger();

		logger.info("DNT loaded!");
    }
    
}