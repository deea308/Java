package gui;

import domain.Travel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;


import javafx.event.ActionEvent;
import service.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class Controller {
    private Service service;

    public Controller(Service service) {
        this.service = service;
    }

    private ObservableList<Travel> travelObservableList;
    @FXML
    private ListView<Travel> travelListView;
    @FXML
    private TextField descriptionTextField;
    @FXML
    private VBox updateBox;
    @FXML
    private ComboBox<String> continentComboBox;
    @FXML
    private TextField searchTextField;

    @FXML
    private Button updateButton;
    @FXML
    private TextField searchPlacesTextField;



    void initializeOrReset(){
        travelObservableList = FXCollections.observableArrayList();

        // Retrieve all recipes, sort by cuisine, and add to the observable list
        List<Travel> travels = new ArrayList<>((Collection) this.service.getAll());
        travels = travels.stream()
                .sorted((r1, r2) -> r1.getContinent().compareTo(r2.getContinent()))
                .toList();

        travelObservableList.addAll(travels);
        travelListView.setItems(travelObservableList);

        // Set a custom cell factory to display only name, cuisine, and description
        travelListView.setCellFactory(listView -> new ListCell<>() {
            @Override
            protected void updateItem(Travel travel, boolean empty) {
                super.updateItem(travel, empty);

                if (empty || travel == null) {
                    setText(null);
                } else {
                    setText(String.format("%s (%s): %s", travel.getName(), travel.getContinent(), travel.getDescription()));
                }
            }
        });
        List<String> continents = new ArrayList<>();
        for(Travel travel : travels){
            if(!continents.contains(travel.getContinent())){
                continents.add(travel.getContinent());
            }
        }
        continentComboBox.setItems(FXCollections.observableArrayList(continents));
        // Add listeners for filtering
        searchTextField.textProperty().addListener((observable, oldValue, newValue) -> handleFilterTravel());
        continentComboBox.setOnAction(this::setContinentComboBox);
    }



    @FXML
    void setContinentComboBox(ActionEvent event) {
        handleFilterTravel();
    }

    public void initialize(){
        //travelListView.setItems(travelObservableList);
        initializeOrReset();
        travelListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            boolean hasSelection = newValue != null;
            updateBox.setDisable(!hasSelection);
            descriptionTextField.setDisable(!hasSelection);
            if (hasSelection) {
                descriptionTextField.setText(newValue.getDescription());
            } else {
                descriptionTextField.clear();
            }
        });
    }

    @FXML
    void handleFilterTravel(){
        String selectedContinent = continentComboBox.getSelectionModel().getSelectedItem();
        String searchText = searchTextField.getText().toLowerCase();

        List<Travel> filteredRecipes=travelObservableList.stream()
                .filter(recipe->(  recipe.getContinent().equals(selectedContinent) )
                        && (
                        recipe.getName().toLowerCase().contains(searchText)
                                ||recipe.getDescription().toLowerCase().contains(searchText))).toList();

        //update the list view with filtered
        travelListView.setItems(FXCollections.observableArrayList(filteredRecipes));
    }

    @FXML
    void handleSearchTextChange(){
        searchTextField.textProperty().addListener((observable, oldValue, newValue) -> {});
    }

    @FXML
    public void handleUpdateButtonAction(javafx.event.ActionEvent event) {
        Travel selectedTravel = travelListView.getSelectionModel().getSelectedItem();
        if (selectedTravel != null) {
            System.out.println("Selected itinerary: " + selectedTravel.getName());

            String newDescription = descriptionTextField.getText();
            System.out.println("Updating description to: " + newDescription);

            // Update the description in the object
            selectedTravel.setDescription(newDescription);

            // Update the database
            service.updateRecipe(selectedTravel);

            // Refresh the ListView
            initializeOrReset();
        } else {
            System.out.println("No itinerary selected.");
            Alert alert = new Alert(Alert.AlertType.WARNING, "No itinerary selected");
            alert.showAndWait();
        }

    }

    @FXML
    public void handleSearchPlaces() {
        String[] queries = searchPlacesTextField.getText().toLowerCase().split(", ");

        // Filter the travels based on places containing the search query
        List<Travel> filteredTravel = travelObservableList.stream()
                .filter(travel -> {
                    // Convert the places list to a single string for easy comparison
                    String places = String.join(", ", travel.getPlaces()).toLowerCase();

                    // Check if all search queries are present in the places list
                    return Arrays.stream(queries).allMatch(places::contains);
                })
                .collect(Collectors.toList());

        // If there are no matches, show an alert
        if (filteredTravel.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "No matching places found.");
            alert.setTitle("Search Result");
            alert.showAndWait();
        }

        // Update the ListView with the filtered results
        travelListView.setItems(FXCollections.observableArrayList(filteredTravel));

    }
}
