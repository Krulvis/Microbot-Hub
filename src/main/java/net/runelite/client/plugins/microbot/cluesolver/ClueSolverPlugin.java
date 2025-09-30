package net.runelite.client.plugins.microbot.cluesolver;

import com.google.inject.Inject;
import com.google.inject.Provides;
import lombok.extern.slf4j.Slf4j;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDependency;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.plugins.cluescrolls.ClueScrollPlugin;
import net.runelite.client.plugins.microbot.PluginConstants;
import net.runelite.client.ui.overlay.OverlayManager;

@PluginDescriptor(
        name = "Clue Solver",
        description = "Automates solving clue scrolls by managing interactions",
        tags = {"clue", "solver", "automation"},
        authors = {"unknown"},
        version = ClueSolverPlugin.version,
        minClientVersion = "2.0.7",
        cardUrl = "https://chsami.github.io/Microbot-Hub/ClueSolverPlugin/assets/card.png",
        iconUrl = "https://chsami.github.io/Microbot-Hub/ClueSolverPlugin/assets/icon.png",
        enabledByDefault = PluginConstants.DEFAULT_ENABLED,
        isExternal = PluginConstants.IS_EXTERNAL
)
@Slf4j
@PluginDependency(ClueScrollPlugin.class)
public class ClueSolverPlugin extends Plugin {

    final static String version = "1.0.0";
    @Inject
    private ClueSolverScript clueSolverScript;

    @Inject
    private ClueSolverOverlay clueSolverOverlay;

    @Inject
    private OverlayManager overlayManager;

    @Inject
    private ClueSolverConfig clueSolverConfig;

    @Provides
    ClueSolverConfig provideClueSolverConfig(ConfigManager configManager) {
        return configManager.getConfig(ClueSolverConfig.class);
    }

    @Override
    protected void startUp() {
        log.info("Starting Clue Solver Plugin");
        if (null != overlayManager) {
            overlayManager.add(clueSolverOverlay);
        }
        clueSolverScript.start();
    }

    @Override
    protected void shutDown() {
        log.info("Shutting down Clue Solver Plugin");
        overlayManager.remove(clueSolverOverlay);
        clueSolverScript.shutdown();
    }

}
