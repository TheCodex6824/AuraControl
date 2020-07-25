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

package thecodex6824.auracontrol.api;

import java.util.Set;

import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import thecodex6824.auracontrol.api.internal.IInternalMethodHandler;

public final class AuraControlAPI {

    private AuraControlAPI() {}
    
    public static final String MODID = "auracontrol";
    public static final String NAME = "AuraControl";
    public static final String PROVIDES = MODID + "api";
    public static final String API_VERSION = "@APIVERSION@";
    
    private static IInternalMethodHandler handler;
    
    /**
     * For internal use only - sets the handler for API methods
     * @param newHandler The handler to use
     */
    public static void setMethodHandler(IInternalMethodHandler newHandler) {
        handler = newHandler;
    }
    
    /**
     * Returns the set of all biomes that should have an aura and aura features.
     * It will always be the allowed biomes regardless of how the user configured
     * the list (i.e. regardless of whether a blocklist or allowlist was used).
     * @return The set of all biomes that should have an aura generated
     */
    public static Set<Biome> getAllowedBiomes() {
        return handler.getAllowedBiomes();
    }
    
    /**
     * Modifies the aura, taking the allowed biome list into account, for the given chunk.
     * It may set the chunk vis cap, vis amount, and flux amount, but will not change
     * the information in the chunk's OriginalAuraInfo capability.
     * @param world The world the chunk is located in
     * @param chunkX The chunk X coordinate
     * @param chunkZ The chunk Z coordinate
     * @see IOriginalAuraInfo
     */
    public static void handleAura(World world, int chunkX, int chunkZ) {
        handler.handleAura(world, chunkX, chunkZ);
    }
    
    /**
     * Prepares the static final fields in TC's config class to support disabling
     * crystals and Thaumcraft trees in biomes with no aura. This needs to be called
     * before Thaumcraft's world generator is called.
     * @param world The world the chunk is located in
     * @param chunkX The chunk X coordinate
     * @param chunkZ The chunk Z coordinate
     */
    public static void setupTCWorldgenFlags(World world, int chunkX, int chunkZ) {
        handler.setupTCWorldgenFlags(world, chunkX, chunkZ);
    }
    
    /**
     * Returns if aura gen should be controlled.
     * @return If crystal gen should be controlled
     */
    public static boolean shouldHandleAuraGen() {
        return handler.shouldHandleAuraGen();
    }
    
    /**
     * Sets if crystal gen should be controlled.
     * @param handle If crystal gen should be handled
     */
    public static void setHandleAuraGen(boolean handle) {
        handler.setHandleAuraGen(handle);
    }
    
    /**
     * Returns if crystal gen should be controlled.
     * @return If crystal gen should be controlled
     */
    public static boolean shouldHandleCrystalGen() {
        return handler.shouldHandleCrystalGen();
    }
    
    /**
     * Sets if crystal gen should be controlled.
     * @param handle If crystal gen should be handled
     */
    public static void setHandleCrystalGen(boolean handle) {
        handler.setHandleCrystalGen(handle);
    }
    
    /**
     * Returns if Thaumcraft tree gen should be controlled.
     * @return If tree gen should be controlled
     */
    public static boolean shouldHandleTreeGen() {
        return handler.shouldHandleTreeGen();
    }
    
    /**
     * Sets if Thaumcraft tree gen should be controlled.
     * @param handle If tree gen should be handled
     */
    public static void setHandleTreeGen(boolean handle) {
        handler.setHandleTreeGen(handle);
    }
    
}
