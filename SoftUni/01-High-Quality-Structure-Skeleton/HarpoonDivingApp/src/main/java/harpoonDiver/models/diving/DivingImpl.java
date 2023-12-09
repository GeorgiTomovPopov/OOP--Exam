package harpoonDiver.models.diving;

import harpoonDiver.models.diver.Diver;
import harpoonDiver.models.divingSite.DivingSite;

import java.util.Collection;

public class DivingImpl implements Diving {
    @Override
    public void searching(DivingSite divingSite, Collection<Diver> divers) {
        for (Diver diver : divers) {
            while (diver.canDive()) {
                Collection<String> seaCreatures = divingSite.getSeaCreatures();
//                for (String seaCreature : seaCreatures) {
//                    diver.shoot();
//                    diver.getSeaCatch().getSeaCreatures().add(seaCreature);
//                    seaCreatures.remove(seaCreature);
//                    if (diver.getOxygen() <= 0) {
//                        break;
//                    }
//                }
                if (seaCreatures.isEmpty()) {
                    break;
                } else {
                    diver.shoot();
                    String seaCatch = seaCreatures.iterator().next();
                    diver.getSeaCatch().getSeaCreatures().add(seaCatch);
                    divingSite.getSeaCreatures().remove(seaCatch);
                }
            }
        }
    }
}
