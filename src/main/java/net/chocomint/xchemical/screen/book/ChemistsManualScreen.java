package net.chocomint.xchemical.screen.book;

import com.mojang.blaze3d.systems.RenderSystem;
import net.chocomint.xchemical.XChemical;
import net.chocomint.xchemical.screen.button.NextPageButton;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.LiteralText;
import net.minecraft.util.Identifier;

public class ChemistsManualScreen extends Screen {
	private static final Identifier TEXTURE = new Identifier(XChemical.MOD_ID, "chemists_manual_gui");
	private static final int MANUAL_WIDTH = 512;
	private static final int MANUAL_HEIGHT = 327;

	private PlayerEntity player;
	private NextPageButton next;
	private NextPageButton previous;
	private int currentPage;
	private int totalPages;

	public ChemistsManualScreen(PlayerEntity player) {
		super(LiteralText.EMPTY);
		this.player = player;
		this.currentPage = 0;
		this.totalPages = 10;
	}

	@Override
	public void init() {
		super.init();
		int x = (width - MANUAL_WIDTH) / 2;
		int y = (height - MANUAL_HEIGHT) / 2;

		this.next = this.addDrawableChild(new NextPageButton(this, 0, 0, true));
		this.next = this.addDrawableChild(new NextPageButton(this, 0, 0, false));
	}

	@Override
	public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
		// background
		RenderSystem.setShader(GameRenderer::getPositionTexShader);
		RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
		RenderSystem.setShaderTexture(0, TEXTURE);
		int x = (width - MANUAL_WIDTH) / 2;
		int y = (height - MANUAL_HEIGHT) / 2;
		drawTexture(matrices, x, y, 0, 0, MANUAL_WIDTH, MANUAL_HEIGHT, 512, 512);

		super.render(matrices, mouseX, mouseY, delta);
	}

	private void drawPage(MatrixStack matrices, int page) {
		// Page number
		drawCenteredText(matrices, textRenderer, new LiteralText("" + page), MANUAL_WIDTH / 2 - 12, 290, 0);
		drawCenteredText(matrices, textRenderer, new LiteralText("" + (page + 1)), MANUAL_WIDTH / 2 + 12, 290, 0);
	}

	public void nextPage() {
		currentPage += 2;
	}

	public void previousPage() {
		currentPage -= 2;
	}
}
