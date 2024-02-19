package be.kdg.fill.views.compontents;

import be.kdg.fill.FillApplication;

public class SecondaryButton extends FillButton {
        
    public SecondaryButton(String text) 
    {
        super(text, "secondary-btn-label", FillApplication.class.getResource("images/secondary-btn-side.png").toExternalForm());
    }

}
