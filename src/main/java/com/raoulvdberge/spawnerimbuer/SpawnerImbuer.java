package com.raoulvdberge.spawnerimbuer;

import net.minecraftforge.fml.relauncher.IFMLLoadingPlugin;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.annotation.Nullable;
import java.util.Map;

@IFMLLoadingPlugin.Name("SpawnerImbuer Coremod")
public class SpawnerImbuer implements IFMLLoadingPlugin {
    static final Logger LOGGER = LogManager.getLogger("SpawnerImbuer");

    @Override
    public String[] getASMTransformerClass() {
        return new String[]{
            "com.raoulvdberge.spawnerimbuer.SpawnerImbuerTransformer"
        };
    }

    @Override
    public String getModContainerClass() {
        return "com.raoulvdberge.spawnerimbuer.SpawnerImbuerContainer";
    }

    @Nullable
    @Override
    public String getSetupClass() {
        return null;
    }

    @Override
    public void injectData(Map<String, Object> data) {
        // NO OP
    }

    @Override
    public String getAccessTransformerClass() {
        return null;
    }
}
