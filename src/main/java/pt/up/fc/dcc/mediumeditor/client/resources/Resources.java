package pt.up.fc.dcc.mediumeditor.client.resources;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.resources.client.TextResource;

/**
 * Medium Editor resources
 *
 * @author José Carlos Paiva <code>josepaiva94@gmail.com</code>
 */
public interface Resources extends ClientBundle {
    Resources INSTANCE = GWT.create(Resources.class);

    /* Medium Editor JS */
    @Source("js/medium-editor.min.js")
    TextResource mediumEditorJs();

    @Source("css/medium-editor.min.gss")
    @CssResource.NotStrict
    CssResource mediumEditorCss();

    /* Rangy resources */
    @Source("js/rangy-core.min.js")
    TextResource rangyCoreJs();

    @Source("js/rangy-classapplier.min.js")
    TextResource rangyClassApplierJs();

    /* JSON configs */
    @Source("emotions.json")
    TextResource emotionsJson();
}
