package br.edu.ifba.inf008.shell;

import br.edu.ifba.inf008.App;
import br.edu.ifba.inf008.interfaces.IPluginController;
import br.edu.ifba.inf008.interfaces.IPlugin;
import br.edu.ifba.inf008.interfaces.ICore;
import java.io.File;
import java.io.FilenameFilter;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.HashMap;
import java.util.Map;

public class PluginController implements IPluginController
{

    private final Map<String, IPlugin> loadedPlugins = new HashMap<>();

    public boolean init() {
        try {
            File currentDir = new File("../plugins/jarPlugins");

            // Define a FilenameFilter to include only .jar files
            FilenameFilter jarFilter = new FilenameFilter() {
                @Override
                public boolean accept(File dir, String name) {
                    return name.toLowerCase().endsWith(".jar");
                }
            };

            String[] plugins = currentDir.list(jarFilter);

            if (plugins == null || plugins.length == 0) {
                System.err.println("Error: 'plugins' directory not found or no JAR files found within it.");
                System.err.println("Please ensure a 'plugins' directory exists next to your application's JAR file,");
                System.err.println("and it contains plugin JARs.");
                return false; // Indicate failure to load plugins
            }

            int i;

            URL[] jars = new URL[plugins.length];

            for (i = 0; i < plugins.length; i++) 
            {
                File jarFile = new File("../plugins/jarPlugins/" + plugins[i]);
                System.out.println("-> Plugin jar file: " + jarFile.getAbsolutePath());
                jars[i] = jarFile.toURI().toURL();
            }

            URLClassLoader ulc = new URLClassLoader(jars, App.class.getClassLoader());

            for (i = 0; i < plugins.length; i++)
            {
                String pluginName = plugins[i].split("\\.")[0];
                IPlugin plugin = (IPlugin) Class.forName("br.edu.ifba.inf008.plugins." + pluginName, true, ulc).newInstance();
                System.out.println("Plugin loaded: " + pluginName);
                loadedPlugins.put(pluginName, plugin);
                //plugin.init();
            }

            return true;

        } catch (Exception e) {
            System.out.println("Error: " + e.getClass().getName() + " - " + e.getMessage());
            System.out.println(e.getStackTrace());
            

            return false;
        }
    }

    @Override
    public IPlugin getPlugin(String pluginName) {
        return loadedPlugins.get(pluginName);
    }
}
