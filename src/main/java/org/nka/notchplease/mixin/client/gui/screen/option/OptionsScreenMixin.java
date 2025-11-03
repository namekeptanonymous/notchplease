package org.nka.notchplease.mixin.client.gui.screen.option;

import net.minecraft.client.gui.screen.option.OptionsScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.ThreePartsLayoutWidget;
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
    private ThreePartsLayoutWidget layout;

    @Inject(method = "refreshWidgetPositions", at = @At("TAIL"))
    private void onRefreshWidgetPositions(CallbackInfo ci) {
        int notchHeight = getScaledNotchHeight();
        if (notchHeight == -1) return;
        this.layout.forEachChild((element) -> {
//            System.out.println("all: " + element + " " + element.getMessage());
            if (!(element instanceof ButtonWidget)
                    || element.getMessage().toString().contains("difficulty")
                    || element.getMessage().toString().contains("online"))
            {
//                System.out.println("caught: " + element + element.getMessage());
                element.setY(element.getY() + notchHeight);
            }
        });
    }
}