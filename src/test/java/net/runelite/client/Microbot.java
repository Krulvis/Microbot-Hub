package net.runelite.client;

import net.runelite.client.plugins.microbot.cluesolver.ClueSolverPlugin;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Microbot
{

	private static final Class<?>[] debugPlugins = {
		ClueSolverPlugin.class
	};

    public static void main(String[] args) throws Exception
    {
		List<Class<?>> _debugPlugins = Arrays.stream(debugPlugins).collect(Collectors.toList());
        RuneLiteDebug.pluginsToDebug.addAll(_debugPlugins);
        RuneLiteDebug.main(args);
    }
}
