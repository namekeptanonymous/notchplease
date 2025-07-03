package org.nka.notchplease.mixin.client.gui.screen.world;

import net.minecraft.client.gui.screen.world.ExperimentsScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.ThreePartsLayoutWidget;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static org.nka.notchplease.Notchplease.getScaledNotchHeight;

@Mixin(ExperimentsScreen.class)
public class ExperimentsScreenMixin {
    @Mutable
    @Shadow
    @Final
    private final ThreePartsLayoutWidget experimentToggleList;

    public ExperimentsScreenMixin(ThreePartsLayoutWidget experimentToggleList) {
        this.experimentToggleList = experimentToggleList;
    }

    @Inject(method = "refreshWidgetPositions", at = @At("TAIL"))
    private void onRefreshWidgetPositions(CallbackInfo ci) {
        int notchHeight = getScaledNotchHeight();
        if (notchHeight == -1) return;
        this.experimentToggleList.forEachChild((element) -> {
            if (!(element instanceof ButtonWidget))
            {
                element.setY(element.getY() + notchHeight);
            }
        });
    }
}