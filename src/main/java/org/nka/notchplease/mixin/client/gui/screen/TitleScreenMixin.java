package org.nka.notchplease.mixin.client.gui.screen;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.LogoDrawer;
import net.minecraft.client.gui.screen.SplashTextRenderer;
import net.minecraft.client.gui.screen.TitleScreen;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import static org.nka.notchplease.Notchplease.getScaledNotchHeight;

@Mixin(TitleScreen.class)
public class TitleScreenMixin {
    @Shadow
    @Nullable
    private SplashTextRenderer splashText;
    @Redirect(
            method = "render",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/LogoDrawer;draw(Lnet/minecraft/client/gui/DrawContext;IF)V"
            )
    )
    private void redirectLogoDraw(LogoDrawer drawer, DrawContext context, int width, float alpha) {
        int notchHeight = getScaledNotchHeight();
        if (notchHeight == -1) {
            drawer.draw(context, width, alpha);
            return;
        }
        // 30, as Minecraft uses this value when y isn't given to the method.
        // Which is how the logo is drawn for some reason on the title screen.
        //    public void draw(DrawContext context, int screenWidth, float alpha) {
        //        this.draw(context, screenWidth, alpha, 30);
        //    }
        drawer.draw(context, width, alpha, 30 + notchHeight);
    }
}
