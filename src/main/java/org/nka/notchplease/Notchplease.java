package org.nka.notchplease;

import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.Pointer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientLifecycleEvents;
import net.minecraft.client.MinecraftClient;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWNativeCocoa;
import org.lwjgl.glfw.GLFWVidMode;
import org.spongepowered.asm.mixin.Unique;
import org.lwjgl.system.macosx.*;

import java.util.ArrayList;

import static org.lwjgl.opengl.GL11.*;

public class Notchplease implements ClientModInitializer {
    static String gpuName;
    static boolean isAppleSilicon = false;

    public static ArrayList<Integer> resolutionHeights = new ArrayList<>();

    @Override
    public void onInitializeClient() {
        ClientLifecycleEvents.CLIENT_STARTED.register(client -> {
            gpuName = glGetString(GL_RENDERER);
            if (gpuName != null && gpuName.contains("Apple")) {
                isAppleSilicon = true;
            }
        });
    }

    public static boolean listAvailableResolutions() {
        resolutionHeights = new ArrayList<>();
        long window = MinecraftClient.getInstance().getWindow().getHandle();
        long monitor = GLFW.glfwGetWindowMonitor(window);

        if (monitor == 0L) {
//            System.out.println("Notch, Please! No monitor found.");
            return false;
        }

        int displayID = GLFWNativeCocoa.glfwGetCocoaMonitor(monitor);
        if (CoreGraphics.INSTANCE.CGDisplayIsBuiltin(displayID) == 0) {
//            System.out.println("Notch, Please! This is not the built-in monitor.");
            return false;
        }

        GLFWVidMode.Buffer vidModes = GLFW.glfwGetVideoModes(monitor);
        if (vidModes == null) {
//            System.out.println("Notch, Please! No video modes found.");
            return false;
        }

        for (int i = 0; i < vidModes.limit(); i++) {
            GLFWVidMode mode = vidModes.get(i);
            resolutionHeights.add(mode.height());
        }
        return !resolutionHeights.isEmpty();
    }

    @Unique
    public static int getScaledNotchHeight() {
        if (!listAvailableResolutions()) return -1;
        int currentHeight = MinecraftClient.getInstance().getWindow().getHeight();
        boolean isFullScreen = MinecraftClient.getInstance().getWindow().isFullscreen();
        double currentScale = MinecraftClient.getInstance().getWindow().getScaleFactor();

        // if resolution is not set to show notch
        if (currentHeight % 10 == 0 || resolutionHeights == null || !isFullScreen || !isAppleSilicon) return -1;

        int closestNonNotchedHeight = Integer.MIN_VALUE;
        int closestHeightDiff = Integer.MAX_VALUE;

        int currentHeightDiff = Integer.MIN_VALUE;
        for (Integer height : resolutionHeights) {
            currentHeightDiff = Math.abs(currentHeight - height);
            if (height < currentHeight && currentHeightDiff < closestHeightDiff) {
                closestHeightDiff = currentHeightDiff;
                closestNonNotchedHeight = height;
            }
        }

        int rawNotchHeight = currentHeight - closestNonNotchedHeight;

        // check to see if height diff = typical notch height in px, not something crazy
        if (rawNotchHeight > 200 || rawNotchHeight < 0) {
//            System.out.println("Notch, Please! There was an error calculating the notch height. Does your current display have a notch?");
            return -1;
        }
        return (int) (rawNotchHeight / currentScale);
    }

    public interface CoreGraphics extends Library {
        CoreGraphics INSTANCE = Native.load("CoreGraphics", CoreGraphics.class);

        // for future ref, this is linking to a pre-existing C function in CoreGraphics:
        // int CGDisplayIsBuiltin(CGDirectDisplayID display);
        // this returns a 1 if displayID = built-in displayID, 0 otherwise
        // this presumably needs to be an interface bcz you can't override/overload native code
        int CGDisplayIsBuiltin(int displayID);
    }

/*
    interface ObjCRuntime extends Library {
        ObjCRuntime INSTANCE = Native.load("objc", ObjCRuntime.class

        // This is also the same, linking native C functions to Java here
        Pointer objc_getClass(String className);
        Pointer sel_registerName(String selectorName);
        long objc_msgSend(Pointer receiver, Pointer selector);
    }

    public static boolean isNativeFullscreen() {
        if (!System.getProperty("os.name").toLowerCase().contains("mac")) return false;

        long windowPtr = MinecraftClient.getInstance().getWindow().getHandle();
        long nsWindowPtr = GLFWNativeCocoa.glfwGetCocoaWindow(windowPtr);
        if (nsWindowPtr == 0) return false;

        Pointer nsWindow = new Pointer(nsWindowPtr);
        Pointer selStyleMask = ObjCRuntime.INSTANCE.sel_registerName("styleMask");
        long styleMask = ObjCRuntime.INSTANCE.objc_msgSend(nsWindow, selStyleMask);

        final long NSWindowStyleMaskFullScreen = 1L << 14;
        return (styleMask & NSWindowStyleMaskFullScreen) != 0;
    }

    public static void toggleNativeFullscreen() {
        long windowPtr = MinecraftClient.getInstance().getWindow().getHandle();
        long nsWindowPtr = GLFWNativeCocoa.glfwGetCocoaWindow(windowPtr);
        Pointer nsWindow = new Pointer(nsWindowPtr);
        Pointer selToggleFullscreen = ObjCRuntime.INSTANCE.sel_registerName("toggleFullScreen:");
        ObjCRuntime.INSTANCE.objc_msgSend(nsWindow, selToggleFullscreen);
    }

    // since minecraft by default on macOS does not enable Minecraft's built-in fullscreen functionality
    public static void triggerActualFullscreen() {
        boolean isFullScreen = MinecraftClient.getInstance().getWindow().isFullscreen();
        if (!isFullScreen) {
            if (isNativeFullscreen()) toggleNativeFullscreen();
            MinecraftClient.getInstance().getWindow().toggleFullscreen();
        } else {
            MinecraftClient.getInstance().getWindow().toggleFullscreen();
            if (!isNativeFullscreen()) toggleNativeFullscreen();
        }
    }
*/
}
