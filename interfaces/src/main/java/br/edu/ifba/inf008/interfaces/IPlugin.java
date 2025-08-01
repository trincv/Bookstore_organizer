package br.edu.ifba.inf008.interfaces;

import br.edu.ifba.inf008.interfaces.ICore;

public interface IPlugin
{
    public abstract String getName();

    public abstract boolean init(INavigationController navController);
}
