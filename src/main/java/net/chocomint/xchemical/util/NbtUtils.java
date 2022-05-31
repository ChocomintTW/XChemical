package net.chocomint.xchemical.util;

import net.minecraft.nbt.NbtElement;
import net.minecraft.nbt.NbtList;

import java.util.Arrays;

public class NbtUtils {

	public static NbtList createList(NbtElement... elements) {
		NbtList list = new NbtList();
		list.addAll(Arrays.asList(elements));
		return list;
	}
}
