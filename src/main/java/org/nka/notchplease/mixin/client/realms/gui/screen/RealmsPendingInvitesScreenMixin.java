package org.nka.notchplease.mixin.client.realms.gui.screen;

import net.minecraft.client.gui.widget.AlwaysSelectedEntryListWidget;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.ThreePartsLayoutWidget;
import net.minecraft.client.realms.gui.screen.RealmsPendingInvitesScreen;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static org.nka.notchplease.Notchplease.getScaledNotchHeight;

@Mixin(RealmsPendingInvitesScreen.class)
public class RealmsPendingInvitesScreenMixin {

//    @Shadow
//    @Final
//    @Nullable
//    private ThreePartsLayoutWidget layout;
//
//    @Inject(method = "refreshWidgetPositions", at = @At("TAIL"))
//    private void onRefreshWidgetPositions(CallbackInfo ci) {
//        int notchHeight = getScaledNotchHeight();
//        if (notchHeight == -1) return;
//        if (this.layout != null) {
//            this.layout.forEachChild((element) -> {
//                System.out.println("all: " + element + " " + element.getMessage());
//                if (!(element instanceof ButtonWidget))
//                {
//                    element.setY(element.getY() + notchHeight);
//                    if (element instanceof AlwaysSelectedEntryListWidget<?>) {
//                        element.setHeight(element.getHeight() - notchHeight);
//                    }
//                }
//            });
//        }
//    }

}