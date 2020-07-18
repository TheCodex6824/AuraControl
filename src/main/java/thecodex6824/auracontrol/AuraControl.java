/**
 *  Thaumic Augmentation
 *  Copyright (c) 2019 TheCodex6824.
 *
 *  This file is part of Thaumic Augmentation.
 *
 *  Thaumic Augmentation is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU Lesser General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  Thaumic Augmentation is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU Lesser General Public License for more details.
 *
 *  You should have received a copy of the GNU Lesser General Public License
 *  along with Thaumic Augmentation.  If not, see <https://www.gnu.org/licenses/>.
 */

package thecodex6824.auracontrol;

import java.util.Set;

import com.google.common.collect.ImmutableSet;

import net.minecraft.world.biome.Biome;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import thecodex6824.auracontrol.api.AuraControlAPI;

@Mod(modid = AuraControlAPI.MODID, name = AuraControlAPI.NAME, version = AuraControl.VERSION, useMetadata = true)
public class AuraControl {
    public static final String VERSION = "@VERSION@";
    
    @Instance(AuraControlAPI.MODID)
    public static AuraControl instance;
    
    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        AuraControlAPI.setMethodHandler(new InternalMethodHandler());
    }
    
    @EventHandler
    public void init(FMLInitializationEvent event) {
        Set<String> list = ImmutableSet.copyOf(AuraConfig.biomeList);
        Set<Biome> allowedBiomes = AuraControlAPI.getAllowedBiomes();
        Biome.REGISTRY.forEach(allowedBiomes::add);
        if (AuraConfig.allowList)
            allowedBiomes.removeIf(b -> !list.contains(b.getRegistryName().toString()));
        else
            allowedBiomes.removeIf(b -> list.contains(b.getRegistryName().toString()));
        
        AuraControlAPI.setHandleCrystalGen(AuraConfig.crystals);
        AuraControlAPI.setHandleTreeGen(AuraConfig.trees);
        
        GameRegistry.registerWorldGenerator(new AuraWorldSetupGenerator(), -1);
        GameRegistry.registerWorldGenerator(new AuraWorldGenerator(), 100000);
    }
    
}
