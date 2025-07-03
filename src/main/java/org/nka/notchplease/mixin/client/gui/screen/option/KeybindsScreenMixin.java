package org.nka.notchplease.mixin.client.gui.screen.option;

import net.minecraft.client.gui.screen.option.ControlsListWidget;
import net.minecraft.client.gui.screen.option.KeybindsScreen;
import net.minecraft.client.gui.widget.TextWidget;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static org.nka.notchplease.Notchplease.getScaledNotchHeight;

@Mixin(KeybindsScreen.class)
public class KeybindsScreenMixin extends GameOptionsScreenMixin {
    @Shadow
    private ControlsListWidget controlsList;

    @Inject(method = "refreshWidgetPositions", at = @At("TAIL"))
    private void onRefreshWidgetPositions(CallbackInfo ci) {
        int notchHeight = getScaledNotchHeight();
        if (notchHeight == -1) return;
        this.layout.forEachChild((element) -> {
            if (element instanceof TextWidget)
            {
                element.setY(element.getY() + notchHeight);
            }
        });
        this.controlsList.setY(this.controlsList.getY() + notchHeight);
        this.controlsList.setHeight(this.controlsList.getHeight() - notchHeight);
    }
}