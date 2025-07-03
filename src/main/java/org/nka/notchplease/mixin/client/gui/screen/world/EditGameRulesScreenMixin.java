package org.nka.notchplease.mixin.client.gui.screen.world;

import net.minecraft.client.gui.screen.world.EditGameRulesScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.ThreePartsLayoutWidget;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static org.nka.notchplease.Notchplease.getScaledNotchHeight;

@Mixin(EditGameRulesScreen.class)
public class EditGameRulesScreenMixin {
    @Shadow
    @Final
    ThreePartsLayoutWidget layout;

    @Inject(method = "refreshWidgetPositions", at = @At("TAIL"))
    private void onRefreshWidgetPositions(CallbackInfo ci) {
        int notchHeight = getScaledNotchHeight();
        if (notchHeight == -1) return;
        this.layout.forEachChild((element) -> {
            if (!(element instanceof ButtonWidget))
            {
                element.setY(element.getY() + notchHeight);
                System.out.println("caught: " + element + " " + element.getMessage());
                if (element instanceof EditGameRulesScreen.RuleListWidget) {
                    element.setHeight(element.getHeight() - notchHeight);
                }
            }
        });
    }
}