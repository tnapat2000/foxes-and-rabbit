package io.muic.ooc.fab.observer;

import io.muic.ooc.fab.Field;
import io.muic.ooc.fab.view.SimulatorView;

public class SimulatorViewObserver extends Observer{

    SimulatorView simulatorView;

    public SimulatorViewObserver(SimulatorView simulatorView){
        this.simulatorView = simulatorView;
    }

    @Override
    public void update(int step, Field field) {
        simulatorView.showStatus(step,field);
    }
}
