package org.nka.notchplease.mixin.client.gui.screen.world;

import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.ObjectSelectionList;
import net.minecraft.client.gui.components.events.GuiEventListener;
import net.minecraft.client.gui.screens.PresetFlatWorldScreen;
import net.minecraft.network.chat.Component;
import org.nka.notchplease.mixin.client.gui.screen.ScreenMixin;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

import static org.nka.notchplease.Notchplease.getScaledNotchHeight;

@Mixin(PresetFlatWorldScreen.class)
public class PresetsScreenMixin extends ScreenMixin {
    protected PresetsScreenMixin(List<GuiEventListener> children) {
        super(children);
    }

    @Inject(method = "init", at = @At("TAIL"))
    protected void afterInit(CallbackInfo ci) {
        int notchHeight = getScaledNotchHeight();
        if (notchHeight == -1) return;

        for (GuiEventListener element : this.getChildren()) {
            if (element instanceof AbstractWidget w && !(element instanceof Button)) {
                w.setY(w.getY() + notchHeight);
                if (element instanceof ObjectSelectionList<?> list) {
                    list.setHeight(list.getHeight() - notchHeight);
                    list.setScrollAmount(0);
                }
            }
        }
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