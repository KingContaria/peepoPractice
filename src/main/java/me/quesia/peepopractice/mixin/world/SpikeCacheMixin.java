package me.quesia.peepopractice.mixin.world;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import me.quesia.peepopractice.core.category.CategoryPreference;
import me.quesia.peepopractice.core.category.PracticeTypes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import java.util.List;
import java.util.Random;

@Mixin(targets = "net/minecraft/world/gen/feature/EndSpikeFeature$SpikeCache")
public abstract class SpikeCacheMixin {
    @WrapOperation(method = "load(Ljava/lang/Long;)Ljava/util/List;", at = @At(value = "INVOKE", target = "Ljava/util/Collections;shuffle(Ljava/util/List;Ljava/util/Random;)V"))
    private void peepoPractice$modifyTowerOrder(List<Integer> towers, Random random, Operation<Void> original) {
        PracticeTypes.StartNodeType configValue = PracticeTypes.StartNodeType.fromLabel(CategoryPreference.getValue("start_node"));
        if (configValue != null) {
            original.call(towers, random);
            PracticeTypes.StartNodeType dragonType = configValue.equals(PracticeTypes.StartNodeType.RANDOM) ? this.peepoPractice$getDragonType(towers) : configValue;
            if (dragonType != this.peepoPractice$getDragonType(towers)){
                int temp = towers.get(0);
                towers.set(0,towers.get(5));
                towers.set(5,temp);
            }
        }
    }

    private PracticeTypes.StartNodeType peepoPractice$getDragonType(List<Integer> towers) {
        return towers.get(0) > towers.get(5) ? PracticeTypes.StartNodeType.FRONT : PracticeTypes.StartNodeType.BACK;
    }
}
