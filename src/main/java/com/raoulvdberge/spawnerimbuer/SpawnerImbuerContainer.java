package com.raoulvdberge.spawnerimbuer;

import com.google.common.collect.ImmutableList;
import com.google.common.eventbus.EventBus;
import net.minecraftforge.fml.common.DummyModContainer;
import net.minecraftforge.fml.common.LoadController;
import net.minecraftforge.fml.common.ModMetadata;

import java.util.List;

public class SpawnerImbuerContainer extends DummyModContainer {
    private static final ModMetadata metaData;

    public SpawnerImbuerContainer() {
        super(metaData);
    }

    @Override
    public boolean registerBus(EventBus bus, LoadController controller) {
        return true;
    }

    @Override
    public List<String> getOwnedPackages() {
        return ImmutableList.of("com.raoulvdberge.spawnerimbuer");
    }

    static {
        metaData = new ModMetadata();
        metaData.modId = "spawnerimbuer";
        metaData.name = "Spawner Imbuer";
        metaData.description = "Allows mob spawners to run even if there are no players in range.";
        metaData.authorList = ImmutableList.of("raoulvdberge");
        metaData.version = "0.1";
    }
}
