import java.net.URL;
import java.util.*;
import javafx.application.Application;
import javafx.fxml.*;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.scene.control.ScrollPane;
import javafx.event.ActionEvent;

public class CalcController extends Application implements Initializable{
    private static final DoubleMatrix dMap = new DoubleMatrix();
    
    //private static final RationalMatrix rMap = new RationalMatrix();
    
    //private static final ComplexMatrix cMap = new ComplexMatrix();
    
    @FXML
    private CheckBox printSwitch;
    
    @FXML
    private TabPane tabs;
    
    @FXML
    private TextField console, newName, nameField, rowField, colField;
    
    @FXML
    private ScrollPane scrPane;
    
    @FXML
    private BorderPane createPane;
    
    @FXML
    private TextArea outputWindow;
    
    @FXML
    private Button printBtn, sumBtn, detBtn, addBtn, subtractBtn, multiplyBtn, enterBtn, createBtn;
    
    @FXML
    private ComboBox<String> matrixBox, m1Box, m2Box;
    
    @FXML
    private GridPane textMatrix;
    
    @FXML
    private TextField[][] mTexxtBox;
    
    @FXML
    private void handleEnterButton(ActionEvent event){
        int row = Integer.parseInt(rowField.getText()),
            col = Integer.parseInt(colField.getText());
        textMatrix = new GridPane();
        mTexxtBox = new TextField[row][col];
        for(int i = 0; i < row; ++i)
            for(int j = 0; j < col; ++j)
                textMatrix.add(mTexxtBox[i][j] = new TextField(), j, i);
        createPane.setCenter(textMatrix);
        BorderPane.setAlignment(textMatrix, Pos.BOTTOM_CENTER);
        createBtn.setVisible(true);
    }
    
    @FXML
    private void handleCreateButton(ActionEvent event){
        int row = Integer.parseInt(rowField.getText()),
            col = Integer.parseInt(colField.getText());
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < row; ++i)
            for(int j = 0; j < col; ++j)
                sb.append(mTexxtBox[i][j].getText().trim()).append(" ");
        String command = "Matrix Create " + nameField.getText() + " " + row + " " + col + " " + sb.toString();
        dMap.processIO(new Scanner(command), null);
        String item = dMap.processIO(new Scanner("Matrix Name " + nameField.getText()), null).trim();
        if(!matrixBox.getItems().contains(item)){
            matrixBox.getItems().add(item);
            m1Box.getItems().add(item);
            m2Box.getItems().add(item);
        }
        tabs.getSelectionModel().select(0);
    }
    
    @FXML
    private void handleCommandEntered(ActionEvent event){
        outputWindow.appendText(dMap.processIO(new Scanner(console.getText()), null));
    }
    
    @FXML
    private void handlePrintSwitched(ActionEvent event){
        if(((CheckBox)event.getSource()).isSelected())
            dMap.processIO(new Scanner("Matrix PrintResult true"), null);
        else
            dMap.processIO(new Scanner("Matrix PrintResult false"), null);
    }
    
    @FXML
    private void handleUnaryOp(ActionEvent event){
        String matrix = new Scanner(matrixBox.getValue()).next();
        String command = "Matrix " + ((Button)event.getSource()).getText() + " " + matrix;
        outputWindow.appendText(dMap.processIO(new Scanner(command), null));
    }
    
    @FXML
    private void handleBinaryOp(ActionEvent event){
        String m1 = new Scanner(m1Box.getValue()).next(),
            m2 = new Scanner(m2Box.getValue()).next(),
            m3 = newName.getText().trim();
        String command = "Matrix " + ((Button)event.getSource()).getText() + " " + m1 + " " + m2 + " " + m3;
        outputWindow.appendText(dMap.processIO(new Scanner(command), null));
        String item = dMap.processIO(new Scanner("Matrix Name " + m3), null).trim();
        if(!matrixBox.getItems().contains(item)){
            matrixBox.getItems().add(item);
            m1Box.getItems().add(item);
            m2Box.getItems().add(item);
        }
    }
    
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("calc.fxml"));
        primaryStage.setTitle("Matrix Calculator");
        primaryStage.setScene(new Scene(root, 689, 515));
        primaryStage.show();
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources){
        
        String input = "Matrix Create $x 2 2 2.0 3.0 4.0 5.0\n" +
                "Matrix Create $abc 3 2 2.0 3.0 4.0 2.0 3.0 5.0\n" +
                "Matrix Create $tuv 2 2 2.0 3.0 4.0 5.0\n" +
                "Matrix Create $x1 2 2 1.0 3.0 2.0 4.0\n" +
                "Matrix Create $y 2 2 2.0 2.0 -2.0 -2.0\n" +
                "Matrix Create $z 1 2 1.0 1.0\n";
        dMap.processIO(new Scanner(input), null);
        String info[] = new String[5];
        Scanner mScanner = new Scanner(dMap.processIO(new Scanner("TreeMap Info"), null));
        mScanner.nextLine();
        for(int i = 0; i < 5; ++i)
            info[i] = mScanner.nextLine().substring(1);
        matrixBox.getItems().addAll(info);
        m1Box.getItems().addAll(info);
        m2Box.getItems().addAll(info);
    }
    
    public static void main(String[] args){
        launch(args);
    }
}
