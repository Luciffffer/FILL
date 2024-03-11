package be.kdg.fill.views.admin.addcheckboxes;

import be.kdg.fill.views.Presenter;
import be.kdg.fill.views.ScreenManager;
import be.kdg.fill.views.admin.dimention.DimentionView;
import javafx.scene.layout.Region;

public class CheckBoxesPresenter implements Presenter {
    private CheckBoxesView view;
    private ScreenManager screenManager;

    public static final String SCREEN_NAME = "checkboxes";

    public CheckBoxesPresenter(CheckBoxesView view, ScreenManager screenManager) {
        this.view = view;
        this.screenManager = screenManager;
    }

    @Override
    public String getScreenName() {
        return SCREEN_NAME;
    }

    @Override
    public CheckBoxesView getView() {
        return view;
    }
}
