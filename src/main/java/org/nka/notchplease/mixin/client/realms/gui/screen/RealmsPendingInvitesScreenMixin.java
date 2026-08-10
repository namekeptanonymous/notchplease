package org.nka.notchplease.mixin.client.realms.gui.screen;

import com.mojang.realmsclient.gui.screens.RealmsPendingInvitesScreen;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.ObjectSelectionList;
import net.minecraft.client.gui.layouts.HeaderAndFooterLayout;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static org.nka.notchplease.Notchplease.getScaledNotchHeight;

@Mixin(RealmsPendingInvitesScreen.class)
public class RealmsPendingInvitesScreenMixin {

    @Shadow
    @Final
    private HeaderAndFooterLayout layout;

    @Inject(method = "repositionElements", at = @At("TAIL"))
    private void onRepositionElements(CallbackInfo ci) {
        int notchHeight = getScaledNotchHeight();
        if (notchHeight == -1) return;
        if (this.layout != null) {
            this.layout.visitWidgets((element) -> {
//                System.out.println("all: " + element + " " + element.getMessage());
                if (!(element instanceof Button))
                {
                    element.setY(element.getY() + notchHeight);
                    if (element instanceof ObjectSelectionList<?>) {
                        element.setHeight(element.getHeight() - notchHeight);
                    }
                }
            });
        }
    }
}