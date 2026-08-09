package org.nka.notchplease.mixin.client.gui.hud;

import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.hud.PlayerListHud;
import net.minecraft.text.OrderedText;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

import static org.nka.notchplease.Notchplease.getScaledNotchHeight;

@Mixin(value = PlayerListHud.class)
public class PlayerListHudMixin {
    @Redirect(
            method = "render",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/DrawContext;drawTextWithShadow(Lnet/minecraft/client/font/TextRenderer;Lnet/minecraft/text/Text;III)V"
            )
    )
    private void redirectTextWithShadow_void(DrawContext context, TextRenderer renderer, Text text, int x, int y, int color) {
        int notchHeight = getScaledNotchHeight();
        if (notchHeight == -1) {
            context.drawTextWithShadow(renderer, text, x, y, color);
            return;
        }
        context.drawTextWithShadow(renderer, text, x, y + notchHeight, color);
    }
    @Redirect(
            method = "render",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/DrawContext;drawTextWithShadow(Lnet/minecraft/client/font/TextRenderer;Lnet/minecraft/text/OrderedText;III)V"
            )
    )
    private void redirectTextWithShadow_void(DrawContext context, TextRenderer renderer, OrderedText text, int x, int y, int color) {
        int notchHeight = getScaledNotchHeight();
        if (notchHeight == -1) {
            context.drawTextWithShadow(renderer, text, x, y, color);
            return;
        }
        context.drawTextWithShadow(renderer, text, x, y + notchHeight, color);
    }

    @ModifyArg(
            method = "render",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/PlayerSkinDrawer;draw(Lnet/minecraft/client/gui/DrawContext;Lnet/minecraft/util/Identifier;IIIZZI)V"
            ),
            index = 3
    )
    private int adjustPlayerSkinIcon(int originalY) {
        int notchHeight = getScaledNotchHeight();
        if (notchHeight == -1) return originalY;
        return originalY + notchHeight;
    }

    @ModifyArgs(
        method = "render",
        at = @At(
                value = "INVOKE",
                target = "Lnet/minecraft/client/gui/DrawContext;fill(IIIII)V"
        )

    )
    private void adjustBackgroundFill(Args args) {
        int notchHeight = getScaledNotchHeight();
        if (notchHeight == -1) return;

        int y1 = args.get(1);
        int y2 = args.get(3);

        args.set(1, y1 + notchHeight);
        args.set(3, y2 + notchHeight);
    }

    @ModifyArg(
            method = "renderLatencyIcon",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/DrawContext;drawGuiTexture(Lcom/mojang/blaze3d/pipeline/RenderPipeline;Lnet/minecraft/util/Identifier;IIII)V"
            ),
            index = 3
    )
    private int adjustRenderLatencyIconY(int originalY) {
        int notchHeight = getScaledNotchHeight();
        if (notchHeight == -1) return originalY;
        return originalY + notchHeight;
    }
    @ModifyArg(
            method = "renderScoreboardObjective",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/DrawContext;drawTextWithShadow(Lnet/minecraft/client/font/TextRenderer;Lnet/minecraft/text/Text;III)V"
            ),
            index = 3
    )
    private int adjustRenderScoreboardObjective(int originalY) {
        int notchHeight = getScaledNotchHeight();
        if (notchHeight == -1) return originalY;
        return originalY + notchHeight;
    }
    @ModifyArg(
            method = "renderHearts",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/DrawContext;drawTextWithShadow(Lnet/minecraft/client/font/TextRenderer;Lnet/minecraft/text/Text;III)V"
            ),
            index = 3
    )
    private int adjustRenderHeartsText(int originalY) {
        int notchHeight = getScaledNotchHeight();
        if (notchHeight == -1) return originalY;
        return originalY + notchHeight;
    }
    @ModifyArg(
            method = "renderHearts",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/DrawContext;drawGuiTexture(Lcom/mojang/blaze3d/pipeline/RenderPipeline;Lnet/minecraft/util/Identifier;IIII)V"
            ),
            index = 3
    )
    private int adjustRenderHeartsTexture(int originalY) {
        int notchHeight = getScaledNotchHeight();
        if (notchHeight == -1) return originalY;
        return originalY + notchHeight;
    }
}
