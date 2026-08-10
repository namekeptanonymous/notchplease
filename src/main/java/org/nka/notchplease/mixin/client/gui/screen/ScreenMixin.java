package org.nka.notchplease.mixin.client.gui.screen;

//import net.minecraft.client.gui.Element;
import net.minecraft.client.gui.components.events.GuiEventListener;
import net.minecraft.client.gui.navigation.ScreenRectangle;
import net.minecraft.client.gui.screens.Screen;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;

import static org.nka.notchplease.Notchplease.getScaledNotchHeight;

@Mixin(Screen.class)
public abstract class ScreenMixin {
    @Mutable
    @Shadow @Final private final List<GuiEventListener> children;

    @Shadow public int width;

    protected ScreenMixin(List<GuiEventListener> children) {
        this.children = children;
    }

    @Unique
    public List<GuiEventListener> getChildren() {
        return this.children;
    }
}
