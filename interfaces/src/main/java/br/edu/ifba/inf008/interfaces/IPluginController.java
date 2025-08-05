package br.edu.ifba.inf008.interfaces;

import java.util.List;

public interface IPluginController
{
    public abstract boolean init();

    public abstract IPlugin getPlugin(String pluginName); 

    public List<IPlugin> getAllLoadedPlugins();
}
