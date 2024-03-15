package be.kdg.fill.views.gamemenu.addworld;

import be.kdg.fill.views.Presenter;
import be.kdg.fill.views.gamemenu.GameMenuPresenter;

public class AddWorldPresenter implements Presenter {
    
    private AddWorldView view;
    private GameMenuPresenter parent;

    public static final String SCREEN_NAME = "addworld";

    public AddWorldPresenter(AddWorldView addWorldView, GameMenuPresenter parent) 
    {
        this.view = addWorldView;
        this.parent = parent;
    }

    public void reset() 
    {
        // temp
    }


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
