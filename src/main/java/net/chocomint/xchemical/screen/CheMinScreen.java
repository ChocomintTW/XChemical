package net.chocomint.xchemical.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import net.chocomint.xchemical.XChemical;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class CheMinScreen extends HandledScreen<CheMinScreenHandler> {
	private static final Identifier TEXTURE = new Identifier(XChemical.MOD_ID, "textures/gui/chemin_gui.png");

	public CheMinScreen(CheMinScreenHandler handler, PlayerInventory inventory, Text title) {
		super(handler, inventory, title);
	}

	@Override
	protected void init() {
		super.init();
		// Center the title
		titleX = (backgroundWidth - textRenderer.getWidth(title)) / 2;
	}

	@Override
	protected void drawBackground(MatrixStack matrices, float delta, int mouseX, int mouseY) {
		RenderSystem.setShader(GameRenderer::getPositionTexShader);
		RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
		RenderSystem.setShaderTexture(0, TEXTURE);
		int x = (width - backgroundWidth) / 2;
		int y = (height - backgroundHeight) / 2;
		drawTexture(matrices, x, y, 0, 0, backgroundWidth, backgroundHeight);

		drawTexture(matrices, x + 52, y + 41, 176, 0, handler.getLength(), 12);
		int h = handler.getBurnHeight();
		drawTexture(matrices, x + 61, y + 67 - h, 176, 26 - h, 14, h);
	}

	@Override
	public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
		renderBackground(matrices);
		super.render(matrices, mouseX, mouseY, delta);
		drawMouseoverTooltip(matrices, mouseX, mouseY);
	}
}
