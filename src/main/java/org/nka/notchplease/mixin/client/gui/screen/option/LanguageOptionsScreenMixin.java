package org.nka.notchplease.mixin.client.gui.screen.option;

import net.minecraft.client.gui.screen.option.LanguageOptionsScreen;
import net.minecraft.client.gui.widget.AlwaysSelectedEntryListWidget;
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
            if (element instanceof AlwaysSelectedEntryListWidget<?> list) {
                list.setY(list.getY() + notchHeight);
                list.setHeight(list.getHeight() - notchHeight);
                list.setScrollY(0);
            } else if (element.getMessage().toString().contains("languageAccuracyWarning")) {
                element.setY(element.getY() - notchHeight);
            }
        });
    }
}