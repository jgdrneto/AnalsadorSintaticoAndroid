package grupos.g15;

import android.graphics.Point;
import android.view.View;

import com.anysoftkeyboard.keyboards.Keyboard;

public interface PositionCalculator {
    Point calculatePositionForPreview(Keyboard.Key key, View keyboardView, PreviewPopupTheme theme, int[] windowOffset);
}
