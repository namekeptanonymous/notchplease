<img src="src/main/resources/assets/notchplease/icon.png" width="128">

# Notch, Please!

A simple Fabric mod for Minecraft 1.21.4 – 1.21.8 that aims to make *vanilla* UI elements fit better on Apple's notched MacBooks when in fullscreen mode.

![pack-diff-s](https://github.com/user-attachments/assets/747db53a-52d4-42c1-b66f-218aadb4e0e2)

---

### 📥 Downloads

The latest releases of Notch, Please! can be downloaded [from the releases page](https://github.com/namekeptanonymous/notchplease/releases), [Modrinth](https://modrinth.com/mod/notch-please!), or [CurseForge](https://curseforge.com/minecraft/mc-mods/notch-please).

<a href="https://modrinth.com/mod/notch-please!"><img alt="modrinth" height="56" src="https://cdn.jsdelivr.net/npm/@intergrav/devins-badges@3/assets/cozy/available/modrinth_64h.png"></a>
<a href="https://curseforge.com/minecraft/mc-mods/notch-please"><img alt="curseforge" height="56" src="https://cdn.jsdelivr.net/npm/@intergrav/devins-badges@3/assets/cozy/available/curseforge_64h.png" style="margin-left: 0.1rem"></a>

### 🖥️ Installation

Notch, Please! requires the [_Fabric_ mod loader](https://fabricmc.net/) & the [Fabric API](https://modrinth.com/mod/fabric-api/).

Notch, Please! is designed specifically for MacBooks with display notches (M1 Pro, M2 and later) running macOS.

### 🔧 Usage

Since macOS's default fullscreen mode doesn't allow the game to extend into the notched area, you'll first need to enable Fullscreen under **Options → Video Settings**.

Then, make sure your screen resolution is set to something like `2560x1664` (not the notch-less `2560x1600`) to take full advantage of the available space.

Once that's set up, I recommend using [RenderScale](https://modrinth.com/mod/renderscale) to reduce your resolution to Retina levels (typically by half). This can significantly improve performance with little to no visible loss in quality on a MacBook display.

## 🛠️ Building from source

Notch, Please! uses the [Gradle build tool](https://gradle.org/) and can be built with the `./gradlew build` (macOS/Linux) or `./gradlew.bat build` commands.

### Build Requirements

- OpenJDK 21
- Gradle 8.x

## 📜 License

Except where otherwise stated, the content of this repository is provided under the [GPL v3](LICENSE) license by [namekeptanonymous](https://www.namekeptanonymous.me/).

<a href="https://buymeacoffee.com/justalilguy"><img alt="buymeacoffee-singular" height="56" src="https://cdn.jsdelivr.net/npm/@intergrav/devins-badges@3/assets/cozy/donate/buymeacoffee-singular_vector.svg"></a>