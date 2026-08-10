package org.nka.notchplease.mixin.client.gui.screen.world;

import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.layouts.HeaderAndFooterLayout;
import net.minecraft.client.gui.screens.worldselection.ExperimentsScreen;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static org.nka.notchplease.Notchplease.getScaledNotchHeight;

@Mixin(ExperimentsScreen.class)
public class ExperimentsScreenMixin {
    @Mutable
    @Shadow
    @Final
    private final HeaderAndFooterLayout layout;

    public ExperimentsScreenMixin(HeaderAndFooterLayout layout) {
        this.layout = layout;
    }

    @Inject(method = "repositionElements", at = @At("TAIL"))
    private void onRepositionElements(CallbackInfo ci) {
        int notchHeight = getScaledNotchHeight();
        if (notchHeight == -1) return;
        this.layout.visitWidgets((element) -> {
            if (!(element instanceof Button)) {
                element.setY(element.getY() + notchHeight);
            }
        });
    }
}