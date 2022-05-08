package net.chocomint.xchemical.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import net.chocomint.xchemical.XChemical;
import net.chocomint.xchemical.util.Utilities;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import static net.chocomint.xchemical.block.entity.CompoundMixerBlockEntity.*;

public class CompoundMixerScreen extends HandledScreen<CompoundMixerScreenHandler> {
	public static final Identifier COMPOUND_MIXER_RECEIVER = new Identifier(XChemical.MOD_ID, "compound_mixer_receiver");
	public ButtonWidget MIX;

	private static final Identifier TEXTURE = new Identifier(XChemical.MOD_ID, "textures/gui/compound_mixer_gui.png");

	public CompoundMixerScreen(CompoundMixerScreenHandler handler, PlayerInventory inventory, Text title) {
		super(handler, inventory, title);
	}

	@Override
	protected void init() {
		backgroundHeight = 191;
		super.init();
		// Center the title
		titleX = (backgroundWidth - textRenderer.getWidth(title)) / 2;
		playerInventoryTitleY = backgroundHeight - 94;

		// Create Buttons
		int x = (width - backgroundWidth) / 2;
		int y = (height - backgroundHeight) / 2;

		this.MIX = this.addDrawableChild(new ButtonWidget(x + 135, y + 85, 30, 20,
				new LiteralText(handler.isCrafting() ? "Stop" : "Mix"), button -> {
			PacketByteBuf buf = PacketByteBufs.create();
			buf.writeInt(handler.isCrafting() ? STOP_PROGRESS : START_PROGRESS);
			ClientPlayNetworking.send(COMPOUND_MIXER_RECEIVER, buf);
		}));
	}

	@Override
	protected void drawBackground(MatrixStack matrices, float delta, int mouseX, int mouseY) {
		RenderSystem.setShader(GameRenderer::getPositionTexShader);
		RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
		RenderSystem.setShaderTexture(0, TEXTURE);
		int x = (width - backgroundWidth) / 2;
		int y = (height - backgroundHeight) / 2;
		drawTexture(matrices, x, y, 0, 0, backgroundWidth, backgroundHeight);

		if (handler.isCrafting()) {
			int progress = handler.getProgress() / 2;

			// red & cyan
			if (Utilities.range(progress, 1, 10)) {
				drawTexture(matrices, x + 97, y + 23, 176, 0, progress, 8);
				drawTexture(matrices, x + 69 + (10 - progress), y + 89, 176 + (10 - progress), 16, progress, 8);
			} else if (progress > 10) {
				drawTexture(matrices, x + 97, y + 23, 176, 0, 10, 8);
				drawTexture(matrices, x + 69, y + 89, 176, 16, 10, 8);
			}

			// orange & blue
			if (Utilities.range(progress, 11, 20)) {
				drawTexture(matrices, x + 117, y + 41, 186, 0, 8, progress - 10);
				drawTexture(matrices, x + 51, y + 69 + (20 - progress), 186, 20 + (20 - progress), 8, progress - 10);
			} else if (progress > 20) {
				drawTexture(matrices, x + 117, y + 41, 186, 0, 8, 10);
				drawTexture(matrices, x + 51, y + 69, 186, 20, 8, 10);
			}

			// yellow & purple
			if (Utilities.range(progress, 21, 30)) {
				drawTexture(matrices, x + 117, y + 69, 186, 10, 8, progress - 20);
				drawTexture(matrices, x + 51, y + 41 + (30 - progress), 186, 30 + (30 - progress), 8, progress - 20);
			} else if (progress > 30) {
				drawTexture(matrices, x + 117, y + 69, 186, 10, 8, 10);
				drawTexture(matrices, x + 51, y + 41, 186, 30, 8, 10);
			}

			// green & pink
			if (Utilities.range(progress, 31, 40)) {
				drawTexture(matrices, x + 97 + (40 - progress), y + 89, 176 + (40 - progress), 8, progress - 30, 8);
				drawTexture(matrices, x + 69, y + 23, 176, 24, progress - 30, 8);
			} else if (progress > 40) {
				drawTexture(matrices, x + 97, y + 89, 176, 8, 10, 8);
				drawTexture(matrices, x + 69, y + 23, 176, 24, 10, 8);
			}

			// up and down
			if (Utilities.range(progress, 41, 53)) {
				drawTexture(matrices, x + 85, y + 35, 176, 32, 6, progress - 40);
				drawTexture(matrices, x + 85, y + 72 + (53 - progress), 176, 45 + (53 - progress), 6, progress - 40);
			}
		}
	}

	@Override
	public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
		renderBackground(matrices);
		super.render(matrices, mouseX, mouseY, delta);
		drawMouseoverTooltip(matrices, mouseX, mouseY);
	}
}
