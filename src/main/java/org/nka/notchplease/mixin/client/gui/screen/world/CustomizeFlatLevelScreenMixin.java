package org.nka.notchplease.mixin.client.gui.screen.world;

import net.minecraft.client.gui.screen.world.CustomizeFlatLevelScreen;
import net.minecraft.client.gui.widget.AlwaysSelectedEntryListWidget;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.TextWidget;
import net.minecraft.client.gui.widget.ThreePartsLayoutWidget;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static org.nka.notchplease.Notchplease.getScaledNotchHeight;

@Mixin(CustomizeFlatLevelScreen.class)
public class CustomizeFlatLevelScreenMixin {
    @Shadow
    @Final
    private ThreePartsLayoutWidget layout;

    @Inject(method = "refreshWidgetPositions", at = @At("TAIL"))
    private void onRefreshWidgetPositions(CallbackInfo ci) {
        int notchHeight = getScaledNotchHeight();
        if (notchHeight == -1) return;
        this.layout.forEachChild((element) -> {
            if (element instanceof TextWidget tw) {
                tw.setY(tw.getY() + notchHeight);
            }
            if (element instanceof AlwaysSelectedEntryListWidget<?> list) {
                list.setY(list.getY() + notchHeight);
                list.setHeight(list.getHeight() - notchHeight);
                list.setScrollY(0);
            }
        });
    }
}