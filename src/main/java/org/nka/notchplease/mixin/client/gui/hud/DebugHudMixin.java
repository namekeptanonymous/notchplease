package org.nka.notchplease.mixin.client.gui.hud;

import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.hud.DebugHud;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import static org.nka.notchplease.Notchplease.getScaledNotchHeight;

@Mixin(value = DebugHud.class)
public class DebugHudMixin {
    // for 1.21.4-5
    @Redirect(
            method = "drawText",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/DrawContext;drawText(Lnet/minecraft/client/font/TextRenderer;Ljava/lang/String;IIIZ)I"
            )
    )
    private int redirectText_int(DrawContext context, TextRenderer renderer, String text, int x, int y, int color, boolean shadow) {
        int notchHeight = getScaledNotchHeight();
        if (notchHeight == -1) {
            return context.drawText(renderer, text, x, y, color, shadow);
        }
        return context.drawText(renderer, text, x, y + notchHeight, color, shadow);
    }

    // for 1.21.6-7
//    @Redirect(
//            method = "drawText",
//            at = @At(
//                    value = "INVOKE",
//                    target = "Lnet/minecraft/client/gui/DrawContext;drawText(Lnet/minecraft/client/font/TextRenderer;Ljava/lang/String;IIIZ)V"
//            )
//    )
//    private void redirectText_void(DrawContext context, TextRenderer renderer, String text, int x, int y, int color, boolean shadow) {
//        int notchHeight = getScaledNotchHeight();
//        if (notchHeight == -1) {
//            context.drawText(renderer, text, x, y, color, shadow);
//            return;
//        }
//        context.drawText(renderer, text, x, y + notchHeight, color, shadow);
//    }

    @Redirect(
            method = "drawText",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/DrawContext;fill(IIIII)V"
            )
    )
    private void redirectFill(DrawContext context, int x1, int y1, int x2, int y2, int color) {
        int notchHeight = getScaledNotchHeight();
        if (notchHeight == -1) {
            context.fill(x1, y1, x2, y2, color);
            return;
        }
        context.fill(x1, y1 + notchHeight, x2, y2 + notchHeight, color);;
    }
}