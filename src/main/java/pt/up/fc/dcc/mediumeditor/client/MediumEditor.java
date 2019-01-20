package pt.up.fc.dcc.mediumeditor.client;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.ScriptInjector;
import com.google.gwt.dom.client.Element;
import com.google.gwt.user.client.ui.*;
import pt.up.fc.dcc.mediumeditor.client.models.EditorOptions;
import pt.up.fc.dcc.mediumeditor.client.resources.Resources;

/**
 * A GWT widget for Medium Editor clone
 *
 * @see <a href="https://github.com/josepaiva94/medium-editor">MediumEditor</a>
 * @author José Carlos Paiva <code>josepaiva94@gmail.com</code>
 */
public class MediumEditor extends Composite implements HasHTML, RequiresResize {
    private static final String EDITOR_CSS_NAME = "editable";

    private static boolean loaded = false;

    private Element editorElement;

    private JavaScriptObject editor;

    private String selector;
    private EditorOptions options;

    public MediumEditor(EditorOptions options) {
        this(null, options);
    }

    public MediumEditor(String selector, EditorOptions options) {
        this.selector = (selector == null ? "." : selector + " .") + EDITOR_CSS_NAME;
        this.options = options;

        HTMLPanel editable = new HTMLPanel("");
        editable.getElement().addClassName(EDITOR_CSS_NAME);

        initWidget(editable);

        editorElement = editable.getElement();

        if (loaded)
            editor = init(selector, options);

        /*loadTheme(theme);*/
    }

    /*private void loadTheme(Theme theme) {

        switch (theme) {

            case BEAGLE:
                Resources.INSTANCE.beagleCss().ensureInjected();
                break;
            case BOOTSTRAP:
                Resources.INSTANCE.bootstrapCss().ensureInjected();
                break;
            case FLAT:
                Resources.INSTANCE.flatCss().ensureInjected();
                break;
            case MANI:
                Resources.INSTANCE.maniCss().ensureInjected();
                break;
            case ROMAN:
                Resources.INSTANCE.romanCss().ensureInjected();
                break;
            case TIM:
                Resources.INSTANCE.timCss().ensureInjected();
                break;
            default:
                Resources.INSTANCE.defaultCss().ensureInjected();
                break;
        }
    }*/

    @Override
    protected void onLoad() {
        super.onLoad();

        if (!loaded) {
            Resources.INSTANCE.mediumEditorCss().ensureInjected();

            ScriptInjector
                    .fromString(Resources.INSTANCE.mediumEditorJs().getText())
                    .setWindow(ScriptInjector.TOP_WINDOW)
                    .setRemoveTag(false)
                    .inject();

            ScriptInjector
                    .fromString(Resources.INSTANCE.rangyCoreJs().getText())
                    .setWindow(ScriptInjector.TOP_WINDOW)
                    .setRemoveTag(false)
                    .inject();

            ScriptInjector
                    .fromString(Resources.INSTANCE.rangyClassApplierJs().getText())
                    .setWindow(ScriptInjector.TOP_WINDOW)
                    .setRemoveTag(false)
                    .inject();

            editor = init(selector, options);
        }

        loaded = true;
    }

    private final native JavaScriptObject init(
            String selector, EditorOptions options) /*-{

        return new $wnd.MediumEditor(selector, options);
    }-*/;

    @Override
    public String getHTML() {
        return editorElement.getInnerHTML();
    }

    @Override
    public void setHTML(String html) {
        editorElement.setInnerHTML(html);
    }

    @Override
    public String getText() {
        return editorElement.getInnerText();
    }

    @Override
    public void setText(String text) {
        editorElement.setInnerText(text);
    }

    @Override
    public void onResize() {

    }
}
