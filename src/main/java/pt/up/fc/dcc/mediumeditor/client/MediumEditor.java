package pt.up.fc.dcc.mediumeditor.client;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.ScriptInjector;
import com.google.gwt.dom.client.Element;
import com.google.gwt.event.logical.shared.HasValueChangeHandlers;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.Window;
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
                .replaceAll("(\r\n|\n)", "\n")
                .replaceAll("\n\n\n\n\n", "</p><p><br /></p><p>")
                .replaceAll("\n\n", "</p><p>")
                .replaceAll("\n", "<br />") + "</p>")
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
        String text = getText().trim();
        return text.length();
    }

    public int countWords() {
        String text = getText();
        if (text.endsWith("&nbsp;")) {
            text = text.substring(0, text.length() - 6);
        }
        text = text
                .replaceAll("[,.:;?!_\\[\\]()\"/*+%={}#$<>'«»\\\\|]", " ")
                .replaceAll("\\s+", " ")
                .trim();
        if (text.isEmpty())
            return 0;
        return text.split("\\s+").length;
    }

    private final native String getTextWithNewLines() /*-{
        var editor = this.@pt.up.fc.dcc.mediumeditor.client.MediumEditor::editorElement;
        var ps = editor.querySelectorAll('p');
        return Array.prototype.slice.call(ps)
            .map(function (p) {
                var html = p.innerHTML;
                var text = '';

                var tags;
                try { // direct childs only
                    tags = p.querySelectorAll(':scope > *');
                } catch (e) { // whatever, cross your fingers
                    tags = p.querySelectorAll('*');
                }

                for (var j = 0; j < tags.length; j++) {
                    var index = html.indexOf(tags[j].outerHTML);

                    text += html.substr(0, index);

                    html = html.slice(index);

                    var tagName = tags[j].tagName.toLowerCase();
                    if (tagName === 'br') {
                        text += '\n';
                    } else {
                        text += tags[j].innerText;
                    }

                    html = html.slice(tags[j].outerHTML.length);
                }

                return text + html;
            })
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
        editor.checkContentChanged(
            this.@pt.up.fc.dcc.mediumeditor.client.MediumEditor::editorElement
        );
    }-*/;
}
