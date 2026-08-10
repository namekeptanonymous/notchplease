package org.nka.notchplease.mixin.client.gui.screens.options;

import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.layouts.HeaderAndFooterLayout;
import net.minecraft.client.gui.screens.options.OptionsScreen;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static org.nka.notchplease.Notchplease.getScaledNotchHeight;

@Mixin(OptionsScreen.class)
public class OptionsScreenMixin {
    @Shadow
    @Final
    private HeaderAndFooterLayout layout;

    @Inject(method = "repositionElements", at = @At("TAIL"))
    private void onRepositionElements(CallbackInfo ci) {
        int notchHeight = getScaledNotchHeight();
        if (notchHeight == -1) return;
        this.layout.visitChildren((element) -> {
            if (!(element instanceof Button)
                    || ((Button) element).getMessage().toString().contains("World Options")
                    || ((Button) element).getMessage().toString().contains("online"))
            {
                element.setY(element.getY() + notchHeight);
            }
        });
    }
}