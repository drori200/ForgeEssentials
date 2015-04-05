package com.forgeessentials.core.environment;

import com.forgeessentials.core.moduleLauncher.ModuleLauncher;
import com.forgeessentials.commons.VersionUtils;
import com.forgeessentials.core.preloader.FELaunchHandler;
import com.forgeessentials.util.FunctionHelper;
import cpw.mods.fml.common.ICrashCallable;

/**
 * Adds FE debug info to crash reports
 */
public class FECrashCallable implements ICrashCallable
{
    @Override
    public String getLabel()
    {
        return "ForgeEssentials";
    }

    @Override
    public String call() throws Exception
    {
        String modules = FunctionHelper.stitchString(ModuleLauncher.getModuleList().toArray(new String[]{}), true);
        String n = System.getProperty("line.separator");
        String returned =  "Build information: Build number is: " + VersionUtils.getBuildNumber(FELaunchHandler.jarLocation)
                + ", Build hash is: " + VersionUtils.getBuildHash(FELaunchHandler.jarLocation)
                + ", Modules loaded: " + modules;

        if (Environment.hasCauldron)
        {
            returned = returned + n + "Cauldron detected - DO NOT REPORT THIS CRASH TO FE OR CAULDRON.";
        }

        return returned;
    }
}
