package org.nka.notchplease.mixin.client.gui.screen.advancement;

import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.layouts.HeaderAndFooterLayout;
import net.minecraft.client.gui.screens.advancements.AdvancementsScreen;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;


import static org.nka.notchplease.Notchplease.getScaledNotchHeight;

@Mixin(AdvancementsScreen.class)
public class AdvancementsScreenMixin {
    @Shadow
    @Final
    private HeaderAndFooterLayout layout;

    @Inject(method = "init", at = @At("TAIL"))
    private void onInit(CallbackInfo ci) {
        int notchHeight = getScaledNotchHeight();
        if (notchHeight == -1) return;
        this.layout.visitChildren((element) -> {
            if (!(element instanceof Button)) {
                element.setY(element.getY() + notchHeight);
            }
        });
    }
}
