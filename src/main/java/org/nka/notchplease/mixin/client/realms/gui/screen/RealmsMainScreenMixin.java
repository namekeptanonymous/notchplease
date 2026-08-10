package org.nka.notchplease.mixin.client.realms.gui.screen;

//import net.minecraft.client.gui.widget.AlwaysSelectedEntryListWidget;
//import net.minecraft.client.gui.widget.ButtonWidget;
//import net.minecraft.client.gui.widget.ThreePartsLayoutWidget;
//import net.minecraft.client.realms.gui.screen.RealmsMainScreen;
//import org.jetbrains.annotations.Nullable;
//import org.spongepowered.asm.mixin.Mixin;
//import org.spongepowered.asm.mixin.Shadow;
//import org.spongepowered.asm.mixin.injection.At;
//import org.spongepowered.asm.mixin.injection.Inject;
//import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
//
//import static org.nka.notchplease.Notchplease.getScaledNotchHeight;

//@Mixin(RealmsMainScreen.class)
public class RealmsMainScreenMixin {

//    @Shadow @Final
//    private HeaderAndFooterLayout layout;
//
//    @Inject(method = "repositionElements", at = @At("TAIL"))
//    private void onRepositionElements(CallbackInfo ci) {
//        int notchHeight = getScaledNotchHeight();
//        if (notchHeight == -1) return;
//        if (this.layout != null) {
//            this.layout.visitChildren((element) -> {
////                System.out.println("all: " + element + " " + element.getMessage());
//                if (!(element instanceof Button)
//                        || ((Button) element).getMessage().toString().contains("invites")
//                        || ((Button) element).getMessage().toString().contains("news"))
//                {
//                    element.setY(element.getY() + notchHeight);
//                    if (element instanceof AlwaysSelectedEntryListWidget<?> list) {
//                        list.setHeight(list.getHeight() - notchHeight);
//                        list.setScrollY(0);
//                    }
//                }
//            });
//        }
//    }

}