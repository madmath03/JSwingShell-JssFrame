package jswingshell.jssframe.utils;

import jswingshell.IJssController;
import jswingshell.action.IJssAction;
import jswingshell.jssframe.JssFrame;
import jswingshell.jssframe.action.ClearAction;
import jswingshell.jssframe.action.CopyAction;
import jswingshell.jssframe.action.CutAction;
import jswingshell.jssframe.action.EchoAction;
import jswingshell.jssframe.action.ExitAction;
import jswingshell.jssframe.action.FullScreenAction;
import jswingshell.jssframe.action.HelpAction;
import jswingshell.jssframe.action.LevelAction;
import jswingshell.jssframe.action.LocaleAction;
import jswingshell.jssframe.action.OpenAction;
import jswingshell.jssframe.action.PasteAction;
import jswingshell.jssframe.action.RecordAction;
import jswingshell.jssframe.action.SaveScreenAction;
import jswingshell.jssframe.action.SelectAllAction;
import jswingshell.jssframe.action.SleepAction;
import jswingshell.jssframe.action.TimeAction;
import jswingshell.jssframe.action.ToggleToolbarAction;
import jswingshell.jssframe.action.ToggleToolbarIconsAction;
import jswingshell.jssframe.action.ToggleToolbarLargeIconsAction;
import jswingshell.jssframe.action.ToggleToolbarLevelCombo;
import jswingshell.jssframe.action.ToggleToolbarLocaleCombo;
import jswingshell.jssframe.action.ToggleToolbarNamesAction;
import jswingshell.jssframe.action.WaitAction;
import jswingshell.jssframe.action.ZoomAction;
import jswingshell.jssframe.resources.LocaleChangeListener;
import jswingshell.jssframe.resources.ResourceUtils;

/**
 * Action factory.
 *
 * @author Mathieu Brunot
 */
public class ActionFactory {

    private final JssFrame frame;

    public ActionFactory(JssFrame frame) {
        this.frame = frame;
    }

