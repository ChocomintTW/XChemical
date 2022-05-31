package net.chocomint.xchemical.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.chocomint.xchemical.item.custom.ElementItem;
import net.minecraft.item.ItemStack;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;

public class IsotopeCommand {
	public static void register(CommandDispatcher<ServerCommandSource> dispatcher, boolean dedicated) {
		dispatcher.register(CommandManager.literal("isotope")
				.then(CommandManager.literal("set")
						.then(CommandManager.argument("atom_mass", IntegerArgumentType.integer(1))
						.executes(context -> run(context, Type.ADD))))
				.then(CommandManager.literal("remove").executes(context -> run(context, Type.REMOVE)))
		);
	}

	public static int run(CommandContext<ServerCommandSource> context, Type type) throws CommandSyntaxException {
		ServerPlayerEntity player = context.getSource().getPlayer();
		ItemStack stack = player.getMainHandStack();
		if (stack.getItem() instanceof ElementItem) {
			if (type == Type.ADD)
				ElementItem.putAtomMass(stack, context.getArgument("atom_mass", Integer.class));
			else
				stack.getOrCreateNbt().remove("atom_mass");
		}
		return 1;
	}

	private enum Type {
		ADD, REMOVE;
	}
}
