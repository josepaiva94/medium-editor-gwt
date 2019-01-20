package pt.up.fc.dcc.mediumeditor.client.models;

import com.google.gwt.core.client.JavaScriptObject;

/**
 * JS Object for managing Medium Editor toolbar options
 *
 * @author José Carlos Paiva <code>josepaiva94@gmail.com</code>
 */
public class ToolbarOptions extends JavaScriptObject {

    protected ToolbarOptions() {
    }

    public final native void setAllowMultiParagraphSelection(
            boolean allowMultiParagraphSelection) /*-{
        this.allowMultiParagraphSelection = allowMultiParagraphSelection;
    }-*/;

    public final native boolean getAllowMultiParagraphSelection() /*-{
        return this.allowMultiParagraphSelection || true;
    }-*/;

    public final native void setButtons(String[] buttons) /*-{
        this.buttons = buttons;
    }-*/;

    public final native String[] getButtons() /*-{
        return this.buttons || ['bold', 'italic', 'underline', 'anchor', 'h2', 'h3', 'quote'];
    }-*/;

    public final native void setDiffLeft(int diffLeft) /*-{
        this.diffLeft = diffLeft;
    }-*/;

    public final native int getDiffLeft() /*-{
        return this.diffLeft || 0;
    }-*/;

    public final native void setDiffTop(int diffTop) /*-{
        this.diffTop = diffTop;
    }-*/;

    public final native int getDiffTop() /*-{
        return this.diffTop || -10;
    }-*/;

    public final native void setFirstButtonClass(String firstButtonClass) /*-{
        this.firstButtonClass = firstButtonClass;
    }-*/;

    public final native String getFirstButtonClass() /*-{
        return this.firstButtonClass || 'medium-editor-button-first';
    }-*/;

    public final native void setLastButtonClass(String lastButtonClass) /*-{
        this.lastButtonClass = lastButtonClass;
    }-*/;

    public final native String getLastButtonClass() /*-{
        return this.lastButtonClass || 'medium-editor-button-last';
    }-*/;

    public final native void setRelativeContainer(
            JavaScriptObject relativeContainer) /*-{
        this.relativeContainer = relativeContainer;
    }-*/;

    public final native JavaScriptObject getRelativeContainer() /*-{
        return this.relativeContainer || null;
    }-*/;

    public final native void setStandardizeSelectionStart(
            boolean standardizeSelectionStart) /*-{
        this.standardizeSelectionStart = standardizeSelectionStart;
    }-*/;

    public final native boolean getStandardizeSelectionStart() /*-{
        return this.standardizeSelectionStart || false;
    }-*/;

    public final native void setStatic(boolean s) /*-{
        this['static'] = s;
    }-*/;

    public final native boolean getStatic() /*-{
        return this['static'] || false;
    }-*/;

    public final native void setAlign(String align) /*-{
        this.align = align;
    }-*/;

    public final native String getAlign() /*-{
        return this.align || 'center';
    }-*/;

    public final native void setSticky(boolean sticky) /*-{
        this.sticky = sticky;
    }-*/;

    public final native boolean getSticky() /*-{
        return this.sticky || false;
    }-*/;

    public final native void setUpdateOnEmptySelection(
            boolean updateOnEmptySelection) /*-{
        this.updateOnEmptySelection = updateOnEmptySelection;
    }-*/;

    public final native boolean getUpdateOnEmptySelection() /*-{
        return this.updateOnEmptySelection || false;
    }-*/;
}
