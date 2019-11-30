package pt.up.fc.dcc.mediumeditor.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsonUtils;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.RootPanel;
import pt.up.fc.dcc.mediumeditor.client.models.EditorOptions;
import pt.up.fc.dcc.mediumeditor.client.models.ToolbarOptions;
import pt.up.fc.dcc.mediumeditor.client.resources.Resources;

import java.util.logging.Logger;

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
        toolbarOptions.setButtons(new String[]{"emotion-highlighter"});

        editorOptions.setToolbar(toolbarOptions);
        editorOptions.set("emotionHighlighter",
                JsonUtils.safeEval(Resources.INSTANCE.emotionsJson().getText()));

        MediumEditor mediumEditor = new MediumEditor(editorOptions);

        mediumEditor.setText(
                "My father’s family name being Pirrip, and my Christian name" +
                        " Philip, my infant tongue could make of both names " +
                        "nothing longer or more explicit than Pip. So, I cal" +
                        "led myself Pip, and came to be called Pip.\n\nI giv" +
                        "e Pirrip as my father’s family name, on the authori" +
                        "ty of his tombstone and my sister,—Mrs. Joe Gargery" +
                        ", who married the blacksmith.\nAs I never saw my fat" +
                        "her or my mother, and never saw any likeness of eit" +
                        "her of them (for their days were long before the da" +
                        "ys of photographs), my first fancies regarding what" +
                        " they were like were unreasonably derived from thei" +
                        "r tombstones..."
        );

        mediumEditor.setText(mediumEditor.getText());

        RootPanel.get(EDITOR_CONTAINER_ID).add(mediumEditor);

        Button sayBtn = new Button("Say it!");
        sayBtn.addClickHandler((e) -> {
            Logger.getLogger("").severe(mediumEditor.getText());
        });

        RootPanel.get(EDITOR_CONTAINER_ID).add(sayBtn);

        Button wcBtn = new Button("Word Count");
        wcBtn.addClickHandler((e) -> {
            Logger.getLogger("").severe("" + mediumEditor.countWords());
        });

        RootPanel.get(EDITOR_CONTAINER_ID).add(wcBtn);

        mediumEditor.addValueChangeHandler((e) -> {
            Logger.getLogger("").severe("on value change");
        });
    }
}
