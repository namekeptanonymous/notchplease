package org.nka.notchplease.mixin.client.gui.screens.social;


import com.mojang.blaze3d.pipeline.RenderPipeline;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.layouts.HeaderAndFooterLayout;
import net.minecraft.client.gui.screens.social.SocialInteractionsPlayerList;
import net.minecraft.client.gui.screens.social.SocialInteractionsScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import org.jspecify.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static org.nka.notchplease.Notchplease.getScaledNotchHeight;

@Mixin(value = SocialInteractionsScreen.class)
public class SocialInteractionsScreenMixin {
    @Shadow
    private EditBox searchBox;

    @Shadow @Nullable
    private SocialInteractionsPlayerList socialInteractionsPlayerList;

    @Shadow private Button allButton;
    @Shadow private Button hiddenButton;
    @Shadow private Button blockedButton;
    @Shadow private Button blockingHintButton;

    @Final
    @Shadow
    private HeaderAndFooterLayout layout;

    @Redirect(
            method = "extractRenderState",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/GuiGraphicsExtractor;text(Lnet/minecraft/client/gui/Font;Lnet/minecraft/network/chat/Component;III)V"
            )
    )
    private void redirectText(GuiGraphicsExtractor instance, Font font, Component str, int x, int y, int color) {
        int notchHeight = getScaledNotchHeight();
        if (notchHeight == -1) {
            instance.text(font, str, x, y, color);
            return;
        }
        instance.text(font, str, x, y + notchHeight, color);
    }


    @Inject(method = "repositionElements", at = @At("TAIL"))
    private void onRepositionElements(CallbackInfo ci) {
        int notchHeight = getScaledNotchHeight();
        if (notchHeight == -1) return;

        assert this.socialInteractionsPlayerList != null;

        this.layout.visitWidgets((element) -> {
            if (!(element instanceof Button)) {
                element.setY(element.getY() + notchHeight);
            }
        });
        this.socialInteractionsPlayerList.setY(this.socialInteractionsPlayerList.getY() + notchHeight);
        this.socialInteractionsPlayerList.setHeight(this.socialInteractionsPlayerList.getHeight() - notchHeight);
        this.socialInteractionsPlayerList.setScrollAmount(0);

        this.searchBox.setY(this.searchBox.getY() + notchHeight);
        this.allButton.setY(this.allButton.getY() + notchHeight);
        this.hiddenButton.setY(this.hiddenButton.getY() + notchHeight);
        this.blockedButton.setY(this.blockedButton.getY() + notchHeight);
        this.blockingHintButton.setY(this.blockingHintButton.getY() + notchHeight);
    }

    @Redirect(
            method = "extractBackground",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/GuiGraphicsExtractor;blitSprite(Lcom/mojang/blaze3d/pipeline/RenderPipeline;Lnet/minecraft/resources/Identifier;IIII)V"
            )
    )
    private void adjustBackground(GuiGraphicsExtractor instance, RenderPipeline renderPipeline, Identifier location, int x, int y, int width, int height) {
        int notchHeight = getScaledNotchHeight();
        if (notchHeight == -1) {
            instance.blitSprite(renderPipeline, location, x, y, width, height);
            return;
        }
        instance.blitSprite(renderPipeline, location, x, y + notchHeight, width, height);
    }
}
