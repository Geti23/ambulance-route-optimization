package org.model;

public class ActionDrop extends Action {
    private Ambulance ambulance;
    private int at;
    private Patient p;

    public ActionDrop(Ambulance ambulance, int at, Patient patient) {
        this.ambulance = ambulance;
        this.at = at;
        this.p = patient;
    }

    @Override
    protected void applyEffects(CityMap cityMap) {
        ambulance.unload();
        p.unload();
    }

    @Override
    protected void checkPreconditions(CityMap cityMap) {
        if (!(ambulance.getNode() == at && cityMap.getContentAt(at).stream().anyMatch(c -> c instanceof Hospital)
                && ambulance.getPatient() == p))
            throw new IllegalStateException();
    }

    @Override
    public String toString() {
        return String.format("drop(A%d P%d @ N%d)", ambulance.getId(), p.getId(), at);
    }
}
