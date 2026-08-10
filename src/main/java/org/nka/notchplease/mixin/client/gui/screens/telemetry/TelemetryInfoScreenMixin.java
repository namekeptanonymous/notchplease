package org.nka.notchplease.mixin.client.gui.screens.telemetry;

import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.Checkbox;
import net.minecraft.client.gui.components.CycleButton;
import net.minecraft.client.gui.layouts.HeaderAndFooterLayout;
import net.minecraft.client.gui.screens.telemetry.TelemetryEventWidget;
import net.minecraft.client.gui.screens.telemetry.TelemetryInfoScreen;
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
    private HeaderAndFooterLayout layout;

    @Inject(method = "repositionElements", at = @At("TAIL"))
    private void onRepositionElements(CallbackInfo ci) {
        int notchHeight = getScaledNotchHeight();
        if (notchHeight == -1) return;
        this.layout.visitWidgets((element) -> {
//            System.out.println("all: " + element + " " + element.toString());
            if (!(element instanceof Checkbox || element instanceof CycleButton<?>)
                    && (
                            !(element instanceof Button)
                            || element.getMessage().toString().contains("privacy")
                            || element.getMessage().toString().contains("feedback")
                    ))
            {
                element.setY(element.getY() + notchHeight);
                if (element instanceof TelemetryEventWidget tew) {
                    tew.setHeight(tew.getHeight() - notchHeight);
                    tew.setScrollAmount(0);
                }
            }
        });
    }
}