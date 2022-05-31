package net.chocomint.xchemical.item.custom;

import net.chocomint.xchemical.recipe.CheMinRecipe;
import net.minecraft.block.Block;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.text.LiteralText;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Formatting;
import net.minecraft.util.Pair;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.world.World;

import java.util.Optional;

public class HHXRFItem extends Item {
	public HHXRFItem(Settings settings) {
		super(settings.maxCount(1));
	}

	@Override
	public ActionResult useOnBlock(ItemUsageContext context) {
		if (!context.getWorld().isClient())
		{
			World world = context.getWorld();
			Block b = world.getBlockState(context.getBlockPos()).getBlock();

			if (context.getPlayer() != null) {

				SimpleInventory inventory = new SimpleInventory(1);
				inventory.setStack(0, new ItemStack(b.asItem()));
				Optional<CheMinRecipe> match = world.getRecipeManager().getFirstMatch(CheMinRecipe.Type.INSTANCE, inventory, world);

				if (match.isPresent()) {
					DefaultedList<Pair<ElementItem, Double>> list = match.get().getElements();
					context.getPlayer().sendMessage(new TranslatableText("message.xchemical.hhxrf.contains", b.getName()), false);
					for (Pair<ElementItem, Double> p : list) {
						context.getPlayer().sendMessage(new LiteralText(p.getLeft().getSymbol()).append(" " + p.getRight() * 100 + "%")
								.formatted(p.getLeft().getElementGroup().getFormat()), false);
					}
				} else
					context.getPlayer().sendMessage(new TranslatableText("message.xchemical.hhxrf.not_exists", b.getName()).formatted(Formatting.RED), false);
			}
		}
		return ActionResult.SUCCESS;
	}
}
