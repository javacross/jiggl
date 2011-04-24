//
// $Id$

package com.threerings.jiggl.view;

import java.util.ArrayList;
import java.util.List;

/**
 * Coordinates the display and animation of a collection of {@link Viz} instances.
 */
public abstract class View
{
    /**
     * Adds the supplied viz to this view. Returns the added viz for convenient chaining.
     */
    public <T extends Viz> T add (T viz)
    {
        viz.onAdd(this);
        _vizs.add(viz);
        return viz;
    }

    /**
     * Removes the specified viz from this view.
     */
    public void remove (Viz viz)
    {
        _vizs.remove(viz);
        viz.onRemove(this);
    }

    /**
     * Removes all registered visibles from this view.
     */
    public void clear ()
    {
        for (Viz v : _vizs) {
            v.onRemove(this);
        }
        _vizs.clear();
    }

    /**
     * Instructs this view to render its visibles.
     */
    public void render ()
    {
        for (int ii = 0, ll = _vizs.size(); ii < ll; ii++) {
            _vizs.get(ii).render(this);
        }
    }

    /** A list of our active visibles. */
    protected List<Viz> _vizs = new ArrayList<Viz>();
}