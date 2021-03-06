package jswingshell.jssframe.resources;

import java.awt.Image;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JComponent;

/**
 * Common resource management utilities.
 *
 * @author Mathieu Brunot
 */
public final class ResourceUtils {

    private ResourceUtils() {
    }

    // #########################################################################
    /**
     * Logger.
     */
    private static final Logger LOGGER = Logger.getLogger(ResourceUtils.class.getName());

    private static final String SMALL_ICON_PATH = "icons/16x16/";

    private static final String LARGE_ICON_PATH = "icons/32x32/";

    // #########################################################################
    /**
     * Locale change notifier for {@link LocaleResourceBundleWrapper}s.
     */
    private static final LocaleChangeNotifier RESOURCE_BUNDLE_LOCALE_CHANGE_NOTIFIER = new LocaleChangeNotifier(ResourceUtils.class, false);
    /**
     * Locale change notifier for beans that support internationalization.
     */
    private static final LocaleChangeNotifier BEANS_LOCALE_CHANGE_NOTIFIER = new LocaleChangeNotifier(ResourceUtils.class, false);

    /**
     * Application's available locales.
     */
    private static final Locale[] APPLICATION_AVAILABLE_LOCALES = {Locale.FRENCH, Locale.ENGLISH};

    /**
     * Returns an array of all installed locales.
     *
     * <p>
     * The returned array represents the union of locales supported by the
     * application.</p>
     *
     * @return An array of installed locales.
     */
    public static Locale[] getAvailableLocales() {
        // If application did not define its available locales
        if (APPLICATION_AVAILABLE_LOCALES == null || APPLICATION_AVAILABLE_LOCALES.length == 0) {
            // Use the JVM available locales
            return Locale.getAvailableLocales();
        } else {
            return APPLICATION_AVAILABLE_LOCALES;
        }
    }

    /**
     * Gets the current value of the default locale for this instance of the
     * application.
     *
     * <p>
     * The Java Virtual Machine sets the default locale during startup based on
     * the host environment. It is used by many locale-sensitive methods if no
     * locale is explicitly specified. It can be changed using the
     * {@link #setDefault(java.util.Locale) setDefault} method.</p>
     *
     * @return the default locale for this instance of the application
     */
    public static Locale getLocale() {
        return Locale.getDefault();
    }

    /**
     * Sets the default locale for this instance of the application and alert
     * all registered {@code LocaleChangeListener}s.
     *
     * @param newLocale the new default locale
     *
     * @throws SecurityException if a security manager exists and its
     * {@code checkPermission} method doesn't allow the operation.
     * @throws NullPointerException if {@code newLocale} is {@code null}
     */
    public static void setLocale(Locale newLocale) {
        Locale oldLocale = getLocale();
        Locale.setDefault(newLocale);
        // First alert resource bundle wrappers to reload of resource bundles
        RESOURCE_BUNDLE_LOCALE_CHANGE_NOTIFIER.fireLocaleChange(oldLocale, newLocale);
        // Then alert beans that support internationalization
        BEANS_LOCALE_CHANGE_NOTIFIER.fireLocaleChange(oldLocale, newLocale);
    }

    /**
     * Add a {@code LocaleChangeListener} to the listener list.
     *
     * @param listener The {@code LocaleChangeListener} to be added
     *
     * @return {@code true} (as specified by {@link Collection#add})
     */
    public static boolean addLocaleChangeListener(LocaleChangeListener listener) {
        return BEANS_LOCALE_CHANGE_NOTIFIER.addLocaleChangeListener(listener);
    }

    /**
     * Remove a {@code LocaleChangeListener} from the listener list.
     *
     * @param listener The {@code LocaleChangeListener} to be removed
     *
     * @return {@code true} if this {@code LocaleChangeNotifier} contained the
     * {@code LocaleChangeListener}
     */
    public static boolean removeLocaleChangeListener(LocaleChangeListener listener) {
        return BEANS_LOCALE_CHANGE_NOTIFIER.removeLocaleChangeListener(listener);
    }

    /**
     * Is a {@code LocaleChangeListener} in the listener list.
     *
     * @param listener The {@code LocaleChangeListener} to be test
     *
     * @return {@code true} if this {@code LocaleChangeNotifier} contains the
     * {@code LocaleChangeListener}
     */
    public static boolean containsLocaleChangeListener(LocaleChangeListener listener) {
        return BEANS_LOCALE_CHANGE_NOTIFIER.containsLocaleChangeListener(listener);
    }

    // #########################################################################
    private static LocaleResourceBundleWrapper localeResourceBundleManager;

    protected static LocaleResourceBundleWrapper getLocaleResourceBundle() {
        if (localeResourceBundleManager == null) {
            localeResourceBundleManager = new LocaleResourceBundleWrapper("jswingshell.jssframe.resources.i8n.jssframe");
            RESOURCE_BUNDLE_LOCALE_CHANGE_NOTIFIER.addLocaleChangeListener(localeResourceBundleManager);
        }
        return localeResourceBundleManager;
    }

    public static ResourceBundle getResourceBundle() {
        return getLocaleResourceBundle().getResourceBundle();
    }

    public static String getMessage(String key, Object... arguments) throws MissingResourceException {
        return getLocaleResourceBundle().getMessage(key, arguments);
    }

    public static int getMnemonic(String key) throws MissingResourceException {
        return getLocaleResourceBundle().getMnemonic(key);
    }

    public static int getDisplayedMnemonicIndex(String key) throws MissingResourceException {
        return getLocaleResourceBundle().getDisplayedMnemonicIndex(key);
    }

    public static void setTextAndMnemonic(JComponent comp, String key, Object... arguments) throws MissingResourceException {
        getLocaleResourceBundle().setTextAndMnemonic(comp, key, arguments);
    }

    public static void setTextAndMnemonic(Action action, String key, Object... arguments) throws MissingResourceException {
        getLocaleResourceBundle().setTextAndMnemonic(action, key, arguments);
    }

    // #########################################################################
    /**
     * Returns an ImageIcon, or null if the path was invalid.
     *
     * <p>
     * Defines the path to the file depending on the size requested (either
     * small or large).</p>
     *
     * @param fileName the file name of the icon
     * @param description the icon image description
     * @param largeSize Use a large size icon?
     * @return an ImageIcon, or null if the path was invalid.
     */
    public static ImageIcon createImageIcon(String fileName,
            String description, boolean largeSize) {
        String path = (largeSize ? LARGE_ICON_PATH : SMALL_ICON_PATH) + fileName;
        java.net.URL imgURL = ResourceUtils.class.getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL, description);
        } else {
            LOGGER.log(Level.WARNING, "Couldn''t find file: {0}", fileName);
            return null;
        }
    }

    /**
     * Returns an ImageIcon, or null if the path was invalid.
     *
     * @param fileName the file name of the icon
     * @param description the icon image description
     * @return an ImageIcon, or null if the path was invalid.
     */
    public static ImageIcon createImageIcon(String fileName,
            String description) {
        return createImageIcon(fileName, description, false);
    }

    /**
     * Returns a list of image icons, or null if the path was invalid.
     *
     * @param fileName the file name of the images
     * @param description the images description
     * @return a list of image icons, or null if the path was invalid.
     */
    public static List<Image> createImages(String fileName,
            String description) {
        ImageIcon icon = createImageIcon(fileName, description, true);
        if (icon != null) {
            List<Image> images = new ArrayList<>(2);
            images.add(icon.getImage());
            icon = createImageIcon(fileName, description, false);
            images.add(icon.getImage());
            return images;
        } else {
            return null;
        }
    }

}
