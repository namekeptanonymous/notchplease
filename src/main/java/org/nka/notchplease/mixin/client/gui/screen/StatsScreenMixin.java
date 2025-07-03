package org.nka.notchplease.mixin.client.gui.screen;

import net.minecraft.client.gui.screen.StatsScreen;
import net.minecraft.client.gui.widget.AlwaysSelectedEntryListWidget;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.ThreePartsLayoutWidget;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static org.nka.notchplease.Notchplease.getScaledNotchHeight;

@Mixin(StatsScreen.class)
public class StatsScreenMixin {
    @Shadow
    private ThreePartsLayoutWidget layout;
    @Shadow
    @Nullable
    private AlwaysSelectedEntryListWidget<?> selectedList;

    @Inject(method = "refreshWidgetPositions", at = @At("TAIL"))
    private void onRefreshWidgetPositions(CallbackInfo ci) {
        int notchHeight = getScaledNotchHeight();
        if (notchHeight == -1) return;
        this.layout.forEachChild((element) -> {
            if (!(element instanceof ButtonWidget)) {
                element.setY(element.getY() + notchHeight);
            }
        });
        if (this.selectedList != null) {
            this.selectedList.setY(this.selectedList.getY() + notchHeight);
            this.selectedList.setHeight(this.selectedList.getHeight() - notchHeight);
        }
    }
}