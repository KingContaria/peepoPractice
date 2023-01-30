package me.quesia.peepopractice.core.category.properties;

import me.quesia.peepopractice.core.category.PracticeCategory;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.ConfiguredStructureFeature;
import net.minecraft.world.gen.feature.StructureFeature;

import java.util.Random;

@SuppressWarnings("UnusedDeclaration")
public class StructureProperties extends BaseProperties {
    private ConfiguredStructureFeature<?, ?> structure;
    private ChunkPos chunkPos;
    private Direction orientation;
    private PracticeCategory.ExecuteReturnTask<ChunkPos> chunkPosTask;
    private Integer structureTopY;
    private boolean unique = false;
    private boolean generated = false;

    public ConfiguredStructureFeature<?, ?> getStructure() {
        return this.structure;
    }

    public boolean isSameStructure(ConfiguredStructureFeature<?, ?> feature) {
        return this.hasStructure() && feature.field_24835.getName().equals(this.structure.field_24835.getName());
    }

    public boolean isSameStructure(StructureFeature<?> feature) {
        return this.hasStructure() && feature.getName().equals(this.structure.field_24835.getName());
    }

    public boolean hasStructure() {
        return this.structure != null;
    }

    public StructureProperties setStructure(ConfiguredStructureFeature<?, ?> structure) {
        this.structure = structure;
        return this;
    }

    public ChunkPos getChunkPos() {
        return this.chunkPos;
    }

    public boolean hasChunkPos() {
        return this.chunkPos != null || this.chunkPosTask != null;
    }

    public StructureProperties setChunkPos(ChunkPos structureChunkPos) {
        this.chunkPos = structureChunkPos;
        this.chunkPosTask = null;
        return this;
    }

    public StructureProperties setChunkPos(PracticeCategory.ExecuteReturnTask<ChunkPos> task) {
        this.chunkPosTask = task;
        this.chunkPos = null;
        return this;
    }

    public Direction getOrientation() {
        return this.orientation;
    }

    public boolean hasOrientation() {
        return this.orientation != null;
    }

    public StructureProperties setOrientation(Direction orientation) {
        this.orientation = orientation;
        return this;
    }

    public Integer getStructureTopY() {
        return this.structureTopY;
    }

    public boolean hasStructureTopY() {
        return this.structureTopY != null;
    }

    public StructureProperties setStructureTopY(int structureTopY) {
        this.structureTopY = structureTopY;
        return this;
    }

    public boolean isUnique() {
        return this.unique;
    }

    public StructureProperties setUnique(boolean unique) {
        this.unique = unique;
        return this;
    }

    public boolean hasGenerated() {
        return this.generated;
    }

    public void setGenerated() {
        this.generated = true;
    }

    public void reset(Random random, World world) {
        this.generated = false;
        if (this.chunkPosTask != null) {
            this.setChunkPos(this.chunkPosTask.execute(random, world));
        }
    }
}
