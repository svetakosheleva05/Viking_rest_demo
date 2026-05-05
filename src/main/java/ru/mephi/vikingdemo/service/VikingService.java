package ru.mephi.vikingdemo.service;

import org.springframework.stereotype.Service;
import ru.mephi.vikingdemo.model.Viking;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import org.springframework.context.ApplicationEventPublisher;
import ru.mephi.vikingdemo.controller.VikingDataChangedEvent;
import ru.mephi.vikingdemo.model.BeardStyle;
import ru.mephi.vikingdemo.model.EquipmentItem;
import ru.mephi.vikingdemo.model.HairColor;
import ru.mephi.vikingdemo.model.VikingEntity;

@Service
public class VikingService {
    // каждый раз при изменении создаётся новая копия списка 
    private final CopyOnWriteArrayList<Viking> vikings = new CopyOnWriteArrayList<>();
    private final VikingFactory vikingFactory;
    private final ApplicationEventPublisher eventPublisher;
    
    public VikingService(VikingFactory vikingFactory, ApplicationEventPublisher eventPublisher) {
        this.vikingFactory = vikingFactory;
        this.eventPublisher = eventPublisher;
    }
    
    public List<Viking> findAll() {
        return List.copyOf(vikings);
    }

    public Viking createRandomViking() {
        Viking viking = vikingFactory.createRandomViking();
        vikings.add(viking);
        eventPublisher.publishEvent(new VikingDataChangedEvent(this));
        return viking;
    }
    
    public Viking addViking(VikingEntity entity) {
        Viking viking = vikingFactory.createViking(entity);
        vikings.add(viking);
        eventPublisher.publishEvent(new VikingDataChangedEvent(this));
        return viking;
    }
    
    public void deleteViking(int index) {
        if (index >= 0 && index < vikings.size()) {
            vikings.remove(index);
            eventPublisher.publishEvent(new VikingDataChangedEvent(this));
        } else {
            throw new IndexOutOfBoundsException("Viking not found at index: " + index);
        }
    }
    
    public Viking updateViking(int index, VikingEntity entity) {
        if (index >= 0 && index < vikings.size()) {
            Viking old = vikings.get(index);
            Viking updatedViking = vikingFactory.updateViking(old, entity);
            
            vikings.set(index, updatedViking);
            eventPublisher.publishEvent(new VikingDataChangedEvent(this));
            return updatedViking;
        } else {
            throw new IndexOutOfBoundsException("Viking not found at index: " + index);
        }
    }

    public void testAdd() {
        for (int i = 0; i <= 1; i++) {
            createRandomViking();
        }
    }
}
