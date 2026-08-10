package org.nka.notchplease.mixin.client.gui.screens;

import net.minecraft.client.gui.components.events.GuiEventListener;
import net.minecraft.client.gui.screens.Screen;
import org.spongepowered.asm.mixin.*;

import java.util.List;

@Mixin(Screen.class)
public abstract class ScreenMixin {
    @Mutable
    @Shadow @Final private final List<GuiEventListener> children;

    protected ScreenMixin(List<GuiEventListener> children) {
        this.children = children;
    }

    @Unique
    public List<GuiEventListener> getChildren() {
        return this.children;
    }
}
