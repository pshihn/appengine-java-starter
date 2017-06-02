package ai.usher.model;

import java.io.Serializable;

import com.googlecode.objectify.Objectify;

import ai.usher.OfyHelper;

public abstract class ModelObject implements Serializable {
    private static final long serialVersionUID = 1L;

    protected static Objectify ofy() {
        return OfyHelper.$();
    }
}
