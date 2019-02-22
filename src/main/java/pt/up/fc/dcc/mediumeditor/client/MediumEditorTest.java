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

        mediumEditor.setHTML(
                "<p>My father’s family name being Pirrip, and my Christian n" +
                        "ame Philip, my infant tongue could make of both nam" +
                        "es nothing longer or more <mark data-global=\"benev" +
                        "olence\" data-intermediate=\"affection\" data-speci" +
                        "fic=\"desire\" data-preview=\"Benevolence > Affecti" +
                        "on > Desire\" class=\"emotion-highlight\">explicit<" +
                        "/mark> than Pip. So, I called myself Pip, and came " +
                        "to be called Pip.</p>" +
                "<p>I give Pirrip as my father’s family name, on the authori" +
                        "ty of his <mark data-global=\"safety\" data-interme" +
                        "diate=\"courage\" data-specific=\"extroversion\" da" +
                        "ta-preview=\"Safety > Courage > Extroversion\" clas" +
                        "s=\"emotion-highlight\">tombstone</mark> and my sis" +
                        "ter,—Mrs. Joe Gargery, who married the blacksmith. " +
                        "As I never saw my father or my mother, and never sa" +
                        "w any likeness of either of them (for their days we" +
                        "re long before the days of photographs), my first f" +
                        "ancies regarding what they were like were unreasona" +
                        "bly derived from their tombstones...</p>");

        RootPanel.get(EDITOR_CONTAINER_ID).add(mediumEditor);

        Button sayBtn = new Button("Say it!");
        sayBtn.addClickHandler((e) -> {
            Logger.getLogger("").severe(mediumEditor.getText());
        });

        RootPanel.get(EDITOR_CONTAINER_ID).add(sayBtn);

        mediumEditor.addValueChangeHandler((e) -> {
            Logger.getLogger("").severe("on value change");
        });
    }
}
