package ru.mephi.vikingdemo.service;

import org.springframework.stereotype.Service;
import ru.mephi.vikingdemo.model.Viking;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class VikingService {
    // каждый раз при изменении создаётся новая копия списка 
    private final CopyOnWriteArrayList<Viking> vikings = new CopyOnWriteArrayList<>();
    private final VikingFactory vikingFactory;
    @Autowired
    public VikingService(VikingFactory vikingFactory) {
        this.vikingFactory = vikingFactory;
    }
    
    public List<Viking> findAll() {
        return List.copyOf(vikings);
    }

    public Viking createRandomViking() {
        

        Viking viking = vikingFactory.createRandomViking();

        vikings.add(viking);
        return viking;
    }
    
    // НОВЫЙ МЕТОД: Создание викинга из готового объекта
    public Viking createViking(Viking viking) {
        vikings.add(viking);
        return viking;
    }
    
    // НОВЫЙ МЕТОД: Удаление викинга по индексу
    public void deleteViking(int index) {
        if (index >= 0 && index < vikings.size()) {
            vikings.remove(index);
        } else {
            throw new IndexOutOfBoundsException("Viking not found at index: " + index);
        }
    }
    
    // НОВЫЙ МЕТОД: Обновление викинга по индексу
    public Viking updateViking(int index, Viking updatedViking) {
        if (index >= 0 && index < vikings.size()) {
            vikings.set(index, updatedViking);
            return updatedViking;
        } else {
            throw new IndexOutOfBoundsException("Viking not found at index: " + index);
        }
    }
}
