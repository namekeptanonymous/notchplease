package org.nka.notchplease.mixin.client.gui.screen.option;

import net.minecraft.client.gui.screen.option.LanguageOptionsScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static org.nka.notchplease.Notchplease.getScaledNotchHeight;

@Mixin(LanguageOptionsScreen.class)
public class LanguageOptionsScreenMixin extends GameOptionsScreenMixin {

    @Inject(method = "refreshWidgetPositions", at = @At("TAIL"))
    private void onRefreshWidgetPositions(CallbackInfo ci) {
        int notchHeight = getScaledNotchHeight();
        if (notchHeight == -1) return;
        this.layout.forEachChild((element) -> {
            System.out.println("all: " + element + " " + element.getMessage());
            if (element.toString().contains("$") && element.getMessage().toString().contains("empty")) {
                element.setY(element.getY() + notchHeight);
                element.setHeight(element.getHeight() - notchHeight);
            } else if (element.getMessage().toString().contains("languageAccuracyWarning")) {
                element.setY(element.getY() - notchHeight);
            }
        });
    }
}