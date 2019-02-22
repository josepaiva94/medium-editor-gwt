package pt.up.fc.dcc.mediumeditor.client;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.ScriptInjector;
import com.google.gwt.dom.client.Element;
import com.google.gwt.event.logical.shared.HasValueChangeHandlers;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.*;
import pt.up.fc.dcc.mediumeditor.client.models.EditorOptions;
import pt.up.fc.dcc.mediumeditor.client.resources.Resources;

import java.util.ArrayList;
import java.util.List;

/**
 * A GWT widget for Medium Editor clone
 *
 * @see <a href="https://github.com/josepaiva94/medium-editor">MediumEditor</a>
 * @author José Carlos Paiva <code>josepaiva94@gmail.com</code>
 */
public class MediumEditor extends Composite
        implements HasHTML, RequiresResize, HasValueChangeHandlers<String> {
    private static final String EDITOR_CSS_NAME = "editable";

    private static boolean loaded = false;

    private Element editorElement;

    private JavaScriptObject editor;

    private String selector;
    private EditorOptions options;

    private List<ValueChangeHandler<String>> valueChangeHandlers = new ArrayList<>();

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
            editor = init(editorElement, selector, options);
    }

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

            editor = init(editorElement, selector, options);
        } else
            editor = init(editorElement, selector, options);

        attachValueChangeHandler(editorElement);

        loaded = true;
    }

    private final native JavaScriptObject init(
            Element element,
            String selector, EditorOptions options) /*-{

        var editor = $wnd.MediumEditor.getEditorFromElement(element);
        if (editor)
            return editor;

        return new $wnd.MediumEditor(selector, options);
    }-*/;

    private final native void attachValueChangeHandler(
            Element element) /*-{

        var trigger = this.@pt.up.fc.dcc.mediumeditor.client.MediumEditor::triggerOnValueChange().bind(this);

        // Options for the observer (which mutations to observe)
        var config = { attributes: true, childList: true, subtree: true };

        // Create an observer instance linked to the callback function
        var observer = new MutationObserver(trigger);

        // Start observing the target node for configured mutations
        observer.observe(element, config);
    }-*/;

    @Override
    public String getHTML() {
        return editorElement.getInnerHTML();
    }

    @Override
    public void setHTML(String html) {
        editorElement.setInnerHTML(html);

        if (loaded)
            checkContentChanged();
    }

    @Override
    public String getText() {
        return getTextWithNewLines();
    }

    @Override
    public void setText(String text) {
        String html = ("<p>" + text
                .replaceAll("\\R\\R\\R\\R\\R", "</p><p><br /></p><p>")
                .replaceAll("\\R\\R", "</p><p>")
                .replaceAll("\\R", "<br />") + "</p>")
                .replaceAll("<p></p>", "<p><br /></p>");

        editorElement.setInnerHTML(html);

        if (loaded)
            checkContentChanged();
    }

    @Override
    public void onResize() {

    }

    @Override
    public HandlerRegistration addValueChangeHandler(ValueChangeHandler<String> handler) {
        int index = valueChangeHandlers.size();
        valueChangeHandlers.add(handler);
        return () -> valueChangeHandlers.remove(index);
    }

    public int countCharacters() {
        String text = getText();
        return text.length();
    }

    public int countWords() {
        String text = getText();
        return text.trim().replaceAll("\\s+", " ").split("\\s").length;
    }

    private final native String getTextWithNewLines() /*-{

        var editor = this.@pt.up.fc.dcc.mediumeditor.client.MediumEditor::editorElement;
        var ps = editor.querySelectorAll('p');
        return Array.prototype.slice.call(ps)
            .map(function (p) { return p.innerText.replace(/<br\s*[\/]?>/gi, '\n'); })
            .join('\n\n');
    }-*/;

    private void triggerOnValueChange() {
        String text = getText();
        for (ValueChangeHandler<String> handler: valueChangeHandlers) {
            handler.onValueChange(new ValueChangeEvent<String>(text) {});
        }
    }

    public final native void checkContentChanged() /*-{
        var editor = this.@pt.up.fc.dcc.mediumeditor.client.MediumEditor::editor;
        editor.checkContentChanged(this.@pt.up.fc.dcc.mediumeditor.client.MediumEditor::editorElement);
    }-*/;
}
