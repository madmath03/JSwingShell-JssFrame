package jswingshell.jssframe.shell;

import jswingshell.JssSimpleModel;
import jswingshell.gui.JssTextArea;
import jswingshell.gui.JssTextAreaController;

/**
 * A shell controller with a localized model.
 *
 * <p>
 * The localized model must be set separately through
 * {@link #setModel(jswingshell.jssframe.shell.LocalizedJssModel) }.</p>
 *
 * <p>
 * The localized model will automatically register any localized action it
 * contains to the shell.</p>
 *
 * @author Mathieu Brunot
 */
public final class LocalizedJssTextAreaController extends JssTextAreaController {

    public LocalizedJssTextAreaController(JssTextArea view) {
        super(view, null);
    }

    public LocalizedJssTextAreaController(String text) {
        super((JssSimpleModel) null, text);
    }

    public void setModel(LocalizedJssModel anotherModel) {
        super.setModel(anotherModel);
    }

    @Override
    public LocalizedJssModel getModel() {
        return (LocalizedJssModel) super.getModel();
    }

}
