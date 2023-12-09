package harpoonDiver.repositories;

import harpoonDiver.models.diver.Diver;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class DiverRepository implements Repository<Diver>{
    private Collection<Diver> divers;

    public DiverRepository() {
        this.divers = new ArrayList<>();
    }

    @Override
    public Collection<Diver> getCollection() {
        return Collections.unmodifiableCollection(divers);
    }

    @Override
    public void add(Diver diver) {
        divers.add(diver);
    }

    @Override
    public boolean remove(Diver diver) {
        return divers.remove(diver);
    }

    @Override
    public Diver byName(String name) {
        return divers.stream().filter(diver -> diver.getName().equals(name)).findFirst().orElse(null);
    }
}
