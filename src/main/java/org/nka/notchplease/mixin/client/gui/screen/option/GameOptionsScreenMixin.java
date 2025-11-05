package org.nka.notchplease.mixin.client.gui.screen.option;

import net.minecraft.client.gui.screen.option.GameOptionsScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.OptionListWidget;
import net.minecraft.client.gui.widget.TextWidget;
import net.minecraft.client.gui.widget.ThreePartsLayoutWidget;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static org.nka.notchplease.Notchplease.getScaledNotchHeight;

@Mixin(GameOptionsScreen.class)
public class GameOptionsScreenMixin {
    @Shadow
    @Final
    public ThreePartsLayoutWidget layout;

    @Shadow
    @Nullable
    protected OptionListWidget body;

    @Inject(method = "refreshWidgetPositions", at = @At("TAIL"))
    private void onRefreshWidgetPositions(CallbackInfo ci) {
        int notchHeight = getScaledNotchHeight();
        if (notchHeight == -1) return;
        this.layout.forEachChild((element) -> {
            if (element instanceof TextWidget)
                element.setY(element.getY() + notchHeight);
        });
        if (body != null) {
            body.setY(body.getY() + notchHeight);
            body.setHeight(body.getHeight() - notchHeight);
            body.setScrollY(0);
        }
    }
}