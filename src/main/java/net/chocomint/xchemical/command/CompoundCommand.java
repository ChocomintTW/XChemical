package net.chocomint.xchemical.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.chocomint.xchemical.command.argument_type.ElementArgumentType;
import net.chocomint.xchemical.item.custom.CompoundItem;
import net.chocomint.xchemical.item.custom.ElementItem;
import net.chocomint.xchemical.util.ElementsInfo;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtList;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Formatting;

import java.util.Objects;

public class CompoundCommand {
	public static void register(CommandDispatcher<ServerCommandSource> dispatcher, boolean dedicated) {
		dispatcher.register(CommandManager.literal("compound")
				.then(CommandManager.literal("add")
						.then(CommandManager.argument("element", ElementArgumentType.arg())
						.then(CommandManager.argument("amount", IntegerArgumentType.integer(1, 64))
						.executes(context -> run(context, Type.ADD)))))
				.then(CommandManager.literal("remove")
						.then(CommandManager.argument("element", ElementArgumentType.arg())
						.then(CommandManager.argument("amount", IntegerArgumentType.integer(1, 64))
						.executes(context -> run(context, Type.REMOVE)))))
		);
	}

	public static int run(CommandContext<ServerCommandSource> context, Type type) throws CommandSyntaxException {
		ServerPlayerEntity player = context.getSource().getPlayer();
		ItemStack stack = player.getMainHandStack();
		if (stack.getItem() instanceof CompoundItem) {
			ElementItem element = context.getArgument("element", ElementItem.class);
			int amount = context.getArgument("amount", Integer.class);

			if (!stack.getOrCreateNbt().contains("Elements", 9)) {
				stack.getOrCreateNbt().put("Elements", new NbtList());
			}

			NbtList nbtList = stack.getOrCreateNbt().getList("Elements", 10);
			boolean contain = false;
			int i;
			for (i = 0; i < nbtList.size(); i++) {
				if (Objects.equals(nbtList.getCompound(i).getString("symbol"), element.getSymbol())) {
					contain = true;
					break;
				}
			}
			int nowAmount = nbtList.getCompound(i).getInt("amount");

			if (type == Type.ADD) {
				if (contain)
					nbtList.getCompound(i).putInt("amount", nowAmount + amount);
				else
					nbtList.add(new ElementsInfo.CompoundUnit(element, amount).toNbt());
			} else if (type == Type.REMOVE) {
				if (contain && nowAmount >= amount)
					nbtList.getCompound(i).putInt("amount", nowAmount - amount);

				Text t = new TranslatableText(element.getTranslationKey()).append("(" + element.getSymbol() + ")").formatted(element.getElementGroup().getFormat());
				if (!contain)
					context.getSource().sendFeedback(new TranslatableText("commands.execute.compound_command.not_contain").formatted(Formatting.RED).append(t), true);
				else if (nowAmount < amount)
					context.getSource().sendFeedback(new TranslatableText("commands.execute.compound_command.less1").formatted(Formatting.RED)
							.append(t).append(new TranslatableText("commands.execute.compound_command.less2", amount).formatted(Formatting.RED)), true);
			}
		}
		return 1;
	}

	private enum Type {
		ADD, REMOVE;
	}
}
