/**
 *  AuraControl
 *  Copyright (c) 2020 TheCodex6824.
 *
 *  This file is part of AuraControl.
 *
 *  AuraControl is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU Lesser General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  AuraControl is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU Lesser General Public License for more details.
 *
 *  You should have received a copy of the GNU Lesser General Public License
 *  along with AuraControl.  If not, see <https://www.gnu.org/licenses/>.
 */

package thecodex6824.auracontrol;

import java.util.Set;

import javax.annotation.Nullable;

import com.google.common.collect.ImmutableSet;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.Chunk;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.Capability.IStorage;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import thecodex6824.auracontrol.api.AuraControlAPI;
import thecodex6824.auracontrol.api.CapabilityOriginalAuraInfo;
import thecodex6824.auracontrol.api.IOriginalAuraInfo;
import thecodex6824.auracontrol.api.OriginalAuraInfo;

@Mod(modid = AuraControlAPI.MODID, name = AuraControlAPI.NAME, version = AuraControl.VERSION, useMetadata = true)
@EventBusSubscriber(modid = AuraControlAPI.MODID)
public class AuraControl {
    public static final String VERSION = "@VERSION@";
    
    @Instance(AuraControlAPI.MODID)
    public static AuraControl instance;
    
    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        AuraControlAPI.setMethodHandler(new InternalMethodHandler());
        CapabilityManager.INSTANCE.register(IOriginalAuraInfo.class, new IStorage<IOriginalAuraInfo>() {
                @Override
                public void readNBT(Capability<IOriginalAuraInfo> capability, IOriginalAuraInfo instance, EnumFacing side, NBTBase nbt) {
                    if (!(instance instanceof OriginalAuraInfo) || !(nbt instanceof NBTTagCompound))
                        throw new UnsupportedOperationException("Can't deserialize non-API implementation");
                    
                    ((OriginalAuraInfo) instance).deserializeNBT((NBTTagCompound) nbt);
                }
                
                @Override
                @Nullable
                public NBTBase writeNBT(Capability<IOriginalAuraInfo> capability, IOriginalAuraInfo instance, EnumFacing side) {
                    if (!(instance instanceof OriginalAuraInfo))
                        throw new UnsupportedOperationException("Can't serialize non-API implementation");
                    
                    return ((OriginalAuraInfo) instance).serializeNBT();
                }
        }, OriginalAuraInfo::new);
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
        
        AuraControlAPI.setHandleAuraGen(AuraConfig.aura);
        AuraControlAPI.setHandleCrystalGen(AuraConfig.crystals);
        AuraControlAPI.setHandleTreeGen(AuraConfig.trees);
        
        GameRegistry.registerWorldGenerator(new AuraWorldSetupGenerator(), -1);
        GameRegistry.registerWorldGenerator(new AuraWorldGenerator(), 100000);
    }
    
    @SubscribeEvent
    public static void onAttachCapabilityChunk(AttachCapabilitiesEvent<Chunk> event) {
        event.addCapability(new ResourceLocation(AuraControlAPI.NAME, "original_aura_info"),
                new SimpleCapabilityProvider<>(new OriginalAuraInfo(), CapabilityOriginalAuraInfo.AURA_INFO));
    }
    
}
