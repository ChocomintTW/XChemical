package net.chocomint.xchemical.screen.button;

import net.chocomint.xchemical.screen.book.ChemistsManualScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.LiteralText;

public class NextPageButton extends ButtonWidget {

	private final ChemistsManualScreen gui;
	private final boolean forward;

	public NextPageButton(ChemistsManualScreen gui, int x, int y, boolean forward) {
		super(x, y, 16, 16, LiteralText.EMPTY, button -> {});
		this.gui = gui;
		this.forward = forward;
	}

	@Override
	public void onClick(double mouseX, double mouseY) {
		if (this.forward) this.gui.nextPage();
		else this.gui.previousPage();
	}
}
