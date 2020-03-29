ADD VM Options (IntelliJ)
1. Download JavaFX jdk
2. Unzip and save on your pc
3. Form the main menu, select **RUN** | **Edit Configuration**
4. Select **Application** | **Main** from the list on the left
5. In the **VM options** field, specify: `--module-path %PATH_TO_FX% --add-modules javafx.controls,javafx.fxml`
6. Instead of `%PATH_TO_FX%`, specify the path to the JavaFX SDK **lib** directory
7. Apply the change