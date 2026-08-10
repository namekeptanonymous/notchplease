package org.nka.notchplease.mixin.client.gui.screen.multiplayer;

import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.screens.DirectJoinServerScreen;
import net.minecraft.network.chat.Component;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static org.nka.notchplease.Notchplease.getScaledNotchHeight;

@Mixin(DirectJoinServerScreen.class)
public class DirectConnectScreenMixin {
    @Shadow
    private EditBox ipEdit;

    @Redirect(
            method = "extractRenderState",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/GuiGraphicsExtractor;centeredText(Lnet/minecraft/client/gui/Font;Lnet/minecraft/network/chat/Component;III)V"
            )
    )
    private void redirectCenteredText(GuiGraphicsExtractor instance, Font font, Component text, int x, int y, int color) {
        int notchHeight = getScaledNotchHeight();
        if (notchHeight == -1) {
            instance.centeredText(font, text, x, y, color);
            return;
        }
        instance.centeredText(font, text, x, y + notchHeight, color);
    }

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

    @Inject(method = "init", at = @At("TAIL"))
    private void afterInit(CallbackInfo ci) {
        int notchHeight = getScaledNotchHeight();
        if (notchHeight == -1) return;

        ipEdit.setY(ipEdit.getY() + notchHeight);
    }

    @ModifyArg(
            method = "init",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/components/Button$Builder;bounds(IIII)Lnet/minecraft/client/gui/components/Button$Builder;"
            ),
            index = 1
    )
    private int adjustJoinCancelButtons(int originalY) {
        int notchHeight = getScaledNotchHeight();
        if (notchHeight == -1) return originalY;
        return originalY + notchHeight;
    }
}
