package ru.mephi.vikingdemo.service;

import org.springframework.stereotype.Service;
import ru.mephi.vikingdemo.model.Viking;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import ru.mephi.vikingdemo.controller.VikingListener;
import ru.mephi.vikingdemo.model.BeardStyle;
import ru.mephi.vikingdemo.model.EquipmentItem;
import ru.mephi.vikingdemo.model.HairColor;

@Service
public class VikingService {
    // каждый раз при изменении создаётся новая копия списка 
    private final CopyOnWriteArrayList<Viking> vikings = new CopyOnWriteArrayList<>();
    private final VikingFactory vikingFactory;
    private final VikingListener vikingListener;
    
    @Autowired
    public VikingService(VikingFactory vikingFactory, VikingListener vikingListener) {
        this.vikingFactory = vikingFactory;
        this.vikingListener = vikingListener;
    }
    
    public List<Viking> findAll() {
        return List.copyOf(vikings);
    }

    public Viking createRandomViking() {
        

        Viking viking = vikingFactory.createRandomViking();

        vikings.add(viking);
        vikingListener.onDataChanged();
        return viking;
    }
    
    public Viking addViking(String name, int age, int heightCm, 
                               HairColor hairColor, BeardStyle beardStyle,
                               List<EquipmentItem> equipment) {
        Viking viking = vikingFactory.createViking(name, age, heightCm, hairColor, beardStyle, equipment);
        vikings.add(viking);
        vikingListener.onDataChanged();
        return viking;
    }
    
    public void deleteViking(int index) {
        if (index >= 0 && index < vikings.size()) {
            vikings.remove(index);
            vikingListener.onDataChanged();
        } else {
            throw new IndexOutOfBoundsException("Viking not found at index: " + index);
        }
    }
    
    public Viking updateViking(int index, String name, int age, int heightCm,
                               HairColor hairColor, BeardStyle beardStyle,
                               List<EquipmentItem> equipment) {
        if (index >= 0 && index < vikings.size()) {
            Viking updatedViking = vikingFactory.createViking(name, age, heightCm, hairColor, beardStyle, equipment);
            vikings.set(index, updatedViking);
            vikingListener.onDataChanged();
            return updatedViking;
        } else {
            throw new IndexOutOfBoundsException("Viking not found at index: " + index);
        }
    }
}
