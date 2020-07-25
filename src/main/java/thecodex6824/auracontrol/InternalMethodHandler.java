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

import java.util.HashSet;
import java.util.Set;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import thaumcraft.common.config.ModConfig;
import thaumcraft.common.world.aura.AuraChunk;
import thaumcraft.common.world.aura.AuraHandler;
import thecodex6824.auracontrol.api.AuraControlAPI;
import thecodex6824.auracontrol.api.internal.IInternalMethodHandler;

public class InternalMethodHandler implements IInternalMethodHandler {

    protected HashSet<Biome> allowedBiomes = new HashSet<>();
    protected boolean crystalGen;
    protected boolean treeGen;
    protected boolean auraGen;
    
    @Override
    public Set<Biome> getAllowedBiomes() {
        return allowedBiomes;
    }
    
    @Override
    public void handleAura(World world, int chunkX, int chunkZ) {
        if (auraGen) {
            Biome biome = world.getBiome(new BlockPos(chunkX * 16 + 8, 64, chunkZ * 16 + 8));
            if (!AuraControlAPI.getAllowedBiomes().contains(biome)) {
                AuraChunk chunk = AuraHandler.getAuraChunk(world.provider.getDimension(), chunkX, chunkZ);
                chunk.setBase((short) 0);
                chunk.setVis(0.0F);
                chunk.setFlux(0.0F);
            }
        }
    }
    
    @Override
    public void setupTCWorldgenFlags(World world, int chunkX, int chunkZ) {
        if (crystalGen || treeGen) {
            Biome biome = world.getBiome(new BlockPos(chunkX * 16 + 8, 64, chunkZ * 16 + 8));
            if (!AuraControlAPI.getAllowedBiomes().contains(biome)) {
                ModConfig.CONFIG_WORLD.generateCrystals = false;
                ModConfig.CONFIG_WORLD.generateTrees = false;
            }
            else {
                ModConfig.CONFIG_WORLD.generateCrystals = true;
                ModConfig.CONFIG_WORLD.generateTrees = true;
            }
        }
    }
    
    @Override
    public boolean shouldHandleAuraGen() {
        return auraGen;
    }
    
    @Override
    public void setHandleAuraGen(boolean allow) {
        auraGen = allow;
    }
    
    @Override
    public boolean shouldHandleCrystalGen() {
        return crystalGen;
    }
    
    @Override
    public void setHandleCrystalGen(boolean allow) {
        crystalGen = allow;
    }
    
    @Override
    public boolean shouldHandleTreeGen() {
        return treeGen;
    }
    
    @Override
    public void setHandleTreeGen(boolean allow) {
        treeGen = allow;
    }
    
}
