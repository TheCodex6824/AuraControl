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
    
    public static void setMethodHandler(IInternalMethodHandler newHandler) {
        handler = newHandler;
    }
    
    public static Set<Biome> getAllowedBiomes() {
        return handler.getAllowedBiomes();
    }
    
    public static void handleAura(World world, int chunkX, int chunkZ) {
        handler.handleAura(world, chunkX, chunkZ);
    }
    
    public static void setupTCWorldgenFlags(World world, int chunkX, int chunkZ) {
        handler.setupTCWorldgenFlags(world, chunkX, chunkZ);
    }
    
    public static boolean shouldHandleCrystalGen() {
        return handler.shouldHandleCrystalGen();
    }
    
    public static void setHandleCrystalGen(boolean handle) {
        handler.setHandleCrystalGen(handle);
    }
    
    public static boolean shouldHandleTreeGen() {
        return handler.shouldHandleTreeGen();
    }
    
    public static void setHandleTreeGen(boolean handle) {
        handler.setHandleTreeGen(handle);
    }
    
}
