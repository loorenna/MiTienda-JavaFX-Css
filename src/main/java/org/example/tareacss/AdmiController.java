package org.example.tareacss;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;

public class AdmiController {
    @FXML
    public TextField codigo;
    @FXML
    public TextField precio;
    @FXML
    public TextField nombre;
    @FXML
    public TextField stock;
    @FXML
    public ComboBox<String> categoria;
    @FXML
    public ComboBox<String> estado;
    @FXML
    public Button Salir;
    @FXML
    public Button nuevo;
    @FXML
    public Button Guardar;
    @FXML
    public Button Actualizar;
    @FXML
    public Button Eliminar;
    @FXML
    public Button Limpiar;
    @FXML
    public TableView<Producto> tablaProductos;
    @FXML
    public TextField Buscar;
    @FXML
    private TableColumn<Producto,String> colcod;

    @FXML
    private TableColumn<Producto,String> colnom;

    @FXML
    private TableColumn<Producto,String> colcat;

    @FXML
    private TableColumn<Producto,Double> colpre;

    @FXML
    private TableColumn<Producto,Integer> colstock;
    @FXML
    private TableColumn<Producto,String> colestado;
    @FXML
    private TableColumn<Producto, Void> colaccion;

    private ObservableList<Producto> productos = FXCollections.observableArrayList();

    @FXML
    public void initialize() {


        categoria.getItems().addAll("Ropa", "Calzado", "Accesorios");
        estado.getItems().addAll("Activo", "Inactivo");

        FilteredList<Producto> filtrados = new FilteredList<>(productos, p -> true);

        Buscar.textProperty().addListener((observable, oldValue, newValue) -> {
            filtrados.setPredicate(producto -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                return producto.getNombre().toLowerCase().contains(newValue.toLowerCase());
            });
        });

        tablaProductos.setItems(filtrados);

        tablaProductos.setOnMouseClicked(e -> cargarSeleccion());

        colcod.setCellValueFactory(new PropertyValueFactory<>("codigo"));
        colnom.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colcat.setCellValueFactory(new PropertyValueFactory<>("categoria"));
        colpre.setCellValueFactory(new PropertyValueFactory<>("precio"));
        colstock.setCellValueFactory(new PropertyValueFactory<>("stock"));
        colestado.setCellValueFactory(new PropertyValueFactory<>("estado"));


        colaccion.setCellFactory(col -> new TableCell<>() {
            private final Button editar = new Button("🖋");
            private final Button eliminar = new Button("🗑");

            {
                editar.setStyle("-fx-background-color: #3B82F6; " + "-fx-text-fill: white; " + "-fx-background-radius: 5; " + "-fx-cursor: hand; " + "-fx-font-size: 14px;"+"-fx-alignment: CENTER;");
                editar.setPrefSize(35, 35);

                eliminar.setStyle("-fx-background-color: #EF4444; " + "-fx-text-fill: white; " + "-fx-background-radius: 5; " + "-fx-cursor: hand; " + "-fx-font-size: 14px;"+"-fx-alignment: CENTER;");
                eliminar.setPrefSize(35, 35);
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);

                if (empty) {
                    setGraphic(null);
                } else {
                    HBox contenedor = new HBox(8, editar, eliminar);
                    contenedor.setAlignment(Pos.CENTER);
                    setGraphic(contenedor);
                }
            }
        });
        colestado.setCellFactory(column -> new TableCell<>() {
            private final Label etiqueta = new Label();

            {
                etiqueta.setPadding(new Insets(4, 12, 4, 12));
                etiqueta.setStyle("-fx-background-radius: 5; -fx-font-weight: bold;");
            }

            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);

                if (empty || item == null) {
                    setGraphic(null);
                } else {
                    etiqueta.setText(item);

                    if (item.equalsIgnoreCase("Activo")) {
                        etiqueta.setStyle("-fx-background-color: #DCFCE7; " + "-fx-text-fill: #16A34A; " + "-fx-background-radius: 4;");
                    } else if (item.equalsIgnoreCase("Inactivo")) {
                        etiqueta.setStyle("-fx-background-color: #FEE2E2; " + "-fx-text-fill: #DC2626; " + "-fx-background-radius: 4;");
                    }
                    setAlignment(Pos.CENTER);
                    setGraphic(etiqueta);
                }
            }
        });

    }
    private void cargarSeleccion() {
        Producto p = tablaProductos.getSelectionModel().getSelectedItem();
        if (p == null) return;

        codigo.setText(p.getCodigo());
        nombre.setText(p.getNombre());
        precio.setText(String.valueOf(p.getPrecio()));
        stock.setText(String.valueOf(p.getStock()));
        categoria.setValue(p.getCategoria());
        estado.setValue(p.getEstado());
    }
    @FXML
    public void NuevoPro(){
        Limpiar();
    }
    @FXML
    public void Guardar(){
        int Stock=Integer.parseInt(stock.getText());
        double Precio=Double.parseDouble(precio.getText());
        Producto p=new Producto(codigo.getText(),estado.getValue(),Stock,Precio,categoria.getValue(),nombre.getText());
        productos.add(p);
        Limpiar();
    }
    @FXML
    public void Actualizar() {

        Producto selec = tablaProductos.getSelectionModel().getSelectedItem();
        if (selec == null) return;

        selec.setCodigo(codigo.getText());
        selec.setNombre(nombre.getText());
        selec.setPrecio(Double.parseDouble(precio.getText()));
        selec.setStock(Integer.parseInt(stock.getText()));
        selec.setCategoria(categoria.getValue());
        selec.setEstado(estado.getValue());

        tablaProductos.refresh();
    }

    @FXML
    public void Eliminar(){
        Producto selec=tablaProductos.getSelectionModel().getSelectedItem();
        if (selec!=null){
            productos.remove(selec);
        }
    }
    @FXML
    public void Limpiar(){
        codigo.clear();
        nombre.clear();
        precio.clear();
        stock.clear();
        categoria.setValue(null);
        estado.setValue(null);
    }
    @FXML
    public void Salir(){
        Platform.exit();
    }

}
