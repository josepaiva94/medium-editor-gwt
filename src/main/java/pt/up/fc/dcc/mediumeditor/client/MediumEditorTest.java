package pt.up.fc.dcc.mediumeditor.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.RootPanel;
import pt.up.fc.dcc.mediumeditor.client.models.EditorOptions;
import pt.up.fc.dcc.mediumeditor.client.models.ToolbarOptions;

/**
 * Entry point for testing Medium Editor
 *
 * @author José Carlos Paiva <code>josepaiva94@gmail.com</code>
 */
public class MediumEditorTest implements EntryPoint {
    private static final String EDITOR_CONTAINER_ID = "editorContainer";

    @Override
    public void onModuleLoad() {

        EditorOptions editorOptions = JavaScriptObject.createObject().cast();

        ToolbarOptions toolbarOptions = JavaScriptObject.createObject().cast();
        toolbarOptions.setButtons(new String[] { "emotion-highlighter" });

        editorOptions.setToolbar(toolbarOptions);

        MediumEditor mediumEditor = new MediumEditor(editorOptions);

        //mediumEditor.setHTML();

        RootPanel.get(EDITOR_CONTAINER_ID).add(mediumEditor);
    }
}
