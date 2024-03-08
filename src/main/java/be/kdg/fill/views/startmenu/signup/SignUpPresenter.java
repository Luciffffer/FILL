package be.kdg.fill.views.startmenu.signup;

import be.kdg.fill.models.core.User;
import be.kdg.fill.views.Presenter;
import be.kdg.fill.views.startmenu.StartMenuPresenter;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class SignUpPresenter implements Presenter {

    private SignUpView signUpView;
    private StartMenuPresenter parent;

    public static final String SCREEN_NAME = "signup";

    public SignUpPresenter(SignUpView signUpView, StartMenuPresenter startMenuPresenter) 
    {
        this.signUpView = signUpView;
        this.parent = startMenuPresenter;
        this.addEventHandlers();
    }

    private void addEventHandlers() 
    {
        signUpView.getSignUpButton().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                String username = String.valueOf((signUpView.getUsernameTextField().getText()));
                String password = String.valueOf((signUpView.getPasswordPasswordField().getText()));

                try {
                    User user = parent.getModel();

                    user.setUsername(username);
                    user.setPassword(password);
                    user.register();
                    parent.updateViewToGameMenu();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });

        signUpView.getCancelButton().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                updateViewToMainMenu();
            }
        });
    }

    private void updateViewToMainMenu() 
    {
        parent.getSubScreenManager().switchScreen("mainmenu");
    }

    @Override
    public SignUpView getView() 
    {
        return signUpView;
    }

    @Override
    public String getScreenName() 
    {
        return SCREEN_NAME;
    } 
}
