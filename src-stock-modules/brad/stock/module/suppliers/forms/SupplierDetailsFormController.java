package brad.stock.module.suppliers.forms;

import brad.stock.sdk.api.DesktopPlatform;
import brad.stock.sdk.data.beans.Supplier;
import brad.util.sys.BRADException;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

import static java.awt.Toolkit.getDefaultToolkit;

public class SupplierDetailsFormController implements Initializable {

    private DesktopPlatform platform;
    private Stage stage;

    @FXML private TextField fldID;
    @FXML private CheckBox chkActive;
    @FXML private TextField fldCompanyName;
    @FXML private TextField fldBusinessPhone;
    @FXML private TextField fldMobile;
    @FXML private TextField fldFax;
    @FXML private TextField fldPostalCode;
    @FXML private TextField fldEmail;
    @FXML private TextField fldWebPage;
    @FXML private TextArea fldAddress;
    @FXML private TextField fldStreet;
    @FXML private TextField fldCity;
    @FXML private TextField fldRegion;
    @FXML private TextField fldCountry;
    @FXML private TextArea fldNotes;
    @FXML private Button btnSave;
    @FXML private Button btnNew;

    public void setDesktopPlatform(DesktopPlatform platform) {
        this.platform = platform;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        assert fldID != null;
        assert chkActive != null;
        assert fldCompanyName != null;
        assert fldBusinessPhone != null;
        assert fldMobile != null;
        assert fldFax != null;
        assert fldPostalCode != null;
        assert fldEmail != null;
        assert fldWebPage != null;
        assert fldAddress != null;
        assert fldStreet != null;
        assert fldCity != null;
        assert fldRegion != null;
        assert fldCountry != null;
        assert fldNotes != null;
        assert btnSave != null;
        assert btnNew != null;

        fldAddress.setWrapText(true);
        fldNotes.setWrapText(true);
    }

    @FXML
    private void btnSaveClicked() {
        Supplier supplier = new Supplier();
        supplier.setActive(chkActive.isSelected());
        supplier.setName(fldCompanyName.getText().trim());
        supplier.setBusinessPhone(fldBusinessPhone.getText().trim());
        supplier.setMobile(fldMobile.getText().trim());
        supplier.setFax(fldFax.getText().trim());
        supplier.setPostalCode(fldPostalCode.getText().trim());
        supplier.setEmail(fldEmail.getText().trim());
        supplier.setWebPage(fldWebPage.getText().trim());
        supplier.setAddress(fldAddress.getText().trim());
        supplier.setStreet(fldStreet.getText().trim());
        supplier.setCity(fldCity.getText().trim());
        supplier.setRegion(fldRegion.getText().trim());
        supplier.setCountry(fldCountry.getText().trim());
        supplier.setNotes(fldNotes.getText().trim());

        if (fldID.getText().trim().equals("(NEW)")) {
            if (validated()) {
                try {
                    final long id = platform.getDataManager().getSupplierManager().create(supplier);
                    fldID.setText("" + id);
                    supplier.setId(id);
                } catch (BRADException ex) {
                    platform.getPromptUtilities().exception("Error Saving New Supplier", ex);
                }
            }
        } else {
            if (validated()) {
                try {
                    supplier.setId(Long.parseLong(fldID.getText().trim()));
                    platform.getDataManager().getSupplierManager().update(supplier);
                } catch (BRADException ex) {
                    platform.getPromptUtilities().exception("Error Updating Supplier", ex);
                }
            } else {
                getDefaultToolkit().beep();
            }
        }
    }

    @FXML
    private void btnNewClicked() {
        if (fldID.getText().trim().equals("(NEW)")) {
            if (platform.getPromptUtilities().ask("New Record", "You are currently on a new record.\n"+
                    "Do you wish to discard your current changes?"))
                clearFields();
        } else {
            clearFields();
        }
    }

    @FXML
    public void setOnCloseAction() {
        if (stage != null && validated()) {
            btnSaveClicked();
            stage.close();
        } else
            getDefaultToolkit().beep();
    }

    private void clearFields() {
        fldID.setText("(NEW)");
        chkActive.setSelected(true);
        fldCompanyName.clear();
        fldBusinessPhone.clear();
        fldMobile.clear();
        fldFax.clear();
        fldPostalCode.clear();
        fldEmail.clear();
        fldWebPage.clear();
        fldAddress.clear();
        fldStreet.clear();
        fldCity.clear();
        fldRegion.clear();
        fldCountry.clear();
        fldNotes.clear();
    }

    private boolean validated() {
        if (fldCompanyName.getText().trim().isEmpty()) {
            fldCompanyName.requestFocus();
            return false;
        }

        if (fldAddress.getText().trim().isEmpty()) {
            fldAddress.requestFocus();
            return false;
        }

        if (fldBusinessPhone.getText().trim().isEmpty()) {
            fldBusinessPhone.requestFocus();
            return false;
        }

        if (fldEmail.getText().trim().isEmpty()) {
            fldEmail.requestFocus();
            return false;
        }

        return true;
    }

    public void updateFields(Supplier supplier) {
        fldID.setText(supplier.getId() + "");
        chkActive.setSelected(supplier.isActive());
        fldCompanyName.setText(supplier.getName());
        fldBusinessPhone.setText(supplier.getBusinessPhone());
        fldMobile.setText(supplier.getMobile());
        fldFax.setText(supplier.getFax());
        fldPostalCode.setText(supplier.getPostalCode());
        fldEmail.setText(supplier.getEmail());
        fldWebPage.setText(supplier.getWebPage());
        fldAddress.setText(supplier.getAddress());
        fldStreet.setText(supplier.getStreet());
        fldCity.setText(supplier.getCity());
        fldRegion.setText(supplier.getRegion());
        fldCountry.setText(supplier.getCountry());
        fldNotes.setText(supplier.getNotes());
    }
}
