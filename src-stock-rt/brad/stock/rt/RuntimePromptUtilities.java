package brad.stock.rt;

import brad.util.sys.PromptUtilities;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.PrintWriter;
import java.io.StringWriter;

class RuntimePromptUtilities implements PromptUtilities {

    private String defaultStyleSheet;

    {
        defaultStyleSheet = getClass().getResource("style.css").toExternalForm();
    }

    RuntimePromptUtilities(String defaultStyleSheet) {
        super();

        this.defaultStyleSheet = defaultStyleSheet;
    }

    @Override
    public void inform(String title, String message) {
        Stage stage = new Stage(StageStyle.UTILITY);

        Button button = new Button("OK");
        button.setOnAction(event -> stage.close());
        button.setMinWidth(80);

        Region hSpacer1 = new Region();
        HBox.setHgrow(hSpacer1, Priority.ALWAYS);
        Region hSpacer2 = new Region();
        HBox.setHgrow(hSpacer2, Priority.ALWAYS);

        final HBox actionPane = new HBox(10, hSpacer1, button, hSpacer2);
        actionPane.setPadding(new Insets(10, 0, 0, 0));

        Label label = new Label(message);
        label.setWrapText(true);
        label.setFont(Font.font(14));

        final BorderPane root = new BorderPane(label);
        root.setBottom(actionPane);
        root.setPadding(new Insets(10));

        Scene scene = new Scene(root);
        scene.getStylesheets().add(defaultStyleSheet);

        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle(title);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setAlwaysOnTop(true);

        stage.show();
    }

    @Override
    public void warn(String title, String message) {
        Stage stage = new Stage(StageStyle.UTILITY);

        Button button = new Button("OK");
        button.setOnAction(event -> stage.close());
        button.setMinWidth(80);

        Region hSpacer1 = new Region();
        HBox.setHgrow(hSpacer1, Priority.ALWAYS);
        Region hSpacer2 = new Region();
        HBox.setHgrow(hSpacer2, Priority.ALWAYS);

        final HBox actionPane = new HBox(10, hSpacer1, button, hSpacer2);
        actionPane.setPadding(new Insets(10, 0, 0, 0));

        Label label = new Label(message);
        label.setWrapText(true);
        label.setFont(Font.font(14));

        final BorderPane root = new BorderPane(label);
        root.setBottom(actionPane);
        root.setPadding(new Insets(10));

        Scene scene = new Scene(root);
        scene.getStylesheets().add(defaultStyleSheet);

        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle(title);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setAlwaysOnTop(true);

        stage.show();
    }

    private boolean response;
    @Override
    public boolean ask(String title, String question) {
        response = false;
        Stage stage = new Stage(StageStyle.UTILITY);

        Button btnYes, btnNo;
        btnYes = new Button("Yes");
        btnYes.setOnAction(event -> {
            response = true;
            stage.close();
        });
        btnYes.setMinWidth(80);

        btnNo = new Button("No");
        btnNo.setOnAction(event -> {
            response = false;
            stage.close();
        });
        btnNo.setMinWidth(80);

        Region hSpacer = new Region();
        HBox.setHgrow(hSpacer, Priority.ALWAYS);

        final HBox actionPane = new HBox(10, hSpacer, btnYes, btnNo);
        actionPane.setPadding(new Insets(10, 0, 0, 0));

        Label label = new Label(question);
        label.setWrapText(true);
        label.setFont(Font.font(14));

        final BorderPane root = new BorderPane(label);
        root.setBottom(actionPane);
        root.setPadding(new Insets(10));

        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());

        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle(title);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.showAndWait();
        stage.setAlwaysOnTop(true);

        return response;
    }

    @Override
    public void exception(String title, Exception ex) {
        final Label lblTitle = new Label(title);
        lblTitle.setFont(Font.font(16));

        StringWriter sw = new StringWriter();
        ex.printStackTrace(new PrintWriter(sw));
        String exceptionAsString = sw.toString();

        final Stage stage = new Stage(StageStyle.UTILITY);

        final TextArea textArea = new TextArea(exceptionAsString);
        textArea.setEditable(false);

        final Button button = new Button("OK");
        button.setOnAction(event -> stage.close());
        button.setMinWidth(80);

        Region hSpacer1 = new Region();
        HBox.setHgrow(hSpacer1, Priority.ALWAYS);
        Region hSpacer2 = new Region();
        HBox.setHgrow(hSpacer2, Priority.ALWAYS);

        final HBox actionPane = new HBox(10, hSpacer1, button, hSpacer2);
        actionPane.setPadding(new Insets(10, 0, 0, 0));

        final BorderPane root = new BorderPane(textArea);
        root.setTop(new HBox(10, lblTitle));
        root.setBottom(actionPane);
        root.setPadding(new Insets(10));

        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());

        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Exception - " + ex.getClass().getSimpleName());
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setAlwaysOnTop(true);

        stage.show();
    }

    void setDefaultStyleSheet(String defaultStyleSheet) {
        this.defaultStyleSheet = defaultStyleSheet;
    }
}
