package pt.up.fc.dcc.mediumeditor.client.models;

import com.google.gwt.core.client.JavaScriptObject;

import java.util.Map;

/**
 * JS Object for managing Medium Editor options
 *
 * @author José Carlos Paiva <code>josepaiva94@gmail.com</code>
 */
public class EditorOptions extends JavaScriptObject {

    protected EditorOptions() {
    }

    public final native void setActiveButtonClass(
            String activeButtonClass) /*-{
        this.activeButtonClass = activeButtonClass;
    }-*/;

    public final native String getActiveButtonClass() /*-{
        return this.activeButtonClass || 'medium-editor-button-active';
    }-*/;

    public final native void setButtonLabels(String buttonLabels) /*-{
        this.buttonLabels = buttonLabels;
    }-*/;

    public final native String getButtonLabels() /*-{
        return this.buttonLabels || false;
    }-*/;

    public final native void setDelay(int delay) /*-{
        this.delay = delay;
    }-*/;

    public final native int getDelay() /*-{
        return this.delay || 0;
    }-*/;

    public final native void setDisableReturn(boolean disableReturn) /*-{
        this.disableReturn = disableReturn;
    }-*/;

    public final native boolean isDisableReturn() /*-{
        return this.disableReturn || false;
    }-*/;

    public final native void setDisableDoubleReturn(boolean disableDoubleReturn) /*-{
        this.disableDoubleReturn = disableDoubleReturn;
    }-*/;

    public final native boolean isDisableDoubleReturn() /*-{
        return this.disableDoubleReturn || false;
    }-*/;

    public final native void setDisableExtraSpaces(boolean disableExtraSpaces) /*-{
        this.disableExtraSpaces = disableExtraSpaces;
    }-*/;

    public final native boolean isDisableExtraSpaces() /*-{
        return this.disableExtraSpaces || false;
    }-*/;

    public final native void setDisableEditing(boolean disableEditing) /*-{
        this.disableEditing = disableEditing;
    }-*/;

    public final native boolean isDisableEditing() /*-{
        return this.disableEditing || false;
    }-*/;

    public final native void setElementsContainer(JavaScriptObject elementsContainer) /*-{
        this.elementsContainer = elementsContainer;
    }-*/;

    public final native JavaScriptObject setElementsContainer() /*-{
        return this.elementsContainer || $doc.body;
    }-*/;

    public final native void setExtensions(Map<String, JavaScriptObject> extensions) /*-{
        this.extensions = extensions;
    }-*/;

    public final native Map<String, JavaScriptObject> getExtensions() /*-{
        return this.extensions || {};
    }-*/;

    public final native void setSpellcheck(boolean spellcheck) /*-{
        this.spellcheck = spellcheck;
    }-*/;

    public final native boolean isSpellcheck() /*-{
        return this.spellcheck || true;
    }-*/;

    public final native void setTargetBlank(boolean targetBlank) /*-{
        this.targetBlank = targetBlank;
    }-*/;

    public final native boolean isTargetBlank() /*-{
        return this.targetBlank || false;
    }-*/;

    public final native void setToolbar(ToolbarOptions toolbar) /*-{
        this.toolbar = toolbar;
    }-*/;

    public final native ToolbarOptions getToolbar() /*-{
        return this.toolbar || {};
    }-*/;

    public final native JavaScriptObject get(String name) /*-{
        return this[name];
    }-*/;

    public final native void set(String name, JavaScriptObject jsObject) /*-{
        this[name] = jsObject;
    }-*/;
}
