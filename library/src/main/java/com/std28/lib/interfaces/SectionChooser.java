package com.std28.lib.interfaces;

import android.os.Bundle;

/**
 * Created by Mauricio Barcelo on 11/02/18.
 * email: mauricio.barcelo30@gmail.com
 */

public interface SectionChooser {

    void goToSection(int sectionId);

    void goToSection(int sectionId, Bundle args);

    void goToSection(int sectionId, boolean toBackStack);

    void goToSection(int sectionId, Bundle args, boolean toBackStack);

}
