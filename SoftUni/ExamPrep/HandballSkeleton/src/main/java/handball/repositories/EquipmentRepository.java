package handball.repositories;

import handball.entities.equipment.Equipment;

import java.util.ArrayList;
import java.util.Collection;

public class EquipmentRepository implements Repository{

    private Collection<Equipment> equipments = new ArrayList<>();

    @Override
    public void add(Equipment equipment) {
        equipments.add(equipment);
    }

    @Override
    public boolean remove(Equipment equipment) {
        return equipments.remove(equipment);
    }

    @Override
    public Equipment findByType(String type) {
        for (Equipment eq :
                equipments) {
            if (eq.getClass().getSimpleName().equals(type)) {
                return eq;
            }
        }
        return null;
    }
}
