package org.nka.notchplease.mixin.client.gui.screen.option;

import net.minecraft.client.gui.screen.option.TelemetryEventWidget;
import net.minecraft.client.gui.screen.option.TelemetryInfoScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.OptionListWidget;
import net.minecraft.client.gui.widget.ThreePartsLayoutWidget;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static org.nka.notchplease.Notchplease.getScaledNotchHeight;

@Mixin(TelemetryInfoScreen.class)
public class TelemetryInfoScreenMixin {
    @Shadow
    @Final
    private ThreePartsLayoutWidget layout;

    @Inject(method = "refreshWidgetPositions", at = @At("TAIL"))
    private void onRefreshWidgetPositions(CallbackInfo ci) {
        int notchHeight = getScaledNotchHeight();
        if (notchHeight == -1) return;
        this.layout.forEachChild((element) -> {
            System.out.println("all: " + element + " " + element.getMessage());
            if (!element.getMessage().toString().contains("telemetry.button")
                    && (
                            !(element instanceof ButtonWidget)
                            || element.getMessage().toString().contains("privacy")
                            || element.getMessage().toString().contains("feedback")
                    ))
            {
                element.setY(element.getY() + notchHeight);
                if (element instanceof TelemetryEventWidget) {
                    element.setHeight(element.getHeight() - notchHeight);
                }
            }
        });
    }
}