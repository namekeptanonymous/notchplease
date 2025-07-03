package org.nka.notchplease.mixin.client.gui.screen.world;

import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.world.EditWorldScreen;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.*;

import static org.nka.notchplease.Notchplease.getScaledNotchHeight;

@Mixin(EditWorldScreen.class)
public class EditWorldScreenMixin {
    @Redirect(
            method = "render",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/DrawContext;drawCenteredTextWithShadow(Lnet/minecraft/client/font/TextRenderer;Lnet/minecraft/text/Text;III)V"
            )
    )
    private void redirectCenteredTextWithShadow(DrawContext context, TextRenderer renderer, Text text, int x, int y, int color) {
        int notchHeight = getScaledNotchHeight();
        if (notchHeight == -1) {
            context.drawCenteredTextWithShadow(renderer, text, x, y, color);
            return;
        }
        context.drawCenteredTextWithShadow(renderer, text, x, y + notchHeight, color);
    }
}
