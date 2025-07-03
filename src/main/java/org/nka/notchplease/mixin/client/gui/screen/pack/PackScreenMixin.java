package org.nka.notchplease.mixin.client.gui.screen.pack;

import net.minecraft.client.gui.screen.pack.PackListWidget;
import net.minecraft.client.gui.screen.pack.PackScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.ThreePartsLayoutWidget;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static org.nka.notchplease.Notchplease.getScaledNotchHeight;

@Mixin(PackScreen.class)
public abstract class PackScreenMixin {

    @Shadow
    private PackListWidget availablePackList;

    @Shadow
    private PackListWidget selectedPackList;

    @Shadow
    @Final
    private ThreePartsLayoutWidget layout;

    @Inject(method = "refreshWidgetPositions", at = @At("TAIL"))
    private void onRefreshWidgetPositions(CallbackInfo ci) {
        int notchHeight = getScaledNotchHeight();
        if (notchHeight == -1) return;

        this.availablePackList.setY(this.availablePackList.getY() + notchHeight);
        this.selectedPackList.setY(this.selectedPackList.getY() + notchHeight);
        this.layout.forEachChild((element) -> {
            if (!(element instanceof ButtonWidget)) {
                element.setY(element.getY() + notchHeight);
            }
        });
        this.availablePackList.setHeight(this.availablePackList.getHeight() - notchHeight);
        this.selectedPackList.setHeight(this.selectedPackList.getHeight() - notchHeight);
    }
}
