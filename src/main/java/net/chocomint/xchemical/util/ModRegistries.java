package net.chocomint.xchemical.util;

import net.chocomint.xchemical.command.CompoundCommand;
import net.chocomint.xchemical.command.IsotopeCommand;
import net.fabricmc.fabric.api.command.v1.CommandRegistrationCallback;

public class ModRegistries {

	public static void registerAllRegistries() {
		registerCommands();
	}

	public static void registerCommands() {
		CommandRegistrationCallback.EVENT.register(CompoundCommand::register);
		CommandRegistrationCallback.EVENT.register(IsotopeCommand::register);
	}
}
