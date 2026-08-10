package org.nka.notchplease.mixin.client.gui.hud;

import com.mojang.blaze3d.pipeline.RenderPipeline;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.components.PlayerFaceExtractor;
import net.minecraft.client.gui.components.PlayerTabOverlay;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.util.FormattedCharSequence;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.*;

import static org.nka.notchplease.Notchplease.getScaledNotchHeight;

@Mixin(value = PlayerTabOverlay.class)
public class PlayerListHudMixin {
    @Redirect(
            method = "extractRenderState",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/GuiGraphicsExtractor;text(Lnet/minecraft/client/gui/Font;Lnet/minecraft/util/FormattedCharSequence;III)V"
            )
    )
    private void adjustHeaderText(GuiGraphicsExtractor instance, Font font, FormattedCharSequence str, int x, int y, int color) {
        int notchHeight = getScaledNotchHeight();
        if (notchHeight == -1) {
            instance.text(font, str, x, y, color);
            return;
        }
        instance.text(font, str, x, y + notchHeight, color);
    }

    @Redirect(
            method = "extractRenderState",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/GuiGraphicsExtractor;text(Lnet/minecraft/client/gui/Font;Lnet/minecraft/network/chat/Component;III)V"
            )
    )
    private void adjustPlayerTabText(GuiGraphicsExtractor instance, Font font, Component str, int x, int y, int color) {
        int notchHeight = getScaledNotchHeight();
        if (notchHeight == -1) {
            instance.text(font, str, x, y, color);
            return;
        }
        instance.text(font, str, x, y + notchHeight, color);
    }

    @Redirect(
            method = "extractRenderState",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/components/PlayerFaceExtractor;extractRenderState(Lnet/minecraft/client/gui/GuiGraphicsExtractor;Lnet/minecraft/resources/Identifier;IIIZZI)V"
            )
    )
    private void adjustPlayerSkinIcon(GuiGraphicsExtractor graphics, Identifier texture, int x, int y, int size, boolean hat, boolean flip, int color) {
        int notchHeight = getScaledNotchHeight();
        if (notchHeight == -1) {
            PlayerFaceExtractor.extractRenderState(graphics, texture, x, y, size, hat, flip, color);
            return;
        }
        PlayerFaceExtractor.extractRenderState(graphics, texture, x, y + notchHeight, size, hat, flip, color);
    }

    @Redirect(
            method = "extractRenderState",
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

    @Redirect(
            method = "extractPingIcon",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/GuiGraphicsExtractor;blitSprite(Lcom/mojang/blaze3d/pipeline/RenderPipeline;Lnet/minecraft/resources/Identifier;IIII)V"
            )
    )
    private void adjustPingIconY(GuiGraphicsExtractor instance, RenderPipeline renderPipeline, Identifier location, int x, int y, int width, int height) {
        int notchHeight = getScaledNotchHeight();
        if (notchHeight == -1) {
            instance.blitSprite(renderPipeline, location, x, y, width, height);
            return;
        }
        instance.blitSprite(renderPipeline, location, x, y + notchHeight, width, height);
    }

    @Redirect(
            method = "extractTablistScore",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/GuiGraphicsExtractor;text(Lnet/minecraft/client/gui/Font;Lnet/minecraft/network/chat/Component;III)V"
            )
    )
    private void adjustTablistScoreY(GuiGraphicsExtractor instance, Font font, Component str, int x, int y, int color) {
        // this is the scores next to usernames, method name changed as MC code is no longer obfuscated
        int notchHeight = getScaledNotchHeight();
        if (notchHeight == -1) {
            instance.text(font, str, x, y, color);
            return;
        }
        instance.text(font, str, x, y + notchHeight, color);
    }

    @Redirect(
            method = "extractTablistHearts",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/GuiGraphicsExtractor;text(Lnet/minecraft/client/gui/Font;Lnet/minecraft/network/chat/Component;III)V"
            )
    )
    private void adjustRenderHeartsText(GuiGraphicsExtractor instance, Font font, Component str, int x, int y, int color) {
        int notchHeight = getScaledNotchHeight();
        if (notchHeight == -1) {
            instance.text(font, str, x, y, color);
            return;
        }
        instance.text(font, str, x, y + notchHeight, color);
    }

    @Redirect(
            method = "extractTablistHearts",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/GuiGraphicsExtractor;blitSprite(Lcom/mojang/blaze3d/pipeline/RenderPipeline;Lnet/minecraft/resources/Identifier;IIII)V"
            )
    )
    private void adjustRenderHeartsTexture(GuiGraphicsExtractor instance, RenderPipeline renderPipeline, Identifier location, int x, int y, int width, int height) {
        int notchHeight = getScaledNotchHeight();
        if (notchHeight == -1) {
            instance.blitSprite(renderPipeline, location, x, y, width, height);
            return;
        }
        instance.blitSprite(renderPipeline, location, x, y + notchHeight, width, height);
    }
}
