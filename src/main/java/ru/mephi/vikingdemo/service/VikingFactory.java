
package ru.mephi.vikingdemo.service;

import java.util.List;
import java.util.Random;
import net.datafaker.Faker;
import org.springframework.stereotype.Component;
import ru.mephi.vikingdemo.model.BeardStyle;
import ru.mephi.vikingdemo.model.EquipmentItem;
import ru.mephi.vikingdemo.model.HairColor;
import ru.mephi.vikingdemo.model.Viking;
import java.util.Locale;
import ru.mephi.vikingdemo.model.VikingEntity;

@Component
public class VikingFactory {

    private final Faker faker = new Faker(Locale.of("nor"));
    private final Random random = new Random();

    public Viking createRandomViking() {
        return new Viking(
                faker.name().firstName(),
                18 + random.nextInt(43),
                160 + random.nextInt(41),
                HairColor.values()[random.nextInt(HairColor.values().length)],
                BeardStyle.values()[random.nextInt(BeardStyle.values().length)],
                createRandomEquipment()
        );
    }
    
    private List<EquipmentItem> createRandomEquipment() {
        return List.of(
                EquipmentFactory.createItem(),
                EquipmentFactory.createItem()
        );
    }
    
    public Viking createViking(VikingEntity entity) {
        return new Viking(
                entity.name(),
                entity.age(),
                entity.heightCm(),
                entity.hairColor(),
                entity.beardStyle(),
                entity.equipment()
        );
    }
    
    public Viking updateViking(Viking old, VikingEntity newData) {
    return new Viking(
            newData.name() != null ? newData.name() : old.name(),
            newData.age() > 0 ? newData.age() : old.age(),
            newData.heightCm() > 0 ? newData.heightCm() : old.heightCm(),
            newData.hairColor() != null ? newData.hairColor() : old.hairColor(),
            newData.beardStyle() != null ? newData.beardStyle() : old.beardStyle(),
            newData.equipment() != null ? newData.equipment() : old.equipment()
    );
}
}
