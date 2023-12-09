package harpoonDiver.repositories;

import harpoonDiver.models.divingSite.DivingSite;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class DivingSiteRepository implements Repository<DivingSite>{

    private Collection<DivingSite> sites;

    public DivingSiteRepository() {
        this.sites = new ArrayList<>();
    }

    @Override
    public Collection<DivingSite> getCollection() {
        return Collections.unmodifiableCollection(sites);
    }

    @Override
    public void add(DivingSite site) {
        sites.add(site);
    }

    @Override
    public boolean remove(DivingSite site) {
        return sites.remove(site);
    }

    @Override
    public DivingSite byName(String name) {
        return sites.stream().filter(divingSite -> divingSite.getName().equals(name)).findFirst().orElse(null);
    }
}
