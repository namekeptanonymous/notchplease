package org.nka.notchplease.mixin.client.gui.components;

import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.components.DebugScreenOverlay;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import static org.nka.notchplease.Notchplease.getScaledNotchHeight;

@Mixin(value = DebugScreenOverlay.class)
public class DebugScreenOverlayMixin {
    @Redirect(
            method = "extractLines",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/GuiGraphicsExtractor;text(Lnet/minecraft/client/gui/Font;Ljava/lang/String;IIIZ)V"
            )
    )
    private void adjustText(GuiGraphicsExtractor instance, Font font, String str, int x, int y, int color, boolean dropShadow) {
        int notchHeight = getScaledNotchHeight();
        if (notchHeight == -1) {
            instance.text(font, str, x, y, color, dropShadow);
            return;
        }
        instance.text(font, str, x, y + notchHeight, color, dropShadow);
    }

    @Redirect(
            method = "extractLines",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/GuiGraphicsExtractor;fill(IIIII)V"
            )
    )
    private void adjustTextBackground(GuiGraphicsExtractor instance, int x0, int y0, int x1, int y1, int col) {
        int notchHeight = getScaledNotchHeight();
        if (notchHeight == -1) {
            instance.fill(x0, y0, x1, y1, col);
            return;
        }
        instance.fill(x0, y0 + notchHeight, x1, y1 + notchHeight, col);
    }
}