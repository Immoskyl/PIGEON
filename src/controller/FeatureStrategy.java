package controller;


import display.ADisplayLanguage;

/**
 * Created by immoskyl on 11/12/16.
 */
public interface FeatureStrategy {

    void setDisplay(ADisplayLanguage display);
    void execute();
}
