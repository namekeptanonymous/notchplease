package org.nka.notchplease.mixin.client.realms.gui.screen;

import com.mojang.realmsclient.RealmsMainScreen;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.ObjectSelectionList;
import net.minecraft.client.gui.layouts.HeaderAndFooterLayout;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static org.nka.notchplease.Notchplease.getScaledNotchHeight;

@Mixin(RealmsMainScreen.class)
public class RealmsMainScreenMixin {

    @Shadow @Nullable
    private HeaderAndFooterLayout layout;

    @Inject(method = "repositionElements", at = @At("TAIL"))
    private void onRepositionElements(CallbackInfo ci) {
        int notchHeight = getScaledNotchHeight();
        if (notchHeight == -1) return;
        if (this.layout != null) {
            this.layout.visitWidgets((element) -> {
//                System.out.println("all: " + element + " " + element.getMessage());
                if (!(element instanceof Button)
                        || element.getMessage().toString().contains("invites")
                        || element.getMessage().toString().contains("news"))
                {
                    element.setY(element.getY() + notchHeight);
                    if (element instanceof ObjectSelectionList<?> list) {
                        list.setHeight(list.getHeight() - notchHeight);
                        list.setScrollAmount(0);
                    }
                }
            });
        }
    }

}