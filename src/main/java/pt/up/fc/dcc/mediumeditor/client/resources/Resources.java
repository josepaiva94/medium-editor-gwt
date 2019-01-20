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

    @Source("css/medium-editor.css")
    @CssResource.NotStrict
    CssResource mediumEditorCss();

    /* Rangy resources */
    @Source("js/rangy-core.min.js")
    TextResource rangyCoreJs();

    @Source("js/rangy-classapplier.min.js")
    TextResource rangyClassApplierJs();

    /* Themes CSS */
    /*@Source("css/themes/beagle.css")
    @CssResource.NotStrict
    CssResource beagleCss();

    @Source("css/themes/bootstrap.css")
    @CssResource.NotStrict
    CssResource bootstrapCss();

    @Source("css/themes/default.css")
    @CssResource.NotStrict
    CssResource defaultCss();

    @Source("css/themes/flat.css")
    @CssResource.NotStrict
    CssResource flatCss();

    @Source("css/themes/mani.css")
    @CssResource.NotStrict
    CssResource maniCss();

    @Source("css/themes/roman.css")
    @CssResource.NotStrict
    CssResource romanCss();

    @Source("css/themes/tim.css")
    @CssResource.NotStrict
    CssResource timCss();*/
}
