package org.nka.notchplease.mixin.client.gui.screens.options;

import net.minecraft.client.gui.components.OptionsList;
import net.minecraft.client.gui.components.StringWidget;
import net.minecraft.client.gui.layouts.HeaderAndFooterLayout;
import net.minecraft.client.gui.screens.options.OptionsSubScreen;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static org.nka.notchplease.Notchplease.getScaledNotchHeight;

@Mixin(OptionsSubScreen.class)
public class OptionsSubScreenMixin {
    @Shadow
    @Final
    public HeaderAndFooterLayout layout;

    @Shadow
    @Nullable
    protected OptionsList list;

    @Inject(method = "repositionElements", at = @At("TAIL"))
    private void onRepositionElements(CallbackInfo ci) {
        int notchHeight = getScaledNotchHeight();
        if (notchHeight == -1) return;
        this.layout.visitChildren((element) -> {
            if (element instanceof StringWidget)
                element.setY(element.getY() + notchHeight);
        });
        if (list != null) {
            list.setY(list.getY() + notchHeight);
            list.setHeight(list.getHeight() - notchHeight);
            list.setScrollAmount(0);
        }
    }
}