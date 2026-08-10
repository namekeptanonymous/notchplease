package org.nka.notchplease.mixin.client.gui.screen.world;

import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.screens.worldselection.EditWorldScreen;
import net.minecraft.network.chat.Component;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.*;

import static org.nka.notchplease.Notchplease.getScaledNotchHeight;

@Mixin(EditWorldScreen.class)
public class EditWorldScreenMixin {
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
}
