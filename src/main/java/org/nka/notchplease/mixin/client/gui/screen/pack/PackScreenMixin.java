package org.nka.notchplease.mixin.client.gui.screen.pack;

import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.layouts.HeaderAndFooterLayout;
import net.minecraft.client.gui.screens.packs.PackSelectionScreen;
import net.minecraft.client.gui.screens.packs.TransferableSelectionList;
import org.jspecify.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static org.nka.notchplease.Notchplease.getScaledNotchHeight;

@Mixin(PackSelectionScreen.class)
public abstract class PackScreenMixin {

    @Shadow @Nullable
    private TransferableSelectionList availablePackList;

    @Shadow @Nullable
    private TransferableSelectionList selectedPackList;

    @Shadow
    @Final
    private HeaderAndFooterLayout layout;

    @Inject(method = "repositionElements", at = @At("TAIL"))
    private void onRepositionElements(CallbackInfo ci) {
        int notchHeight = getScaledNotchHeight();
        if (notchHeight == -1) return;

        this.layout.visitWidgets((element) -> {
            if (!(element instanceof Button)) {
                element.setY(element.getY() + notchHeight);
            }
        });
        assert this.availablePackList != null;
        assert this.selectedPackList != null;
        this.availablePackList.setHeight(this.availablePackList.getHeight() - notchHeight);
        this.selectedPackList.setHeight(this.selectedPackList.getHeight() - notchHeight);
        this.availablePackList.setScrollAmount(0);
        this.selectedPackList.setScrollAmount(0);
    }
}
