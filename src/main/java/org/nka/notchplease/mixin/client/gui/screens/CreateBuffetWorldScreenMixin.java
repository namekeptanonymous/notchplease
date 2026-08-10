package org.nka.notchplease.mixin.client.gui.screens;

import net.minecraft.client.gui.components.ObjectSelectionList;
import net.minecraft.client.gui.components.StringWidget;
import net.minecraft.client.gui.layouts.HeaderAndFooterLayout;
import net.minecraft.client.gui.screens.CreateBuffetWorldScreen;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static org.nka.notchplease.Notchplease.getScaledNotchHeight;

@Mixin(CreateBuffetWorldScreen.class)
public class CreateBuffetWorldScreenMixin {
    @Shadow
    @Final
    private HeaderAndFooterLayout layout;

    @Inject(method = "repositionElements", at = @At("TAIL"))
    private void onRepositionElements(CallbackInfo ci) {
        int notchHeight = getScaledNotchHeight();
        if (notchHeight == -1) return;
        this.layout.visitChildren((element) -> {
            if (element instanceof StringWidget strWdgt) {
                strWdgt.setY(strWdgt.getY() + notchHeight);
            }
            if (element instanceof ObjectSelectionList<?> list) {
                list.setY(list.getY() + notchHeight);
                list.setHeight(list.getHeight() - notchHeight);
                list.setScrollAmount(0);
            }
        });
    }
}