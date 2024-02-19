package be.kdg.fill.views.compontents;

import be.kdg.fill.FillApplication;

public class PrimaryButton extends FillButton {
    
    public PrimaryButton(String text) 
    {
        super(text, "primary-btn-label", FillApplication.class.getResource("images/primary-btn-side.png").toExternalForm());
    }

}
