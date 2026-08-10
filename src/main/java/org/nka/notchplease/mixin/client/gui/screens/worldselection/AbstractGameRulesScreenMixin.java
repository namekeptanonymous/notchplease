package org.nka.notchplease.mixin.client.gui.screens.worldselection;

import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.layouts.HeaderAndFooterLayout;
import net.minecraft.client.gui.screens.worldselection.AbstractGameRulesScreen;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static org.nka.notchplease.Notchplease.getScaledNotchHeight;

@Mixin(AbstractGameRulesScreen.class)
public class AbstractGameRulesScreenMixin {
    // this is the edit game rules screen.
    @Shadow
    @Final
    protected HeaderAndFooterLayout layout;

    @Inject(method = "repositionElements", at = @At("TAIL"))
    private void onRepositionElements(CallbackInfo ci) {
        int notchHeight = getScaledNotchHeight();
        if (notchHeight == -1) return;
        this.layout.visitWidgets((element) -> {
            if (!(element instanceof Button))
            {
                element.setY(element.getY() + notchHeight);
                if (element instanceof AbstractGameRulesScreen.RuleList) {
                    element.setHeight(element.getHeight() - notchHeight);
                    ((AbstractGameRulesScreen.RuleList) element).setScrollAmount(0);
                }
            }
        });
    }
}