    public IJssAction getAction(String actionId) {
        IJssAction action = frame.getShellModel().getActionForCommandIdentifier(actionId);

        switch (actionId) {
            case OpenAction.DEFAULT_IDENTIFIER:
                OpenAction openAction;
                if (action == null) {
                    openAction = new OpenAction(frame.getjFileChooser(), frame, frame.getShellController());
                    action = openAction;
                } else {
                    openAction = (OpenAction) action;
                    openAction.setFileChooser(frame.getjFileChooser());
                    openAction.setParent(frame);
                    openAction.setDefaultShellController(frame.getShellController());
                }
                break;
            case SaveScreenAction.DEFAULT_IDENTIFIER:
                SaveScreenAction saveScreenAction;
                if (action == null) {
                    saveScreenAction = new SaveScreenAction(frame.getjFileChooser(), frame, frame, frame.getShellController(), null);
                    action = saveScreenAction;
                } else {
                    saveScreenAction = (SaveScreenAction) action;
                    saveScreenAction.setFileChooser(frame.getjFileChooser());
                    saveScreenAction.setParent(frame);
                    saveScreenAction.setPrintComponent(frame);
                    saveScreenAction.setDefaultShellController(frame.getShellController());
                }
                break;
            case ExitAction.DEFAULT_IDENTIFIER:
                ExitAction exitAction;
                if (action == null) {
                    exitAction = new ExitAction(frame.getShellController());
                    action = exitAction;
                } else {
                    exitAction = (ExitAction) action;
                    exitAction.setDefaultShellController(frame.getShellController());
                }
                break;
            case CutAction.DEFAULT_IDENTIFIER:
                CutAction cutAction;
                if (action == null) {
                    cutAction = new CutAction(frame.getShellController());
                    action = cutAction;
                } else {
                    cutAction = (CutAction) action;
                    cutAction.setDefaultShellController(frame.getShellController());
                }
                break;
            case CopyAction.DEFAULT_IDENTIFIER:
                CopyAction copyAction;
                if (action == null) {
                    copyAction = new CopyAction(frame.getShellController());
                    action = copyAction;
                } else {
                    copyAction = (CopyAction) action;
                    copyAction.setDefaultShellController(frame.getShellController());
                }
                break;
            case PasteAction.DEFAULT_IDENTIFIER:
                PasteAction pasteAction;
                if (action == null) {
                    pasteAction = new PasteAction(frame.getShellController());
                    action = pasteAction;
                } else {
                    pasteAction = (PasteAction) action;
                    pasteAction.setDefaultShellController(frame.getShellController());
                }
                break;
            case ClearAction.DEFAULT_IDENTIFIER:
                ClearAction clearAction;
                if (action == null) {
                    clearAction = new ClearAction(frame.getShellController());
                    action = clearAction;
                } else {
                    clearAction = (ClearAction) action;
                    clearAction.setDefaultShellController(frame.getShellController());
                }
                break;
            case SelectAllAction.DEFAULT_IDENTIFIER:
                SelectAllAction selectAllAction;
                if (action == null) {
                    selectAllAction = new SelectAllAction(frame.getShellController());
                    action = selectAllAction;
                } else {
                    selectAllAction = (SelectAllAction) action;
                    selectAllAction.setDefaultShellController(frame.getShellController());
                }
                break;
            case ToggleToolbarAction.DEFAULT_IDENTIFIER:
                ToggleToolbarAction toggleToolbarAction;
                if (action == null) {
                    toggleToolbarAction = new ToggleToolbarAction(frame.isDisplayToolbar(), frame, frame.getShellController(), null);
                    action = toggleToolbarAction;
                } else {
                    toggleToolbarAction = (ToggleToolbarAction) action;
                    toggleToolbarAction.setFrame(frame);
                    toggleToolbarAction.setDefaultShellController(frame.getShellController());
                }
                break;
            case ToggleToolbarIconsAction.DEFAULT_IDENTIFIER:
                ToggleToolbarIconsAction toggleToolbarIconsAction;
                if (action == null) {
                    toggleToolbarIconsAction = new ToggleToolbarIconsAction(frame.isDisplayToolbarButtonIcons(), frame, frame.getShellController(), null);
                    action = toggleToolbarIconsAction;
                } else {
                    toggleToolbarIconsAction = (ToggleToolbarIconsAction) action;
                    toggleToolbarIconsAction.setFrame(frame);
                    toggleToolbarIconsAction.setDefaultShellController(frame.getShellController());
                }
                break;
            case ToggleToolbarNamesAction.DEFAULT_IDENTIFIER:
                ToggleToolbarNamesAction toggleToolbarNamesAction;
                if (action == null) {
                    toggleToolbarNamesAction = new ToggleToolbarNamesAction(frame.isDisplayToolbarButtonNames(), frame, frame.getShellController(), null);
                    action = toggleToolbarNamesAction;
                } else {
                    toggleToolbarNamesAction = (ToggleToolbarNamesAction) action;
                    toggleToolbarNamesAction.setFrame(frame);
                    toggleToolbarNamesAction.setDefaultShellController(frame.getShellController());
                }
                break;
            case ToggleToolbarLargeIconsAction.DEFAULT_IDENTIFIER:
                ToggleToolbarLargeIconsAction toggleToolbarLargeIconsAction;
                if (action == null) {
                    toggleToolbarLargeIconsAction = new ToggleToolbarLargeIconsAction(frame.isDisplayToolbarButtonLargeIcons(), frame, frame.getShellController(), null);
                    action = toggleToolbarLargeIconsAction;
                } else {
                    toggleToolbarLargeIconsAction = (ToggleToolbarLargeIconsAction) action;
                    toggleToolbarLargeIconsAction.setFrame(frame);
                    toggleToolbarLargeIconsAction.setDefaultShellController(frame.getShellController());
                }
                break;
            case ToggleToolbarLevelCombo.DEFAULT_IDENTIFIER:
                ToggleToolbarLevelCombo toggleToolbarLevelCombo;
                if (action == null) {
                    toggleToolbarLevelCombo = new ToggleToolbarLevelCombo(frame.isDisplayToolbarLevelCombo(), frame, frame.getShellController(), null);
                    action = toggleToolbarLevelCombo;
                } else {
                    toggleToolbarLevelCombo = (ToggleToolbarLevelCombo) action;
                    toggleToolbarLevelCombo.setFrame(frame);
                    toggleToolbarLevelCombo.setDefaultShellController(frame.getShellController());
                }
                break;
            case ToggleToolbarLocaleCombo.DEFAULT_IDENTIFIER:
                ToggleToolbarLocaleCombo toggleToolbarLocaleCombo;
                if (action == null) {
                    toggleToolbarLocaleCombo = new ToggleToolbarLocaleCombo(frame.isDisplayToolbarLocaleCombo(), frame, frame.getShellController(), null);
                    action = toggleToolbarLocaleCombo;
                } else {
                    toggleToolbarLocaleCombo = (ToggleToolbarLocaleCombo) action;
                    toggleToolbarLocaleCombo.setFrame(frame);
                    toggleToolbarLocaleCombo.setDefaultShellController(frame.getShellController());
                }
                break;
            case ZoomAction.DEFAULT_IDENTIFIER:
                ZoomAction zoomAction;
                if (action == null) {
                    zoomAction = new ZoomAction(frame.getShellController());
                    action = zoomAction;
                } else {
                    zoomAction = (ZoomAction) action;
                    zoomAction.setDefaultShellController(frame.getShellController());
                }
                break;
            case FullScreenAction.DEFAULT_IDENTIFIER:
                FullScreenAction fullScreenAction;
                if (action == null) {
                    fullScreenAction = new FullScreenAction(false, frame, frame.getShellController(), null);
                    action = fullScreenAction;
                } else {
                    fullScreenAction = (FullScreenAction) action;
                    fullScreenAction.setFrame(frame);
                    fullScreenAction.setDefaultShellController(frame.getShellController());
                }
                break;
            case RecordAction.DEFAULT_IDENTIFIER:
                RecordAction recordAction;
                if (action == null) {
                    recordAction = new RecordAction(frame.getShellController());
                    action = recordAction;
                } else {
                    recordAction = (RecordAction) action;
                    recordAction.setDefaultShellController(frame.getShellController());
                }
                break;
            case HelpAction.DEFAULT_IDENTIFIER:
                HelpAction helpAction;
                if (action == null) {
                    helpAction = new HelpAction(frame.getShellController());
                    action = helpAction;
                } else {
                    helpAction = (HelpAction) action;
                    helpAction.setDefaultShellController(frame.getShellController());
                }
                break;
            case EchoAction.DEFAULT_IDENTIFIER:
                if (action == null) {
                    action = (EchoAction) Serialization.loadSerializedAction(actionId);
                    if (action == null) {
                        action = new EchoAction();
                    }
                }
                break;
            case TimeAction.DEFAULT_IDENTIFIER:
                if (action == null) {
                    action = new TimeAction();
                }
                break;
            case SleepAction.DEFAULT_IDENTIFIER:
                if (action == null) {
                    action = new SleepAction();
                }
                break;
            case WaitAction.DEFAULT_IDENTIFIER:
                if (action == null) {
                    action = new WaitAction();
                }
                break;
            case LevelAction.DEFAULT_IDENTIFIER:
                LevelAction levelComboAction;
                if (action == null) {
                    levelComboAction = (LevelAction) Serialization.loadSerializedAction(actionId);
                    if (levelComboAction == null) {
                        levelComboAction = new LevelAction(IJssController.PublicationLevel.values(), frame.getShellController());
                        levelComboAction.setSelectedItem(frame.getShellController().getPublicationLevel());
                    }
                    action = levelComboAction;
                } else {
                    levelComboAction = (LevelAction) action;
                    levelComboAction.setDefaultShellController(frame.getShellController());
                }
                break;
            case LocaleAction.DEFAULT_IDENTIFIER:
                LocaleAction localeAction;
                if (action == null) {
                    localeAction = new LocaleAction(ResourceUtils.getAvailableLocales(), frame.getShellController());
                    action = localeAction;
                } else {
                    localeAction = (LocaleAction) action;
                    localeAction.setDefaultShellController(frame.getShellController());
                }
                break;
            default:
                break;
        }

        // Add action to the shell
        if (action != null) {
            boolean added = frame.getShellModel().add(action);
            // If the action was not added to the model (already there?)
            if (!added && action instanceof LocaleChangeListener
                    && !ResourceUtils.containsLocaleChangeListener((LocaleChangeListener) action)) {
                ResourceUtils.addLocaleChangeListener((LocaleChangeListener) action);
            }
        }

        return action;
    }
}
