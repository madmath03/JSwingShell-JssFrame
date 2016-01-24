package jswingshell.jssframe.shell;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.Set;
import jswingshell.AbstractJssModel;
import jswingshell.IJssController;
import jswingshell.JssSimpleModel;
import jswingshell.action.IJssAction;
import jswingshell.jssframe.resources.LocaleChangeListener;
import jswingshell.jssframe.resources.ResourceUtils;

/**
 * A localized shell model.
 *
 * <p>
 * The localized model will automatically register any localized action it
 * contains to the shell.</p>
 *
 * @author Mathieu Brunot
 */
public class LocalizedJssModel extends JssSimpleModel implements Serializable {

    private static final long serialVersionUID = 1L;

    // #########################################################################
    /**
     * Contruct a shell model with no controller.
     * 
     * @see #setController(jswingshell.IJssController) 
     * @see AbstractJssModel#AbstractJssModel()
     * 
     * @since 1.4
     */
    protected LocalizedJssModel() {
        super();
    }

    /**
     * Contruct a shell model, with no controller, and the available commands.
     *
     * @param actions the available commands.
     *
     * @see #setController(jswingshell.IJssController)
     *
     * @since 1.4
     */
    protected LocalizedJssModel(Collection<IJssAction> actions) {
        super(actions);
    }

    public LocalizedJssModel(IJssController controller) {
        super(controller);
    }

    public LocalizedJssModel(IJssController controller, int initialCapacity) {
        super(controller, initialCapacity);
    }

    public LocalizedJssModel(IJssController controller, Collection<IJssAction> actions) {
        super(controller, actions);
    }

    public LocalizedJssModel(AbstractJssModel anotherModel) {
        super(anotherModel);
    }

    public LocalizedJssModel(IJssController anotherController, JssSimpleModel anotherModel) {
        super(anotherController, anotherModel);
    }

    // #########################################################################
    @Override
    public void clear() {
        for (IJssAction action : super.getAvailableActions()) {
            if (action instanceof LocaleChangeListener) {
                ResourceUtils.removeLocaleChangeListener((LocaleChangeListener) action);
            }
        }
        super.clear();
    }

    @Override
    public boolean add(IJssAction action) {
        boolean added = super.add(action);
        if (added && action instanceof LocaleChangeListener) {
            added &= ResourceUtils.addLocaleChangeListener((LocaleChangeListener) action);
        }
        return added;
    }

    @Override
    public boolean addAll(Collection<? extends IJssAction> actions) {
        boolean added = false;
        for (IJssAction action : actions) {
            if (action instanceof LocaleChangeListener) {
                added |= ResourceUtils.addLocaleChangeListener((LocaleChangeListener) action);
            }
        }
        return added | super.addAll(actions);
    }

    @Override
    public boolean remove(IJssAction action) {
        boolean removed = super.remove(action);
        if (removed && action instanceof LocaleChangeListener) {
            removed &= ResourceUtils.removeLocaleChangeListener((LocaleChangeListener) action);
        }
        return removed;
    }

    @Override
    public boolean removeAll(Collection<? extends IJssAction> actions) {
        boolean removed = false;
        for (IJssAction action : actions) {
            if (action instanceof LocaleChangeListener) {
                removed |= ResourceUtils.removeLocaleChangeListener((LocaleChangeListener) action);
            }
        }
        return removed | super.removeAll(actions);
    }

    @Override
    public boolean retainAll(Collection<? extends IJssAction> actions) {
        boolean retained = false;
        for (IJssAction action : super.getAvailableActions()) {
            if (action instanceof LocaleChangeListener && !getAvailableActions().contains(action)) {
                retained = ResourceUtils.removeLocaleChangeListener((LocaleChangeListener) action);
            }
        }
        return retained | super.retainAll(actions);
    }

    @Override
    public void setController(IJssController anotherController) {
        super.setController(anotherController);
    }

    public Set<IJssAction> getActions() {
        return Collections.unmodifiableSet(super.getAvailableActions());
    }

}
