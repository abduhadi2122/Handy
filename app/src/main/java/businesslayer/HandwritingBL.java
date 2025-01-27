package businesslayer;

import android.graphics.Color;

/**
 * Created by Ian on 21/02/2016.
 * 
 * Business Layer for Handwriting functionality
 * Implements the "Business Logic" of the Handwriting functionality
 * HandwritingView.java calls these methods
 * 
 * FOR ITERATION 2: Look into separating the OnTouchEvent override found in HandwritingView.java
 * FOR ITERATION 2: Look into possibly implementing brush/eraser size BL methods for when they are implemented
 */
public class HandwritingBL {

    // Business Logic implementation of changing color
    // Returns int of color that has been parsed from the string newColor
    public int changeColor(String newColor) {
        return Color.parseColor(newColor);
    }

    // Business Logic implementation of determining whether user is in "eraser" mode or "pen" mode
    // If in eraser mode, eraseState is true, thus set color to white (as background as white)
    // Else, return previously set color (which was passed from HandwritingView.java)
    public int setErase(int selectedColor, boolean eraseState) {
        int returnColor = selectedColor;

        if(eraseState){
            returnColor = Color.parseColor("#ffffffff");
        }

        return returnColor;
    }
}
