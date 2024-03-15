package be.kdg.fill.views.gamemenu.addworld;

import be.kdg.fill.views.Presenter;

public class AddWorldPresenter implements Presenter {
    
    private AddWorldView view;

    public static final String SCREEN_NAME = "addworld";


    // GETTERS

    @Override
    public AddWorldView getView() {
        return this.view;
    }

    @Override
    public String getScreenName() {
        return SCREEN_NAME;
    }
}
