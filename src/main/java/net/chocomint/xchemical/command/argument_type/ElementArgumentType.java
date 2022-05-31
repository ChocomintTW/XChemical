package net.chocomint.xchemical.command.argument_type;

import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import net.chocomint.xchemical.item.custom.ElementItem;
import net.chocomint.xchemical.util.ElementsInfo;
import net.minecraft.command.CommandSource;

import java.util.Collection;
import java.util.concurrent.CompletableFuture;

public class ElementArgumentType implements ArgumentType<ElementItem> {

	private ElementArgumentType() {}

	public static ElementArgumentType arg() {
		return new ElementArgumentType();
	}

	@Override
	public ElementItem parse(StringReader reader) {
		return ElementsInfo.SYMBOL_MAP.get(reader.readUnquotedString());
	}

	@Override
	public <S> CompletableFuture<Suggestions> listSuggestions(CommandContext<S> context, SuggestionsBuilder builder) {
		return CommandSource.suggestMatching(ElementsInfo.SYMBOL_MAP.keySet(), builder);
	}

	@Override
	public Collection<String> getExamples() {
		return ArgumentType.super.getExamples();
	}
}
