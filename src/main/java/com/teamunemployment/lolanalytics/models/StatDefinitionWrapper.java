package com.teamunemployment.lolanalytics.models;

import java.util.ArrayList;

/**
 * Created by jek40 on 16/02/2017.
 */
public class StatDefinitionWrapper {
    private ArrayList<StatDefinition> definitions;

    public void setDefinitions(ArrayList<StatDefinition> definitions) {
        this.definitions = definitions;
    }

    public ArrayList<StatDefinition> getDefinitions() {
        return definitions;
    }
}
