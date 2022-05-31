package net.chocomint.xchemical.item.custom;

import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.chocomint.xchemical.util.NbtUtils;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.WrittenBookItem;
import net.minecraft.nbt.NbtHelper;
import net.minecraft.nbt.NbtList;
import net.minecraft.world.World;

public class ChemistsManualItem extends WrittenBookItem {
	public ChemistsManualItem(Settings settings) {
		super(settings);
	}

//	@Override
//	public void onCraft(ItemStack stack, World world, PlayerEntity player) {
//		super.onCraft(stack, world, player);
//
//		stack.getOrCreateNbt().putString("title", "Chemist's Manual");
//		stack.getOrCreateNbt().putString("author", "Chocomint");
//	}

	//	@Override
//	public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
//		MinecraftClient.getInstance().setScreen(new ChemistsManualScreen(user));
//		return TypedActionResult.success(user.getStackInHand(hand));
//	}
}
