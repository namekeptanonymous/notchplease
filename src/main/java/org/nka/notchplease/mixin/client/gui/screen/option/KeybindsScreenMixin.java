package org.nka.notchplease.mixin.client.gui.screen.option;

import net.minecraft.client.gui.components.StringWidget;
import net.minecraft.client.gui.screens.options.controls.KeyBindsList;
import net.minecraft.client.gui.screens.options.controls.KeyBindsScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static org.nka.notchplease.Notchplease.getScaledNotchHeight;

@Mixin(KeyBindsScreen.class)
public class KeybindsScreenMixin extends GameOptionsScreenMixin {
    @Shadow
    private KeyBindsList keyBindsList;

    @Inject(method = "repositionElements", at = @At("TAIL"))
    private void onRepositionElements(CallbackInfo ci) {
        int notchHeight = getScaledNotchHeight();
        if (notchHeight == -1) return;
        this.layout.visitChildren((element) -> {
            if (element instanceof StringWidget)
            {
                element.setY(element.getY() + notchHeight);
            }
        });
        this.keyBindsList.setY(this.keyBindsList.getY() + notchHeight);
        this.keyBindsList.setHeight(this.keyBindsList.getHeight() - notchHeight);
        this.keyBindsList.setScrollAmount(0);
    }
}