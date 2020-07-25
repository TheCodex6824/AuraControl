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

package thecodex6824.auracontrol.api.internal;

import java.util.Set;

import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;

/**
 * For internal use only.
 */
public interface IInternalMethodHandler {

    public Set<Biome> getAllowedBiomes();
    
    public void handleAura(World world, int chunkX, int chunkZ);
    
    public void setupTCWorldgenFlags(World world, int chunkX, int chunkZ);
    
    public boolean shouldHandleAuraGen();
    
    public void setHandleAuraGen(boolean allow);
    
    public boolean shouldHandleCrystalGen();
    
    public void setHandleCrystalGen(boolean handle);
    
    public boolean shouldHandleTreeGen();
    
    public void setHandleTreeGen(boolean handle);
    
}
