package org.nka.notchplease.mixin.client.gui.screens;

import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.components.LogoRenderer;
import net.minecraft.client.gui.screens.TitleScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import static org.nka.notchplease.Notchplease.getScaledNotchHeight;

@Mixin(TitleScreen.class)
public class TitleScreenMixin {
    @Redirect(
            method = "extractRenderState",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/components/LogoRenderer;extractRenderState(Lnet/minecraft/client/gui/GuiGraphicsExtractor;IF)V"
            )
    )
    private void redirectLogoDraw(LogoRenderer instance, GuiGraphicsExtractor graphics, int width, float alpha) {
        int notchHeight = getScaledNotchHeight();
        if (notchHeight == -1) {
            instance.extractRenderState(graphics, width, alpha);
            return;
        }
        // 30, as Minecraft uses this value when y isn't given to the method.
        // Which is how the logo is drawn for some reason on the title screen.
        // public void extractRenderState(final GuiGraphicsExtractor graphics, final int width, final float alpha) {
        //     this.extractRenderState(graphics, width, alpha, 30);
        // }
        instance.extractRenderState(graphics, width, alpha, 30 + notchHeight);
    }
}
